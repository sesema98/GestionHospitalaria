import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ListaPacientes from '../components/ListaPacientes';
import DetallesPaciente from '../components/DetallesPaciente';
import '../App.css'; // Importamos los estilos

const API_BASE_URL = 'http://localhost:8080/api';

const PacientesPage = () => {
  const [pacientes, setPacientes] = useState([]);
  const [selectedPaciente, setSelectedPaciente] = useState(null);
  const [detalles, setDetalles] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');

  // Cargar la lista inicial de pacientes
  useEffect(() => {
    axios.get(`${API_BASE_URL}/pacientes`)
      .then(res => {
        setPacientes(res.data);
      })
      .catch(err => {
        console.error("Error al cargar pacientes:", err);
        setError('No se pudieron cargar los pacientes. Revisa la consola y asegúrate de que el backend esté funcionando.');
      });
  }, []);

  // Función para cargar todos los detalles de un paciente seleccionado
  const handleSelectPaciente = (paciente) => {
    if (selectedPaciente && selectedPaciente.idPaciente === paciente.idPaciente) {
      setSelectedPaciente(null); // Permite deseleccionar
      return;
    }
    setSelectedPaciente(paciente);
    setIsLoading(true);
    setDetalles({});
    setError('');

    const citasReq = axios.get(`${API_BASE_URL}/pacientes/${paciente.idPaciente}/citas`);
    const consultasReq = axios.get(`${API_BASE_URL}/pacientes/${paciente.idPaciente}/consultas`);
    const hospReq = axios.get(`${API_BASE_URL}/pacientes/${paciente.idPaciente}/hospitalizaciones`);
    const facturasReq = axios.get(`${API_BASE_URL}/pacientes/${paciente.idPaciente}/facturas`);

    Promise.all([citasReq, consultasReq, hospReq, facturasReq])
      .then(responses => {
        setDetalles({
          citas: responses[0].data,
          consultas: responses[1].data,
          hospitalizaciones: responses[2].data,
          facturas: responses[3].data,
        });
      })
      .catch(err => {
        console.error("Error al cargar detalles:", err);
        setError('No se pudieron cargar los detalles del paciente.');
      })
      .finally(() => setIsLoading(false));
  };

  // Función para manejar el pago de una factura
  const handlePagarFactura = (facturaId) => {
    axios.put(`${API_BASE_URL}/facturas/${facturaId}/pagar`)
      .then(() => {
        // Al pagar, refrescamos los datos para ver el cambio de estado
        handleSelectPaciente(selectedPaciente); 
      })
      .catch(err => {
        console.error("Error al pagar factura:", err);
        setError('No se pudo actualizar la factura.');
      });
  };

  return (
    <div className="page-layout">
      <div className="sub-sidebar">
        <h3>Lista de Pacientes</h3>
        <ListaPacientes 
          pacientes={pacientes} 
          onSelectPaciente={handleSelectPaciente}
          selectedPacienteId={selectedPaciente?.idPaciente}
        />
      </div>
      <div className="page-content">
        {error && <p className="error-message">{error}</p>}
        {selectedPaciente ? (
          <DetallesPaciente 
            paciente={selectedPaciente}
            detalles={detalles}
            isLoading={isLoading}
            onPagarFactura={handlePagarFactura} // Pasamos la función de pagar
          />
        ) : (
          <div className="placeholder">Selecciona un paciente para ver su historial completo</div>
        )}
      </div>
    </div>
  );
};

export default PacientesPage;
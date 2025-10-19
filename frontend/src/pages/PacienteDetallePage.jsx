import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';
import '../App.css';

const PacienteDetallePage = () => {
  const { pacienteId } = useParams();
  const [paciente, setPaciente] = useState(null);
  const [citas, setCitas] = useState([]);
  const [facturas, setFacturas] = useState([]); // Add state for invoices
  const API_BASE_URL = 'http://localhost:8080/api';

  const fetchPacienteData = () => {
    // Fetch patient details
    axios.get(`${API_BASE_URL}/pacientes/${pacienteId}`).then(res => setPaciente(res.data));
    
    // Fetch and filter citas for the patient
    axios.get(`${API_BASE_URL}/citas`).then(res => {
      const citasDelPaciente = res.data.filter(c => c.paciente.idPaciente == pacienteId);
      setCitas(citasDelPaciente);
    });
    
    // Fetch and filter facturas for the patient
    axios.get(`${API_BASE_URL}/facturas`).then(res => {
      const facturasDelPaciente = res.data.filter(f => f.paciente.idPaciente == pacienteId);
      setFacturas(facturasDelPaciente);
    });
  };

  useEffect(() => {
    fetchPacienteData();
  }, [pacienteId]);

  const handleAtenderCita = (citaId) => {
    alert(`Lógica para atender la cita ${citaId}. ¡Esto crearía una nueva consulta!`);
  };

  // 👇 NEW FUNCTION TO HANDLE PAYMENT 👇
  const handlePagarFactura = (facturaId) => {
    axios.put(`${API_BASE_URL}/facturas/${facturaId}/pagar`)
      .then(response => {
        // If successful, refresh the data to show the change
        alert(`Factura #${facturaId} marcada como pagada.`);
        fetchPacienteData(); // Re-fetch all data to update the UI
      })
      .catch(error => {
        console.error("Error al pagar la factura:", error);
        alert("No se pudo actualizar la factura.");
      });
  };

  if (!paciente) return <p>Cargando...</p>;

  return (
    <div>
      <Link to="/" className="back-button">← Volver a la lista</Link>
      <div className="data-card">
        <h1>Detalles de {paciente.nombres} {paciente.apellidos}</h1>
        {/* Patient details */}
      </div>

      <div className="data-card">
        <h2>Historial de Citas</h2>
        {/* Citas mapping code */}
      </div>

      {/* 👇 NEW SECTION FOR INVOICES WITH THE BUTTON 👇 */}
      <div className="data-card">
        <h2>Historial de Facturas</h2>
        {facturas.map(factura => (
          <div key={factura.idFactura} className="data-item">
            <p>
              <strong>Fecha:</strong> {factura.fechaEmision} | <strong>Total:</strong> S/ {factura.total} | <strong>Estado:</strong> {factura.estado}
            </p>
            {factura.estado === 'pendiente' && (
              <button onClick={() => handlePagarFactura(factura.idFactura)} className="action-button-pay">
                Marcar como Pagado
              </button>
            )}
          </div>
        ))}
      </div>
    </div>
  );
};

export default PacienteDetallePage;
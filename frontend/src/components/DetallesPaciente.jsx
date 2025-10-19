import React from 'react';
import ListaCitas from './ListaCitas';
import ListaConsultas from './ListaConsultas';
import ListaHospitalizaciones from './ListaHospitalizaciones';
import ListaFacturas from './ListaFacturas';

const DetallesPaciente = ({ paciente, detalles, isLoading, onPagarFactura }) => {
  if (isLoading) {
    return <div className="placeholder">Cargando detalles de {paciente.nombres}...</div>;
  }
  
  return (
    <div>
      <div className="data-card patient-header">
        <h2>Detalles de: {paciente.nombres} {paciente.apellidos}</h2>
        <p><strong>DNI:</strong> {paciente.dni} | <strong>Correo:</strong> {paciente.correo || "—"} | <strong>Teléfono:</strong> {paciente.telefono || "—"}</p>
      </div>

      {/* Renderiza cada sección solo si hay datos */}
      {detalles.citas && <ListaCitas citas={detalles.citas} />}
      {detalles.consultas && <ListaConsultas consultas={detalles.consultas} />}
      {detalles.hospitalizaciones && <ListaHospitalizaciones hospitalizaciones={detalles.hospitalizaciones} />}
      {detalles.facturas && <ListaFacturas facturas={detalles.facturas} onPagarFactura={onPagarFactura} />}
    </div>
  );
};

export default DetallesPaciente;
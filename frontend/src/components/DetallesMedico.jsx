import React from 'react';
import ListaConsultas from './ListaConsultas';

const DetallesMedico = ({ medico, consultas, isLoading }) => {
  if (!medico) return null;
  return (
    <div>
      <div className="data-card patient-header">
        <h2>Dr(a). {medico.nombres} {medico.apellidos}</h2>
        <p><strong>Colegiatura:</strong> {medico.colegiatura || "—"} &nbsp;|&nbsp; <strong>Especialidad:</strong> {medico.especialidad || "—"}</p>
        <p><strong>Correo:</strong> {medico.correo || "—"} &nbsp;|&nbsp; <strong>Teléfono:</strong> {medico.telefono || "—"}</p>
      </div>
      {isLoading ? <div className="placeholder">Cargando consultas del médico...</div> : <ListaConsultas consultas={consultas} />}
    </div>
  );
};

export default DetallesMedico;
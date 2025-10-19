import React from 'react';

const ListaHospitalizaciones = ({ hospitalizaciones, onDarDeAlta }) => {
  return (
    <div className="data-card">
      <h2>Hospitalizaciones</h2>
      {hospitalizaciones.map(h => (
        <div key={h.idHosp} className="data-item">
          <span><strong>Paciente:</strong> {h.paciente.nombres} {h.paciente.apellidos}</span><br/>
          <span><strong>Habitación:</strong> {h.habitacion.numero} | <strong>Ingreso:</strong> {h.fechaIngreso} | <strong>Estado:</strong> {h.estado}</span>
          
          {h.estado === 'activo' && onDarDeAlta && (
            <button
              className="action-button"
              onClick={() => onDarDeAlta(h.idHosp)}
            >
              Dar de Alta
            </button>
          )}
        </div>
      ))}
    </div>
  );
};
export default ListaHospitalizaciones;
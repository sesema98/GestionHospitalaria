import React from 'react';

const ListaCitas = ({ citas, onMarcarAtendida }) => {
  return (
    <div className="data-card">
      <h2>Citas Programadas</h2>
      {citas.length === 0 ? <p>No hay citas para mostrar.</p> : citas.map(c => (
        <div key={c.idCita} className="data-item">
          <span><strong>Paciente:</strong> {c.paciente.nombres} {c.paciente.apellidos}</span><br/>
          <span><strong>Médico:</strong> {c.medico.nombres} {c.medico.apellidos}</span><br/>
          <span><strong>Fecha:</strong> {c.fecha} - <strong>Estado:</strong> {c.estado}</span>

          {/* --- LÓGICA DEL BOTÓN DINÁMICO --- */}
          {/* Si la cita está 'Programada', muestra el botón. Si no, no muestra nada. */}
          {c.estado === 'Programada' && onMarcarAtendida && (
            <button 
              className="action-button" 
              onClick={() => onMarcarAtendida(c.idCita)}>
              Marcar como Atendida
            </button>
          )}
        </div>
      ))}
    </div>
  );
};

export default ListaCitas;
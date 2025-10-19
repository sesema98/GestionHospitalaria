import React from 'react';

const ListaMedicos = ({ medicos, onSelectMedico, selectedMedicoId }) => {
  return (
    <div className="patient-list">
      {medicos.map(m => (
        <button
          key={m.idMedico}
          className={`patient-button ${m.idMedico === selectedMedicoId ? 'active' : ''}`}
          onClick={() => onSelectMedico && onSelectMedico(m)}
        >
          Dr. {m.nombres} {m.apellidos}
        </button>
      ))}
    </div>
  );
};

export default ListaMedicos;
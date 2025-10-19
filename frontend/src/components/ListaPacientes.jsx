import React from 'react';

const ListaPacientes = ({ pacientes, onSelectPaciente, selectedPacienteId }) => {
  return (
    <div className="paciente-lista">
      {pacientes.map((p) => (
        <button
          key={p.idPaciente}
          className={`paciente-item ${selectedPacienteId === p.idPaciente ? 'active' : ''}`}
          onClick={() => onSelectPaciente(p)}
        >
          {p.nombres} {p.apellidos}
        </button>
      ))}
    </div>
  );
};

export default ListaPacientes;

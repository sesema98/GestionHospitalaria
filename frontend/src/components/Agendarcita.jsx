import React, { useState } from 'react';
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const AgendarCita = ({ pacientes, medicos, onCitaAgendada }) => {
  const [pacienteId, setPacienteId] = useState('');
  const [medicoId, setMedicoId] = useState('');
  const [fecha, setFecha] = useState('');
  const [hora, setHora] = useState('');
  const [motivo, setMotivo] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!pacienteId || !medicoId || !fecha || !hora) {
      alert('Por favor, completa todos los campos requeridos.');
      return;
    }
    
    const nuevaCita = {
      paciente: { idPaciente: pacienteId },
      medico: { idMedico: medicoId },
      fecha,
      hora: `${hora}:00`,
      motivo,
      estado: 'Programada'
    };

    axios.post(`${API_BASE_URL}/citas`, nuevaCita)
      .then(() => {
        alert('Cita agendada con éxito!');
        onCitaAgendada(); // Llama a la función para refrescar datos
        // Limpiar formulario
        setPacienteId('');
        setMedicoId('');
        setFecha('');
        setHora('');
        setMotivo('');
      })
      .catch(() => alert('Error al agendar la cita.'));
  };

  return (
    <div className="data-card">
      <h2>Agendar Nueva Cita</h2>
      <form onSubmit={handleSubmit} className="cita-form">
        <select value={pacienteId} onChange={(e) => setPacienteId(e.target.value)} required>
          <option value="">-- Selecciona un Paciente --</option>
          {pacientes.map(p => <option key={p.idPaciente} value={p.idPaciente}>{p.nombres} {p.apellidos}</option>)}
        </select>
        <select value={medicoId} onChange={(e) => setMedicoId(e.target.value)} required>
          <option value="">-- Selecciona un Médico --</option>
          {medicos.map(m => <option key={m.idMedico} value={m.idMedico}>Dr. {m.nombres} {m.apellidos}</option>)}
        </select>
        <input type="date" value={fecha} onChange={(e) => setFecha(e.target.value)} required />
        <input type="time" value={hora} onChange={(e) => setHora(e.target.value)} required />
        <input type="text" value={motivo} onChange={(e) => setMotivo(e.target.value)} placeholder="Motivo de la cita" />
        <button type="submit">Agendar Cita</button>
      </form>
    </div>
  );
};

export default AgendarCita;
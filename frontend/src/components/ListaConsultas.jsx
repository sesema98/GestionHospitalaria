import React from 'react';

const ListaConsultas = ({ consultas }) => {
  return (
    <div className="data-card">
      <h2>Consultas Registradas</h2>
      {consultas.length === 0 ? <p>No hay consultas para mostrar para este médico.</p> : consultas.map(c => (
        <div key={c.idConsulta} className="data-item">
          <span><strong>Paciente:</strong> {c.paciente.nombres} {c.paciente.apellidos}</span><br/>
          <span><strong>Fecha:</strong> {c.fecha} | <strong>Motivo:</strong> {c.motivoConsulta}</span><br/>
          
          {/* ✅ Muestra los diagnósticos de cada consulta */}
          {c.diagnosticos && c.diagnosticos.length > 0 && (
            <div className="diagnostico-list">
              <strong>Diagnósticos:</strong>
              <ul>
                {c.diagnosticos.map(d => (
                  <li key={d.idDiagnostico}>{d.descripcion} ({d.tipo})</li>
                ))}
              </ul>
            </div>
          )}
        </div>
      ))}
    </div>
  );
};

export default ListaConsultas;
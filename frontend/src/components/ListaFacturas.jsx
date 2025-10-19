import React from 'react';

const ListaFacturas = ({ facturas, onPagarFactura }) => {
  return (
    <div className="data-card">
      <h2>Facturas Emitidas</h2>
      {facturas.length === 0 ? <p>No hay facturas para mostrar.</p> : facturas.map(f => (
        <div key={f.idFactura} className="data-item">
          <span><strong>Paciente:</strong> {f.paciente.nombres} {f.paciente.apellidos}</span><br/>
          <span><strong>Fecha:</strong> {f.fechaEmision} | <strong>Total:</strong> S/ {f.total} | <strong>Estado:</strong> {f.estado}</span>
          
          {/* --- LÓGICA DEL BOTÓN DINÁMICO --- */}
          {/* Usamos un operador ternario: si el estado es 'pendiente', muestra el botón, si no, muestra el texto. */}
          {f.estado === 'pendiente' ? (
            <button
              className="action-button"
              onClick={() => onPagarFactura(f.idFactura)}
            >
              Marcar como Pagada
            </button>
          ) : (
            <span className="status-paid">✅ Pagado</span>
          )}
        </div>
      ))}
    </div>
  );
};

export default ListaFacturas;
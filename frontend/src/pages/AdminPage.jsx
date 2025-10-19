import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import ListaFacturas from "../components/ListaFacturas";
import "../App.css";

const API_BASE_URL = "http://localhost:8080/api";

const AdminPage = () => {
  const navigate = useNavigate();

  const [pacientes, setPacientes] = useState([]);
  const [medicos, setMedicos] = useState([]);
  const [especialidades, setEspecialidades] = useState([]);
  const [habitaciones, setHabitaciones] = useState([]);
  const [allHabitaciones, setAllHabitaciones] = useState([]);
  const [facturas, setFacturas] = useState([]);

  const [pacienteData, setPacienteData] = useState({
    nombres: "", apellidos: "", dni: "", telefono: "", correo: "",
  });
  const [newMedico, setNewMedico] = useState({
    nombres: "", apellidos: "", colegiatura: "",
  });
  const [selectedSpecialties, setSelectedSpecialties] = useState(new Set());
  const [consultaData, setConsultaData] = useState({
    pacienteId: "", medicoId: "", diagnostico: "", tratamiento: "",
  });
  const [hospitalizacionData, setHospitalizacionData] = useState({
    pacienteId: "", habitacionId: "", diagnosticoIngreso: "",
  });
  const [newHabitacion, setNewHabitacion] = useState({
    numero: "", tipo: "General",
  });
  const [facturaData, setFacturaData] = useState({
    pacienteId: "", detalles: [{ concepto: "", monto: "" }],
  });

  const fetchAllData = () => {
    axios.get(`${API_BASE_URL}/pacientes`).then((r) => setPacientes(r.data));
    axios.get(`${API_BASE_URL}/medicos`).then((r) => setMedicos(r.data));
    axios.get(`${API_BASE_URL}/especialidades`).then((r) => setEspecialidades(r.data));
    axios.get(`${API_BASE_URL}/habitaciones/disponibles`).then((r) => setHabitaciones(r.data));
    axios.get(`${API_BASE_URL}/habitaciones`).then((r) => setAllHabitaciones(r.data));
    axios.get(`${API_BASE_URL}/facturas`).then((r) => setFacturas(r.data));
  };

  useEffect(() => {
    fetchAllData();
  }, []);

  const handleChange = (setState) => (e) =>
    setState((prev) => ({ ...prev, [e.target.name]: e.target.value }));

  const handleSpecialtyChange = (id) => {
    const updated = new Set(selectedSpecialties);
    updated.has(id) ? updated.delete(id) : updated.add(id);
    setSelectedSpecialties(updated);
  };

  const handleCreatePatient = (e) => {
    e.preventDefault();
    const user = JSON.parse(localStorage.getItem("user"));
    const registroCompleto = { paciente: pacienteData, antecedentes: [] };
    axios
      .post(`${API_BASE_URL}/pacientes`, registroCompleto, {
        headers: { "X-Usuario-ID": user?.idUsuario },
      })
      .then(() => {
        alert("Paciente creado con éxito.");
        setPacienteData({ nombres: "", apellidos: "", dni: "", telefono: "", correo: "" });
        fetchAllData();
      })
      .catch(() => alert("Error al crear paciente."));
  };

  const handleCreateMedico = (e) => {
    e.preventDefault();
    const medicoCompleto = {
      ...newMedico,
      especialidades: [...selectedSpecialties].map((id) => ({ idEspecialidad: id })),
      estado: "activo",
    };
    axios
      .post(`${API_BASE_URL}/medicos`, medicoCompleto)
      .then(() => {
        alert("Médico creado con éxito.");
        setNewMedico({ nombres: "", apellidos: "", colegiatura: "" });
        setSelectedSpecialties(new Set());
        fetchAllData();
      })
      .catch(() => alert("Error al crear médico."));
  };

  const handleCreateConsulta = (e) => {
    e.preventDefault();
    const consulta = {
      paciente: { idPaciente: consultaData.pacienteId },
      medico: { idMedico: consultaData.medicoId },
      diagnostico: consultaData.diagnostico,
      tratamiento: consultaData.tratamiento,
      fecha: new Date().toISOString().split("T")[0],
    };
    axios
      .post(`${API_BASE_URL}/consultas`, consulta)
      .then(() => {
        alert("Consulta registrada con éxito.");
        setConsultaData({ pacienteId: "", medicoId: "", diagnostico: "", tratamiento: "" });
      })
      .catch(() => alert("Error al registrar la consulta."));
  };

  const handleCreateHospitalizacion = (e) => {
    e.preventDefault();
    axios
      .post(`${API_BASE_URL}/hospitalizaciones/ingresar`, hospitalizacionData)
      .then(() => {
        alert("Hospitalización registrada correctamente.");
        setHospitalizacionData({ pacienteId: "", habitacionId: "", diagnosticoIngreso: "" });
        fetchAllData();
      })
      .catch(() => alert("Error al generar hospitalización."));
  };

  const handleCreateHabitacion = (e) => {
    e.preventDefault();
    axios
      .post(`${API_BASE_URL}/habitaciones`, { ...newHabitacion, estado: "disponible" })
      .then(() => {
        alert("Habitación creada con éxito.");
        setNewHabitacion({ numero: "", tipo: "General" });
        fetchAllData();
      })
      .catch(() => alert("Error al crear la habitación."));
  };

  const handleFacturaChange = (index, event) => {
    const values = [...facturaData.detalles];
    values[index][event.target.name] = event.target.value;
    setFacturaData({ ...facturaData, detalles: values });
  };

  const addFacturaDetalle = () => {
    setFacturaData({
      ...facturaData,
      detalles: [...facturaData.detalles, { concepto: "", monto: "" }],
    });
  };

  const handleCreateFactura = (e) => {
    e.preventDefault();
    const facturaToSend = {
      pacienteId: facturaData.pacienteId,
      detalles: facturaData.detalles.filter((d) => d.concepto && d.monto),
    };
    axios
      .post(`${API_BASE_URL}/facturas`, facturaToSend)
      .then(() => {
        alert("Factura generada con éxito.");
        setFacturaData({ pacienteId: "", detalles: [{ concepto: "", monto: "" }] });
        fetchAllData();
      })
      .catch(() => alert("Error al generar la factura."));
  };

  const handlePagarFactura = (facturaId) => {
    axios
      .put(`${API_BASE_URL}/facturas/${facturaId}/pagar`)
      .then(() => {
        alert("Factura marcada como pagada.");
        fetchAllData();
      })
      .catch(() => alert("Error al actualizar la factura."));
  };

  return (
    <div className="admin-wrapper">
      <nav className="admin-navbar">
        <button className="back-button" onClick={() => navigate(-1)}>← Volver</button>
        <h1 className="admin-title">Panel de Administración y Gestión</h1>
      </nav>

      <div className="admin-content">

        {/* === GRUPO 1: MÉDICOS Y CONSULTAS === */}
        <div className="admin-grid">
          <div className="data-card">
            <h3>Registrar Nuevo Médico</h3>
            <form onSubmit={handleCreateMedico} className="cita-form">
              <input name="nombres" placeholder="Nombres" value={newMedico.nombres} onChange={handleChange(setNewMedico)} required />
              <input name="apellidos" placeholder="Apellidos" value={newMedico.apellidos} onChange={handleChange(setNewMedico)} required />
              <input name="colegiatura" placeholder="N° Colegiatura" value={newMedico.colegiatura} onChange={handleChange(setNewMedico)} required />
              <h4>Asignar Especialidades</h4>
              {especialidades.map((spec) => (
                <label key={spec.idEspecialidad}>
                  <input
                    type="checkbox"
                    checked={selectedSpecialties.has(spec.idEspecialidad)}
                    onChange={() => handleSpecialtyChange(spec.idEspecialidad)}
                  /> {spec.nombre}
                </label>
              ))}
              <button type="submit" className="action-button primary">Crear Médico</button>
            </form>
          </div>

          <div className="data-card">
            <h3>Registrar Consulta Médica</h3>
            <form onSubmit={handleCreateConsulta} className="cita-form">
              <select name="pacienteId" value={consultaData.pacienteId} onChange={handleChange(setConsultaData)} required>
                <option value="">-- Selecciona Paciente --</option>
                {pacientes.map((p) => (
                  <option key={p.idPaciente} value={p.idPaciente}>{p.nombres} {p.apellidos}</option>
                ))}
              </select>
              <select name="medicoId" value={consultaData.medicoId} onChange={handleChange(setConsultaData)} required>
                <option value="">-- Selecciona Médico --</option>
                {medicos.map((m) => (
                  <option key={m.idMedico} value={m.idMedico}>Dr. {m.nombres} {m.apellidos}</option>
                ))}
              </select>
              <input name="diagnostico" placeholder="Diagnóstico" value={consultaData.diagnostico} onChange={handleChange(setConsultaData)} required />
              <input name="tratamiento" placeholder="Tratamiento" value={consultaData.tratamiento} onChange={handleChange(setConsultaData)} required />
              <button type="submit" className="action-button success">Registrar Consulta</button>
            </form>
          </div>
        </div>

        {/* === GRUPO 2: PACIENTES Y HOSPITALIZACIÓN === */}
        <div className="admin-grid">
          <div className="data-card">
            <h3>Registrar Paciente</h3>
            <form onSubmit={handleCreatePatient} className="cita-form">
              <input name="nombres" placeholder="Nombres" value={pacienteData.nombres} onChange={handleChange(setPacienteData)} required />
              <input name="apellidos" placeholder="Apellidos" value={pacienteData.apellidos} onChange={handleChange(setPacienteData)} required />
              <input name="dni" placeholder="DNI" value={pacienteData.dni} onChange={handleChange(setPacienteData)} required />
              <input name="telefono" placeholder="Teléfono" value={pacienteData.telefono} onChange={handleChange(setPacienteData)} />
              <input name="correo" placeholder="Correo" value={pacienteData.correo} onChange={handleChange(setPacienteData)} />
              <button type="submit" className="action-button primary">Crear Paciente</button>
            </form>
          </div>

          <div className="data-card">
            <h3>Registrar Hospitalización</h3>
            <form onSubmit={handleCreateHospitalizacion} className="cita-form">
              <select name="pacienteId" value={hospitalizacionData.pacienteId} onChange={handleChange(setHospitalizacionData)} required>
                <option value="">-- Selecciona Paciente --</option>
                {pacientes.map((p) => (
                  <option key={p.idPaciente} value={p.idPaciente}>{p.nombres} {p.apellidos}</option>
                ))}
              </select>
              <select name="habitacionId" value={hospitalizacionData.habitacionId} onChange={handleChange(setHospitalizacionData)} required>
                <option value="">-- Selecciona Habitación --</option>
                {habitaciones.map((h) => (
                  <option key={h.idHabitacion} value={h.idHabitacion}>Hab. {h.numero} ({h.tipo})</option>
                ))}
              </select>
              <input name="diagnosticoIngreso" placeholder="Diagnóstico de Ingreso" value={hospitalizacionData.diagnosticoIngreso} onChange={handleChange(setHospitalizacionData)} required />
              <button type="submit" className="action-button success">Registrar</button>
            </form>
          </div>
        </div>

        {/* === GRUPO 3: HABITACIONES Y FACTURAS === */}
        <div className="admin-grid">
          <div className="data-card">
            <h3>Nueva Habitación</h3>
            <form onSubmit={handleCreateHabitacion} className="cita-form">
              <input name="numero" placeholder="Número" value={newHabitacion.numero} onChange={handleChange(setNewHabitacion)} required />
              <select name="tipo" value={newHabitacion.tipo} onChange={handleChange(setNewHabitacion)}>
                <option value="General">General</option>
                <option value="UCI">UCI</option>
                <option value="Privada">Privada</option>
              </select>
              <button type="submit" className="action-button primary">Crear</button>
            </form>
          </div>

          <div className="data-card">
            <h3>Facturación</h3>
            <form onSubmit={handleCreateFactura} className="cita-form">
              <select name="pacienteId" value={facturaData.pacienteId} onChange={(e) => setFacturaData({ ...facturaData, pacienteId: e.target.value })} required>
                <option value="">-- Selecciona Paciente --</option>
                {pacientes.map((p) => (
                  <option key={p.idPaciente} value={p.idPaciente}>{p.nombres} {p.apellidos}</option>
                ))}
              </select>
              {facturaData.detalles.map((detalle, index) => (
                <div key={index}>
                  <input name="concepto" placeholder="Concepto" value={detalle.concepto} onChange={(e) => handleFacturaChange(index, e)} required />
                  <input name="monto" type="number" placeholder="Monto" value={detalle.monto} onChange={(e) => handleFacturaChange(index, e)} required />
                </div>
              ))}
              <button type="button" onClick={addFacturaDetalle} className="action-button">Añadir</button>
              <button type="submit" className="action-button primary">Generar</button>
            </form>
            <ListaFacturas facturas={facturas} onPagarFactura={handlePagarFactura} />
          </div>
        </div>

      </div>
    </div>
  );
};

export default AdminPage;

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ListaMedicos from '../components/ListaMedicos';
import ListaConsultas from '../components/ListaConsultas';

const API_BASE_URL = 'http://localhost:8080/api';

const MedicosPage = () => {
    const [medicos, setMedicos] = useState([]);
    const [selectedMedico, setSelectedMedico] = useState(null);
    const [consultas, setConsultas] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState('');

    useEffect(() => {
        axios.get(`${API_BASE_URL}/medicos`)
            .then(res => setMedicos(res.data))
            .catch(() => setError('No se pudieron cargar los médicos.'));
    }, []);

    const handleSelectMedico = (medico) => {
        if (selectedMedico && selectedMedico.idMedico === medico.idMedico) {
            setSelectedMedico(null); // Permite deseleccionar
            setConsultas([]);
            return;
        }
        setSelectedMedico(medico);
        setIsLoading(true);
        setConsultas([]); // Limpia resultados anteriores
        setError('');

        axios.get(`${API_BASE_URL}/medicos/${medico.idMedico}/consultas`)
            .then(res => {
                setConsultas(res.data);
            })
            .catch(err => {
                console.error("Error al cargar consultas:", err);
                setError('No se pudieron cargar las consultas del médico.');
            })
            .finally(() => setIsLoading(false));
    };

    return (
        <div className="page-layout">
            <div className="sub-sidebar">
                <h3>Lista de Médicos</h3>
                <ListaMedicos
                    medicos={medicos}
                    onSelectMedico={handleSelectMedico}
                    selectedMedicoId={selectedMedico?.idMedico}
                />
            </div>
            <div className="page-content">
                {error && <p className="error-message">{error}</p>}
                {selectedMedico ? (
                    <div>
                        <div className="data-card patient-header">
                            <h2>Dr. {selectedMedico.nombres} {selectedMedico.apellidos}</h2>
                            <p><strong>Colegiatura:</strong> {selectedMedico.colegiatura}</p>
                            {/* Muestra las especialidades si existen */}
                            {selectedMedico.especialidades && selectedMedico.especialidades.length > 0 &&
                                <div><strong>Especialidades: </strong>{selectedMedico.especialidades.map(e => e.nombre).join(', ')}</div>
                            }
                        </div>
                        {isLoading ? <p>Cargando consultas...</p> : <ListaConsultas consultas={consultas} />}
                    </div>
                ) : (
                    <div className="placeholder">Selecciona un médico para ver su historial de consultas</div>
                )}
            </div>
        </div>
    );
};
export default MedicosPage;
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ListaCitas from '../components/ListaCitas';

const API_BASE_URL = 'http://localhost:8080/api';

const CitasPage = () => {
    const [citas, setCitas] = useState([]);

    const fetchCitas = () => {
        axios.get(`${API_BASE_URL}/citas`)
            .then(res => setCitas(res.data))
            .catch(err => console.error(err));
    };

    useEffect(() => {
        fetchCitas();
    }, []);

    const handleMarcarAtendida = (citaId) => {
        axios.put(`${API_BASE_URL}/citas/${citaId}/atender`)
            .then(() => {
                fetchCitas(); // Refrescamos la lista
            })
            .catch(err => alert('No se pudo actualizar la cita.'));
    };

    return <ListaCitas citas={citas} onMarcarAtendida={handleMarcarAtendida} />;
};
export default CitasPage;
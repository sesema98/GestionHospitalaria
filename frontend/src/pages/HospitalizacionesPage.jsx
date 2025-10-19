import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ListaHospitalizaciones from '../components/ListaHospitalizaciones';

const API_BASE_URL = 'http://localhost:8080/api';

const HospitalizacionesPage = () => {
    const [hospitalizaciones, setHospitalizaciones] = useState([]);
    
    const fetchHospitalizaciones = () => {
        axios.get(`${API_BASE_URL}/hospitalizaciones`)
            .then(res => setHospitalizaciones(res.data))
            .catch(err => console.error(err));
    };

    useEffect(() => {
        fetchHospitalizaciones();
    }, []);

    const handleDarDeAlta = (hospId) => {
        axios.put(`${API_BASE_URL}/hospitalizaciones/${hospId}/alta`)
            .then(() => {
                fetchHospitalizaciones(); // Refrescamos la lista para ver el cambio
            })
            .catch(err => alert('No se pudo dar de alta al paciente.'));
    };

    return <ListaHospitalizaciones hospitalizaciones={hospitalizaciones} onDarDeAlta={handleDarDeAlta} />;
};
export default HospitalizacionesPage;
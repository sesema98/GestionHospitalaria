import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ListaConsultas from '../components/ListaConsultas';

const ConsultasPage = () => {
    const [consultas, setConsultas] = useState([]);
    useEffect(() => {
        axios.get('http://localhost:8080/api/consultas')
            .then(res => setConsultas(res.data))
            .catch(err => console.error(err));
    }, []);
    return <ListaConsultas consultas={consultas} />;
};
export default ConsultasPage;
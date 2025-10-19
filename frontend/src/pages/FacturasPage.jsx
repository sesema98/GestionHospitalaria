import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ListaFacturas from '../components/ListaFacturas';

const API_BASE_URL = 'http://localhost:8080/api';

const FacturasPage = () => {
    const [facturas, setFacturas] = useState([]);
    
    const fetchFacturas = () => {
        axios.get(`${API_BASE_URL}/facturas`)
            .then(res => setFacturas(res.data))
            .catch(err => console.error(err));
    };

    useEffect(() => {
        fetchFacturas();
    }, []);

    const handlePagarFactura = (facturaId) => {
        axios.put(`${API_BASE_URL}/facturas/${facturaId}/pagar`)
            .then(() => {
                fetchFacturas(); // Refrescamos la lista para ver el cambio
            })
            .catch(err => alert('No se pudo pagar la factura.'));
    };

    return <ListaFacturas facturas={facturas} onPagarFactura={handlePagarFactura} />;
};
export default FacturasPage;
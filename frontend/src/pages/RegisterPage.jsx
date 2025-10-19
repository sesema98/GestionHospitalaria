import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import axios from 'axios';

const RegisterPage = () => {
    const [nombreUsuario, setNombreUsuario] = useState('');
    const [contrasena, setContrasena] = useState('');
    const [rol, setRol] = useState('recepcionista'); // Rol por defecto
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post('http://localhost:8080/api/usuarios/registrar', { nombreUsuario, contrasena, rol })
            .then(() => {
                alert('Usuario registrado con éxito. Ahora puedes iniciar sesión.');
                navigate('/login');
            })
            .catch(() => alert('Error al registrar el usuario.'));
    };

    return (
        <div className="auth-container">
            <div className="auth-card">
                <h2>Crear Nuevo Usuario</h2>
                <form onSubmit={handleSubmit}>
                    <input type="text" value={nombreUsuario} onChange={e => setNombreUsuario(e.target.value)} placeholder="Nombre de Usuario" required />
                    <input type="password" value={contrasena} onChange={e => setContrasena(e.target.value)} placeholder="Contraseña" required />
                    <select value={rol} onChange={e => setRol(e.target.value)}>
                        <option value="admin">Administrador</option>
                        <option value="medico">Médico</option>
                        <option value="recepcionista">Recepcionista</option>
                    </select>
                    <button type="submit">Registrar</button>
                </form>
                <p>¿Ya tienes una cuenta? <Link to="/login">Inicia sesión</Link></p>
            </div>
        </div>
    );
};
export default RegisterPage;
import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import axios from 'axios';
import '../App.css'; // asegúrate de importar tus estilos globales

const LoginPage = ({ onLogin }) => {
  const [nombreUsuario, setNombreUsuario] = useState('');
  const [contrasena, setContrasena] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post('http://localhost:8080/api/usuarios/login', { nombreUsuario, contrasena })
      .then((res) => {
        onLogin(res.data);
        navigate('/');
      })
      .catch(() => setError('Credenciales incorrectas.'));
  };

  return (
    <div className="auth-container">
      <div className="auth-card">
        <h2>Iniciar Sesión</h2>

        <form onSubmit={handleSubmit}>
          <input
            type="text"
            value={nombreUsuario}
            onChange={(e) => setNombreUsuario(e.target.value)}
            placeholder="Nombre de Usuario"
            required
          />
          <input
            type="password"
            value={contrasena}
            onChange={(e) => setContrasena(e.target.value)}
            placeholder="Contraseña"
            required
          />
          {error && <p className="error-message">{error}</p>}
          <button type="submit" className="action-button primary">
            Ingresar
          </button>
        </form>

        <p style={{ marginTop: '16px', fontSize: '0.9rem', color: '#94a3b8' }}>
          ¿No tienes una cuenta?{' '}
          <Link to="/register" style={{ color: '#3b82f6', textDecoration: 'none' }}>
            Regístrate aquí
          </Link>
        </p>
      </div>
    </div>
  );
};

export default LoginPage;

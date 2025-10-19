import React, { useState } from 'react';
import { Routes, Route, Link, Navigate, useLocation, Outlet } from 'react-router-dom'; // Asegúrate de importar Outlet
import HomePage from './pages/HomePage';
import PacientesPage from './pages/PacientesPage';
import MedicosPage from './pages/MedicosPage';
import AdminPage from './pages/AdminPage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import './App.css';

// Componente para proteger rutas (sin cambios, está perfecto)
const ProtectedRoute = ({ user, children }) => {
  if (!user) {
    return <Navigate to="/login" replace />;
  }
  return children;
};

// Layout principal de la aplicación (casi sin cambios)
function AppLayout({ user, handleLogout }) {
  const location = useLocation();
  const getLinkClass = (path) => location.pathname === path ? 'nav-link active' : 'nav-link';
  const showAdminLink = user && (user.rol === 'admin' || user.rol === 'recepcionista');

  return (
    <div className="admin-wrapper">
      {/* Barra superior fija y fluida */}
      <header className="admin-navbar">
        <h1 className="logo">SGH Dashboard</h1>

        <nav className="navbar-links">
          <Link to="/" className={getLinkClass('/')}>Inicio</Link>
          <Link to="/pacientes" className={getLinkClass('/pacientes')}>Pacientes</Link>
          <Link to="/medicos" className={getLinkClass('/medicos')}>Médicos</Link>
          {showAdminLink && (
            <Link to="/admin" className={getLinkClass('/admin')}>Admin</Link>
          )}
          <button onClick={handleLogout} className="logout-button">Cerrar Sesión</button>
        </nav>
      </header>

      {/* Contenido principal centrado */}
      <main className="admin-content">
        <Outlet />
      </main>
    </div>
  );
}



// Componente App principal (con la estructura de rutas corregida)
function App() {
  const [user, setUser] = useState(JSON.parse(localStorage.getItem('user')));

  const handleLogin = (loggedInUser) => {
    localStorage.setItem('user', JSON.stringify(loggedInUser));
    setUser(loggedInUser);
  };

  const handleLogout = () => {
    localStorage.removeItem('user');
    setUser(null);
  };

  const showAdminRoute = user && (user.rol === 'admin' || user.rol === 'recepcionista');

  return (
    <Routes>
      {/* Rutas públicas de Login y Registro */}
      <Route path="/login" element={<LoginPage onLogin={handleLogin} />} />
      <Route path="/register" element={<RegisterPage />} />

      {/* ✅ CORRECCIÓN PRINCIPAL: Estructura de rutas anidadas */}
      <Route 
        path="/" 
        element={
          <ProtectedRoute user={user}>
            <AppLayout user={user} handleLogout={handleLogout} />
          </ProtectedRoute>
        }
      >
        {/* Estas rutas hijas se renderizarán donde esté el <Outlet /> */}
        <Route index element={<HomePage />} /> {/* 'index' es la ruta por defecto para "/" */}
        <Route path="pacientes" element={<PacientesPage />} />
        <Route path="medicos" element={<MedicosPage />} />
        {showAdminRoute && (
          <Route path="admin" element={<AdminPage />} />
        )}
        {/* Si añades más páginas, ponlas aquí como rutas hijas */}
      </Route>
    </Routes>
  );
}

export default App;
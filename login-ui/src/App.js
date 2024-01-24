import React from 'react' // Importa o pacote React
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom' // Importa os componentes do roteador do pacote react-router-dom
import { AuthProvider } from './components/context/AuthContext' // Importa o provedor de contexto de autenticação
import PrivateRoute from './components/misc/PrivateRoute' // Importa o componente PrivateRoute
import Navbar from './components/misc/Navbar' // Importa o componente Navbar
// Importa os componentes das páginas do aplicativo
import Home from './components/home/Home'
import Login from './components/home/Login'
import Signup from './components/home/Signup'
import AdminPage from './components/admin/AdminPage'
import UserPage from './components/user/UserPage'
import RecoveryPass from "./components/home/RecoveryPass";
//ste arquivo JavaScript define o componente App, que é o componente raiz do aplicativo. Ele usa o roteador do React
// para definir as rotas do aplicativo e o contexto de autenticação para fornecer funções de autenticação para os
// componentes filhos. Ele também define várias rotas para diferentes partes do aplicativo e usa o componente
// PrivateRoute para proteger as rotas que só devem ser acessíveis por usuários autenticados.

// Define o componente App
function App() {
  return (
      <AuthProvider>  {/*// Fornece o contexto de autenticação para os componentes filhos*/}
        <Router> {/*// Fornece o roteador para os componentes filhos*/}
          <Navbar /> {/*// Renderiza a barra de navegação*/}
          <Routes> // Define as rotas do aplicativo
            <Route path='/' element={<Login />} /> // Rota para a página de login
            <Route path='/login' element={<Login />} /> // Rota para a página de login
            <Route path='/signup' element={<Signup />} /> // Rota para a página de registro
            <Route path='/recovery' element={<RecoveryPass />} /> // Rota para a página de recuperação de senha
            <Route path="/adminpage" element={<PrivateRoute><AdminPage /></PrivateRoute>} /> // Rota privada para a página de administração
            <Route path="/userpage" element={<PrivateRoute><UserPage /></PrivateRoute>} /> // Rota privada para a página do usuário
            <Route path="/home" element={<PrivateRoute><Home /></PrivateRoute>} /> // Rota privada para a página inicial
            <Route path="*" element={<Navigate to="/" />} /> // Rota padrão que redireciona para a página de login
          </Routes>
        </Router>
      </AuthProvider>
  )
}

export default App // Exporta o componente App
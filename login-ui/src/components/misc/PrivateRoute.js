import React from 'react' // Importa o pacote React
import { Navigate } from 'react-router-dom' // Importa o componente Navigate do pacote react-router-dom para a navegação
import { useAuth } from '../context/AuthContext' // Importa o hook useAuth do contexto de autenticação
//Este arquivo JavaScript define o componente PrivateRoute, que é um componente de rota de alto nível usado para
// proteger rotas que só devem ser acessíveis por usuários autenticados. Ele usa o contexto de autenticação para
// verificar se o usuário está autenticado. Se o usuário estiver autenticado, ele renderiza os componentes filhos;
// caso contrário, ele redireciona o usuário para a página de login.

// Define o componente PrivateRoute
function PrivateRoute({ children }) {
  const { userIsAuthenticated } = useAuth() // Obtenha a função userIsAuthenticated do contexto de autenticação
  // Se o usuário estiver autenticado, renderiza os componentes filhos, caso contrário, redireciona para a página de login
  return userIsAuthenticated() ? children : <Navigate to="/login" />
}

export default PrivateRoute // Exporta o componente PrivateRoutedefault PrivateRoute
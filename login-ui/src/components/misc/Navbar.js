import React from 'react' // Importa o pacote React
import { Link } from 'react-router-dom' // Importa o componente Link do pacote react-router-dom para a navegação
import { Container, Menu } from 'semantic-ui-react' // Importa os componentes Container e Menu do pacote semantic-ui-react para a estilização
import { useAuth } from '../context/AuthContext' // Importa o hook useAuth do contexto de autenticação
//Este arquivo JavaScript define o componente Navbar, que é a barra de navegação do aplicativo. Ele usa o contexto de
// autenticação para determinar se um usuário está autenticado e, em caso afirmativo, qual é o seu papel (administrador
// ou usuário). Com base nisso, ele renderiza diferentes links de navegação.

// Define o componente Navbar
function Navbar() {
  // Obtenha as funções do contexto de autenticação
  const { getUser, userIsAuthenticated, userLogout } = useAuth()

  // Função para fazer logout do usuário
  const logout = () => {
    userLogout()
  }

  // Funções para determinar o estilo dos itens do menu com base no estado de autenticação do usuário
  const enterMenuStyle = () => {
    return userIsAuthenticated() ? { "display": "none" } : { "display": "block" }
  }

  const logoutMenuStyle = () => {
    return userIsAuthenticated() ? { "display": "block" } : { "display": "none" }
  }

  // Funções para determinar o estilo dos itens do menu com base no papel do usuário
  const adminPageStyle = () => {
    const user = getUser()
    return user && user.role === 'ADMIN' ? { "display": "block" } : { "display": "none" }
  }

  const userPageStyle = () => {
    const user = getUser()
    return user && user.role === 'USER' ? { "display": "block" } : { "display": "none" }
  }

  // Função para obter o nome do usuário
  const getUserName = () => {
    const user = getUser()
    return user ? user.name : ''
  }

  {/*// Renderiza a barra de navegação*/}
  return (
      <Menu inverted color='grey' stackable size='massive' style={{borderRadius: 0}}>
        <Container>
          <Menu.Item header>Login-UI</Menu.Item>
          <Menu.Item as={Link} exact='true' to="/Home">Home</Menu.Item>
          <Menu.Item as={Link} to="/adminpage" style={adminPageStyle()}>AdminPage</Menu.Item>
          <Menu.Item as={Link} to="/userpage" style={userPageStyle()}>UserPage</Menu.Item>
          <Menu.Menu position='right'>
            <Menu.Item as={Link} to="/login" style={enterMenuStyle()}>Login</Menu.Item>
            <Menu.Item as={Link} to="/signup" style={enterMenuStyle()}>Sign Up</Menu.Item>
            <Menu.Item as={Link} to="/recovery" style={enterMenuStyle()}>Recovery Password</Menu.Item>
            <Menu.Item header style={logoutMenuStyle()}>{`Hi ${getUserName()}`}</Menu.Item>
            <Menu.Item as={Link} to="/" style={logoutMenuStyle()} onClick={logout}>Logout</Menu.Item>
          </Menu.Menu>
        </Container>
      </Menu>
  )
}

export default Navbar // Exporta o componente Navbar
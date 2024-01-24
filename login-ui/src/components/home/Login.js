import React, { useState } from 'react' // Importa o pacote React e o hook useState
import { NavLink, Navigate } from 'react-router-dom' // Importa os componentes NavLink e Navigate do pacote react-router-dom
import {Button, Form, Grid, Segment, Message, Statistic} from 'semantic-ui-react' // Importa os componentes do pacote semantic-ui-react
import { useAuth } from '../context/AuthContext' // Importa o hook useAuth do contexto de autenticação
import { LoginApi } from '../misc/LoginApi' // Importa a API de login
import { handleLogError } from '../misc/Helpers' // Importa a função de manipulação de erros

// Este arquivo define o componente Login em JavaScript, que é a página de login do aplicativo. Ele permite que os
// usuários insiram suas credenciais de email e senha para autenticação. Se a autenticação for bem-sucedida, o usuário
// será redirecionado para a página inicial. Caso contrário, uma mensagem de erro será exibida.
function Login() {
  const Auth = useAuth() // Obtenha o contexto de autenticação
  const isLoggedIn = Auth.userIsAuthenticated() // Verifique se o usuário está autenticado

  // Define o estado para o email, senha e se ocorreu um erro
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [isError, setIsError] = useState(false)

  // Manipulador para mudanças nos campos de entrada
  const handleInputChange = (e, { name, value }) => {
    if (name === 'email') {
      setEmail(value.toLowerCase()) // Transforma o email em minúsculas
    } else if (name === 'password') {
      setPassword(value)
    }
  }

  // Manipulador para o envio do formulário
  const handleSubmit = async (e) => {
    e.preventDefault() // Previne o comportamento padrão do formulário

    // Verifica se o email e a senha foram fornecidos
    if (!(email && password)) {
      setIsError(true) // Define o erro como verdadeiro
      return
    }

    try {
      // Tenta autenticar o usuário
      const response = await LoginApi.authenticate(email, password)
      const { id, name, role } = response.data
      const authdata = window.btoa(email + ':' + password)
      const authenticatedUser = { id, name, role, authdata }

      Auth.userLogin(authenticatedUser) // Faz login do usuário

      // Limpa o estado
      setEmail('')
      setPassword('')
      setIsError(false)
    } catch (error) {
      handleLogError(error) // Manipula qualquer erro que ocorra
      setIsError(true) // Define o erro como verdadeiro
    }
  }

  // Se o usuário estiver autenticado, redireciona para a página inicial
  if (isLoggedIn) {
    return <Navigate to={'/home'} />
  }

  // Renderiza o formulário de login
  return (
      <Grid textAlign='center'>
        <Grid.Column style={{ maxWidth: 450 }}>
          <Form size='large' onSubmit={handleSubmit}>
            <Statistic.Label>Login</Statistic.Label>
            <Segment>
              <Form.Input
                  fluid
                  autoFocus
                  name='email'
                  icon='user'
                  iconPosition='left'
                  placeholder='Email'
                  value={email}
                  onChange={handleInputChange}
              />
              <Form.Input
                  fluid
                  name='password'
                  icon='lock'
                  iconPosition='left'
                  placeholder='Password'
                  type='password'
                  value={password}
                  onChange={handleInputChange}
              />
              <Button color='blue' fluid size='large'>Login</Button>
            </Segment>
          </Form>
          {isError && <Message negative>The email or password provided are incorrect!</Message>}
          <Message>
            {`Don't have already an account? `}
            <NavLink to="/signup" as={NavLink} color='teal'>Sign Up</NavLink>
          <br/>
            {`Forgot your password? `}
            <NavLink to="/recovery" color='teal'>Recover</NavLink>
          </Message>

        </Grid.Column>
      </Grid>
  )
}

export default Login // Exporta o componente Login
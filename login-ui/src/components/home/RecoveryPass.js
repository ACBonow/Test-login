import React, { useState } from 'react' // Importa o pacote React e o hook useState
import { NavLink, Navigate } from 'react-router-dom' // Importa os componentes NavLink e Navigate do pacote react-router-dom
import {Button, Form, Grid, Segment, Message, Statistic} from 'semantic-ui-react' // Importa os componentes do pacote semantic-ui-react
import { useAuth } from '../context/AuthContext' // Importa o hook useAuth do contexto de autenticação
import { LoginApi } from '../misc/LoginApi' // Importa a API de login
import { handleLogError } from '../misc/Helpers' // Importa a função de manipulação de erros

//Este arquivo define o componente RecoveryPass em JavaScript, que é a página de recuperação de senha do aplicativo.
// Ele permite que os usuários insiram suas informações para recuperar a senha. Se a recuperação for bem-sucedida, o
// usuário será autenticado e redirecionado para a página inicial. Caso contrário, uma mensagem de erro será exibida.

// Função para validar a senha
function validatePassword(password) {
  const re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
  return re.test(password);
}

// Define o componente RecoveryPass
function RecoveryPass() {
  const Auth = useAuth(); {/*// Obtenha o contexto de autenticação*/}
  const isLoggedIn = Auth.userIsAuthenticated() // Verifique se o usuário está autenticado

  // Define o estado para o email, senha, nome, cpf, se ocorreu um erro e a mensagem de erro
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [name, setName] = useState('')
  const [cpf, setCpf] = useState('')
  const [isError, setIsError] = useState(false)
  const [errorMessage, setErrorMessage] = useState('')
  const [successMessage, setSuccessMessage] = useState('')

  // Manipulador para mudanças nos campos de entrada
  const handleInputChange = (e, { name, value }) => {
    if (name === 'email') {
      setEmail(value.toLowerCase()) // Transforma o email em minúsculas
    } else if (name === 'password') {
      setPassword(value)
    } else if (name === 'name') {
      setName(value.replace(/\b\w/g, l => l.toUpperCase())) // Transforma a primeira letra de cada palavra do nome em maiúscula
    } else if (name === 'cpf') {
      setCpf(value)
    }
  }

  // Manipulador para o envio do formulário
  const handleSubmit = async (e) => {
    e.preventDefault() // Previne o comportamento padrão do formulário

    // Verifica se a senha é válida
    if (!validatePassword(password)) {
      setIsError(true) // Define o erro como verdadeiro
      setErrorMessage('Password must have at least 8 characters, including one uppercase letter, one lowercase letter, and one number.')
      return
    }

    // Verifica se o email, senha, nome e cpf foram fornecidos
    if (!(email && password && name && cpf)) {
      setIsError(true) // Define o erro como verdadeiro
      setErrorMessage('Please, inform all fields!')
      return
    }

    const user = { email, password, name, cpf } // Define o usuário

    try {
      // Tenta recuperar a senha do usuário
      const response = await LoginApi.recoveryPass(user)
      const { id, name, role, message } = response.data
      const authdata = window.btoa(email + ':' + password)
      const authenticatedUser = { id, name, role, authdata }


      // Limpa o estado
      setEmail('')
      setPassword('')
      setName('')
      setCpf('')
      setIsError(false)
      setErrorMessage(message)
      setSuccessMessage('Password changed successfully!')
    } catch (error) {
      handleLogError(error) // Manipula qualquer erro que ocorra
      if (error.response && error.response.data) {
        const errorData = error.response.data
        let errorMessage = 'Invalid fields'
        if (errorData.message) {
          errorMessage = errorData.message
        } else if (errorData.status === 409) {
          errorMessage = errorData.message
        } else if (errorData.status === 400 && errorData.errors[0].defaultMessage) {
          errorMessage = errorData.errors[0].defaultMessage
        }
        setIsError(true) // Define o erro como verdadeiro
        setErrorMessage(errorMessage)
      }
    }
  }

  // Se o usuário estiver autenticado, redireciona para a página inicial
  if (isLoggedIn) {
    return <Navigate to='/' />
  }

  // Renderiza o formulário de recuperação de senha
  return (
      <Grid textAlign='center'>
        <Grid.Column style={{ maxWidth: 450 }}>
          <Form size='large' onSubmit={handleSubmit}>
            <Statistic.Label>Recovery Password</Statistic.Label>
            <Statistic.Label color='red'>ATTENTION! Recovering the password removes Administrator permission!</Statistic.Label>

            <Segment>
              <Form.Input
                  fluid
                  autoFocus
                  name='email'
                  icon='user'
                  iconPosition='left'
                  placeholder='Email (lowercase)'
                  value={email}
                  onChange={handleInputChange}
              />
              <Form.Input
                  fluid
                  name='password'
                  icon='lock'
                  iconPosition='left'
                  placeholder='Password (at least 8 characters, including one uppercase letter, one lowercase letter, and one number)'
                  type='password'
                  value={password}
                  onChange={handleInputChange}
              />
              <Form.Input
                  fluid
                  name='name'
                  icon='address card'
                  iconPosition='left'
                  placeholder='Name'
                  value={name}
                  onChange={handleInputChange}
              />
              <Form.Input
                  fluid
                  name='cpf'
                  icon='id card'
                  iconPosition='left'
                  placeholder='CPF(WITHOUT DOTS AND HYPHEN)'
                  value={cpf}
                  onChange={handleInputChange}
              />
                <Button color='blue' fluid size='large'>Signup</Button>
            </Segment>
          </Form>
          {isError && <Message negative>{errorMessage}</Message>}
          {successMessage && <Message positive>{successMessage}</Message>}
          <Message>{`Already have an account? `}
            <NavLink to="/login" color='teal'>Login</NavLink>
          </Message>
        </Grid.Column>
      </Grid>
  )
}

export default RecoveryPass // Exporta o componente RecoveryPass
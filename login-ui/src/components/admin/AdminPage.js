import React, { useEffect, useState } from 'react'
import { Navigate } from 'react-router-dom'
import { Container } from 'semantic-ui-react'
import { useAuth } from '../context/AuthContext'
import { LoginApi } from '../misc/LoginApi'
import AdminTab from './AdminTab'
import { handleLogError } from '../misc/Helpers'

// Este arquivo define o componente AdminPage, que é a página de administração do aplicativo.
// Ele permite que um administrador visualize, pesquise e exclua usuários, além de alterar o papel de um usuário.
// Este componente usa o contexto de autenticação para verificar se o usuário atual é um administrador.
// Se o usuário atual não for um administrador, ele será redirecionado para a página inicial.

function AdminPage() {
  const Auth = useAuth() // Obtenha o contexto de autenticação
  const user = Auth.getUser() // Obtenha o usuário atual
  const isAdmin = user.role === 'ADMIN' // Verifique se o usuário atual é um administrador

  // Defina o estado para a lista de usuários, a pesquisa de email do usuário e o estado de carregamento dos usuários
  const [users, setUsers] = useState([])
  const [userEmailSearch, setUserEmailSearch] = useState('')
  const [isUsersLoading, setIsUsersLoading] = useState(false)

  // Quando o componente é montado, obtenha a lista de usuários
  useEffect(() => {
    handleGetUsers()
  }, [])

  // Manipulador para mudanças nos campos de entrada
  const handleInputChange = (e, { name, value }) => {
    if (name === 'userEmailSearch') {
      setUserEmailSearch(value)
    }
  }

  // Função para obter a lista de usuários
  const handleGetUsers = async () => {
    try {
      setIsUsersLoading(true) // Inicie o carregamento
      const response = await LoginApi.getUsers(user) // Obtenha a lista de usuários
      const users = response.data // Extraia os usuários da resposta
      setUsers(users) // Atualize o estado com os novos usuários
    } catch (error) {
      handleLogError(error) // Manipule qualquer erro que ocorra
    } finally {
      setIsUsersLoading(false) // Pare o carregamento
    }
  }

  // Função para excluir um usuário
  const handleDeleteUser = async (email) => {
    try {
      await LoginApi.deleteUser(user, email) // Exclua o usuário
      await handleGetUsers() // Atualize a lista de usuários
    } catch (error) {
      handleLogError(error) // Manipule qualquer erro que ocorra
    }
  }

  // Função para pesquisar um usuário
  const handleSearchUser = async () => {
    try {
      const response = await LoginApi.getUsers(user, userEmailSearch) // Pesquise o usuário
      const data = response.data // Extraia os dados da resposta
      const users = data instanceof Array ? data : [data] // Se os dados forem um array, use-os, caso contrário, crie um array com os dados
      setUsers(users) // Atualize o estado com os novos usuários
    } catch (error) {
      handleLogError(error) // Manipule qualquer erro que ocorra
      setUsers([]) // Limpe a lista de usuários
    }
  }

  // Função para alterar o papel de um usuário
  const handleChangeRole = async (email, newRole) => {
    try {
      await LoginApi.changeUserRole(user, email, newRole); // Altere o papel do usuário
      await handleGetUsers(); // Atualize a lista de usuários
    } catch (error) {
      handleLogError(error); // Manipule qualquer erro que ocorra
    }
  };

  // Se o usuário atual não for um administrador, redirecione para a página inicial
  if (!isAdmin) {
    return <Navigate to='/' />
  }

  // Renderize o componente AdminTab com as props apropriadas
  return (
      <Container>
        <AdminTab
            isUsersLoading={isUsersLoading}
            users={users}
            userEmailSearch={userEmailSearch}
            handleDeleteUser={handleDeleteUser}
            handleSearchUser={handleSearchUser}
            handleInputChange={handleInputChange}
            handleChangeRole={handleChangeRole}
        />
      </Container>
  )
}

export default AdminPage

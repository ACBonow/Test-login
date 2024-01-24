import axios from 'axios' // Importa a biblioteca axios para fazer solicitações HTTP
import { config } from '../../Constants' // Importa a configuração do aplicativo
//Este arquivo JavaScript define o módulo LoginApi, que contém várias funções para interagir com a API de autenticação
// do aplicativo. Ele usa a biblioteca axios para fazer solicitações HTTP e a biblioteca config para obter a URL
// base da API.

// Define o objeto LoginApi com várias funções para interagir com a API
export const LoginApi = {
  authenticate,
  signup,
  numberOfUsers,
  getUsers,
  deleteUser,
  changeUserRole,
  recoveryPass
}

// Função para autenticar um usuário
function authenticate(email, password) {
  // Faz uma solicitação POST para a rota de autenticação da API
  return instance.post('/auth/authenticate', { email, password }, {
    headers: { 'Content-type': 'application/json' }
  })
}

// Função para registrar um novo usuário
function signup(user) {
  // Faz uma solicitação POST para a rota de registro da API
  return instance.post('/auth/signup', user, {
    headers: { 'Content-type': 'application/json' }
  })
}

// Função para recuperar a senha de um usuário
function recoveryPass(user) {
  // Faz uma solicitação PUT para a rota de recuperação de senha da API
  return instance.put('/auth/recovery-password', user, {
    headers: { 'Content-type': 'application/json' }
  })
}

// Função para obter o número de usuários
function numberOfUsers() {
  // Faz uma solicitação GET para a rota que retorna o número de usuários
  return instance.get('/public/numberOfUsers')
}

// Função para obter os usuários
function getUsers(user, email) {
  // Define a URL com base no email fornecido
  const url = email ? `/api/users/${email}` : '/api/users'
  // Faz uma solicitação GET para a rota de usuários da API
  return instance.get(url, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

// Função para excluir um usuário
function deleteUser(user, email) {
  // Faz uma solicitação DELETE para a rota de usuários da API
  return instance.delete(`/api/users/${email}`, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

// Função para alterar o papel de um usuário
function changeUserRole(user, email, newRole) {
  // Faz uma solicitação PUT para a rota de alteração de papel da API
  return instance.put(`/api/users/change-role/${email}/${newRole}`, {email, role: newRole }, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

// Cria uma instância do axios com a URL base da API
const instance = axios.create({
  baseURL: config.url.API_BASE_URL
})

// Função auxiliar para criar o cabeçalho de autenticação básica
function basicAuth(user) {
  return `Basic ${user.authdata}`
}
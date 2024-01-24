import React, { createContext, useContext, useState, useEffect } from 'react'
//Este arquivo define o contexto de autenticação para o aplicativo. Ele fornece funções para obter o usuário atual,
// verificar se o usuário está autenticado, fazer login e logout do usuário. O estado do usuário é armazenado no
// localStorage para persistência entre as sessões do navegador.

// Cria um novo contexto para autenticação
const AuthContext = createContext()

// Provedor de contexto de autenticação
function AuthProvider({ children }) {
  // Estado para armazenar o usuário atual
  const [user, setUser] = useState(null)

  // Quando o componente é montado, recupera o usuário do localStorage
  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem('user'))
    setUser(storedUser)
  }, [])

  // Função para obter o usuário atual do localStorage
  const getUser = () => {
    return JSON.parse(localStorage.getItem('user'))
  }

  // Função para verificar se o usuário está autenticado
  const userIsAuthenticated = () => {
    return localStorage.getItem('user') !== null
  }

  // Função para fazer login do usuário
  const userLogin = user => {
    localStorage.setItem('user', JSON.stringify(user))
    setUser(user)
  }

  // Função para fazer logout do usuário
  const userLogout = () => {
    localStorage.removeItem('user')
    setUser(null)
  }

  // Valor do contexto que será fornecido aos componentes filhos
  const contextValue = {
    user,
    getUser,
    userIsAuthenticated,
    userLogin,
    userLogout,
  }

  // Renderiza o provedor de contexto com o valor do contexto
  return (
      <AuthContext.Provider value={contextValue}>
        {children}
      </AuthContext.Provider>
  )
}

// Exporta o contexto de autenticação
export default AuthContext

// Hook personalizado para usar o contexto de autenticação
export function useAuth() {
  return useContext(AuthContext)
}

// Exporta o provedor de contexto de autenticação
export { AuthProvider }
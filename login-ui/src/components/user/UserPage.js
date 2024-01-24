import React from 'react' // Importa o pacote React
import { Navigate } from 'react-router-dom' // Importa o componente Navigate do pacote react-router-dom para a navegação
import { Container } from 'semantic-ui-react' // Importa o componente Container do pacote semantic-ui-react para a estilização
import { useAuth } from '../context/AuthContext' // Importa o hook useAuth do contexto de autenticação
//Este arquivo JavaScript define o componente UserPage, que é a página do usuário no aplicativo. Ele usa o contexto de
// autenticação para verificar se o usuário atual tem o papel de 'USER'. Se o usuário atual não for um 'USER', ele será
// redirecionado para a página inicial.

// Define o componente UserPage
function UserPage() {
    const Auth = useAuth() // Obtenha o contexto de autenticação
    const user = Auth.getUser() // Obtenha o usuário atual
    const isUser = user.role === 'USER' // Verifique se o usuário atual é um 'USER'

    // Se o usuário atual não for um 'USER', redireciona para a página inicial
    if (!isUser) {
        return <Navigate to='/' />
    }

    // Renderiza a página do usuário
    return (
        <Container>
            <h1>User Page</h1>
        </Container>
    )
}

export default UserPage // Exporta o componente UserPage
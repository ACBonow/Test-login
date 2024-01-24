import React from 'react' // Importa o pacote React
import { Tab } from 'semantic-ui-react' // Importa o componente Tab do pacote semantic-ui-react
import UserTable from './UserTable' // Importa o componente UserTable

//Este arquivo define o componente AdminTab, que é usado para exibir a interface de administração do aplicativo.
// Ele recebe várias props que são usadas para manipular o estado e os eventos do componente.
function AdminTab(props) {
    // Desestrutura as props para obter as funções e estados necessários
    const { handleInputChange, handleChangeRole } = props
    const { isUsersLoading, users, userEmailSearch, handleDeleteUser, handleSearchUser } = props

    // Define as abas do componente Tab
    const panes = [
        {
            // Define a aba de usuários
            menuItem: { key: 'users', icon: 'users', content: 'Users' },
            render: () => (
                // Renderiza o componente UserTable dentro da aba
                <Tab.Pane loading={isUsersLoading}>
                    <UserTable
                        users={users}
                        userEmailSearch={userEmailSearch}
                        handleInputChange={handleInputChange}
                        handleDeleteUser={handleDeleteUser}
                        handleSearchUser={handleSearchUser}
                        handleChangeRole={handleChangeRole}
                    />
                </Tab.Pane>
            )
        },
    ]

    // Renderiza o componente Tab com as abas definidas
    return (
        <Tab menu={{ attached: 'top' }} panes={panes} />
    )
}

export default AdminTab // Exporta o componente AdminTab
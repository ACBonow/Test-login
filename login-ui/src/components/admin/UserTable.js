import React from 'react' // Importa o pacote React
import { Form, Button, Input, Table, Checkbox } from 'semantic-ui-react' // Importa os componentes Form, Button, Input, Table e Checkbox do pacote semantic-ui-react

// Este arquivo define o componente UserTable em JavaScript, que é usado para exibir uma tabela de usuários na interface
// de administração do aplicativo. Ele recebe várias props que são usadas para manipular o estado e os eventos do componente.
function UserTable({ users, userEmailSearch, handleInputChange, handleDeleteUser, handleSearchUser, handleChangeRole }) {

    // Define uma variável userList para armazenar a lista de usuários a ser exibida
    let userList
    if (users.length === 0) {
        // Se não houver usuários, exibe uma mensagem
        userList = (
            <Table.Row key='no-user'>
                <Table.Cell collapsing textAlign='center' colSpan='7'>No user</Table.Cell>
            </Table.Row>
        )
    } else {
        // Se houver usuários, mapeia cada usuário para uma linha da tabela
        userList = users.map(user => {
            return (
                <Table.Row key={user.id}>
                    <Table.Cell collapsing>
                        <Button
                            circular
                            color='red'
                            size='small'
                            icon='trash'
                            disabled={user.email === 'admin'}
                            onClick={() => handleDeleteUser(user.email)}
                        />
                    </Table.Cell>
                    <Table.Cell>{user.email}</Table.Cell>
                    <Table.Cell>{user.name}</Table.Cell>
                    <Table.Cell>{user.cpf}</Table.Cell>
                    <Table.Cell>{user.role}</Table.Cell>
                    <Table.Cell>
                        <Checkbox
                            label='Admin'
                            onChange={(e, { checked }) => handleChangeRole(user.email, checked ? 'ADMIN' : 'USER')}
                        />
                    </Table.Cell>
                </Table.Row>
            )
        })
    }

    // Renderiza o formulário de pesquisa e a tabela de usuários
    return (
        <>
            <Form onSubmit={handleSearchUser}>
                <Input
                    action={{ icon: 'search' }}
                    name='userEmailSearch'
                    placeholder='Search by Email'
                    value={userEmailSearch}
                    onChange={handleInputChange}
                />
            </Form>
            <Table compact striped selectable>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell width={1}/>
                        <Table.HeaderCell width={3}>Email</Table.HeaderCell>
                        <Table.HeaderCell width={4}>Name</Table.HeaderCell>
                        <Table.HeaderCell width={3}>Cpf</Table.HeaderCell>
                        <Table.HeaderCell width={3}>Role</Table.HeaderCell>
                        <Table.HeaderCell width={3}>Make Admin</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>
                <Table.Body>
                    {userList}
                </Table.Body>
            </Table>
        </>
    )
}

export default UserTable // Exporta o componente UserTable
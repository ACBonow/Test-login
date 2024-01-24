import React, { useState, useEffect } from 'react' // Importa o pacote React e os hooks useState e useEffect
import { Statistic, Icon, Container, Segment, Dimmer, Loader } from 'semantic-ui-react' // Importa os componentes do pacote semantic-ui-react
import { LoginApi } from '../misc/LoginApi' // Importa a API de login
import { handleLogError } from '../misc/Helpers' // Importa a função de manipulação de erros

// Este arquivo define o componente Home em JavaScript, que é a página inicial do aplicativo. Ele exibe o número de
// usuários registrados e uma mensagem de boas-vindas. Este componente usa o hook useEffect para buscar o número de
// usuários do servidor quando o componente é montado. Ele também usa o estado para armazenar o número de usuários e
// se os dados estão sendo carregados.
function Home() {
  // Define o estado para o número de usuários e se os dados estão sendo carregados
  const [numberOfUsers, setNumberOfUsers] = useState(0)
  const [isLoading, setIsLoading] = useState(false)

  // Quando o componente é montado, busca o número de usuários
  useEffect(() => {
    const fetchData = async () => {
      setIsLoading(true) // Inicia o carregamento
      try {
        const responseUsers = await LoginApi.numberOfUsers() // Busca o número de usuários
        setNumberOfUsers(responseUsers.data) // Define o número de usuários
      } catch (error) {
        handleLogError(error) // Manipula qualquer erro que ocorra
      } finally {
        setIsLoading(false) // Finaliza o carregamento
      }
    }

    fetchData() // Chama a função fetchData
  }, [])

  // Se os dados estão sendo carregados, renderiza um loader
  if (isLoading) {
    return (
        <Segment basic style={{ marginTop: window.innerHeight / 2 }}>
          <Dimmer active inverted>
            <Loader inverted size='huge'>Loading</Loader>
          </Dimmer>
        </Segment>
    )
  }

  // Renderiza o número de usuários e a mensagem de boas-vindas
  return (
      <Container text style={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', height: '40vh' }}>
        <Segment color='blue'>
          <Statistic text style={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', fontSize : '3vh', minHeight : '10vh' }}>
            <Statistic.Label>Registered Users</Statistic.Label>
            <Statistic.Value><Icon name='user' color='grey' />{numberOfUsers}</Statistic.Value>
          </Statistic>
        </Segment>
        <Statistic.Label text style={{ display: 'flex', flexDirection: 'column', textAlign: 'center', fontSize : '10vh' }}>Hola Mundo</Statistic.Label>
      </Container>
  )
}

export default Home // Exporta o componente Home
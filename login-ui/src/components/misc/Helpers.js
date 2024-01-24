//Este arquivo JavaScript define uma função auxiliar chamada handleLogError. Esta função é usada para lidar com erros
// que podem ocorrer durante as chamadas de API. Ela recebe um objeto de erro como argumento e registra diferentes
// partes do erro no console, dependendo do que está disponível no objeto de erro.

// Importa a função handleLogError
export const handleLogError = (error) => {
  // Verifica se o objeto de erro tem uma propriedade 'response'
  // Se tiver, significa que a solicitação foi feita e o servidor respondeu com um status fora do intervalo de 2xx
  if (error.response) {
    console.log(error.response.data) // Registra os dados da resposta no console
  }
  // Se o objeto de erro tem uma propriedade 'request', significa que a solicitação foi feita, mas nenhuma resposta foi recebida
  else if (error.request) {
    console.log(error.request) // Registra a solicitação no console
  }
  // Se o objeto de erro não tem nem 'response' nem 'request', significa que algo aconteceu na configuração da solicitação que acionou um erro
  else {
    console.log(error.message) // Registra a mensagem de erro no console
  }
}
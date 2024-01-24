// Define a configuração para o ambiente de produção
const prod = {
  url: {
    API_BASE_URL: 'https://myapp.herokuapp.com', // URL base da API para o ambiente de produção
  }
}

// Define a configuração para o ambiente de desenvolvimento
const dev = {
  url: {
    API_BASE_URL: 'http://localhost:8080' // URL base da API para o ambiente de desenvolvimento
  }
}

// Exporta a configuração apropriada com base no ambiente atual
// Se o ambiente atual for 'development', exporta a configuração de desenvolvimento, caso contrário, exporta a configuração de produção
export const config = process.env.NODE_ENV === 'development' ? dev : prod
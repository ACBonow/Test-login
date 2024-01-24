<!DOCTYPE html>
<html>
<head>
    <title>Projeto Login API</title>
</head>
<body>
<h1>Projeto Login API</h1>
    <p>Este projeto é uma API de login desenvolvida em Java utilizando o framework Spring Boot e gerenciamento de dependências Maven.</p>

<h2>Funcionalidades</h2>
    <ul>
        <li>Autenticação de usuários</li>
        <li>Criação de novos usuários</li>
        <li>Validação de CPF</li>
        <li>Geração de ID de usuário como hash</li>
    </ul>

<h2>Estrutura do Projeto</h2>
    <p>O projeto segue a estrutura padrão de um projeto Spring Boot com Maven. Aqui estão algumas das classes principais e suas responsabilidades:</p>
    <ul>
        <li><code>UserController</code>: Controlador REST para manipulação de usuários.</li>
        <li><code>AuthController</code>: Controlador REST para autenticação de usuários.</li>
        <li><code>UserService</code>: Serviço para operações relacionadas ao usuário.</li>
        <li><code>UserRepository</code>: Repositório para interação com o banco de dados de usuários.</li>
        <li><code>CpfValidator</code>: Classe utilitária para validação de CPF.</li>
    </ul>

<h2>Tecnologias Utilizadas</h2>
    <ul>
        <li>Java</li>
        <li>Spring Boot</li>
        <li>Maven</li>
        <li>H2 Database</li>
    </ul>

<h2>Como Executar</h2>
    <p>Para executar este projeto, você precisa ter o Java e o Maven instalados em seu sistema.</p>
    <ol>
        <li>Clone o repositório para o seu sistema local.</li>
        <li>Navegue até o diretório do projeto.</li>
        <li>Execute o comando <code>mvn spring-boot:run</code> para iniciar a aplicação.</li>
        <li>A aplicação estará disponível no endereço <code>http://localhost:8080</code>.</li>
    </ol>

<h2>Testes</h2>
    <p>Para executar os testes, use o comando <code>mvn test</code>.</p>

<h2>Endpoints</h2>
    <p>Os seguintes endpoints estão disponíveis neste projeto:</p>

<h3>Auth Controller</h3>
    <ul>
      <li><code>PUT /auth/recovery-password</code>: Recupera a senha de um usuário.</li>
      <li><code>POST /auth/signup</code>: Cria um novo usuário.</li>
      <li><code>POST /auth/authenticate</code>: Autentica um usuário existente.</li>
    </ul>

<h3>User Controller</h3>
    <ul>
      <li><code>PUT /api/users/change-role/{email}/{newRole}</code>: Altera a função de um usuário.</li>
      <li><code>GET /api/users</code>: Retorna todos os usuários.</li>
      <li><code>GET /api/users/{email}</code>: Retorna um usuário específico com base no email.</li>
      <li><code>DELETE /api/users/{email}</code>: Exclui um usuário com base no email.</li>
      <li><code>GET /api/users/me</code>: Retorna o usuário atualmente autenticado.</li>
    </ul>

<h3>Public Controller</h3>
    <ul>
      <li><code>GET /public/numberOfUsers</code>: Retorna o número total de usuários.</li>
    </ul>

<h2>Contribuições</h2>
    <p>Contribuições são bem-vindas. Por favor, faça um fork do projeto e crie um pull request com suas alterações. Certifique-se de adicionar testes para qualquer funcionalidade nova ou alterada.</p>

<h2>Licença</h2>
    <p>Este projeto está licenciado sob a licença MIT.</p>
</body>
</html>

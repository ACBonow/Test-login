import React from 'react' // Importa o pacote React
import ReactDOM from 'react-dom/client' // Importa o pacote ReactDOM para renderizar elementos React no DOM
import './index.css' // Importa o arquivo de estilos CSS global
import App from './App' // Importa o componente App, que é o componente raiz do aplicativo
import reportWebVitals from './reportWebVitals' // Importa a função reportWebVitals, que pode ser usada para medir a performance do aplicativo
//Este arquivo JavaScript é o ponto de entrada do aplicativo React. Ele é responsável por renderizar o componente raiz
// do aplicativo (App) no elemento DOM com o id 'root'. Além disso, ele importa e executa a função reportWebVitals, que
// pode ser usada para medir a performance do aplicativo.


// Cria uma raiz do React no elemento DOM com o id 'root'
const root = ReactDOM.createRoot(document.getElementById('root'))

// Renderiza o componente App na raiz do React
root.render(
    // <React.StrictMode>
    <App />
    // </React.StrictMode>
)

// Chama a função reportWebVitals sem argumentos, o que significa que ela não fará nada por enquanto
// Se você quiser começar a medir a performance do seu aplicativo, pode passar uma função para reportWebVitals
// Essa função será chamada com os resultados das medições de performance
reportWebVitals()
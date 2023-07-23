import React from 'react'
import { BrowserRouter } from 'react-router-dom';
import Routes from './Routes';

//Para conseguir fazer a navegabilidade usaremos o BrowserRouter
//Para isso tivemos que auterar um pouco a estrura do app.js 
//Agora ele irá retornar um conjunto de Routes ao invés de uma pagina fixa
//E dentro desse conjunto de Routes.js habilitamos as navegabilidade (opções de rotas)
function WebApp(){

  return (
    <BrowserRouter>

        <Routes />

    </BrowserRouter>
  );

}

export default WebApp;


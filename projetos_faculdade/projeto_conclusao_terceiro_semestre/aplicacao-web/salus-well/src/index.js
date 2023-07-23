import App from './App';
import React from 'react';
import './index.css';
import ReactDOM from 'react-dom';
// Importa a modal do react-modal
import Modal from 'react-modal';
// Código necessário para os recursos de acessibilidade
Modal.setAppElement('#root');

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);


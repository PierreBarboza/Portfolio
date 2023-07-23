import React from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import Sweet from "../modal/SweetAlert";
import urlBase from '../../Api';
//CSS
import style from "../../components/modal/ModalDesativarConta.module.css"
//imgs icons
import iconAlert from "../../assets/icons/icon-alert.svg"
import Modal from 'react-modal';
Modal.setAppElement('#root');

function ModalDesativarConta(props) {
  const navegar = useNavigate()

  function desativarConta() {
    fecharModal()
    if (sessionStorage.getItem('keyPerfil') === 'Nutricionista'){

      axios.delete(urlBase + `nutricionistas/${sessionStorage.getItem('idUser')}`, {
        headers: {
          'Authorization': `Bearer ${sessionStorage.getItem('keyToken')}`
        }
      }).then(response => {
        navegar('/')
        console.log('Action Desactive account Success:  ' + response)
        
      }).catch(err => {
        Sweet('error', '', 'Não foi possível desativar sua conta', false)
        console.log('Action Desactive account Error:  ' + err)
      })

    } else {

      axios.delete(urlBase + `clientes/${sessionStorage.getItem('idUser')}`, {
        headers: {
          'Authorization': `Bearer ${sessionStorage.getItem('keyToken')}`
        }
      }).then(response => {
        navegar('/')
        console.log('Action Desactive account Success:  ' + response)
  
      }).catch(err => {
        Sweet('error', '', 'Não foi possível desativar sua conta', false)
        console.log('Action Desactive account Error:  ' + err)
      })

    }

  }

  // Hook que demonstra se a modal está aberta ou não
  const [modalIsOpen, setIsOpen] = React.useState(false);

  // Função que abre a modal
  function abrirModal() {
    setIsOpen(true);
  }

  // Função que fecha a modal
  function fecharModal() {
    setIsOpen(false);
  }

  // Código JSX necessário para criar uma modal simples que abre e fecha
  return (
    <div>
      <h5 id='desactive-account' style={{ color: 'red', cursor: 'pointer' }} onClick={abrirModal}>Desativar conta</h5>
      <Modal className={style.modal_main}
        style={{
          overlay: {
            position: 'fixed',
            top: 0,
            left: 0,
            right: 0,
            bottom: 0,
            backgroundColor: 'rgba(000, 000, 000, 0.40)'
          },
          content: {
            position: 'absolute',
            top: '20vh',
            left: '30vw',
            right: '30vw',
            bottom: '20vh',
            border: '1px solid #ccc',
            background: '#fff',
            overflow: 'auto',
            WebkitOverflowScrolling: 'touch',
            borderRadius: '4px',
            outline: 'none',
            padding: '20px'
          }
        }}
        isOpen={modalIsOpen}
        onRequestClose={fecharModal}
        contentLabel="Modal de exemplo"
      >

        <div className={style.modal}>
          <div className={style.icon}>
            <img src={iconAlert} />
          </div>
          <div className={style.text}>
            <h3>Deseja mesmo desativar sua conta ?</h3>
          </div>
          <div className={style.groupButtons}>
            <button className={style.btn_sim} onClick={desativarConta}>Sim</button>
            <button className={style.btn_nao} onClick={fecharModal}>Não</button>
          </div>
        </div>
      </Modal>
    </div>
  );
}

export default ModalDesativarConta;
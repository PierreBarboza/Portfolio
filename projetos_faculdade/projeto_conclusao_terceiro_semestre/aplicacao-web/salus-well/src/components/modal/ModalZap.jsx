import React from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import Sweet from "../modal/SweetAlert";
import filtrarPalavroes from '../../filterPalavroes';
import urlBase from '../../Api'
//CSS
import style from "./ModalZap.module.css"
//imgs icons
import icon_whatsapp from '../../assets/icons/whatsapp.png';
import icon_voltar from '../../assets/icons/back 1.svg'
// Importa a modal do react-modal
import Modal from 'react-modal';
// Código necessário para os recursos de acessibilidade
Modal.setAppElement('#root');

function ModalZap(props) {
    
    var idCliente = sessionStorage.getItem('idUser')
    var idNutri = props.idNutri

    function enviarMensagem() {
        var caixaDeMensagem = document.querySelector('#msg').value
        caixaDeMensagem = caixaDeMensagem.toLowerCase()
        if (caixaDeMensagem === '') {
            Sweet(
                'warning',
                'Atenção !',
                'Preencha o campo para que esse nutricionista entenda suas necessidades.',
                false)

        } else {
            if (filtrarPalavroes(caixaDeMensagem)) {
                Sweet(
                    'warning',
                    'Atenção !',
                    'Existem palavras melhores para se iniciar uma conversa',
                    7000,
                )
            } else {
                // Vinculando Paciente com o nutricionista
                axios.put(urlBase + `clientes/mensagem/${idCliente}/${idNutri}`,{},{
                    headers: {
                        'Authorization': `Bearer ${sessionStorage.getItem('keyToken')}`
                    }
                }
                ).then(res =>{
                    console.log(res.data)
                }).catch(err =>{
                    console.log(err)
                })

                fecharModal()
                window.open(`https://api.whatsapp.com/send?phone=${props.numero}&text=${document.querySelector('#msg').value}`, "Nutricionista");
            }
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
            <button className="button_red" onClick={abrirModal} style={{ width: '120%' }}>
                <img src={icon_whatsapp} />
                Enviar Mensagem
            </button>
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
                        background: 'rgb(183, 251, 183)',
                        overflow: 'auto',
                        WebkitOverflowScrolling: 'touch',
                        borderRadius: '30px',
                        outline: 'none',
                        padding: '20px'
                    }
                }}

                isOpen={modalIsOpen}
                onRequestClose={fecharModal}
                contentLabel="Modal Zap"
            >
                <div className={style.titulo}>
                    <img className={style.icon_voltar} src={icon_voltar} onClick={fecharModal} />
                    <span>Informe uma mensagem para enviar ao Nutricionista:</span>
                </div>

                <div className={style.modal}>
                    <textarea id='msg' rows="20" />
                    <button className='button_red' onClick={enviarMensagem}>Enviar</button>
                    <span id='alertArea'></span>
                </div>

            </Modal>
        </div>
    );
}

export default ModalZap;
import React, { useState } from 'react'
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import Sweet from "../modal/SweetAlert";
import urlBase from '../../Api'

//CSS
import style from "./ModalComorbidades.module.css"
//imgs icons
import icon_check from '../../assets/icons/simbolo-correto.png'
// Importa a modal do react-modal
import Modal from 'react-modal';
// Código necessário para os recursos de acessibilidade
Modal.setAppElement('#root');

function ModalComorbidades() {
    const [idComorbidade, setIdComorbidade] = useState('')

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

    const dataUser = JSON.parse(sessionStorage.getItem('dataUser'));
    const email = dataUser.email;

    axios.get(urlBase + `comorbidades/busca-por-email?email=${email}`, {
        headers: {
            'Authorization': `Bearer ${sessionStorage.getItem('keyToken')}`
        }
    }).then(response => {
        if (response.data.diabete === null) {
            console.log(response.data.diabete)
            abrirModal()
            sessionStorage.setItem('idComorbidade', response.data.id)
            setIdComorbidade(response.data.id)
        }
    })

    const [diabete, setDiabete] = useState(false)
    const [colesterol, setColesterol] = useState(false)
    const [hipertensao, setHipertensao] = useState(false)

    const checkin = event => {
        var element = event.currentTarget

        if (element.id === 'check_diabete') {
            setDiabete(current => !current)
            if (!diabete) { document.querySelector('#icon_diabete').style.display = 'block' }
            else { document.querySelector('#icon_diabete').style.display = 'none' }
        }
        else if (element.id === 'check_colesterol') {
            setColesterol(current => !current)
            if (!colesterol) { document.querySelector('#icon_colesterol').style.display = 'block' }
            else { document.querySelector('#icon_colesterol').style.display = 'none' }

        }
        else if (element.id === 'check_hipertensao') {
            setHipertensao(current => !current)
            if (!hipertensao) { document.querySelector('#icon_hipertensao').style.display = 'block' }
            else { document.querySelector('#icon_hipertensao').style.display = 'none' }

        }


    }

    const enviarComorbidades = () => {

        sessionStorage.setItem('hasDiabete', diabete)
        sessionStorage.setItem('hasColesterol', colesterol)
        sessionStorage.setItem('hasHipertensao', hipertensao)

        if (diabete === true || colesterol === true || hipertensao === true) {

            axios.put(urlBase + `comorbidades`, {
                "id": idComorbidade,
                "diabete": diabete,
                "colesterol": colesterol,
                "hipertensao": hipertensao
            }, {
                headers: {
                    'Authorization': `Bearer ${sessionStorage.getItem('keyToken')}`
                }
            }).then(response => {
                console.log(response.status)
                Sweet('success', '', '', 1000)
                fecharModal()
                window.location.reload()
            }).catch(error => {
                console.log(error)
            })
        } else {
            Sweet('warning', '', 'Essa informação é obrigatória, por favor responda.', 7000)
        }

    }

    return (

        <div>
            <Modal className={style.modal_main}
                style={{
                    overlay: {
                        position: 'absolute',
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
                        border: '3px solid #FF5451',
                        background: '#fff',
                        overflow: 'auto',
                        WebkitOverflowScrolling: 'touch',
                        borderRadius: '30px',
                        outline: 'none',
                        padding: '20px'
                    }
                }}

                isOpen={modalIsOpen}
                onRequestClose={fecharModal}
                contentLabel="Modal Comorbidades"
                shouldCloseOnOverlayClick={false}
            >
                <div className={style.titulo}>
                    <span style={{ textAlign: 'center' }}>
                        Para ter acesso á algumas das funcionalidades da Plataforma<br />
                        Você precisa responder esse formulário.
                        <br /><br />
                        Selecione as comorbidades que você possui:<br />
                    </span>
                </div>

                <div className={style.modal}>
                    <div id='option_diabete'>
                        <span id='check_diabete' className={style.checkboxPersonalizado} onClick={checkin}><img id='icon_diabete' src={icon_check} style={{ width: '100%', display: 'none' }} /></span>
                        <span className={style.title_option}> Diabete</span>
                    </div>
                    <div id='option_colesterol'>
                        <span id='check_colesterol' className={style.checkboxPersonalizado} onClick={checkin}><img id='icon_colesterol' src={icon_check} style={{ width: '100%', display: 'none' }} /></span>
                        <span className={style.title_option}> Colesterol</span>
                    </div>
                    <div id='option_hipertensao'>
                        <span id='check_hipertensao' className={style.checkboxPersonalizado} onClick={checkin}><img id='icon_hipertensao' src={icon_check} style={{ width: '100%', display: 'none' }} /></span>
                        <span className={style.title_option}> Hipertensão</span>
                    </div>

                    <button className='button_red' onClick={enviarComorbidades} style={{ marginTop: '20px' }}>Enviar</button>
                    <span id='alertArea'></span>
                </div>

            </Modal>
        </div>
    );
}

export default ModalComorbidades;
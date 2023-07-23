import React, { useState } from 'react'
import style from '../../component_screen/nutricionista/NutricionistaPerfil.module.css';
import Header from "../../header/Header";
import axios from 'axios';
import urlBase from '../../../Api';
import Sweet from "../../modal/SweetAlert";
import ModalDesativarConta from '../../modal/ModalDesativarConta';

//Avatares 
import avatarDefault from "../../../assets/avatar/avatar-default.svg";

import avatarFemale1 from "../../../assets/avatar/menina-avatar-1.png";
import avatarFemale2 from "../../../assets/avatar/menina-avatar-2.png";
import avatarFemale3 from "../../../assets/avatar/menina-avatar-3.png";
import avatarFemale4 from "../../../assets/avatar/menina-avatar-4.png";
import avatarFemale5 from "../../../assets/avatar/menina-avatar-5.png";
import avatarFemale6 from "../../../assets/avatar/menina-avatar-6.png";
import avatarFemale7 from "../../../assets/avatar/menina-avatar-7.png";

import avatarMale1 from "../../../assets/avatar/menino-avatar-1.png";
import avatarMale2 from "../../../assets/avatar/menino-avatar-2.png";
import avatarMale3 from "../../../assets/avatar/menino-avatar-3.png";
import avatarMale4 from "../../../assets/avatar/menino-avatar-4.png";
import avatarMale5 from "../../../assets/avatar/menino-avatar-5.png";
import avatarMale6 from "../../../assets/avatar/menino-avatar-6.png";
import avatarMale7 from "../../../assets/avatar/menino-avatar-7.png";

//imgs icons
import iconEdit from "../../../assets/avatar/icon-edit.svg"

// Importa a modal do react-modal
import Modal from 'react-modal';

// Código necessário para os recursos de acessibilidade
Modal.setAppElement('#root');

export default function NutricionistaPerfil() {
    var user = JSON.parse(sessionStorage.getItem('dataUser'))
    var id = user.id
    var nome = user.nome
    var email = user.email
    // var senha = user.senha
    var contato = user.telefone
    var genero = user.genero
    var endereco = user.endereco

    let avatares = [
        avatarDefault, avatarFemale1, avatarFemale2, avatarFemale3, avatarFemale4, avatarFemale5, avatarFemale6, avatarFemale7,
        avatarMale1, avatarMale2, avatarMale3, avatarMale4, avatarMale5, avatarMale6, avatarMale7
    ]

    var idAvatar = sessionStorage.getItem('idAvatar')
    var srcAvatar = avatares[idAvatar]
    sessionStorage.setItem('srcAvatar', srcAvatar)
    var avatarAuxiliar = srcAvatar;

    const [imgProfile, setImgProfile] = useState(srcAvatar !== null ? avatarAuxiliar : avatarDefault)

    const [changeAvatar, setChangeAvatar] = useState(false)

    let inputs = ['#nome', '#endereco', "#genero", "#email", "#senha", "#contato"];
    let inputsNotEdit = ['#endereco', "#genero", "#email", "#senha", "#contato"];

    function originalInputStyle() {
        for (var i = 0; i < inputs.length; i++) {
            document.querySelector(inputs[i]).style.backgroundColor = '#F3F3F3'
            document.querySelector(inputs[i]).disabled = true
        }
        document.querySelector('#nome').placeholder = nome
    }

    const buttonEdit = event => {
        var element = event.currentTarget;
        if (element.id === 'editar') {
            element.style.display = 'none';
            document.querySelector('#salvar').style.display = 'block'
            document.querySelector('#cancelar').style.display = 'block'

            document.querySelector('#avatarPerfil').style.display = 'none'
            document.querySelector('#editar_avatar').style.display = 'block'

            document.querySelector('#nome').disabled = false
            document.querySelector('#nome').placeholder = nome

            for (var y = 0; y < inputsNotEdit.length; y++) {
                document.querySelector(inputsNotEdit[y]).style.backgroundColor = '#ccc'
            }

        }
        else if (element.id === 'salvar') {

            document.querySelector('#salvar').style.display = 'none'
            document.querySelector('#cancelar').style.display = 'none'
            document.querySelector('#editar').style.display = 'block'
            document.querySelector('#avatarPerfil').style.display = 'block'
            document.querySelector('#editar_avatar').style.display = 'none'

            if (changeAvatar){
                sessionStorage.setItem('srcAvatar', sessionStorage.getItem('srcAvatarPRE'))
                sessionStorage.setItem('idAvatar', sessionStorage.getItem('idAvatarPRE'))
                var srcAvatar = sessionStorage.getItem('srcAvatar');
                setImgProfile(srcAvatar)
                avatarAuxiliar = imgProfile;
            } else {
                sessionStorage.setItem('idAvatar', idAvatar)
            }

            if (document.querySelector('#nome').value !== '') {
                nome = document.getElementById('nome').value;
            }

            originalInputStyle();

            // Atualizando os dados
            axios.put(urlBase + 'nutricionistas', {
                "id": id,
                "nome": nome,
                "avatar": sessionStorage.getItem('idAvatar'),
            }).then((res) => {
                Sweet('success', '', '', 1000)
                axios.get(urlBase + `nutricionistas/${sessionStorage.getItem('idUser')}`)
                    .then(response => {
                        sessionStorage.setItem('dataUser', JSON.stringify(response.data))
                    })
                setTimeout(atualizarPagina, 1000)
                console.log(res)
            }).catch((error) => { console.error(error) })

            setChangeAvatar(false)
        }
        else if (element.id === 'cancelar') {
            setChangeAvatar(false)
            document.querySelector('#salvar').style.display = 'none'
            document.querySelector('#cancelar').style.display = 'none'
            document.querySelector('#editar').style.display = 'block'
            document.querySelector('#avatarPerfil').style.display = 'block'
            document.querySelector('#editar_avatar').style.display = 'none'

            // Volta com o avatar padrão (anterior já selecionado)
            avatarPage = document.querySelector("#avatarPage");
            avatarPage.setAttribute('src', imgProfile)

            originalInputStyle();
        }
    }

    function atualizarPagina() {
        window.location.reload()
    }
    // _____________________Lidando Com a seleção de Avatar_____________
    var srcAvatarSelected;
    var imgTarget;
    var srcImagemAlvo;
    var avatarPage;
    var countSelected = 0;

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
    const avatarConfig = event => {
        if (event.currentTarget.id === 'save') {
            setChangeAvatar(true)
            if (countSelected !== 0) {
                avatarPage = document.querySelector("#avatarPage");
                avatarPage.setAttribute('src', srcImagemAlvo)
                srcAvatarSelected = avatarPage.getAttribute('src')
            }    
        }
        setIsOpen(false);
        sessionStorage.setItem("idAvatarPRE", idAvatar)
        sessionStorage.setItem("srcAvatarPRE", srcImagemAlvo)
        console.log('ID do avatar atual: ' + idAvatar)
        console.log('SRC do avatar atual: ' + srcImagemAlvo)
    }
    const configSelection = event => {
        countSelected += 1;
        var avatares = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"]
        for (var i = 0; i < avatares.length; i++) {
            document.getElementById(avatares[i]).style.border = "none";
        }
        document.getElementById(event.currentTarget.id).style.border = "3px solid green";

        imgTarget = document.getElementById(event.currentTarget.id);
        idAvatar = event.currentTarget.id;
        srcImagemAlvo = imgTarget.getAttribute('src')

        avatarPage = document.querySelector("#avatarPage");
        document.querySelector('#save').style.display = 'block';
    }

    return (
        <>
            <div className={style.main_user_perfil_screen}>
                <Header titulo={"Perfil"} subtitulo="Edite seus dados" />
                <div className={style.main_card_user_perfil}>
                    <div className={style.header_content}>
                        <span id='avatarPerfil' style={{ display: 'block' }}>
                            <img src={imgProfile} style={{ width: "80px", borderRadius: "45px" }} />
                        </span>
                        {/* Avatar Mode Edit */}
                        <span id='editar_avatar' style={{ display: 'none' }}>
                            <div>
                                <button className="btn_avatar" onClick={abrirModal}>
                                    {/* Escolha de Avatar */}
                                    {/* Quando clicar no cancelar, envia a imagem do Auxiliar se não, envia a do sessionStorage */}
                                    <div id="avatarSelection" className="select_avatar">
                                        <img id="avatarPage" src={sessionStorage.getItem('srcAvatar')} className="avatarPage" style={{ width: "80px", borderRadius: "45px" }} />
                                        <span>
                                            <img className="icon_edit" src={iconEdit} style={{ marginBottom: "-12px", marginLeft: "-25px" }} />
                                        </span>
                                    </div>
                                </button>
                                <Modal
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
                                    <div className='header_modal'>
                                        <p className='title_modal'>Selecione um avatar</p>
                                    </div>
                                    <div className='body_modal'>
                                        <div id='section_default'>
                                            <img id='0' src={avatarDefault} style={{ width: "60px", borderRadius: "30px" }} onClick={configSelection} />
                                        </div>
                                        <div id='section_female'>
                                            <img id='1' src={avatarFemale1} style={{ width: "60px", borderRadius: "30px" }} onClick={configSelection} />
                                            <img id='2' src={avatarFemale2} style={{ width: "60px", borderRadius: "30px" }} onClick={configSelection} />
                                            <img id='3' src={avatarFemale3} style={{ width: "60px", borderRadius: "30px" }} onClick={configSelection} />
                                            <img id='4' src={avatarFemale4} style={{ width: "60px", borderRadius: "30px" }} onClick={configSelection} />
                                            <img id='5' src={avatarFemale5} style={{ width: "60px", borderRadius: "30px" }} onClick={configSelection} />
                                            <img id='6' src={avatarFemale6} style={{ width: "60px", borderRadius: "30px" }} onClick={configSelection} />
                                            <img id='7' src={avatarFemale7} style={{ width: "60px", borderRadius: "30px" }} onClick={configSelection} />
                                        </div>
                                        <div id='section_male'>
                                            <img id='8' src={avatarMale1} style={{ width: "60px", borderRadius: "30px" }} onClick={configSelection} />
                                            <img id='9' src={avatarMale2} style={{ width: "60px", borderRadius: "30px" }} onClick={configSelection} />
                                            <img id='10' src={avatarMale3} style={{ width: "60px", borderRadius: "30px" }} onClick={configSelection} />
                                            <img id='11' src={avatarMale4} style={{ width: "60px", borderRadius: "30px" }} onClick={configSelection} />
                                            <img id='12' src={avatarMale5} style={{ width: "60px", borderRadius: "30px" }} onClick={configSelection} />
                                            <img id='13' src={avatarMale6} style={{ width: "60px", borderRadius: "30px" }} onClick={configSelection} />
                                            <img id='14' src={avatarMale7} style={{ width: "60px", borderRadius: "30px" }} onClick={configSelection} />
                                        </div>
                                    </div>
                                    <div className='footer_modal'>
                                        <button id='save' onClick={avatarConfig} style={{ display: "none" }}>Selecionar</button>
                                    </div>
                                </Modal>
                            </div>
                        </span>
                        <div>
                            <ModalDesativarConta />
                        </div>
                    </div>
                    <div className={style.body_content}>
                        <div className={style.section_left}>
                            <div className={style.campos}>
                                <span>
                                    <label>Nome:</label>
                                    <input id="nome" className={style.input} type="text" disabled={true} placeholder={nome} />
                                </span>
                                <span>
                                    <label>Endereço:</label>
                                    <input id="endereco" className={style.input} type="text" disabled={true} value={endereco} />
                                </span>
                                <span>
                                    <label>Genero:</label>
                                    <input id="genero" className={style.input} type="text" disabled={true} value={genero} />
                                </span>
                            </div>
                            <div className={style.area_botao}>
                                <button id='editar' className="button_red" onClick={buttonEdit} style={{ display: 'block' }}>Editar</button>
                                <button id='salvar' className="button_red" onClick={buttonEdit} style={{ display: 'none' }}>Salvar</button>
                                <button id='cancelar' className="button_white" onClick={buttonEdit} style={{ display: 'none' }}>Cancelar</button>
                            </div>
                        </div>
                        <div className={style.section_right}>
                            <div className={style.campos}>
                                <span>
                                    <label>Email:</label>
                                    <input id="email" className={style.input} type="text" disabled={true} value={email} />
                                </span>
                                <span>
                                    <label>Senha:</label>
                                    <input id="senha" className={style.input} type="password" disabled={true} value={'*********'} />
                                </span>
                                <span>
                                    <label>Contato:</label>
                                    <input id="contato" className={style.input} type="text" disabled={true} value={contato} />
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}
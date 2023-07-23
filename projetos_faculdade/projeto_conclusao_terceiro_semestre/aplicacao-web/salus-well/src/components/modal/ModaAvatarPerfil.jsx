import React from 'react';
import '../component_screen/paciente/PacientePerfil';

//CSS
import "./ModalAvatarPerfil.css"

//Avatares 
import avatarDefault from "../../assets/avatar/avatar-default.svg";

import avatarFemale1 from "../../assets/avatar/menina-avatar-1.png";
import avatarFemale2 from "../../assets/avatar/menina-avatar-2.png";
import avatarFemale3 from "../../assets/avatar/menina-avatar-3.png";
import avatarFemale4 from "../../assets/avatar/menina-avatar-4.png";
import avatarFemale5 from "../../assets/avatar/menina-avatar-5.png";
import avatarFemale6 from "../../assets/avatar/menina-avatar-6.png";
import avatarFemale7 from "../../assets/avatar/menina-avatar-7.png";

import avatarMale1 from "../../assets/avatar/menino-avatar-1.png";
import avatarMale2 from "../../assets/avatar/menino-avatar-2.png";
import avatarMale3 from "../../assets/avatar/menino-avatar-3.png";
import avatarMale4 from "../../assets/avatar/menino-avatar-4.png";
import avatarMale5 from "../../assets/avatar/menino-avatar-5.png";
import avatarMale6 from "../../assets/avatar/menino-avatar-6.png";
import avatarMale7 from "../../assets/avatar/menino-avatar-7.png";

//imgs icons
import iconEdit from "../../assets/avatar/icon-edit.svg"

// Importa a modal do react-modal
import Modal from 'react-modal';

// Código necessário para os recursos de acessibilidade
Modal.setAppElement('#root');

function ModalAvatarPerfil(props) {
    // _____________________Lidando Com a seleção de Avatar_____________
    var imgTarget;
    var srcImagemAlvo;
    var avatarPage;
    var idAvatar; // Aqui fica o id do avatar selecionado
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
            if (countSelected !== 0) {
                avatarPage = document.querySelector("#avatarPage");
                srcAvatarSelected = avatarPage.getAttribute('src')
            }
        }

        setIsOpen(false);
        sessionStorage.setItem("idAvatar", idAvatar)
        sessionStorage.setItem("srcAvatar", srcImagemAlvo)
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

    // Código JSX necessário para criar uma modal simples que abre e fecha
    return (
        <div>
            <button className="btn_avatar" onClick={abrirModal}>

                {/* Escolha de Avatar */}
                {/* Quando clicar no cancelar, envia a imagem do Auxiliar se não, envia a do sessionStorage */}
                <div id="avatarSelection" className="select_avatar">
                    <img id="avatarPage" src={props.image} className="avatarPage" style={{ width: "80px", borderRadius: "45px" }} />
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
    );
}

export default ModalAvatarPerfil;
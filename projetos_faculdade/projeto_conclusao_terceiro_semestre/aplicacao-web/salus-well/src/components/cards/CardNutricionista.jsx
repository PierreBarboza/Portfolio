import React from 'react'
import style from "../../components/cards/CardNutricionista.module.css";
import styleCard from "../cards/CardNutricionista.module.css"
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import urlBase from '../../Api'
import Sweet from '../modal/SweetAlert'

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

export default function CardNutricionista(props) {

    const navigate = useNavigate()

    let avatares = [
        avatarDefault, avatarFemale1, avatarFemale2, avatarFemale3, avatarFemale4, avatarFemale5, avatarFemale6, avatarFemale7,
        avatarMale1, avatarMale2, avatarMale3, avatarMale4, avatarMale5, avatarMale6, avatarMale7
    ]

    var idAvatar = props.avatar
    var srcAvatar = avatares[idAvatar]

    const desvincularNutri = () => {

        axios.delete(urlBase + `clientes/desvincular/${sessionStorage.getItem('idUser')}`)
            .then(response => {
                console.log(response);
                if (response.status === 200) {

                    localStorage.setItem('keyPossuiNutri', false);

                    Sweet('success', '', 'Nutricionista desvinculado com sucesso', 4000);
                    props.desvincularNutricionista();
                    setTimeout(() => {
                        window.location.reload(true);
                    }, 4000);
                } else {
                    Sweet('error', '', 'Não foi possível desvincular o nutricionista, tente novamente mais tarde', 5000);
                }
            })
            .catch(err => {
                console.log(err);
                Sweet('error', '', 'Não foi possível desvincular o nutricionista, tente novamente mais tarde', 5000);
            });
    };

    const abrirChat = () => {
        localStorage.setItem('keyPossuiNutri', true);
        axios.put(urlBase + `clientes/mensagem/${sessionStorage.getItem('idUser')}/${props.id}`, {}, {
            headers: {
                'Authorization': `Bearer ${sessionStorage.getItem('keyToken')}`
            }
        }
        ).then(res => {
            console.log(res.data)
        }).catch(err => {
            console.log(err)
        })

        sessionStorage.setItem('ID_msg', props.id)
        sessionStorage.setItem('AVATAR_msg', props.avatar)
        sessionStorage.setItem('NOME_msg', props.nome)

        Sweet(false, 'Aguarde...', 'Estamos abrindo o chat', 2500)

        navigate('/Chat')
    }

    return (
        <>
            <div className={style.div_geral}>
                <div className={style.main_user_home_screen}>
                    {/* Card de um Cliente */}
                    <div className={style.main_card_user_home}>
                        <div className={styleCard.imgAvatar}>
                            <img src={srcAvatar} />
                        </div>
                        <div className={styleCard.headerBiografy}>
                            <div className={styleCard.header}>
                                <b style={{ fontSize: "x-large", color: "var(--dark-green)" }}>Nutricionista</b>
                                <p><b>Nome: </b>{props.nome}</p>
                                <p><b>Telefone: </b>{props.telefone}</p>
                            </div>
                        </div>
                        <div className={styleCard.group_button}>
                            <button className='button_red' style={{ width: '190px' }} onClick={abrirChat}>Abrir chat</button>
                            {props.vinculado ?
                                <><div id={style.btn_desativar} className='button_white' onClick={desvincularNutri} style={{ width: "50%", textAlign: "center" }}>Desvincular Nutricionista</div></>
                                :
                                <></>
                            }
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}
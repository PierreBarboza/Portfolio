import React from 'react'
import style from "../../components/cards/CardCliente.module.css";
import styleCard from "../cards/CardCliente.module.css"
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
import { useNavigate } from 'react-router-dom';

export default function CardCliente(props) {

    const navigate = useNavigate();
    
    console.log(props.hasComorbidade)

    let avatares = [
        avatarDefault, avatarFemale1, avatarFemale2, avatarFemale3, avatarFemale4, avatarFemale5, avatarFemale6, avatarFemale7,
        avatarMale1, avatarMale2, avatarMale3, avatarMale4, avatarMale5, avatarMale6, avatarMale7
    ]

    var idAvatar = props.avatar
    var srcAvatar = avatares[idAvatar]
    // console.log(props.avatar)

    const abrirChat = () =>{
        
        sessionStorage.setItem('ID_msg', props.id)
        sessionStorage.setItem('AVATAR_msg', props.avatar)
        sessionStorage.setItem('NOME_msg', props.nome)
        
        // Sweet(false, 'Aguarde...', '', 5000)
        // setTimeout(() => {
            
        // }, 2500);
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
                                <p><b>Nome: </b>{props.nome}</p>
                                <p><b>Telefone: </b>{props.telefone}</p>
                                <p><b>Endereço: </b>{props.endereco}</p>
                                {props.filtro ?
                                    <>
                                        <p><b>Comorbidades: </b>
                                            {props.diabete ? 'Diabete | ' : ''}
                                            {props.colesterol ? 'colesterol | ' : ''}
                                            {props.hipertensao ? 'hipertensao' : ''}
                                        </p>
                                    </>
                                    :
                                    <>
                                        <p><b>Comorbidades: </b>{props.hasComorbidade ? 'Sim' : 'Não'}</p>
                                    </>
                                }
                            </div>
                        </div>
                        <button className='button_red' style={{width: '190px'}} onClick={abrirChat}>Abrir chat</button>
                    </div>
                </div>
            </div>
        </>
    )
}
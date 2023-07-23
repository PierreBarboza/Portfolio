import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from "react-router-dom";
import styles from './NutricionistaSideBar.module.css';
import avatar_default from "../../assets/avatar/avatar-default.svg";
import icon_home from "../../assets/icons/icon-home.svg";
import icon_alimentos from "../../assets/icons/icon-alimento-nutricionista.svg";
import icon_criar_rotina_alimentar from '../../assets/icons/icon_diario_alimentar.png';
import icon_cliente from "../../assets/icons/icon-cliente.svg"
import icon_sair from "../../assets/icons/icon-sair.svg";

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

export default function UserSideBar() {
    const navigate = useNavigate();

    const [dataUser, setDataUser] = useState([]);

    useEffect(() => {
        const response = JSON.parse(sessionStorage.getItem('dataUser'));
        setDataUser(response);
    }, [])

    let avatares = [
        avatarDefault, avatarFemale1, avatarFemale2, avatarFemale3, avatarFemale4, avatarFemale5, avatarFemale6, avatarFemale7,
        avatarMale1, avatarMale2, avatarMale3, avatarMale4, avatarMale5, avatarMale6, avatarMale7
    ]
    var idAvatar = sessionStorage.getItem('idAvatar')
    var srcAvatar = avatares[idAvatar]

    const removerCredenciais = () => {
        sessionStorage.clear();
        navigate('/');
    }

    const perfil = () =>{
        navigate('/perfilNutricionista')
    }

    return (
        <>
            <nav className={styles.side_bar}>
                <ul className={styles.container_avatar} onClick={perfil}>
                    <img src={srcAvatar} />
                    {/* <img src={dataUser.avatar === dataUser.avatar ? (avatares[dataUser.avatar]) : avatar_default} /> */}
                </ul>
                <ul>
                    <button className={styles.btn_bar} onClick={() => navigate('/homeNutricionista')}>
                        <img className={styles.img_icon} src={icon_home} alt="icone_home" />
                        <p>Home</p>
                    </button>
                </ul>
                <hr />
                <ul>
                    <button className={styles.btn_bar} onClick={() => navigate('/listaClientes')}>
                        <img className={styles.img_icon} src={icon_cliente} alt="icone_cliente" />
                        <p>Clientes</p>
                    </button>
                </ul>
                <ul>
                    <button className={styles.btn_bar} onClick={() => navigate('/listarAlimentos')}>
                        <img className={styles.img_icon} src={icon_alimentos} alt="icone_alimentos" />
                        <p>Alimentos</p>
                    </button>
                </ul>
                <ul>
                    <button className={styles.btn_bar} onClick={() => navigate('/selecionarAlimentos')}>
                        <img className={styles.img_icon} src={icon_criar_rotina_alimentar} alt="icone_alimentos" />
                        <p>Criar rotina</p>
                    </button>
                </ul>
                <hr />
                <ul>
                    <button className={styles.btn_bar} onClick={removerCredenciais}>
                        <img className={styles.img_icon} src={icon_sair} alt="icone_sair" />
                        <p>Sair</p>
                    </button>
                </ul>
            </nav>
        </>
    )
}

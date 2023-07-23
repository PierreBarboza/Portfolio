import React from 'react';
import { useNavigate } from "react-router-dom";
import styles from './PacienteSideBar.module.css';
import icon_home from "../../assets/icons/icon-home.svg";
import icon_nutricionista from "../../assets/icons/icon-nutricionista.svg";
import icon_diario_alimentar from "../../assets/icons/diario_paciente.png";
import icon_alimentos from "../../assets/icons/icon-alimentos.svg";
import icon_historico from "../../assets/icons/icon-historico.svg";
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

    let avatares = [
        avatarDefault, avatarFemale1, avatarFemale2, avatarFemale3, avatarFemale4, avatarFemale5, avatarFemale6, avatarFemale7,
        avatarMale1, avatarMale2, avatarMale3, avatarMale4, avatarMale5, avatarMale6, avatarMale7
    ]
    var idAvatar = sessionStorage.getItem('idAvatar')
    var srcAvatar = avatares[idAvatar]

    const navigate = useNavigate();

    const removerCredenciais = () => {
        sessionStorage.clear();
        navigate('/');
    }

    const perfil = () => {
        navigate('/perfilPaciente')
    }

    return (
        <>
            <nav className={styles.side_bar}>
                <ul className={styles.container_avatar} onClick={perfil}>
                    <img src={srcAvatar} alt="user_avatar" style={{ width: '80px', borderRadius: '60px' }} />
                </ul>
                <ul>
                    <button className={styles.btn_bar} onClick={() => navigate('/homePaciente')}>
                        <img className={styles.img_icon} src={icon_home} alt="icone_home" />
                        <p>Home</p>
                    </button>
                </ul>
                <hr />
                <ul>
                    <button className={styles.btn_bar} onClick={() => navigate('/listaNutricionistas')}>
                        <img className={styles.img_icon} src={icon_nutricionista} alt="icone_nutricionista" />
                        <p>Nutricionista</p>
                    </button>
                </ul>
                <ul>
                    <button className={styles.btn_bar} onClick={() => navigate('/diarioAlimentarPaciente')}>
                        <img className={styles.img_icon} src={icon_diario_alimentar} alt="icone_diario_alimentar" />
                        <p>Diário alimentar</p>
                    </button>
                </ul>
                <ul>
                    <button className={styles.btn_bar} onClick={() => navigate('/searchFood')}>
                        <img className={styles.img_icon} src={icon_alimentos} alt="icone_alimentos" />
                        <p>Alimentos</p>
                    </button>
                </ul>
                <ul>
                    <button className={styles.btn_bar} onClick={() => navigate('/historico')}>
                        <img className={styles.img_icon} src={icon_historico} alt="icone_historico" />
                        <p>Histórico</p>
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

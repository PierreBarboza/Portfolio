import React, { useEffect, useState } from 'react'
import style from '../nutricionista/NutricionistaHomeScreen.module.css';
import Header from "../../header/Header";
import img_main_card from "../../../assets/icons/img-home-card-nutricionista.svg";
import img_card_alimento from "../../../assets/img/nutricionista-alimento.svg"
import { useNavigate } from 'react-router-dom';

export default function UserHomeScreen() {
    const navigate = useNavigate();

    const [dataUser, setDataUser] = useState([]);

    useEffect(() => {
        const response = JSON.parse(sessionStorage.getItem('dataUser'));
        setDataUser(response);
    }, [])

    return (
        <>
            <div className={style.main_user_home_screen}>
                <Header
                    titulo={"Olá " + (dataUser.nome ? dataUser.nome : 'Usuário')}
                    subtitulo="Seja bem-vindo(a)"
                />
                <div className={style.main_card_user_home}>
                    <div className={style.title_main_card}>
                        <p className={style.title}>Clientes</p>
                        <p className={style.sub_title}>Entre em contato com os seus clientes e crie um diário Alimentar.
                            Temos todas as ferramentas que você precisa para conquistar novos clientes.</p>
                        <button className="button_red" onClick={() => navigate("/listaClientes")}>Consultar</button>
                    </div>
                    <div className={style.img_main_card}>
                        <img src={img_main_card} alt="img_main" />
                    </div>
                </div>
                <div className={style.main_card_user_home_alimentos}>
                    <div className={style.title_main_card_alimentos}>
                        <p className={style.title}>Alimentos</p>
                        <p className={style.sub_title_alimentos}>Veja detalhes específicos de cada alimento e seus benefícios e
                            propriedades. Cadastre alimentos e crie diários alimentares para seus pacientes!</p>
                        <button className="button_red" onClick={() => navigate("/listarAlimentos")}>Consultar alimentos</button>
                    </div>
                    <div className={style.img_main_card}>
                        <img src={img_card_alimento} alt="img_main" />
                    </div>
                </div>
            </div>
        </>
    )
}
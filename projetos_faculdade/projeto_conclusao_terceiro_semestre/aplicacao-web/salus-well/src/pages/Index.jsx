import { React, useState, useEffect } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";

import style from './Index.module.css'
//NAVBAR
import logo from "../assets/logo/logo-green.svg";
import icon_nav_home from "../assets/icons/icon-home.png";
import icon_nav_apple from "../assets/icons/icon-apple.png";
import icon_nav_star from "../assets/icons/icon-star.png";
//HOME
import img_woman from "../assets/persons/woman-home-page-2.png"
import icon_banner_heart from "../assets/icons/coracao.png"
import icon_banner_fruits from "../assets/icons/comida-saudavel-2.png"
//BENEFICIOS
import icon_beneficio_apple from "../assets/icons/apple.png"
import img_alimentos from "../assets/background/alimentos.png"
//AVALIACOES
import icon_avaliacao_star from "../assets/icons/star.png"
import img_person_1 from "../assets/persons/pessoa1.png"
import img_person_2 from "../assets/persons/pessoa2.png"
import img_person_3 from "../assets/persons/pessoa3.png"
import img_person_4 from "../assets/persons/pessoa4.png"
import img_person_5 from "../assets/persons/pessoa5.png"
import img_person_6 from "../assets/persons/pessoa6.png"
import icon_arrow from "../assets/icons/arrow.png"
import icon_balloon_message from "../assets/icons/message.png"
//MODAL
import Modal from '../components/modal/Modal';
import Acessibilidade from '../components/acessibilidade/Acessibilidade';

function Home() {
    const navigate = useNavigate();

    //NAVBAR
    useEffect(() => {
        navbar();
    }, []);

    //MODAL
    const [display, setDisplay] = useState(false);
    const toggleDisplay = () => {
        setDisplay(!display);
    }

    //CARROSEL
    const [currentIndex, setCurrentIndex] = useState(0);
    const [images, setImages] = useState([
        { id: 1, src: img_woman, title: "pessoa1" },
        { id: 2, src: img_person_2, title: "pessoa2" },
        { id: 3, src: img_person_6, title: "pessoa3" },
        { id: 4, src: img_person_1, title: "pessoa4" },
        { id: 5, src: img_person_4, title: "pessoa5" },
        { id: 6, src: img_person_5, title: "pessoa6" },
        { id: 7, src: img_person_3, title: "pessoa7" },
    ]);
    const nextImage = () => {
        if (currentIndex === images.length - 1) {
            setCurrentIndex(0);
        } else {
            setCurrentIndex(currentIndex + 1);
        }
    };
    useEffect(() => {
        const interval = setInterval(() => {
            nextImage();
        }, 3000);
        return () => clearInterval(interval);
    }, [currentIndex]);

    //ACESSIBILIDADE
    const [fontSize, setFontSize] = useState(16);
    function handleFontSizeChange(newFontSize) {
        setFontSize(newFontSize);
    }

    return (
        <>
            <body className={style.body_home} id="body_main">
                {display ? <Modal /> : <></>}
                <Acessibilidade onChangeFontSize={handleFontSizeChange} />
                <section className={style.main}>
                    <section className={style.screen_home}>
                        <nav className={style.nav} id="nav">
                            <div className={style.img_logo}>
                                <img src={logo} alt="logo_sallus_well" />
                            </div>
                            <div className={style.group_options}>
                                <a href="#body_main" className={style.option}>
                                    <img src={icon_nav_home} alt="icon_house" />
                                    <p>Home</p>
                                </a>
                                <a href="#title_beneficio" className={style.option}>
                                    <img src={icon_nav_apple} alt="icon_apple" />
                                    <p>Benefícios</p>
                                </a>
                                <a href="#title_avaliacao" className={style.option}>
                                    <img src={icon_nav_star} alt="icon_star" />
                                    <p>Avaliações</p>
                                </a>
                            </div>
                            <div className={style.group_buttons}>
                                <button className="button_white" onClick={toggleDisplay}>Login</button>
                                <button className="button_red" onClick={() => navigate('/cadastroPaciente')}>Cadastrar</button>
                            </div>
                        </nav>
                        <div className={style.container}>
                            <div className={style.main_content}>
                                <div className={style.text_content}>
                                    <p className={style.title}>Sua rotina e bem-estar começa aqui.</p>
                                    <p className={style.sub_title}>
                                        <span className="color_link" onClick={() => navigate('/cadastroPaciente')}>Clique aqui </span>
                                        e inicie a mudança em sua vida.
                                    </p>
                                    <a href="#screen_beneficio">
                                        <button className={style.button_main_content}>Saiba mais</button>
                                    </a>
                                </div>
                                <div className={style.photo_content}>
                                    <img
                                        className={style.myImage}
                                        src={images[currentIndex].src}
                                        alt={images[currentIndex].title}
                                    />
                                </div>
                                <div className={style.banner}>
                                    <div className={style.card}>
                                        <div className={style.title}>
                                            <img src={icon_banner_heart} alt="heart_icon" width="26px" />
                                            <p style={{ fontSize: `${fontSize}px` }}>Bem-estar</p>
                                        </div>
                                        <div className={style.description}>
                                            <p style={{ fontSize: `${fontSize}px` }}>Profissionais capacitados pronto para atender e entender você.</p>
                                        </div>
                                    </div>
                                    <div className={style.card}>
                                        <div className={style.title}>
                                            <img src={icon_banner_fruits} alt="food_icon" width="28px" />
                                            <p style={{ fontSize: `${fontSize}px` }}>Praticidade</p>
                                        </div>
                                        <div className={style.description}>
                                            <p style={{ fontSize: `${fontSize}px` }}>consultas sem complicações, atendimento feito do conforto da sua casa.</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                    <section className={style.screen_beneficio} id="screen_beneficio">
                        <div className={style.title_beneficio} id="title_beneficio">
                            <div className={style.title}>
                                <img src={icon_beneficio_apple} alt="apple" width="50px" />
                                <p className={style.text_title}>Benefícios</p>
                            </div>
                            <div className={style.sub_title}>
                                <p className={style.text_sub_title} style={{ fontSize: `${fontSize}px` }}>Sempre pensando no melhor para atender nossos clientes</p>
                            </div>
                        </div>
                        <div className={style.container_beneficio}>
                            <div className={style.image}>
                                <img src={img_alimentos} alt="alimento" />
                            </div>
                            <div className={style.group_card} style={{ fontSize: `${fontSize}px` }}>
                                <div className={style.card}>
                                    <p className={style.number}>99+</p>
                                    <p className={style.description}>Nutricionistas on-line agora mesmo para te atender, na hora que você
                                        quiser e quando quiser.</p>
                                </div>
                                <div className={style.card}>
                                    <p className={style.number}>99+</p>
                                    <p className={style.description}>Pacientes buscando uma alimentação saúdavel, começe agora mesmo a atender
                                        no conforto de sua casa.</p>
                                </div>
                                <div className={style.card}>
                                    <p className={style.number}>99+</p>
                                    <p className={style.description}>Tipos de alimentos disponíveis para você consultar no nosso sistema e
                                        descobrir seus benefícios.</p>
                                </div>
                            </div>
                        </div>
                    </section>
                    <section className={style.screen_avaliacao} id="screen_avaliacao">
                        <div className={style.title_avaliacao} id="title_avaliacao">
                            <div className={style.title}>
                                <img src={icon_avaliacao_star} alt="star" width="50px" />
                                <p className={style.text_title}>Avaliações</p>
                            </div>
                            <div className={style.sub_title}>
                                <p className={style.text_sub_title} style={{ fontSize: `${fontSize}px` }}>Opiniões e sugestões dos nossos usúarios.</p>
                            </div>
                        </div>
                        <div className={style.container_avaliacao}>
                            <div className={style.group_person}>
                                <img src={img_person_4} alt="person4" />
                                <img src={img_person_1} alt="person1" />
                                <img src={img_person_6} alt="person6" />
                                <img src={img_person_2} alt="person2" />
                                <img src={img_person_5} alt="person5" />
                            </div>
                            <div className={style.arrow_select}>
                                <img src={icon_arrow} alt="arrow" />
                            </div>
                            <div className={style.text_container}>
                                <img src={icon_balloon_message} className={style.icon_msg} alt="icon_msg" />
                                <p className={style.person_comment} style={{ fontSize: `${fontSize}px` }}>“Alimentação é algo muito rigoroso que tenho como um dos meus princípios,
                                    repassei isso para os
                                    meus filhos e agora eles estão repassando para os meus netos, sempre eduquei eles desde muito
                                    cedo a se alimentar bem, deve ser por isso que meus 2 filhos Beatriz e Lucas se tornaram
                                    nutricionistas, e estão nessa plataforma ajudando as pessoas a terem uma vida mais saudável.“
                                </p>
                                <hr />
                                <p className={style.person_name} style={{ fontSize: `${fontSize}px` }}>Olga S. Barros - 73 anos.</p>
                            </div>
                        </div>
                    </section>
                    <footer>
                        <div className={style.group_item}>
                            <img src={logo} alt="logo-footer" className={style.logo_footer} />
                            <div className={style.endereco} style={{ fontSize: `${fontSize}px` }}>
                                <p><b>Endereço</b></p>
                                <br />
                                <p>Rua Haddock Lobo, 595 - Cerqueira César, São Paulo - SP, 01414-001</p>
                            </div>
                            <div className={style.contato} style={{ fontSize: `${fontSize}px` }}>
                                <p><b>Contato</b></p>
                                <br />
                                <p>saluswell@contato.com</p>
                            </div>
                        </div>
                        <hr />
                        <div className={style.copyright} style={{ fontSize: `${fontSize}px` }}>
                            <p>© 2023. Todos os direiros reservados.</p>
                        </div>
                    </footer>
                </section>
            </body>
        </>
    )

    function navbar() {
        var scrollY = 0;
        var navbar = document.querySelector("#nav");
        navbar.style.transition = "all .5s linear";
        setInterval(() => {
            scrollY = window.pageYOffset;

            if (scrollY > 36) {
                navbar.style.top = "0%";
                navbar.style.marginTop = "0px";
            } else if (window.innerWidth < 610) {
                navbar.style.top = "1%";
                navbar.style.marginTop = "20px";
            } else {
                navbar.style.top = "6.5%";
                navbar.style.marginTop = "20px";
            }

        }, 200);
    }
}

export default Home;
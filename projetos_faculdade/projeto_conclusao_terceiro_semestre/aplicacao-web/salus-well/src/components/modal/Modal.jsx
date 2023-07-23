import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import style from './Modal.module.css';
//Img-icon Modal
import icon_email from '../../assets/icons/e-mail.png';
import icon_cadeado from '../../assets/icons/cadeado.png';
import icon_fechar from '../../assets/icons/invalid-icon.png';
import icon_senha from '../../assets/icons/senha.png';
import icon_esconder_senha from '../../assets/icons/esconder_senha.png';

import axios from "axios";
import urlBase from '../../Api';

export default function Modal() {
    const navigate = useNavigate();

    const [display, setDisplay] = useState(true);
    const toggleDisplay = () => {
        setDisplay(!display);
    }

    const [email, setEmail] = useState();
    const [senha, setSenha] = useState();
    const [perfil, setPerfil] = useState('');
    const [usuarioNaoEncontrado, setUsuarioNaoEncontrado] = useState('');
    const [esconderSenha, setEsconderSenha] = useState(false);

    function changeStatusPassword() {
        setEsconderSenha(!esconderSenha);
    }

    const handleCheckboxChange = (checkboxName) => {
        if (checkboxName === 'Paciente') {
            setPerfil('Paciente');
        } else if (checkboxName === 'Nutricionista') {
            setPerfil('Nutricionista');
        }
    };

    function submit() {
        const dataLogin = {
            "email": email,
            "senha": senha
        }

        if (perfil === 'Paciente') {
            axios.post(urlBase + 'clientes/login', dataLogin)
                .then(response => {
                    sessionStorage.setItem('idUser', response.data.userId)
                    sessionStorage.setItem('keyToken', response.data.token);
                    sessionStorage.setItem('keyPerfil', 'Paciente');
                    setUsuarioNaoEncontrado('');

                    const id = response.data.userId;
                    const token = response.data.token;

                    axios.get(urlBase + `clientes/${id}`, {
                        headers: {
                            'Authorization': `Bearer ${token}`
                        }
                    }).then(response => {
                        sessionStorage.setItem('dataUser', JSON.stringify(response.data))
                        sessionStorage.setItem('idAvatar', response.data.avatar)

                        if (response.data.ativo === false) {
                            setUsuarioNaoEncontrado('Esse usuário está desativado!')
                        } else {
                            axios.get(urlBase + 'comorbidades',{
                                headers: {
                                    'Authorization': `Bearer ${token}`
                                }
                            }).then(response => {
                                sessionStorage.setItem('comorbidadesUser', JSON.stringify(response.data))
                            })

                            navigate('/homePaciente');
                        }
                    })
                })
                .catch(error => {
                    setUsuarioNaoEncontrado('Ops! E-mail ou senha incorretos!')
                    console.error(error);
                });
        } else if (perfil === 'Nutricionista') {
            axios.post(urlBase + 'nutricionistas/login', dataLogin)
                .then(response => {
                    sessionStorage.setItem('idUser', response.data.userId);
                    sessionStorage.setItem('keyPerfil', 'Nutricionista');
                    setUsuarioNaoEncontrado('')

                    const id = response.data.userId;

                    axios.get(urlBase + `nutricionistas/${id}`)
                        .then(response => {
                            sessionStorage.setItem('dataUser', JSON.stringify(response.data))
                            sessionStorage.setItem('idAvatar', response.data.avatar)

                            if (response.data.ativo === false) {
                                setUsuarioNaoEncontrado('Esse usuário está desativado!')
                            } else {
                                navigate('/homeNutricionista');
                            }
                        })

                })
                .catch(error => {
                    setUsuarioNaoEncontrado('Ops! E-mail ou senha incorretos!')
                    console.error(error);
                });
        }

    }

    return (
        <>
            {display ? <section className={style.section_modal}>
                <div className={style.modal}>
                    <div className={style.btn_fechar_modal}>
                        <p className={style.title}>Bem vindo novamente</p>
                        <button>
                            <img src={icon_fechar} onClick={toggleDisplay} />
                        </button>
                    </div>
                    <div class="input_wrapper">
                        <p>E-mail</p>
                        <input
                            id="email"
                            type="text"
                            value={email}
                            onChange={event => setEmail(event.target.value)}
                        />
                        <img src={icon_email} alt="Ícone" />
                    </div>
                    <div class="input_wrapper">
                        <p>Senha</p>
                        <input
                            id="senha"
                            type={esconderSenha ? `text` : `password`}
                            value={senha}
                            onChange={event => setSenha(event.target.value)}
                        />
                        <img src={icon_cadeado} alt="Ícone" />
                        <img
                            className={style.esconder_senha}
                            src={esconderSenha ? (icon_senha) : (icon_esconder_senha)}
                            onClick={changeStatusPassword}
                        />
                    </div>
                    <div className={style.forgot_password}>
                        <span>{usuarioNaoEncontrado}</span>
                    </div>
                    <div className={style.group_input_wrapper_half}>
                        <div class="input_wrapper">
                            <p>Selecione o seu perfil:</p>
                            <div className={style.group_buttons_checked}>
                                <div className={style.item_check}>
                                    <input
                                        type="checkbox"
                                        id="myCheckbox"
                                        checked={perfil === 'Paciente'}
                                        onChange={() => handleCheckboxChange('Paciente')}
                                    />
                                    <label for="myCheckbox"></label>
                                    <p>Sou paciente</p>
                                </div>
                                <div className={style.item_check}>
                                    <input
                                        type="checkbox"
                                        id="myCheckbox2"
                                        checked={perfil === 'Nutricionista'}
                                        onChange={() => handleCheckboxChange('Nutricionista')}
                                    />
                                    <label for="myCheckbox2"></label>
                                    <p>Sou nutricionista</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className={style.group_button_modal}>
                        <p onClick={() => navigate('/cadastroPaciente')}>Criar sua conta</p>
                        <button className="button_red" onClick={submit}>Login</button>
                    </div>
                </div>
            </section> : null}
        </>
    );
}
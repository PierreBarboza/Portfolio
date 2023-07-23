import React, { useEffect, useState } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import Sweet from "../../components/modal/SweetAlert";
import urlBase from '../../Api';

// Folhas de Estilo
import styleCadastro from "../../components/cadastro/cadastro.module.css";

// Imagens & Icones necessários
import iconePaciente from '../../assets/icons/icon_paciente_form.png';
import iconeNutricionista from '../../assets/icons/icon_nutri_form.png';
import iconeNome from '../../assets/icons/pessoa.png';
import iconeTelefone from '../../assets/icons/telefone.png';
import iconeEmail from '../../assets/icons/email.png';
import iconeEndereco from '../../assets/icons/casa.png';
import iconeGenero from '../../assets/icons/genero.png';
import iconeCadeado from '../../assets/icons/cadeado.png';
import icon_senha from '../../assets/icons/senha.png';
import icon_esconder_senha from '../../assets/icons/esconder_senha.png';

// Componentes
import "../../components/cadastro/modalAvatar";
import BannerPaciente from '../../components/cadastro/BannerPaciente';
import ModalAvatar from "../../components/cadastro/modalAvatar";

export default function CadastroPaciente() {
    const navigate = useNavigate();

    const [avatar, setAvatar] = useState(0);
    const [nome, setNome] = useState('');
    const [telefone, setTelefone] = useState('');
    const [email, setEmail] = useState('');
    const [endereco, setEndereco] = useState('');
    const [genero, setGenero] = useState('');
    const [comorbidadeChecked, setComorbidadeChecked] = useState(false);
    const [senha, setSenha] = useState('');
    const [confirmarSenha, setConfirmarSenha] = useState('');
    const [error, setError] = useState('');
    const [isUpperCase, setIsUpperCase] = useState(false);
    const [isLowerCase, setIsLowerCase] = useState(false);
    const [hasSpecialChar, setHasSpecialChar] = useState(false);
    const [isLengthValid, setIsLengthValid] = useState(false);
    const [esconderSenha, setEsconderSenha] = useState(false);

    const atualizarAvatar = (idAvatar) => {
        setAvatar(idAvatar);
    }

    const comorbidadeChange = (check) => {
        setComorbidadeChecked(check);
    };

    useEffect(() => {
        checkPassword();
    }, [senha]);

    const checkPassword = () => {
        const regexCaps = /[A-Z]/;
        const regexNoCaps = /[a-z]/;
        const regexEspecial = /(?=.*?[#?!@$%^&*-])/gm;

        setIsUpperCase(regexCaps.test(senha));
        setIsLowerCase(regexNoCaps.test(senha));
        setHasSpecialChar(regexEspecial.test(senha));
        setIsLengthValid(senha.length >= 6);
    }

    const handleSenhaChange = (event) => {
        setSenha(event.target.value);
    }


    function changeStatusPassword() {
        setEsconderSenha(!esconderSenha);
    }

    function submit(event) {
        event.preventDefault();

        const dataPaciente = {
            "nome": nome,
            "email": email,
            "senha": senha,
            "avatar": avatar,
            "genero": genero,
            "endereco": endereco,
            "comorbidade": comorbidadeChecked,
            "telefone": telefone,
            "ativo": true
        }

        const msgError = document.getElementById('error');

        if (isUpperCase && isLowerCase && hasSpecialChar && isLengthValid) {
            if (senha !== confirmarSenha) {
                msgError.style.color = "red";
                setError('Ops! as senhas não coincidem');
            } else {
                setError('');
                axios.post(urlBase + 'clientes/cadastrar', dataPaciente)
                    .then(response => {

                        // Post das comorbidades
                        if (comorbidadeChecked){
                            axios.post(urlBase+'comorbidades', 
                                {
                                    "diabete": null,
                                    "colestorol": null,
                                    "hipertensao": null,
                                    "emailCliente": email
                                }
                            )
                            .then((res) => {console.log(res)})
                            .catch((error) => {console.error(error)})
                        } else {
                            axios.post(urlBase+'comorbidades', 
                                {
                                    "diabete": false,
                                    "colestorol": false,
                                    "hipertensao": false,
                                    "emailCliente": email
                                }
                            )
                            .then((res) => {console.log(res)})
                            .catch((error) => {console.error(error)})
                        }
                        console.log(response.data);
                        msgError.style.color = "green";
                        setError('');
                        Sweet('success', 'Cadastro realizado com sucesso!', 'Aguarde, você será redirecionado para a página inicial', 4500)
                        setTimeout(() => {
                            navigate('/');
                        }, 3500);

                    })
                    .catch(error => {
                        console.error(error);
                        msgError.style.color = "var(--red)";
                        setError(`Erro ao cadastrar, o usuario ${email} já existe!`);
                    });
            }

        } else {
            msgError.style.color = "red";
            setError('Ops! a senha não está seguindo as exigências!');
        }
    }

    return (
        <>
            <div className={styleCadastro.container}>
                <BannerPaciente />
                <div className={styleCadastro.content}>
                    <div className={styleCadastro.header}>
                        <p>Cadastro</p>
                        <p>Crie sua conta agora mesmo</p>
                    </div>
                    <div className={styleCadastro.body}>
                        <ModalAvatar setAtualizarAvatar={atualizarAvatar} />
                        <div className={styleCadastro.group_button_change_customer}>
                            <p>Qual o seu perfil?</p>
                            <div className={styleCadastro.group_button}>
                                <button
                                    className={styleCadastro.btn_paciente}
                                    onClick={() => navigate('/cadastroPaciente')}
                                    style={{ border: "3px solid #51734E", background: "rgba(193, 231, 173, 0.362)" }}
                                >
                                    <img src={iconePaciente} />
                                    <p>Sou Paciente</p>
                                </button>
                                <button
                                    className={styleCadastro.btn_nutricionista}
                                    onClick={() => navigate('/cadastroNutricionista')}
                                >
                                    <img src={iconeNutricionista} />
                                    <p>Sou Nutricionista</p>
                                </button>
                            </div>
                        </div>
                        <form className={styleCadastro.form_nutricionista} onSubmit={submit}>
                            <div className={styleCadastro.section_1}>
                                <div class="input_wrapper">
                                    <p>Nome</p>
                                    <input
                                        id="nome"
                                        type="text"
                                        value={nome}
                                        onChange={event => setNome(event.target.value)}
                                        required
                                    />
                                    <img src={iconeNome} alt="Ícone" />
                                </div>
                                <div class="input_wrapper">
                                    <p>Telefone</p>
                                    <input
                                        id="telefone"
                                        type="text"
                                        value={telefone}
                                        onChange={event => setTelefone(event.target.value)}
                                        maxLength={11}
                                        required
                                    />
                                    <img src={iconeTelefone} alt="Ícone" />
                                </div>
                                <div class="input_wrapper">
                                    <p>E-mail</p>
                                    <input
                                        id="email"
                                        type="text"
                                        value={email}
                                        onChange={event => setEmail(event.target.value)}
                                        required
                                    />
                                    <img src={iconeEmail} alt="Ícone" />
                                </div>
                                <div class="input_wrapper">
                                    <p>Endereço</p>
                                    <input
                                        id="endereco"
                                        type="text"
                                        value={endereco}
                                        onChange={event => setEndereco(event.target.value)}
                                        required
                                    />
                                    <img src={iconeEndereco} alt="Ícone" />
                                </div>

                            </div>
                            <div className={styleCadastro.section_2}>
                                <div className={styleCadastro.group_input_wrapper_half}>
                                    <div class="input_wrapper">
                                        <p>Gênero</p>
                                        <select
                                            id="genero"
                                            value={genero}
                                            onChange={event => setGenero(event.target.value)}
                                            required
                                        >
                                            <option value="" selected disabled>* Selecione *</option>
                                            <option value="Feminino">Feminino</option>
                                            <option value="Masculino">Masculino</option>
                                            <option value="Outros">Outros</option>
                                        </select>
                                        <img src={iconeGenero} alt="Ícone" />
                                    </div>
                                </div>

                                <div className={styleCadastro.group_input_wrapper_half}>
                                    <div class="input_wrapper">
                                        <p>Possuí alguma comorbidade? </p>
                                        <div className={styleCadastro.group_buttons_checked}>
                                            <div className={styleCadastro.item_check}>
                                                <input
                                                    type="checkbox"
                                                    id="myCheckbox"
                                                    checked={comorbidadeChecked}
                                                    onChange={() => comorbidadeChange(true)}
                                                />
                                                <label for="myCheckbox"></label>
                                                <p>Sim</p>
                                            </div>
                                            <div className={styleCadastro.item_check}>
                                                <input
                                                    type="checkbox"
                                                    id="myCheckbox2"
                                                    checked={!comorbidadeChecked}
                                                    onChange={() => comorbidadeChange(false)}
                                                />
                                                <label for="myCheckbox2"></label>
                                                <p>Não</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className={styleCadastro.section_5}>
                                <span>
                                    <div class="input_wrapper" style={{ marginBottom: "0" }}>
                                        <p>Senha</p>
                                        <input
                                            id="senha"
                                            type={esconderSenha ? `text` : `password`}
                                            value={senha}
                                            onChange={handleSenhaChange}
                                            required
                                        />
                                        <img src={iconeCadeado} alt="Ícone" />
                                        <img
                                            className={styleCadastro.esconder_senha}
                                            src={esconderSenha ? (icon_senha) : (icon_esconder_senha)}
                                            onClick={changeStatusPassword}
                                        />
                                    </div>
                                    <ul className={styleCadastro.validar_senha}>
                                        <li style={{ color: isUpperCase ? "#51734E" : "grey" }}>
                                            {isUpperCase ? <><b>Possui letra maiúscula</b></> : "Pelo menos uma letra maiúscula"}
                                        </li>
                                        <li style={{ color: isLowerCase ? "#51734E" : "grey" }}>
                                            {isLowerCase ? <><b>Possui letra minúscula</b></> : "Pelo menos uma letra minúscula"}
                                        </li>
                                        <li style={{ color: hasSpecialChar ? "#51734E" : "grey" }}>
                                            {hasSpecialChar ? <><b>Possui caracter especial</b></> : "Pelo menos um caractere especial (#?!@$%^&*-)"}
                                        </li>
                                        <li style={{ color: isLengthValid ? "#51734E" : "grey" }}>
                                            {isLengthValid ? <><b>Possui tamanho válido</b></> : "A senha deve conter no minímo 6 caracteres"}
                                        </li>
                                    </ul>
                                </span>
                                <div class="input_wrapper" style={{ marginBottom: "0" }}>
                                    <p>Confirme a senha</p>
                                    <input
                                        id="senha2"
                                        type={esconderSenha ? `text` : `password`}
                                        value={confirmarSenha}
                                        onChange={event => setConfirmarSenha(event.target.value)}
                                        required
                                    />
                                    <img src={iconeCadeado} alt="Ícone" />
                                </div>
                            </div>
                            <p id="error" className={styleCadastro.error}>{error}</p>
                            <div className={styleCadastro.group_button_form}>
                                <button type="submit" className="button_red">Cadastrar</button>
                                <button onClick={() => navigate('/')} className="button_white">Voltar</button>
                                <span id='alert'></span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </>
    )
}
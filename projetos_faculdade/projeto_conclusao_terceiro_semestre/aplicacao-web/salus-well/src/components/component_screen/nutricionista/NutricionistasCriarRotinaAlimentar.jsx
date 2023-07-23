import React, { useEffect, useState } from "react";
import style from './NutricionistasCriarRotinaAlimentar.module.css';
import Header from '../../header/Header';
import Sweet from "../../modal/SweetAlert";
import Card from '../../cards/CardAlimentoQtd';

import iconePaciente from '../../../assets/icons/pessoa.png';
import iconeTurno from '../../../assets/icons/sol.png';
import iconeCalendario from '../../../assets/icons/calendario.png';
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import urlBase from '../../../Api';

function OptionPaciente({ id, paciente }) {
    return (
        <option value={id}>{paciente}</option>
    );
}

export default function NutricionistasCriarRotinaAlimentar() {
    const navigate = useNavigate();

    const { state } = useLocation();
    const [erro, setErro] = useState('');
    const [listaAlimento, setListaAlimento] = useState(state);
    const [pacientesData, setpacientesData] = useState([]);
    useEffect(() => {
        axios
            .get(urlBase + `clientes/com-vinculo/por-nome/${sessionStorage.getItem('idUser')}`)
            .then(response => {
                setpacientesData(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, []);

    const [todosAlimentos, setTodosAlimentos] = useState('');
    useEffect(() => {
        const nomeAlimento = listaAlimento.map(alimento => alimento.nome);
        const alimentoString = nomeAlimento.join(', ');
        setTodosAlimentos(alimentoString)
        console.log(nomeAlimento);
    }, [listaAlimento]);

    console.log(todosAlimentos)

    const [paciente, setPaciente] = useState([]);
    const [turno, setTurno] = useState('');

    const [dateRefeicao, setDateRefeicao] = useState('');
    const formatarData = (data) => {
        const dataObj = new Date(data);
        const dia = String(dataObj.getDate() + 1).padStart(2, '0');
        const mes = String(dataObj.getMonth() + 1).padStart(2, '0'); // Mês começa a partir do 0
        const ano = dataObj.getFullYear();
        return `${dia}/${mes}/${ano}`;
    }

    function getCurrentDate() {
        const currentDate = new Date();
        const day = String(currentDate.getDate()).padStart(2, '0');
        const month = String(currentDate.getMonth() + 1).padStart(2, '0');
        const year = currentDate.getFullYear();
        return `${day}/${month}/${year}`;
    }
    const currentDate = getCurrentDate();

    const [comentario, setComentario] = useState('');

    const [totalCalorias, setTotalCalorias] = useState(
        useEffect(() => {
            const totalInicial = listaAlimento.reduce((total, alimento) => total + alimento.calorias, 0);
            setTotalCalorias(totalInicial);
        }, [listaAlimento])
    );

    const [totalCarboidratos, setTotalCarboidratos] = useState(
        useEffect(() => {
            const totalInicial = listaAlimento.reduce((total, alimento) => total + alimento.carboidrato, 0);
            setTotalCarboidratos(totalInicial);
        }, [listaAlimento])
    );

    const [totalProteina, setTotalProteina] = useState(
        useEffect(() => {
            const totalInicial = listaAlimento.reduce((total, alimento) => total + alimento.proteina, 0);
            setTotalProteina(totalInicial);
        }, [listaAlimento])
    );

    const [gordurasTotais, setGordurasTotais] = useState(
        useEffect(() => {
            const totalInicial = listaAlimento.reduce((total, alimento) => total + alimento.gorduraTotal, 0);
            setGordurasTotais(totalInicial);
        }, [listaAlimento])
    );

    const atualizarTotalCarboidratos = () => {
        const totalInicial = listaAlimento.reduce(
            (total, alimento) => total + alimento.carboidrato, 0);
        setTotalCarboidratos(totalInicial);
    }

    const removerItem = (id) => {
        const novaLista = listaAlimento.filter((alimento) => alimento.id !== id);
        setListaAlimento(novaLista);
    }

    function submit(event) {
        event.preventDefault();

        const refeicao = {
            "descricao": comentario,
            "qtdCalorias": totalCalorias,
            "periodo": turno,
            "alimentos": todosAlimentos,
            "idNutricionista": sessionStorage.getItem('idUser'),
            "idCliente": parseInt(paciente[0]),
            "dataConsumir": dateRefeicao
        }

        axios.post(urlBase + 'diarios', refeicao)
            .then(response => {
                setErro('');
                Sweet('success', 'Diário criado com sucesso!', '', 3500)
                setTimeout(() => {
                    navigate(-1);
                }, 3500);
                console.log(response.data);
            })
            .catch(error => {
                setErro('Ops! Houve algum erro ao tentar criar um diário.');
                console.error(error);
            });
    }

    return (
        <>
            <form className={style.main_user_home_screen} onSubmit={submit}>
                <Header titulo="Diário Alimentar" subtitulo="Preencha todos os campos para criar o diario alimentar" />
                <div className={style.conteudo1}>
                    <div className={style.group_cards}>
                        {listaAlimento.map((alimento) => (
                            <Card
                                key={alimento.id}
                                nomeAlimento={((alimento.nome).toLowerCase()).replace(/^[a-z]/, match => match.toUpperCase())}
                                categoriaAlimento={((alimento.tipo).toLowerCase()).replace(/^[a-z]/, match => match.toUpperCase())}
                                fotoAlimento={alimento.nome}
                                idAlimento={alimento.id}
                                remover={() => removerItem(alimento.id)}
                                atualizarTotalCarboidratos={atualizarTotalCarboidratos}
                            />
                        ))}
                    </div>
                    <div className={style.total_calorias}>
                        <p>Total</p>
                        <p>Carboidratos: <b>{totalCarboidratos > 0 ? (totalCarboidratos).toLocaleString('pt-BR') : 0}g</b></p>
                        <p>Proteínas: <b>{totalProteina > 0 ? (totalProteina).toLocaleString('pt-BR') : 0}g</b></p>
                        <p>Gorduras totais: <b>{gordurasTotais > 0 ? (gordurasTotais.toLocaleString('pt-BR')) : 0}g</b></p>
                        <p>Calorias: <b>{totalCalorias > 0 ? (totalCalorias).toLocaleString('pt-BR') : 0}</b></p>
                    </div>
                </div>
                <section className={style.section_conteudo}>
                    <div className={style.selection_and_calendar}>
                        <div className={style.group_inputs}>
                            <div className={style.group_input_wrapper_half}>
                                <div class="input_wrapper">
                                    <p>Paciente</p>
                                    <select
                                        id="pacientes"
                                        onChange={event => setPaciente([event.target.value, event.target.options[event.target.selectedIndex].text])}
                                        required
                                    >
                                        <option value="" selected disabled>* Selecione *</option>
                                        {pacientesData.length > 0 ? (
                                            pacientesData.map((paciente) => (
                                                <>
                                                    <OptionPaciente
                                                        key={paciente.id}
                                                        id={paciente.id}
                                                        paciente={paciente.nome} />
                                                </>
                                            ))
                                        ) : (
                                            <></>
                                        )}
                                    </select>
                                    <img src={iconePaciente} alt="Ícone" />
                                </div>
                            </div>
                            <div className={style.group_input_wrapper_half}>
                                <div class="input_wrapper">
                                    <p>Turno</p>
                                    <select
                                        id="turnoAlimento"
                                        value={turno}
                                        onChange={event => setTurno(event.target.value)}
                                        required
                                    >
                                        <option value="" selected disabled>* Selecione *</option>
                                        <option value="Manhã">Manhã</option>
                                        <option value="Tarde">Tarde</option>
                                        <option value="Noite">Noite</option>
                                    </select>
                                    <img src={iconeTurno} alt="Ícone" style={{ opacity: "90%" }} />
                                </div>
                            </div>
                        </div>
                        <div className={style.select_data}>
                            <p>Selecione a data para o seu paciente consumir essa refeição.</p>
                            <input
                                id="dateRefeica"
                                type="date"
                                value={dateRefeicao}
                                onChange={event => setDateRefeicao(event.target.value)}
                                required
                            />
                        </div>
                        {
                            formatarData(dateRefeicao) >= currentDate ? <></> :
                                <p style={{ color: "var(--red)", fontWeight: "bold" }}>Ops! selecione uma data posterior a de hoje.</p>
                        }
                    </div>
                    <div className={style.confirm_informations}>
                        <div className={style.information}>
                            <img src={iconePaciente} style={{ opacity: "50%" }} />
                            <p><b>Paciente: </b>{paciente[1] ? paciente[1] : ''}</p>
                        </div>
                        <div className={style.information}>
                            <img src={iconeTurno} style={{ opacity: "100%" }} />
                            <p><b>Turno: </b>{turno ? turno : ''}</p>
                        </div>
                        <div className={style.information}>
                            <img src={iconeCalendario} style={{ opacity: "50%" }} />
                            <p><b>Data: </b>{dateRefeicao ? formatarData(dateRefeicao) : 'dd/mm/yyyy'}</p>
                        </div>
                    </div>
                </section>
                <div className={style.chat_ballom}>
                    <p>Detalhes</p>
                    <textarea
                        id="comentario"
                        placeholder="Informe uma descrição/detalhe referente a esse diário."
                        value={comentario}
                        minLength={0}
                        maxLength={80}
                        onChange={event => setComentario(event.target.value)}
                    ></textarea>
                    <p style={{ color: "var(--red)" }}>{erro}</p>
                </div>
                <div className={style.group_buttons}>
                    <button type="submit" className="button_red">Criar rotina alimentar</button>
                    <button className="button_white" onClick={() => navigate(-1)}>Voltar</button>
                </div>
            </form>
        </>
    );
}
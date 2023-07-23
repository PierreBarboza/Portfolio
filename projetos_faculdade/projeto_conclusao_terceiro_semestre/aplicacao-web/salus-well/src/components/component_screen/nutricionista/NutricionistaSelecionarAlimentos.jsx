import React, { useState } from "react";
import Header from '../../header/Header';
import style from './NutricionistaSelecionarAlimento.module.css';
import Card from '../../cards/CardAlimento';
import { useNavigate } from "react-router-dom";

import lupaIcon from "../../../assets/icons/lupa.png";

export default function NutricionistaSelecionarAlimentos() {
    const navigate = useNavigate();

    const [count, setCount] = useState(0);
    const [data, setData] = useState([]);
    const [nomeAlimentoPesquisado, setNomeAlimentoPesquisado] = useState('');
    const [clickLupa, setClickLupa] = useState('');
    const [click, setClick] = useState(false);
    const [comorbidadeFiltro, setComorbidadeFiltro] = useState('');

    function buscaAlimento() {
        setClickLupa(nomeAlimentoPesquisado)
    }

    function resetarComorbidade() {
        setComorbidadeFiltro("")
        setNomeAlimentoPesquisado("")
        setClickLupa("");
        setCount(0)
        setData([])
    }

    function receberAlimentoNutricionista(alimento, clicado) {
        const exists = data.some((item) => item === alimento);

        if (!exists && clicado) {
            setData((prevData) => [...prevData, alimento]);
            setCount(count + 1);
        } else if (exists && !clicado) {
            setData((prevData) => prevData.filter((item) => item !== alimento));
            setCount(count - 1);
        }
    }

    return (
        <>
            <div className={style.main_user_home_screen}>
                <Header titulo={"Alimentos"} subtitulo="Crie rotinas alimentares de acordo com a necessidade do seu paciente" />
                <div className={style.group_search}>
                    <span className={style.inputSearch}>
                        <span>
                            <input
                                placeholder='Busque pelo nome'
                                type="text"
                                value={nomeAlimentoPesquisado}
                                onChange={event => setNomeAlimentoPesquisado(event.target.value)}
                                style={{ border: 'none' }} />
                        </span>
                        <span>
                            <img src={lupaIcon}
                                onClick={buscaAlimento}
                                style={{ width: '20px' }} />
                        </span>
                    </span>
                    <div className={style.group_select}>
                        {comorbidadeFiltro || nomeAlimentoPesquisado ? (
                            <>
                                <button className="button_white" onClick={resetarComorbidade}>resetar filtros</button>
                            </>
                        ) : null}

                        <select
                            id="select_filtro"
                            value={comorbidadeFiltro}
                            onChange={event => setComorbidadeFiltro(event.target.value)}
                        >
                            <option value="" selected disabled>Filtro</option>
                            <option value="hipertensao">Hipertensão</option>
                            <option value="diabete">Diabetes</option>
                            <option value="colesterol">Colesterol</option>
                        </select>
                    </div>

                </div>
                <div className={style.group_itens}>
                    <div className={style.group_buttons}>
                        {count <= 0 ? (<>
                            <h3 className={style.selecionar_alimento_ternario}>Selecione pelo menos 1 alimentos para criar a rotina alimentar <span>*</span></h3>
                        </>) : (
                            <button className="button_red" onClick={count <= 0 ? null : () => navigate('/criarRotina', { state: data })}>Criar rotina alimentar</button>
                        )}
                    </div>
                    <p>Alimentos selecionados: <b>{count}</b></p>
                </div>
                <div className={style.group_card}>
                    <Card
                        funcaoAlimento={receberAlimentoNutricionista}
                        tipoComorbidade={comorbidadeFiltro}
                        nomeAlimento={clickLupa}
                        click={click}
                        filtroBuscaAlimento={nomeAlimentoPesquisado}
                    />
                </div>
            </div>
        </>
    );
}
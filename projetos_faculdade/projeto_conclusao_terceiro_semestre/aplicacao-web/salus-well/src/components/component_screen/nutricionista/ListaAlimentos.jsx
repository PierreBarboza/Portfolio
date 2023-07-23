import React, { useState } from "react";
import Header from '../../header/Header';
import style from './NutricionistaSelecionarAlimento.module.css';
import Card from '../../cards/CardAlimento';
import { useNavigate } from "react-router-dom";

import lupaIcon from "../../../assets/icons/lupa.png";

export default function ListaAlimentos() {
    const navigate = useNavigate();

    const [nomeAlimentoPesquisado, setNomeAlimentoPesquisado] = useState('');
    const [clickLupa, setClickLupa] = useState('');
    const [click, setClick] = useState(false);
    const [comorbidadeFiltro, setComorbidadeFiltro] = useState('');

    function buscaAlimento() {
        setComorbidadeFiltro('');
        setClickLupa(nomeAlimentoPesquisado)
    }

    function desabilitarClick() {
        //ATENÇÃO: NÃO REMOVER ESSA FUNÇÃO 
        //POIS ELA RETORNA UMA RESPOSTA NULA 
        //ESSENCIAL PARA O FUNCIONAMENTO DESSA PAGINA!
    }

    function resetarComorbidade() {
        setComorbidadeFiltro("")
        setNomeAlimentoPesquisado("")
        setClickLupa("");
    }

    return (
        <>
            <div className={style.main_user_home_screen}>
                <Header titulo={"Alimentos"} subtitulo="Consulte as propriedades de cada alimento" />
                <div className={style.group_search}>
                    <span className={style.inputSearch}>
                        <span>
                            <input
                                placeholder='Busque pelo nome'
                                type="text"
                                value={nomeAlimentoPesquisado}
                                onChange={event => setNomeAlimentoPesquisado(event.target.value)}
                                style={{ border: 'none' }}
                            />
                        </span>
                        <span>
                            <img src={lupaIcon}
                                onClick={buscaAlimento}
                                style={{ width: '20px' }}
                            />
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
                        <button className="button_red" onClick={() => navigate('/cadastrarAlimento')}>Cadastrar novo alimento</button>
                    </div>
                </div>
                <div className={style.group_card}>
                    <Card
                        funcaoAlimento={desabilitarClick}
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
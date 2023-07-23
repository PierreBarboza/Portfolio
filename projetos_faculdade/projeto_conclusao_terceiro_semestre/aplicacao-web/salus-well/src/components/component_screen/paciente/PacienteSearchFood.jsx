import React, { useState } from "react";
import style from '../paciente/PacienteSearchFood.module.css';
import Header from "../../header/Header";
import CardAlimento from '../../cards/CardAlimento';
import lupaIcon from "../../../assets/icons/lupa.png";

export default function UserSearchFood() {

    const [nomeAlimentoPesquisado, setNomeAlimentoPesquisado] = useState('');
    const [clickLupa, setClickLupa] = useState('');
    const [click, setClick] = useState(false);
    const [comorbidadeFiltro, setComorbidadeFiltro] = useState('');

    function buscaAlimento() {
        setComorbidadeFiltro('');
        setClickLupa(nomeAlimentoPesquisado)
    }

    function resetarComorbidade() {
        setComorbidadeFiltro("")
        setNomeAlimentoPesquisado("")
        setClickLupa("");
    }

    return (
        <>
            <div className={style.main_user_search_food}>
                <Header titulo="Alimentos" subtitulo="Encontre comidas perfeitas para você" />
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
                <div className={style.group_card}>
                    <CardAlimento
                        tipoComorbidade={comorbidadeFiltro}
                        nomeAlimento={clickLupa}
                        click={click}
                        filtroBuscaAlimento={nomeAlimentoPesquisado}
                    />
                </div>
            </div>
        </>
    )
}
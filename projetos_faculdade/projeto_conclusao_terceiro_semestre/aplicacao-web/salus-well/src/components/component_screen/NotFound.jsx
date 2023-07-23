import React from "react";
import style from './NotFound.module.css';

import imgMacaNotFound from '../../assets/icons/maca_not_found.jpg';
import { useNavigate } from "react-router-dom";

export default function NotFound() {
    const navigate = useNavigate();

    return (
        <>
            <section className={style.main}>
                <div className={style.container}>
                    <p className={style.first_title}>OPS!</p>
                    <div className={style.group_404}>
                        <p>4</p>
                        <img src={imgMacaNotFound} />
                        <p>4</p>
                    </div>
                    <p className={style.last_title}>Está página que você esta tentando acessar não existe</p>
                    <button className="button_red" onClick={() => navigate(-1)}>Voltar</button>
                </div>
            </section>
        </>
    );
}
import React from "react";
import style from "../cadastro/cadastro.module.css";
import imgBannerPaciente from "../../assets/img/paciente-bg-cadastro 2.svg";

export default function BannerPaciente() {
    return (
        <>
            <div className={style.banner}>
                <div className={style.banner_paciente}>
                    <img className={style.imgBannerPaciente} src={imgBannerPaciente} alt="Imagem Paciente" />
                </div>
            </div>
        </>
    )
}
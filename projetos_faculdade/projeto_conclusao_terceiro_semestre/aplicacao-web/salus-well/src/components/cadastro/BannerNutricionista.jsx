import React from "react";
import style from "../cadastro/cadastro.module.css";
import imgBannerNutricionista from "../../assets/img/nutricionista-bg-cadastro 1.svg";

export default function BannerNutricionista() {
    return (
        <>
            <div className={style.banner}>
                <div className={style.banner_nutricionista}>
                    <img className={style.imgBannerNutricionista} src={imgBannerNutricionista} alt="Imagem Nutricionista" />
                </div>
            </div>
        </>
    )
}
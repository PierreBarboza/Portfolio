import React from "react";
import UserSideBar from "../../components/side_bar/NutricionistaSideBar";
import HomeScreen from '../../components/component_screen/nutricionista/NutricionistaSelecionarAlimentos';

export default function ListarAlimentos() {
    return (
        <>
            <body className="body_home">
                <UserSideBar />
                <HomeScreen />
            </body>
        </>
    );
}
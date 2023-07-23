import React from "react";
import SideBar from '../../components/side_bar/NutricionistaSideBar';
import HomeScreen from '../../components/component_screen/nutricionista/NutricionistaCadastrarAlimento';

export default function CadastrarAlimento() {
    return (
        <>
            <body className="body_home">
                <SideBar />
                <HomeScreen />
            </body>
        </>
    );
}
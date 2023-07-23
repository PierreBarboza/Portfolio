import React from "react";
import SideBar from '../../components/side_bar/NutricionistaSideBar';
import HomeScreen from '../../components/component_screen/nutricionista/NutricionistasCriarRotinaAlimentar';

export default function CriarRotinaAlimentar() {
    return (
        <>
            <div className="body_home">
                <SideBar />
                <HomeScreen />
            </div>
        </>
    );
}
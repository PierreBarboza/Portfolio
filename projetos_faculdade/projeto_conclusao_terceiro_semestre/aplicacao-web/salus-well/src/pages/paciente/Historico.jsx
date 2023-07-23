import React from 'react'
import { useLocation } from 'react-router'
import PacienteSideBar from "../../components/side_bar/PacienteSideBar"
import HistoricoScreen from '../../components/component_screen/paciente/HistoricoScreen'

export default function Historico() {
  const locationURL = useLocation();
  console.log(locationURL);

  return (
    <>
      <body className='body_home'>
        <PacienteSideBar />
        <HistoricoScreen />
      </body>
    </>
  )
}

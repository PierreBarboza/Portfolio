import React from 'react'
import SideBar from '../../components/side_bar/PacienteSideBar'
import HomeScreen from '../../components/component_screen/paciente/PacienteHomeScreen'
import ModalComorbidades from '../../components/modal/ModalComorbidades'

export default function HomeLogin() {
  return (
    <>
      <body className='body_home'>
        <SideBar />
        <HomeScreen />
        <ModalComorbidades />
      </body>
    </>
  )
}

import React from 'react'
import SideBar from '../../components/side_bar/PacienteSideBar'
import ScreenPage from '../../components/component_screen/paciente/VisaoPerfilNutricionistaScreen';

export default function visaoPerfilNutricionista() {
  return (
    <>
      <body className='body_home'>
        <SideBar />
        <ScreenPage />
      </body>
    </>
  )
}

import React from 'react'
import SideBar from '../../components/side_bar/PacienteSideBar'
import ScreenPage from '../../components/component_screen/paciente/ListaNutricionistaScreen';
import ModalComorbidades from '../../components/modal/ModalComorbidades'
import axios from 'axios';
import urlBase from '../../Api'

export default function listaNutricionistas() {

  const dataUser = JSON.parse(sessionStorage.getItem('dataUser'));
  const email = dataUser.email;

  axios.get(urlBase + `comorbidades/busca-por-email?email=${email}`, {
    headers: {
      'Authorization': `Bearer ${sessionStorage.getItem('keyToken')}`
    }
  }).then(response => {
    if (response.data.diabete === null) {

    }
  })

  return (
    <>
      <body className='body_home'>
        <SideBar />
        <ScreenPage />
        <ModalComorbidades />
      </body>
    </>
  )
}

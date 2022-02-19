package Controlador

import Vista.vista_Principal

import java.awt.event.{ActionEvent, ActionListener}

class ctrPrincipal extends ActionListener{
  val vista=new vista_Principal()
  vista.setVisible(true)
  vista.jtbSalir.addActionListener(this)
  vista.jtbPaises.addActionListener(this)
  vista.jtbPremios.addActionListener(this)
  vista.jtbPartidos.addActionListener(this)
  vista.jtbGanadores.addActionListener(this)
  vista.jtbParticipantes.addActionListener(this)

  override def actionPerformed(e: ActionEvent): Unit = {
    if(e.getSource==vista.jtbPremios){
      new ctrPremios()
      vista.dispose()
    }
    else if(e.getSource==vista.jtbSalir){
      vista.dispose()
    }
    else if(e.getSource==vista.jtbPaises){
      new ctrPaises()
      vista.dispose()
    }
    else if(e.getSource==vista.jtbPartidos){
      new ctrPartidos()
      vista.dispose()
    }
    else if(e.getSource==vista.jtbGanadores){
      new ctrGanadores()
      vista.dispose()
    }
    else if(e.getSource==vista.jtbParticipantes){
      new ctrParticipantes()
      vista.dispose()
    }

  }
}

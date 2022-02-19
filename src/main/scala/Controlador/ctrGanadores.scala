package Controlador

import Modelo.{Administrador, Participante}
import Vista.vista_Ganadores

import java.awt.event.{ActionEvent, ActionListener, WindowAdapter, WindowEvent}
import javax.swing.JOptionPane
import javax.swing.table.DefaultTableModel
import scala.collection.mutable.ListBuffer
import scala.math.Ordering.Implicits.seqOrdering

class ctrGanadores extends ActionListener{
  val admin=new Administrador
  val vista=new vista_Ganadores()
  var ganadores_definidos=false
  vista.addWindowListener(new WindowAdapter() {
    override def windowClosing(e:WindowEvent):Unit={
      vista.dispose()
      new ctrPrincipal()
    }
  })
  vista.setVisible(true)
  vista.btnDefinirGanador.addActionListener(this)
  clasificar_participantes()

  def clasificar_participantes(): Unit ={
    var participantes=admin.obtener_lista_participantes()
    if(participantes.size==0) return
    participantes=participantes.sortBy(x=>{x.puntos})
    participantes=participantes.reverse
    mostrar_tabla(participantes.toList,ganadores_definidos)
  }

  def definir_ganador(): Unit ={
    admin.obtener_premio()
    var participantes=admin.obtener_lista_participantes()
    if(participantes.size==0){
      JOptionPane.showMessageDialog(null,"Todavía no se han ingresado participantes","¡Aviso Importante!",JOptionPane.INFORMATION_MESSAGE)
      return
    }
    participantes=participantes.sortBy(x=>{x.puntos})
    participantes=participantes.reverse
    val primer_ganador=participantes(0)
    val ganadores=participantes.filter(x=>{x.puntos==primer_ganador.puntos})
    import Administrador.premio
    premio(2)=for(g<-ganadores) yield g.cedula
    admin.actualizar_premio()
    ganadores_definidos=true
    mostrar_tabla(participantes.toList,true)
    JOptionPane.showMessageDialog(null,"Se han asignado los premios a los respectivos ganadores.","Ganadores asignados con éxito!",JOptionPane.INFORMATION_MESSAGE)

  }
  def mostrar_tabla(lista:List[Participante],ganadores:Boolean): Unit ={
    admin.obtener_premio()
    import Administrador.premio
    val lsGanadores=premio(2).asInstanceOf[ListBuffer[String]].toList
    val modelo=vista.jgdParticipantes.getModel.asInstanceOf[DefaultTableModel]
    modelo.setRowCount(0)
    for(i<-0 until lista.size){
      var ls:ListBuffer[Any]=ListBuffer(lista(i).cedula,lista(i).nombre,lista(i).apellido,lista(i).puntos(0))
      if(ganadores){
        if(lsGanadores.contains(lista(i).cedula)){
          ls+="$ "+(premio(1).asInstanceOf[Double]/lsGanadores.size.toDouble)
        }
        else{
          ls+=""
        }
      }
      else ls+=""
      ls.appendAll(for(j<-1 until 9) yield lista(i).puntos(j))
      modelo.insertRow(i,ls.toArray.asInstanceOf[Array[AnyRef]])
    }


  }

  override def actionPerformed(e: ActionEvent): Unit = {
    definir_ganador()

  }
}

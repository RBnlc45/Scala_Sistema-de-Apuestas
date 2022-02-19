package Controlador

import Modelo.Administrador
import Vista.vista_Premios

import java.awt.event.{ActionEvent, ActionListener, WindowAdapter, WindowEvent}
import javax.swing.JOptionPane

class ctrPremios extends ActionListener{
  val admin=new Administrador
  val vista=new vista_Premios()
  vista.addWindowListener(new WindowAdapter() {
    override def windowClosing(e:WindowEvent):Unit={
      vista.dispose()
      new ctrPrincipal()
    }
  })
  vista.btnRegistrar.addActionListener(this)
  vista.btnCambiarPrecio.addActionListener(this)
  vista.btnQuitarMonto.addActionListener(this)
  vista.setVisible(true)
  actualizar_contenedores()
  def actualizar_contenedores(): Unit ={
    var premio=admin.obtener_premio()
    vista.lblPrecioActualColocar.setText(premio(0).asInstanceOf[Double].toString)
    vista.lblMontoDisponible.setText(premio(1).asInstanceOf[Double].toString)
  }
  def validar_monto(valor:String): Boolean ={
    try{
      if(valor.size==0) throw new Exception()
      var value=valor.toDouble
      if(value<=0) throw new Exception()
      return true
    }
    catch {
      case e:Exception=>
        JOptionPane.showMessageDialog(null,"Debe ingresar un valor numérico mayor a 0", "¡Error en el valor ingresado!",JOptionPane.ERROR_MESSAGE)
        return false
    }
  }
  def ingresar_monto(): Unit ={
    var value=vista.txtMontoAgregar.getText
    if(validar_monto(value)==false) return
    var num=value.toDouble
    admin.obtener_premio()
    admin.premio_agregar_monto(num)
    JOptionPane.showMessageDialog(null,"El monto ingresado se ha registrado éxitosamente","¡El monto se ha actualizado éxitosamente!",JOptionPane.INFORMATION_MESSAGE)
    actualizar_contenedores()
    vista.txtMontoAgregar.setText("")
  }
  def eliminar_monto(): Unit ={
    var value=vista.txtMontoQuitar.getText
    if(validar_monto(value)==false) return
    val v=value.toDouble
    admin.obtener_premio()
    import Administrador.premio
    if(v>premio(1).asInstanceOf[Double]){
      JOptionPane.showMessageDialog(null,"El valor debe ser menor o igual al monto actual.","¡Error en el valor ingresado!",JOptionPane.ERROR_MESSAGE)
      return
    }
    admin.premio_agregar_monto(-v)
    JOptionPane.showMessageDialog(null,"El monto disponible se ha actualizado","¡El monto se ha modificado éxitosamente!",JOptionPane.INFORMATION_MESSAGE)
    actualizar_contenedores()
    vista.txtMontoQuitar.setText("")
  }
  def cambiar_precio(): Unit ={
    var value=vista.txtNuevoPrecio.getText
    if(validar_monto(value)==false) return
    var v=value.toDouble
    admin.obtener_premio()
    admin.premio_cambiar_precio(v)
    JOptionPane.showMessageDialog(null,"El precio de la participación en el torneo ha sido modificado","¡El precio por participación se ha modificado éxitosamente!",JOptionPane.INFORMATION_MESSAGE)
    actualizar_contenedores()
    vista.txtNuevoPrecio.setText("")
  }
  override def actionPerformed(e: ActionEvent): Unit = {
      if(e.getSource==vista.btnRegistrar){
        ingresar_monto()
      }
      else if(e.getSource==vista.btnCambiarPrecio){
        cambiar_precio()
      }
      else if(e.getSource==vista.btnQuitarMonto){
        eliminar_monto()
      }
  }
}

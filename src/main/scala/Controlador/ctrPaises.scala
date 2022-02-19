package Controlador

import Modelo.{Administrador, Grupo, Seleccion}
import Vista.vista_Selecciones

import java.awt.event.{ActionEvent, ActionListener, WindowAdapter, WindowEvent}
import javax.swing.{DefaultComboBoxModel, DefaultListModel, JList, JOptionPane}
import scala.collection.mutable.ListBuffer

class ctrPaises extends ActionListener{
  val admin = new Administrador()
  val vista = new vista_Selecciones()
  vista.addWindowListener(new WindowAdapter() {
    override def windowClosing(e:WindowEvent):Unit={
      vista.dispose()
      new ctrPrincipal()
    }
  })
  var seleccion: Seleccion = null
  //Action listener
  vista.btnEditar.setEnabled(false)
  vista.cmbGruposEditar.setEnabled(false)
  vista.setVisible(true)
  vista.cmbGrupos_2.setModel(new DefaultComboBoxModel[String](Array[String]("A","B","C","D","E","F","G","H")))
  vista.cmbGruposEditar.setModel(new DefaultComboBoxModel[String](Array[String]("A","B","C","D","E","F","G","H")))
  vista.btnAgregar.addActionListener(this)
  vista.cmbPaises.addActionListener(this)
  vista.btnEditar.addActionListener(this)
  vista.btnEliminar.addActionListener(this)
  cargarValoresPaises()
  cargarValoresGrupos()
  def agregarSeleccion(): Unit = {
    var listaSelecciones = admin.obtener_lista_selecciones()
    var listaGrupos = admin.obtener_lista_grupos()
    var seleccion = new Seleccion(vista.txtNombrePais.getText())
    var grupo = new Grupo(vista.cmbGrupos_2.getSelectedItem.toString)
    if (!listaSelecciones.contains(seleccion) && listaSelecciones.size < 32 && seleccion.pais.replace(" ", "") != "") {
      if (admin.obtener_grupo_pais(seleccion.pais) == null) {
        agregar_seleccion_grupo(seleccion,grupo,listaGrupos,listaSelecciones)
      }
      else {
        JOptionPane.showMessageDialog(null, "La selección ingresada ya está registrada en un grupo diferente", "Error!", JOptionPane.ERROR_MESSAGE)
      }
    }
    else if (listaSelecciones.size == 32) {
      JOptionPane.showMessageDialog(null, "Se ha completado el cupo de 32 selecciones para este evento", "Error!", JOptionPane.ERROR_MESSAGE)
    }
    else if (seleccion.pais.replace(" ", "") == "") {
      JOptionPane.showMessageDialog(null, "Ingrese un nombre válido", "Error!", JOptionPane.ERROR_MESSAGE)
    }
    else JOptionPane.showMessageDialog(null, "La selección ingresada ya está registrada", "Error!", JOptionPane.ERROR_MESSAGE)
  }
  def agregar_seleccion_grupo(seleccion: Seleccion, grupo: Grupo, grupos: ListBuffer[Grupo], selecciones: ListBuffer[Seleccion]): Unit = {
    if (grupos.contains(grupo) == false) {
      grupo.selecciones += (seleccion.pais)
      grupos += grupo
      selecciones += seleccion
      admin.actualizar_lista_selecciones(null)
      admin.actualizar_lista_grupos(null)
      JOptionPane.showMessageDialog(null, "El país se creó y asignó correctamente al grupo seleccionado", "¡Asignación Éxitosa!", JOptionPane.INFORMATION_MESSAGE)
      vista.txtNombrePais.setText("")
      cargarValoresGrupos()
      cargarValoresPaises()
    }
    else {
      var grupo2 = admin.obtener_grupo_codigo(grupo.codigo_grupo)
      if (grupo2.selecciones.size < 4) {
        grupo2.selecciones += seleccion.pais
        admin.actualizar_lista_grupos(null)
        selecciones += seleccion
        admin.actualizar_lista_selecciones(null)
        JOptionPane.showMessageDialog(null, "El país se creó y asignó correctamente al grupo seleccionado", "¡Asignación Éxitosa!", JOptionPane.INFORMATION_MESSAGE)
        vista.txtNombrePais.setText("")
        cargarValoresGrupos()
        cargarValoresPaises()
      }
      else {
        JOptionPane.showMessageDialog(null, "El grupo seleccionado contiene los 4 equipos reglamentarios", "¡Error!", JOptionPane.INFORMATION_MESSAGE)
      }
    }
  }
  def cargarValoresGrupos(): Unit = {
    val grupos = admin.obtener_lista_grupos()
    val grupos_listas = Map("A" -> vista.lstGrupoA, "B" -> vista.lstGrupoB, "C" -> vista.lstGrupoC, "D" -> vista.lstGrupoD, "E" -> vista.lstGrupoE, "F" -> vista.lstGrupoF,
      "G" -> vista.lstGrupoG, "H" -> vista.lstGrupoH)
    for (g <- grupos) {
      val lst: JList[String] = grupos_listas.get(g.codigo_grupo).get
      lst.removeAll()
      var selecciones = for (p <- g.selecciones) yield p
      selecciones = selecciones.sorted
      var modelo:DefaultListModel[String]=new DefaultListModel()
      for(s<-selecciones) modelo.addElement(s)
      lst.setModel(modelo)
    }
  }
  def cargarValoresPaises(): Unit = {
    val selecciones = admin.obtener_lista_selecciones()
    var selecciones_nombres = for (p <- selecciones) yield p.pais
    selecciones_nombres = selecciones_nombres.sorted
    vista.lstPaises.removeAll()
    vista.cmbPaises.removeAll()
    vista.cmbPaisesEliminar.removeAll()
    var aux = for (p <- selecciones) yield p.pais
    var modelo:DefaultListModel[String]=new DefaultListModel()
    for (p<-aux) modelo.addElement(p)
    vista.lstPaises.setModel(modelo)
    vista.lblCantidadPaisesValor.setText(selecciones.size.toString)
    vista.cmbPaises.setModel(new DefaultComboBoxModel[String](selecciones_nombres.toArray))
    vista.cmbPaisesEliminar.setModel(new DefaultComboBoxModel[String](selecciones_nombres.toArray))
  }
  def cargar_valores_seleccion(): Unit = {
    admin.obtener_lista_selecciones()
    this.seleccion = admin.obtener_seleccion(vista.cmbPaises.getSelectedItem.toString)
    if (this.seleccion == null) {
      JOptionPane.showMessageDialog(null, "El país seleccionado no se encuentra en la lista", "¡Error!", JOptionPane.ERROR_MESSAGE)
      return
    }
    val grupo_seleccion = admin.obtener_grupo_pais(this.seleccion.pais)
    if (grupo_seleccion == null) {
      vista.cmbGruposEditar.setSelectedIndex(-1)
      vista.btnEditar.setEnabled(false)
      vista.cmbGruposEditar.setEnabled(false)
    }
    else {
      vista.cmbGruposEditar.setSelectedIndex(grupo_seleccion.codigo_grupo(0).toInt - 65)
      vista.btnEditar.setEnabled(true)
      vista.cmbGruposEditar.setEnabled(true)
    }
  }

  def editar_seleccion(): Unit ={
    def limpiar_paneles() {
      vista.cmbGruposEditar.setSelectedIndex(-1)
      vista.cmbGruposEditar.setEnabled(false)
      vista.btnEditar.setEnabled(false)
    }
    def actualizacion_exitosa() {
      JOptionPane.showMessageDialog(null, "Se ha cambiado el grupo de la selección escogida", "Actualización éxitosa!", JOptionPane.INFORMATION_MESSAGE)
      cargarValoresGrupos()
      cargarValoresPaises()
      limpiar_paneles()
    }
    def grupo_no_creado(grupo_seleccionado:String,nombre_ingresado:String,grupo_actual:Grupo):Unit= {
      val grupo = new Grupo(grupo_seleccionado)
      grupo.selecciones.append(nombre_ingresado)
      if (grupo_actual!=null)  grupo_actual.selecciones-=nombre_ingresado
      admin.agregar_grupo(grupo)
    }
    def grupo_creado(grupo:Grupo,nombre_ingresado:String,grupo_actual:Grupo):Unit= {
      grupo.selecciones+=nombre_ingresado
      if (grupo_actual!=null)  grupo_actual.selecciones-=nombre_ingresado
      admin.actualizar_lista_grupos(null)
    }
    var grupo_seleccionado=vista.cmbGruposEditar.getSelectedItem.toString
    var grupo_actual=admin.obtener_grupo_pais(this.seleccion.pais)
    var nombre_ingresado=this.seleccion.pais
    if(grupo_seleccionado!=""&&grupo_actual!=null&&grupo_seleccionado!=grupo_actual.codigo_grupo){
      if(admin.obtener_lista_partidos_grupos().values.size!=0){
        JOptionPane.showMessageDialog(null, "Los partidos de fase de grupos ya han sido generados, no se puede realizar un cambio de grupo", "¡Error!", JOptionPane.ERROR_MESSAGE)
        limpiar_paneles()
        return
      }
      var grupo=admin.obtener_grupo_codigo(grupo_seleccionado)
      if(grupo!=null&&grupo.selecciones.size==4){
        JOptionPane.showMessageDialog(null, "El grupo seleccionado está lleno", "¡Error!", JOptionPane.ERROR_MESSAGE)
        limpiar_paneles()
      }
      else if(grupo==null){
        grupo_no_creado(grupo_seleccionado,nombre_ingresado,grupo_actual)
        actualizacion_exitosa()
      }
      else{
        grupo_creado(grupo,nombre_ingresado,grupo_actual)
        actualizacion_exitosa()
      }
    }
    else if (grupo_actual ==null) {
      var grupo = admin.obtener_grupo_codigo(grupo_seleccionado)
      if (grupo!=null && grupo.selecciones.size == 4) {
        JOptionPane.showMessageDialog(null, "El grupo seleccionado está lleno", "¡Error!", JOptionPane.ERROR_MESSAGE)
        limpiar_paneles()
        return
      }
      else if (grupo==null) {
        grupo_no_creado(grupo_seleccionado,nombre_ingresado,grupo_actual)
        actualizacion_exitosa()
        return
      }
      grupo_creado(grupo,nombre_ingresado,grupo_actual)
      actualizacion_exitosa()
      return
    }

  }
  def eliminar_seleccion() {
    if(vista.cmbPaisesEliminar.getItemCount==0){
      return
    }
    var selecciones = admin.obtener_lista_selecciones()
    this.seleccion = admin.obtener_seleccion(vista.cmbPaisesEliminar.getSelectedItem.toString)
    if (this.seleccion==null) {
      JOptionPane.showMessageDialog(null, "El país seleccionado no se encuentra en la lista", "¡Error!", JOptionPane.ERROR_MESSAGE)
      return
    }
    if (admin.obtener_partidos_grupo_pais(this.seleccion.pais).size > 0) {
      JOptionPane.showMessageDialog(null,  "La selección no puede ser eliminada debido a que ya tiene partidos asignados", "¡Error!", JOptionPane.ERROR_MESSAGE)
      return
    }
    var grupo_seleccion = admin.obtener_grupo_pais(this.seleccion.pais)
    grupo_seleccion.selecciones-=this.seleccion.pais
    selecciones-=this.seleccion
    admin.actualizar_lista_grupos(null)
    admin.actualizar_lista_selecciones(null)
    cargarValoresPaises()
    cargarValoresGrupos()
  }
  override def actionPerformed(e: ActionEvent): Unit = {
    if(e.getSource==vista.btnAgregar){
      agregarSeleccion()
    }
    else if(e.getSource==vista.cmbPaises){
      cargar_valores_seleccion()
    }
    else if(e.getSource==vista.btnEditar){
      editar_seleccion()
    }
    else if(e.getSource==vista.btnEliminar){
      eliminar_seleccion()
    }


  }
}
package Controlador

import Modelo.{Administrador, Participante, Partido, Pronostico}
import Vista.{vista_Desgloce, vista_Participantes}

import java.awt.event.{ActionEvent, ActionListener, WindowAdapter, WindowEvent}
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.swing.table.DefaultTableModel
import javax.swing.{DefaultComboBoxModel, JComboBox, JMenuItem, JOptionPane, JPopupMenu, JSpinner, JTable, SpinnerNumberModel}
import scala.collection.mutable.ListBuffer

class ctrParticipantes extends ActionListener{
  val admin=new Administrador()
  val vista=new vista_Participantes()
  vista.addWindowListener(new WindowAdapter() {
    override def windowClosing(e:WindowEvent):Unit={
      vista.dispose()
      new ctrPrincipal()
    }
  })
  vista.setVisible(true)
  vista.grbDatosEditar.hide()
  inicializar_cmbs()
  inicializar_botones()
  menuContextualGrupos()
  menuContextualEditarPronostico()
  var participante:Participante=null
  var partidos:List[Partido]=null
  var marcadores:List[List[Int]]=null
  var fecha_ingreso:LocalDateTime=null
  def inicializar_botones(): Unit ={
    vista.btnEliminar.addActionListener(this)
    vista.btnCrearUsuario.addActionListener(this)
    vista.btnBuscarEditar.addActionListener(this)
    vista.btnBuscarAgregar.addActionListener(this)
    vista.btnEditarUsuario.addActionListener(this)
    vista.cmbParticipantesEditar.addActionListener(this)
    vista.rbtPronosticosPorDefinir.addActionListener(this)
    vista.rbtPronosticosDefinidos.addActionListener(this)
  }

  def inicializar_cmbs(): Unit ={
    val cmbs:List[JComboBox[String]]=List(vista.cmbParticipantesEditar.asInstanceOf[JComboBox[String]],vista.cmbParticipantesPronostico.asInstanceOf[JComboBox[String]],vista.cmbParticipantesPronostico2.asInstanceOf[JComboBox[String]],vista.cmbParticipantesEliminar.asInstanceOf[JComboBox[String]])
    val personas=admin.obtener_lista_participantes()
    val personasString=for(i<-personas) yield "%s-%s %s".format(i.cedula,i.nombre,i.apellido)
    val modelo=new DefaultComboBoxModel[String](personasString.toArray)
    for (i<-cmbs){
      i.removeAll()
      i.setModel(modelo)
    }
  }

  def verificar_telefono_cedula(cedula:String,telefono:String): Boolean ={
    //Verificacion de que cedula tenga solo digitos
    if (cedula.size!=10){
      JOptionPane.showMessageDialog(null,"Ingrese un número de cédula válido","Error en el número de cédula!",JOptionPane.ERROR_MESSAGE)
      return false
    }
    for(i<-cedula.toCharArray){
      if(Character.isDigit(i)==false){
        JOptionPane.showMessageDialog(null,"Ingrese un número de cédula válido","Error en el número de cédula!",JOptionPane.ERROR_MESSAGE)
        return false
      }
    }
    //Comprobacion de telefono
    if (telefono.size!=10){
      JOptionPane.showMessageDialog(null,"Ingrese un número de teléfono valido","Error en el número de teléfono!",JOptionPane.ERROR_MESSAGE)
      return false
    }
    for(i<-telefono.toCharArray){
      if(Character.isDigit(i)==false){
        JOptionPane.showMessageDialog(null,"Ingrese un número de teléfono válido","Error en el número de teléfono!",JOptionPane.ERROR_MESSAGE)
        return false
      }
    }
    return true
  }

  def ingresar_usuario(): Unit ={
    val elementos =List(vista.txtCedula,vista.txtTelefono,vista.txtDireccion, vista.txtNombre, vista.txtApellido)
    val values=for(i<-elementos) yield i.getText
    if(values.filter(x=>{x.length==0}).size!=0){
      JOptionPane.showMessageDialog(null, "Algunos campos están vacíos, asegurese de llenar todos","Error, campos vacíos!", JOptionPane.ERROR_MESSAGE)
      return
    }
    else if(verificar_telefono_cedula(values(0),values(1))==false){
      return
    }
    //Existencia de participante
    participante=admin.obtener_participante(values(0))
    if(participante!=null){
      JOptionPane.showMessageDialog(null,"El número de cédula que se intento ingresar ya esta registrado", "¡Error!", JOptionPane.ERROR_MESSAGE)
      return
    }
    val premio=admin.obtener_premio()
    val msg=JOptionPane.showConfirmDialog(null,"El participante está a punto de ser registrado. Se debe realizar el cobro de una cuota de $"+premio(0)+" para ser parte del sistema.\n ¿Está de acuerdo?")
    if(msg!=0) return//Cancelado
    admin.agregar_participante(new Participante(values(0),values(3),values(4),values(2),values(1)))
    premio(1)=premio(0).asInstanceOf[Double]+premio(1).asInstanceOf[Double]
    admin.actualizar_premio()
    for(i<-elementos) i.setText("")
    inicializar_cmbs()
  }

  def buscar_usuario(): Unit ={
    val elementos =List(vista.txtCedulaEditar,vista.txtTelefonoEditar,vista.txtDireccionEditar, vista.txtNombreEditar, vista.txtApellidoEditar)
    this.participante=admin.obtener_participante(vista.cmbParticipantesEditar.getSelectedItem.toString.substring(0,10))
    if(this.participante==null){
      JOptionPane.showMessageDialog(null,"El participante buscado no ha sido encontrado","¡Error!",JOptionPane.ERROR_MESSAGE)
      return
    }
    else{
      vista.grbDatosEditar.show(true)
    }
    for(i<-elementos){ i.setText("") }
    vista.txtCedulaEditar.setText(participante.cedula)
    vista.txtNombreEditar.setText(participante.nombre)
    vista.txtApellidoEditar.setText(participante.apellido)
    vista.txtTelefonoEditar.setText(participante.telefono)
    vista.txtDireccionEditar.setText(participante.direccion)
  }

  def editar_usuario(): Unit ={
    val elementos =List(vista.txtCedulaEditar,vista.txtTelefonoEditar,vista.txtDireccionEditar, vista.txtNombreEditar, vista.txtApellidoEditar)
    val values=for(i<-elementos) yield i.getText
    if(values.filter(x=>{x.length==0}).size!=0){
      JOptionPane.showMessageDialog(null,"Algunos campos están vacíos, asegurese de llenar todos","Error, campos vacíos!",JOptionPane.ERROR_MESSAGE)
      return
    }
    else if(verificar_telefono_cedula(values(0),values(1))==false){
      return
    }
    val cedula_anterior=participante.cedula
    participante.cedula=values(0)
    participante.nombre=values(3)
    participante.apellido=values(4)
    participante.telefono=values(1)
    participante.direccion=values(2)
    admin.obtener_lista_pronosticos()
    if(cedula_anterior!=values(0)){
      val pronosticos=admin.obtener_pronosticos(cedula_anterior)
      for(p<-pronosticos) p.participante=values(0)
      admin.actualizar_lista_pronosticos(null)
    }
    admin.actualizar_lista_participantes()
    inicializar_cmbs()
    for(e<-elementos) e.setText("")
    vista.grbDatosEditar.hide()
  }

  def eliminar_usuario(): Unit ={
    val participante_aux=admin.obtener_participante(vista.cmbParticipantesEliminar.getSelectedItem.toString.substring(0,10))
    if (participante_aux==null){
      JOptionPane.showMessageDialog(null,"El participante ha eliminar no ha sido encontrado.","¡Error!",JOptionPane.ERROR_MESSAGE)
      return
    }
    val msg=JOptionPane.showConfirmDialog(null,"¿Está seguro en eliminar al participante? ","¡Advertencia!",JOptionPane.YES_NO_OPTION)
    if(msg!=0) return
    admin.obtener_lista_pronosticos()
    var pronosticos=admin.obtener_pronosticos(participante_aux.cedula)
    if(pronosticos.size==0){
      admin.obtener_lista_participantes()-=participante_aux
      admin.actualizar_lista_participantes()
      JOptionPane.showMessageDialog(null, "El participante ha sido eliminado","Eliminación éxitosa!",JOptionPane.INFORMATION_MESSAGE)
      inicializar_cmbs()
      return
    }
    var pronosticos_ids:ListBuffer[Int]=(for(p<-pronosticos) yield p.id).asInstanceOf[ListBuffer[Int]]
    var partidos=for(p<-pronosticos_ids) yield admin.obtener_partido_pronostico(p)
    for(e<-pronosticos_ids zip partidos){
      e._2.pronosticos-=e._1
    }
    var pronosticos_aux=admin.obtener_lista_pronosticos()//Se obtiene la lista de pronosticos disponibles
    pronosticos_aux=pronosticos_aux.filter(x=> !pronosticos_ids.contains(x.id))//Se eliminan los pronosticos del participante
    admin.actualizar_lista_pronosticos(pronosticos_aux)//Se actualiza la lista de pronosticos
    admin.actualizar_lista_partidos_disponibles()//Se actualiza la lista de partidos disponibles
    admin.obtener_lista_participantes()-=participante ///Se elimina el participante
    admin.actualizar_lista_participantes() //Se actualiza la lista de participantes
    JOptionPane.showMessageDialog(null,"El participante ha sido eliminado","Eliminación éxitosa!",JOptionPane.INFORMATION_MESSAGE)
    vista.lblColocarPuntosA2.setText("")
    vista.lblColocarPuntosA.setText("")
    vista.lblColocarParticipanteA.setText("")
    vista.lblColocarParticipanteA2.setText("")
    this.partidos=null
    this.marcadores=null
    this.participante=null
    inicializar_cmbs()
  }

  def mostrar_tabla(tabla:JTable,lista:List[Partido],opc:Boolean,marcador:List[List[Int]]): Unit ={
    val table=tabla.getModel.asInstanceOf[DefaultTableModel]
    table.setRowCount(0)
    if(lista!=null){
      for(i<-0 until lista.size){
        var values:List[AnyRef]=lista(i).obtener_valores_mostrar()
        var data:ListBuffer[AnyRef]=ListBuffer(values(0),values(1))
        if(opc==true){
          data+=values(3)
        }
        else{
          data+="%s - %s".format(marcador(i)(0),marcador(i)(1))
          data+=values(3)
        }
        table.insertRow(i,data.toArray)
      }
    }
  }

  def partidos_seleccionar_1(pronosticos: List[Partido], fecha: LocalDateTime, editar: Boolean): List[Partido] = {
    var grupos = admin.obtener_lista_grupos()
    if (editar == true) grupos = grupos.filter(x => {x.fechas_definidas == true})
    else grupos = grupos.filter(x => {x.fechas_definidas == true && x.definido == false})
    var partidos: ListBuffer[Partido] = ListBuffer()
    for (c <- grupos){
      for(gp <- admin.obtener_partidos_grupo(c.codigo_grupo)){
        partidos += gp
      }
    }
    partidos = partidos.sortBy(x => {x.fechaInicio})
    if (editar == false) partidos = partidos.filter(x => {
      !pronosticos.contains(x) && x.fechaInicio.isAfter(fecha)==true
    })
    else partidos = partidos.filter(x => {
      pronosticos.contains(x)
    })
    if (partidos.size == 0 && admin.fase_definida(0) == true && editar == false) {
      JOptionPane.showMessageDialog(null, "La fase de grupos ha finalizado. \n No se pueden realizar mas pronósticos para esta fase", "¡Aviso importante!", JOptionPane.ERROR_MESSAGE)
      return null
    }
    else if (partidos.size == 0 && editar == false) {
      JOptionPane.showMessageDialog(null, "No existen partidos para pronosticar en la fecha de ingreso colocada.\n Puede esperar que se definan las fechas de los partidos de esta fase.", "¡Aviso importante!", JOptionPane.ERROR_MESSAGE)
      return null
    }
    return partidos.toList
  }

  def partidos_seleccionar_2(index: Int, pronosticos: List[Partido], fecha: LocalDateTime, editar: Boolean):  List[Partido] = {
    var partidos = admin.obtener_lista_partidos_superiores(index).values.toList
    if (editar == true) partidos = partidos.filter(x => {x.fechaDefinida == true && pronosticos.contains(x)})
    else partidos = partidos.filter(x => {x.fechaDefinida == true && x.partidoDefinido==false && !pronosticos.contains(x) && x.fechaInicio.compareTo(fecha)>0})
    if (partidos.size == 0 && admin.fase_definida(index+1) == true && editar == false) {
      JOptionPane.showMessageDialog(null, "La fase de grupos ha finalizado. \n No se pueden realizar mas pronósticos para esta fase", "¡Aviso importante!", JOptionPane.ERROR_MESSAGE)
      return null
    }
    else if (partidos.size == 0 && editar == false) {
      JOptionPane.showMessageDialog(null, "No existen partidos para pronosticar en la fecha de ingreso colocada.\n Puede esperar que se definan las fechas de los partidos de esta fase.", "¡Aviso importante!", JOptionPane.ERROR_MESSAGE)
      return null
    }
    return partidos
  }

  def filtro(flag:Boolean): Unit ={
    try{
      var values=this.marcadores zip this.partidos
      values=values.filter(x=>{x._2.partidoDefinido==flag})
      var partidos=for(p<-values) yield p._2
      var marcadores=for(p<-values) yield p._1
      mostrar_tabla(vista.jgdEditarPronosticos,partidos,false,marcadores)
    }
    catch{
      case e:Exception=> return
    }
  }

  def get_marcador(seleccion1:String,seleccion2:String): ListBuffer[Int] ={
    var marcador:ListBuffer[Int] = ListBuffer()
    //Spinner de seleccion de marcador
    val spinner=new JSpinner()
    val modelo_spinner=new SpinnerNumberModel()
    modelo_spinner.setMaximum(50)
    modelo_spinner.setMinimum(0)
    spinner.setModel(modelo_spinner)
    spinner.setSize(spinner.getPreferredSize.width,spinner.getPreferredSize.height)
    var a:Int = JOptionPane.showConfirmDialog(null,spinner,"Ingrese el marcador a favor de " + seleccion1,JOptionPane.OK_CANCEL_OPTION)
    if(a != 0) return null
    marcador+=spinner.getValue.asInstanceOf[Int]
    //Marcador seleccion 2
    spinner.setValue(0)//reseteo
    a= JOptionPane.showConfirmDialog(null,spinner,"Ingrese el marcador a favor de " + seleccion2,JOptionPane.OK_CANCEL_OPTION)
    if(a != 0) return null
    marcador+=spinner.getValue.asInstanceOf[Int]
    return marcador
  }

  def agregar_pronostico(): Unit ={
    try{
      fecha_ingreso=LocalDateTime.parse(vista.dtpFechaSimulada.getText,DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))//Se obtiene la fecha de ingreso del pronostico
    }
    catch {
      case e:Exception=> JOptionPane.showMessageDialog(null,"Ingrese una fecha correcta","Error!",JOptionPane.ERROR_MESSAGE)
      return
    }
    if(vista.cmbParticipantesPronostico.getSelectedIndex<0) return
    admin.obtener_lista_pronosticos()//Se cargan los pronosticos disponibles
    val participante_aux = admin.obtener_participante(vista.cmbParticipantesPronostico.getSelectedItem.toString.substring(0, 10)) //Se busca la instancia del participante
    if( participante_aux ==null) {//Verificacion de la existencia del participante
      JOptionPane.showMessageDialog(null,"El participante no ha sido encontrado","Error!",JOptionPane.ERROR_MESSAGE)
      return
    }
    //Se coloca en pantalla la informacion del participante
    vista.lblColocarParticipanteA.setText(participante_aux.nombre + " " + participante_aux.apellido)
    vista.lblColocarPuntosA.setText(participante_aux.puntos(0)+"")
    val pronosticos:ListBuffer[Pronostico]=admin.obtener_pronosticos(participante_aux.cedula)//Se obtienen todos los pronosticos que realizo el participante
    val pronosticos_par= for(p:Pronostico<-pronosticos) yield admin.obtener_partido_pronostico(p.id) //Se obtienen los partidos asociados a los pronosticos
    val tipo=vista.cmbTipoEncuentro.getSelectedItem.toString
    var partidos:List[Partido]=null
    if(tipo=="Fase de Grupos"){
      partidos=partidos_seleccionar_1(pronosticos_par.toList,fecha_ingreso,false)
    }
    else if (tipo=="Octavos de Final") {
      partidos = partidos_seleccionar_2(0,pronosticos_par.toList,fecha_ingreso,false)
    }
    else if (tipo=="Cuartos de Final") {
      partidos = partidos_seleccionar_2(1,pronosticos_par.toList,fecha_ingreso,false)
    }
    else if (tipo=="Semifinales") {
      partidos = partidos_seleccionar_2(2,pronosticos_par.toList,fecha_ingreso,false)
    }
    else if (tipo=="Finales") {
      partidos = partidos_seleccionar_2(3,pronosticos_par.toList,fecha_ingreso,false)
    }
    mostrar_tabla(vista.jgdAgregarPronostico,partidos,true,null)//Se muestran los partidos disponibles para ser pronosticados
    participante=participante_aux//Se actualiza la referencia del participante buscado
  }

  def asignar_pronostico(): Unit ={
    admin.obtener_lista_pronosticos()
    val tabla=vista.jgdAgregarPronostico
    val fila=tabla.getSelectedRow
    val values:ListBuffer[String]=ListBuffer(tabla.getValueAt(fila,0).toString,tabla.getValueAt(fila,1).toString,tabla.getValueAt(fila,2).toString)
    val fecha=LocalDateTime.parse(values(0),DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    var partido:Partido=null
    if(values(2)=="PD"){
      values(2)=null
      partido=new Partido(fecha,values(1),null)
    }
    else partido=new Partido(fecha,values(1),values(2))
    var opc=vista.cmbTipoEncuentro.getSelectedIndex
    if(opc==0) partido=admin.obtener_partido_grupo(partido)
    else partido=admin.obtener_partido_fase_superior(opc-1,values(1),values(2))
    var marcador=get_marcador(values(1),values(2))
    if (marcador==null) return
    var pronostico=new Pronostico(fecha_ingreso,participante.cedula,marcador)
    admin.agregar_pronostico(pronostico)
    partido.pronosticos.append(pronostico.id)
    admin.actualizar_lista_partidos_disponibles()
    admin.actualizar_lista_pronosticos(null)
    agregar_pronostico()
  }

  def editar_buscar_pronostico(): Unit ={
    try{
      var fecha=LocalDateTime.parse(vista.dtpFechaSimulada2.getText,DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))//Se obtiene la fecha de ingreso del pronostico
    }
    catch {
      case e:Exception=> JOptionPane.showMessageDialog(null,"Ingrese una fecha correcta","Error!",JOptionPane.ERROR_MESSAGE)
        return
    }
    if(vista.cmbParticipantesPronostico2.getSelectedIndex<0) return
    admin.obtener_lista_pronosticos()
    val participante=admin.obtener_participante(vista.cmbParticipantesPronostico2.getSelectedItem.toString.substring(0,10))
    if(participante==null){
      JOptionPane.showMessageDialog(null,"El participante seleccionado no ha sido encontrado")
      return
    }
    //Se coloca en pantalla la informacion del participante
    vista.lblColocarParticipanteA2.setText(participante.nombre+" "+participante.apellido)
    vista.lblColocarPuntosA2.setText(participante.puntos(0)+"")
    //Se obtienen los pronosticos asociados al participante
    val pronosticos=admin.obtener_pronosticos(participante.cedula)
    //Se obtienen los partidos de cada pronostico asociado
    val partidos_pronosticados:ListBuffer[Partido]=for(p<-pronosticos) yield admin.obtener_partido_pronostico(p.id)
    //Se generan los pares partido pronostico para poder posteriormente ubicarlos
    val pronosticos_aux = pronosticos zip partidos_pronosticados
    val tipo=vista.cmbTipoEncuentro2.getSelectedIndex
    if (tipo==0){
      this.partidos=partidos_seleccionar_1(partidos_pronosticados.toList,null,true)
    }
    else{
      this.partidos=partidos_seleccionar_2(tipo-1,partidos_pronosticados.toList,null,true)
    }
    //Se ordenan los partidos obtenidos por fecha
    this.partidos=this.partidos.sortBy(x=>{x.fechaInicio})
    //Se obtienen los marcadores en base a los partidos obtenidos
    var marcadores_aux:ListBuffer[List[Int]]=ListBuffer()
    for(p<-this.partidos){
      for(pr<-pronosticos_aux){
        if(p==pr._2) marcadores_aux+=pr._1.marcador_pronosticado.toList
      }
    }
    this.marcadores=marcadores_aux.toList
    //Se aplica el filtro seleccionado en el radio button
    if(vista.rbtPronosticosPorDefinir.isSelected){
      filtro(false)
    }
    else filtro(true)
    //Se actualiza la referencia del participante
    this.participante = participante
  }

  def editar_pronostico(): List[Serializable] ={
    admin.obtener_lista_pronosticos()
    val tabla=vista.jgdEditarPronosticos
    val fila=tabla.getSelectedRow
    if (fila<0) return null
    val values:ListBuffer[String]=ListBuffer(tabla.getValueAt(fila,0).toString,tabla.getValueAt(fila,1).toString,tabla.getValueAt(fila,3).toString)
    val fecha=LocalDateTime.parse(values(0),DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    var partido:Partido=null
    if(values(2)=="PD"){
      values(2)=null
      partido=new Partido(fecha,values(1),null)
    }
    else partido=new Partido(fecha,values(1),values(2))
    var opc=vista.cmbTipoEncuentro2.getSelectedIndex
    if(opc==0) partido=admin.obtener_partido_grupo(partido)
    else partido=admin.obtener_partido_fase_superior(opc-1,values(1),values(2))
    var pronosticos=admin.obtener_pronosticos(this.participante.cedula)//Se obtienen los pronosticos del participante asociado
    pronosticos=pronosticos.filter(x=>{partido.pronosticos.contains(x.id)==true})
    return List(partido,pronosticos(0))
  }
  def validar_partido_definido(partido:Partido): Boolean ={
    val fechaIngreso=LocalDateTime.parse(vista.dtpFechaSimulada2.getText,DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    if(partido.partidoDefinido==true){
      JOptionPane.showMessageDialog(null,"El partido se ha definido, no se pueden realizar cambios", "Error!",JOptionPane.ERROR_MESSAGE)
      return false
    }
    else if(fechaIngreso.isAfter(partido.fechaInicio)){
      JOptionPane.showMessageDialog(null,"El partido esta en juego, no se pueden realizar cambios", "Error!",JOptionPane.ERROR_MESSAGE)
      return false
    }
    return true
  }
  /*********************Menu contextual*************/
  def menuContextualGrupos(): Unit ={//menu contextual
    val menuA=new JMenuItem()
    menuA.setText("Colocar Pronostico")
    menuA.addActionListener((e: ActionEvent) => {
      asignar_pronostico()
    })
    val menu=new JPopupMenu()
    menu.add(menuA)
    vista.jgdAgregarPronostico.setComponentPopupMenu(menu)
    vista.jgdAgregarPronostico.addMouseListener(new java.awt.event.MouseAdapter() {
      override def mouseClicked(evt:java.awt.event.MouseEvent ) {
      }
    })
  }
  def menuContextualEditarPronostico(): Unit ={//menu contextual
    val menuA=new JMenuItem()
    menuA.setText("Editar Pronostico")
    menuA.addActionListener((e: ActionEvent) => {
      val values=editar_pronostico()
      if(values!=null) {
        val partido = values(0).asInstanceOf[Partido]
        val pronostico = values(1).asInstanceOf[Pronostico]
        if (validar_partido_definido(partido) == true) {
          val marcador_pronosticado = get_marcador(partido.selecciones(0), partido.selecciones(1))
          if (marcador_pronosticado != null) {
            pronostico.marcador_pronosticado = marcador_pronosticado
            admin.actualizar_lista_pronosticos(null)
            editar_buscar_pronostico()
          }
        }
      }
    })
    val menuB=new JMenuItem()
    menuB.setText("Eliminar Pronostico")
    menuB.addActionListener((e: ActionEvent) => {
      val values=editar_pronostico()
      if(values!=null) {
        val partido = values(0).asInstanceOf[Partido]
        val pronostico = values(1).asInstanceOf[Pronostico]
        if (validar_partido_definido(partido) == true) {
          partido.pronosticos -= pronostico.id
          admin.obtener_lista_pronosticos() -= pronostico
          admin.actualizar_lista_partidos_disponibles()
          admin.actualizar_lista_pronosticos(null)
          editar_buscar_pronostico()
        }
      }

    })
    val menuC=new JMenuItem()
    menuC.setText("Ver Resultados")
    menuC.addActionListener((e: ActionEvent) => {
      val values=editar_pronostico()
      if(values!=null) {
        val partido = values(0).asInstanceOf[Partido]
        val pronostico = values(1).asInstanceOf[Pronostico]
        if (partido.partidoDefinido == false) {
          JOptionPane.showMessageDialog(null, "El partido aún no se ha definido.\n Intentelo de nuevo cuando esté disponible.", "¡Información importante!", JOptionPane.INFORMATION_MESSAGE)
        }
        else {
          val ctr = new ctrResultados(partido, pronostico)
        }
      }
    })
    val menu=new JPopupMenu()
    menu.add(menuA)
    menu.add(menuB)
    menu.add(menuC)
    vista.jgdEditarPronosticos.setComponentPopupMenu(menu)
    vista.jgdEditarPronosticos.addMouseListener(new java.awt.event.MouseAdapter() {
      override def mouseClicked(evt:java.awt.event.MouseEvent ) {
      }
    })
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    if(e.getSource==vista.btnCrearUsuario){
      ingresar_usuario()
    }
    else if(e.getSource==vista.cmbParticipantesEditar){
      buscar_usuario()
    }
    else if(e.getSource==vista.btnEditarUsuario){
      editar_usuario()
    }
    else if(e.getSource==vista.btnEliminar){
      eliminar_usuario()
    }
    else if(e.getSource==vista.btnBuscarAgregar){
      agregar_pronostico()
    }
    else if(e.getSource==vista.btnBuscarEditar){
      editar_buscar_pronostico()
    }
    else if(e.getSource==vista.rbtPronosticosPorDefinir){
      filtro(false)
    }
    else if(e.getSource==vista.rbtPronosticosDefinidos){
      filtro(true)
    }

  }
}
class ctrResultados(val partido: Partido,val pronostico: Pronostico){
  val vista=new vista_Desgloce(null)
  vista.setVisible(true)
  actualizar_lbls()
  actualizar_tabla()
  def actualizar_lbls(): Unit ={
    vista.lblPartido.setText("%s v %s".format(partido.selecciones(0),partido.selecciones(1)))
    vista.lblMarcadorFinal2.setText("%s-%s".format(partido.marcador(0),partido.marcador(1)))
    vista.lblMarcadorPronosticado2.setText("%s-%s".format(pronostico.marcador_pronosticado(0), pronostico.marcador_pronosticado(1)))
    vista.lblPartidoAnulado.setVisible(partido.anulado)
  }
  def actualizar_tabla(): Unit ={
    val modelo:DefaultTableModel=vista.jtbDesgloce.getModel.asInstanceOf[DefaultTableModel]
    modelo.setRowCount(0)
    val puntos=pronostico.puntos
    puntos+=puntos.sum
    val ls=puntos.toList.asInstanceOf[List[AnyRef]]
    modelo.insertRow(0,ls.toArray)

  }
}

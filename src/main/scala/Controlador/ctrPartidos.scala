package Controlador

import Modelo.{Administrador, Partido, PartidoEliminatorio}
import Vista.vista_Partidos

import java.awt.event.{ActionEvent, ActionListener, WindowAdapter, WindowEvent}
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.swing.table.DefaultTableModel
import javax.swing.{JButton, JMenuItem, JOptionPane, JPopupMenu, JSpinner, JTable, SpinnerNumberModel}
import scala.collection.mutable.ListBuffer

class ctrPartidos extends ActionListener{
  val admin=new Administrador()
  val vista=new vista_Partidos()
  vista.addWindowListener(new WindowAdapter() {
    override def windowClosing(e:WindowEvent):Unit={
      vista.dispose()
      new ctrPrincipal()
    }
  })
  vista.setVisible(true)
  vista.btnDefinirMarcadores.addActionListener(this)
  vista.btnAsignar.addActionListener(this)
  vista.cmbGrupos.addActionListener(this)
  vista.btnDefinirFechas.addActionListener(this)
  vista.btnDefinirFechasOctavos.addActionListener(this)
  vista.btnDefinirFechasCuartos.addActionListener(this)
  vista.btnDefinirFechasSemifinales.addActionListener(this)
  vista.btnDefinirFechasFinales.addActionListener(this)
  vista.cmbGruposTablaPosiciones.addActionListener(this)
  vista.btnDefinirFaseOctavos.addActionListener(this)
  vista.btnDefinirFaseCuartos.addActionListener(this)
  vista.btnDefinirFaseSemifinales.addActionListener(this)
  vista.btnDefinirFaseFinales.addActionListener(this)
  menuContextualGrupos()
  menuContextualOctavos()
  menuContextualCuartos()
  menuContextualSemifinales()
  menuContextualFinales()
  actualizar_calendario_grupos()
  actualizar_calendario_superior(0,vista.jgdTablaPartidosOctavos,vista.btnDefinirFaseOctavos,vista.btnDefinirFechasOctavos)
  actualizar_calendario_superior(1,vista.jgdTablaPartidosCuartos,vista.btnDefinirFaseCuartos,vista.btnDefinirFechasCuartos)
  actualizar_calendario_superior(2,vista.jgdTablaPartidosSemifinales,vista.btnDefinirFaseSemifinales,vista.btnDefinirFechasSemifinales)
  actualizar_calendario_superior(3,vista.jgdTablaPartidosFinales,vista.btnDefinirFaseFinales,vista.btnDefinirFechasFinales)
  mostrar_tabla_posiciones()

  /********************************Actualizar Calendario************************************/
  def actualizar_calendario_grupos(): Unit ={
    val lista_partidos=admin.obtener_partidos_grupo(vista.cmbGrupos.getSelectedItem.toString)
    vista.jgdTablaPartidosGrupos.getModel.asInstanceOf[DefaultTableModel].setRowCount(0)
    if(lista_partidos==null) return
    /*Modelo de tabla*/
    if(lista_partidos!=null){
      val grupo=admin.obtener_grupo_codigo(vista.cmbGrupos.getSelectedItem.toString)
      vista.btnDefinirMarcadores.setEnabled(!grupo.definido)
      vista.btnDefinirFechas.setEnabled(!grupo.fechas_definidas)
      var lista_partidos_aux:ListBuffer[Partido]=ListBuffer()
      for(y<-lista_partidos){if(y.fechaInicio!=null) lista_partidos_aux+=y}
      lista_partidos_aux=lista_partidos_aux.sortBy(x=>{x.fechaInicio})
      for(p<-lista_partidos){if(p.fechaInicio==null) lista_partidos_aux+=p}
      mostrar_en_tabla_partidos(vista.jgdTablaPartidosGrupos, lista_partidos_aux.toList)
    }
  }
  def actualizar_calendario_superior(index:Int,tabla:JTable,marcadores:JButton,fechas:JButton): Unit ={
    val lista_partidos=admin.obtener_lista_partidos_superiores(index).values.toList
    if(lista_partidos==null) return
    /*Modelo de tabla*/
    tabla.getModel.asInstanceOf[DefaultTableModel].setRowCount(0)
    if(lista_partidos!=null){
      var fecha_definido=lista_partidos.filter(x=>{x.fechaDefinida==false}).size==0
      var definido=lista_partidos.filter(x=>{x.partidoDefinido==false}).size==0
      marcadores.setEnabled(!definido)
      fechas.setEnabled(!fecha_definido)
      var lista_partidos_aux:ListBuffer[PartidoEliminatorio]=ListBuffer()
      for(y<-lista_partidos){if(y.fechaInicio!=null) lista_partidos_aux+=y}
      lista_partidos_aux=lista_partidos_aux.sortBy(x=>{x.fechaInicio})
      for(p<-lista_partidos){if(p.fechaInicio==null) lista_partidos_aux+=p}
      mostrar_en_tabla_partidos(tabla, lista_partidos_aux.toList)
    }
  }
  /********************************Generar Partidos Grupos*************************************/
  def generar_partidos_grupos(): Unit ={
    try{
      admin.generar_partidos_grupos()
      JOptionPane.showMessageDialog(null,"Los partidos de la fase de grupos se han generado con éxito","Partidos Generados!",JOptionPane.INFORMATION_MESSAGE)
    }
    catch {case e:Exception=> JOptionPane.showMessageDialog(null,e.getMessage,"Error!",JOptionPane.ERROR_MESSAGE)}
  }
  /************************************Solicitar Marcadores************************************/
  def get_marcador(seleccion1:String,seleccion2:String,msg:String): ListBuffer[Int] ={
    var marcador:ListBuffer[Int] = ListBuffer()
    //Spinner de selección de marcador
    val spinner=new JSpinner()
    val modelo_spinner=new SpinnerNumberModel()
    modelo_spinner.setMaximum(50)
    modelo_spinner.setMinimum(0)
    spinner.setModel(modelo_spinner)
    spinner.setSize(spinner.getPreferredSize.width,spinner.getPreferredSize.height)
    var a:Int = JOptionPane.showConfirmDialog(null,spinner,msg + seleccion1,JOptionPane.OK_CANCEL_OPTION)
    if(a != 0) return null
    marcador+=spinner.getValue.asInstanceOf[Int]
    //Marcador seleccion 2
    spinner.setValue(0)//reseteo
    a= JOptionPane.showConfirmDialog(null,spinner,msg + seleccion2,JOptionPane.OK_CANCEL_OPTION)
    if(a != 0) return null
    marcador+=spinner.getValue.asInstanceOf[Int]
    return marcador
  }
  def get_marcador_penales(seleccion1:String,seleccion2:String): ListBuffer[Any] ={
    var marcador:ListBuffer[Int] = null
    var marcador_penales:ListBuffer[Int] = null
    /**/
    marcador=get_marcador(seleccion1,seleccion2, "Marcador final de ")
    if (marcador==null) return null
    if(marcador(0)==marcador(1)){//empate en tiempo reglamentario
      marcador_penales=get_marcador(seleccion1,seleccion2,"Marcador en penales de ")
      if (marcador_penales==null) return null
      if(marcador_penales(0)==marcador_penales(1)) {
        JOptionPane.showMessageDialog(null, "No puede haber empate en la definición de penales", "Error!", JOptionPane.ERROR_MESSAGE)
        return null
      }
    }
    var flag = JOptionPane.showConfirmDialog(null,"¿Está seguro en cambiar el marcador?","Advertencia",JOptionPane.YES_NO_OPTION)
    if (flag == JOptionPane.NO_OPTION) return null
    else return ListBuffer(marcador,marcador_penales)
  }
  /********************************Actualizar Marcadores********************************/
  def actualizar_marcador_grupos(tabla: JTable,get_partido:Partido=>Partido): Boolean ={
    /*******Contenido de la fila seleccionada*****/
    val fila=tabla.getSelectedRow
    if (fila<0) return false
    val aux:String=tabla.getModel.getValueAt(fila,0).toString
    if(aux=="PD"){//Cuando se quiere definir marcador antes de definir fecha
      JOptionPane.showMessageDialog(null,"Se tiene que definir la fecha antes de colocar un marcador","Error!",JOptionPane.ERROR_MESSAGE)
      return false
    }
    val seleccion1=tabla.getModel.getValueAt(fila,1).toString
    val seleccion2=tabla.getModel.getValueAt(fila,3).toString
    val fecha=LocalDateTime.parse(aux,DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    val partido=get_partido(new Partido(fecha,seleccion1,seleccion2))//se obtiene la referencia del partido
    if(partido==null) {//Partido no encontrado
      JOptionPane.showMessageDialog(null,"Partido no encontrado","Error!",JOptionPane.ERROR_MESSAGE)
      return false
    }
    else if(partido!=null && partido.fechaDefinida==false){//Cuando se quiere definir marcador antes de definir fecha
      JOptionPane.showMessageDialog(null,"Se tiene que definir la fecha antes de colocar un marcador","Error!",JOptionPane.ERROR_MESSAGE)
      return false
    }
    else if(partido!=null && partido.partidoDefinido==true){//Cuando se quiere definir marcador antes de definir fecha
      JOptionPane.showMessageDialog(null,"Las fechas y marcadores se han definido, no se pueden realizar cambios.","Error!",JOptionPane.ERROR_MESSAGE)
      return false
    }
    else{//Actualización permitida
      var marcador:ListBuffer[Int]=get_marcador(seleccion1,seleccion2,"Marcador final de ")//marcador final del partido
      if (marcador==null) return false//problema al ingresar un marcador
      partido.actualizar_marcador(marcador)//actualizacion de marcador
      JOptionPane.showMessageDialog(null,"Marcador Actualizado con éxito")
      return true
    }
  }
  /***********************************Actualizar Marcadores Superior************************/
  def actualizar_marcador_superior(tabla: JTable,index:Int): Boolean ={
    /*******Contenido de la fila seleccionada*****/
    val fila=tabla.getSelectedRow
    if (fila<0) return false
    val seleccion1=tabla.getModel.getValueAt(fila,1).toString
    val seleccion2=tabla.getModel.getValueAt(fila,3).toString
    val partido=admin.obtener_partido_fase_superior(index,seleccion1,seleccion2)//se obtiene la referencia del partido
    if(partido==null) {//Partido no encontrado
      JOptionPane.showMessageDialog(null,"Partido no encontrado","Error!",JOptionPane.ERROR_MESSAGE)
      return false
    }
    else if(partido!=null && partido.fechaDefinida==false){//Cuando se quiere definir marcador antes de definir fecha
      JOptionPane.showMessageDialog(null,"Se tiene que definir la fecha antes de colocar un marcador","Error!",JOptionPane.ERROR_MESSAGE)
      return false
    }
    else if(partido!=null && partido.partidoDefinido==true){//Cuando se quiere definir marcador antes de definir fecha
      JOptionPane.showMessageDialog(null,"Las fechas y marcadores se han definido, no se pueden realizar cambios.","Error!",JOptionPane.ERROR_MESSAGE)
      return false
    }
    else{//Actualización permitida
      val marcadores:ListBuffer[Any]=get_marcador_penales(seleccion1,seleccion2)//marcador final y marcador de penales
      if (marcadores==null) return false//algún problema al ingresar marcadores
      partido.asInstanceOf[PartidoEliminatorio].actualizar_marcador(marcadores(0).asInstanceOf[ListBuffer[Int]],marcadores(1).asInstanceOf[ListBuffer[Int]])//actualizacion de marcador
      JOptionPane.showMessageDialog(null,"Marcador Actualizado con éxito","Actualización éxitosa",JOptionPane.INFORMATION_MESSAGE)
      return true
    }
  }
  /***********************************Solicitar Fechas********************************/
  def get_fecha(): LocalDateTime ={
    import javax.swing.JFormattedTextField
    import javax.swing.text.MaskFormatter
    val format = new MaskFormatter("##/##/#### ##:##")
    val dateTextField = new JFormattedTextField(format)
    var a:Int = JOptionPane.showConfirmDialog(null,dateTextField,"Ingrese la fecha",JOptionPane.OK_CANCEL_OPTION)
    var fecha:LocalDateTime=null
    if(a != 0) return null
    try{
      fecha=LocalDateTime.parse(dateTextField.getText,DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    }catch {
      case e:Exception=>
        JOptionPane.showMessageDialog(null,"Ingrese una fecha válida","Error!",JOptionPane.ERROR_MESSAGE)
        return null
    }
    return fecha
  }
  /**************************************Actualizar Fecha Grupos***************************/
  def actualizar_fecha_grupos(tabla: JTable,get_partido:Partido=>Partido): Boolean ={
    /*******Contenido de la fila seleccionada*/
    val fila=tabla.getSelectedRow
    if (fila<0) return false
    var fecha:LocalDateTime=null
    var aux:String=tabla.getModel.getValueAt(fila,0).toString
    val seleccion1=tabla.getModel.getValueAt(fila,1).toString
    val seleccion2=tabla.getModel.getValueAt(fila,3).toString
    if(aux!="PD") fecha=LocalDateTime.parse(aux,DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    val partido=get_partido(new Partido(fecha,seleccion1,seleccion2))//se obtiene la referencia del partido
    if(partido!=null&&partido.fechaDefinida==false){
      fecha=get_fecha()
      if (fecha!=null){
        partido.fechaInicio = fecha
        JOptionPane.showMessageDialog(null,"Se ha actualizado la fecha con éxito")
        return true
      }
    }
    else{JOptionPane.showMessageDialog(null,"La fecha se ha definido, no se pueden realizar cambios","Error!",JOptionPane.ERROR_MESSAGE)}
    return false
  }
  /**************************************Actualizar Fecha Superior***************************/
  def actualizar_fecha_superior(tabla: JTable,index:Int): Boolean ={
    /*******Contenido de la fila seleccionada*/
    val fila=tabla.getSelectedRow
    if (fila<0) return false
    var fecha:LocalDateTime=null
    val seleccion1=tabla.getModel.getValueAt(fila,1).toString
    val seleccion2=tabla.getModel.getValueAt(fila,3).toString
    val partido=admin.obtener_partido_fase_superior(index,seleccion1,seleccion2)//se obtiene la referencia del partido
    if(partido!=null&&partido.fechaDefinida==false){
      fecha=get_fecha()
      if (fecha!=null){
        partido.fechaInicio = fecha
        JOptionPane.showMessageDialog(null,"Se ha actualizado la fecha con éxito")
        return true
      }
    }
    else if(partido!=null&&partido.fechaDefinida==true) {
      JOptionPane.showMessageDialog(null,"La fecha se ha definido, no se pueden realizar cambios","Error!",JOptionPane.ERROR_MESSAGE)
      return false
    }
    else  JOptionPane.showMessageDialog(null,"Las selecciones todavía no se han definido, no se pueden colocar fechas de momento","Error!",JOptionPane.ERROR_MESSAGE)
    return false
  }
  /*************************************Definir Fechas*************************/
  def definir_fechas(partidos:List[Partido]): Boolean ={
    if(partidos.filter(x=>{x.fechaInicio==null}).size>0){
      JOptionPane.showMessageDialog(null,"Todas las fechas de los partidos deben estar definidas para continuar","Error!",JOptionPane.ERROR_MESSAGE)
      return false
    }
    else{
      val op=JOptionPane.showConfirmDialog(null,"¿Está seguro en definir las fechas de los enfrentamientos? \n Una vez definidos no se pueden realizar cambios","Advertencia",JOptionPane.YES_NO_OPTION)
      if(op!=0) return false
      for(p<-partidos) p.fechaDefinida=true//definición de fechas
      return true
    }
  }
  /**********************************Definir Marcadores Grupos***********************/
  def definir_marcadores(partidos:List[Partido]): Boolean ={
    if(partidos.filter(x=>{x.marcador==null}).size>0){//partidos sin un marcador definido
      JOptionPane.showMessageDialog(null,"Todos los marcadores de los partidos deben estar definidas para continuar","Error!",JOptionPane.ERROR_MESSAGE)
      return false
    }
    else{
      var op=JOptionPane.showConfirmDialog(null,"¿Está seguro en definir los marcadores de los enfrentamientos? \n Una vez definidos no se pueden realizar cambios","Advertencia",JOptionPane.YES_NO_OPTION)
      if(op!=0) return false//Se canceló el proceso
      for(p<-partidos) p.partidoDefinido=true//definición de los partidos
      for(p<-partidos) p.calcular_pronosticos()
      return true
    }
  }
  /*****************************Mostrar en tablas***********************/
  def mostrar_en_tabla_partidos(tabla:JTable,lista:List[Any]): Unit ={
    val modelo=tabla.getModel.asInstanceOf[DefaultTableModel]
    modelo.setRowCount(0)
    if(lista!=null){
      for(i<-0 until lista.size){
        if(!lista(i).isInstanceOf[PartidoEliminatorio]){
          var lsData:List[AnyRef]=lista(i).asInstanceOf[Partido].obtener_valores_mostrar()
          modelo.insertRow(i,lsData.toArray)
        }
        else {
          var lsData:List[AnyRef]=lista(i).asInstanceOf[PartidoEliminatorio].obtener_valores_mostrar()
          modelo.insertRow(i,lsData.toArray)
        }
      }
    }
  }
  def mostrar_tabla_posiciones(): Unit ={
    val grupo=admin.obtener_grupo_codigo(vista.cmbGruposTablaPosiciones.getSelectedItem.toString)
    if (grupo==null) return
    var selecciones=admin.obtener_selecciones(grupo.selecciones)
    selecciones=selecciones.sortBy(x=>{(x.puntos,x.goles,x.goles_contra)}).reverse
    val modelo=vista.jgdPosiciones.getModel.asInstanceOf[DefaultTableModel]
    for(i<-0 until selecciones.size){
      modelo.setValueAt(selecciones(i).pais,i,0)
      modelo.setValueAt(selecciones(i).puntos,i,1)
      modelo.setValueAt(selecciones(i).goles,i,2)
      modelo.setValueAt(selecciones(i).goles_contra,i,3)
    }
  }
  /*************************Menús contextuales****************/
  def menuContextualGrupos(): Unit ={//menú contextual
    val menuAF=new JMenuItem()
    menuAF.setText("Actualizar Fecha")
    menuAF.addActionListener((e: ActionEvent) => {
      if(actualizar_fecha_grupos(vista.jgdTablaPartidosGrupos,admin.obtener_partido_grupo)){//si se actualizó la fecha
        admin.actualizar_lista_partidos_grupos()
        actualizar_calendario_grupos()
      }
    })
    val menuAM=new JMenuItem()
    menuAM.setText("Actualizar Marcador")
    menuAM.addActionListener((e: ActionEvent) => {
      if(actualizar_marcador_grupos(vista.jgdTablaPartidosGrupos,admin.obtener_partido_grupo)){
        admin.actualizar_lista_partidos_grupos()
        actualizar_calendario_grupos()
        mostrar_tabla_posiciones()
      }
    })
   /* val menuAN=new JMenuItem()
    menuAN.setText("Anular Partido")
    menuAN.addActionListener((e: ActionEvent) => {
      println("Grupos")
    })*/
    val menu=new JPopupMenu()
    menu.add(menuAM)
    menu.add(menuAF)
    //menu.add(menuAN)
    vista.jgdTablaPartidosGrupos.setComponentPopupMenu(menu)
    vista.jgdTablaPartidosGrupos.addMouseListener(new java.awt.event.MouseAdapter() {
      override def mouseClicked(evt:java.awt.event.MouseEvent ) {
      }
    })
  }
  def menuContextualOctavos(): Unit ={
    val menuAF=new JMenuItem()
    menuAF.setText("Actualizar Fecha")
    menuAF.addActionListener((e: ActionEvent) => {
      if(actualizar_fecha_superior(vista.jgdTablaPartidosOctavos,0)){//si se actualizó la fecha
        admin.actualizar_lista_partidos_superior(0)
        actualizar_calendario_superior(0,vista.jgdTablaPartidosOctavos,vista.btnDefinirFaseOctavos,vista.btnDefinirFechasOctavos)
      }
    })
    val menuAM=new JMenuItem()
    menuAM.setText("Actualizar Marcador")
    menuAM.addActionListener((e: ActionEvent) => {
      if(actualizar_marcador_superior(vista.jgdTablaPartidosOctavos,0)){
        admin.actualizar_lista_partidos_superior(0)
        actualizar_calendario_superior(0,vista.jgdTablaPartidosOctavos,vista.btnDefinirFaseOctavos,vista.btnDefinirFechasOctavos)
      }
    })
    /*val menuAN=new JMenuItem()
    menuAN.setText("Anular Partido")
    menuAN.addActionListener((e: ActionEvent) => {

    })*/
    val menu=new JPopupMenu()
    menu.add(menuAM)
    menu.add(menuAF)
    //menu.add(menuAN)
    vista.jgdTablaPartidosOctavos.setComponentPopupMenu(menu)
    vista.jgdTablaPartidosOctavos.addMouseListener(new java.awt.event.MouseAdapter() {
      override def mouseClicked(evt:java.awt.event.MouseEvent ) {
      }
    })
  }
  def menuContextualCuartos(): Unit ={
    val menuAF=new JMenuItem()
    menuAF.setText("Actualizar Fecha")
    menuAF.addActionListener((e: ActionEvent) => {
      if(actualizar_fecha_superior(vista.jgdTablaPartidosCuartos,1)){//si se actualizó la fecha
        admin.actualizar_lista_partidos_superior(1)
        actualizar_calendario_superior(1,vista.jgdTablaPartidosCuartos,vista.btnDefinirFaseCuartos,vista.btnDefinirFechasCuartos)
      }
    })
    val menuAM=new JMenuItem()
    menuAM.setText("Actualizar Marcador")
    menuAM.addActionListener((e: ActionEvent) => {
      if(actualizar_marcador_superior(vista.jgdTablaPartidosCuartos,1)){
        admin.actualizar_lista_partidos_superior(1)
        actualizar_calendario_superior(1,vista.jgdTablaPartidosCuartos,vista.btnDefinirFaseCuartos,vista.btnDefinirFechasCuartos)
      }
    })
    /*val menuAN=new JMenuItem()
    menuAN.setText("Anular Partido")
    menuAN.addActionListener((e: ActionEvent) => {
    })*/
    val menu=new JPopupMenu()
    menu.add(menuAM)
    menu.add(menuAF)
   // menu.add(menuAN)
    vista.jgdTablaPartidosCuartos.setComponentPopupMenu(menu)
    vista.jgdTablaPartidosCuartos.addMouseListener(new java.awt.event.MouseAdapter() {
      override def mouseClicked(evt:java.awt.event.MouseEvent ) {
      }
    })
  }
  def menuContextualSemifinales(): Unit ={
    val menuAF=new JMenuItem()
    menuAF.setText("Actualizar Fecha")
    menuAF.addActionListener((e: ActionEvent) => {
      if(actualizar_fecha_superior(vista.jgdTablaPartidosSemifinales,2)){//si se actualizó la fecha
        admin.actualizar_lista_partidos_superior(2)
        actualizar_calendario_superior(2,vista.jgdTablaPartidosSemifinales,vista.btnDefinirFaseSemifinales,vista.btnDefinirFechasSemifinales)
      }
    })
    val menuAM=new JMenuItem()
    menuAM.setText("Actualizar Marcador")
    menuAM.addActionListener((e: ActionEvent) => {
      if(actualizar_marcador_superior(vista.jgdTablaPartidosSemifinales,2)){
        admin.actualizar_lista_partidos_superior(2)
        actualizar_calendario_superior(2,vista.jgdTablaPartidosSemifinales,vista.btnDefinirFaseSemifinales,vista.btnDefinirFechasSemifinales)
      }
    })
    /*val menuAN=new JMenuItem()
    menuAN.setText("Anular Partido")
    menuAN.addActionListener((e: ActionEvent) => {

    })*/
    val menu=new JPopupMenu()
    menu.add(menuAM)
    menu.add(menuAF)
    //menu.add(menuAN)
    vista.jgdTablaPartidosSemifinales.setComponentPopupMenu(menu)
    vista.jgdTablaPartidosSemifinales.addMouseListener(new java.awt.event.MouseAdapter() {
      override def mouseClicked(evt:java.awt.event.MouseEvent ) {
      }
    })
  }
  def menuContextualFinales(): Unit ={
    val menuAF=new JMenuItem()
    menuAF.setText("Actualizar Fecha")
    menuAF.addActionListener((e: ActionEvent) => {
      if(actualizar_fecha_superior(vista.jgdTablaPartidosFinales,3)){//si se actualizó la fecha
        admin.actualizar_lista_partidos_superior(3)
        actualizar_calendario_superior(3,vista.jgdTablaPartidosFinales,vista.btnDefinirFaseFinales,vista.btnDefinirFechasFinales)
      }
    })
    val menuAM=new JMenuItem()
    menuAM.setText("Actualizar Marcador")
    menuAM.addActionListener((e: ActionEvent) => {
      if(actualizar_marcador_superior(vista.jgdTablaPartidosFinales,3)){
        admin.actualizar_lista_partidos_superior(3)
        actualizar_calendario_superior(3,vista.jgdTablaPartidosFinales,vista.btnDefinirFaseFinales,vista.btnDefinirFechasFinales)
      }
    })
    /*val menuAN=new JMenuItem()
    menuAN.setText("Anular Partido")
    menuAN.addActionListener((e: ActionEvent) => {
      println("Finales")
    })*/
    val menu=new JPopupMenu()
    menu.add(menuAM)
    menu.add(menuAF)
    //menu.add(menuAN)
    vista.jgdTablaPartidosFinales.setComponentPopupMenu(menu)
    vista.jgdTablaPartidosFinales.addMouseListener(new java.awt.event.MouseAdapter() {
      override def mouseClicked(evt:java.awt.event.MouseEvent ) {
      }
    })
  }


  override def actionPerformed(e: ActionEvent): Unit = {
    if(e.getSource==vista.btnAsignar) generar_partidos_grupos()
    else if(e.getSource==vista.cmbGrupos) actualizar_calendario_grupos()
    else if(e.getSource==vista.cmbGruposTablaPosiciones) mostrar_tabla_posiciones()
    else if(e.getSource==vista.btnDefinirFechas) { //Definir fechas de la fase de grupos
      val flag = definir_fechas(admin.obtener_partidos_grupo(vista.cmbGrupos.getSelectedItem.toString).toList) //definicion de fechas de fase de grupos
      if (flag == true) { //Correcto
        admin.actualizar_lista_partidos_grupos() //actualización de partidos de grupos
        val grupo = admin.obtener_grupo_codigo(vista.cmbGrupos.getSelectedItem.toString) //grupo específico
        grupo.fechas_definidas = true //definición de fechas de grupo
        admin.actualizar_lista_grupos(null) //actualización de lista de grupos
        JOptionPane.showMessageDialog(null, "Las fechas de los enfrentamientos del grupo se han definido", "Definición éxitosa", JOptionPane.INFORMATION_MESSAGE)
        vista.btnDefinirFechas.setEnabled(false)
      }
    }
    else if(e.getSource==vista.btnDefinirMarcadores) {
      val flag = definir_marcadores(admin.obtener_partidos_grupo(vista.cmbGrupos.getSelectedItem.toString).toList) //definicion de fechas de fase de grupos
      if (flag == true) { //Correcto
        admin.actualizar_lista_partidos_grupos()
        val grupo = admin.obtener_grupo_codigo(vista.cmbGrupos.getSelectedItem.toString) //grupo específico
        grupo.definido = true //definición de grupo
        admin.actualizar_lista_grupos(null) ///actualización de lista de grupos
        JOptionPane.showMessageDialog(null, "Los marcadores de los enfrentamientos del grupo se han definido", "Definición éxitosa", JOptionPane.INFORMATION_MESSAGE)
        vista.btnDefinirMarcadores.setEnabled(false)
        admin.generar_partidos_octavos() //generación de partidos de octavos de final
        mostrar_en_tabla_partidos(vista.jgdTablaPartidosOctavos, admin.obtener_lista_partidos_superiores(0).values.toList) //mostrar partidos de octavos
      }
    }
    else if(e.getSource==vista.btnDefinirFechasOctavos){
      val flag = definir_fechas(admin.obtener_lista_partidos_superiores(0).values.toList) //definición de fechas
      if (flag) { //Correcto
        admin.actualizar_lista_partidos_superior(0) //actualización de partidos de octavos
        JOptionPane.showMessageDialog(null, "Las fechas de los enfrentamientos se han definido", "Definición éxitosa", JOptionPane.INFORMATION_MESSAGE)
        vista.btnDefinirFechasOctavos.setEnabled(false)
      }
    }
    else if(e.getSource==vista.btnDefinirFaseOctavos) {
      val flag = definir_marcadores(admin.obtener_lista_partidos_superiores(0).values.toList) //definicion de fechas de fase de grupos
      if (flag == true) { //Correcto
        admin.actualizar_lista_partidos_superior(0) //actualización de partidos de octavos
        JOptionPane.showMessageDialog(null, "Los marcadores de los enfrentamientos se han definido", "Definición éxitosa", JOptionPane.INFORMATION_MESSAGE)
        vista.btnDefinirFaseOctavos.setEnabled(false)
        admin.generar_partidos_cuartos() //generación de partidos de octavos de final
        mostrar_en_tabla_partidos(vista.jgdTablaPartidosCuartos, admin.obtener_lista_partidos_superiores(1).values.toList) //mostrar partidos de cuartos
      }
    }
    else if(e.getSource==vista.btnDefinirFechasCuartos){
      val flag = definir_fechas(admin.obtener_lista_partidos_superiores(1).values.toList) //definición de fechas
      if (flag) { //Correcto
        admin.actualizar_lista_partidos_superior(1) //actualización de partidos de octavos
        JOptionPane.showMessageDialog(null, "Las fechas de los enfrentamientos se han definido", "Definición éxitosa", JOptionPane.INFORMATION_MESSAGE)
        vista.btnDefinirFechasCuartos.setEnabled(false)
      }
    }
    else if(e.getSource==vista.btnDefinirFaseCuartos) {
      val flag = definir_marcadores(admin.obtener_lista_partidos_superiores(1).values.toList) //definicion de fechas de fase de grupos
      if (flag == true) { //Correcto
        admin.actualizar_lista_partidos_superior(1) //actualización de partidos de octavos
        JOptionPane.showMessageDialog(null, "Los marcadores de los enfrentamientos se han definido", "Definición éxitosa", JOptionPane.INFORMATION_MESSAGE)
        vista.btnDefinirFaseCuartos.setEnabled(false)
        admin.generar_partidos_semifinales() //generación de partidos de octavos de final
        mostrar_en_tabla_partidos(vista.jgdTablaPartidosSemifinales, admin.obtener_lista_partidos_superiores(2).values.toList) //mostrar partidos de cuartos
      }
    }
    else if(e.getSource==vista.btnDefinirFechasSemifinales){
      val flag = definir_fechas(admin.obtener_lista_partidos_superiores(2).values.toList) //definición de fechas
      if (flag) { //Correcto
        admin.actualizar_lista_partidos_superior(2) //actualización de partidos de octavos
        JOptionPane.showMessageDialog(null, "Las fechas de los enfrentamientos se han definido", "Definición éxitosa", JOptionPane.INFORMATION_MESSAGE)
        vista.btnDefinirFechasSemifinales.setEnabled(false)
      }
    }
    else if(e.getSource==vista.btnDefinirFaseSemifinales) {
      val flag = definir_marcadores(admin.obtener_lista_partidos_superiores(2).values.toList) //definicion de fechas de fase de grupos
      if (flag == true) { //Correcto
        admin.actualizar_lista_partidos_superior(2) //actualización de partidos de octavos
        JOptionPane.showMessageDialog(null, "Los marcadores de los enfrentamientos se han definido", "Definición éxitosa", JOptionPane.INFORMATION_MESSAGE)
        vista.btnDefinirFaseSemifinales.setEnabled(false)
        admin.generar_partidos_finales() //generación de partidos de octavos de final
        mostrar_en_tabla_partidos(vista.jgdTablaPartidosFinales, admin.obtener_lista_partidos_superiores(3).values.toList) //mostrar partidos de cuartos
      }
    }
    else if(e.getSource==vista.btnDefinirFechasFinales){
      val flag = definir_fechas(admin.obtener_lista_partidos_superiores(3).values.toList) //definición de fechas
      if (flag) { //Correcto
        admin.actualizar_lista_partidos_superior(3) //actualización de partidos de octavos
        JOptionPane.showMessageDialog(null, "Las fechas de los enfrentamientos se han definido", "Definición éxitosa", JOptionPane.INFORMATION_MESSAGE)
        vista.btnDefinirFechasFinales.setEnabled(false)
      }
    }
    else if(e.getSource==vista.btnDefinirFaseFinales) {
      val flag = definir_marcadores(admin.obtener_lista_partidos_superiores(3).values.toList) //definicion de fechas de fase de grupos
      if (flag == true) { //Correcto
        admin.actualizar_lista_partidos_superior(3) //actualización de partidos de octavos
        JOptionPane.showMessageDialog(null, "Los marcadores de los enfrentamientos se han definido", "Definición éxitosa", JOptionPane.INFORMATION_MESSAGE)
        vista.btnDefinirFaseFinales.setEnabled(false)
      }
    }
  }
}

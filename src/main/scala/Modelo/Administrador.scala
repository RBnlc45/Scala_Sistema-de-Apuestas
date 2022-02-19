package Modelo
import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}
import scala.collection.mutable.{ListBuffer, Map}

class Administrador extends Serializable{
  import Administrador._
  //Array con los pares lista y nombre de archivo que los contiene
  private val array:Array[String]=Array("octavos","cuartos","semifinales","finales")
  /*SElecciones*/
  def obtener_lista_selecciones(): ListBuffer[Seleccion] ={
    listaSelecciones = recuperar_estructura("src/main/scala/Datos/selecciones.dat").asInstanceOf[ListBuffer[Seleccion]]
    return listaSelecciones
  }
  def actualizar_lista_selecciones(lista:ListBuffer[Seleccion]): Unit ={
    if(lista!=null){
      listaSelecciones=lista
    }
    listaSelecciones.sortBy(_.pais)
    guardar_estructura("src/main/scala/Datos/selecciones.dat",listaSelecciones)
  }
  def obtener_seleccion(seleccion_pais:String): Seleccion ={
    try{
      if(listaSelecciones==null) obtener_lista_selecciones()
      var aux=listaSelecciones.filter(_.pais==seleccion_pais)
      return aux(0)
    }
    catch {
      case e:Exception=> return null
    }
  }
  def obtener_selecciones(lista_paises:ListBuffer[String]): ListBuffer[Seleccion] ={
    try{
      obtener_lista_selecciones()
      var aux=listaSelecciones.filter(x=>{lista_paises.contains(x.pais)})
      return aux
    }catch {
      case e:Exception=> return null
    }
  }

  /*Lista de grupos*/
  def obtener_lista_grupos(): ListBuffer[Grupo] ={
    if(listaGrupos==null){
      listaGrupos=recuperar_estructura("src/main/scala/Datos/grupos.dat").asInstanceOf[ListBuffer[Grupo]]
    }
    return listaGrupos
  }
  def obtener_grupo_pais(pais:String): Grupo ={
    try{
      if(listaGrupos==null) obtener_lista_grupos()
      var aux=listaGrupos.filter(x=>{x.selecciones.contains(pais)})
      return aux(0)
    }
    catch {
      case e:Exception=> return null
    }
  }
  def obtener_grupo_codigo(codigo:String): Grupo ={
    try{
      if (listaGrupos==null) obtener_lista_grupos()
      var aux=listaGrupos.filter(x=>{x.codigo_grupo==codigo})
      return aux(0)
    }
    catch {
      case e:Exception=>return null
    }
  }
  def agregar_grupo(grupo:Grupo): Unit ={
    if (listaGrupos==null) obtener_lista_grupos()
    listaGrupos+=grupo
    guardar_estructura("src/main/scala/Datos/grupos.dat",listaGrupos)
  }
  def actualizar_lista_grupos(lista:ListBuffer[Grupo]): Unit ={
    if (lista!=null){
      listaGrupos=lista
    }
    listaGrupos=listaGrupos.sortBy(_.codigo_grupo)
    guardar_estructura("src/main/scala/Datos/grupos.dat",listaGrupos)
  }
  /*Funciones para manipular lista de partidos fase de grupos*/
  def obtener_lista_partidos_grupos(): Map[String,ListBuffer[Partido]] ={
    if (listaPartidosGrupos==null){
      listaPartidosGrupos=recuperar_estructura("src/main/scala/Datos/partidos_grupo.dat").asInstanceOf[Map[String,ListBuffer[Partido]]]
    }
    return listaPartidosGrupos
  }
  def obtener_partidos_grupo_pais(seleccion:String): ListBuffer[Partido] ={
    if(listaPartidosGrupos==null) obtener_lista_partidos_grupos()
    val grupo=obtener_grupo_pais(seleccion)
    var lista_partidos:ListBuffer[Partido]=null
    if(listaPartidosGrupos.contains(grupo.codigo_grupo)) lista_partidos=listaPartidosGrupos.get(grupo.codigo_grupo).get
    if (lista_partidos==null)  lista_partidos=ListBuffer()
    val aux=lista_partidos.filter(x=>{x.selecciones.contains(seleccion)})
    return aux
  }
  def obtener_partidos_grupo(codigo_grupo:String): ListBuffer[Partido] ={
    val grupo=obtener_grupo_codigo(codigo_grupo)
    if (grupo==null) return null
    if (listaPartidosGrupos == null) obtener_lista_partidos_grupos()
    val lista_partidos=listaPartidosGrupos.get(grupo.codigo_grupo)
    if(lista_partidos!=None) return lista_partidos.get
    else return null
  }
  def obtener_partido_grupo(partido:Partido): Partido ={
    obtener_lista_partidos_grupos()
    val aux:ListBuffer[ListBuffer[Partido]]=ListBuffer()
    for(g<-listaPartidosGrupos.values){
      if(g.contains(partido)){aux+=g}
    }
    val aux2=aux(0)
    return aux2(aux2.indexOf(partido))
  }
  def agregar_partidos_grupos(partidos:ListBuffer[Partido],grupo:String): Unit ={
    if (listaPartidosGrupos==null) obtener_lista_partidos_grupos()
    var a=List(grupo) zip List(partidos)
    listaPartidosGrupos.update(a(0)._1,a(0)._2)
    guardar_estructura("src/main/scala/Datos/partidos_grupo.dat",listaPartidosGrupos)
  }
  def actualizar_lista_partidos_grupos(): Unit ={
    guardar_estructura("src/main/scala/Datos/partidos_grupo.dat", listaPartidosGrupos)
  }

  /*Partidos de fases superiores*/
  def obtener_lista_partidos_superiores(index:Int):Map[String,PartidoEliminatorio]={
    var path="src/main/scala/Datos/partidos_%s.dat".format(array(index))
    index match {
      case 0 =>
        listaPartidosOctavos=recuperar_estructura(path).asInstanceOf[Map[String,PartidoEliminatorio]]
        return listaPartidosOctavos
      case 1 =>
        listaPartidosCuartos=recuperar_estructura(path).asInstanceOf[Map[String,PartidoEliminatorio]]
        return listaPartidosCuartos
      case 2 =>
        listaPartidosSemifinales=recuperar_estructura(path).asInstanceOf[Map[String,PartidoEliminatorio]]
        return listaPartidosSemifinales
      case 3 =>
        listaPartidosFinales=recuperar_estructura(path).asInstanceOf[Map[String,PartidoEliminatorio]]
        return listaPartidosFinales
    }
  }
  def obtener_partido_fase_superior(index:Int,seleccion1:String,seleccion2:String): PartidoEliminatorio ={
    var partidos:Map[String,PartidoEliminatorio]=null
    index match {
      case 0 =>
        partidos=listaPartidosOctavos
      case 1 =>
        partidos=listaPartidosCuartos
      case 2 =>
        partidos=listaPartidosSemifinales
      case 3 =>
        partidos=listaPartidosFinales
    }
    val listaPartidos=partidos.values.toList
    if(seleccion2==null){//partido de una selección
      val aux=listaPartidos.filter(x=>{x.selecciones.contains(seleccion1)})
      if (aux.size==1) return aux(0)
      else return null
    }
    else{//partido entre dos selecciones
      val aux=listaPartidos.filter(x=>{x.selecciones.contains(seleccion1) && x.selecciones.contains(seleccion2)})
      if (aux.size==1) return aux(0)
      else return null
    }
  }
  def obtener_partido_eliminatorio_codigo(codigo:String,index:Int): PartidoEliminatorio ={
    var partidos: Map[String, PartidoEliminatorio]=null
    index match {
      case 0 =>
        partidos=listaPartidosOctavos
      case 1 =>
        partidos=listaPartidosCuartos
      case 2 =>
        partidos=listaPartidosSemifinales
      case 3 =>
        partidos=listaPartidosFinales
    }
    val listaPartidos=partidos.get(codigo)
    if (listaPartidos!=None) return listaPartidos.get
    else return null
  }
  def actualizar_lista_partidos_superior(index:Int): Unit ={//Se guarda la lista seleccionada
    var partidos: Map[String, PartidoEliminatorio]=null
    index match {
      case 0 =>  partidos=listaPartidosOctavos
      case 1 =>  partidos=listaPartidosCuartos
      case 2 =>  partidos=listaPartidosSemifinales
      case 3 =>  partidos=listaPartidosFinales
    }
    guardar_estructura("src/main/scala/Datos/partidos_%s.dat".format(array(index)),partidos)
  }

  /*Generación de partidos*/
  def generar_partidos_grupos(): Unit ={//se generan los enfrentamientos para la fase de grupos
    /*Actualización de listas necesarias*/
    obtener_lista_grupos()
    obtener_lista_partidos_grupos()
    val grupos_listos:List[String]=listaPartidosGrupos.keys.toList//lista de grupos que ya tienen definidos sus partidos
    if(grupos_listos.size==8) throw new Exception("Ya se han generado los partidos de la fase de grupos.")//Ya se han generado todos los partidos
    //Comprobación de la cantidad de selecciones colocadas en cada grupo
    if(listaGrupos.size==0 || (for(g<-listaGrupos) yield g.selecciones.size==4).contains(false)) throw new Exception("Para generar automáticamente los partidos, todos los grupos deben tener sus 4 selecciones respectivas")
    for (g<-listaGrupos){//recorrido de los grupos
      if(!grupos_listos.contains(g.codigo_grupo)){//si el grupo no se encuentra en los que ya tienen sus partidos definidos
        try{
          agregar_partidos_grupos(g.generar_partidos(),g.codigo_grupo)//se generan los partidos
        }
        catch {//Error: el grupo no tiene las 4 selecciones reglamentarias
          case e:Exception=>throw new Exception("Para generar automáticamente los partidos, todos los grupos deben tener sus 4 selecciones respectivas")
        }
      }
    }
  }
  def generar_partidos_octavos(): Unit ={
    obtener_lista_grupos()
    listaPartidosOctavos=obtener_lista_partidos_superiores(0)
    val grupos_enfrentamientos= (for(i<-0 until 8 by 2) yield listaGrupos(i)) zip (for(i<-1 until 8 by 2) yield listaGrupos(i))
    var definidos=grupos_enfrentamientos.filter(x=>{x._1.definido && x._2.definido})
    val nDefinidos=grupos_enfrentamientos.filter(x=>{(x._1.definido || x._2.definido)&&(!(x._1.definido && x._2.definido))})
    for (p<-nDefinidos){
      val codigo=p._1.codigo_grupo+p._2.codigo_grupo
      var definido=p._1
      if (!definido.definido) definido=p._2
      val clasificados=definido.obtener_clasificados()
      val p_c=List(codigo+"1",codigo+"2") zip List(new PartidoEliminatorio(null,clasificados(0),null),new PartidoEliminatorio(null,clasificados(1),null))
      listaPartidosOctavos.update(p_c(0)._1,p_c(0)._2)
      listaPartidosOctavos.update(p_c(1)._1,p_c(1)._2)
      actualizar_lista_partidos_superior(0)
    }
    for(p<-definidos){
      val codigo=p._1.codigo_grupo+p._2.codigo_grupo
      val c1=p._1.obtener_clasificados()
      val c2=p._2.obtener_clasificados()
      val partido1=obtener_partido_eliminatorio_codigo(codigo+"1",0)
      val partido2=obtener_partido_eliminatorio_codigo(codigo+"2",0)
      if(partido1.selecciones.contains(null) || partido2.selecciones.contains(null)){
        if(p._1.selecciones.contains(partido1.selecciones(0))){
          partido1.selecciones(1)=c2(1)
          partido2.selecciones(1)=c2(0)
        }
        else{
          partido1.selecciones(1)=c1(1)
          partido2.selecciones(1)=c1(0)
        }
      }
    }
    actualizar_lista_partidos_superior(0)
  }
  def generar_partidos_cuartos(): Unit ={
    listaPartidosOctavos=obtener_lista_partidos_superiores(0)
    var aux:ListBuffer[(String,String)]=ListBuffer()
    aux+=Tuple2(listaPartidosOctavos.get("AB1").get.ganador,listaPartidosOctavos.get("CD1").get.ganador)
    aux+=Tuple2(listaPartidosOctavos.get("EF1").get.ganador,listaPartidosOctavos.get("GH1").get.ganador)
    aux+=Tuple2(listaPartidosOctavos.get("AB2").get.ganador,listaPartidosOctavos.get("CD2").get.ganador)
    aux+=Tuple2(listaPartidosOctavos.get("EF2").get.ganador,listaPartidosOctavos.get("GH2").get.ganador)
    var partidos=for(p<-aux) yield new PartidoEliminatorio(null,p._1,p._2)
    var diccionario= List("A","B","C","D") zip partidos
    listaPartidosCuartos=obtener_lista_partidos_superiores(1)
    for (p<-diccionario) listaPartidosCuartos.update(p._1,p._2)
    actualizar_lista_partidos_superior(1)
  }
  def generar_partidos_semifinales(): Unit ={
    listaPartidosCuartos=obtener_lista_partidos_superiores(1)
    var aux:ListBuffer[(String,String)]=ListBuffer()
    aux+=Tuple2(listaPartidosCuartos.get("A").get.ganador,listaPartidosCuartos.get("B").get.ganador)
    aux+=Tuple2(listaPartidosCuartos.get("C").get.ganador,listaPartidosCuartos.get("D").get.ganador)
    var partidos=for(p<-aux) yield new PartidoEliminatorio(null,p._1,p._2)
    var diccionario= List("A","B") zip partidos
    listaPartidosSemifinales=obtener_lista_partidos_superiores(2)
    for (p<-diccionario) listaPartidosSemifinales.update(p._1,p._2)
    actualizar_lista_partidos_superior(2)
  }
  def generar_partidos_finales(): Unit ={
    listaPartidosSemifinales=obtener_lista_partidos_superiores(2)
    val partido1=listaPartidosSemifinales.get("A").get
    val partido2=listaPartidosSemifinales.get("B").get
    val ganador1=partido1.ganador
    val ganador2=partido2.ganador
    val aux:ListBuffer[(String,String)]=ListBuffer()
    aux+=Tuple2(ganador1,ganador2)
    aux+=Tuple2(partido1.selecciones(1-partido1.selecciones.indexOf(ganador1)),partido2.selecciones(1-partido2.selecciones.indexOf(ganador2)))
    val partidos=for(p<-aux) yield new PartidoEliminatorio(null,p._1,p._2)
    val diccionario= List("A","B") zip partidos
    listaPartidosFinales=obtener_lista_partidos_superiores(3)
    for (p<-diccionario) listaPartidosFinales.update(p._1,p._2)
    actualizar_lista_partidos_superior(3)
  }

  /*Pronosticos*/
  def obtener_lista_pronosticos():ListBuffer[Pronostico]={
    if (listaPronosticos==null){
      listaPronosticos=recuperar_estructura("src/main/scala/Datos/pronosticos.dat").asInstanceOf[ListBuffer[Pronostico]]
    }
    return listaPronosticos
  }
  def actualizar_lista_pronosticos(lista:ListBuffer[Pronostico]): Unit ={
    if (lista!=null) listaPronosticos=lista
    listaPronosticos=listaPronosticos.sortBy(_.id.asInstanceOf[Int])
    guardar_estructura("src/main/scala/Datos/pronosticos.dat", listaPronosticos)
  }
  def agregar_pronostico(pronostico:Pronostico): Unit ={
    if(listaPronosticos.size==0) pronostico.id=1
    else pronostico.id=listaPronosticos.last.id.asInstanceOf[Int]+1
    listaPronosticos+=pronostico
    actualizar_lista_pronosticos(null)
  }
  def obtener_pronostico(id:Int):Pronostico={
    try{
      val aux=listaPronosticos.filter(x=>{x.id==id})
      return aux(0)
    }
    catch{ case e:Exception=> return null}
  }
  def obtener_pronosticos(cedula:String): ListBuffer[Pronostico] ={
    try{
      val aux=listaPronosticos.filter(x=>{x.participante==cedula})
      return aux
    }
    catch{case e:Exception=>return null}
  }
  def obtener_partido_pronostico(id_pronostico:Int): Partido ={
    obtener_lista_partidos_grupos()
    obtener_lista_partidos_superiores(0)
    obtener_lista_partidos_superiores(1)
    obtener_lista_partidos_superiores(2)
    obtener_lista_partidos_superiores(3)
    val listaPartidosDisponibles:ListBuffer[Partido]=ListBuffer()
    listaPartidosDisponibles.addAll(for(i<-listaPartidosGrupos.values;j<-i) yield j)
    listaPartidosDisponibles.addAll(listaPartidosOctavos.values)
    listaPartidosDisponibles.addAll(listaPartidosCuartos.values)
    listaPartidosDisponibles.addAll(listaPartidosSemifinales.values)
    listaPartidosDisponibles.addAll(listaPartidosFinales.values)
    try{
      for (p<-listaPartidosDisponibles){
        if(p.pronosticos.contains(id_pronostico)){return p}
      }
      return null
    }
    catch {case e:Exception=>return null}
  }
  /*Lista de participantes*/
  def obtener_lista_participantes(): ListBuffer[Participante] ={
    if(listaParticipantes==null){
      listaParticipantes=recuperar_estructura("src/main/scala/Datos/participantes.dat").asInstanceOf[ListBuffer[Participante]]
    }
    return listaParticipantes
  }
  def actualizar_lista_participantes(): Unit ={
    listaParticipantes=listaParticipantes.sortBy(x=>{x.nombre})
    guardar_estructura("src/main/scala/Datos/participantes.dat",listaParticipantes)
  }
  def obtener_participante(cedula:String): Participante ={
    var aux= obtener_lista_participantes().filter(x=>{x.cedula==cedula})
    if(aux.size==1) return aux(0)
    else return null
  }
  def agregar_participante(participante:Participante): Unit ={
    listaParticipantes+=participante
    listaParticipantes=listaParticipantes.sortBy(x=>{x.nombre})
    guardar_estructura("src/main/scala/Datos/participantes.dat",listaParticipantes)
  }
  /*Funciones para manipular la lista de premios*/
  def obtener_premio(): ListBuffer[Any] ={
    if(premio==null){
      premio=recuperar_estructura("src/main/scala/Datos/premios.dat").asInstanceOf[ListBuffer[Any]]
      if(premio.size == 0){
        premio=ListBuffer(0.0,0.0,ListBuffer())
      }
    }
    return premio
  }
  def premio_agregar_monto(monto:Double): Unit ={
    premio(1)=premio(1).asInstanceOf[Double]+monto
    actualizar_premio()
  }
  def premio_cambiar_precio(precio:Double): Unit ={
    premio(0)=precio
    actualizar_premio()
  }
  def actualizar_premio(): Unit ={
    guardar_estructura("src/main/scala/Datos/premios.dat",premio)
  }
  /**/
  def actualizar_lista_partidos_disponibles(): Unit ={
    actualizar_lista_partidos_grupos()
    actualizar_lista_partidos_superior(0)
    actualizar_lista_partidos_superior(1)
    actualizar_lista_partidos_superior(2)
    actualizar_lista_partidos_superior(3)
  }
  def fase_definida(index:Int): Boolean ={
    var partidos:List[Partido]=null
    index match {
      case 0 =>
       partidos= (for(p<-obtener_lista_partidos_grupos().values;g<-p) yield g).toList
      case 1=>
        partidos=(for(p<-obtener_lista_partidos_superiores(0).values) yield p).toList
      case 2=>
        partidos=(for(p<-obtener_lista_partidos_superiores(1).values) yield p).toList
      case 3=>
        partidos=(for(p<-obtener_lista_partidos_superiores(2).values) yield p).toList
      case 4=>
        partidos=(for(p<-obtener_lista_partidos_superiores(3).values) yield p).toList
    }
    return partidos.filter(x=>{x.partidoDefinido==false})==0
  }
  /*REcuperación y guardado*/
  def recuperar_estructura(archivo:String): AnyRef ={
    var opciones=List("src/main/scala/Datos/partidos_grupo.dat","src/main/scala/Datos/partidos_octavos.dat","src/main/scala/Datos/partidos_cuartos.dat" ,"src/main/scala/Datos/partidos_semifinales.dat" ,"src/main/scala/Datos/partidos_finales.dat")
    try{
      val reader=new ObjectInputStream(new FileInputStream(archivo))
      var ref=reader.readObject
      reader.close()
      if (ref==null){
        if (opciones.contains(archivo)) return Map()
        else return ListBuffer()
      }
      return ref
    }
    catch {
      case e:Exception=>
        if (opciones.contains(archivo)) return Map()
        else return ListBuffer()
    }
  }
  def guardar_estructura(archivo:String,estructura:AnyRef): Unit ={
    val writer=new ObjectOutputStream(new FileOutputStream(archivo))
    writer.writeObject(estructura)
    writer.close()
  }

}
object Administrador{
  private var listaSelecciones:ListBuffer[Seleccion]=null
  private var listaGrupos:ListBuffer[Grupo]=null
  private var listaPartidosGrupos:Map[String,ListBuffer[Partido]]=null
  private var listaPartidosOctavos:Map[String,PartidoEliminatorio]=null
  private var listaPartidosCuartos:Map[String,PartidoEliminatorio]=null
  private var listaPartidosSemifinales:Map[String,PartidoEliminatorio]=null
  private var listaPartidosFinales:Map[String,PartidoEliminatorio]= null
  private var listaParticipantes:ListBuffer[Participante]=null
  private var listaPronosticos:ListBuffer[Pronostico] =null
  var premio:ListBuffer[Any] = null
}

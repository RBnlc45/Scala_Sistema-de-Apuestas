package Modelo

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.collection.mutable.ListBuffer

class Partido(FechaInicio:LocalDateTime, seleccion_1:String,seleccion_2:String) extends Serializable{
  val admin=new Administrador()
  var fechaInicio=FechaInicio
  var selecciones:ListBuffer[String]=ListBuffer(seleccion_1,seleccion_2)
  var marcador:ListBuffer[Int]=null
  var pronosticos:ListBuffer[Int]=ListBuffer()
  var ganador:String=null
  var fechaDefinida=false
  var partidoDefinido=false
  var anulado=false

  def canEqual(other: Any): Boolean = other.isInstanceOf[Partido]
  override def equals(other: Any): Boolean = other match {
    case that: Partido =>
      (that canEqual this) &&
        fechaInicio == that.fechaInicio &&
        selecciones == that.selecciones
    case _ => false
  }
  override def hashCode(): Int = {
    val state = Seq(fechaInicio, selecciones)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  def cambio_marcador(s1:Seleccion,s2:Seleccion,ganador:String): Unit ={
    if (ganador==null){
      s1.puntos=s1.puntos-1
      s2.puntos=s2.puntos-1
    }
    else if (ganador==s1.pais){
      s1.puntos=s1.puntos-3
    }
    else{
      s2.puntos=s2.puntos-3
    }
    s1.goles=s1.goles-this.marcador(0)
    s2.goles=s2.goles-this.marcador(1)
    s1.goles_contra=s1.goles_contra+this.marcador(1)
    s2.goles_contra=s2.goles_contra+this.marcador(0)
  }
  def actualizar_marcador(marcador:ListBuffer[Int]): Unit ={
    var getGanador=(x:Int,y:Int) => if(x > y) selecciones(0) else if(x < y) selecciones(1) else null
    var ganador=getGanador(marcador(0),marcador(1))
    var s1=admin.obtener_seleccion(selecciones(0))
    var s2=admin.obtener_seleccion(selecciones(1))
    if(this.marcador!=null) cambio_marcador(s1,s2,this.ganador)
    if (ganador==null){
      s1.asignar_puntos(1,marcador(0),-marcador(1))
      s2.asignar_puntos(1,marcador(1),-marcador(0))
    }
    else if (ganador==s1.pais){
      s1.asignar_puntos(3,marcador(0),-marcador(1))
      s2.asignar_puntos(0,marcador(1),-marcador(0))
    }
    else{
      s2.asignar_puntos(3,marcador(1),-marcador(0))
      s1.asignar_puntos(0,marcador(0),-marcador(1))
    }
    admin.actualizar_lista_selecciones(null)
    this.marcador=marcador
    this.ganador=ganador
  }
  def calcular_pronosticos(): Unit ={
    /*Actualización de puntajes de los pronósticos asociados*/
    val admin=new Administrador()
    admin.obtener_lista_pronosticos()
    var pronostico=for(p<-this.pronosticos) yield admin.obtener_pronostico(p)
    for(p<-pronostico) p.calcular_puntos(marcador.toList,selecciones.toList,ganador)
    admin.actualizar_lista_pronosticos(null)
  }
  def obtener_valores_mostrar(): List[AnyRef] ={
    val seleccion_2 = admin.obtener_seleccion(selecciones(1))
    val seleccion_1 = admin.obtener_seleccion(selecciones(0))
    var nombre:String=""
    var marcador:String=""
    var fecha:String=""
    if(this.marcador==null) marcador="PD"
    else marcador="%s - %s".format(this.marcador(0),this.marcador(1))
    if(fechaInicio==null) fecha="PD"
    else fecha=fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    if (seleccion_2==null) nombre="PD"
    else nombre=seleccion_2.pais
    return List(fecha,selecciones(0),marcador,nombre)
  }
  def anular_partido(): Unit ={
    admin.obtener_lista_pronosticos()
    val pronosticos_aux=for(p<-this.pronosticos) yield admin.obtener_pronostico(p)
    for(p<-pronosticos_aux){
      p.anular_pronostico()
    }
    this.anulado=true
    admin.actualizar_lista_pronosticos(null)
  }
}

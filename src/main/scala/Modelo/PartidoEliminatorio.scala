package Modelo

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.collection.mutable.ListBuffer

class PartidoEliminatorio(FechaInicio:LocalDateTime, seleccion_1:String,seleccion_2:String) extends Partido(FechaInicio,seleccion_1,seleccion_2){
  var marcador_penales:ListBuffer[Int]=null

  //Actualizacion de marcadores
  def actualizar_marcador(marcador: ListBuffer[Int],marcador_penales:ListBuffer[Int]): Unit = {
    val getGanador=(x:Int,y:Int) => if(x > y) selecciones(0) else if(x < y) selecciones(1) else null
    var marcador_aux:ListBuffer[Int]=marcador//marcador que se actualiza
    if(marcador_penales!=null){//Hubo penales
      marcador_aux=marcador_penales
    }
    this.ganador=getGanador(marcador_aux(0),marcador_aux(1))//obtención del ganador
    this.marcador=marcador//actualizacion de marcador
    this.marcador_penales=marcador_penales//actualizacion de marcador de penales
  }

  override def calcular_pronosticos(): Unit = {
    val admin=new Administrador()
    admin.obtener_lista_pronosticos()
    val pronosticos_aux=for(p<-pronosticos) yield admin.obtener_pronostico(p)
    var ganador:String=null
    if (marcador_penales==null) ganador=this.ganador
    for(p<-pronosticos_aux){
      p.calcular_puntos(this.marcador.toList,this.selecciones.toList,ganador)
    }
    admin.actualizar_lista_pronosticos(null)

  }
  //Valores para mostrar
  override def obtener_valores_mostrar(): List[AnyRef] = {
    var nombre:String=""
    var marcador:String=""
    var marcador_penales:String=""
    var fecha:String=""
    //Marcador final
    if(this.marcador==null) marcador="PD"
    else marcador="%s - %s".format(this.marcador(0),this.marcador(1))
    //Marcador penales
    if(this.marcador_penales==null) marcador_penales="NA"
    else if(this.marcador_penales.contains(null)) marcador_penales="PD"
    else marcador_penales="%s - %s".format(this.marcador_penales(0),this.marcador_penales(1))
    //Fecha
    if(fechaInicio==null) fecha="PD"
    else fecha=fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    nombre=selecciones(1)
    if (nombre==null) nombre="PD"
    return List(fecha,selecciones(0),marcador,nombre,marcador_penales)
  }

}

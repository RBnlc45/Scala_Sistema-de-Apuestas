package Modelo

import java.time.LocalDateTime
import scala.collection.mutable.ListBuffer

class Pronostico(var fecha:LocalDateTime,var participante:String,var marcador_pronosticado:ListBuffer[Int]) extends Serializable{
  var id:Int=0
  var puntos:ListBuffer[Double] = ListBuffer()
  var anulado=false
  var aciertos:ListBuffer[Double]=ListBuffer()
  /*Métodos para comparar dos objetos pronostico por sus atributos*/
  def canEqual(other: Any): Boolean = other.isInstanceOf[Pronostico]
  override def equals(other: Any): Boolean = other match {
    case that: Pronostico =>
      (that canEqual this) &&
        id == that.id
    case _ => false
  }
  override def hashCode(): Int = {
    val state = Seq(id)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
  /*Calculo de puntos*/
  def calcular_puntos(marcador_real:List[Int],selecciones:List[String],ganador:String): Unit ={
    this.puntos = ListBuffer()
    var marcador=marcador_pronosticado
    var getGanador=(x:Int,y:Int) => if(x > y) selecciones(0) else if(x < y) selecciones(1) else null
    var acierto_resultado=(x:Int,y:Int) => if(getGanador(x,y)==ganador) 2 else 0
    var acierto_marcador=(x:Int,y:Int) => if(x==marcador_real(0) && y==marcador_real(1)) 1 else 0
    var acierto_pleno=(x:Int,y:Int) => if(x!=0 && y!=0) 3 else 0
    var acierto_goles=(x:Int,y:Int,z:Int) => if(z!=0 && x+y>3) 1 else 0
    var acierto_pleno_goles=(x:Int,y:Int)=>if(x!=0 && y!=0) 1 else 0
    var acierto_gol_equipo=(x:Int,y:Int,z:Int)=> if (z == 0 && (x == marcador_real(0) || y == marcador_real(1))) 0.5 else 0
    var pego_al_palo=(x:Int,y:Int) => if ((math.abs(x - marcador_real(0))==1 && math.abs(y - marcador_real(1))==0) || (math.abs(y - marcador_real(1))==1 && math.abs(x - marcador_real(0))==0)) 0.5 else 0
    this.puntos+=acierto_resultado(marcador(0), marcador(1))
    this.puntos+=acierto_marcador(marcador(0), marcador(1))
    this.puntos+=acierto_pleno(this.puntos(0).toInt, this.puntos(1).toInt)
    this.puntos+=acierto_goles(marcador(0), marcador(1), this.puntos(1).toInt)
    this.puntos+=acierto_pleno_goles(this.puntos(2).toInt, this.puntos(3).toInt)
    this.puntos+=acierto_gol_equipo(marcador(0), marcador(1), this.puntos(1).toInt)
    this.puntos+=pego_al_palo(marcador(0), marcador(1))
    val ref_participante= new Administrador().obtener_participante(this.participante)
    this.aciertos=desgloce_aciertos()
    ref_participante.agregar_puntos(this.aciertos.toList)
    new Administrador().actualizar_lista_participantes()
    new Administrador().actualizar_lista_pronosticos(null)
  }
  def anular_pronostico(): Unit ={
    val admin=new Administrador()
    admin.obtener_lista_participantes()
    admin.obtener_lista_pronosticos()
    val participante=admin.obtener_participante(this.participante)
    participante.agregar_puntos((aciertos.map(x=>{-x})).toList)//se restan los puntos
    puntos=ListBuffer(0,0,0,0,0,0,0)
    anulado=true
    admin.actualizar_lista_participantes()
    admin.actualizar_lista_pronosticos(null)
  }
  def desgloce_aciertos(): ListBuffer[Double] ={
    val desgloce:ListBuffer[Double]=ListBuffer(0,0,0,0,0,0,0,0,0)
    val pt:List[Double]=List(8,6,3,2.5,2,1,0.5)
    desgloce(0)=this.puntos.sum
    val index:Int=pt.indexOf(desgloce(0))
    desgloce(index+1)=1
    desgloce(8)=1
    return desgloce
  }





}


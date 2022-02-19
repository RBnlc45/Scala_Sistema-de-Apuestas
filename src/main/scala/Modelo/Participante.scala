package Modelo

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer}

class Participante(var cedula:String,var nombre:String,var apellido:String,var direccion:String,var telefono:String) extends Serializable{
  var puntos:mutable.Buffer[Double]=ArrayBuffer(0,0,0,0,0,0,0,0,0)
  //(aciertos de 8 puntos, aciertos de 6 puntos, aciertos de 3 puntos, aciertos de 2.5 puntos, aciertos de 2 puntos, aciertos de 1 punto,bonificaciones de 0.5 puntos,numero de pronosticos)

  def canEqual(other: Any): Boolean = other.isInstanceOf[Participante]
  override def equals(other: Any): Boolean = other match {
    case that: Participante =>
      (that canEqual this) &&
        cedula == that.cedula
    case _ => false
  }
  override def hashCode(): Int = {
    val state = Seq(cedula)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
  def agregar_puntos(puntos:List[Double]): Unit ={
    this.puntos=(for(i<-0 until 9) yield this.puntos(i)+puntos(i)).toBuffer
  }
}


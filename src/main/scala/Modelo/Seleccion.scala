package Modelo

class Seleccion (Nombre:String) extends Serializable{
  var pais=Nombre
  var bandera= "src/main/scala/Icons/paises/%s.png".format(Nombre.toLowerCase.trim)
  var puntos:Int=0
  var goles=0
  var goles_contra=0

  /*Comparar objetos por sus nombres*/
  def canEqual(other: Any): Boolean = other.isInstanceOf[Seleccion]
  override def equals(other: Any): Boolean = other match {
    case that: Seleccion =>
      (that canEqual this) &&
        pais.toLowerCase == that.pais.toLowerCase
    case _ => false
  }
  override def hashCode(): Int = {
    val state = Seq(pais)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
  /*Métodos*/
  def asignar_puntos(puntos:Int,goles:Int,goles_contra:Int): Unit ={
    this.puntos=this.puntos+puntos
    this.goles=this.goles+goles
    this.goles_contra=this.goles_contra+goles_contra
  }

}

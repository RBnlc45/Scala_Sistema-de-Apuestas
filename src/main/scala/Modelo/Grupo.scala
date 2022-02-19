package Modelo

import scala.collection.mutable.ListBuffer

class Grupo(codigo:String) extends Serializable{
  var codigo_grupo=codigo
  var selecciones:ListBuffer[String]=ListBuffer()
  var definido:Boolean=false
  var fechas_definidas=false

  def canEqual(other: Any): Boolean = other.isInstanceOf[Grupo]
  override def equals(other: Any): Boolean = other match {
    case that: Grupo =>
      (that canEqual this) &&
        codigo_grupo == that.codigo_grupo
    case _ => false
  }
  override def hashCode(): Int = {
    val state = Seq(codigo_grupo)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
  def generar_partidos(): ListBuffer[Partido] ={
    if(selecciones.length==4){
      var ls:ListBuffer[(String,String)]=ListBuffer()
      for(p<-selecciones;q<-selecciones){
        if(p!=q) ls += Tuple2(p,q)
      }
      val ls_aux=ls.clone()
      for(p<-ls_aux){
        if(ls.contains((p._1,p._2))){
          ls-=Tuple2(p._2,p._1)
        }
      }
      val partidos_grupo=for(p<-ls) yield new Partido(null,p._1,p._2)
      return partidos_grupo
    }
    else{
      throw new Exception()
    }
  }
  def obtener_clasificados(): List[String] ={
    var selecciones=new Administrador().obtener_selecciones(this.selecciones)
    selecciones=selecciones.sortBy(x=>{(x.puntos,x.goles,x.goles_contra)}).reverse
    return List(selecciones(0).pais,selecciones(1).pais)
  }
}

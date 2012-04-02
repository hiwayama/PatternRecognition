package main.scala

import scala.util.parsing.json.JSON
import scala.io.Source

object NumberPattern {

    val jsonFileName = "./src/number.json"

    val stream:String = Source.fromFile(jsonFileName).getLines().toList.foldLeft(new StringBuilder("")){(s,a)=>s.append(a+"\n")}.toString

    //IntがDoubleに自動的に変換される
    val pattern = JSON.parseFull(stream) match {
        case p:Some[Map[String,List[Double]]] => p.get
        case _ => Map.empty[String,List[Double]]
    }

    def hammingDist(pattern1:List[Double],pattern2:List[Double]):Int={
        pattern1.zip(pattern2).filter(p=>p._1 != p._2).size
    }

}

//idを25桁2進数に変換しパターン化するクラス
class NumberPattern(id:Int){
    val maxPlace = 25
    require(Math.pow(2,maxPlace).toInt>id)
    val pattern = toBin 
    
    def toBin:Array[Double]={
        (0 until maxPlace).reverse.map(m=>(id/Math.pow(2,m).toInt)%2.toDouble).toArray
    }


}
// vim: set ts=4 sw=4 et:

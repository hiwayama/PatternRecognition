package main.scala

import scala.util.parsing.json.JSON
import scala.io.Source

class NumberPattern {

    val jsonFileName = "./src/number.json"

    val stream:String = Source.fromFile(jsonFileName).getLines().toList.foldLeft(new StringBuilder("")){(s,a)=>s.append(a+"\n")}.toString

    val pattern = JSON.parseFull(stream) match {
        case p:Some[Map[String,List[Double]]] => p.get
    }

    def hammingDist(pattern1:List[Double],pattern2:List[Double]):Int={
        pattern1.zip(pattern2).filter(p=>p._1 != p._2).size
    }

}

// vim: set ts=4 sw=4 et:

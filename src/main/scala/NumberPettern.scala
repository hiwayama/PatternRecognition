package scala.main

import scala.util.parsing.json.JSON
import scala.io.Source

object NumberPattern {
    val jsonFileName = "./src/number.json"

    type Pattern = List[Int]

    val r = new scala.util.Random

    val stream:String = Source.fromFile(jsonFileName).getLines().toList.foldLeft(new StringBuilder("")){(s,a)=>s.append(a+"\n")}.toString

    //IntがDoubleに自動的に変換される
    val obj = JSON.parseFull(stream) match {
        case p:Some[Map[String,List[Double]]] => p.get
        case _ => Map.empty[String,List[Double]]
    }
    
    //Intに変換
    val pattern:Map[String,Pattern] = obj.map(m=>m._1->m._2.map(d=>d.toInt))

    //桁数の上限
    val maxPlace = pattern("0").size

    //ハミング距離の計算メソッド
    def hammingDist(pattern1:Pattern,pattern2:Pattern):Int={
        pattern1.zip(pattern2).filter(p=>p._1 != p._2).size
    }

    
    //10進数を対応する25桁2進パターンに変換するメソッド
    def toBin(id:Int):Pattern={
        require(Math.pow(2,maxPlace).toInt>id)
        (0 until maxPlace).reverse.map(m=>(id/Math.pow(2,m).toInt)%2).toList
    }

    //パターンを乱数で発生させるメソッド
    def createRandomPattern(q:Double):Pattern={
        (0 until maxPlace).map(i=> if(q>r.nextDouble) 1 else 0).toList
    }

    //パターンを確率q/maxPlaceで変動させるメソッド
    def toRandomInvert(pattern:Pattern,q:Double):Pattern={
        pattern.map(p=>if(q/(maxPlace.toDouble)>r.nextDouble) p ^ 1 else p)
    }

}

// vim: set ts=4 sw=4 et:

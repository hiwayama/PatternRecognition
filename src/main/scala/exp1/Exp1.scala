package main.scala.exp1

import main.scala.NumberPattern

object Exp1 {

    //0~9間の10C2の組合せのハミング距離を出力
    val np = NumberPattern
    
    val results = (0 to 9).map(n=>(n,np.pattern(n.toString))).combinations(2).map(l=>(l(0)._1,l(1)._1,np.hammingDist(l(0)._2,l(1)._2))).toSeq
    
    //ハミング距離が最大・最小の組み合わせを表示
    val max = results.maxBy(_._3)
    println("max:%d (%d-%d)".format(max._3,max._1,max._2))
    val min = results.minBy(_._3)
    println("min:%d (%d-%d)".format(min._3,min._1,min._2))

    def main(args:Array[String]){} 
}

// vim: set ts=4 sw=4 et:

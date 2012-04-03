package scala.main.exp1

import scala.main.NumberPattern
import scala.collection.mutable.Map

object Exp1 {

    //0~9間の10C2の組合せのハミング距離を出力
    val np = NumberPattern
    
    val results = (0 to 9).map(n=>(n,np.pattern(n.toString))).combinations(2).map(l=>(l(0)._1,l(1)._1,np.hammingDist(l(0)._2,l(1)._2))).toList
    

    def main(args:Array[String]){
        println("1 (1)")
        //ハミング距離が最大・最小の組み合わせを表示
        val max = results.maxBy(_._3)
        println("max:%d (%d-%d)".format(max._3,max._1,max._2))
        val min = results.minBy(_._3)
        println("min:%d (%d-%d)".format(min._3,min._1,min._2))
    
        //ハミング距離のヒストグラムを出力
        println("--histgram--")
        toHist(results.map(_._3)).toSeq.sortWith(_._1 < _._1)foreach(s=>println(s._1+":"+s._2))

        println("------------")
        
        println("1 (2)")
        

        

    } 

    //ヒストグラム作成メソッド
    def toHist(lists:List[Int]):Map[Int,Int]={
        val map = Map[Int,Int]()
        lists.foreach(l=>map(l)=map.getOrElse(l,0)+1)
        map
    }
}

// vim: set ts=4 sw=4 et:

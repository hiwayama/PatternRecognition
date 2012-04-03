package scala.main.exp1

import scala.main.NumberPattern

object Exp1 {

    val r = new scala.util.Random 
    val np = NumberPattern
        
    def exp11 {
        //0~9間の10C2の組合せのハミング距離
        val results = (0 to 9).map(n=>(n,np.pattern(n.toString))).combinations(2).map(l=>(l(0)._1,l(1)._1,np.hammingDist(l(0)._2,l(1)._2))).toList
     
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
        
    }

    /**
    * "2"の各成分を確率p/25で反転した場合のパターンベクトルについて,
    * 100個中"2"と正しく判定される割合を出力
    */
    def exp12A {
        val answer = "2"
        val results = (0 to np.maxPlace).map(q=>
            (q,(0 until 100).filter(i=>
                nearestNeighborRule(np.toRandomInvert(np.pattern(answer),q))==answer
            ).size)
        )
        results.foreach(println)
    }

    //乱数で生成したパターンベクトルについて,100個中"2"と正しく判定される割合を出力
    def exp12B {
        val answer = "2"
        val results = (0 to 10).map(i=>i/10.0).map(q=>
            (q,(0 until 100).filter(i=>
                nearestNeighborRule(np.createRandomPattern(q)) == answer
            ).size)
        )
        results.foreach(println)
    }

    def main(args:Array[String]){
        exp11

        exp12A
        println("-------")
        exp12B
    } 

    //ヒストグラム作成メソッド
    def toHist(lists:List[Int]):Map[Int,Int]={
        val map = scala.collection.mutable.Map[Int,Int]()
        lists.foreach(l=>map(l)=map.getOrElse(l,0)+1)
        map.toMap
    }

    //0~9の各パターンとのハミング距離を比較し,最短なパターンを示す文字列を返すメソッド
    def nearestNeighborRule(pattern:List[Int]):String={
        (0 to 9).map(i=>i.toString).minBy(s=>np.hammingDist(pattern,np.pattern(s)))
    }
}

// vim: set ts=4 sw=4 et:

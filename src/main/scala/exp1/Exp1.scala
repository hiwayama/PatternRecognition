package main.scala

object Exp1 {

    //数字間のハミング距離を出力
    val np = new NumberPattern

    (0 to 9).map(n=>np.pattern(n.toString)).combinations(2).foreach(l=>println(np.hammingDist(l(0),l(1))))


    def main(args:Array[String]){
        
    } 
}

// vim: set ts=4 sw=4 et:

package scala.main.exp4

import scala.main.{Img,Distribution}

object Exp4 extends Distribution {
    
    def main(args:Array[String]){
        val dists = imgs.map(toDensityDist)

        println(calcKLDivergence(dists(0),dists(1)))
        println(calcKLDivergence(dists(1),dists(0)))
        println(calcKLDivergence(dists(0),dists(2)))
        println(calcKLDivergence(dists(1),dists(2)))
    }

}

// vim: set ts=4 sw=4 et:

package scala.main.exp4

import scala.main.Img

object Exp4 {
    val imgs = List(new Img("./src/CIMG0209.pgm")
        ,new Img("./src/CIMG0210.pgm")
        ,new Img("./src/CIMG0211.pgm"))

    def main(args:Array[String]){
        val dists = imgs.map(toDensityDist)
        println(calcKLDivergence(dists(0),dists(1)))
        println(calcKLDivergence(dists(1),dists(0)))
        println(calcKLDivergence(dists(0),dists(2)))
        println(calcKLDivergence(dists(1),dists(2)))
    }

    //濃度分布への変換
    //keyでソートしたSeqを返す
    def toDensityDist(img:Img):Seq[(Double,Double)]={
        val grad = 256.0//階調値
        val size = img.x*img.y
        val hist = img.createHistgram
        val kMax = hist.maxBy(_._1)._1
        img.createHistgram.map(h=>
            h._1/grad*kMax -> h._2/size.toDouble*grad/kMax
        ).toSeq.sortWith(_._1 < _._1)
    }

    def calcKLDivergence(dist1:Seq[(Double,Double)],dist2:Seq[(Double,Double)]):Double={
        dist1.zip(dist2).map(d=>d._1._2*StrictMath.log(d._1._2/d._2._2)).sum[Double]
    }
}

// vim: set ts=4 sw=4 et:

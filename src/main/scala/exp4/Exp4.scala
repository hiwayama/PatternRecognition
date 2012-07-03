package scala.main.exp4

import scala.main.Img

object Exp4 {
    val imgs = List(
        new Img("./src/CIMG0209.pgm")
        ,new Img("./src/CIMG0210.pgm")
        ,new Img("./src/CIMG0211.pgm"))

    
    def main(args:Array[String]){
        val dists = imgs.map(toDensityDist)

        outputDensityDist("dist-0.txt",dists(0))
        outputDensityDist("dist-1.txt",dists(1))
        outputDensityDist("dist-2.txt",dists(2))

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
        val kMax = 5 //(分布の実質的な無限大)hist.maxBy(_._1)._1
        img.createHistgram.map(h=>
            h._1/grad*kMax -> h._2/size.toDouble//*grad/kMax
        ).toSeq.sortWith(_._1 < _._1)
    }

    /**
    * 2つの濃度分布からKLダイバージェンスを計算する
    */
    def calcKLDivergence(dist1:Seq[(Double,Double)],dist2:Seq[(Double,Double)]):Double={
        dist1.zip(dist2).map(d=>d._1._2*StrictMath.log(d._1._2/d._2._2)).sum[Double]
    }

    /**
     * ファイル出力
    */
    def outputDensityDist(name:String,dist:Seq[(Double,Double)]){
        val pw = new java.io.PrintWriter("./"+name)
        val stream = dist.foldLeft(new StringBuilder("")){(stream,seq)=>
            stream.append(seq._1+" "+seq._2+"\n")
        }.toString
        pw.write(stream)
        pw.close
    }

}

// vim: set ts=4 sw=4 et:

package scala.main

trait Distribution {

  val imgs = List(new Img("./src/CIMG0209.pgm")
      ,new Img("./src/CIMG0210.pgm")
      ,new Img("./src/CIMG0211.pgm"))

  /**
   * 正規分布関数
   * @param x 入力値
   * @param m 平均
   * @param s 標準偏差
   */
  def normalDist(x:Double,m:Double, s:Double):Double={
    Math.exp(-(x-m)*(x-m)/(2.0*s*s)) / Math.sqrt(2.0*Math.Pi)*s
  }

  def toDensityDist(img:Img):Seq[(Double, Double)] = {
      val grad = 256.0//階調値
      val size = img.x*img.y
      val hist = img.createHistgram
      val kMax = hist.maxBy(_._1)._1
      img.createHistgram.map(h=>
          h._1/grad*kMax -> h._2/size.toDouble*grad/kMax
      ).toSeq.sortWith(_._1 < _._1)
  }

  def calcKLDivergence(dist1:Seq[(Double,Double)], 
                        dist2:Seq[(Double,Double)]):Double={
      dist1.zip(dist2).map(d=>d._1._2*StrictMath.log(d._1._2/d._2._2)).sum[Double]
  }
}

// vim: set ts=4 sw=4 et:


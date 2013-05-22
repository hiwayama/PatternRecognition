package scala.main.exp5

import scala.main.{Img,Distribution}
import scala.collection.mutable.HashMap

object Exp5 extends Distribution {

  val dists = imgs.map(toDensityDist)

  def main(args:Array[String]) {
    // 各画像の濃度分布の計算     
    dists.foreach{dist=>
      var params = fit(dist);
      for(i<- 0 until 100){
        params()
      }
      println(params())
    }
  }


  def normalDist(x:Double,m:Double, s:Double):Double={
    Math.exp(-(x-m)*(x-m)/2.0*s*s) / Math.sqrt(2.0*Math.Pi*s*s)
  }
  
  def mixNormal(x:Double,a:Double, m1:Double, m2:Double, s1:Double,s2:Double):Double={

    a*normalDist(x,m1,s1) + (1-a)*normalDist(x,m2,s2)

  }

  def fit(dist:Seq[(Double,Double)]) = {
    // 値の初期化
    val d = dist
    var (a, m1, m2, s1, s2) = (0.5, 0.0, 1.0, 0.5, 0.5)
    () => {
      //値の更新式
      a += dist.map{p=>
        p._2*normalDist(p._1,m1,s1)*normalDist(p._1,m2,s2) / mixNormal(p._1,a,m1,m2,s1,s2)
      }.sum[Double]
      /*
        s1 += ...
        s2 += ...
        m1 += ...
        m2 += ...
      */
      (a,m1,m2,s1,s2)
    }
  }


}

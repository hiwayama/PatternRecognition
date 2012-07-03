package exp5

import scala.io.Source

object Exp5 {
    
    // 課題4で生成した濃度分布ファイルの名前
    val inputFileNames = Array("dist-", ".txt")
    
    def main(args:Array[String]){
        // 課題4で生成した濃度分布ファイルの読込
        val dists = (0 to 2).map{i=>
            Source.fromFile(inputFileNames(0)+i.toString+inputFileNames(1)).getLines.toList.map{l=>
                 val arr = l.split(" ")
                 (arr(0).toDouble, arr(1).toDouble)
            }
        }

        dists.foreach{dist=>
//            fitting(dist)
        }
    }

    def fitting(dist:Array[(Double,Double)]) {
        // パラメータの初期値を決定
        var (a,m1,m2,sigma1,sigma2) = (0.5,1.0,1.0,1.0,1.0)

        // ループを回してパラメータを更新 
        val loopLimit = 100
        (0 until loopLimit).foreach{n=>
            val newParams = updateParams(dist,a,m1,m2,sigma1,sigma2)
            a = newParams._1
            m1 = newParams._2
            m2 = newParams._3
            sigma1 = newParams._4
            sigma2 = newParams._5
            
        }
        
        // パラメータを出力        
        println("params:"+a+" "+m1+" "+m2+" "+sigma1+" "+sigma2)
    }

    // パラメータの更新    
    def updateParams(dist:Array[(Double,Double)]a:Double,m1:Double,m2:Double,sigma1:Double,sigma2:Double):(Double,Double,Double,Double,Double)={

/*        val newA = a + dist.foldLeft(0.0){(sum,pair)=>
                sum + pair._2*(normDist(pair._1,m1,sigma1)-normDist(pair._1,m2,sigma2))/
        }

        
  */      
    }

    // 正規分布
    def normDist(x:Double,m:Double,sigma:Double):Double={
        StrictMath.exp(-(x-m)*(x-m)/(2.0*sigma*sigma))/StrictMath.sqrt(2.0*StrictMath.PI*sigma*sigma)
    }
}

// vim: set ts=4 sw=4 et:

package scala.main.exp3

import scala.main.Img

/**
* レナ画像のパターンマッチング
*/
object Exp3 {
    val fileName = "./src/lena256.pgm"
    val g = new Img(fileName)

    def main(args:Array[String]){
     
        val t = new Img("./src/pattern25.pgm")    
        
        println(patternRecognition(g,t))
        
    }
    
    /**
    * 原画像とテンプレート画像のマッチングを行い
    * ,相関係数が最大になるidとその相関係数の値を返す
    * @param g 原画像
    * @param t テンプレート画像
    */
    def patternRecognition(g:Img,t:Img):(Int,Double)={
        val range:Seq[Int] = (0 until g.x*g.y).filter(i=>i%(g.x) < g.x-t.x && i/(g.y) < g.y-t.y)

        val M = t.x    
        val results = range.par.map(r=>r -> corr(r%g.x,r/g.y,g,t,M))
        results.maxBy(_._2)
    }

    /**
    * パターン画像生成用メソッド
    * テンプレートが端を超えてしまう場合は,次の段へ移動するので注意
    */
    def createTestPattern(fileName:String,m:Int,n:Int,size:Int){
        val pw = new java.io.PrintWriter("./src/"+fileName)
        
        val header = "p2\n%d %d\n%d\n".format(size,size,255)
        val stream:StringBuilder = g.outputTestPics(m,n,size).foldLeft(new StringBuilder("")){(s,v)=>
            s.append(v.toString+"\n")
        }
        pw.write(header+stream.toString)
        pw.close
    }

    /**
    * @param i,j 原画像の左上の座標
    * @param g 原画像
    * @param t テンプレート
    * @param M テンプレートの幅
    */
    def corr(i:Int,j:Int,g:Img,t:Img,M:Int):Double={
        //2次元化したテンプレートの各座標(0~M-1 * 0~M-1)
        val xyListT = (0 until M).map(l=>(0 until M).map(k=>(k,l))).flatten
        val xyListG = xyListT.map(l=>(l._1+i,l._2+j))
        val tAve  = xyListT.map(xy=>t(xy._1*t.x+xy._2).toDouble).sum[Double]*1/(M*M)//tの平均
        val gAve  = xyListG.map(xy=>g(xy._1*g.x+xy._2).toDouble).sum[Double]*1/(M*M)//gの平均
        val nume = xyListG.map(xy=>g(xy._1*g.x+xy._2)-gAve).zip(xyListT.map(xy=>t(xy._1*t.x+xy._2)-tAve)).map(gt=>gt._1 * gt._2).sum[Double]//分子
        val deno = xyListG.map(xy=>StrictMath.pow(g(xy._1*g.x+xy._2)-gAve,2)).sum[Double] * xyListT.map(xy=>StrictMath.pow(t(xy._1*t.x+xy._2)-tAve,2)).sum[Double]//分母
        if(deno == 0.0) 0.0 else nume/StrictMath.sqrt(deno)
    }
}

// vim: set ts=4 sw=4 et:

package scala.main.exp3

import scala.io.Source

object Exp3 {
    val fileName = "./src/lena256.pgm"
    val g = new Img(fileName)

    def main(args:Array[String]){
        createTestPattern(100,100,25)
     
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
        val results:Seq[(Int,Double)] = range.map(r=>(r , corr(r%g.x,r/g.y,g,t,M)))
        results.maxBy(_._2)
    }

    /**
    * パターン画像生成用メソッド
    */
    def createTestPattern(m:Int,n:Int,size:Int){
        val pw = new java.io.PrintWriter("./src/pattern"+size+".pgm")
        
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

class Img(fileName:String) {
    
    val stream = Source.fromFile(fileName).getLines.toList
        .foldLeft(new StringBuilder){
            (stream,l)=>stream.append(l+" ")
        }.toString.replace("  "," ").replace("  "," ")

    val arrays:Array[String] = stream.split(" ")

    //headerは順に(識別子,x軸の画素数,y軸の画素数,階調数)
    val (header,other) = arrays.splitAt(4)
    val (x,y) = (header(1).toInt,header(2).toInt)
    val pics = other.map(p=>p.toInt)
    
    /**
    * テンプレート画像出力用メソッド
    * @param m,n テンプレート画像に用いる左上の座標
    * @param size テンプレート画像の一辺のサイズ
    */
    def outputTestPics(m:Int,n:Int,size:Int):Array[Int]={
        val range = (m until m+size).map(j=>(n until n+size).map(i=>pics(j*x+i).toInt).toArray).toArray.flatten
        range
    }
    
    def apply(place:Int):Int={
        this.pics(place).toInt
    }
    
}


// vim: set ts=4 sw=4 et:

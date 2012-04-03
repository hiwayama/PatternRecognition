package scala.main.exp3

import scala.io.Source

object Exp3 {

    def main(args:Array[String]){
        new Img("./src/lena256.pgm")
        new Img("./src/pattern3.pgm")
    }

    /**
    * @param i,j テンプレート画像の中心の座標
    * @param g 原画像
    * @param t テンプレート
    * @param M テンプレートの幅
    */
    /*def corr(i:Int,j:Int,g:Img,t:Img,M:Int):Double={
        //2次元化したテンプレートの各座標(0~M-1 * 0~M-1)
        val xyList = (0 until M).map(l=>(0 until M).map(k=>(k,l)))
        val tAve // tの平均
        val gSum //gの移動平均
        val denom //分母

        //テンプレの座標系:中心を0,0
        //原画像の座標系:中心をi,j
        //にして実装
    }*/
}

class Img(fileName:String) {

    val stream = Source.fromFile(fileName).getLines.toList
        .foldLeft(new StringBuilder){
            (stream,l)=>stream.append(l+" ")
        }.toString.replace("  "," ").replace("  "," ")
    val arrays:Array[String] = stream.split(" ")

    //headerは順に(識別子,x軸の画素数,y軸の画素数,階調数)
    val (header,pics) = arrays.splitAt(4)
    val (x,y) = (header(1).toInt,header(2).toInt)
    
    println(x+" "+y)
    println(pics.size)
    //outputTestPics(0,0).foreach(a=>a.foreach(println))

    def outputTestPics(m:Int,n:Int):Array[Array[Int]]={
        val N = 3
        (m until n+N).map(i=>(n until n+N).map(j=>pics(i*x+j).toInt).toArray).toArray
    }

    
}


// vim: set ts=4 sw=4 et:

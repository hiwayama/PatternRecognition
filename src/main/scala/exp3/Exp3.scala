package scala.main.exp3

import scala.io.Source

object Exp3 {

    def main(args:Array[String]){
        val g = new Img("./src/lena256.pgm")
        val t =  new Img("./src/pattern3.pgm")
        //println(corr(0,0,g,t,3))
    
        val range:Seq[Int] = (0 until g.x*g.y).filter(i=>i%(g.x) < g.x-t.x && i/(g.y) < g.y-t.y)

        val M = 3
         
        val results:Seq[(Int,Double)] = range.map(r=>(r , corr(r%g.x,r/g.y,g,t,3)))
        println({val r = results.maxBy(_._2);"id:"+r._1+" corr:"+r._2})
        
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
        //テンプレの座標系:中心を0,0
        //原画像の座標系:中心をi,j
        if(deno == 0.0){
            0.0
        }else{
            nume/StrictMath.sqrt(deno)
        }
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
    val pics = other.map(p=>p.toInt)
    val x = header(1).toInt
    val y = header(2).toInt
    println(x+" "+y)
    println(pics.size)
    //outputTestPics(0,0).foreach(a=>a.foreach(println))

    def outputTestPics(m:Int,n:Int):Array[Array[Int]]={
        val N = 3
        (m until n+N).map(i=>(n until n+N).map(j=>pics(i*x+j).toInt).toArray).toArray
    }
    
    def apply(place:Int):Int={
        this.pics(place).toInt
    }
    
}


// vim: set ts=4 sw=4 et:

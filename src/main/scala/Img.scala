package scala.main

import scala.io.Source

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
        val range = (n until n+size).map(i=>(m until m+size).map(j=>pics(i*x+j).toInt).toArray).toArray.flatten
        range
    }
    
    def apply(place:Int):Int={
        this.pics(place).toInt
    }
    
}



// vim: set ts=4 sw=4 et:

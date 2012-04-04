package scala.main.exp4

import scala.main.Img

object Exp4 {
    val imgs = List(new Img("./src/CIMG0209.pgm")
        ,new Img("./src/CIMG0210.pgm")
        ,new Img("./src/CIMG0211.pgm"))

    def main(args:Array[String]){
        val hists = imgs.map(_.createHistgram)
    }


}

// vim: set ts=4 sw=4 et:

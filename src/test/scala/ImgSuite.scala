import org.scalatest.FunSuite

import scala.main.Img

class ImgSuite extends FunSuite{

    val lena = new Img("./src/lena256.pgm")
	
    test(""" test Img header """){
        assert(lena.x == 256)
        assert(lena.y == 256)
        assert(lena.pics.size == 256*256)
    }
    test(""" test createHist """){
        val img = new Img("./src/pattern3.pgm")
        val hist = img.createHistgram
        assert(hist(160) == 5)
        assert(hist(161) == 4)
    }

}

// vim: set ts=4 sw=4 et:

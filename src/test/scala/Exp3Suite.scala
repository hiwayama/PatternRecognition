import org.scalatest.FunSuite

import scala.main.exp3.{Exp3,Img}

class Exp3Suite extends FunSuite{

	test(""" test Img header """){
        val lena = new Img("./src/lena256.pgm")
        assert(lena.x == 256)
        assert(lena.y == 256)
        assert(lena.pics.size == 256*256)
    }
}

// vim: set ts=4 sw=4 et:

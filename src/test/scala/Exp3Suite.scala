import org.scalatest.FunSuite

import scala.main.exp3.Exp3
import scala.main.Img

class Exp3Suite extends FunSuite{

    val lena = new Img("./src/lena256.pgm")

    test(""" test Pattern Recognition (100,100)~(125,125)"""){
        val (x,y,size) = (100,100,25)
        val testPatternName = "test-pattern25.pgm"
        Exp3.createTestPattern(testPatternName,x,y,size)
        val t = new Img("./src/"+testPatternName)
        assert(Exp3.patternRecognition(lena,t) == (x*lena.x+y,1.0))

    }
    test(""" test Pattern Recognition (100,100)~(110,110)"""){
        val (x,y,size) = (100,100,10)
        val testPatternName = "test-pattern10.pgm"
        Exp3.createTestPattern(testPatternName,x,y,size)
        val t = new Img("./src/"+testPatternName)
        assert(Exp3.patternRecognition(lena,t) == (x*lena.x+y,1.0))
    }
    test(""" test Pattern Recognition (50,30)~(78,58)"""){
        val testPatternName = "test-pattern28.pgm"
        val (x,y,size) = (50,30,28)
        Exp3.createTestPattern(testPatternName,x,y,size)
        val t = new Img("./src/"+testPatternName)
        assert(Exp3.patternRecognition(lena,t) == (x*lena.x+y,1.0))
    }
}

// vim: set ts=4 sw=4 et:

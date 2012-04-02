import org.scalatest.FunSuite
import main.scala.NumberPattern

class NumberPatternSuite extends FunSuite{

    val np = NumberPattern
	test(""" test hamming distance between zero pattern and one pattern """){
        assert(np.hammingDist(np.pattern("0"),np.pattern("1")) == 13)
    }
	test(""" test hamming distance between five pattern and nine pattern """){
        assert(np.hammingDist(np.pattern("0"),np.pattern("8")) == 5)
    }
	test(""" test hamming distance between three pattern and eight pattern """){
        assert(np.hammingDist(np.pattern("3"),np.pattern("8")) == 1)
    }
	test(""" test hamming distance between two pattern and four pattern """){
        assert(np.hammingDist(np.pattern("2"),np.pattern("4")) == 17)
    }

    val npInstance = new NumberPattern(Math.pow(2,25).toInt - 1)

	test(""" create NumberPattern Instance (id=2^25-1) test"""){
        assert(npInstance.toBin.reduceLeft(_ + _) == 25.0)
    }
}

// vim: set ts=4 sw=4 et:

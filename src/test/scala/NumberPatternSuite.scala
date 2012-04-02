import org.scalatest.FunSuite
import main.scala.NumberPattern

class NumberPatterSuite extends FunSuite{

    val np = new NumberPattern
	test(""" test hamming distance between zero pattern and one pattern """){
        assert(np.hammingDist(np.pattern("zero"),np.pattern("one")) == 13)
    }
	test(""" test hamming distance between five pattern and nine pattern """){
        assert(np.hammingDist(np.pattern("zero"),np.pattern("eight")) == 5)
    }
	test(""" test hamming distance between three pattern and eight pattern """){
        assert(np.hammingDist(np.pattern("three"),np.pattern("eight")) == 1)
    }

}

// vim: set ts=4 sw=4 et:

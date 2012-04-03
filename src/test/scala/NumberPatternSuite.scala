import org.scalatest.FunSuite
import scala.main.NumberPattern

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

    /* 25桁時のテスト */
    val maxSize = 25
    
	test(""" NumberPattern toBin method (id=2^25-1) test"""){
        val maxId = Math.pow(2,maxSize).toInt - 1
        assert(np.toBin(maxId).reduceLeft(_ + _) == 25.0)
    }

	test(""" NumberPattern toBin method (id=1) test"""){
        assert(np.toBin(1).reduceLeft(_ + _) == 1.0)
        assert(np.toBin(1).apply(maxSize-1) == 1.0)
    }
}

// vim: set ts=4 sw=4 et:

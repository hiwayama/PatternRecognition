import org.scalatest.FunSuite
import scala.main.exp1.Exp1

class Exp1Suite extends FunSuite{

	test(""" test Exp1.toHist """){
        val resultHist = Exp1.toHist(Exp1.results.map(r => r._3))
        assert(resultHist(17) == 1)
    }
	test(""" test Exp1.toHist 2 """){
        val resultHist2 = Exp1.toHist(List(1,1,1))
        assert(resultHist2(1) == 3)
    }
}

// vim: set ts=4 sw=4 et:

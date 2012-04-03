import org.scalatest.FunSuite
import scala.main.exp1.Exp1
import scala.main.NumberPattern

class Exp1Suite extends FunSuite{

	test(""" test Exp1.toHist 2 """){
        val resultHist2 = Exp1.toHist(List(1,1,1))
        assert(resultHist2(1) == 3)
    }

	test(""" test nearestNeighborRule """){
        assert(Exp1.nearestNeighborRule(NumberPattern.pattern("0")) == "0")
    }
}

// vim: set ts=4 sw=4 et:

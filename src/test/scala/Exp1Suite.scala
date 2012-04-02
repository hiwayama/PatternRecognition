import org.scalatest.FunSuite
import main.scala.exp1.Exp1

class Exp1Suite extends FunSuite{

	test(""" test Exp1.toHist """){
        val resultHist = Exp1.toHist(Exp1.results.map(r => r._3))
        assert(resultHist(17) == 1)
    }
}

// vim: set ts=4 sw=4 et:

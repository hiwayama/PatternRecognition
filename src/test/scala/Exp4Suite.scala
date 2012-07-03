import org.scalatest.FunSuite

import scala.main.exp4.Exp4
import scala.main.Img

class Exp4Suite extends FunSuite{

    val dists = Exp4.imgs.map(Exp4.toDensityDist)

    test(""" test KLDivergence same patterns"""){
        assert(Exp4.calcKLDivergence(dists(0),dists(0)) == 0.0)
        assert(Exp4.calcKLDivergence(dists(1),dists(1)) == 0.0)
        assert(Exp4.calcKLDivergence(dists(2),dists(2)) == 0.0)
    }
}

// vim: set ts=4 sw=4 et:

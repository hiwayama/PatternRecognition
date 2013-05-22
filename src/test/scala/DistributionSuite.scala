import org.scalatest.FunSuite
import scala.main.Distribution

class DistributionSuite extends FunSuite with Distribution{
  test(""" test normalDist """){
    assert(normalDist(100.0,0.0,1.0) < 1.0e-4)
    assert(normalDist(-100.0,0.0,1.0) < 1.0e-4)
    assert(normalDist(10.0,0.0,1.0) < 1.0e-4)
    assert(normalDist(-10.0,0.0,1.0) < 1.0e-4)
    assert( (0 until 20).maxBy{i=> // -2.0 ~ 2.0
        normalDist((i-10.0)/5.0, 0.0, 1.0)
      } == 10 )
  }

}

// vim: set ts=4 sw=4 et:

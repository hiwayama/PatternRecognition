import org.scalatest.FunSuite

import scala.main.Matrix

class MatrixSuite extends FunSuite{

    val testArray1 = new Matrix(Array(Array(1,2),Array(3,4)))
    val testArray2 = new Matrix(Array(Array(3,4),Array(1,2)))
    val resultArray = new Matrix(Array(Array(5.0,8.0),Array(13.0,20.0)))
    
    test(""" test Matrix mat1 * mat2"""){
        assert(resultArray.equals(testArray1 * testArray2))
    }

}

// vim: set ts=4 sw=4 et:

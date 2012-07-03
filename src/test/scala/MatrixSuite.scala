import org.scalatest.FunSuite

import scala.main.Matrix

class MatrixSuite extends FunSuite{

    val test_array1 = new Matrix(Array(Array(1,2),Array(3,4)))
    val test_array2 = new Matrix(Array(Array(3,4),Array(1,2)))
    val result_array = new Matrix(Array(Array(5,8),Array(7,20)))
    test(""" test Matrix mat1 * mat2"""){
        assert(test_array1 * test_array2,result_array)
    }

}

// vim: set ts=4 sw=4 et:

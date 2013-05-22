package scala.main

/**
* 二次元配列に行列演算を追加したラッパークラス
*/
class Matrix(arrays:Array[Array[Double]]) {

    private val elems = arrays
    val col:Int = this.elems.size
    val row:Int = this.elems(0).size

    def *(mat:Matrix):Matrix={
        new Matrix(
            (0 until this.col).map(c=>
                (0 until mat.row).map(r=>
                  (0 until this.row).foldLeft(0.0){(sum,i)=>
                    sum + this(c,i) * mat(i,r)
                  }
                ).toArray 
            ).toArray
        )
    }

    def apply(i:Int,j:Int):Double={
        elems(i)(j)
    }

    def equals(mat:Matrix):Boolean={
      // 行数列数が同じで全要素が一致する
      (this.col == mat.col) &&
      (this.row == mat.row) &&
      (0 until col).map{c=> (0 until row).map{r=>(c,r)}}.flatten.foldLeft(true){(b,indexes) =>
        b && (this(indexes._1,indexes._2)==mat(indexes._1,indexes._2))
      }
    }


    override def toString():String ={
      var str = new StringBuilder("")
      (0 until this.elems.size).foreach{c=>
        (0 until elems(0).size).foreach{r=>
            str.append(this(c,r)+",")
        }
        str.append("\n")
      }
      str.toString
    }

}

// vim: set ts=4 sw=4 et:

package main.scala

class Matrix(arrays:Array[Array[Double]]) {

    private val elems = arrays 
    
    def *(mat:Matrix):Matrix={
        val col = this.elems.size        
        val row = this.elems(0).size

        new Matrix(
            (1 to col).map(c=>
                (1 to row).map(r=>
                    this(c,r)*mat(c,r)
                ).toArray 
            ).toArray
        )
    }

    def apply(i:Int,j:Int):Double={
        elems(i)(j)
    }


}

// vim: set ts=4 sw=4 et:

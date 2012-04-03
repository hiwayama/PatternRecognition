package scala.main.exp2

object Exp2 {
    val N = 2000
    val r = new scala.util.Random

    def main(args:Array[String]){
        
        val learnN = 500//学習回数
        val results = (0 to learnN by 50).map{i=>
            //学習を行ったパーセプロトロン
            val p1:Perceptron = (0 until learnN).foldLeft(new Perceptron(N)){
                (p,j)=>p.update(createRandomVector)
                p
            }

            //未学習のパーセプロトロン
            val p2 = new Perceptron(N)

            //評価集合による食い違いの評価
            val validN = 100//評価ベクトル数
            (i,(0 until validN).filterNot(n=>p1.getY(createRandomVector)==p2.getY(createRandomVector)).size)
        }

        results.foreach(println)
    }


    /**
    * 各要素の二乗はは1になるようなベクトルを乱数で生成
    */
    def createRandomVector:List[Double]={
        val x = (0 until N).map(n=>r.nextDouble*2.0-1.0).toList
        val absX = x.map(d=>Math.pow(d,2)).sum[Double]
        x.map(d=>d/absX)
    }

}

// vim: set ts=4 sw=4 et:

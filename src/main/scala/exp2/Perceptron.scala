package exp2

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

class Perceptron(val N:Int) {
    //パーセプトロンの重みベクトル
    var j = (0 until N).map(n=>1/Math.sqrt(N)).toList

    //パーセプトロンの更新
    def update(x:List[Double]){
        val y = getY(x)
        j = x.zip(j).map(l=>l._1 * y + l._2)
    }

    //重みベクトルの二乗和
    def sumJ:Double={
        j.map(d=>Math.pow(d,2)).sum[Double]
    }

    //出力
    def getY(x:List[Double]):Double={
        sigmoid(x.zip(j).map(l=>l._1*l._2).sum[Double])
    }

    //シグモイド関数
    //0は負に入れたが本来は...？
    def sigmoid(v:Double):Double={
        if(v > 0) 1.0 else -1.0 
    }
}

// vim: set ts=4 sw=4 et:

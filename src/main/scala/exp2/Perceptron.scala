package scala.main.exp2

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

package exp5

object Exp5 {
    
    // 課題4で生成した濃度分布ファイルの名前
    val inputFileNames = ["dist-", ".txt"]
    
    def main(args:Array[String]){
        // 課題4で生成した濃度分布ファイルの読込
        val dists = (0 until 2).map{i=>
            Source.fromFile(inputFileNames[0]+i.toString+fileNames[1]).getLines.toList.foldLeft(new StringBuilder){(stream, l)=>
                stream.append(l+" ")
            }.toString.replace("  "," ").split(" ")
        }

        // パラメータの初期値を決定
        var (a,m1,m2,sigma1,sigma2) = (0.5, 1.0, 1.0, 1.0, 1.0)

        // ループを回してパラメータを更新 
        
    
        
        // パラメータを出力        
    }

    
    def update_params(a,m1,m2,sigma1,sigma2){


    }
}

// vim: set ts=4 sw=4 et:

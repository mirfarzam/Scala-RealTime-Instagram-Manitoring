

import scala.util.parsing.json._

object HelloWorld {


  def SelfDescription (usToken : String) ={

    var userDetail : Map[String,Any] = Map()
    var url = "https://api.instagram.com/v1/users/self/?access_token=" + usToken
    var result = scala.io.Source.fromURL(url).mkString
    val farzam  = JSON.parseFull(result)
    farzam match {
      case Some(e:Map[String,Map[String,String]]) => {
        e.foreach{
          pair => pair._2.foreach {
            innerPair =>
              if (innerPair._1 != "counts") userDetail = userDetail + (innerPair._1 -> innerPair._2)
              else {
                val b = innerPair._2.asInstanceOf[Map[String, String]]
                b.foreach { one => userDetail = userDetail + (one._1 -> one._2) }
              }
          }

        }

      }
      case None => println("Failed.")
    }
    userDetail = userDetail - ("code")
    userDetail.foreach(println)
  }

  def SelfPosts (usToken : String) ={

  }


  def main(args: Array[String]) {
    SelfDescription("145329505.a181a73.c1085882837e49c29c4b987cc0b4fa5a")
  }





}


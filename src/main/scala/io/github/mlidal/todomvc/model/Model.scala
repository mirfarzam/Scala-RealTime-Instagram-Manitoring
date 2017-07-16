package io.github.mlidal.todomvc.model

import scala.util.parsing.json.JSON


class Todo{

  var ListOutput : List[Map[String,Any]] = List()
  var userDetail: Map[String, Any] = Map()

  def SelfPosts(usToken: String) = {

    ListOutput = List.empty[Map[String,Any]]
    var photo: List[Map[String, Any]] = List()
    var tempVar2: Map[String, Any] = Map()

    var url = "https://api.instagram.com/v1/users/self/media/recent/?access_token=" + usToken
    var result = scala.io.Source.fromURL(url).mkString
    var tempVar1 = JSON.parseFull(result)

    tempVar1 match {
      case Some(e: Map[String, List[Map[String, Any]]]) => {
        e.foreach{
          pair=> if(pair._1 == "data")
            photo = pair._2
        }
      }


      case None => println("Failed.")
    }
    var output : Map[String,Any] = Map()
    photo.foreach{
      tempVar1 =>
        output = output + ("mediaId" -> tempVar1("id"))
        if(tempVar1("location") != null)
        {
          var tempVar2 = tempVar1("location").asInstanceOf[Map[String,Any]]
          output = output + ( "latitude" -> tempVar2("latitude"))
          output = output + ("longitude" -> tempVar2("longitude"))
        }
        output = output + ("createdTime" -> tempVar1("created_time"))
        //println(tempVar1("created_time"))
        if(tempVar1("tags") != null)
        {
          output = output + ("tags" -> tempVar1("tags"))
        }
        tempVar2 = tempVar1("likes").asInstanceOf[Map[String,Any]]
        output = output + ("likes" -> tempVar2("count"))
        tempVar2 = tempVar1("caption").asInstanceOf[Map[String,Any]]
        output = output + ("commentId" -> tempVar2("id"))
        output = output + ("commentText" -> tempVar2("text"))
        tempVar2 = tempVar1("images").asInstanceOf[Map[String,Any]]
        tempVar2 = tempVar2("standard_resolution").asInstanceOf[Map[String,Any]]
        output = output + ("mediaUrl" -> tempVar2("url"))
        tempVar2 = tempVar1("user").asInstanceOf[Map[String,Any]]
        output = output + ("userId" -> tempVar2("id"))
        tempVar2 = tempVar1("comments").asInstanceOf[Map[String,Any]]
        output = output + ("comments" -> tempVar2("count"))
        //todos += (counter -> output)
        ListOutput ::= output
    }

    ListOutput.foreach(println)
  }

  def SelfDescription(usToken: String) = {


    var url = "https://api.instagram.com/v1/users/self/?access_token=" + usToken
    var result = scala.io.Source.fromURL(url).mkString
    val tempVar1 = JSON.parseFull(result)
    tempVar1 match {
      case Some(e: Map[String, Map[String, String]]) => {
        e.foreach {
          pair =>
            pair._2.foreach {
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
    val counter : Integer = 1
    userDetail = userDetail - ("code")
    //todos += (counter ->  userDetail)
  }
}

class User{
  var Name = "farzam"
  var Username = "farzam"
  var Password = "1234"
}





package io.github.mlidal.todomvc
import javax.servlet.http.HttpSession

import io.github.mlidal.todomvc.model._
import org.scalatra.ScalatraServlet
import org.scalatra.scalate.ScalateSupport




class ImageControl(todos : Todo) extends ScalatraServlet with ScalateSupport {

  get("/home") {
    contentType = "text/html"
    todos.SelfDescription("145329505.a181a73.c1085882837e49c29c4b987cc0b4fa5a")
    jade("/index" , "todos" -> todos)
  }

  get("/mypictures") {
    contentType = "text/html"
    todos.SelfPosts("145329505.a181a73.c1085882837e49c29c4b987cc0b4fa5a")
    todos.SelfDescription("145329505.a181a73.c1085882837e49c29c4b987cc0b4fa5a")
    jade("/mypicture" , "todos" -> todos)
  }

  get("/:id/delete") {
    //todos.remove(params("id").toInt)
    redirect("/")
  }
}


class UserControl(user : User) extends ScalatraServlet with ScalateSupport {

  get("/login") {
    contentType = "text/html"
    jade("/login")
  }

  post("/loginCheck"){
    var username = params.get("username").get
    var password = params.get("password").get
    if(username == user.Username && password == user.Password)
    {
      val farzam = HttpSession
      farzam.request.getSession()
      //setAttribute("Username", user.Username)
      //setAttribute("Name", user.Name)
      contentType = "text/html"
      redirect("/home")
    }
    else
    {
      contentType = "text/html"
      redirect("/login")
    }
  }


}
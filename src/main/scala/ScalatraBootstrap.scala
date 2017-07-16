import io.github.mlidal.todomvc._
import io.github.mlidal.todomvc.model._
import org.scalatra.LifeCycle
import javax.servlet._



class ScalatraBootstrap extends LifeCycle {

  val todos = new Todo
  val user = new User

  override def init(context: ServletContext) {
    context mount (new ImageControl(todos), "/*")
    context mount (new UserControl(user), "/user")
  }
}

import com.github.mlidal.Dashboard.MyServlet
import com.github.mlidal.Dashboard.model.Todo
import org.scalatra.LifeCycle
import javax.servlet.ServletContext



class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context mount (new MyServlet, "/*")
  }
}
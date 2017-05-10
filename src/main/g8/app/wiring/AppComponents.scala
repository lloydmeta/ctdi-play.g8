package wiring

import play.api.ApplicationLoader.Context
import play.api._
import com.softwaremill.macwire._
import _root_.controllers.Greetings

import router.Routes

class AppComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
    with NoHttpFiltersComponents {

  private implicit def as = actorSystem

  // Controllers
  private lazy val greetings = wire[Greetings]

  // Router
  lazy val router = {
    val routePrefix: String = "/"
    wire[Routes]
  }


}

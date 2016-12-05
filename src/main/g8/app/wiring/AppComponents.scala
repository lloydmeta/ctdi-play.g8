package wiring

import play.api.ApplicationLoader.Context
import play.api._
import com.softwaremill.macwire._
import controllers.Greetings

import router.Routes

class AppComponents(context: Context)
    extends BuiltInComponentsFromContext(context) {

  private implicit def as = actorSystem

  // Controllers
  private lazy val greetings = wire[Greetings]

  // Router
  private lazy val routePrefix: String = "/"
  lazy val router = wire[Routes]

}

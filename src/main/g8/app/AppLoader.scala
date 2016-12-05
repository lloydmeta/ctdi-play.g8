import play.api.ApplicationLoader.Context
import play.api._

import wiring.AppComponents

class AppLoader extends ApplicationLoader {
  def load(context: Context) = {
    LoggerConfigurator(context.environment.classLoader).foreach {
      _.configure(context.environment)
    }
    new AppComponents(context).application
  }
}

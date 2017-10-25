package wiring

import play.api.ApplicationLoader.Context
import play.api._

class AppLoader extends ApplicationLoader {

  def load(context: Context) = {
    val appComponents = new AppComponents(context)
    appComponents.application
  }

}

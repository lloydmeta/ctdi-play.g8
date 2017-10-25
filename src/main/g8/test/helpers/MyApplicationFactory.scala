package helpers

import java.io.File

import org.scalatestplus.play.FakeApplicationFactory
import play.api._
import play.api.inject._
import play.core.DefaultWebCommands
import wiring.AppComponents
import play.api.ApplicationLoader.Context

trait MyApplicationFactory extends FakeApplicationFactory {

  // Override this with something of your choosing that returns perhaps
  // a subclass of AppComponents with a specific component overriden
  def buildComponents(context: Context): AppComponents = new AppComponents(context)

  private[this] var _components: AppComponents = _

  // accessed to get the components in tests
  final def components: AppComponents = _components

  override def fakeApplication: Application = {
    val env           = Environment.simple(new File("."))
    val configuration = Configuration.load(env)
    val context = ApplicationLoader.Context(
      environment = env,
      sourceMapper = None,
      webCommands = new DefaultWebCommands(),
      initialConfiguration = configuration,
      lifecycle = new DefaultApplicationLifecycle()
    )
    val components = buildComponents(context)
    _components = components
    components.application
  }

}

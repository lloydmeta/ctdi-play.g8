package helpers

import java.io.File

import org.scalatestplus.play.FakeApplicationFactory
import play.api._
import play.api.inject._
import play.core.DefaultWebCommands
import wiring.{AppLoader, AppComponents}

trait MyApplicationFactory extends FakeApplicationFactory {

  private var _components: AppComponents = _

  // accessed to get the components in tests
  final def components: AppComponents = _components

  override def fakeApplication: Application = {
    val env = Environment.simple(new File("."))
    val configuration = Configuration.load(env)
    val context = ApplicationLoader.Context(
      environment = env,
      sourceMapper = None,
      webCommands = new DefaultWebCommands(),
      initialConfiguration = configuration,
      lifecycle = new DefaultApplicationLifecycle()
    )
    val loader = new AppLoader()
    // Order matters here
    val application = loader.load(context)
    _components = loader.components
    application
  }

}
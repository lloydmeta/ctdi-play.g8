import helpers.MyApplicationFactory
import org.scalatest.{FunSpec, Matchers}
import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatestplus.play._

class ServerSpec
    extends FunSpec
    with BaseOneServerPerSuite
    with MyApplicationFactory
    with Matchers
    with ScalaFutures
    with IntegrationPatience {

  private lazy val ws               = play.api.test.WsTestClient
  private implicit val implicitPort = port

  describe("/hello/\$name") {
    it("""should respond with "Hello \$name"""") {
      whenReady(ws.wsUrl("/hello/joe").get()) { r =>
        r.body shouldBe "Hello joe"
      }
    }
  }

  describe("/sqrt/\$num") {
    it("""should respond with the proper square root""") {
      whenReady(ws.wsUrl("/sqrt/9").get()) { r =>
        r.body shouldBe "3.0"
      }
    }
  }

}

package controllers

import akka.actor.ActorSystem
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

/**
  * Created by Lloyd on 12/6/16.
  *
  * Copyright 2016
  */
class Greetings(implicit actorSystem: ActorSystem) extends Controller {

  import actorSystem.dispatcher

  // Essentially copied verbatim from the SIRD example
  def hello(to: String) = Action {
    Ok(s"Hello $to")
  }

  /*
     Use Action.async to return a Future result (sqrt can be intense :P)
     Note the use of double(num) to bind only numbers (built-in :)
   */
  def sqrt(num: Double) = Action.async {
    Future {
      Ok(Math.sqrt(num).toString)
    }
  }
}

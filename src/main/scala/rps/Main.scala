package rps

import scala.io.StdIn

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.server._
import akka.http.scaladsl.model._
import de.heikoseeberger.akkahttpcirce.ErrorAccumulatingCirceSupport._
import io.circe.generic.auto._
import io.buildo.enumero.circe._

import Directives._
import StatusCodes._
import model._
import Game._
import Result._

object Main extends App {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  implicit def myRejectionHandler =
    RejectionHandler
      .newBuilder()
      .handleAll[MalformedRequestContentRejection] { rejection =>
        complete(WrongMove())
      }
      .handleNotFound { complete((NotFound, "Not here!")) }
      .result()

  val route =
    post {
      path("playGame") {
        entity(as[GameRequest]) { body =>
          complete(play(body.userMove))
        }
      }
    } ~ options(complete())

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")

  StdIn.readLine()

  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())

}

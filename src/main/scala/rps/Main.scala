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

import wiro.Config
import wiro.server.akkaHttp._

import Directives._
import StatusCodes._
import model._
import Result._
import FailSupport._

object Main extends App with RouterDerivationModule {
  implicit def throwableResponse: ToHttpResponse[Throwable] = null
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val gameRouter = deriveRouter[GameApi](new GameApiImpl)

  val rpcServer = new HttpRPCServer(
    config = Config("localhost", 8080),
    routers = List(gameRouter)
  )
}

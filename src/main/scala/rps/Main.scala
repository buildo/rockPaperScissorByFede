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

import rps.model.error._
import rps.controller._
import rps.service.{GameServiceImpl}
import rps.repository.{GameRepository, InMemoryGameRepository}

object Main extends App with RouterDerivationModule {
  implicit def noGameResponse: ToHttpResponse[NoGameInMemory] =
    (error: NoGameInMemory) =>
      HttpResponse(
        status = NotFound,
        entity = error.message
    )
  implicit def throwableResponse: ToHttpResponse[Throwable] = null
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val gameRepository = InMemoryGameRepository()
  val gameService = GameServiceImpl(gameRepository)

  val gameRouter = deriveRouter[GameApi](GameApiImpl(gameService))

  val rpcServer = new HttpRPCServer(
    config = Config("localhost", 8080),
    routers = List(gameRouter)
  )
}

package rps.controller.GameController

import scala.concurrent.{ExecutionContext, Future}

import rps.model.{GameSummary, Move, Result}
import Move._, Result._
import rps.model.error._
import rps.service.{GameService}
import rps.repository.{InMemoryGameRepository}

import wiro.annotation._

@path("rps")
trait GameApi {
  @query
  def result(): Future[Either[NoGameInMemory, GameSummary]]

  @command
  def play(
      userMove: Move
  ): Future[Either[Throwable, Unit]]
}

class GameApiImpl(implicit ec: ExecutionContext, repo: InMemoryGameRepository) extends GameApi {
  private val service = GameService()

  override def play(userMove: Move): Future[Either[Throwable, Unit]] =
    Future {
      Right(service.playGame(userMove))
    }

  override def result(): Future[Either[NoGameInMemory, GameSummary]] =
    Future {
      repo.getGame() match {
        case None              => Left(NoGameInMemory())
        case Some(gameSummary) => Right(gameSummary)
      }
    }
}

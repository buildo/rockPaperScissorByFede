package rps.controller

import scala.concurrent.{ExecutionContext, Future}

import rps.model.{GameSummary, Move, Result}
import Move._, Result._
import rps.model.error._
import rps.service.{GameService}
import rps.repository.{GameRepository}

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

case class GameApiImpl(
  service: GameService
)(
  implicit
  ec: ExecutionContext,
  repo: GameRepository
) extends GameApi {
  override def play(userMove: Move): Future[Either[Throwable, Unit]] =
    Future {
      Right(service.playGame(userMove))
    }

  override def result(): Future[Either[NoGameInMemory, GameSummary]] =
    Future {
      service.getGame() match {
        case None              => Left(NoGameInMemory())
        case Some(gameSummary) => Right(gameSummary)
      }
    }
}

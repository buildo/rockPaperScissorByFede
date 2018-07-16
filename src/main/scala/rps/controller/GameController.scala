package rps.controller

import rps.model.{GameSummary, Move, Result}
import Move._, Result._
import rps.model.error._
import rps.service.{GameService}
import rps.repository.{GameRepository}
import GameRepository._

import scala.concurrent.{ExecutionContext, Future}

import wiro.annotation._

package GameController {
  @path("rps")
  trait GameApi {
    @query
    def result(): Future[Either[NoGameInMemory, GameSummary]]

    @command
    def play(
        userMove: Move
    ): Future[Either[Throwable, Unit]]
  }

  class GameApiImpl(implicit ec: ExecutionContext) extends GameApi {
    override def play(userMove: Move): Future[Either[Throwable, Unit]] =
      Future {
        Right(GameService.playGame(userMove))
      }

    override def result(): Future[Either[NoGameInMemory, GameSummary]] =
      Future {
        summary.get(1) match {
          case None              => Left(NoGameInMemory())
          case Some(gameSummary) => Right(gameSummary)
        }
      }
  }
}

package rps

import scala.util.Random
import scala.concurrent.{ExecutionContext, Future}
import wiro.server.akkaHttp.ToHttpResponse
import io.buildo.enumero.annotations.enum

import model._
import Move._, Result._

class GameApiImpl(implicit ec: ExecutionContext) extends GameApi {
  def getGameResult(a: Move, b: Move): Result =
    (a, b) match {
      case (a, b) if a == b => Tie
      case (Rock, Paper) | (Paper, Scissors) | (Scissors, Rock) =>
        Won
      case _ => Lost
    }

  def play(userMove: Move): GameSummary = {
    val computerMove = Random.shuffle(Move.values).head
    val result = getGameResult(computerMove, userMove)

    GameSummary(result, computerMove, userMove)
  }

  override def game(userMove: Move): Future[Either[Throwable, GameSummary]] =
    Future {
      Right(play(userMove))
    }
}

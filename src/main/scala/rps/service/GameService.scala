package rps.service

import scala.util.Random

import rps.model.{GameSummary, Move, Result}
import Move._, Result._
import rps.model.error._
import rps.repository.{GameRepository}

trait GameService {
  def playGame(move: Move): Unit;
  def getGame(): Option[GameSummary];
}

case class GameServiceImpl(implicit repo: GameRepository) extends GameService {
  def getGameResult(a: Move, b: Move): Result =
    (a, b) match {
      case (a, b) if a == b => Tie
      case (Rock, Paper) | (Paper, Scissors) | (Scissors, Rock) =>
        Won
      case _ => Lost
    }

  override def playGame(userMove: Move): Unit = {
    val computerMove = Random.shuffle(Move.values).head
    val result = getGameResult(computerMove, userMove)

    repo.updateGame(GameSummary(result, computerMove, userMove))
  }

  override def getGame(): Option[GameSummary] = {
    repo.getGame()
  }
}

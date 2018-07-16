package rps.service

import scala.util.Random

import rps.model.{GameSummary, Move, Result}
import Move._, Result._
import rps.model.error._
import rps.repository.{GameRepository}
import GameRepository._

object GameService {
  def getGameResult(a: Move, b: Move): Result =
    (a, b) match {
      case (a, b) if a == b => Tie
      case (Rock, Paper) | (Paper, Scissors) | (Scissors, Rock) =>
        Won
      case _ => Lost
    }

  def playGame(userMove: Move): Unit = {
    val computerMove = Random.shuffle(Move.values).head
    val result = getGameResult(computerMove, userMove)

    summary.update(1, GameSummary(result, computerMove, userMove))
  }
}

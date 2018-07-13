package rps

import scala.util.Random

import model._
import Move._, Result._
import io.buildo.enumero.annotations.enum

object Game {
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
}

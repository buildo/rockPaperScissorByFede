package rps

import scala.util.Random

import model._
import Move._, Result._, MoveHelpers._
import io.buildo.enumero.annotations.enum

object Game {
  def getGameResult(a: Move, b: Move): Result =
    (a, b) match {
      case (a, b) if a == b => Tie
      case (Rock, Paper) | (Paper, Scissors) | (Scissors, Rock) =>
        Won
      case _ => Lost
    }

  def play(userInput: String): ResponseBody = {
    userInput.convertToMove match {
      case Some(computerMove: Move) => {
        val ourMove = Random.shuffle(List(Rock, Paper, Scissors)).head
        val result = getGameResult(ourMove, computerMove)

        ResponseBody(Some(result), Some(computerMove), Some(ourMove), None)
      }
      case None => {
        ResponseBody(
          None,
          None,
          None,
          Some("this move is not allowed. Allowed moves are 0, 1, 2"))
      }
    }
  }
}

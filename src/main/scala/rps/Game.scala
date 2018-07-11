package rps

import scala.util.Random

object Moves {
  sealed class Move(val id: String, val name: String)

  case object Rock extends Move("0", "Rock")
  case object Paper extends Move("1", "Paper")
  case object Scissors extends Move("2", "Scissors")

  val moves: Set[Move] = Set(Rock, Paper, Scissors)
}

object Results {
  sealed class Result(val message: String)

  case object Won extends Result("you won!")
  case object Lost extends Result("you lost...")
  case object Tie extends Result("it's a tie!")
}

object Game {
  import Moves._, Results._

  def getGameResult(a: Move, b: Move): Result =
    (a, b) match {
      case (a, b) if a == b => Tie
      case (Rock, Paper) | (Paper, Scissors) | (Scissors, Rock) =>
        Won
      case _ => Lost
    }

  def play(): Unit = {
    println("make your move (0 for Rock, 1 for Paper, 2 for Scissors):")

    val userInput = scala.io.StdIn.readLine()

    val maybeUserMove = moves.find(move => move.id == userInput)

    maybeUserMove match {
      case Some(userMove) => {
        val ourMove = Random.shuffle(moves).head

        println(s"you played ${userMove.name}")
        println(s"we played ${ourMove.name}")
        println(getGameResult(ourMove, userMove).message)
      }
      case None =>
        println("this move is not allowed. Allowed moves are 0, 1, 2")
    }
  }
}

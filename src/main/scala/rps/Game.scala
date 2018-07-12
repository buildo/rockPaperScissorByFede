package rps

import scala.util.Random
import model.{Move, Result}
import Move._, Result._

object Game {
  def getGameResult(a: Move)(b: Move): Result =
    (a, b) match {
      case (a, b) if a == b => Tie
      case (Rock, Paper) | (Paper, Scissors) | (Scissors, Rock) =>
        Won
      case _ => Lost
    }

  def play(): Unit = {
    println("make your move (0 for Rock, 1 for Paper, 2 for Scissors):")

    val userInput = scala.io.StdIn.readLine()

    val maybeUserMove = Move.moves.find(move => Move.getId(move) == userInput)

    maybeUserMove match {
      case Some(userMove: Move) => {
        val ourMove = Random.shuffle(Move.moves).head

        println(s"you played ${Move.getName(userMove)}")
        println(s"we played ${Move.getName(ourMove)}")

        val result = getGameResult(ourMove)(userMove)

        println(Result.getMessage(result))
      }
      case None =>
        println("this move is not allowed. Allowed moves are 0, 1, 2")
    }
  }
}

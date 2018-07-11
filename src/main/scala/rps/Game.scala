package rps

import scala.util.Random
import io.buildo.enumero.{CaseEnumIndex, CaseEnumSerialization}

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

  def writeResult(result: Result) = result match {
    case Won  => "you won!"
    case Lost => "you lost..."
    case Tie  => "It's a tie!"
  }

  def play(): Unit = {
    println("make your move (0 for Rock, 1 for Paper, 2 for Scissors):")

    val userInput = scala.io.StdIn.readLine()

    CaseEnumIndex[Move].caseFromIndex(userInput) match {
      case Some(userMove: Move) => {
        val ourMove = CaseEnumIndex[Move]
          .caseFromIndex(Random.shuffle(List("0", "1", "2")).head)
          .get

        println(
          s"you played ${CaseEnumSerialization[Move].caseToString(userMove)}")
        println(
          s"we played ${CaseEnumSerialization[Move].caseToString(ourMove)}")

        val result = getGameResult(ourMove)(userMove)

        println(writeResult(result))
      }
      case None =>
        println("this move is not allowed. Allowed moves are 0, 1, 2")
    }
  }
}

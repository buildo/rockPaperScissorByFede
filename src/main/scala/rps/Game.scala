package rps

object Move extends Enumeration {
  type Move = Value
  val Rock = Value("0")
  val Paper = Value("1")
  val Scissor = Value("2")

  def isOrderType(s: String) = values.exists(_.toString == s)
}

object Result extends Enumeration {
  type Result = Value
  val Won = Value("you won!")
  val Lost = Value("you lost...")
  val Tie = Value("it's a tie!")
}

object Game {
  def getRandomMove(): Move.Move = {
    Move(scala.util.Random.nextInt(Move.maxId))
  }

  def getMoveName(move: Move.Move): String = move match {
    case Move.Rock    => "Rock"
    case Move.Paper   => "Paper"
    case Move.Scissor => "Scissor"
  }

  def getGameResult(a: Move.Move, b: Move.Move): Result.Result =
    (a, b) match {
      case (a, b) if a == b => Result.Tie
      case (Move.Rock, Move.Paper) | (Move.Paper, Move.Scissor) |
          (Move.Scissor, Move.Rock) =>
        Result.Won
      case _ => Result.Lost
    }

  def play(): Unit = {
    println("make your move (0 for Rock, 1 for Paper, 2 for Scissors):")

    val userMove = scala.io.StdIn.readLine()

    Move.isOrderType(userMove) match {
      case true => {
        val userMoveName = getMoveName(Move.withName(userMove))
        val ourMove = getRandomMove()
        val ourMoveName = getMoveName(ourMove)

        println(s"you played $userMoveName")
        println(s"we played $ourMoveName")
        println(getGameResult(ourMove, Move.withName(userMove)))
      }
      case false =>
        println("this move is not allowed. Allowed moves are 0, 1, 2")
    }
  }
}

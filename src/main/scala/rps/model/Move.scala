package rps.model

sealed trait Move
object Move {
  case object Rock extends Move
  case object Paper extends Move
  case object Scissors extends Move

  val moves = Set(Rock, Paper, Scissors)

  def getId(move: Move) = move match {
    case Rock     => "0"
    case Paper    => "1"
    case Scissors => "2"
  }

  def getName(move: Move) = move match {
    case Rock     => "Rock"
    case Paper    => "Paper"
    case Scissors => "Scissors"
  }
}

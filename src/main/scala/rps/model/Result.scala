package rps.model

sealed trait Result
object Result {
  case object Won extends Result
  case object Lost extends Result
  case object Tie extends Result

  def getMessage(result: Result): String = result match {
    case Won  => "you won!"
    case Lost => "you lost..."
    case Tie  => "It's a tie!"
  }
}

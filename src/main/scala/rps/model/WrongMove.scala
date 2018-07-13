package rps.model

case class WrongMove(error: String =
  "This move is not allowed, allowed moves are \"Rock\", \"Paper\", \"Scissors\"")

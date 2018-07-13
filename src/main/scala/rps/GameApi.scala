package rps

import scala.concurrent.Future
import wiro.annotation._

import rps.model.{GameSummary, Move}

@path("play")
trait GameApi {
  @command
  def game(
      userMove: Move
  ): Future[Either[Throwable, GameSummary]]
}

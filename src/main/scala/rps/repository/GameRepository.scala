package rps.repository

import scala.collection.concurrent.TrieMap

import rps.model.{GameSummary}

trait GameRepository {
  def updateGame: GameSummary => Unit
  def getGame: () => Option[GameSummary]
}

case class InMemoryGameRepository() extends GameRepository {
  private val summary: TrieMap[Int, GameSummary] = TrieMap.empty

  override def updateGame: (GameSummary) => Unit = gameSummary => summary.update(1, gameSummary)
  override def getGame: () => Option[GameSummary] = () => summary.get(1)
}

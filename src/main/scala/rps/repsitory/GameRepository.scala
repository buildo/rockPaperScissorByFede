package rps.repository

import scala.collection.concurrent.TrieMap

import rps.model.{GameSummary}

object GameRepository {
  val summary: TrieMap[Int, GameSummary] = TrieMap.empty
}

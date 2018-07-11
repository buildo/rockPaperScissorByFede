package rps.model

import io.buildo.enumero.annotations.indexedEnum

@indexedEnum trait Move {
  type Index = String
  Rock { "0" }
  Paper { "1" }
  Scissors { "2" }
}

package rps.model

import io.buildo.enumero.annotations.enum
import io.buildo.enumero.{CaseEnumSerialization}

@enum trait Move {
  Rock
  Paper
  Scissors
}

object MoveHelpers {
  implicit class ConversionHelper(x: String) {
    def convertToMove(): Option[Move] = {
      CaseEnumSerialization[Move].caseFromString(x)
    }
  }
}

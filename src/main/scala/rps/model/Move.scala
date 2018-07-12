package rps.model

import io.buildo.enumero.annotations.indexedEnum
import io.buildo.enumero.{CaseEnumIndex, CaseEnumSerialization}

@indexedEnum trait Move {
  type Index = String
  Rock { "0" }
  Paper { "1" }
  Scissors { "2" }
}

object MoveHelpers {
  implicit class ConversionHelper(x: String) {
    def convertToMove(): Option[Move] = {
      CaseEnumIndex[Move].caseFromIndex(x)
    }
  }

  implicit class ReverseConversionHelper(x: Move) {
    def convertToString(): String = {
      CaseEnumSerialization[Move].caseToString(x)
    }
  }
}

import rps.service.{GameService}
import rps.model.{Move, Result}
import Move._, Result._
import org.scalatest._

class GameSpec extends FlatSpec {
  "The client" should "win when a winning combo is evaluated" in {
    assert(GameService.getGameResult(Scissors, Rock) === Won)
    assert(GameService.getGameResult(Rock, Paper) === Won)
    assert(GameService.getGameResult(Paper, Scissors) === Won)
  }

  "The client" should "loose when a loosing combo is evaluated" in {
    assert(GameService.getGameResult(Rock, Scissors) === Lost)
    assert(GameService.getGameResult(Paper, Rock) === Lost)
    assert(GameService.getGameResult(Scissors, Paper) === Lost)
  }

  it should "return a tie when client and computer return the same move" in {
    var move = Move

    for (move <- Move.values) {
      assert(GameService.getGameResult(move, move) === Tie)
    }
  }
}

import rps.service.{GameServiceImpl}
import rps.repository.{InMemoryGameRepository}
import rps.model.{Move, Result}
import Move._, Result._
import org.scalatest._

class GameSpec extends FlatSpec {
  implicit val repo = InMemoryGameRepository()
  private val gameService = GameServiceImpl()

  "The client" should "win when a winning combo is evaluated" in {
    assert(gameService.getGameResult(Scissors, Rock) === Won)
    assert(gameService.getGameResult(Rock, Paper) === Won)
    assert(gameService.getGameResult(Paper, Scissors) === Won)
  }

  "The client" should "loose when a loosing combo is evaluated" in {
    assert(gameService.getGameResult(Rock, Scissors) === Lost)
    assert(gameService.getGameResult(Paper, Rock) === Lost)
    assert(gameService.getGameResult(Scissors, Paper) === Lost)
  }

  it should "return a tie when client and computer return the same move" in {
    Move.values.foreach { move =>
      assert(gameService.getGameResult(move, move) === Tie)
    }
  }
}

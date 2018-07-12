package rps.model

case class ResponseBody(result: Option[Result],
                        computerMove: Option[Move],
                        ourMove: Option[Move],
                        error: Option[String])

package rps.model

case class ResponseBody(result: Option[Result],
                        userMove: Option[Move],
                        ourMove: Option[Move],
                        error: Option[String])

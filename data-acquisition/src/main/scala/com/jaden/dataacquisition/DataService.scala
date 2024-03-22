package com.jaden.dataacquisition

import com.jaden.dataacquisition.entity.{Endpoint, GroupedDaily, MarketData, Sort, Timespan}
import org.json4s._
import org.json4s.jackson.Serialization.read
import sttp.client3._
import sttp.model.Uri

import scala.util.{Failure, Success, Try}

object DataService {
  def main(args: Array[String]): Unit = {
    val m = fetchData("AAPL")

    m match {
      case Failure(e) => sys.error(e.getMessage)
      case Success(data) => println(data)
    }
  }

  def fetchData(symbol: String): Try[MarketData] = {
    val httpBackend = HttpURLConnectionBackend()

    val endpoint = Endpoint.Aggregates(symbol, 1, Timespan.Day, "2024-01-01", "2024-03-21", true, Sort.Asc, 120)
    val requestUrl = DataProvider.generateUrl(endpoint)
    val response = Try {
      httpBackend.send(
        basicRequest
          .get(Uri(requestUrl))
      )
    }

    response match {
      case Failure(ex) => Failure(ex)
      case Success(res) =>
        res.body match {
          case Left(e) => Failure(new Exception(""))
          case Right(value) =>
            implicit val formats: Formats = DefaultFormats
            val groupedDaily: GroupedDaily = read[GroupedDaily](value)
            val closedPrice = groupedDaily.results.filter(_.T == symbol).head.c.get
            Success(MarketData(symbol, closedPrice))
        }
    }
  }
}

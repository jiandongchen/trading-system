package com.jaden.dataacquisition

import com.jaden.dataacquisition.entity.{Endpoint, Sort, Timespan}

import java.io.FileNotFoundException
import scala.io.Source

object DataProvider {
  private val baseUrl = "https://api.polygon.io/v2/aggs"

  private def getApiKey: String = {
    val apiKeyStream = this.getClass.getClassLoader.getResourceAsStream("apiKey")
    if (null != apiKeyStream) {
      Source.fromInputStream(apiKeyStream).mkString
    } else {
      throw new FileNotFoundException("")
    }
  }

  def generateUrl(endpoint: Endpoint): String = {
    val apiKey = getApiKey
    endpoint match {
      case Endpoint.Aggregates(stocksTicker, multiplier, timespan, from, to, adjust, sort, limit) =>
        baseUrl + "/ticker/" + stocksTicker + "/range/" + multiplier + "/" + timespan.toString + "/" + from + "/" +
          to + "?" + "adjusted=" + adjust.toString + "&" + "sort=" + sort.toString + "&" + "limit=" + limit.toString + "&" +
          "apiKey=" + apiKey
      case Endpoint.GroupedDaily(date, adjust) =>
        baseUrl + "/grouped/locale/us/market/stocks/" + date + "?" + "adjusted=" + adjust.toString + "&" +
          "apiKey=" + apiKey
    }
  }
}

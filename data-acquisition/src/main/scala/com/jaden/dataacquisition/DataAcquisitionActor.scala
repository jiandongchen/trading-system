package com.jaden.dataacquisition

import akka.actor.{Actor, ActorLogging}
import com.jaden.dataacquisition.entity.{DataResponse, FetchData}

import scala.util.{Failure, Success}

class DataAcquisitionActor extends Actor with ActorLogging {
  def receive: Receive = {
    case FetchData(symbol) =>
      log.info(s"Fetching data for $symbol")
      // Logic to fetch real-time market data for the specified symbol
      // Example: Use a data service to fetch data from an external API
      val data = DataService.fetchData(symbol)
      data match {
        case Failure(ex) =>
        case Success(d) => sender() ! DataResponse(d)
      }
  }
}

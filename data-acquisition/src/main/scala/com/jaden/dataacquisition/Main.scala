package com.jaden.dataacquisition

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.jaden.dataacquisition.entity.{DataResponse, FetchData}

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object Main {
  def main(args: Array[String]): Unit = {
    // Create an actor system
    val system = ActorSystem("DataAcquisitionSystem")

    // Create an instance of the DataAcquisitionActor
    val dataAcquisitionActor = system.actorOf(Props[DataAcquisitionActor], "dataAcquisitionActor")

    // Specify the timeout duration (e.g., 5 seconds)
    implicit val timeout: Timeout = Timeout(5.seconds)

    // Send a message to fetch data for a specific symbol (e.g., "AAPL")
    val fetchDataFuture = (dataAcquisitionActor ? FetchData("AAPL")).mapTo[DataResponse]

    // Process the fetched data when the future completes
    fetchDataFuture.foreach { response =>
      println(s"Received data: ${response.data}")
    }

    // Shutdown the actor system
    system.terminate()
  }
}

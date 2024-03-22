package com.jaden.dataacquisition.entity

case class GroupedDaily(
                         adjusted: Boolean, // Whether or not this response was adjusted for splits.
                         queryCount: Long, // The number of aggregates (minute or day) used to generate the response.
                         request_id: String, // A request id assigned by the server.
                         resultsCount: Long, // The total number of results for this request.
                         status: String, // The status of this request's response.
                         results: Array[Result]
                       )

case class Result(
                   T: String, // The exchange symbol that this item is traded under.
                   c: Option[Double], // The close price for the symbol in the given time period.
                   h: Option[Double], // The highest price for the symbol in the given time period.
                   l: Option[Double], // The lowest price for the symbol in the given time period.
                   n: Option[Long], // The number of transactions in the aggregate window.
                   o: Option[Long], // The open price for the symbol in the given time period.
                   otc: Option[Boolean], // Whether or not this aggregate is for an OTC ticker. This field will be left off if false.
                   t: Option[Long], // The Unix Msec timestamp for the end of the aggregate window.
                   v: Option[Double], // The trading volume of the symbol in the given time period.
                   vw: Option[Double] // The volume weighted average price.
                 )

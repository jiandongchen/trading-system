package com.jaden.dataacquisition.entity

sealed abstract class Endpoint extends Product with Serializable

object Endpoint {
  final case class Aggregates(stocksTicker: String,
                              multiplier: Int,
                              timespan: Timespan,
                              from: String,
                              to: String,
                              adjust: Boolean,
                              sort: Sort,
                              limit: Int) extends Endpoint

  final case class GroupedDaily(date: String, adjust: Boolean) extends Endpoint
}

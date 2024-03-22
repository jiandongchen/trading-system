package com.jaden.dataacquisition.entity

sealed abstract class Timespan extends Product with Serializable

object Timespan {
  case object Second extends Timespan {
    override def toString: String = "second"
  }

  case object Minute extends Timespan {
    override def toString: String = "minute"
  }

  case object Hour extends Timespan {
    override def toString: String = "hour"
  }

  case object Day extends Timespan {
    override def toString: String = "day"
  }

  case object Week extends Timespan {
    override def toString: String = "week"
  }

  case object Month extends Timespan {
    override def toString: String = "month"
  }

  case object Quarter extends Timespan {
    override def toString: String = "quarter"
  }

  case object Year extends Timespan {
    override def toString: String = "year"
  }
}

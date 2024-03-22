package com.jaden.dataacquisition.entity

sealed abstract class Sort extends Product with Serializable

object Sort {
  case object Asc extends Sort {
    override def toString: String = {
      "asc"
    }
  }

  case object Desc extends Sort {
    override def toString: String = {
      "desc"
    }
  }
}

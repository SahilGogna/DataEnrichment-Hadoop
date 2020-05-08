package com.mcit.scala.Mapping

import com.mcit.scala.Entities.Calender

/**
 * @author sahilgogna on 2020-02-08
 */
class CalendarLookup (calendars: List[Calender]){

  private val lookupTable: Map[String, Calender] =
    calendars.map(calendar => calendar.service_id -> calendar).toMap

  def lookup(serviceId: String): Calender = lookupTable.getOrElse(serviceId, null)
}

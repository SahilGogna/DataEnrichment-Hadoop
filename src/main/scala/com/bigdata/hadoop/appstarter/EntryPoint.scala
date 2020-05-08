package com.mcit.scala.AppStarter
import com.mcit.scala.Entities.{Calender, EnrichedTrip, Routes, TripRoute, Trips}
import com.mcit.scala.ReadWriteServices.{DataReader}
import com.mcit.scala.Mapping.{CalendarLookup, RouteLookup}
import com.mcit.scala.readwriteservices.DataWriter

/**
 * @author sahilgogna on 2020-02-08
 * This class is the entry point of the application
 */
object EntryPoint extends App {

  val readData : DataReader = new DataReader

  val tripList: List[Trips] = readData.getTripList
  val routeList: List[Routes] = readData.getRouteList
  val calendarList: List[Calender] = readData.getCalenderList

  val routeLookup = new RouteLookup(routeList)
  val calenderLookUp = new CalendarLookup(calendarList)

  val enrichedTripRoute: List[TripRoute] = tripList.map(trip => TripRoute(trip,
                                                    routeLookup.lookup(trip.route_id)))

  val enrichedTrip: List[EnrichedTrip] = enrichedTripRoute.map(tripRoute => EnrichedTrip(tripRoute,
                                                    calenderLookUp.lookup(tripRoute.trips.service_id)))

  val finalListOfTrips: List[EnrichedTrip] = enrichedTrip.filter(a => a.calender.monday.equals("1") && a.tripRoute.routes.route_type.equals("1"))
  val writer = new DataWriter(finalListOfTrips)
  writer.writeData()
}
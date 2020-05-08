package com.mcit.scala.readwriteservices

import com.bigdata.hadoop.readwriteservices.HadoopConfiguration
import com.mcit.scala.Entities.EnrichedTrip
import org.apache.hadoop.fs.{FSDataOutputStream, Path}


class DataWriter(enrichedList: List[EnrichedTrip]) extends HadoopConfiguration {


  val outputFolderPath = "/user/fall2019/sahilgogna/course3"
  val outputFilePath = "/user/fall2019/sahilgogna/course3/finaloutput.csv"
  val comma = ","

  val csvSchema: String = "Route Id" + comma +
    "Service Id" + comma +
    "Trip Id" + comma +
    "Trip Head Sign" + comma +
    "Direction Id" + comma +
    "Shape Id" + comma +
    "Wheelchair accessible" + comma +
    "Note_FR" + comma +
    "Note En" + comma +
    "Agency Id" + comma +
    "Route Short Name" + comma +
    "Route Long Name" + comma +
    "Route Type" + comma +
    "Route Url" + comma +
    "Route Colour" + comma +
    "Monday"


  def writeData(): Unit = {
    if (!fileSystem.exists(new Path(outputFolderPath))) fileSystem.mkdirs(new Path(outputFolderPath))

    //Create a path
    val writePath = new Path(outputFilePath)

    //Init output stream
    val outputStream: FSDataOutputStream = fileSystem.create(writePath, true)

    outputStream.writeChars(csvSchema)

    enrichedList.foreach(element => {
      val data: String = element.tripRoute.routes.route_id.toString + comma +
        element.calender.service_id.toString + comma +
        element.tripRoute.trips.trip_id.toString + comma +
        element.tripRoute.trips.trip_headsign.toString + comma +
        element.tripRoute.trips.direction_id.toString + comma +
        element.tripRoute.trips.shape_id.toString + comma +
        element.tripRoute.trips.wheelchair_accessible.toString + comma +
        element.tripRoute.trips.note_fr.toString + comma +
        element.tripRoute.trips.note_en.toString + comma +
        element.tripRoute.routes.agency_id.toString + comma +
        element.tripRoute.routes.route_short_name.toString + comma +
        element.tripRoute.routes.route_long_name.toString + comma +
        element.tripRoute.routes.route_type.toString + comma +
        element.tripRoute.routes.route_url.toString + comma +
        element.tripRoute.routes.route_color.toString + comma +
        element.calender.monday.toString

      outputStream.writeChars("\n")
      outputStream.writeChars(data)

    })

    // closing writer connection
    outputStream.close()

  }

}

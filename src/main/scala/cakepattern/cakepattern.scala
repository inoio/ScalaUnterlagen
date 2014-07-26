package cakepattern
import scala.io.StdIn._
trait SensorDataProviderComponent {
  self =>
  val sensorDataProvider: SensorDataProvider
  class SensorDataProvider {

    def getData() = {
      println("Enter data:")
      readLine()
    }
  }
}

trait SensorDataClientComponent {
  self: SensorDataProviderComponent =>
  val sensorDataClient: SensorDataClient

  class SensorDataClient {

    def sensorDatenAnzeigen() = {
      val data = sensorDataProvider.getData()
      println(data)
      data
    }
  }
}

object Cake extends SensorDataProviderComponent with SensorDataClientComponent  {
  val sensorDataProvider = new SensorDataProvider()
  val sensorDataClient = new SensorDataClient()
}


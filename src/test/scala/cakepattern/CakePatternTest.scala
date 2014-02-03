package cakepattern

import org.specs2.mutable._
import org.specs2.mock.Mockito
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CakePatternTest extends Specification with Mockito {

  object CakeTest extends SensorDataClientComponent with SensorDataProviderComponent {
    val sensorDataClient = new SensorDataClient()
    val sensorDataProvider = mock[SensorDataProvider]
  }

  import CakeTest._

  "The caketest" should {
    "deliver correct sensor results " in {
      sensorDataProvider.getData() returns "3"
      sensorDataClient.sensorDatenAnzeigen() must be equalTo ("3")
    }
  }
}
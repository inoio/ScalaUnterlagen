package oscilloscope.model

import oscilloscope.ui.parser.OscilloscopeParser

/**
 * Model class to store values for the 2 channel oscilloscope
 */
case class DataPoints(ch1: Option[Boolean], ch2: Option[Boolean], logic: Option[Boolean])

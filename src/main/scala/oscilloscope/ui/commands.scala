package oscilloscope.ui

/**
 * Various commands which can be entered via the UI
 */
sealed trait Command
/**
 * enable a channel
 */
case class Enable(channel: Int) extends Command
/**
 * disable a channel
 */
case class Disable(channel: Int) extends Command
/**
 * Enable all channels
 */
case object EnableAll extends Command
/**
 * Disable all channels
 */
case object DisableAll extends Command
/**
 * set logic channel in or mode
 */
case object ModeOR extends Command
/**
 * set logic channel in and mode
 */
case object ModeAND extends Command
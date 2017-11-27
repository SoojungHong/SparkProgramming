/**
  * Created by a613274 on 27.11.2017.
  */

import com.typesafe.config.Config

trait Settings {
  def longProperty: (Config, String) => Long = (config: Config, path: String) => config.getLong(path)

  def stringProperty: (Config, String) => String = (config: Config, path: String) => config.getString(path)

  def intProperty: (Config, String) => Int = (config: Config, path: String) => config.getInt(path)

  def booleanProperty: (Config, String) => Boolean = (config: Config, path: String) => config.getBoolean(path)

}

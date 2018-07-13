/**
  * Created by a613274 on 28.11.2017.
  */


import com.typesafe.config.Config

trait ConfigProvider {
  def provide : Config
}

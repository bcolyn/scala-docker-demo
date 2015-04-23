package demo

import com.typesafe.config.ConfigFactory

object Main extends App{
  val config = ConfigFactory.load()
  val greeting = config.getString("greeting")
  println(greeting)
}

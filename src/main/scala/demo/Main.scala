package demo

import com.typesafe.config.ConfigFactory

object Main extends App{
  val config = ConfigFactory.load()
  val greeting = config.getString("greeting")
  println(greeting)

  var x = 0
  while (true) {
    Thread.sleep(1000)
    println(s"$x this is just filler. filler this is. $greeting. spam the log. spam the log. spam the log.")
    x+=1
  }
}

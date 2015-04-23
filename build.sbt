enablePlugins(DockerPlugin)

name := "scala-docker-demo"

version := "1.0"

scalaVersion := "2.10.5"

libraryDependencies += "com.typesafe" % "config" % "1.2.1"

docker <<= docker.dependsOn(Keys.`package`.in(Compile, packageBin))

dockerfile in docker := {
  val jarFile = artifactPath.in(Compile, packageBin).value
  val classpath = (managedClasspath in Compile).value
  val mainclass = mainClass.in(Compile, packageBin).value.getOrElse(sys.error("Expected exactly one main class"))
  val jarTarget = s"/app/${jarFile.getName}"
  // Make a colon separated classpath with the JAR file
  val classpathString = "/conf:" + jarTarget  + ":" +
    classpath.files.map("/lib/" + _.getName).mkString(":")
  new Dockerfile {
    // Base image
    from("jeanblanchard/busybox-java:8")
    // Add all files on the classpath
    add(classpath.files, "/lib/")
    // Add the JAR file
    add(jarFile, jarTarget)
    // On launch run Java with the classpath and the main class
    entryPoint("java", "-cp", classpathString, mainclass)
  }
}
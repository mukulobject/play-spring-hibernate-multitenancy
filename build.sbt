name := "play-java-spring"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaCore,
  javaJdbc,
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "org.hibernate" % "hibernate-core" % "4.2.7.Final",
  "org.springframework" % "spring-core" % "3.2.3.RELEASE",
  "org.springframework" % "spring-beans" % "3.2.3.RELEASE",
  "org.springframework" % "spring-context" % "3.2.3.RELEASE",
  "org.springframework" % "spring-orm" % "3.2.3.RELEASE",
  "org.springframework" % "spring-jdbc" % "3.2.3.RELEASE",
  "org.springframework" % "spring-tx" % "3.2.3.RELEASE",
  "org.springframework" % "spring-expression" % "3.2.3.RELEASE",
  "org.springframework" % "spring-aop" % "3.2.3.RELEASE",
  "org.springframework" % "spring-aspects" % "3.2.3.RELEASE",
  "org.springframework" % "spring-web" % "3.2.3.RELEASE",
  "org.springframework" % "spring-test" % "3.2.3.RELEASE" % "test",
  "commons-pool" % "commons-pool" % "1.6",
  "commons-dbcp" % "commons-dbcp" % "1.4",
  "cglib" % "cglib" % "2.2.2"
)

play.Project.playJavaSettings

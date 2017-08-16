package com.example.Session
import com.softwaremill.session.{JValueSessionSerializer, JwtSessionEncoder, SessionConfig, SessionManager}

class JWTSessionManagerScala {
  case class SessionData(value: String)

  implicit val serializer = JValueSessionSerializer.caseClass[SessionData]
  implicit val encoder = new JwtSessionEncoder[SessionData]
  implicit val manager = new SessionManager(SessionConfig.fromConfig())

}

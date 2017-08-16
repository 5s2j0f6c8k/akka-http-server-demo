package com.example.Session
import com.softwaremill.session.SessionDirectives._
import com.softwaremill.session.SessionOptions._
import com.softwaremill.session._
import com.typesafe.scalalogging.StrictLogging
import  scala.concurrent.ExecutionContext.Implicits.global

import scala.util.Try
case class AppScalaSession(username: String)

trait AppSession extends StrictLogging{
  implicit def serializer: SessionSerializer[AppScalaSession, String] = new SingleValueSessionSerializer(
    _.username,
    (un: String) => Try {
      AppScalaSession(un)
    }
  )
  val sessionConfig = SessionConfig.default("c05ll3lesrinf39t7mc5h6un6r0c69lgfno69dsak3vabeqamouq4328cuaekros401ajdpkh60rrtpd8ro24rbuqmgtnd1ebag6ljnb65i8a55d482ok7o0nch0bfbe")

  implicit val sessionManager = new SessionManager[AppScalaSession](sessionConfig)

  implicit val refreshTokenStorage = new InMemoryRefreshTokenStorage[AppScalaSession] {
    def log(msg: String) = logger.info(msg)
  }

  def appSetSession(v: AppScalaSession) = setSession(refreshable, usingCookies, v)

  val appRequiredSession = requiredSession(refreshable, usingCookies)
  val appInvalidateSession = invalidateSession(refreshable, usingCookies)



}

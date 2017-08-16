package com.example.BusinessHandle

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import com.example.Session.AppSession
import spray.json.DefaultJsonProtocol._

object Logout extends AppSession {

  final case class LogoutResponse(returnCode: String, returnMessage: String)

  implicit val logoutResponseFormat = jsonFormat2(LogoutResponse)

  val logoutRoute: Route =
    path("logout") {
      post {
        appRequiredSession { session =>
          appInvalidateSession { ctx =>
            logger.info(s"Logging out $session")
            ctx.complete(LogoutResponse("10000", "注销成功"))
          }
        }
      }

    }
}

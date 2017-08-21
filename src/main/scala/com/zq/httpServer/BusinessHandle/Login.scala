package com.zq.httpServer.BusinessHandle

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.zq.httpServer.Session.{AppScalaSession, AppSession}
import spray.json.DefaultJsonProtocol._

object Login extends AppSession {

  final case class LoginRequest(phoneNo: String, authCode: String, password: String)

  final case class LoginResponse(returnCode: String, returnMessage: String)

  implicit val LoginRequestFormat = jsonFormat3(LoginRequest)
  implicit val loginResponseFormat = jsonFormat2(LoginResponse)

  val loginRoute: Route =
    path("login") {
      post {
        entity(as[LoginRequest]) { loginRequest =>
          appSetSession(AppScalaSession(loginRequest.phoneNo)) {
            complete(LoginResponse("10000","登陆成功"))
          }
        }
      }
    }
}

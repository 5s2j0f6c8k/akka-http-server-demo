package com.zq.httpServer.BusinessHandle

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.zq.httpServer.Session.AppSession
import spray.json.DefaultJsonProtocol._

object CPPPreserveInfo extends AppSession {

  final case class CPPPreserveInfoRequest(proNo: String)

  final case class CPPPreserveInfoResponse(returnCode: String, returnMessage: String, expense: String)

  implicit val cPPPreserveInfoRequestFormat = jsonFormat1(CPPPreserveInfoRequest)

  implicit val cPPPreserveInfoResponseFormat = jsonFormat3(CPPPreserveInfoResponse)


  val cPPPreserveInfoRoute: Route =
    path("cPPPreserveInfo") {
      post {
        entity(as[CPPPreserveInfoRequest]) { entity => {
          appRequiredSession { _ => {
            complete(CPPPreserveInfoResponse("10000", "版权保全成功", "100"))
          }
          }
        }
        }
      }
    }
}

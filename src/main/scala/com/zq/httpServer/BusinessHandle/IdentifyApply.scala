package com.zq.httpServer.BusinessHandle

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.zq.httpServer.Session.AppSession
import spray.json.DefaultJsonProtocol._

object IdentifyApply extends AppSession {
  final case class IdentifyApplyRequest(legAlApplyNo:String)

  final case class IdentifyApplyResponse(returnCode:String,returnMessage:String,expense:String)


  implicit val identifyApplyRequestFormat = jsonFormat1(IdentifyApplyRequest)

  implicit val identifyApplyResponseFormat = jsonFormat3(IdentifyApplyResponse)

  val identifyApplyRoute:Route =
    path("identifyApply"){
      post{
        entity(as[IdentifyApplyRequest]) { entity =>
          appRequiredSession { _ =>
            complete(IdentifyApplyResponse("10000","查询成功","100"))
          }
        }
      }
    }

}

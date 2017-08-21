package com.zq.httpServer.BusinessHandle

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.zq.httpServer.Session.AppSession
import spray.json.DefaultJsonProtocol._

object AlApply extends AppSession  {
  final case class AlApplyRequest(proNo:String)
  final case class AlApplyResponse(returnCode:String,returnMessage:String,expense:String)

  implicit val alApplyRequestFormat = jsonFormat1(AlApplyRequest)

  implicit val alApplyResponseFormat = jsonFormat3(AlApplyResponse)

  val alApplyRoute:Route =
    path("alApply"){
      post {
        entity(as[AlApplyRequest]) { entity =>
          appRequiredSession { _ =>
            complete(AlApplyResponse("10000", "维权获取费用成功", "100"))
          }
        }
      }
    }

}

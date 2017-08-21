package com.zq.httpServer.BusinessHandle

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import com.zq.httpServer.Session.AppSession
import spray.json.DefaultJsonProtocol._
object LgPreserveSave extends AppSession{
  final case class LgPreserveSaveRequest(legAlApplyNo:String)
  final case class LgPreserveSaveResponse(returnCode:String,returnMessage:String,expense:String)

  implicit val LgPreserveSaveRequestFormat = jsonFormat1(LgPreserveSaveRequest)

  implicit val LgPreserveSaveResponseFormat = jsonFormat3(LgPreserveSaveResponse)


  val lgPreserveSaveRoute:Route =
    path("lgPreserveSave"){
      post {
        entity(as[LgPreserveSaveRequest]) { entity =>
          appRequiredSession { _ =>
            complete(LgPreserveSaveResponse("10000","查询成功","100"))
          }
        }
      }
    }
}

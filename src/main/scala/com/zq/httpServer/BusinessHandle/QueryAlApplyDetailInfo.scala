package com.zq.httpServer.BusinessHandle

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import com.zq.httpServer.Session.AppSession
import spray.json.DefaultJsonProtocol._
object QueryAlApplyDetailInfo extends AppSession{
  final case class QueryAlApplyDetailInfoRequest(legAlApplyNo:String)

  final case class QueryAlApplyDetailInfoResponse(returnCode:String,returnMessage:String,fileZip:String)

  implicit val queryAlApplyDetailInfoRequestFormat = jsonFormat1(QueryAlApplyDetailInfoRequest)

  implicit val queryAlApplyDetailInfoResponseFormat = jsonFormat3(QueryAlApplyDetailInfoResponse)

  val queryAlApplyDetailInfoRoute:Route=
    path("queryAlApplyDetailInfo"){
      post {
        entity(as[QueryAlApplyDetailInfoRequest]) { entity =>
          appRequiredSession { _ =>
            complete(QueryAlApplyDetailInfoResponse("10000","查询成功","www.baidu.com"))
          }
        }
      }
    }
}

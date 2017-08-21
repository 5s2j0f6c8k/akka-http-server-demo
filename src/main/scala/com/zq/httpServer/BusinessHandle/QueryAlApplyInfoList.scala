package com.zq.httpServer.BusinessHandle

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import com.zq.httpServer.Session.AppSession
import spray.json.DefaultJsonProtocol._

object QueryAlApplyInfoList  extends AppSession{
  final case class QueryAlApplyInfoListRequest(proNo:String)

  final case class QueryAlApplyInfoResponse(queryAlApplyInfoList:List[QueryAlApplyInfoList])
  final case class QueryAlApplyInfoList(alApplyStartDate:String,legAlApplyNo:String,state:String)

  implicit val queryAlApplyInfoListRequestFormat = jsonFormat1(QueryAlApplyInfoListRequest)

  implicit val QueryAlApplyInfoResponseFormat = jsonFormat1(QueryAlApplyInfoResponse)

  val queryAlApplyInfoListRoute:Route =
    path("queryAlApplyInfoList"){
      post {
        entity(as[QueryAlApplyInfoListRequest]) { entity =>
          appRequiredSession { _ =>
            complete(QueryAlApplyInfoResponse(List(QueryAlApplyInfoList("20170821","123456","0"))))
          }
        }
      }
    }

}

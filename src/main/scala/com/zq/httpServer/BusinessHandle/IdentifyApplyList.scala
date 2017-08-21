package com.zq.httpServer.BusinessHandle

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import com.zq.httpServer.Session.AppSession
import spray.json.DefaultJsonProtocol._
object IdentifyApplyList extends AppSession{
  final case class IdentifyApplyListRequest(page:String)
  final case class IdentifyApplyListResponse(returnCode:String,returnMessage:String,pageCount:String,count:String,identifyApplyDetail:List[IdentifyApplyDetail])
  final case class IdentifyApplyDetail(legAlApplyNo:String,upDate:String,schedule:String)

  implicit val identifyApplyListRequestFormat = jsonFormat1(IdentifyApplyListRequest)

  implicit val identifyApplyListResponseFormat = jsonFormat5(IdentifyApplyListResponse)


  val identifyApplyListRoute:Route =
    path("identifyApplyList"){
      post{
        entity(as[IdentifyApplyListRequest]) { entity =>
          appRequiredSession { _ =>
            complete(IdentifyApplyListResponse("10000","申请鉴定成功","10","10",List(IdentifyApplyDetail("123","20170821","鉴定完成"))))
          }
        }
      }
    }
}

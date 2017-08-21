package com.zq.httpServer.BusinessHandle

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import spray.json.DefaultJsonProtocol.jsonFormat11
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import com.zq.httpServer.Session.AppSession
import spray.json.DefaultJsonProtocol._

object QueryPreserveDetailInfo extends AppSession {

  final case class QueryPreserveDetailInfoRequest(proNo: String)

  final case class QueryPreserveDetailInfoResponse(proName: String, proSummary: String, proDigest: String,
                                                   proType: String, proOwer: String, proAuthor: String,
                                                   proAddress: String, proTime: String, proFileSign: String,
                                                   proFile: String, proState: String)

  implicit val queryPreserveDetailInfoRequestFormat = jsonFormat1(QueryPreserveDetailInfoRequest)

  implicit val QueryPreserveDetailInfoResponseFormat = jsonFormat11(QueryPreserveDetailInfoResponse)

  val queryPreserveDetailRoute: Route =
    path("queryPreserveDetailInfo") {
      post {
        entity(as[QueryPreserveDetailInfoRequest]) { entity =>
          appRequiredSession { _ =>
            complete(QueryPreserveDetailInfoResponse("测试名称", "简介", "摘要", "0", "作者", "著作人", "地点", "时间", "文件签名", "文件", "0"))
          }
        }
      }
    }
}

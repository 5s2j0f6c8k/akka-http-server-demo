package com.zq.httpServer.BusinessHandle

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.zq.httpServer.Session.AppSession
import spray.json.DefaultJsonProtocol._

object QueryPreserveInfoList extends AppSession {

  final case class QueryPreserveInfoListRequest(page: String, queryState: String)

  final case class QueryPreserveInfoListResponse(returnCode: String, returnMessage: String, pageCount: String, count: String, preserveDetail: List[QueryPreserveDetail])

  final case class QueryPreserveDetail(proName: String, upDate: String, proState: String, proNo: String)

  implicit val queryPreserveInfoListRequestFormat = jsonFormat2(QueryPreserveInfoListRequest)

  implicit val queryPreserveDetailFormat = jsonFormat4(QueryPreserveDetail)

  implicit val queryPreserveInfoListResponseFormat = jsonFormat5(QueryPreserveInfoListResponse)

  val queryPreserveInfoListRoute: Route =
    path("queryPreserveInfoList") {
      post {
        entity(as[QueryPreserveInfoListRequest]) { entity =>
          appRequiredSession { _ =>
            complete(QueryPreserveInfoListResponse("10000", "查询成功", "10", "10", List(
              QueryPreserveDetail("测试作品", "20170821", "0", "123456")
            )))
          }
        }
      }
    }


}

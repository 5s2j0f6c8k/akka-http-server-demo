package com.zq.httpServer.BusinessHandle

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import com.zq.httpServer.Session.AppSession
import spray.json.DefaultJsonProtocol._

object QueryUserInfo extends AppSession {

  final case class QueryUserInfoResponse(returnCode: String, returnMessage: String, balance: String, phoneNo: String, certType: String,
                                         certNo: String, name: String, address: String, regType: String)

  implicit val queryUserInfoResponseFormat = jsonFormat9(QueryUserInfoResponse)

  val queryUserInfoRoute: Route =
    path("queryUserInfo") {
      get {
        appRequiredSession { session =>
          complete(QueryUserInfoResponse("10000", "查询成功", "0", "13811548435", "0", "433101198602564134", "qugang", "北京", "1"))
        }
      }
    }

}

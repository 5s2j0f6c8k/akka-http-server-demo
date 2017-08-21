package com.zq.httpServer.BusinessHandle

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.zq.httpServer.Session.AppSession
import spray.json.DefaultJsonProtocol._

object QueryLgPreserveCertificate extends AppSession{
  final case class QueryLgPreserveCertificateRequest(legAlApplyNo:String)
  final case class QueryLgPreserveCertificateResponse(returnCode:String,returnMessage:String,expense:String,certificate:String)

  implicit val queryLgPreserveCertificateRequestFormat = jsonFormat1(QueryLgPreserveCertificateRequest)
  implicit val queryLgPreserveCertificateResponseFormat = jsonFormat4(QueryLgPreserveCertificateResponse)

  val queryLgPreserveCertificateRoute:Route =
    path("queryLgPreserveCertificate"){
      post{
        entity(as[QueryLgPreserveCertificateRequest]) { entity =>
          appRequiredSession { _ =>
            complete(QueryLgPreserveCertificateResponse("10000","查询成功","100","www.baidu.com"))
          }
        }
      }
    }
}

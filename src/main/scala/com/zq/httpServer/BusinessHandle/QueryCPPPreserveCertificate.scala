package com.zq.httpServer.BusinessHandle

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import com.zq.httpServer.Session.AppSession
import spray.json.DefaultJsonProtocol._

object QueryCPPPreserveCertificate extends AppSession{
  final case class QueryCPPPreserveCertificateRequest(proNo:String)

  final case class QueryCPPPreserveCertificateResponse(returnCode:String,returnMessage:String,expense:String,certificate:String)

  implicit val queryCPPPreserveCertificateRequestFormat = jsonFormat1(QueryCPPPreserveCertificateRequest)

  implicit val queryCPPPreserveCertificateResponseFormat = jsonFormat4(QueryCPPPreserveCertificateResponse)

  val queryCPPPreserveCertificateRoute:Route =
    path("queryCPPPreserveCertificate"){
      post {
        entity(as[QueryCPPPreserveCertificateRequest]) { entity =>
          appRequiredSession { _ =>
            complete(QueryCPPPreserveCertificateResponse("10000", "发布成功","100","www.baidu.com"))
          }
        }
      }
    }
}

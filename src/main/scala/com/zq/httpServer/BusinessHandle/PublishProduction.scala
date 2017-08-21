package com.zq.httpServer.BusinessHandle

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import spray.json.DefaultJsonProtocol.{jsonFormat11, jsonFormat2}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.Multipart
import akka.http.scaladsl.server.Directives._
import com.zq.httpServer.Session.AppSession
import spray.json.DefaultJsonProtocol._

object PublishProduction extends AppSession {

  final case class PublishProductionRequest(proName: String, proSummary: String, proDigest: String,
                                            proType: String, proOwer: String, proAuthor: String, proAddress: String,
                                            proDate: String, proTime: String, proFileSign: String, priFile: String)

  final case class PublishProductionResponse(returnCode: String, returnMessage: String)

  implicit val publishProductionRequestFormat = jsonFormat11(PublishProductionRequest)

  implicit val publishProductionResponseFormat = jsonFormat2(PublishProductionResponse)

  val publishProductionRoute: Route =
    path("publishProduction") {
      post {
        entity(as[Multipart.FormData]) { entity =>
          appRequiredSession { _ =>
            complete(PublishProductionResponse("10000", "发布成功"))
          }
        }
      }
    }
}

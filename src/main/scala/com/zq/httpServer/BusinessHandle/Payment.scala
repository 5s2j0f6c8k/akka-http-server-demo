package com.zq.httpServer.BusinessHandle

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import com.zq.httpServer.Session.AppSession
import spray.json.DefaultJsonProtocol._

object Payment extends AppSession{
  final case class PaymentRequest(proNo:String,expense:String,channel:String,payType:String)
  final case class PaymentResponse(returnCode:String,returnMessage:String,orderNo:String,prePayNo:String)

  implicit val paymentRequestFormat = jsonFormat4(PaymentRequest)

  implicit val PaymentResponseFormat = jsonFormat4(PaymentResponse)

  val paymentRoute:Route =
    path("payment"){
      post {
        entity(as[PaymentRequest]) { entity =>
          appRequiredSession { _ =>
            complete(PaymentResponse("10000", "发布成功","123456","123456"))
          }
        }
      }
    }
}

package com.zq.httpServer

import akka.http.scaladsl.server.{HttpApp, Route}
import com.softwaremill.session.CsrfDirectives.randomTokenCsrfProtection
import com.softwaremill.session.CsrfOptions.checkHeader
import com.typesafe.config.ConfigFactory
import com.zq.httpServer.BusinessHandle._
import com.zq.httpServer.Session.AppSession

/**
  * Server will be started calling `WebServerHttpApp.startServer("localhost", 8080)`
  * and it will be shutdown after pressing return.
  */
object WebServerHttpApp extends HttpApp with App with AppSession {
  val conf = ConfigFactory.load()
  val host = conf.getString("akka.http.app.host")
  val port = conf.getInt("akka.http.app.port")

  // Routes that this WebServer must handle are defined here
  // Please note this method was named `route` in versions prior to 10.0.7
  def routes: Route =
  randomTokenCsrfProtection(checkHeader) {
    pathEndOrSingleSlash { // Listens to the top `/`
      complete("Server up and running") // Completes with some text
    } ~
      Login.loginRoute ~
      Logout.logoutRoute ~
      AlApply.alApplyRoute ~
      CPPPreserveInfo.cPPPreserveInfoRoute ~
      IdentifyApply.identifyApplyRoute ~
      IdentifyApplyList.identifyApplyListRoute ~
      LgPreserveSave.lgPreserveSaveRoute ~
      Payment.paymentRoute ~
      PublishProduction.publishProductionRoute ~
      QueryAlApplyDetailInfo.queryAlApplyDetailInfoRoute ~
      QueryAlApplyInfoList.queryAlApplyInfoListRoute ~
      QueryCPPPreserveCertificate.queryCPPPreserveCertificateRoute ~
      QueryLgPreserveCertificate.queryLgPreserveCertificateRoute ~
      QueryPreserveDetailInfo.queryPreserveDetailRoute ~
      QueryPreserveInfoList.queryPreserveInfoListRoute ~
      QueryUserInfo.queryUserInfoRoute
  }

  // This will start the server until the return key is pressed
  startServer(host, port)
}

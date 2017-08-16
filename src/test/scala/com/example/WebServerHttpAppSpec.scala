package com.example

import akka.http.scaladsl.model.headers.{Cookie, `Set-Cookie`}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.model.{HttpEntity, MediaTypes, StatusCodes}
import com.example.TestData.sessionConfig
import org.scalatest.{Matchers, WordSpec}

class WebServerHttpAppSpec extends WordSpec with Matchers with ScalatestRouteTest {
  val cookieName = sessionConfig.csrfCookieConfig.name
  "WebServiceHttpApp" should {
    "not handle a POST request to `/Login` and `/logout`" in {
      Get("/") ~> WebServerHttpApp.routes ~> check {
        status shouldBe StatusCodes.OK
        responseAs[String] shouldBe "Server up and running"

        val Some(csrfCookie) = header[`Set-Cookie`]
        Post("/login", HttpEntity(MediaTypes.`application/json`, "{\"phoneNo\":\"123456\",\"authCode\":\"123456\",\"password\":\"password\"}")) ~>
          addHeader(Cookie(cookieName, csrfCookie.cookie.value)) ~>
          addHeader(sessionConfig.csrfSubmittedName, csrfCookie.cookie.value) ~>
          WebServerHttpApp.routes ~> check {
          status shouldBe StatusCodes.OK
          val Some(sessionData) = header[`Set-Cookie`]

          responseAs[String] shouldBe "{\"returnCode\":\"10000\",\"returnMessage\":\"登陆成功\"}"
          Post("/logout") ~> addHeader(Cookie(cookieName, csrfCookie.cookie.value)) ~>
            addHeader(sessionConfig.csrfSubmittedName, csrfCookie.cookie.value) ~>
            addHeader(Cookie(sessionConfig.sessionCookieConfig.name, sessionData.cookie.value)) ~>
            WebServerHttpApp.routes ~> check {
            status shouldBe StatusCodes.OK
            responseAs[String] shouldBe "{\"returnCode\":\"10000\",\"returnMessage\":\"注销成功\"}"
          }
        }
      }
    }
  }

}

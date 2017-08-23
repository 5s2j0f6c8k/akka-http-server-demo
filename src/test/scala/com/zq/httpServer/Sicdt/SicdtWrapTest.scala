package com.zq.httpServer.Sicdt

import java.nio.file.{Files, Paths}

import org.scalatest.{Matchers, WordSpec}

import scala.io.Source

class SicdtWrapTest extends WordSpec with Matchers {
  "SicdtWrapTest" should {
    "login UploadPreserve" in {
      val token = SicdtWrap.login("zjzh_s_rank", "CpievEp3tWpuK7exnZldGFzkQJDBPimEt+zG1EbUth6pmRt2pMLwSxtNJEhBRJRU", "测试")
      val result = SicdtWrap.UploadPreserveJson("zjzh_s_rank", "CpievEp3tWpuK7exnZldGFzkQJDBPimEt+zG1EbUth6pmRt2pMLwSxtNJEhBRJRU", "测试",
        "BQD20170821145443581",
        "YWCJ20170821145418245",
        1,
        "CZLC201708211455009921510000",
        "1",
        "{\n        \"qq\":\"321321321\",\n        \"MSN\":\"asdf@asdf.com\",\n        \"Tel\":\"0852-8888888\"\n    }",
        token.get)

      result shouldBe true
    }
    "login UploadPreserve File" in {
      val fileByte = Files.readAllBytes(Paths.get("E:\\职权测试.jpg"))
      val token = SicdtWrap.login("zjzh_s_rank", "CpievEp3tWpuK7exnZldGFzkQJDBPimEt+zG1EbUth6pmRt2pMLwSxtNJEhBRJRU", "测试")
      val result = SicdtWrap.UploadPreserveFile("zjzh_s_rank",
        "CpievEp3tWpuK7exnZldGFzkQJDBPimEt+zG1EbUth6pmRt2pMLwSxtNJEhBRJRU",
        "测试",
        "BQD20170821145443581",
        "YWCJ20170821145418245",
        1,
        "CZLC201708211455009921510000",
        "1",
        "职权测试.jpg",
        "职权测试.jpg",
        "测试上传图片",
        fileByte,
        token.get
      )
      result shouldBe true
    }

    "login QueryPreserve" in {
      val token = SicdtWrap.login("zjzh_s_rank", "CpievEp3tWpuK7exnZldGFzkQJDBPimEt+zG1EbUth6pmRt2pMLwSxtNJEhBRJRU", "测试")
      val result = SicdtWrap.QueryPreserveInfo("zjzh_s_rank", "测试", token.get,"318337ce85024804860483865c54a6f6")
    }


  }
}

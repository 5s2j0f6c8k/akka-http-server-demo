package com.zq.httpServer.Sicdt

import java.net.{URLDecoder, URLEncoder}
import java.util
import java.util.{ArrayList, List}

import com.sicdt.business.inceptor.api.domain.getpreserveinfo.GetPreserveInfo
import com.sicdt.business.inceptor.api.domain.login.Login
import com.sicdt.business.inceptor.api.domain.uploadpreserveinfo.UploadInfo
import com.sicdt.sdk.model.ReqHeader
import com.sicdt.sdk.model.req.{LoginReq, PreserveInfoReq, UploadInfoReq}
import com.sicdt.sdk.model.resp.{LoginResp, PreserveInfoResp, UploadInfoResp}
import com.sicdt.sdk.server.ISicdtInceptor
import com.sicdt.sdk.server.impl.SicdtInceptorImpl
import com.sicdt.sdk.util.{SystemInfo, UUIDUtil}
import com.typesafe.scalalogging.StrictLogging

object SicdtWrap extends StrictLogging {


  def login(sid: String, pwd: String, md: String): Option[String] = {
    val req = getHeader(sid, md)
    val service = new SicdtInceptorImpl
    val inReq = new LoginReq
    val in = new Login
    in.setSid(sid)
    in.setPwd(pwd)
    inReq.setIn(in)
    inReq.setReq(req)
    val resp = service.login(inReq)
    resp.getResp.gethStatus match {
      case 200 if resp.getOut.getResultCode == "0" => Option(resp.getOut.getToken)
      case _ => None
    }
  }


  def UploadPreserveJson(sid: String, pwd: String, md: String,
                         pptNum: String,
                         processTypeId: String,
                         bizFlowVersion: Int,
                         bizFlowRootNum: String, bizType: String, jsonData: String, token: String): Boolean = {
    val sApplyNum = UUIDUtil.randomUUID32
    val service = new SicdtInceptorImpl
    val uliIn = new UploadInfo
    val req = getHeader(sid, md)

    uliIn.setChainCode("")
    uliIn.setPptNum(pptNum)
    uliIn.setProcessTypeId(processTypeId)
    uliIn.setProcessNum("")
    uliIn.setsApplyNum(UUIDUtil.randomUUID32)
    uliIn.setSid(req.getSid)
    uliIn.setToken(token)
    uliIn.setFileName("智权联调测试")
    uliIn.setFileType(".txt")
    uliIn.setFileInfo("职权联调测试")
    uliIn.setBizFlowRootNum(bizFlowRootNum)
    uliIn.setVersion(bizFlowVersion)
    uliIn.setBizType(bizType)
    uliIn.setJsonStr(s"${jsonData}-$sApplyNum")

    req.setMsgId(UUIDUtil.randomUUID32)
    val in = new UploadInfoReq(req, uliIn)
    val uliResp = service.uploadPreserveInfo(in)

    uliResp.getResp.gethStatus match {
      case 200 => true
      case _ => {
        logger.info(s"上传失败 ${uliResp.getOut.toString} ${uliResp.getResp.toString}")
        false
      }
    }

  }

  def UploadPreserveFile(sid: String, pwd: String, md: String,
                         pptNum: String,
                         processTypeId: String,
                         bizFlowVersion: Int,
                         bizFlowRootNum: String, bizType: String, fileName: String,
                         fileType: String,
                         fileInfo: String,
                         fileByte: Array[Byte],
                         token: String
                        ): Boolean = {
    val sApplyNum = UUIDUtil.randomUUID32
    val service = new SicdtInceptorImpl
    val uliIn = new UploadInfo
    val req = getHeader(sid, md)

    uliIn.setChainCode("")
    uliIn.setPptNum(pptNum)
    uliIn.setProcessTypeId(processTypeId)
    uliIn.setProcessNum("")
    uliIn.setsApplyNum(UUIDUtil.randomUUID32)
    uliIn.setSid(req.getSid)
    uliIn.setToken(token)
    uliIn.setFileName(fileName)
    uliIn.setFileType(fileType)
    uliIn.setFileInfo(fileInfo)
    uliIn.setBizFlowRootNum(bizFlowRootNum)
    uliIn.setVersion(bizFlowVersion)
    uliIn.setBizType(bizType)
    uliIn.setFileByte(fileByte)

    req.setMsgId(UUIDUtil.randomUUID32)
    val in = new UploadInfoReq(req, uliIn)
    val uliResp = service.uploadPreserveInfo(in)

    uliResp.getResp.gethStatus match {
      case 200 => {
        logger.info(s"上传成功 ${uliResp.getOut.toString} ${uliResp.getResp.toString}")
        true
      }
      case _ => {
        logger.info(s"上传失败 ${uliResp.getResp.toString}")
        false
      }
    }
  }

  def QueryPreserveInfo(sid: String, md: String, token: String,sAppltyNum:String): Boolean = {

    val req = getHeader(sid, md)

    // 构建登录请求参数并获取token
    val service: ISicdtInceptor = new SicdtInceptorImpl

    val gpiIn: GetPreserveInfo = new GetPreserveInfo
    val preserveApplyNumList: util.List[String] = new util.ArrayList[String]
    gpiIn.setSid(sid)
    gpiIn.setToken(token)
    preserveApplyNumList.add(sAppltyNum)
    gpiIn.setPreserveApplyNumList(preserveApplyNumList)

    val in: PreserveInfoReq = new PreserveInfoReq(req, gpiIn)
    val resp: PreserveInfoResp = service.getPreserveInfo(in)
    resp.getResp.gethStatus match {
      case 200 => {
        logger.info(s"保全结果 ${resp.getOut.getPreserveResInfoList.toString} ${resp.getResp.toString}")
        true
      }
      case _ => {
        logger.info(s"查询失败  ${URLDecoder.decode(resp.getResp.toString)}")
        false
      }
    }
  }


  def getHeader(sid: String, md: String): ReqHeader = {
    val req = new ReqHeader()
    req.setSid(sid)
    req.setDev(SystemInfo.getDev)
    req.setMsgId(UUIDUtil.randomUUID32)
    req.setProtocolVer("1.0")
    req.setMd(md)
    req.setSize(100)
    req
  }
}

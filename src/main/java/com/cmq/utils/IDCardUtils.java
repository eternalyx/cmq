package com.cmq.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class IDCardUtils {
	// OCR webapi 接口地址
	public static final String WEBOCR_URL = "http://webapi.xfyun.cn/v1/service/v1/ocr/idcard";
	// 应用ID
	private static final String APPID = "5c82ceaf";
	// 接口密钥
	private static final String API_KEY = "18fc6d537de815abe111a918596005af";
	// 引擎类型
	private static final String ENGINE_TYPE = "idcard";
	// 是否返回头像图片
	private static final String HEAD_PORTRAIT = "0";
	// 是否返回切片图
	//private static final String CROP_IMAGE = "0";
	// 是否返回身份证号码区域截图
	private static final String ID_NUMBER_IMAGE = "0";
	// 图片地址
	private static final String AUDIO_PATH = "/Users/eternal/Downloads/card.jpg";

	/**
	 * 组装http请求头
	 */
	public static Map<String, String> buildHttpHeader() {
		try{
			String curTime = System.currentTimeMillis() / 1000L + "";
			String param = "{\"engine_type\":\"" + ENGINE_TYPE + "\",\"head_portrait\":\"" + HEAD_PORTRAIT + "\",\"id_number_image\":\"" + ID_NUMBER_IMAGE +"\"}";
			String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
			String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);
			Map<String, String> header = new HashMap<String, String>();
			header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			header.put("X-Param", paramBase64);
			header.put("X-CurTime", curTime);
			header.put("X-CheckSum", checkSum);
			header.put("X-Appid", APPID);
			return header;
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return new HashMap<>();
		}
	}

	/**
	 * OCR WebAPI 调用示例程序
	 * @param args
	 * @throws IOException
	 */
//	public static void main(String[] args) throws IOException {
//		Map<String, String> header = buildHttpHeader();
//		byte[] imageByteArray = FileUtils.read(AUDIO_PATH);
//		String imageBase64 = new String(Base64.encodeBase64(imageByteArray), "UTF-8");
//		String result = HttpUtils.doPost1(WEBOCR_URL, header, "image=" + URLEncoder.encode(imageBase64, "UTF-8"));
//		System.out.println("WEB card 接口调用结果：" + result);
//		System.out.println(result);
//	}
}
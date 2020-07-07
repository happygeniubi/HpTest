package com.happyge.empl.controller;

import com.happyge.empl.constant.MethodType;
import com.happyge.empl.constant.db.HappygeAccountState;
import com.happyge.empl.model.HappygeAccount;
import com.happyge.empl.repository.HappygeAccountMapper;
import com.happyge.empl.service.HappygeAccountService;
import com.happyge.empl.service.HappygeModuleService;
import com.happyge.empl.utils.FaceClient;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.happyge.empl.constant.SessionKey;
import com.happyge.empl.support.JSONReturn;
import com.happyge.empl.support.RandomValidateCode;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class HappygeSupportController extends AbstractController{
	
	private static final long serialVersionUID = 5024218829931214440L;
	@Autowired
	private HappygeAccountService happygeAccountService;
	@Autowired
	private HappygeModuleService happygeModuleService;
	@Autowired
	private HappygeAccountMapper happygeAccountMapper;

	@RequestMapping(value = "/0/findVerifycode")
	public void findVerifycode(HttpServletRequest request, HttpServletResponse response){
		//生成图形验证码并将图片返回给前台
		RandomValidateCode.getRandcode(request, response);
	}

	@ResponseBody
	@RequestMapping(value = "/0/login")
	public JSONReturn login(@RequestParam String username,@RequestParam String password,@RequestParam String verify,HttpServletRequest request,HttpServletResponse response){
		//处理图形验证码的检测
		if (StringUtils.isEmpty(verify)) {
			return JSONReturn.buildFailure("验证码为空!");
		}
		Object code = request.getSession().getAttribute(SessionKey.VALIDATE_CODE);
		if(!verify.equalsIgnoreCase(String.valueOf(code))) {
			return JSONReturn.buildFailure("验证码输入错误!");
		}
		return happygeAccountService.login(username,password,request);
	}

	@ResponseBody
		@RequestMapping(value = "/findMenu")
	public JSONReturn findMenu(HttpServletRequest request) {
		return happygeModuleService.findMenu(acctName(request));
	}

	@ResponseBody
	@RequestMapping(value = "/findModuleParameter")
	public JSONReturn findModuleParameter(@RequestParam String moduleCode, HttpServletRequest request) {
		return happygeModuleService.findModuleParameter(moduleCode,acctName(request));
	}

	@ResponseBody
	@RequestMapping(value = "/faceRegister")
	public String faceRegister(HttpServletRequest request, String base){
		JSONObject json = new JSONObject();
		// 获取session中的用户信息
		HappygeAccount account = (HappygeAccount) request.getSession().getAttribute(SessionKey.LOGIN_USER_INFO);
		// 定义图片的存储目录
		String path=request.getServletContext().getRealPath("/")+"headPhoto/" ;
		// 定义图片存储数据库中的url
		String urlPath=request.getContextPath() + "/headPhoto/"+account.getUsername()+".jpg";
		// 检查目录该存储是否存在，如果不存在，则创建该目录
		File uploadDir = new File(path);
		if (!uploadDir.exists() && !uploadDir.isDirectory()) {
			uploadDir.mkdirs();
		}
		// 目录创建后，完善图片的存储路径名
		path+=account.getUsername()+".jpg";
		System.out.println(path);
		// ============将图片存入到硬盘中===============
		OutputStream out = null;
		InputStream is = null;
		try {
			// 将base64图片数据解码成字节数组
			byte[] imgByte  = new BASE64Decoder().decodeBuffer(base);
			// 调整异常数据 base64数据解码可能会出现负数
			for (int i = 0; i < imgByte.length; ++i) {
				if (imgByte[i] < 0) {
					imgByte[i] += 256;
				}
			}
			out = new FileOutputStream(path);
			is = new ByteArrayInputStream(imgByte);

			byte[] buff = new byte[1024];
			int len = 0;
			while((len=is.read(buff))!=-1){
				out.write(buff, 0, len);
			}
		} catch (IOException e) {
			// 捕获到异常则表示上传出错
			json.put("message", "注册失败");
			e.printStackTrace();
			return json.toString();
		} finally {
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// 更新登录用户的faceurl(服务器存储路径)字段和facepath(实际存储路径)字段
		happygeAccountService.updataFaceUrlByName(account.getUsername(), urlPath, path);
		json.put("message", "注册成功");
		return json.toString();
	}

	@ResponseBody
	@RequestMapping(value = "0/facelogin")
	public String facelogin(HttpServletRequest request, String base) {
		String APP_ID="17061690";
		String API_KEY="05B0VkRBMeFzZHmQoVhMbT1m";
		String SECRET_KEY="N2TfIZ0oMwUOkpzLqD8jgGG6GduEf4CF";
		JSONObject json = new JSONObject();
		//查询出所有用户信息
		List<HappygeAccount> accountList = happygeAccountMapper.findAll();
		for(HappygeAccount acc : accountList) {
				if(acc.getFaceurl() != null && !acc.getFaceurl().equals("")) {
					String localImgBase = getImageStr(acc.getFacepath());
					FaceClient faceClient = FaceClient.getInstance(APP_ID, API_KEY, SECRET_KEY);
					Boolean loginBool = faceClient.faceContrast(localImgBase, base);
					if (loginBool) {
						json.put("message", "登录成功");
						request.getSession().setAttribute(SessionKey.LOGIN_USER_INFO, acc);
						return json.toString();
					}
				}
		}
		json.put("message", "登录失败");
		return json.toString();
	}

	public static String getImageStr(String imgFile)  {
		InputStream in = null;
		byte[] data = null;
		//读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		}catch (IOException e){
			e.printStackTrace();
		}
		//对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);//返回Base64编码过的字节数组字符串
	}
}

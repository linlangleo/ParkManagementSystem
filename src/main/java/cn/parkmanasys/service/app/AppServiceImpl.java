package cn.parkmanasys.service.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.parkmanasys.dao.CarOwnerUserMapper;
import cn.parkmanasys.entity.CarOwnerUser;
import cn.parkmanasys.util.StaticClass;
import cn.parkmanasys.util.ocr.Base64Util;
import cn.parkmanasys.util.ocr.FileUtil;
import cn.parkmanasys.util.ocr.HttpUtil;
import cn.parkmanasys.util.ocr.OrcToken;
import io.netty.handler.codec.base64.Base64Decoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import net.coobird.thumbnailator.Thumbnails;

/**
 * 接口实现
 * 
 * @author 刘大仙
 *
 */
@WebService(serviceName = "AppService", // 与接口中指定的name一致
		targetNamespace = "http://webservice.parkmanasys.cn", // 与接口中的命名空间一致,一般是接口的包名倒
		endpointInterface = "cn.parkmanasys.service.app.AppService"// 接口地址
)
@Component
public class AppServiceImpl implements AppService{
	@Resource
	private CarOwnerUserMapper carOwnerUserMapper;
	

	@Override
	public CarOwnerUser appLogin(String name, String password) {
		CarOwnerUser carOwner = null;
		
		try {
			carOwner = carOwnerUserMapper.findCarOwnerById(name, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	return carOwner;
	}
	
	/**
     * 更新用户信息,返回消息冒泡
     * @param id
     */
    @WebMethod
   	@WebResult(name = "String", targetNamespace = "")
   	public boolean updateUserInfo(@WebParam(name = "id") Integer id, 
   			@WebParam(name = "nickName") String nickName,
   			@WebParam(name = "sex") String sex,
   			@WebParam(name = "bornDate") Date borndate){
		CarOwnerUser carOwnerUser = null;
    	
		try {
	    	if(id != null && id > 0){
				carOwnerUser = carOwnerUserMapper.findOne(id);
				carOwnerUser.setNickName(nickName);
				carOwnerUser.setSex(sex);
				carOwnerUser.setBorndate(borndate);

				carOwnerUser = carOwnerUserMapper.save(carOwnerUser);
				
				//提示管理员
				if(StaticClass.channels.get(8) != null){
					StaticClass.channels.get(8).channel().writeAndFlush(new TextWebSocketFrame("用户号码:"+carOwnerUser.getPhone()+",更新了信息!".toUpperCase(Locale.US)));
				}
				
	        	return true;
	    	}else{
	        	return false;
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    
	/**
     * 获取用户信息
     * @param id
     */
    @WebMethod
   	@WebResult(name = "String", targetNamespace = "")
   	public CarOwnerUser getUserInfoById(@WebParam(name = "id") Integer id){
		CarOwnerUser carOwner = null;
		
		try {
			carOwner = carOwnerUserMapper.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	return carOwner;
    }

    /**
     * 获取照片二进制流，识别车牌
     * @param imgByte
     * @throws IOException 
     */
	@Override
	public String plateNumberDiscriminate(String imgBase64) throws IOException {
		//保存文件
		FileOutputStream fos = null;  
		//删除用文件
		File file_del = null;
        try{  
        	//存储路径 
        	String toDir= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getServletContext().getRealPath("/");
        	 //对android传过来的图片字符串进行解码 
            byte[] buffer =  com.alibaba.fastjson.util.Base64.decodeFast(imgBase64);  
            File destDir = new File(toDir);    
            if(!destDir.exists()) destDir.mkdir();  
            fos = new FileOutputStream(new File(destDir, "fileImg.jpg"));   //保存图片  
            fos.write(buffer);  
            fos.flush();  
            fos.close();    
            //进行压缩
            Thumbnails.of(destDir.getPath()+"\\fileImg.jpg").scale(1f).outputQuality(0.1f).outputFormat("jpg").toFile(destDir.getPath()+"\\fileImg.jpg");
            file_del = new File(destDir.getPath()+"\\fileImg.jpg");
//            return "上传图片成功!" + "图片路径为：" + toDir;  
        }catch (Exception e){  
            e.printStackTrace();  
        }
        
        //车牌识别
		byte[] imgByte = FileUtil.readFileByBytes(file_del.getPath());
		int size = imgByte.length;
        JSONObject jj = new JSONObject();
		String plateNumber = null;

        // 通用识别url
        String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate";
        try {
            String imgStr = Base64Util.encode(imgByte);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = OrcToken.getToken();
            String result = HttpUtil.post(otherHost, accessToken, params);
            jj = JSONObject.parseObject(result);
            System.out.println(jj);
            plateNumber = JSONObject.parseObject(jj.getString("words_result")).getString("number");
            System.out.println(plateNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

		//提示管理员
		if(StaticClass.channels.get(3) != null){
			StaticClass.channels.get(3).channel().writeAndFlush(new TextWebSocketFrame("车牌:"+plateNumber+"已进场!时间:"+LocalDateTime.now()));
		}
        file_del.delete();
        return plateNumber;
	}
	
	/**
     * 压缩图片
     * @param imgByte
     * @throws IOException 
     */
	@Override
	public String compressPicture(String imgBase64) throws IOException {
		//保存文件
		FileOutputStream fos = null;  
		//删除用文件
		File file_del = null;
        try{  
        	//存储路径 
        	String toDir= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getServletContext().getRealPath("/");
        	 //对android传过来的图片字符串进行解码 
            byte[] buffer =  com.alibaba.fastjson.util.Base64.decodeFast(imgBase64);  
            File destDir = new File(toDir);    
            if(!destDir.exists()) destDir.mkdir();  
            fos = new FileOutputStream(new File(destDir, "fileImg.jpg"));   //保存图片  
            fos.write(buffer);  
            fos.flush();  
            fos.close();    
            //进行压缩
            Thumbnails.of(destDir.getPath()+"\\fileImg.jpg").scale(1f).outputQuality(0.1f).outputFormat("jpg").toFile(destDir.getPath()+"\\fileImg.jpg");
            file_del = new File(destDir.getPath()+"\\fileImg.jpg");
//            return "上传图片成功!" + "图片路径为：" + toDir;  
        }catch (Exception e){  
            e.printStackTrace();  
        }
        
        //车牌识别
		byte[] imgByte = FileUtil.readFileByBytes(file_del.getPath());
		//加密
        String imgStr = Base64Util.encode(imgByte);
        
        file_del.delete();
        return imgStr;
	}
    
}

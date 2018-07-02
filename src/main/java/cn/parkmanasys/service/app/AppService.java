package cn.parkmanasys.service.app;

import java.io.IOException;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import cn.parkmanasys.entity.CarOwnerUser;
/**
 * 接口
 * 
 * @author 刘大仙
 *
 */
@WebService(name = "AppService", // 暴露服务名称
		targetNamespace = "http://webservice.parkmanasys.cn"// 命名空间,一般是接口的包名倒序
)
public interface AppService {
    @WebMethod
	@WebResult(name = "String", targetNamespace = "")
	public CarOwnerUser appLogin(@WebParam(name = "userName") String name, @WebParam(name = "password") String password);
    
    @WebMethod
   	@WebResult(name = "String", targetNamespace = "")
   	public boolean updateUserInfo(@WebParam(name = "id") Integer id, 
   			@WebParam(name = "nickName") String nickName,
   			@WebParam(name = "sex") String sex,
   			@WebParam(name = "bornDate") Date bornDate); 
    
    @WebMethod
   	@WebResult(name = "String", targetNamespace = "")
   	public CarOwnerUser getUserInfoById(@WebParam(name = "id") Integer id);

    /**
     * 获取照片二进制流，识别车牌
     * @param imgByte
     */
    @WebMethod
   	@WebResult(name = "String", targetNamespace = "")
    public String plateNumberDiscriminate(String imgBase64) throws IOException ;
    
	/**
     * 压缩图片
     * @param imgByte
     * @throws IOException 
     */
	public String compressPicture(String imgBase64) throws IOException ;
       
}

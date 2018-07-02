package cn.parkmanasys.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.service.parkingaccount.ParkingAccountService;
import cn.parkmanasys.util.StaticClass;
import cn.parkmanasys.util.annotation.LogTypeEnum;
import cn.parkmanasys.util.annotation.SystemLog;


@Controller
public class LogController {
	@Resource
	private ParkingAccountService parkingAccountService;
	
	@RequestMapping("/login")
	@SystemLog(logType = LogTypeEnum.OPERATION_LOG, operationDesc = "登陆系统")
	public String logTest(ParkingAccount logAccount, HttpSession session){
		ParkingAccount logUser = null;
		
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(logAccount.getAccountName(), logAccount.getAccountPassword());

		try {
			currentUser.login(token);
			
			logUser = parkingAccountService.getAccountByName(token.getUsername());
			session.setAttribute("logUser", logUser);
			StaticClass.currentAccount = logUser;
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/login/login.jsp";//返回登陆界面
		}
		
		if(logUser.getAccountType().getTypeName().equals("管理员")){
			return "redirect:/AdministratorAccount/index.jsp";//主界面
		}else if(logUser.getAccountType().getTypeName().equals("停车场")){
			return "redirect:/ParkAccount/index.jsp";//主界面
		}
		
		return "redirect:/login/login.jsp";//返回登陆界面
	}
	

	
//	@RequestMapping("/jsontest")
//	@ResponseBody
//	public String jsontest(){
//		List<ParkingAccount> tList =  parkingAccountService.findAll();
//		
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("data", tList);
//		result.put("msg", "");
//		result.put("count", 100);
//		result.put("code", 0);
//		
//		return JSONArray.toJSONString(result);
//	}
}

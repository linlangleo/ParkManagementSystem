package cn.parkmanasys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.service.parkingaccount.ParkingAccountService;

@Controller
@RequestMapping("/ParkingAccount")
public class ParkingAccountController {
	
	@Resource
	private ParkingAccountService parkingacountService;
	
	/**
	 * 查看或修改账户信息 
	 */
	@RequestMapping("/Accountinformation")
	public String Accountinformation(String eventType,HttpSession session)
	{
		if(eventType!=null&&eventType.equals("detail"))
		{
			ParkingAccount Park=(ParkingAccount) session.getAttribute("logUser");
			Park=parkingacountService.getParkingAccountbyId(Park.getId());
			session.setAttribute("userinfo", Park);
			return "ParkAccount/layerTemp/ParkingAcountTemp";
		}
		return "ParkAccount/layerTemp/ParkingAcountUpdateTemp";
	}
	
	/**
	 * 查看账户信息 
	 */
	@RequestMapping(value="/viewParkingAcount")
	public String parkingAccount(String eventType,HttpSession session)
	{
		ParkingAccount park=(ParkingAccount) session.getAttribute("logUser");
		park=parkingacountService.getParkingAccountbyId(park.getId());
		session.setAttribute("parkAcount", park);
		if(eventType.equals("detail"))
		{
		 return	"AdministratorAccount/layerTemp/ParkingAcountTemp";
		}
		
		return "AdministratorAccount/layerTemp/ParkingAcountUpdateTemp";
	}
	
	/**
	 * 验证密码 
	 */
	@RequestMapping(value="/parkingPasswordValidation")
	@ResponseBody
	public Object parkingPasswordValidation(String oldPassword,String newPassword,String newPassword1,HttpSession session)
	{
		Map<String,Object> result= new HashMap<String,Object>();
		ParkingAccount Park=(ParkingAccount) session.getAttribute("logUser");
		result.put("result", true);
		if(oldPassword==null || oldPassword.equals(""))
		{
			result.put("result", "原密码为空");
		}else if(newPassword==null  || newPassword1.equals(""))
		{
			result.put("result", "新密码为空");
			return	result;
		}else if(newPassword1==null  || newPassword1.equals(""))
		{
		    result.put("result", "新密码为空");
		    return	result;
		}
		if(!oldPassword.equals(Park.getAccountPassword()))
		{
			result.put("result", "原密码不正确");
			return 	result;
		}
		if(!newPassword.equals(newPassword1))
		{
			result.put("result", "两次密码不一致");
		}
		return 	result;
	}
	/**
	 * 修改密码  
	 */
	@RequestMapping(value="/parkingPasswordUpdate")
	@ResponseBody
	public Object parkingPasswordUpdate(String newPassword,HttpSession session)
	{
		Map<String,Object> result= new HashMap<String,Object>();
		ParkingAccount Park=(ParkingAccount) session.getAttribute("logUser");
		Park.setAccountPassword(newPassword);
		ParkingAccount ps=null;
		result.put("result","true");
		ps=parkingacountService.updateParkingAccountPassword(Park);
		if(ps==null)
		{
			result.put("result","false");
		}
		return result;
	}
	
	/**
	 * 注销 
	 */
	@RequestMapping(value="/logout")
	@ResponseBody
	public Object logout(HttpSession session)
	{
		session.removeAttribute("logUser");
		Map<String,Object> result= new HashMap<String,Object>();
		result.put("url", "/login/login.jsp");
		return result;
	}
}

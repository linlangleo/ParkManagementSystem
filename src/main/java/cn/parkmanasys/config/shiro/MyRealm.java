package cn.parkmanasys.config.shiro;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.parkmanasys.dao.ParkingAccountMapper;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.service.parkingaccount.ParkingAccountService;


public class MyRealm extends AuthorizingRealm{

	@Resource
	private ParkingAccountService parkingAccountService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) { //传入身份集合
		//根据身份信息获取用户名
		String userName=(String) principals.getPrimaryPrincipal();
		//获取信息认证对象
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		//设置当前用户的角色
//		authorizationInfo.setRoles(userService.getRoles(userName));
		//设置当前用户的权限
//		authorizationInfo.setStringPermissions(userService.getPermissions(userName));
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//根据认证信息获取用户名
		String userName=(String) token.getPrincipal();
		//获取用户对象
		ParkingAccount account =  parkingAccountService.getAccountByName(userName);
		
		if(account!=null) {
			//获取认证对象 第一个参数用户名 第二个密码 第三个为realName 可以随便取
			AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(account.getAccountName(), account.getAccountPassword(),"xx");
			//返回认证信息
			return authcInfo;
		}
		return null;
	}

}

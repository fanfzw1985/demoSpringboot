package cn.fzw.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.fzw.service.LoginService;
import cn.fzw.vo.SysPermission;
import cn.fzw.vo.SysRole;
import cn.fzw.vo.Users;

/*** <p>Title:MyShiroRealm </p>
* <p>Description: </p>
* <p>Company: </p> 
* @author 樊志文
* @date 2018年5月23日*/
public class MyShiroRealm extends AuthorizingRealm{
	@Autowired
	private LoginService loginService;
	
	//清除缓存
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	    Users userInfo  = (Users)principals.getPrimaryPrincipal();
	    List<SysRole> roleList=null;
	    roleList=loginService.selectRoleByUserId(userInfo.getId());
	    if(roleList!=null){
	    	for (SysRole sysRole : roleList) {
	    		 authorizationInfo.addRole(sysRole.getRole());
	    		 List<SysPermission> permissionList=null;
	    		 permissionList=loginService. selectPermissionListByRoleId(sysRole.getId());
	    		 if(permissionList!=null) {
	    			  for (SysPermission sysPermission : permissionList) {
	    			 authorizationInfo.addStringPermission(sysPermission.getPermission());
				}
	    		 }
	    		
			}
	    }
/*	    
	    for(SysRole role:userInfo.getRoleList()){
	        authorizationInfo.addRole(role.getRole());
	        for(SysPermission p:role.getPermissions()){
	            authorizationInfo.addStringPermission(p.getPermission());
	        }
	    }*/
	    return authorizationInfo;	
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	    //获取用户的输入的账号.
	    String username = (String)token.getPrincipal();
	    if(username==null||username.length()==0) {
	    	return null;
	    }
	    //通过username从数据库中查找 User对象，如果找到，没找到.
	    //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
	    Users userInfo=null;
		try {
			userInfo = loginService.selectByUsername(username);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    if(userInfo == null){
	        return null;
	    }
	    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
	            userInfo, //用户名
	            userInfo.getPassword(), //密码 //ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
	            this.getName()  //realm name
	    );
	    return authenticationInfo;
	}
}

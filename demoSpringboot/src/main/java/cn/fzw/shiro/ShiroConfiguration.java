/**
 * 
 */
package cn.fzw.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*** <p>Title:ShiroConfig </p>
* <p>Description: </p>
* <p>Company: </p> 
* @author 樊志文
* @date 2018年5月23日*/
@Configuration
public class ShiroConfiguration {
		@Bean
	    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
	        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();	        
	       // Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();//获取filters
			//filters.put("authc", new CustomFormAuthenticationFilter());//将自定义 的FormAuthenticationFilter注入shiroFilter中 
			//shiroFilterFactoryBean.setFilters(filters);
	        shiroFilterFactoryBean.setSecurityManager(securityManager); 
	        //拦截器.
	        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
	        // 配置不会被拦截的链接 顺序判断
	        //filterChainDefinitionMap.put("/static/**", "anon");
	        filterChainDefinitionMap.put("/imags/**", "anon");
	        filterChainDefinitionMap.put("/util/**", "anon");
	        filterChainDefinitionMap.put("/jsp/login.jsp", "anon");
	        filterChainDefinitionMap.put("/login/login.action", "anon");
	        filterChainDefinitionMap.put("/utils/**", "anon");
	        filterChainDefinitionMap.put("/weixin/**", "anon");
	        //filterChainDefinitionMap.put("/login", "authc"); 
	        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
	        filterChainDefinitionMap.put("/login/logout.action", "logout");
	        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
	        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
	        //filterChainDefinitionMap.put("/login/userInfo.action", "perms[userInfo:add]");
	        filterChainDefinitionMap.put("/**", "authc");
	        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
	        //shiroFilterFactoryBean.setLoginUrl("/login/login.action");
	        // 登录成功后要跳转的链接/
	        shiroFilterFactoryBean.setSuccessUrl("/login/homepage.action");
	        //未授权界面;
	        shiroFilterFactoryBean.setUnauthorizedUrl("/login/Error.action");
	        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
			
	        return shiroFilterFactoryBean;
	    }
		/**  
	     *  开启shiro aop注解支持.  
	     *  使用代理方式;所以需要开启代码支持;  
	     * @param securityManager  
	     * @return  
	     */ 
	    @Bean
	    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
	        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
	        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
	        return authorizationAttributeSourceAdvisor;
	    }

	    @Bean
	    public MyShiroRealm myShiroRealm(){
	        MyShiroRealm myShiroRealm = new MyShiroRealm();
	        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());  
	        return myShiroRealm;
	    }
	    

	    /**
		 * @return
		 */
	    @Bean  
	    public HashedCredentialsMatcher hashedCredentialsMatcher() {  
	        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();  
	        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;  
	        hashedCredentialsMatcher.setHashIterations(1);//散列的次数，比如散列两次，相当于 md5(md5(""));  
	        return hashedCredentialsMatcher;  
	    }  

		@Bean
	    public SecurityManager securityManager(){
	        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
	        securityManager.setRealm(myShiroRealm());
	        securityManager.setCacheManager(ehCacheManager());
	        //securityManager.setRememberMeManager(rememberMeManager());
	        securityManager.setSessionManager(sessionManager());
	        return securityManager;
	    }

		/**
		 * @param key 
		 * @return
		 */
	    @Bean
	    public SessionManager sessionManager() {
			DefaultWebSessionManager defaultWebSessionManager=new DefaultWebSessionManager();
			defaultWebSessionManager.setCacheManager(ehCacheManager());  
			defaultWebSessionManager.setGlobalSessionTimeout(1800000);  
			defaultWebSessionManager.setDeleteInvalidSessions(true);  
			defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);  
			//defaultWebSessionManager.setDeleteInvalidSessions(true); 
			return defaultWebSessionManager;
		}


	    @Bean
	    public  CookieRememberMeManager rememberMeManager() {
			CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		       cookieRememberMeManager.setCookie(rememberMeCookie());
		       //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
		       cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
		       return cookieRememberMeManager;
		}


	    @Bean
	    public Cookie rememberMeCookie() {
			//这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		       SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		       //<!-- 记住我cookie生效时间30天 ,单位秒;-->
		       simpleCookie.setMaxAge(7*24*3600);
		       return simpleCookie;
		}

	    @Bean
	    public EhCacheManager ehCacheManager(){
	        EhCacheManager cacheManager = new EhCacheManager();
	        cacheManager.setCacheManagerConfigFile("classpath:shiro-ehcache.xml");
	        return cacheManager;
	    }
}

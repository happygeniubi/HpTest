package com.happyge.empl.realm;

import com.happyge.empl.model.HllcUser;
import com.happyge.empl.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

   @Autowired
   private UserService userService;

    @Override
    public String getName() {
        return "UserRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        System.out.println("进入授权===================");
        //获取用户名
        String username = (String)pc.getPrimaryPrincipal();
        //new一个授权信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //给授权信息设置角色集合,只能放角色名
        authorizationInfo.setRoles(userService.listRoles(username));
        //给授权信息设置权限集合
        authorizationInfo.setStringPermissions(userService.listPermissions(username));
        //返回用户授权信息
        return authorizationInfo;
    }


    /**
     * 获取身份验证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名
        System.out.println("开始认证================s");
        String username = (String)token.getPrincipal();
        //根据用户名获取User对象
        HllcUser user = userService.findUserbyUsername(username);
        if(user == null) {
            //找不到账号
            throw new UnknownAccountException();
        }
        /*
         * new一个身份验证信息
         */
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
            //用户名
            user.getUname(),
            //从数据库中查出的密文密码
            user.getUpassword(),
          //盐值
            ByteSource.Util.bytes(user.getUsalt()),
          //realm名称
            getName()
        );
        Session session = getSession();
        //将当前用户放进session
        session.setAttribute("username", username);
        /*
         * 返回身份验证信息，将交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
         * CredentialsMatcher使用盐加密传入的明文密码和此处的密文密码进行匹配
         */
        return authenticationInfo;
    }

    /**
     * 获取shiro封装的session
     */
    private Session getSession(){
        try{
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null){
                session = subject.getSession();
            }
                if (session != null){
                return session;
                }
                }catch (InvalidSessionException e){

                }
                return null;
                }

                }

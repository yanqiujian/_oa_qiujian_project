package com.qiujian.realname;

import com.qiujian.dto.UserDto;
import com.qiujian.service.userservice.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MybatisRealm extends AuthorizingRealm{
    @Override
    public String getName(){return "MybatisRealm";}
    @Autowired
    private IUserService userService;

    /**
     * 权限和角色授权
     * @param principalCollection 是用户传进来的用户名
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前的用户名
        String userName = principalCollection.getPrimaryPrincipal().toString();
        //查询当前用户的角色和权限
        List<String> jss = userService.queryJsByName(userName);
        List<String> qxs = userService.queryQXByName(userName);
        //封装用户的角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //封装用户角色
        simpleAuthorizationInfo.addRoles(jss);
        //封装用户所有权限
        simpleAuthorizationInfo.addStringPermissions(qxs);
        return simpleAuthorizationInfo;
    }

    /**
     * 用来进行登陆验证的方法
     * @param authenticationToken token 用户提交的口令，就是UsernamePasswordToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //AuthenticationToken是UsernamePasswordToken的父类
        //1:将AuthenticationToken转换为UsernamePasswordToken类型
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //2:获取用户提交的用户名和密码
        String username = usernamePasswordToken.getUsername();
        char[] password = usernamePasswordToken.getPassword();
        //3:将密码转换为字符串类型
        String passwordStr = new String(password, 0, password.length);
        //4:使用M层方法查询数据库数据
        UserDto userDto = userService.queryUserByName(username);
        //5：对用户名以及密码进行验证
        if (userDto == null){
            throw new UnknownAccountException("你输入的用户名不存在，请注册后在登陆");
        }
        //对用户传入的密码的明文进行加密
        SimpleHash simpleHash = new SimpleHash("MD5", passwordStr, userDto.getUserName());
        //判断密码
        if (!userDto.getUserPassword().equals(simpleHash.toString())){
            throw new IncorrectCredentialsException("密码错误");
        }
        //判断密码
//        if (!tbUserdto.getPassword().equals(passwordStr)){
//            throw new IncorrectCredentialsException("您输入的密码有误");
//        }
        //6:验证通过 参数1和2：用户名和密码，参数3：是当前realm的唯一标识---realm的名称
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, passwordStr, getName());
        return simpleAuthenticationInfo;
    }
}

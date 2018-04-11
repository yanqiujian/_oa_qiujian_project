package com.qiujian.dao.userdao;

import com.qiujian.dto.BumenDto;
import com.qiujian.dto.PemissionDto;
import com.qiujian.dto.RolesDto;
import com.qiujian.dto.UserDto;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDAO extends SqlSessionDaoSupport implements IUserDAO {
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    /**
     * 判断用户登陆名称是否正确
     *
     * @param userName
     * @return
     */
    public UserDto queryUserByName(String userName) {
        UserDto userDto = getSqlSession().selectOne("com.qiujian.dto.UserMapper.queryByName", userName);
        return userDto;
    }

    /**
     * 通过用户名查询对应的角色信息
     *
     * @param userName
     */
    public List<RolesDto> queryJsByName(String userName) {
        List<RolesDto> list = getSqlSession().selectList("com.qiujian.dto.RolesMapper.getRolesByName", userName);
        return list;
    }

    /**
     * 通过用户名查询对应角色的权限
     *
     * @param userName
     */
    public List<PemissionDto> queryQXByName(String userName) {
        List<PemissionDto> list = getSqlSession().selectList("com.qiujian.dto.PemissionDtoMapper.queryPemissionDtoByName", userName);
        return list;
    }

    /**
     * 根据用户名。查询所在的部门
     *
     * @param userName
     */
    public BumenDto queryBMByName(String userName) {
        BumenDto o = getSqlSession().selectOne("com.qiujian.dto.BumenMapper.getBumenByName", userName);
        return o;
    }

    /**
     * 根据部门信息查询该部门主管姓名
     *
     * @param bmName
     */
    public UserDto queryUserNameByBumenName(String bmName) {
        UserDto o = getSqlSession().selectOne("com.qiujian.dto.UserMapper.queryBumenZhuGuan",bmName);
        return o;
    }

    /**
     * 查询公司CEO姓名
     */
    public UserDto queryUserNameCEO() {
        UserDto userDto = getSqlSession().selectOne("com.qiujian.dto.UserMapper.getCEOName");
        return userDto;
    }
}

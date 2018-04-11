package com.qiujian.dao.qingjiadao;

import com.qiujian.dto.QingjiaDto;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
@Repository
public class QingjiaDAO extends SqlSessionDaoSupport implements IQingjiaDAO {
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    /**
     * 添加一条请假记录
     *
     * @param qingjiaDto
     */
    public void insertHistory(QingjiaDto qingjiaDto) {
        getSqlSession().insert("com.qiujian.dto.QingjiaDtoMapper.insertQingjia",qingjiaDto);
    }

    /**
     * 根据审批人名字查询其名下的审批记录
     *
     * @param name
     */
    public List<QingjiaDto> queryZhuguanList(String name) {
        List<QingjiaDto> list = getSqlSession().selectList("com.qiujian.dto.QingjiaDtoMapper.getQingjiaBySPR", name);
        return list;
    }

    /**
     * 根据提交人姓名查询其补签记录
     *
     * @param name
     */
    public List<QingjiaDto> queryApllyList(String name) {
        List<QingjiaDto> list = getSqlSession().selectList("com.qiujian.dto.QingjiaDtoMapper.getQjApplynameByName", name);
        return list;
    }

    /**
     * 修改补签状态为已审批
     *
     * @param id
     */
    public void updateZhuangtai(String id) {
        getSqlSession().update("com.qiujian.dto.QingjiaDtoMapper.updateShenpi", id);
    }

}

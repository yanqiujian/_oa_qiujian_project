package com.qiujian.dao.historydao;

import com.qiujian.dto.History;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoryDAO extends SqlSessionDaoSupport implements IHistoryDao {
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    /**
     * 添加一条历史记录
     *
     * @param history
     */
    public void insertHistory(History history) {
//        String hsZhuguanname = history.getHsZhuguanname();
//        String hsTime = history.getHsTime();
//        String hsGanwei = history.getHsGanwei();
//        String hsJihao = history.getHsJihao();
//        String hsFangshi = history.getHsFangshi();
//        String hsDidian = history.getHsDidian();
//        String hsApplyname = history.getHsApplyname();
//        String hsDepartment = history.getHsDepartment();
        getSqlSession().insert("com.qiujian.dto.HistoryMapper.insertHistory",history);
//                insert("com.qiujian.dto.HistoryMapper.insertHistory",
//                (hsZhuguanname,hsTime,hsGanwei,hsJihao,hsFangshi,
//                hsDidian,hsApplyname,hsDepartment))
    }

    /**
     * 根据审批人名字查询其名下的审批记录
     */
    public List<History> queryZhuguanList(String name) {
        List<History> list = getSqlSession().selectList("com.qiujian.dto.HistoryMapper.getHistoryByZhuguanname", name);
        return list;
    }

    /**
     * 根据提交人姓名查询其补签记录
     *
     * @param name
     */
    public List<History> queryApllyList(String name) {
        List<History> list = getSqlSession().selectList("com.qiujian.dto.HistoryMapper.gethsApplynameByName",name);
        return list;
    }

    /**
     * 修改补签状态为已审批
     *
     * @param id
     */
    public void updateZhuangtai(String id) {
        getSqlSession().update("com.qiujian.dto.HistoryMapper.updateShenpi",id);
    }
}

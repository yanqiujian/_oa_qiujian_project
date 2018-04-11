package com.qiujian.dao.historydao;

import com.qiujian.dto.History;

import java.util.List;

public interface IHistoryDao {
    /**
     * 添加一条历史记录
     */
    void insertHistory(History history);
    /**
     * 根据审批人名字查询其名下的审批记录
     */
    List<History> queryZhuguanList(String name);
    /**
     * 根据提交人姓名查询其补签记录
     */
    List<History> queryApllyList(String name);
    /**
     * 修改补签状态为已审批
     */
    void updateZhuangtai(String id);
}

package com.qiujian.dao.qingjiadao;

import com.qiujian.dto.QingjiaDto;

import java.util.List;

public interface IQingjiaDAO {
    /**
     * 添加一条请假记录
     */
    void insertHistory(QingjiaDto qingjiaDto);
    /**
     * 根据审批人名字查询其名下的审批记录
     */
    List<QingjiaDto> queryZhuguanList(String name);
    /**
     * 根据提交人姓名查询其补签记录
     */
    List<QingjiaDto> queryApllyList(String name);
    /**
     * 修改补签状态为已审批
     */
    void updateZhuangtai(String id);

}

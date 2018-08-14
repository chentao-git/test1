package com.xxm.it.activiti.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.xxm.it.activiti.vo.Leave;

/**
 * 请假实体管理接口
 *
 * @author HenryYan
 */
@Component
public interface LeaveDao extends CrudRepository<Leave, Long> {
}

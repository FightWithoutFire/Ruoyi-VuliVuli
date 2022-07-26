package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.datasource.annotation.DataSource;
import com.ruoyi.common.datasource.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysOrderMapper;
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.system.service.ISysOrderService;


/**
 * 订单Service业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysOrderServiceImpl implements ISysOrderService 
{
    @Autowired
    private SysOrderMapper myShardingMapper;

    /**
     * 查询订单
     * 
     * @param orderId 订单编号
     * @return 订单信息
     */
    @Override
    @DataSource(DataSourceType.SHARDING)
    public SysOrder selectSysOrderById(Long orderId)
    {
        return myShardingMapper.selectSysOrderById(orderId);
    }

    /**
     * 查询订单列表
     * 
     * @param sysOrder 订单信息
     * @return 订单列表
     */
    @Override
    @DataSource(DataSourceType.SHARDING)
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder)
    {
        return myShardingMapper.selectSysOrderList(sysOrder);
    }

    /**
     * 新增订单
     * 
     * @param sysOrder 订单
     * @return 结果
     */
    @Override
    @DataSource(DataSourceType.SHARDING)
    public int insertSysOrder(SysOrder sysOrder)
    {
        return myShardingMapper.insertSysOrder(sysOrder);
    }
}

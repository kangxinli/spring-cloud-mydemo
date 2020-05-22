package com.sample.demo.storage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sample.demo.storage.mapper.StorageMapper;
import com.sample.demo.storage.model.Storage;

@Service
public class StorageService {
    @Resource
    private StorageMapper storageMapper;

    /**
     * 减库存
     * 
     * @param commodityCode 商品编号
     * @param count 数量
     */
    //@Transactional(rollbackFor = Exception.class)
    public void deduct(String commodityCode, int count) {
        QueryWrapper<Storage> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new Storage().setCommodityCode(commodityCode));
        Storage storage = storageMapper.selectOne(wrapper);
        storage.setCount(storage.getCount() - count);

        storageMapper.updateById(storage);
    }
}

package com.btw.parser.mapper;

import com.btw.parser.model.AuditorExchange;

import java.util.List;

/**
 * Created by ydc on 2020/9/13.
 */
public interface ExchangeMapper {
    void batchInsert(List<AuditorExchange> list);
}

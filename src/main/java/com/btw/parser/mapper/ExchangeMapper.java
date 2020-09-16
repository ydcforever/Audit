package com.btw.parser.mapper;

import com.btw.parser.model.AuditorExchange;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ydc on 2020/9/13.
 */

@Repository
public interface ExchangeMapper {
    void batchInsert(List<AuditorExchange> list);
}

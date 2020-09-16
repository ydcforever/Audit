package com.btw.parser.mapper;

import com.btw.parser.model.AuditorCouponTest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponTestMapper {
    void batchInsert(List<AuditorCouponTest> list);
}

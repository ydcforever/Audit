package com.btw.parser.mapper;

import com.btw.parser.model.AuditorCouponTaxTest;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CouponTaxTestMapper {
    void batchInsert(List<AuditorCouponTaxTest> list);
}

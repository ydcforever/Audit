package com.btw.parser.mapper;

import com.btw.parser.model.AuditorStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusMapper {
    void batchInsert(List<AuditorStatus> list);
}

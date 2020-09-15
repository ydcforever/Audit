package com.btw.parser.mapper;

import com.btw.parser.model.AuditorStatus;

import java.util.List;

public interface StatusMapper {
    void batchInsert(List<AuditorStatus> list);
}

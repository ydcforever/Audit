package com.btw.parser.service;

import com.btw.parser.mapper.StatusMapper;
import com.btw.parser.model.AuditorStatus;
import com.fate.file.parse.batch.BatchInsertDB;
import com.fate.file.parse.batch.BatchPool;
import com.fate.file.parse.processor.FileProcessor;
import com.fate.file.parse.processor.LineProcessor;
import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class StatusParser implements IParser {

    @Autowired
    private StatusMapper statusMapper;
    
    @Override
    public void parse(File file) throws Exception {
        BatchInsertDB<AuditorStatus> insertDB = new BatchInsertDB<AuditorStatus>(){
            @Override
            public void doWith(String s, List<AuditorStatus> list) throws Exception{
                statusMapper.batchInsert(list);
            }
        };
        BatchPool<AuditorStatus> pool = new BatchPool<AuditorStatus>("", insertDB, 1000);
        pool.init(new AuditorStatus().test());
        LineProcessor<Object> processor = new LineProcessor<Object>() {
            @Override
            public void doWith(String line, int lineNo, String fileName, Object object) throws Exception {
                AuditorStatus row = pool.getBatchRow();
                String[] split = line.split(",");
                row.setTicketNo(split[0]);
                row.setCnjTicketNo(split[1]);
                row.setIssueDate(split[2]);
                row.setOperation(split[3]);
                row.setCouponStatus(split[4]);
                row.setOriSource(fileName);
                pool.tryBatch();
            }
        };
        FileProcessor.getInstance().process(file, processor);
        pool.restBatch();
        
        
    }
}

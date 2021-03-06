package com.btw.parser.service;

import com.btw.parser.mapper.ExchangeMapper;
import com.btw.parser.model.AuditorExchange;
import com.btw.parser.utils.ParserUtil;
import com.fate.file.parse.batch.BatchInsertDB;
import com.fate.file.parse.batch.BatchPool;
import com.fate.file.parse.processor.FileProcessor;
import com.fate.file.parse.processor.LineProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * Created by ydc on 2020/9/13.
 */
@Service
public class ExchangeParser implements IParser{

    @Autowired
    private ExchangeMapper exchangeMapper;

//    private BatchPool<AuditorExchange> pool = buildPool();

    public void parse(File file) throws Exception {
        BatchPool<AuditorExchange> pool = getPool();
        LineProcessor<Object> processor = new LineProcessor<Object>() {
            @Override
            public void doWith(String line, int lineNo, String fileName, Object object) throws Exception {
                AuditorExchange row = pool.getBatchRow();
                String[] split = line.split(",");
                row.setBalMonth(split[0]);
                row.setCnjTicketNo(split[1]);
                row.setIssueDate(ParserUtil.dateFormat(split[2]));
                row.setCnjNo(split[3]);
                row.setOrgTicketNo(split[4]);
                row.setExchangeStatus(split[5]);
                row.setOrgIssueDate(split[6]);
                row.setSource(split[7]);
                row.setOriSource(fileName);
                pool.tryBatch();
            }
        };
        FileProcessor.getInstance().process(file, processor);
        pool.restBatch();
        pool.close();
    }

    private BatchPool<AuditorExchange> getPool(){
        BatchInsertDB<AuditorExchange> insertDB = new BatchInsertDB<AuditorExchange>(){
            @Override
            public void doWith(String s, List<AuditorExchange> list) throws Exception{
                exchangeMapper.batchInsert(list);
            }
        };
        BatchPool<AuditorExchange> pool = new BatchPool<AuditorExchange>("", insertDB, 500);
        pool.init(new AuditorExchange().test());
        return pool;
    }
}

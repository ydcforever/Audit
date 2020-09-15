package com.btw.parser.service;

import com.btw.parser.mapper.CouponTaxTestMapper;
import com.btw.parser.model.AuditorCouponTaxTest;
import com.fate.file.parse.batch.BatchInsertDB;
import com.fate.file.parse.batch.BatchPool;
import com.fate.file.parse.processor.FileProcessor;
import com.fate.file.parse.processor.LineProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class CouponTaxTestParser implements IParser  {

    @Autowired
    private  CouponTaxTestMapper couponTaxTestMapper;

    @Override
    public void parse(File file) throws Exception {
        BatchInsertDB<AuditorCouponTaxTest> insertDB = new BatchInsertDB<AuditorCouponTaxTest>(){
            @Override
            public void doWith(String s, List<AuditorCouponTaxTest> list) throws Exception{
                couponTaxTestMapper.batchInsert(list);
            }
        };
        BatchPool<AuditorCouponTaxTest> pool = new BatchPool<AuditorCouponTaxTest>("", insertDB, 1000);
        pool.init(new AuditorCouponTaxTest().test());
        LineProcessor<Object> processor = new LineProcessor<Object>() {
            @Override
            public void doWith(String line, int lineNo, String fileName, Object object) throws Exception {
                AuditorCouponTaxTest row = pool.getBatchRow();
                String[] split = line.split(",");
                row.setTicketNo(split[0]+split[1]);
                row.setTaxCode(split[2]);
                row.setTaxDefinition(split[3]);
                row.setSalesCurrencyCode(split[4]);
                row.setTaxAmountReceived(Double.parseDouble(split[18]));
                //row.setTaxAmountDue(Double.parseDouble(split[6]));
                //row.setDiffAmount(Double.parseDouble(split[7]));
                row.setLineNo(lineNo);
                //row.setDiffPrecent(Double.parseDouble(split[9]));

                pool.tryBatch();
            }
        };
        FileProcessor.getInstance().process(file, processor);
        pool.restBatch();


    }
}
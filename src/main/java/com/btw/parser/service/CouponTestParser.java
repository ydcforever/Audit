package com.btw.parser.service;

import com.btw.parser.mapper.CouponTaxTestMapper;
import com.btw.parser.mapper.CouponTestMapper;
import com.btw.parser.model.AuditorCouponTest;
import com.fate.file.parse.batch.BatchInsertDB;
import com.fate.file.parse.batch.BatchPool;
import com.fate.file.parse.processor.FileProcessor;
import com.fate.file.parse.processor.LineProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class CouponTestParser implements IParser {

    @Autowired
    private CouponTestMapper couponTestMapper;
    
    @Override
    public void parse(File file) throws Exception {
        BatchInsertDB<AuditorCouponTest> insertDB = new BatchInsertDB<AuditorCouponTest>(){
            @Override
            public void doWith(String s, List<AuditorCouponTest> list) throws Exception{
                couponTestMapper.batchInsert(list);
            }
        };
        BatchPool<AuditorCouponTest> pool = new BatchPool<AuditorCouponTest>("", insertDB, 1000);
        pool.init(new AuditorCouponTest().test());
        LineProcessor<Object> processor = new LineProcessor<Object>() {
            @Override
            public void doWith(String line, int lineNo, String fileName, Object object) throws Exception {
                AuditorCouponTest row = pool.getBatchRow();
                String[] split = line.split(",");
                row.setTicketNo(split[0]+split[1]);
                row.setSalesCurrencyCode(split[4]);
                row.setRouting(split[5]);
                row.setIssueDate(split[6]);
                row.setClazz(split[9]);
                row.setPaxType(split[10]);
                row.setFare(Double.parseDouble(split[11]));
                row.setEtType(split[12]);
                row.setSalesType(split[13]);
                row.setSalesStation(split[14]);
                row.setGdsSystem(split[15]);
                row.setSalesSource(split[16]);
                row.setAgentIataNo(split[17]);
                row.setOperatingCarrier(split[19]);
                row.setOriSource(fileName);
                //row.setFiller1(split[18]);
                //row.setTicketUnionNo(split[19]);
                row.setDepartureDate(split[7]);
                row.setArriveDate(split[8]);
                pool.tryBatch();
            }
        };
        FileProcessor.getInstance().process(file, processor);
        pool.restBatch();


    }
}

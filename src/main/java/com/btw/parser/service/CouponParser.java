package com.btw.parser.service;

import com.btw.parser.mapper.CouponTaxTestMapper;
import com.btw.parser.mapper.CouponTestMapper;
import com.btw.parser.model.AuditorCouponTaxTest;
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
public class CouponParser implements IParser {

    @Autowired
    private CouponTestMapper couponTestMapper;

    @Autowired
    private CouponTaxTestMapper couponTaxTestMapper;

    private String parent = "";

//    private BatchPool<AuditorCouponTest> ticketPool = getTicketPool();
//
//    private BatchPool<AuditorCouponTaxTest> taxPool = getTaxPool();

    @Override
    public void parse(File file) throws Exception {
        BatchPool<AuditorCouponTest> ticketPool = getTicketPool();
        BatchPool<AuditorCouponTaxTest> taxPool = getTaxPool();

        LineProcessor<Object> processor = new LineProcessor<Object>() {
            @Override
            public void doWith(String line, int lineNo, String fileName, Object object) throws Exception {
                String[] split = line.split(",");
                String key = split[0] + split[1];
                if (!parent.equals(key)) {
                    parent = key;
                    AuditorCouponTest row = ticketPool.getBatchRow();
                    row.setTicketNo(split[0] + split[1]);
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
                    row.setTicketUnionNo(split[21]);
                    row.setDepartureDate(adTime(split[7], split[22]));
                    row.setArriveDate(adTime(split[8], split[23]));
                    ticketPool.tryBatch();
                }
                AuditorCouponTaxTest taxRow = taxPool.getBatchRow();
                taxRow.setTicketNo(split[0] + split[1]);
                taxRow.setIssueDate(split[6]);
                taxRow.setTaxCode(split[2]);
                taxRow.setTaxDefinition(split[3]);
                taxRow.setSalesCurrencyCode(split[4]);
                taxRow.setTaxAmountReceived(Double.parseDouble(split[18]));
                taxRow.setLineNo(lineNo);
                taxPool.tryBatch();
            }
        };
        FileProcessor.getInstance().process(file, processor);
        ticketPool.restBatch();
        taxPool.restBatch();
        ticketPool.close();
        taxPool.close();
    }

    private BatchPool<AuditorCouponTest> getTicketPool() {
        BatchInsertDB<AuditorCouponTest> insertDB = new BatchInsertDB<AuditorCouponTest>() {
            @Override
            public void doWith(String s, List<AuditorCouponTest> list) throws Exception {
                couponTestMapper.batchInsert(list);
            }
        };
        BatchPool<AuditorCouponTest> pool = new BatchPool<AuditorCouponTest>("", insertDB, 1000);
        pool.init(new AuditorCouponTest().test());
        return pool;
    }

    private BatchPool<AuditorCouponTaxTest> getTaxPool() {
        BatchInsertDB<AuditorCouponTaxTest> insertDB = new BatchInsertDB<AuditorCouponTaxTest>() {
            @Override
            public void doWith(String s, List<AuditorCouponTaxTest> list) throws Exception {
                couponTaxTestMapper.batchInsert(list);
            }
        };
        BatchPool<AuditorCouponTaxTest> pool = new BatchPool<AuditorCouponTaxTest>("", insertDB, 1000);
        pool.init(new AuditorCouponTaxTest().test());
        return pool;
    }

    public static String adTime(String dayStr, String timeStr){
        String[] day = dayStr.split(";");
        String[] time = timeStr.split(";");
        StringBuilder d = new StringBuilder();
        for(int i = 0, len = day.length; i < len; i++){
            if(i> 0) {
                d.append(";");
            }
            d.append(day[i]).append(" ").append(time[0].substring(0, 5));
        }
        return d.toString();
    }

}

package com.btw.parser.service;

import com.btw.parser.utils.ParserUtil;
import com.fate.file.parse.batch.BatchInsertDB;
import com.fate.file.parse.batch.BatchPool;
import com.fate.file.parse.processor.FileProcessor;
import com.fate.file.parse.processor.LineProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ydc on 2020/9/14.
 */
@Service
public class RefundParser implements IParser {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${sql.insert.refund}")
    private String insertRefundSQL;

    @Value("${sql.insert.refundTax}")
    private String insertRefundTaxSQL;

    private String parent = "";

//    private BatchPool<String[]> refundPool = getRefundPool();
//
//    private BatchPool<String[]> refundTaxPool = getRefundTaxPool();

    @Override
    public void parse(File file) throws Exception {
//        refundPool.reset();
//        refundTaxPool.reset();
        BatchPool<String[]> refundPool = getRefundPool();
        BatchPool<String[]> refundTaxPool = getRefundTaxPool();
        LineProcessor<Object> processor = new LineProcessor<Object>() {
            @Override
            public void doWith(String line, int lineNo, String fileName, Object object) throws Exception {
                String[] split = line.split(",");
                String key = split[0] + split[1];
                if(!parent.equals(key)) {
                    parent = key;
                    String[] row = refundPool.getBatchRow();
                    System.arraycopy(split, 0, row, 0, 4);
                    row[4] = fileName;
                    row[5] = split[7];
                    refundPool.tryBatch();
                }

                String[] taxRow = refundTaxPool.getBatchRow();
                System.arraycopy(split, 4, taxRow, 2, 3);
                taxRow[0] = split[0];
                taxRow[1] = split[2];
                refundTaxPool.tryBatch();
            }
        };
        FileProcessor.getInstance().process(file, processor);
        refundPool.restBatch();
        refundTaxPool.restBatch();
        refundPool.close();
        refundTaxPool.close();
    }

    private BatchPool<String[]> getRefundPool(){
        BatchInsertDB<String[]> insertRefund = new BatchInsertDB<String[]>(){
            @Override
            public void doWith(String s, List<String[]> list) throws Exception{
                jdbcTemplate.batchUpdate(insertRefundSQL, new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        String[] a = list.get(i);
                        ps.setString(1, a[0]);
                        ps.setString(2, a[1]);
                        ps.setString(3, ParserUtil.dateFormat(a[2]));
                        ps.setString(4, a[3]);
                        ps.setString(5, a[4]);
                        ps.setString(6, a[5]);
                    }

                    @Override
                    public int getBatchSize() {
                        return list.size();
                    }
                });
            }
        };
        BatchPool<String[]> poolRefund = new BatchPool<String[]>("", insertRefund, 500);
        poolRefund.init(new String[6]);
        return poolRefund;
    };

    private BatchPool<String[]> getRefundTaxPool(){
        BatchInsertDB<String[]> insertDB = new BatchInsertDB<String[]>(){
            @Override
            public void doWith(String s, List<String[]> list) throws Exception{
                jdbcTemplate.batchUpdate(insertRefundTaxSQL, new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        String[] a = list.get(i);
                        ps.setString(1, a[0]);
                        ps.setString(2, ParserUtil.dateFormat(a[1]));
                        ps.setString(3, a[2]);
                        ps.setString(4, a[3]);
                        ps.setBigDecimal(5, str2num(a[4]));
                    }

                    @Override
                    public int getBatchSize() {
                        return list.size();
                    }
                });
            }
        };
        BatchPool<String[]> poolRefundTax = new BatchPool<String[]>("", insertDB, 500);
        poolRefundTax.init(new String[5]);
        return poolRefundTax;
    }

    private BigDecimal str2num(String value) {
        try {
            return new BigDecimal(value);
        } catch (Exception e) {
            return new BigDecimal(0);
        }
    }
}

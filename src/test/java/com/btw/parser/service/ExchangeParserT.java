package com.btw.parser.service;

import com.fate.log.IParserLog;
import com.fate.log.ParserLogger;
import com.fate.log.ParserLoggerProxy;
import com.github.junrar.Junrar;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ExchangeParserT extends TestCase {

    @Autowired
    private ExchangeParser exchangeParser;

    private String unzipDir;


    @Test
    public void testSingle() throws Exception {
        String path = "G:\\解析测试\\itaxCoupon_Refund20200901.rar";
//        exchangeParser.parse(new File(path));
        unzipDir="G:\\解析测试\\";
        File file=new File(path);
        Junrar.extract(file.getPath(), unzipDir);
    }

    @Test
    public void testLog() throws Exception {
        IParser parser = new ParserLoggerProxy(new IParserLog() {
            @Override
            public void insertLog(ParserLogger parserLogger) {

            }

            @Override
            public void updateLog(ParserLogger parserLogger) {

            }
        }, "Audit", "fileName",  exchangeParser).getTarget();
//        parser.parse();
    }
}
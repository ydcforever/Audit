package com.btw.parser.service;

import com.fate.log.IParserLog;
import com.fate.log.ParserLogger;
import com.fate.log.ParserLoggerProxy;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ExchangeParserTest extends TestCase {

    @Autowired
    private ExchangeParser exchangeParser;

    @Test
    public void testSingle() throws Exception {
        String path = "C:\\Users\\T440\\Desktop\\beans\\audit\\itaxCoupon_Exchange20200701.txt";
        exchangeParser.parse(new File(path));
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
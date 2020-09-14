package com.btw.parser.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RefundParserTest extends TestCase {

    @Autowired
    private RefundParser refundParser;

    @Test
    public void testParse() throws Exception {
        String path = "C:\\Users\\T440\\Desktop\\beans\\audit\\itaxCoupon_Refund20200901.txt";
        refundParser.parse(new File(path));
    }

    @Test
    public void testArray() throws Exception {
        String[] t = new String[5];
        String[] src = new String[]{"781","2;","20200805","1000;", "CNY", "CP", "707.00", "TCN"};
        System.arraycopy(src, 4, t, 2, 3);
        for(String s : t) {
            System.out.println(s);
        }
    }
}
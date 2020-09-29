package com.btw.parser.service;

import com.btw.parser.utils.ParserFactory;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CouponParserTest extends TestCase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CouponParser couponParser;

    public void testAdTime() throws Exception {
        String day = "2020-02-07;2020-02-13;2020-02-13";
        String time = "00:00:00;00:00:00;00:00:00";
        String s = CouponParser.adTime(day, time);
        System.out.println(s);
    }

    @Test
    public void test() throws Exception{
        ParserFactory factory = new ParserFactory(jdbcTemplate, "ITAX_COUPON");
//        factory.download();
        factory.unrar(false);
        factory.parse(couponParser, false);
    }
}
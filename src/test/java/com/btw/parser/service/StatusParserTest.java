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
public class StatusParserTest extends TestCase {

    @Autowired
    private StatusParser statusParser;

    @Test
    public void parse() throws Exception{
        String path ="G:\\解析测试\\itaxCoupon_Status20200701.txt";
        statusParser.parse(new File(path));

    }
}
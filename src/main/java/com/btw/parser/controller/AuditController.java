package com.btw.parser.controller;

import com.btw.parser.mapper.ParserLogMapper;
import com.btw.parser.service.CouponParser;
import com.btw.parser.service.ExchangeParser;
import com.btw.parser.service.RefundParser;
import com.btw.parser.service.StatusParser;
import com.btw.parser.utils.FTPFactory;
import com.btw.parser.utils.ParserFactory;
import com.fate.schedule.SteerableSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ydc on 2020/9/13.
 */
@RestController
@RequestMapping("/audit/parser")
public class AuditController {

    @Autowired
    private FTPFactory ftpFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ParserLogMapper logMapper;

    @Autowired
    private ExchangeParser exchangeParser;

    @Autowired
    private RefundParser refundParser;

    @Autowired
    private StatusParser statusParser;

    @Autowired
    private CouponParser couponParser;

    @RequestMapping(value = "/exchange.do", method = RequestMethod.POST)
    @SteerableSchedule(id = "ITAX_EXCHANGE", cron = "0 30 14 ? * *")
    public void exchange() throws Exception {
        ParserFactory factory = new ParserFactory(jdbcTemplate, "ITAX_EXCHANGE").logMapper(logMapper).ftpFactory(ftpFactory);
        factory.download();
        factory.unrar(true);
        factory.parse(exchangeParser, true);
    }

    @RequestMapping(value = "/refund.do", method = RequestMethod.POST)
    @SteerableSchedule(id = "ITAX_REFUND", cron = "0 30 14 ? * *")
    public void refund() throws Exception{
        ParserFactory factory = new ParserFactory(jdbcTemplate, "ITAX_REFUND").logMapper(logMapper).ftpFactory(ftpFactory);
        factory.download();
        factory.unrar(true);
        factory.parse(refundParser, true);
    }


    @RequestMapping(value = "/status.do", method = RequestMethod.POST)
    @SteerableSchedule(id = "ITAX_STATUS", cron = "0 30 14 ? * *")
    public void status() throws Exception{
        ParserFactory factory = new ParserFactory(jdbcTemplate, "ITAX_STATUS").logMapper(logMapper).ftpFactory(ftpFactory);
        factory.download();
        factory.unrar(true);
        factory.parse(statusParser, true);
    }


    @RequestMapping(value = "/coupon.do", method = RequestMethod.POST)
    @SteerableSchedule(id = "ITAX_COUPON", cron = "0 30 14 ? * *")
    public void ticket() throws Exception{
        ParserFactory factory = new ParserFactory(jdbcTemplate, "ITAX_COUPON").logMapper(logMapper).ftpFactory(ftpFactory);
        factory.download();
        factory.unrar(true);
        factory.parse(couponParser, true);
    }
}

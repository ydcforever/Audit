package com.btw.parser.controller;

import com.btw.parser.mapper.ParserLogMapper;
import com.btw.parser.service.ExchangeParser;
import com.btw.parser.service.RefundParser;
import com.btw.parser.utils.ParserFactory;
import com.fate.schedule.SteerableSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ydc on 2020/9/13.
 */
@Controller
@RequestMapping("/audit/parser")
public class AuditController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ExchangeParser exchangeParser;

    @Autowired
    private RefundParser refundParser;

    @Autowired
    private ParserLogMapper logMapper;

    @RequestMapping(value = "/exchange.do", method = RequestMethod.POST)
    @SteerableSchedule(id = "Exchange", cron = "0 30 14 ? * *")
    public void exchange() throws Exception{
        ParserFactory factory = new ParserFactory(jdbcTemplate, "ITAX_EXCHANGE").logMapper(logMapper);
        factory.parseUnrarDir(exchangeParser, false);
    }

    @RequestMapping(value = "/refund.do", method = RequestMethod.POST)
    @SteerableSchedule(id = "Refund", cron = "0 30 14 ? * *")
    public void refund() throws Exception{
        ParserFactory factory = new ParserFactory(jdbcTemplate, "ITAX_REFUND").logMapper(logMapper);
        factory.parseUnrarDir(refundParser, false);
    }
}

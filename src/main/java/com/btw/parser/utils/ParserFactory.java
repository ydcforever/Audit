package com.btw.parser.utils;

import com.btw.parser.mapper.ParserLogMapper;
import com.btw.parser.service.IParser;
import com.fate.file.parse.DBSteerableConfig;
import com.fate.file.transfer.FileSelector;
import com.github.junrar.Junrar;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.util.Map;

/**
 * Created by ydc on 2020/9/13.
 */
public class ParserFactory {

    private DBSteerableConfig config;

    private String fileType;

    private String splitType;

    private String saveDir;

    private String unzipDir;

    private FileSelector fileSelector;

    private ParserLogMapper logMapper = null;

    private boolean valid = true;

    public ParserFactory(JdbcTemplate jdbcTemplate) {
        this.config = new DBSteerableConfig(jdbcTemplate);
        Map<String, Object> info = config.queryFileStorage(fileType);
        if (info == null) {
            valid = false;
        } else {
            this.splitType = info.get("PARSE_TYPE").toString();
            this.saveDir = info.get("SAVE_DIR").toString();
            Object unDir = info.get("UNZIP_DIR");
            if (unDir != null) {
                this.unzipDir = unDir.toString();
                checkDir(this.unzipDir);
            }
            this.fileSelector = new FileSelector(info.get("FEATURE").toString(), info.get("REGEXP").toString());
            Object begin = info.get("BEGIN_FLAG");
            Object end = info.get("END_FLAG");
            if (begin != null) {
                this.fileSelector.begin(begin.toString());
            }
            if (end != null) {
                this.fileSelector.end(end.toString());
            }
        }
    }

    //    public String lastDate(String ftype) {
//
//    }
//
//    public String updateDate(String ftype, String date) {
//
//    }

    public <T> void parseUnrarDir(IParser iParser, boolean delete) {
        File[] files = new File(unzipDir).listFiles();
        assert files != null;
        for (File file : files) {
            String name = file.getName();
            String order = fileSelector.getOrder(name);
            if (fileSelector.acceptFile(name) && fileSelector.acceptOrder(order)) {
                try {
                    //日志代理
//                    IParser pro = new ParserLoggerProxy(logMapper, fileType, name, iParser).getTarget();
                    Junrar.extract(file.getPath(), this.unzipDir);
                    iParser.parse(file);
                    config.updateOrder(fileType, order);
                    if (delete) {
                        file.delete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void checkDir(String path) {
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdir();
        }
    }
}

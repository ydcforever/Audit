package com.btw.parser.utils;

import com.btw.parser.mapper.ParserLogMapper;
import com.btw.parser.service.IParser;
import com.fate.decompress.Unrar5;
import com.fate.file.parse.DBSteerableConfig;
import com.fate.file.transfer.FileSelector;
import com.fate.log.ParserLoggerProxy;
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

    private FTPFactory ftpFactory;

    public ParserFactory(JdbcTemplate jdbcTemplate, String fileType) {
        this.fileType = fileType;
        this.config = new DBSteerableConfig(jdbcTemplate);
        Map<String, Object> info = config.queryNoFtpStorage(fileType);
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

    public ParserFactory ftpFactory(FTPFactory ftpFactory) {
        this.ftpFactory = ftpFactory;
        return this;
    }

    public ParserFactory logMapper(ParserLogMapper logMapper) {
        this.logMapper = logMapper;
        return this;
    }

    public boolean isValid() {
        return valid;
    }

    public void download() throws Exception{
        ftpFactory.download(this.saveDir, fileSelector);
    }

    public void unrar(boolean delete){
        File[] files = new File(saveDir).listFiles();
        for(File file: files) {
            String name = file.getName();
            String order = fileSelector.getOrder(name);
            if (fileSelector.acceptFile(name) && fileSelector.acceptOrder(order)) {
                Unrar5.linux(file.getPath(), this.unzipDir);
                if (delete) {
                    file.delete();
                }
            }
        }
    }

    public <T> void parse(IParser iParser, boolean delete) {
        File[] files = new File(unzipDir).listFiles();
        assert files != null;
        for (File file : files) {
            String name = file.getName();
            String order = fileSelector.getOrder(name);
            if (fileSelector.acceptFile(name) && fileSelector.acceptOrder(order)) {
                try {
                    Unrar5.linux(file.getPath(), this.unzipDir);
//                    Junrar.extract(file.getPath(), this.unzipDir);
                    IParser pro = new ParserLoggerProxy(logMapper, fileType, name, iParser).getTarget();
                    pro.parse(file);
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

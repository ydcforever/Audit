package com.btw.parser.utils;

import com.fate.file.transfer.FTPAccessor;
import com.fate.file.transfer.FileSelector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by ydc on 2020/7/16.
 */
@Component
public class FTPFactory {
    @Value("${ftp.default.username}")
    private String username;

    @Value("${ftp.default.password}")
    private String password;

    @Value("${ftp.default.host}")
    private String host;

    @Value("${ftp.default.port}")
    private int port;

    @Value("${ftp.acca.root}")
    private String serverRoot;

    @Value("${download.path}")
    private String downloadPath;

    public FTPFactory() {
    }

    public void download(FileSelector fileSelector) throws Exception {
        checkSaveDir(downloadPath);
        FTPAccessor.accessWithFtpFileProcessor(host, port, username, password, fileSelector, downloadPath, true, serverRoot);
    }

    public String getDownloadPath() {
        return downloadPath;
    }


    private void checkSaveDir(String path){
        File file = new File(path);
        if(!file.exists() || !file.isDirectory()){
            file.mkdir();
        }
    }
}

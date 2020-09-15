package com.btw.parser.model;

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
    private String saveRoot;

    public FTPFactory() {
    }

    public void download(String module, FileSelector fileSelector) throws Exception {
        String saveDir = this.saveRoot + module;
        String serverDir = this.serverRoot + module;
        checkSaveDir(saveDir);
        FTPAccessor.accessWithFtpFileProcessor(host, port, username, password, fileSelector, saveDir, true, serverDir);
    }

    public String getSaveRoot() {
        return saveRoot;
    }


    private void checkSaveDir(String path){
        File file = new File(path);
        if(!file.exists() || !file.isDirectory()){
            file.mkdir();
        }
    }
}

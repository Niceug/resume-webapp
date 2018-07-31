package com.ltp.resume.service.impl;

import com.ltp.resume.service.FileAO;

import java.io.*;
import java.util.Scanner;


/**
 * 读文件
 */
public class FileAOImpl implements FileAO {
    private final File file = new File("/home/apache-tomcat-8.5.30/webapps/ROOT/log/accessLog.txt");
    @Override
    public String getAccessInfo() {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner s = new Scanner(new FileInputStream(file));
            while(s.hasNextLine()) {
                String str = s.nextLine();
                if(str != null) {
                    if(str.contains("index") || str.contains("projects") || str.contains("knowledge") || str.contains("/ access times")) {
                        sb.append("<font style = 'color : green'>" + str + "</font><br />\n");
                    } else if (str.contains("Manager")){
                        sb.append("<font style = 'color : red'>" + str + "</font><br />\n");
                    } else {
                        sb.append(str + "<br />\n");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

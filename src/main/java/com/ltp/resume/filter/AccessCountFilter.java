package com.ltp.resume.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AccessCountFilter implements Filter {
    private File accessFile;
    private PrintWriter out;
    private int count;
    private final Executor executor = Executors.newSingleThreadExecutor();

    @Override
    public void destroy() {
        if (out != null) {
            out.close();
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        final String url = httpServletRequest.getRequestURI();

        executor.execute(() -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String time = dateFormat.format(date);
            System.out.println(time + ": " + url + " access times : " +  (count + 1));

            if("/manage".equals(url)) {
                out.println(time + "-------------------------------Manager Access");
                System.out.println(time + "-------------------------------Manager Access");
                out.flush();
            } else {
                out.println(time + ": " + url + " access times : " +  (++count));
                out.flush();
            }

        });

        chain.doFilter(req, resp);
    }


    @Override
    public void init(FilterConfig config) throws ServletException {
        String logpath = config.getServletContext().getRealPath("/log");
        String logFileName = config.getInitParameter("logFileName");

        accessFile = new File(logpath, logFileName);
        if(!accessFile.getParentFile().exists()) {
            accessFile.getParentFile().mkdirs();
        }

        try {

            if(!accessFile.exists())
                accessFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out = new PrintWriter(accessFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        count = 0;
    }

}

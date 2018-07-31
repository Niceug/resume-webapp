package com.ltp.resume.servlet;

import com.ltp.resume.service.FileAO;
import com.ltp.resume.service.impl.FileAOImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ManageServlet extends HttpServlet {
    private FileAO fileAO;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        fileAO = new FileAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(fileAO.getAccessInfo());
    }
}

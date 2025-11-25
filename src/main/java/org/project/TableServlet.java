package org.project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/table")
public class TableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/table.html").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = safe(request.getParameter("title"), "Таблица");
        int rows = parseInt(request.getParameter("rows"), 3);
        int cols = parseInt(request.getParameter("cols"), 3);
        String bgcolor = safe(request.getParameter("bgcolor"), "#ffffff");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head><meta charset='utf-8'><title>"+escape(title)+"</title></head><body>");
            out.println("<h2>" + escape(title) + "</h2>");
            out.println("<table border='1' cellpadding='8' style='border-collapse:collapse;'>");
            for (int r = 0; r < rows; r++) {
                out.println("<tr>");
                for (int c = 0; c < cols; c++) {
                    out.printf("<td style='background:%s;'>%s</td>%n", escape(bgcolor), "R" + (r+1) + "C" + (c+1));
                }
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body></html>");
        }
    }

    private int parseInt(String s, int def) {
        try { return Integer.parseInt(s); }
        catch (Exception e) {
            return def;
        }
    }
    private String safe(String s, String def) {
        return (s==null||s.isBlank())?def:s;
    }


    private String escape(String s) {
        return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;");
    }
}

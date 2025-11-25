package org.project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/theme")
public class ThemeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String theme = req.getParameter("theme"); // "dark" або "light"
        if (theme != null && (theme.equals("dark") || theme.equals("light"))) {
            Cookie cookie = new Cookie("theme", theme);
            cookie.setMaxAge(7 * 24 * 60 * 60); // 7 днів
            cookie.setPath("/");
            resp.addCookie(cookie);
        }
        resp.sendRedirect("/theme.jsp");
    }
}

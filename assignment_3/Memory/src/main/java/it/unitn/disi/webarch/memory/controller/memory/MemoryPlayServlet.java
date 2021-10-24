package it.unitn.disi.webarch.memory.controller.memory;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class MemoryPlayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext()
                .getRequestDispatcher("/views/memory/play/index.html")
                .forward(request, response);
    }

}

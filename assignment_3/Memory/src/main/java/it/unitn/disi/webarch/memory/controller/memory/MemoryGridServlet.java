package it.unitn.disi.webarch.memory.controller.memory;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MemoryGridServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private final Gson gson = new Gson();
    private final String REQUEST_KEY_INDEX = "index";

    private List<int[]> grid = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (this.grid == null) {
            String mode = this.getInitParameter("mode");
            this.logger.info("Grid has not been generated");
            this.grid = this.generateGrid(mode);
        }

        String indexString = request.getParameter(REQUEST_KEY_INDEX);
        if (indexString != null) {
            Integer index = Integer.parseInt(indexString);
            int value = this.translateIndexToValue(index);
            this.logger.info("Requested value for index(" + index + "): " + value);

            String valueForIndex = String.valueOf(value);
            PrintWriter out = response.getWriter();
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            out.print(valueForIndex);
            out.flush();
        }
    }

    private Integer translateIndexToValue(int index) {
        int differentCards = 8;
        int colIndex = (int) Math.floor(index / (differentCards / 2));
        int rowIndex = index % (differentCards / 2);
        int selectedValue = this.grid.get(colIndex)[rowIndex];
        return selectedValue;
    }

    private List<int[]> generateGrid(String mode) {
        List<int[]> grid;
        if (mode.equals("development")) {
            grid = new ArrayList<>();
            grid.add(new int[]{1, 1, 2, 2});
            grid.add(new int[]{3, 3, 4, 4});
            grid.add(new int[]{5, 5, 6, 6});
            grid.add(new int[]{7, 7, 8, 8});
        } else {
            grid = new ArrayList<>();
            grid.add(new int[]{7, 1, 8, 4});
            grid.add(new int[]{3, 2, 1, 4});
            grid.add(new int[]{6, 5, 6, 8});
            grid.add(new int[]{2, 5, 3, 7});
        }
        return grid;
    }

}



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/myservlet")
@MultipartConfig
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("csvFile");
        BufferedReader reader = new BufferedReader(new InputStreamReader(filePart.getInputStream()));

        List<String[]> dataArray = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] columns = line.split(",");
            dataArray.add(columns);
        }

        // Save the ArrayList of String arrays in the session
        request.getSession().setAttribute("dataArray", dataArray);

        // Redirect to the print.html page
        response.sendRedirect("print.jsp");
    }
}

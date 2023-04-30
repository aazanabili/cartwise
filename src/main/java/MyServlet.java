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
        List<String[]> errorArray = new ArrayList<>();
        String line;
        int row_nummer=0;
        while ((line = reader.readLine()) != null) {
            String[] Row = line.split(",");
            dataArray.add(Row);
            if(row_nummer!=0) {
            	errorArray.add(testRowValues(Row,row_nummer));
            }
            row_nummer++;
        }

        // Save the ArrayList of String arrays in the session
        request.getSession().setAttribute("dataArray", dataArray);
        request.getSession().setAttribute("errorArray", errorArray);
        
        // Redirect to the print.html page
        response.sendRedirect("print.jsp");
    }
    
    private String[] testRowValues(String[] row,int row_num) {
        String[] rowValue = new String[row.length+1];
        if (!"w".equals(row[0]) && !"m".equals(row[0]) || row[0] == null) {
            rowValue[0] = "Invalid value in the Geschlecht Row "+row_num +":"+ row[0];
        } else {
            rowValue[0] = null;
        }
        if (row[1] == null) {
            rowValue[1] = "Invalid value in the Alter Row: " + row[1];
        } else {
            rowValue[1] = null;
        }
        if (!"ja".equals(row[2]) && !"nein".equals(row[2]) || row[2] == null) {
            rowValue[2] = "Invalid value in the Kinder Row: " + row[2];
        } else {
            rowValue[2] = null;
        }
        if (!"ledig".equals(row[3]) && !"Partnerschaft".equals(row[3]) || row[3] == null) {
            rowValue[3] = "Invalid value in the Familienstand Row: " + row[3];
        } else {
            rowValue[3] = null;
        }

        return rowValue;
    }
}

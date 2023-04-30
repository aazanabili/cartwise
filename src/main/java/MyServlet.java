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
        
        
        
        if (!"ja".equals(row[4]) && !"nein".equals(row[4]) || row[4] == null) {
            rowValue[4] = "Invalid value in the Berufstaetig Row: " + row[4];
        } else {
            rowValue[4] = null;
        }

        
        
	    if (!"Freitag".equals(row[5]) && !"Samstag".equals(row[5])&& !"Dienstag".equals(row[5])&& !"Montag".equals(row[5])&& !"Mittwoch".equals(row[5])&& !"Sonntag".equals(row[5])&& !"Donnerstag".equals(row[5]) || row[5] == null) {
	          rowValue[5] = "Invalid value in the Einkaufstag Row: " + row[5];
	    } else {
	          rowValue[5] = null;
	    }
  	  
	    
	    
	    
        if (row[6] == null) {
            rowValue[6] = "Invalid value in the Einkaufsuhrzeit Row: " + row[6];
        } else {
            rowValue[6] = null;
        }
        
        
        
        
        if (row[7] == null) {
            rowValue[7] = "Invalid value in the Wohnort Row: " + row[7];
        } else {
            rowValue[7] = null;
        }
        
        
        
        if (row[8] == null) {
            rowValue[8] = "Invalid value in the Haushaltsnettoeinkommen Row: " + row[8];
        } else {
            rowValue[8] = null;
        }
        
        
        
        
	   
	   try {
		    int rowValue9 = Integer.parseInt(row[9]);
		    if (rowValue9 < 0) {
		        rowValue[9] = "Invalid value in the Einkaufssumme Row: " + row[9];
		    } else {
		        rowValue[9] = null;
		    }
		} catch (NumberFormatException e) {
		    rowValue[9] = "Invalid value in the Einkaufssumme Row: " + row[9];
		}

	   
	   
	   
	   try {
		    int rowValue10 = Integer.parseInt(row[10]);
		    if (rowValue10 < 0) {
		        rowValue[10] = "Invalid value in the Fertiggerichte Row: " + row[10];
		    } else {
		        rowValue[10] = null;
		    }
		} catch (NumberFormatException e) {
		    rowValue[10] = "Invalid value in the Fertiggerichte Row: " + row[10];
		}
	   
	   
	   
	   
	   try {
		    int rowValue11 = Integer.parseInt(row[11]);
		    if (rowValue11 < 0) {
		        rowValue[11] = "Invalid value in the Tiefkuehlware Row: " + row[11];
		    } else {
		        rowValue[11] = null;
		    }
		} catch (NumberFormatException e) {
		    rowValue[11] = "Invalid value in the Tiefkuehlware Row: " + row[11];
		}
	   
	   
	   
	   
	   
	   try {
		    int rowValue12 = Integer.parseInt(row[12]);
		    if (rowValue12 < 0) {
		        rowValue[12] = "Invalid value in the Milchprodukte Row: " + row[12];
		    } else {
		        rowValue[12] = null;
		    }
		} catch (NumberFormatException e) {
		    rowValue[12] = "Invalid value in the Milchprodukte Row: " + row[12];
		}
	   
	   
	   
	   
	   
	   try {
		    int rowValue13 = Integer.parseInt(row[13]);
		    if (rowValue13 < 0) {
		        rowValue[13] = "Invalid value in the Backwaren Row: " + row[13];
		    } else {
		        rowValue[13] = null;
		    }
		} catch (NumberFormatException e) {
		    rowValue[13] = "Invalid value in the Backwaren Row: " + row[13];
		}
	   
	   
	   
	   
	   
	   try {
		    int rowValue14 = Integer.parseInt(row[14]);
		    if (rowValue14 < 0) {
		        rowValue[14] = "Invalid value in the Obst/Gemuese Row: " + row[14];
		    } else {
		        rowValue[14] = null;
		    }
		} catch (NumberFormatException e) {
		    rowValue[14] = "Invalid value in the Obst/Gemuese Row: " + row[14];
		}
	   
	   
	   
	   
	   
	   try {
		    int rowValue15 = Integer.parseInt(row[15]);
		    if (rowValue15 < 0) {
		        rowValue[15] = "Invalid value in the Spirituosen Row: " + row[15];
		    } else {
		        rowValue[15] = null;
		    }
		} catch (NumberFormatException e) {
		    rowValue[15] = "Invalid value in the Spirituosen Row: " + row[15];
		}
	   
	   
	   
	   
	   
	   try {
		    int rowValue16 = Integer.parseInt(row[16]);
		    if (rowValue16 < 0) {
		        rowValue[16] = "Invalid value in the Tiernahrung Row: " + row[16];
		    } else {
		        rowValue[16] = null;
		    }
		} catch (NumberFormatException e) {
		    rowValue[16] = "Invalid value in the Tiernahrung Row: " + row[16];
		}
	   
	   
	   
	   
	   
	   try {
		    int rowValue17 = Integer.parseInt(row[17]);
		    if (rowValue17 < 0) {
		        rowValue[17] = "Invalid value in the Bier Row: " + row[17];
		    } else {
		        rowValue[17] = null;
		    }
		} catch (NumberFormatException e) {
		    rowValue[17] = "Invalid value in the Bier Row: " + row[17];
		}
	   
	   
	   

	   
	   
	   
	   
	   try {
		    int rowValue18 = Integer.parseInt(row[18]);
		    if (rowValue18 < 0) {
		        rowValue[18] = "Invalid value in the Frischfleisch Row: " + row[18];
		    } else {
		        rowValue[18] = null;
		    }
		} catch (NumberFormatException e) {
		    rowValue[18] = "Invalid value in the Frischfleisch Row: " + row[18];
		}
	   
	   
	   

	   
	   
	   
	   try {
		    int rowValue19 = Integer.parseInt(row[19]);
		    if (rowValue19 < 0) {
		        rowValue[19] = "Invalid value in the Drogerieartikel Row: " + row[19];
		    } else {
		        rowValue[19] = null;
		    }
		} catch (NumberFormatException e) {
		    rowValue[19] = "Invalid value in the Drogerieartikel Row: " + row[19];
		}
	   
	   
	   

	   
	   
	   
	   
	   try {
		    int rowValue20 = Integer.parseInt(row[20]);
		    if (rowValue20 < 0) {
		        rowValue[20] = "Invalid value in the Konserven Row: " + row[20];
		    } else {
		        rowValue[20] = null;
		    }
		} catch (NumberFormatException e) {
		    rowValue[20] = "Invalid value in the Konserven Row: " + row[20];
		}
	   
	   
	   

	   
	   
	   
	   
	   try {
		    int rowValue21 = Integer.parseInt(row[21]);
		    if (rowValue21 < 0) {
		        rowValue[21] = "Invalid value in the Kaffee/Tee Row: " + row[21];
		    } else {
		        rowValue[21] = null;
		    }
		} catch (NumberFormatException e) {
		    rowValue[21] = "Invalid value in the Kaffee/Tee Row: " + row[21];
		}
	   
	   
	   

	   
	   
	   
	   
	   try {
		    int rowValue22 = Integer.parseInt(row[22]);
		    if (rowValue22 < 0) {
		        rowValue[22] = "Invalid value in the Suessigkeiten Row: " + row[22];
		    } else {
		        rowValue[22] = null;
		    }
		} catch (NumberFormatException e) {
		    rowValue[22] = "Invalid value in the Suessigkeiten Row: " + row[22];
		}
	   
	   
	   

	   
	   
	   
	   
	   try {
		    int rowValue23 = Integer.parseInt(row[23]);
		    if (rowValue23 < 0) {
		        rowValue[23] = "Invalid value in the Wurstwaren Row: " + row[23];
		    } else {
		        rowValue[23] = null;
		    }
		} catch (NumberFormatException e) {
		    rowValue[23] = "Invalid value in the Wurstwaren Row: " + row[23];
		}
	   
	   
	   

	   
	   
	   
	   
	   try {
		    int rowValue24 = Integer.parseInt(row[24]);
		    if (rowValue24 < 0) {
		        rowValue[24] = "Invalid value in the Schreibwaren Row: " + row[24];
		    } else {
		        rowValue[24] = null;
		    }
		} catch (NumberFormatException e) {
		    rowValue[24] = "Invalid value in the Schreibwaren Row: " + row[24];
		}
	   
	   
  

        return rowValue;
    }
}

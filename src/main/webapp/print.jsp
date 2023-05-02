<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Print CSV</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
    </style>
</head>
<body>

    <header>
        <h1>CSV Data</h1>
        <% 
        List<String[]> errorArray = (List<String[]>) request.getSession().getAttribute("errorArray");
        for (String[] errorRow : errorArray) {
            for (String error : errorRow) {
                if (error != null) {
        %>
                    <p style="color: red;"><%= error %></p>
        <% 
                }
                
            }
        }
        %>
        <p style="color: blue;"><%= "kein fehler mher" %></p>
 <% 

        %>
    </header>

    <main>
        <section>
        <!--


            <table id="csvTable">

                <thead>
                    <tr>
                        <% 
                        List<String[]> dataArray = (List<String[]>) request.getSession().getAttribute("dataArray");
                        for (int i = 0; i < dataArray.get(0).length; i++) { 
                        %>
                            <th>Column <%= i + 1 %></th>
                        <% } %>
                    </tr>
                </thead>
                <tbody>
                    <% for (String[] row : dataArray) { %>
                        <tr>
                            <% for (String cell : row) { %>
                                <td><%= cell %></td>
                            <% } %>
                        </tr>
                    <% } %>
                </tbody>
            </table>
            //-->
            <button onclick="printCSV()">Print CSV</button>
        </section>
    </main>

    <script>
        function printCSV() {
            const originalContent = document.body.innerHTML;
            const printContent = document.getElementById('csvTable').outerHTML;

            document.body.innerHTML = printContent;
            window.print();
            document.body.innerHTML = originalContent;
        }
    </script>
</body>
</html>

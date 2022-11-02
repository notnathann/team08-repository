package group08.boeing.index;

import io.javalin.http.Handler;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.fasterxml.jackson.databind.ObjectMapper;

public class IndexController {
    public static final String HTML_REQUEST = "/";
    
    public static final String JSON_POST_REQUEST = "/upload";
    
    public static final String TARGET_HTML_FILE_PATH = "/html/index.html";

    private static final String DATABASE = "jdbc:sqlite:database/autoclave.db";

    // Implement Handler functional interface with lambda expression (acts as override for `handle` abstract method)
    public static Handler servePage = ctx -> {
        // String headerHtml = FileUtil.readFile(RESOURCE_HEADER_HTML_FILE_PATH);
        Map<String, Object> model = new HashMap<>();
        // model.put("header", headerHtml);
        ctx.render(TARGET_HTML_FILE_PATH, model);
    };

    public static Handler parseJSON = ctx -> {
        // Retrieve Contents of JSON file
        String jsonStr = ctx.body();

        int ctr = 0;
        int index = jsonStr.indexOf("IP");
        while (index >= 0) {
            jsonStr = jsonStr.substring(0, index) + "IP" + ctr + jsonStr.substring(index + 2, jsonStr.length());
            index = jsonStr.indexOf("IP", index + 1);
            ctr = ctr + 1;
        }
        System.out.println(jsonStr);

        // Create HashMap from String
        Map<String, Object> mapping = new ObjectMapper().readValue(jsonStr, HashMap.class);

        // Split HashMap into runDetails and partInformation
        Map<String, Object> runDetails = (Map<String, Object>)mapping.get("RunDetails");
        ArrayList<Map<String, Object>> partInformationList = (ArrayList<Map<String, Object>>)mapping.get("PartInformation");

        String fileName = (String)runDetails.get("FileName");
        String filePath = (String)runDetails.get("FilePath");
        Integer loadNumber = Integer.parseInt((String)runDetails.get("LoadNumber"));
        String equipment = (String)runDetails.get("Equipment");
        String runRecipe = (String)runDetails.get("RunRecipe");
        String runStart = (String)runDetails.get("RunStart");
        String runEnd = (String)runDetails.get("RunEnd");
        Float runDuration = Float.parseFloat((String)runDetails.get("RunDuration"));
        Integer fileLength = Integer.parseInt((String)runDetails.get("FileLength"));
        String operatorName = (String)runDetails.get("OperatorName");
        String exportControl = (String)runDetails.get("ExportControl");
        String ip0 = (String)runDetails.get("IP0");
        String ip1 = (String)runDetails.get("IP1");
        String ip2 = (String)runDetails.get("IP2");
        String ip3 = (String)runDetails.get("IP3");
        String ip = ip0 + " " + ip1 + " " + ip2 + " " + ip3;

        // Connect to JDBC data base
        Connection connection = DriverManager.getConnection(DATABASE);

        // Prepare INSERT statement
        String sql = String.format("""
            INSERT into RUN_DETAILS VALUES (
                \"%s\", \"%s\", %d, \"%s\", \"%s\", \"%s\", \"%s\", %f, %d, \"%s\", \"%s\", \"%s\"
            );""", 
            fileName, filePath, loadNumber, equipment, runRecipe, runStart, runEnd, runDuration, fileLength, operatorName, exportControl, ip
        );

        // Execute INSERT statement
        Statement statement = connection.createStatement();
        System.out.println("Executing: " + sql);

        try {
            statement.execute(sql);
        }
        catch (SQLException e) {
            // Likely data already exists in database, just ignore
        }

        for (int i = 0; i < partInformationList.size(); i++) {
            Map<String, Object> partInformation = partInformationList.get(i);

            Integer indexNumber = (Integer)partInformation.get("Index");
            Integer workOrder = Integer.parseInt((String)partInformation.get("WorkOrder"));
            String partNumber = (String)partInformation.get("PartNumber");
            String partDescription = (String)partInformation.get("PartDescription");
            String toolLocation = (String)partInformation.get("ToolLocation");
            String comment1 = (String)partInformation.get("Comment1");
            String comment2 = (String)partInformation.get("Comment2");
            String comment3 = (String)partInformation.get("Comment3");
            ArrayList<String> partTCsList = (ArrayList<String>)partInformation.get("PartTCs");
            String partTCs = String.join(",", partTCsList);
            ArrayList<String> partProbesList = (ArrayList<String>)partInformation.get("PartProbes");
            String partProbes = String.join(",", partProbesList);
            ArrayList<String> otherSensorsList = (ArrayList<String>)partInformation.get("OtherSensors");
            String otherSensors = String.join(",", otherSensorsList);

            // Prepare INSERT statement
            sql = String.format("""
                INSERT into PART_INFORMATION VALUES (
                    \"%s\", %d, %d, \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\"
                );""", 
                fileName, indexNumber, workOrder, partNumber, partDescription, toolLocation, comment1, comment2, comment3, partTCs, partProbes, otherSensors
            );

            // Execute INSERT statement
            statement = connection.createStatement();
            System.out.println("Executing: " + sql);

            try {
                statement.execute(sql);
            }
            catch (SQLException e) {
                // Likely data already exists in database, just ignore
            }
        }

        connection.close();
    };
}

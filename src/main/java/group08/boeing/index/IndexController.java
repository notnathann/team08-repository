package group08.boeing.index;

import io.javalin.http.Handler;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
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

        for (int i = 0; i < jsonStr.length(); i++) {
            System.out.println(i);
          }

        // Create HashMap from String
        Map<String, Object> mapping = new ObjectMapper().readValue(jsonStr, HashMap.class);

        // Split HashMap into runDetails and partInformation
        Map<String, Object> runDetails = (Map<String, Object>)mapping.get("RunDetails");
        ArrayList<Map<String, Object>> partInformation = (ArrayList<Map<String, Object>>)mapping.get("PartInformation");

        String fileName = (String)runDetails.get("FileName");
        String filePath = (String)runDetails.get("FilePath");
        Integer loadNumber = (Integer)runDetails.get("LoadNumber");
        String equipment = (String)runDetails.get("LoadNumber");
        String runRecipe = (String)runDetails.get("LoadNumber");
        String runStart = (String)runDetails.get("LoadNumber");
        String runEnd = (String)runDetails.get("LoadNumber");
        Float runDuration = (Float)runDetails.get("LoadNumber");
        Integer fileLength = (Integer)runDetails.get("LoadNumber");
        String operatorName = (String)runDetails.get("LoadNumber");
        String exportControl = (String)runDetails.get("LoadNumber");
        String ip = (String)runDetails.get("LoadNumber");
        

        // Connect to JDBC data base
        Connection connection = DriverManager.getConnection(DATABASE);

        // Prepare INSERT statement
        String sql = String.format("""
            INSERT into RUN_DETAILS VALUES (
                %s, %s, %d, %s, %s, %s, %s, %f, %d, %s, %s, %s
            );""", 
            fileName, filePath, INDIGENOUS_STATUS[indexStatus], 
            SEX[indexSex], AGE_CATEGORY[indexCategory], count
        );

        // Execute INSERT statement
        Statement statement = connection.createStatement();
        System.out.println("Executing: " + sql);
        statement.execute(sql);
    };
}

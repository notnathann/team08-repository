package group08.boeing.index;

import io.javalin.http.Handler;
import java.util.Map;
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
        String jsonStr = ctx.body();
        Map<String, Object> mapping = new ObjectMapper().readValue(jsonStr, HashMap.class);
        Map<String, Object> runDetails = (Map<String, Object>)mapping.get("RunDetails");
        System.out.println(runDetails.get("FileName"));

        // Connect to JDBC data base
        Connection connection = DriverManager.getConnection(DATABASE);

        // Prepare INSERT statement
        String sql = String.format("""
            INSERT into LGA_Age_Base VALUES (
                %d, %d, \"%s\", \"%s\", \"%s\", %d
            );""", 
            lgaCode, CENSUS_YEAR, INDIGENOUS_STATUS[indexStatus], 
            SEX[indexSex], AGE_CATEGORY[indexCategory], count
        );

        // Execute INSERT statement
        Statement statement = connection.createStatement();
        System.out.println("Executing: " + sql);
        statement.execute(sql);
    };
}

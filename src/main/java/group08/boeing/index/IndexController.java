package group08.boeing.index;

import io.javalin.http.Handler;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class IndexController {
    public static final String HTML_REQUEST = "/";
    public static final String JSON_POST_REQUEST = "/upload";
    public static final String TARGET_HTML_FILE_PATH = "/html/index.html";
    // public static final String RESOURCE_HEADER_HTML_FILE_PATH = "./src/main/resources/html/common/header.html";

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
    };
}

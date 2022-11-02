package group08.boeing.index;

import io.javalin.http.Handler;
import java.util.Map;
import java.util.HashMap;

import group08.boeing.util.FileUtil;

public class IndexController {
    public static final String HTML_REQUEST = "/";
    public static final String TARGET_HTML_FILE_PATH = "/html/index.html";
    // public static final String RESOURCE_HEADER_HTML_FILE_PATH = "./src/main/resources/html/common/header.html";

    // Implement Handler functional interface with lambda expression (acts as override for `handle` abstract method)
    public static Handler servePage = ctx -> {
        // String headerHtml = FileUtil.readFile(RESOURCE_HEADER_HTML_FILE_PATH);
        Map<String, Object> model = new HashMap<>();
        // model.put("header", headerHtml);
        ctx.render(TARGET_HTML_FILE_PATH, model);
    };
}
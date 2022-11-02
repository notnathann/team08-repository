package group08.boeing.search;

import io.javalin.http.Handler;
import java.util.Map;
import java.util.HashMap;

public class SearchController {

    public static final String HTML_REQUEST = "/search";

    public static final String TARGET_HTML_FILE_PATH = "/html/search.html";

    // Implement Handler functional interface with lambda expression (acts as override for `handle` abstract method)
    public static Handler servePage = ctx -> {
        // String headerHtml = FileUtil.readFile(RESOURCE_HEADER_HTML_FILE_PATH);
        Map<String, Object> model = new HashMap<>();
        // model.put("header", headerHtml);
        ctx.render(TARGET_HTML_FILE_PATH, model);
    };
}

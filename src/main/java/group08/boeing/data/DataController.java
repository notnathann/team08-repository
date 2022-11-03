package group08.boeing.data;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Handler;

public class DataController {
    public static final String HTML_REQUEST = "/data";

    // Implement Handler functional interface with lambda expression (acts as override for `handle` abstract method)
    public static Handler servePage = ctx -> {
        String data = ctx.pathParam("filename");
        Map<String, Object> model = new HashMap<>();
        
        ctx.render(data, model);
    };
}

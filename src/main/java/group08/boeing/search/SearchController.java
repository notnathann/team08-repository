package group08.boeing.search;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Handler;

public class SearchController {

    public static final String HTML_REQUEST = "/search";

    public static final String TARGET_HTML_FILE_PATH = "/html/search.html";

    public static final String OPERATOR_NAME_SEARCH_REQUEST = "/search/operatorNameSearch/:partial";
    public static final String LOAD_NUMBER_SEARCH_REQUEST = "/search/loadNumberSearch/:partial";
    public static final String EQUIPMENT_SEARCH_REQUEST = "/search/equipmentSearch/:partial";
    public static final String RUN_RECIPE_SEARCH_REQUEST = "/search/runRecipeSearch/:partial";

    public static final String OPERATOR_NAME_SEARCH_REQUEST_URL = "/search/operatorNameSearch/";
    public static final String LOAD_NUMBER_SEARCH_REQUEST_URL = "/search/loadNumberSearch/";
    public static final String EQUIPMENT_SEARCH_REQUEST_URL = "/search/equipmentSearch/";
    public static final String RUN_RECIPE_SEARCH_REQUEST_URL = "/search/runRecipeSearch/";

    public static final String DATA_REQUEST = "/searchData/:operatorName/:loadNumber/:equipment/:runRecipe";

    // Implement Handler functional interface with lambda expression (acts as override for `handle` abstract method)
    public static Handler servePage = ctx -> {
        // String headerHtml = FileUtil.readFile(RESOURCE_HEADER_HTML_FILE_PATH);
        Map<String, Object> model = new HashMap<>();
        
        // Data Request Constants
        model.put("operatorNameRequest", OPERATOR_NAME_SEARCH_REQUEST_URL);
        model.put("loadNumberRequest", LOAD_NUMBER_SEARCH_REQUEST_URL);
        model.put("equipmentRequest", EQUIPMENT_SEARCH_REQUEST_URL);
        model.put("runRecipeRequest", RUN_RECIPE_SEARCH_REQUEST_URL);
        
        ctx.render(TARGET_HTML_FILE_PATH, model);
    };

    public static Handler fetchData = ctx -> {
        String operatorName = ctx.pathParam("operatorName");
        String loadNumber = ctx.pathParam("loadNumber");
        String equipment = ctx.pathParam("equipment");
        String runRecipe = ctx.pathParam("runRecipe");

        JDBC jdbc = new JDBC();
        ArrayList<RunDetails> dataList = jdbc.getData(operatorName, loadNumber, equipment, runRecipe);

        ObjectMapper jMapper = new ObjectMapper();
        ObjectNode data = jMapper.createObjectNode();

        // Matches
        ArrayNode cureArrayNode = jMapper.createArrayNode();
        
        for (RunDetails runDetails : dataList) {
            ObjectNode cureNode = jMapper.createObjectNode();

            cureNode.put("fileName", runDetails.fileName);
            cureNode.put("filePath", runDetails.filePath);
            cureNode.put("loadNumber", runDetails.loadNumber);
            cureNode.put("equipment", runDetails.equipment);
            cureNode.put("runRecipe", runDetails.runRecipe);
            cureNode.put("runStart", runDetails.runStart);
            cureNode.put("runEnd", runDetails.runEnd);
            cureNode.put("runDuration", runDetails.runDuration);
            cureNode.put("fileLength", runDetails.fileLength);
            cureNode.put("operatorName", runDetails.operatorName);
            cureNode.put("exportControl", runDetails.exportControl);
            cureNode.put("runDetails", runDetails.ip);

            cureArrayNode.add(cureNode);;
        }

        data.set("values", cureArrayNode);

        try {
            ctx.json(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public static Handler fetchMatchingOperatorNames = ctx -> {
        String partial = ctx.pathParam("partial");

        System.out.println(partial);

        JDBC jdbc = new JDBC();
        ArrayList<String> namesList = jdbc.getMatchingOperatorNames(partial);

        ObjectMapper jMapper = new ObjectMapper();
        ObjectNode data = jMapper.createObjectNode();

        // Matches
        ArrayNode operatorNamesNode = jMapper.createArrayNode();
        
        for (String name : namesList) {
            operatorNamesNode.add(name);
        }

        data.set("values", operatorNamesNode);

        try {
            ctx.json(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public static Handler fetchMatchingLoadNumbers = ctx -> {
        String partial = ctx.pathParam("partial");

        JDBC jdbc = new JDBC();
        ArrayList<String> loadNumbersList = jdbc.getMatchingLoadNumbers(partial);

        ObjectMapper jMapper = new ObjectMapper();
        ObjectNode data = jMapper.createObjectNode();

        // Matches
        ArrayNode loadNumbersNode = jMapper.createArrayNode();
        
        for (String loadNumber : loadNumbersList) {
            loadNumbersNode.add(loadNumber);
        }

        data.set("values", loadNumbersNode);

        try {
            ctx.json(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public static Handler fetchMatchingEquipment = ctx -> {
        String partial = ctx.pathParam("partial");

        JDBC jdbc = new JDBC();
        ArrayList<String> equipmentList = jdbc.getMatchingEquipment(partial);

        ObjectMapper jMapper = new ObjectMapper();
        ObjectNode data = jMapper.createObjectNode();

        // Matches
        ArrayNode equipmentNode = jMapper.createArrayNode();
        
        for (String equipment : equipmentList) {
            equipmentNode.add(equipment);
        }

        data.set("values", equipmentNode);

        try {
            ctx.json(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public static Handler fetchMatchingRunRecipes = ctx -> {
        String partial = ctx.pathParam("partial");

        JDBC jdbc = new JDBC();
        ArrayList<String> runRecipesList = jdbc.getMatchingRunRecipes(partial);

        ObjectMapper jMapper = new ObjectMapper();
        ObjectNode data = jMapper.createObjectNode();

        // Matches
        ArrayNode runRecipesNode = jMapper.createArrayNode();
        
        for (String runRecipe : runRecipesList) {
            runRecipesNode.add(runRecipe);
        }

        data.set("values", runRecipesNode);

        try {
            ctx.json(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
}

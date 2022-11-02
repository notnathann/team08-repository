package group08.boeing;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

import group08.boeing.index.IndexController;

/**
 * Main Application Class.
 *
 * Running class as java application to start the
 * Javalin HTTP Server and our web application.
 */
public class App {

    public static final int JAVALIN_PORT = 7001;
    public static final String CSS_DIR = "css/";
    public static final String GRAPHICS_DIR = "graphics/";
    public static final String HTML_DIR = "html/";
    // public static final String ICONS_DIR = "icons/";
    // public static final String IMAGES_DIR = "images/";
    public static final String JS_DIR = "js/";
    public static final String VENDOR_DIR = "vendor/";

    public static void main(String[] args) {
        // Create our HTTP server and listen in port 7001
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(new RouteOverviewPlugin("/help/routes"));

            // Make resources available
            config.addStaticFiles(CSS_DIR);
            config.addStaticFiles(GRAPHICS_DIR);
            config.addStaticFiles(HTML_DIR);
            // config.addStaticFiles(ICONS_DIR);
            // config.addStaticFiles(IMAGES_DIR);
            config.addStaticFiles(JS_DIR);
            config.addStaticFiles(VENDOR_DIR);

        }).start(JAVALIN_PORT);

        // Configure Web Routes
        configureRoutes(app);
    }

    public static void configureRoutes(Javalin app) {
        // GET - Task 1.1
        app.get(IndexController.HTML_REQUEST, IndexController.servePage);
        app.post(IndexController.JSON_POST_REQUEST, IndexController.parseJSON);
    }
}
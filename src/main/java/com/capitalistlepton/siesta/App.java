package com.capitalistlepton.siesta;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * Runner class that launches the application
 */
public class App {

    /** Name of the templates folder. */
    private static final String TEMPLATE_DIR = "templates/";
    /** Name of the folder to put finished HTML. */
    private static final String OUTPUT_DIR = "build/";

    /** FreeMarker {@link Configuration} object. */
    private static Configuration cfg;

    /**
     * Main method.
     *
     * @param args String[] Command line arguments.
     */
    public static void main(String[] args) {
        cfg = initFreeMarker();

        Map<String, Object> root = new HashMap<>();
        root.put("message", "Hello there");

        try {
            File test = new File(OUTPUT_DIR + "test.html");
            Writer out = new FileWriter(test);

            Template template = cfg.getTemplate("test.ftlh");
            template.process(root, out);
        } catch (Exception e) {
            System.err.println("Encountered exception " + e.getMessage());
        }
    }

    /**
     * Create a FreeMarker {@link Configuration} object.
     */
    private static Configuration initFreeMarker() {
        Configuration cfg = null;
        try {
            cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_DIR));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
        } catch (IOException e) {
            System.err.println("Problem occured while loading templates directory");
        }
        return cfg;
    }
}

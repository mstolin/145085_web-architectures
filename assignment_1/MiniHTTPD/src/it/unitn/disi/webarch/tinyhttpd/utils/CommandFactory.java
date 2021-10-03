package it.unitn.disi.webarch.tinyhttpd.utils;

import java.util.StringTokenizer;

public class CommandFactory {

    /**
     * Generates the command for the given process name.
     *
     * @param process Name of the requested process
     * @param parameters Probably the query string
     * @return Command as string
     * @throws Exception If the process name is unknown
     */
    public static String generateCommand(String process, String parameters) throws Exception {
        if (process.equals("reverse")) {
            return generateReverseProcessCommand(parameters);
        } else {
            throw new Exception("No process called \"" + process + "\" available");
        }
    }

    private static String generateReverseProcessCommand(String parameters) throws Exception {
        StringTokenizer paramTokenizer = new StringTokenizer(parameters, "&");
        // first check if there are any parameters
        if (!paramTokenizer.hasMoreTokens()) {
            throw new Exception("No parameters given. The reverse process needs at least one parameter");
        }
        String firstParam = paramTokenizer.nextToken();
        String[] paramKeyValue = firstParam.split("=");
        if (paramKeyValue.length >= 2) {
            // We need at least 2 elements
            String textToReverse = paramKeyValue[1];
            String artifactPath = System.getProperty("user.dir") + "/jars/StringReverser.jar";
            String command = "java" + " -jar " + artifactPath + " \"" + textToReverse + "\"";

            return command;
        } else {
            throw new Exception("No valid parameter input given for parameters \"" + parameters + "\"");
        }
    }

}

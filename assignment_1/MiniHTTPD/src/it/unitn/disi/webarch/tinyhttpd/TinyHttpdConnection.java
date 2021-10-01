package it.unitn.disi.webarch.tinyhttpd;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

class TinyHttpdConnection extends Thread {

    private Socket sock;
    private PrintStream ps;
    private OutputStream out;

    TinyHttpdConnection(Socket s) {
        this.sock = s;
        setPriority(NORM_PRIORITY - 1);
        start();
    }

    @Override
    public void run() {
        System.out.println("=========");
        System.out.println("Connected on port "+ this.sock.getPort());
        this.out = null;
        try {
            // OPEN SOCKETS FOR READING AND WRITING
            BufferedReader d =
                    new BufferedReader(new InputStreamReader(
                            this.sock.getInputStream()));
            this.out = this.sock.getOutputStream();
            this.ps = new PrintStream(out);
            // READ REQUEST
            String req = d.readLine();
            if (req==null) return;
            System.out.println("Request: " + req);
            // READ REQUEST HEADERS
            String head=null;
            do {
                head = d.readLine();
                System.out.println("Header: " + head);
            } while (head!=null && head.length()>0);

            // PARSE REQUEST
            StringTokenizer st = new StringTokenizer(req);
            if ((st.countTokens() >= 2) && st.nextToken().equals("GET")) {
                // GANZ WICHTIG: Erstmal URL in seine EinzelTeile PATH, QUERY
                req = st.nextToken();
                String path;
                String query = "";
                String[] splittedURL = req.split("\\?");
                path = splittedURL[0];
                if (path.equals("/")) {
                    path = path + "index.html";
                }
                if (splittedURL.length > 1) {
                    query = splittedURL[1];
                }
                System.out.println("Path: " + path);
                System.out.println("Query: " + query);

                StringTokenizer pathTokenizer = new StringTokenizer(path, "/");
                path = pathTokenizer.nextToken();

                if (path.equals("process") && pathTokenizer.hasMoreTokens()) {
                    // user wants to start a java process
                    String processName = pathTokenizer.nextToken();

                    if (!pathTokenizer.hasMoreTokens()) {
                        // Path is a valid process name
                        System.out.println("Java process name: " + processName);

                        try {
                            String processOutput = this.launchJavaProcess(processName, query);
                            System.out.println("OUTPUT IS: " + processOutput);
                            this.sendResponseHeader(processOutput.length());
                            ps.print(processOutput);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                            ps.print("HTTP/1.1 400 Bad Request\r\n\r\n");
                            System.out.println("400 Bad Request: " + req);
                        }
                    } else {
                        System.out.println("Error: The path " + req + " has too many tokens.");
                        // SEND ERROR RESPONSE
                        ps.print("HTTP/1.1 400 Bad Request\r\n\r\n");
                        System.out.println("400 Bad Request: " + req);
                    }
                } else {
                    // OPEN REQUESTED FILE AND COPY IT TO CLIENT
                    try {
                        this.sendResponse(req);
                    } catch (FileNotFoundException e) {
                        ps.print("HTTP/1.1 404 Not Found \r\n\r\n");
                        System.out.println("404 Not Found: " + req);
                    }
                }
            } else {
                ps.print("HTTP/1.1 400 Bad Request\r\n\r\n");
                System.out.println("400 Bad Request: " + req);
            }
        } catch (IOException e) { // Let's catch all generic I/O troubles
            System.out.println("Generic I/O error " + e);
        } finally { // the following code is ALWAYS executed
            try {
                // let's close all channels
                this.out.close();
                this.sock.close();
            } catch (IOException ex) {
                System.out.println("I/O error on close" + ex);
            }
        }
    }

    private String launchJavaProcess(String processName, String query) throws Exception {
        String command;

        if (processName.equals("reverse")) {
            command = this.generateReverseProcessCommand(query);
        } else {
            // This process is not known
            throw new Exception("No process with the name \"" + processName + "\" known");
        }

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", command);
        Process process = processBuilder.start();

        StringBuilder output = new StringBuilder();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line + "\n");
        }

        return output.toString();
    }

    private String generateReverseProcessCommand(String query) throws Exception {
        StringTokenizer paramTokenizer = new StringTokenizer(query, "&");
        // first check if there are any parameters
        if (!paramTokenizer.hasMoreTokens()) {
            throw new Exception("No parameters given. The reverse process needs at least one parameter");
        }
        String firstParam = paramTokenizer.nextToken();
        String[] paramKeyValue = firstParam.split("=");
        if (paramKeyValue.length >= 2) {
            // We need at least 2 elements
            String textToReverse = paramKeyValue[1];
            String classPath = "/Users/marcel/workspace/web-architectures/assignment_1/StringReverser/out/production/StringReverser";
            String className = "it.unitn.disi.webarch.assignment1.StringReverser";
            String command = "java" + " -cp " + classPath + " " + className + " " + textToReverse;

            return command;
        } else {
            System.out.println();
            throw new Exception("No valid parameter input given for query \"" + query + "\"");
        }
    }

    private void sendResponse(String request) throws java.io.IOException {
        //All our requested files must be in the "Documents" directory
        FileInputStream fis = new FileInputStream("Documents/" + request);
        int responseLength = fis.available();
        // LET'S SEND THE RESPONSE HEADERS
        this.sendResponseHeader(responseLength);
        // LET'S SEND THE CONTENT
        byte[] data = new byte[responseLength];
        fis.read(data);
        out.write(data);
        fis.close();
    }

    private void sendResponseHeader(int responseLength) {
        ps.print("HTTP/1.1 200 OK\r\n");
        ps.print("Content-Length: " + responseLength + "\r\n");
        ps.print("Content-Type: text/html\r\n");
        ps.print("\r\n");
    }

}

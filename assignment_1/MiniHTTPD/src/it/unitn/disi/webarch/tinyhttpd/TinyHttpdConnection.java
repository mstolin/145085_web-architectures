package it.unitn.disi.webarch.tinyhttpd;

import it.unitn.disi.webarch.tinyhttpd.utils.CommandFactory;
import it.unitn.disi.webarch.tinyhttpd.utils.RequestParser;

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

            // Parse the request
            RequestParser requestParser = new RequestParser(req);
            String method = requestParser.getMethod();
            System.out.println("Method: " + method);
            System.out.println("Protocol: " + requestParser.getProtocol());
            String path = requestParser.getPath();
            System.out.println("Path: " + path);
            String query = requestParser.getQueryString();
            System.out.println("Query: " + query);

            if (method.equals("GET")) {
                StringTokenizer pathTokenizer = new StringTokenizer(path, "/");
                if (pathTokenizer.hasMoreTokens() && pathTokenizer.nextToken().equals("process")) {
                    // user wants to start a java process
                    String processName = pathTokenizer.nextToken();

                    if (!pathTokenizer.hasMoreTokens()) {
                        // Path is a valid process name
                        System.out.println("Java process name: " + processName);

                        try {
                            String processResponse = this.launchJavaProcess(processName, query);
                            System.out.println("OUTPUT IS: " + processResponse);
                            this.sendSuccessResponseHeader(processResponse.length());
                            this.ps.print(processResponse);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                            this.sendErrorResponseHeader("400 Bad Request");
                            System.out.println("400 Bad Request: " + path);
                        }
                    } else {
                        System.out.println("Error: The path " + path + " has too many tokens.");
                        // SEND ERROR RESPONSE
                        this.sendErrorResponseHeader("400 Bad Request");
                        System.out.println("400 Bad Request: " + path);
                    }
                } else {
                    // OPEN REQUESTED FILE AND COPY IT TO CLIENT
                    if (path.endsWith("/")) {
                        path = path + "index.html";
                    }
                    try {
                        this.sendFileResponse(path);
                    } catch (FileNotFoundException e) {
                        this.sendErrorResponseHeader("404 Not Found");
                        System.out.println("404 Not Found: " + path);
                    }
                }
            } else {
                this.sendErrorResponseHeader("400 Bad Request");
                System.out.println("400 Bad Request: " + path);
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
        String command = CommandFactory.generateCommand(processName, query);

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

    private void sendFileResponse(String path) throws IOException {
        //All our requested files must be in the "Documents" directory
        FileInputStream fis = new FileInputStream("Documents/" + path);
        int responseLength = fis.available();
        // LET'S SEND THE RESPONSE HEADERS
        this.sendSuccessResponseHeader(responseLength);
        // LET'S SEND THE CONTENT
        byte[] data = new byte[responseLength];
        fis.read(data);
        out.write(data);
        fis.close();
    }

    private void sendSuccessResponseHeader(int responseLength) {
        ps.print("HTTP/1.1 200 OK\r\n");
        ps.print("Content-Length: " + responseLength + "\r\n");
        ps.print("Content-Type: text/html\r\n");
        ps.print("\r\n");
    }

    private void sendErrorResponseHeader(String error) {
        this.ps.print("HTTP/1.1 " + error + "\r\n\r\n");
    }

}

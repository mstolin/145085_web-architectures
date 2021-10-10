package it.unitn.disi.webarch.chat.controller.auth;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AuthLoginServlet extends HttpServlet {

    private final String SESSION_KEY_USERNAME = "username";
    private final String SESSION_KEY_IS_AUTHENTICATED = "is_authenticated";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("Login attempt for user " + username);

        String userFilePath = this.getClass().getClassLoader().getResource("users.txt").getPath();
        System.out.println("Read users from " + userFilePath);
        File userFile = new File(userFilePath);
        Map<String, String> authorizedUsers = this.readUsersFromFile(userFile);

        if (username != null && password != null) {
            if (authorizedUsers.containsKey(username) || username.equals("admin")) {
                String userPassword = authorizedUsers.get(username);

                if (userPassword.equals(password)) {
                    // user is authenticated
                    HttpSession session = request.getSession();
                    this.updateUserAuthStatus(session, username, true);
                    // redirect to user page
                    response.sendRedirect(request.getContextPath() + "/user");
                } else {
                    // Wrong password -> Back to login
                    System.out.println("ERROR (AuthLogin) - Wrong password for user " + username);
                    response.sendRedirect(request.getContextPath() + "/login");
                }
            } else {
                // The user does not exist -> Back to login
                System.out.println("ERROR (AuthLogin) - User '" + username + "' does not exist");
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            // No username or password given -> Send error
            System.out.println("ERROR (AuthLogin) - Bad Request, no username or password provided");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void updateUserAuthStatus(HttpSession session, String username, boolean isAuthenticated) {
        session.setAttribute(SESSION_KEY_IS_AUTHENTICATED, isAuthenticated);
        session.setAttribute(SESSION_KEY_USERNAME, username);
    }

    private Map<String, String> readUsersFromFile(File userFile) {
        Map<String, String> userMap = new HashMap();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(userFile);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userAndPw = line.split(":");
                if (userAndPw.length >= 2) {
                    String user = userAndPw[0].trim();
                    String password = userAndPw[1].trim();

                    userMap.put(user, password);
                }
            }
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (fileReader != null) fileReader.close();
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }

        return userMap;
    }

}

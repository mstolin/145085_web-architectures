package it.unitn.disi.webarch.chat.helper;

import it.unitn.disi.webarch.chat.models.user.User;

import java.io.*;
import java.util.*;

public class UserStore {

    private final String USERS_FILE_PATH = "users.txt";
    private Set<User> users;

    public UserStore() {
        File usersFile = this.getUsersFile();
        this.users = this.readUsersFromFile(usersFile);
    }

    private File getUsersFile() {
        String userFilePath = this.getClass().getClassLoader().getResource(this.USERS_FILE_PATH).getPath();
        System.out.println("UserStore - Read users from " + userFilePath);
        File userFile = new File(userFilePath);
        return userFile;
    }

    private Set<User> readUsersFromFile(File userFile) {
        Set<User> userSet = new HashSet<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            // init all the stuff
            fileReader = new FileReader(userFile);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userAndPw = line.split(":");
                if (userAndPw.length >= 2) {
                    String username = userAndPw[0].trim();
                    String password = userAndPw[1].trim();

                    User user = new User(username, password);
                    userSet.add(user);
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

        return userSet;
    }

    public boolean isUserAvailable(String username) {
        List<String> usernames = this.getAllUsers();
        return usernames.contains(username);
    }

    public List<String> getAllUsers() {
        // Map set uf users to an array of usernames
        String[] usernameArray = this.users.stream().map(user -> user.getName()).toArray(String[]::new);
        List<String> usernames = Arrays.asList(usernameArray);
        return usernames;
    }

    public User getUser(String name) {
        if (this.isUserAvailable(name)) {
            User firstUser = this.users
                    .stream()
                    .filter(user -> user.getName().equals(name))
                    .findFirst()
                    .get();
            return firstUser;
        } else {
            return null;
        }
    }

}

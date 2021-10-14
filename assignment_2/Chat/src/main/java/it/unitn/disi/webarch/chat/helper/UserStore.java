package it.unitn.disi.webarch.chat.helper;

import it.unitn.disi.webarch.chat.models.user.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class UserStore extends ObjectStore<User> {

    private static UserStore instance = null;

    private final String USERS_FILE_NAME = "users.txt";
    private final String USERS_FILE_PATH;

    private File usersFile;

    private UserStore() {
        this.USERS_FILE_PATH = this.getClass().getClassLoader().getResource(this.USERS_FILE_NAME).getPath();
        System.out.println("UserStore - Read users from " + this.USERS_FILE_PATH);
        this.usersFile = new File(this.USERS_FILE_PATH);
        this.store = this.readUsersFromFile();
    }

    public static UserStore getInstance() {
        if (instance == null) {
            instance = new UserStore();
        }
        return instance;
    }

    public User getUser(String userName) {
        Optional<User> firstUser = this.get(user -> user.getName().equals(userName));

        if (firstUser.isPresent()) {
            return firstUser.get();
        } else {
            return null;
        }
    }

    public void addUser(User user) throws Exception {
        try {
            this.writeUserToFile(user);
        } catch (IOException exception) {
            System.out.println("ERROR (UserStore) - " + exception.getMessage());
            throw new Exception("Could not write user " + user.getName() + " to the file");
        } finally {
            this.store = this.readUsersFromFile();
        }
    }

    public List<String> getAllUsernames() {
        // Map set uf users to an array of usernames
        String[] usernameArray = this.store.stream().map(user -> user.getName()).toArray(String[]::new);
        List<String> usernames = Arrays.asList(usernameArray);
        return usernames;
    }

    private Set<User> readUsersFromFile() {
        Set<User> userSet = new HashSet<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            // init all the stuff
            fileReader = new FileReader(this.usersFile);
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

    private void writeUserToFile(User user) throws IOException {
        String lineToWrite = "\n" + user.getName() + ": " + user.getPassword();
        Files.write(Paths.get(this.USERS_FILE_PATH), lineToWrite.getBytes(), StandardOpenOption.APPEND);
    }

}

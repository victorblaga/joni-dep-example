package joni.dep.example.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class UserFileRepository implements UserRepository {
    private final String fileName;
    private final Logger log = LoggerFactory.getLogger(UserFileRepository.class);

    public UserFileRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        log.info("Saving user to file {}", fileName);
        try {
            FileOutputStream out = new FileOutputStream(fileName, true);
            PrintWriter print = new PrintWriter(out);
            String line = String.format("%d,%s,%s", user.getUserId(), user.getFirstName(), user.getLastName());
            print.println(line);
            print.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

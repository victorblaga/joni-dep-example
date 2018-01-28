package joni.dep.example.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainService {
    private final NameRepository firstNameRepository;
    private final NameRepository lastNameRepository;
    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(MainService.class);

    public MainService(NameRepository firstNameRepository,
                       NameRepository lastNameRepository,
                       UserRepository userRepository) {
        this.firstNameRepository = firstNameRepository;
        this.lastNameRepository = lastNameRepository;
        this.userRepository = userRepository;
    }

    public void run() {
        log.info("Running main service");
        Map<Integer, Name> firstNames = readFirstNames();
        Map<Integer, Name> lastNames = readLastNames();
        Map<Integer, User> users = combine(firstNames, lastNames);
        for (User user : users.values()) {
            userRepository.save(user);
        }
    }

    private Map<Integer, Name> readFirstNames() {
        Map<Integer, Name> result = new HashMap<>();
        for (Name name : firstNameRepository.findAll()) {
            result.put(name.getUserId(), name);
        }

        return result;
    }

    private Map<Integer, Name> readLastNames() {
        Map<Integer, Name> result = new HashMap<>();
        for (Name name : lastNameRepository.findAll()) {
            result.put(name.getUserId(), name);
        }

        return result;
    }

    private Map<Integer, User> combine(Map<Integer, Name> firstNames, Map<Integer, Name> lastNames) {
        Set<Integer> userIds = new HashSet<>(firstNames.keySet());
        userIds.addAll(lastNames.keySet());
        Map<Integer, User> users = new HashMap<>();

        for (int i : userIds) {
            Name firstName = firstNames.getOrDefault(i, new Name(i, "unknown"));
            Name lastName = lastNames.getOrDefault(i, new Name(i, "unknown"));
            users.put(i, new User(i, firstName.getName(), lastName.getName()));
        }

        return users;
    }

}

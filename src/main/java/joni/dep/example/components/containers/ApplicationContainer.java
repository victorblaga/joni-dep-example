package joni.dep.example.components.containers;

import com.typesafe.config.Config;
import joni.dep.CustomContainer;
import joni.dep.Qualifier;
import joni.dep.example.components.*;

public class ApplicationContainer extends CustomContainer {
    public ApplicationContainer(Config config) {
        super(config);
    }

    @Qualifier("first_name")
    public NameRepository firstNameRepo() {
        return new NameResourceRepository(config.getString("names.first_name"));
    }

    @Qualifier("last_name")
    public NameRepository lastNameRepo() {
        return new NameResourceRepository(config.getString("names.last_name"));
    }

    public UserRepository userRepository() {
        return new UserFileRepository(config.getString("args.user_file"));
    }

    public MainService mainService() {
        return new MainService(
                get(NameRepository.class, "first_name"),
                get(NameRepository.class, "last_name"),
                get(UserRepository.class)
        );
    }
}

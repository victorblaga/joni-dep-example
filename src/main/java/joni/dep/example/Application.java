package joni.dep.example;

import com.typesafe.config.Config;
import joni.dep.ConfigParser;
import joni.dep.Container;
import joni.dep.ContainerBuilder;
import joni.dep.example.components.MainService;

public class Application {
    public static void main(String[] args) {
        Config config = new ConfigParser().parse(args);
        Container container = new ContainerBuilder(config, "joni.dep.example.components.containers").build();
        MainService mainService = container.get(MainService.class);
        mainService.run();
    }
}

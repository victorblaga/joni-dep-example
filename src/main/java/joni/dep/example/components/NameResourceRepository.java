package joni.dep.example.components;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class NameResourceRepository implements NameRepository {
    private final String resourceName;
    private final Logger log = LoggerFactory.getLogger(NameResourceRepository.class);

    public NameResourceRepository(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public Iterable<Name> findAll() {
        log.info("Reading from resource {}", resourceName);
        URL url = Resources.getResource(resourceName);
        try {
            List<String> lines = Resources.readLines(url, Charsets.UTF_8);
            return lines.stream()
                    .map(l -> l.trim().split(",", 2))
                    .map(parts -> new Name(Integer.valueOf(parts[0]), parts[1]))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

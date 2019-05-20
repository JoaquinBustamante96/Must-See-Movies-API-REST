package com.first.demoMongo.dataServices;

import com.first.demoMongo.repositories.MovieRepository;
import com.first.demoMongo.repositories.PasswordResetTokenRepository;
import com.first.demoMongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import sun.security.util.Password;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

@Service
public class DatabaseSeederService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Value("${database.yml}")
    private String ymlFileName;

    @PostConstruct
    public void constructor() {
       /* if (movieRepository.findAll().size() == 0) {
            this.initializeDB();
        }*/
    }

    public void initializeDB() {
        this.seedDatabase(this.ymlFileName);
    }

    public void seedDatabase(String ymlFileName) {
        try {
            assert ymlFileName != null && !ymlFileName.isEmpty();
            Yaml yamlParser = new Yaml(new Constructor(DatabaseGraph.class));
            InputStream input = new ClassPathResource(ymlFileName).getInputStream();
            DatabaseGraph databaseGraph = (DatabaseGraph) yamlParser.load(input);

            databaseGraph.getMovieList().forEach(
                    movie -> {
                        if (Integer.parseInt(movie.getId()) >= 8)
                            movie.setReleaseDate(LocalDate.of(1999, 01, 01));
                    }
            );

            this.movieRepository.saveAll(databaseGraph.getMovieList());
            this.userRepository.saveAll(databaseGraph.getUserList());
            this.passwordResetTokenRepository.saveAll(databaseGraph.getPasswordResetTokenList());


        } catch (IOException e) {
            System.err.println("ERROR: File " + ymlFileName + " doesn't exist or can't be opened");
            e.printStackTrace();
        }
    }

    public void resetDB() {
        this.deleteAll();
        this.initializeDB();
    }

    public void deleteAll() {
        this.movieRepository.deleteAll();
        this.userRepository.deleteAll();
        this.passwordResetTokenRepository.deleteAll();
    }

}

package pl.edu.amu.wmi.students.mario.isi.searchengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.xml.sax.SAXException;
import pl.edu.amu.wmi.students.mario.isi.searchengine.dataloader.PostsHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by Mariusz on 2017-06-02.
 */
@SpringBootApplication
public class DataLoader implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    public static void main(String[] args) {
        new SpringApplicationBuilder(DataLoader.class).web(false).run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        try {
            File inputFile = new File("Posts.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            PostsHandler postsHandler = (PostsHandler) beanFactory.autowire(PostsHandler.class,
                    AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR, true);
            saxParser.parse(inputFile, postsHandler);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOGGER.error("Error occured", e);
        }
    }

}

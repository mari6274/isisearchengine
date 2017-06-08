package pl.edu.amu.wmi.students.mario.isi.searchengine.dataloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import pl.edu.amu.wmi.students.mario.isi.searchengine.entities.Document;
import pl.edu.amu.wmi.students.mario.isi.searchengine.repositories.elasticsearch.DocumentElasticSearchRepository;

/**
 * Created by Mariusz on 2017-06-08.
 */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PostsHandler extends DefaultHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostsHandler.class);

    private DocumentElasticSearchRepository documentElasticSearchRepository;
    private HtmlCleaner htmlCleaner;

    @Autowired
    public PostsHandler(DocumentElasticSearchRepository documentElasticSearchRepository, HtmlCleaner htmlCleaner) {
        this.documentElasticSearchRepository = documentElasticSearchRepository;
        this.htmlCleaner = htmlCleaner;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws
            SAXException {
        if ("row".equals(qName)) {
            String body = attributes.getValue("Body");
            String cleanedBody = htmlCleaner.clean(body);
            uploadPost(cleanedBody);
        }
    }

    private void uploadPost(String body) {
        Document savedDocument = documentElasticSearchRepository.save(new Document(body));
        LOGGER.info("Saved document with id: {}", savedDocument.getId());
    }
}

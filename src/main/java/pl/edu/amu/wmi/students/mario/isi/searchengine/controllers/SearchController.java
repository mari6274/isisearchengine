package pl.edu.amu.wmi.students.mario.isi.searchengine.controllers;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.amu.wmi.students.mario.isi.searchengine.entities.Document;
import pl.edu.amu.wmi.students.mario.isi.searchengine.repositories.elasticsearch.DocumentElasticSearchRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mariusz on 2017-05-30.
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private DocumentElasticSearchRepository documentElasticSearchRepository;

    @GetMapping
    public List<Document> searchDocuments(@RequestParam String query) {
        Iterable<Document> result = documentElasticSearchRepository.search(QueryBuilders.queryStringQuery(query));
        List<Document> documents = new ArrayList<>();
        result.forEach(documents::add);
        return documents;
    }
}

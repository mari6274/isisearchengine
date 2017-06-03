package pl.edu.amu.wmi.students.mario.isi.searchengine.controllers;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.InternalAggregations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.amu.wmi.students.mario.isi.searchengine.entities.Document;
import pl.edu.amu.wmi.students.mario.isi.searchengine.repositories.elasticsearch.DocumentElasticSearchRepository;

import java.lang.reflect.Field;

/**
 * Created by Mariusz on 2017-05-30.
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private DocumentElasticSearchRepository documentElasticSearchRepository;

    @GetMapping
    public Page<Document> searchDocuments(@RequestParam String query, Pageable pageable) {
        return fixEmptyPage(documentElasticSearchRepository.search(QueryBuilders.queryStringQuery(query), pageable));
    }

    /**
     * Spring issue workaround
     * https://jira.spring.io/browse/DATAES-274
     * https://github.com/spring-projects/spring-data-elasticsearch/pull/175
     */
    private <T> Page<T> fixEmptyPage(Page<T> page) {
        AggregatedPageImpl<T> aggregatedPage = (AggregatedPageImpl<T>) page;
        Aggregations aggregations = aggregatedPage.getAggregations();
        if (aggregations == null) {
            Field field = ReflectionUtils.findField(AggregatedPageImpl.class, "aggregations");
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, aggregatedPage, InternalAggregations.EMPTY);
            return aggregatedPage;
        }
        return page;
    }
}

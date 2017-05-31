package pl.edu.amu.wmi.students.mario.isi.searchengine.repositories.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.edu.amu.wmi.students.mario.isi.searchengine.entities.Document;

/**
 * Created by Mariusz on 2017-05-30.
 */
public interface DocumentElasticSearchRepository extends ElasticsearchRepository<Document, String> {
}

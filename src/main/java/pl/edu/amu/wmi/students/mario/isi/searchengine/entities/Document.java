package pl.edu.amu.wmi.students.mario.isi.searchengine.entities;

import org.springframework.data.annotation.Id;

/**
 * Created by Mariusz on 2017-05-30.
 */
@org.springframework.data.elasticsearch.annotations.Document(indexName = "documents", type = "document")
public class Document {

    @Id
    private String id;
    private String content;

    public Document() {
    }

    public Document(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

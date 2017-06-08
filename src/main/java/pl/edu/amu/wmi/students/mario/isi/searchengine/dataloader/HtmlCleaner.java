package pl.edu.amu.wmi.students.mario.isi.searchengine.dataloader;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Service;

/**
 * Created by Mariusz on 2017-06-08.
 */
@Service
class HtmlCleaner {

    private static final Whitelist WHITELIST = Whitelist.none();

    String clean(String body) {
        return Jsoup.clean(body, WHITELIST);
    }
}

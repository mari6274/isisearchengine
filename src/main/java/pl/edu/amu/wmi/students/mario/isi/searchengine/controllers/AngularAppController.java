package pl.edu.amu.wmi.students.mario.isi.searchengine.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mariusz on 2017-05-30.
 */
@Controller
@RequestMapping("/")
public class AngularAppController {

    @GetMapping
    public String index() {
        return "index.html";
    }
}

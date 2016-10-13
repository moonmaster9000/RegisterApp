package webFrontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemsController {
    @GetMapping("/items/new")
    public String newItem(){
        return "items/new";
    }
}

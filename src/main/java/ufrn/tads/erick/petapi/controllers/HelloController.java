package ufrn.tads.erick.petapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HelloController {
    @GetMapping(value="/")
    public String getHello(@RequestParam String mensagem) {
        return new String(mensagem);
    }
}

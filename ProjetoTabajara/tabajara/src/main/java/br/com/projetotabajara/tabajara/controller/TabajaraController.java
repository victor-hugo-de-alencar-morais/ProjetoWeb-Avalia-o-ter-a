package br.com.projetotabajara.tabajara.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tabajara")
public class TabajaraController {

    @GetMapping
    public String index(Model model) {
        // Retorna a página index.html
        return "/home";
    }
    
}

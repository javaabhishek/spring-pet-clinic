package com.asoft.springpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PetController {

    @RequestMapping({"pets","pets/index","pets/index"})
    public String index(){
        return "pets/index";
    }
}

package com.asoft.springpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OwnerController {

    @RequestMapping({"owner","owners","owners/index","owners/index"})
    public String index(){
        return "owners/index";
    }
}

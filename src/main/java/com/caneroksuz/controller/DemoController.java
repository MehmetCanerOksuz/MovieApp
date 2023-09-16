package com.caneroksuz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    /*
    http metotları
    Get
    Post
    Put
    Delete
     */

    @GetMapping()
    public String hello(){
        return "Hello";
    }

    @GetMapping("/getname")
    public String getName(){
        return "Caner";
    }

    @GetMapping("/getname2")
    public String getName2(String name, String surname){
        return "<style> .myname{color:red}</style> <div class='myname'>" +name+"-"+surname+"</div>";
    }

    @GetMapping("/getlist")
    public List<String> getList(){
        String name1 = "Caner";
        String name2 = "Mehmet";
        String name3 = "Aslı";
        return List.of(name1,name2,name3);
    }
}

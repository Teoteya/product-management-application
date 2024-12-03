package com.example.product_management_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/favicon.ico")
public class FaviconController {
    @GetMapping(produces = "image/png")
    public byte[] favicon() throws IOException {
        InputStream in = getClass().getResourceAsStream("/static/favicon.png");
        return in.readAllBytes();
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/");
//    }
//
//    @RequestMapping("favicon.ico")
//    public ModelAndView favicon() {
//        return new ModelAndView("redirect:/favicon.png");
//    }

}

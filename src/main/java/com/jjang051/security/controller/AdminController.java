package com.jjang051.security.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin/admin";
    }
}

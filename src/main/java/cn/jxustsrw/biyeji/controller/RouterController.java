package cn.jxustsrw.biyeji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RouterController {
    @RequestMapping(value = "chat", method = RequestMethod.GET)
    public String login() {
        return "chat";
    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
    @RequestMapping(value = "ueditor", method = RequestMethod.GET)
    public String uEditor() {
        return "ueditor";
    }
}

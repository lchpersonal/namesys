package com.bbw.namesys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jst")
public class SysController {


    @RequestMapping("/{func}/{jsp}")
    public ModelAndView pageDispatcher(@PathVariable("func") String func,
                                       @PathVariable("jsp") String jsp) {
        String uri = String.format("/%s/%s", func, jsp);
        return new ModelAndView(uri);
    }
}

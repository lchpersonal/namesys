package com.bbw.namesys.controller;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.service.interactive.InteractiveResService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/interactive")
public class InteractiveResController {

    @Autowired
    private InteractiveResService interactiveResService;

    @RequestMapping("/getByKeys")
    public Result getRes(@RequestParam(defaultValue = "0") int curId, String keyword) {
        String[] keys = null;
        if (!StringUtils.isBlank(keyword)) {
            if (keyword.startsWith("|") || keyword.endsWith("|")) {
                return Results.error("搜索字符串不能以|开头或者结尾");
            }
            if (keyword.contains("||")) {
                return Results.error("搜索关键词不能含有两个或两个以上的|");
            }
            keys = keyword.split("\\|");
        }
        return interactiveResService.select(curId, keys);
    }

    @RequestMapping("/add")
    public Result add(String info) {
        if (StringUtils.isBlank(info)) {
            return Results.error("内容不能为空~");
        }
        return interactiveResService.add(info);
    }

    @RequestMapping("/edit")
    public Result edit(Integer id, String info) {
        if (id == null) {
            return Results.error("id 非法");
        }
        if (StringUtils.isBlank(info)) {
            return Results.error("内容不能为空~");
        }
        return interactiveResService.edit(id, info);
    }

    @RequestMapping("/editRes")
    public ModelAndView editRes(int id) {
        ModelAndView mv = new ModelAndView("/interactive/editRes");
        Result result = interactiveResService.select(id);
        mv.addObject("result", result);
        return mv;
    }
}

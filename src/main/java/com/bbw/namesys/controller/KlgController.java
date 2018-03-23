package com.bbw.namesys.controller;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.service.klg.KlgService;
import com.bbw.namesys.utils.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/klg")
public class KlgController {

    @Autowired
    private KlgService klgService;

    @RequestMapping("/add")
    public Result addKlg(String title, String content, HttpServletRequest request) {
        if (StringUtils.isBlank(title) || StringUtils.isBlank(content)) {
            return Results.error("标题或内容不能为空");
        }
        try {
            klgService.addKlg(title, content, SessionUtil.getUsername(request));
        } catch (Exception e) {
            e.printStackTrace();
            return Results.exception();
        }
        return Results.success();
    }

    @RequestMapping("/edit")
    public Result editKlg(String title, String content, Integer id, HttpServletRequest request) {
        if (StringUtils.isBlank(title) || StringUtils.isBlank(content)) {
            return Results.error("标题或内容不能为空");
        }
        if (id == null) {
            return Results.error("id非法");
        }
        try {
            klgService.editKlg(title, content, id, SessionUtil.getUsername(request));
        } catch (Exception e) {
            e.printStackTrace();
            return Results.exception();
        }
        return Results.success();
    }

    @RequestMapping("/editPage")
    public ModelAndView editKlgPage(Integer id) {
        ModelAndView mv = new ModelAndView("/klg/editKlg");
        Result result = Results.of(klgService.selectById(id));
        mv.addObject("result", result);
        return mv;
    }

    @RequestMapping("/select")
    public Result selectKlg(@RequestParam(defaultValue = "999999999") int curId, String keyword) {
        try {
            String[] keys = null;
            if (!StringUtils.isBlank(keyword)) {
                if (keyword.startsWith("|") || keyword.endsWith("|")) {
                    return Results.error("搜索字符串不能以|开头或者结尾 ");
                }
                if (keyword.contains("||")) {
                    return Results.error("搜索关键词不能含有两个或两个以上的|");
                }
                keys = keyword.split("\\|");
            }
            return Results.of(klgService.select(curId, keys));
        } catch (Exception e) {
            e.printStackTrace();
            return Results.exception();
        }
    }
}

package com.bbw.namesys.controller;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.service.interactive_new.InteractiveResNew;
import com.bbw.namesys.service.interactive_new.Interactive_newService;
import com.bbw.namesys.service.interactive_new.SelectKeys;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/interactive_new")
public class InteractiveResNewController {

    @Autowired
    private Interactive_newService interactive_newService;

    @RequestMapping("/getByKeys")
    public Result getRes(SelectKeys selectKeys) {
        return interactive_newService.select(selectKeys);
    }

    private final String reg = "^【所属网】([ABC][1-9])[\\n\\r]【姓名】([^\\n\\r]+)[\\n\\r]【性别】([男女])[\\n\\r]【籍贯】([^\\n\\r]+)[\\n\\r]【年龄】([0-9]+)[\\n\\r]【推荐人】([^\\n\\r]+)[\\n\\r]【传统行业】([^\\n\\r]+)[\\n\\r]【付出工作】([^\\n\\r]+)[\\n\\r]【电话】([0-9]+)[\\n\\r]【微信】([^\\n\\r]+)[\\n\\r]【备注】([^\\n\\r]+)";
    private Pattern wp = Pattern.compile(reg);

    @RequestMapping("/add")
    public Result add(String info) {
        if (StringUtils.isBlank(info)) {
            return Results.error("内容不能为空~");
        }
        try {
            info = info.replaceAll(" ","");
            Matcher matcher = wp.matcher(info);
            if (!matcher.find()) {
                return Results.error("输入内容格式不正确,不要含有特殊字符");
            }
            InteractiveResNew interactiveResNew = new InteractiveResNew();
            interactiveResNew.setNet(matcher.group(1));
            interactiveResNew.setName(matcher.group(2));
            interactiveResNew.setSex(matcher.group(3));
            interactiveResNew.setNativeplace(matcher.group(4));
            interactiveResNew.setAge(Integer.valueOf(matcher.group(5)));
            interactiveResNew.setParent(matcher.group(6));
            interactiveResNew.setTradition(matcher.group(7));
            interactiveResNew.setPayfor(matcher.group(8));
            interactiveResNew.setTel(matcher.group(9));
            interactiveResNew.setWechart(matcher.group(10));
            interactiveResNew.setOther(matcher.group(11));
            return interactive_newService.add(interactiveResNew);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Results.exception();
        }
    }

  @RequestMapping("/edit")
    public Result edit(Integer id, String info) {
        if (id == null) {
            return Results.error("id 非法");
        }
      if (StringUtils.isBlank(info)) {
          return Results.error("内容不能为空~");
      }
      try {
          info = info.replaceAll(" ","");
          Matcher matcher = wp.matcher(info);
          if (!matcher.find()) {
              return Results.error("输入内容格式不正确,不要含有特殊字符");
          }
          InteractiveResNew interactiveResNew = new InteractiveResNew();
          interactiveResNew.setNet(matcher.group(1));
          interactiveResNew.setName(matcher.group(2));
          interactiveResNew.setSex(matcher.group(3));
          interactiveResNew.setNativeplace(matcher.group(4));
          interactiveResNew.setAge(Integer.valueOf(matcher.group(5)));
          interactiveResNew.setParent(matcher.group(6));
          interactiveResNew.setTradition(matcher.group(7));
          interactiveResNew.setPayfor(matcher.group(8));
          interactiveResNew.setTel(matcher.group(9));
          interactiveResNew.setWechart(matcher.group(10));
          interactiveResNew.setOther(matcher.group(11));
          return interactive_newService.edit(id, interactiveResNew);
      } catch (NumberFormatException e) {
          e.printStackTrace();
          return Results.exception();
      }
    }

    @RequestMapping("/editRes")
    public ModelAndView editRes(int id) {
        ModelAndView mv = new ModelAndView("/interactive_new/editRes");
        mv.addObject("result", Results.of(interactive_newService.select(id)));
        return mv;
    }
}

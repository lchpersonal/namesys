package com.bbw.namesys.controller;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.service.children.ChildrenService;
import com.bbw.namesys.service.namelist.NameInfo;
import com.bbw.namesys.service.namelist.NameInfoService;
import com.bbw.namesys.service.paving.PavingRecord;
import com.bbw.namesys.service.paving.PavingService;
import com.bbw.namesys.service.paving.VPavingRecord;
import com.bbw.namesys.service.sts.StsService;
import com.bbw.namesys.service.user.User;
import com.bbw.namesys.service.user.UserService;
import com.bbw.namesys.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/children")
public class ChildrenController {

    @Autowired
    private ChildrenService childrenService;
    @Autowired
    private StsService stsService;
    @Autowired
    private UserService userService;
    @Autowired
    private PavingService pavingService;
    @Autowired
    private NameInfoService nameInfoService;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("children/list");
        mv.addObject("data", Results.of(childrenService.select(SessionUtil.getUsername(request))));
        return mv;
    }

    @RequestMapping("/info")
    public ModelAndView selfInfo(String childUsername, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/children/info");
        String username = SessionUtil.getUsername(request);
        User child = userService.selectUser(childUsername);
        if (child == null) {
            mv.addObject("data", Results.error("该主任不存在"));
        } else if (!username.equals(child.getParentUsername())) {
            mv.addObject("data", Results.error("不是你的主任，你无权查看"));
        } else {
            mv.addObject("data", stsService.selectSelfStsInfo(childUsername));
            mv.addObject("name", child.getName());
            mv.addObject("childUsername", child.getUsername());
        }
        return mv;
    }

    @RequestMapping("/pavingRecords.html")
    public ModelAndView pavingRecordsPage(String childUsername) {
        ModelAndView mv = new ModelAndView("/children/pavingRecords");
        mv.addObject("childUsername", childUsername);
        return mv;
    }

    @RequestMapping("/pavingRecords.json")
    public Result pavingRecords(@RequestParam(defaultValue = "0") int curId, String childUsername,
                                HttpServletRequest request) {

        String username = SessionUtil.getUsername(request);
        User child = userService.selectUser(childUsername);
        if (child == null) {
            return Results.error("该主任不存在");
        } else if (!username.equals(child.getParentUsername())) {
            return Results.error("不是你的主任，你无权查看");
        }
        if (curId == 0) {
            curId = Integer.MAX_VALUE;
        }
        List<PavingRecord> pavingRecords = pavingService.selectByUsername(childUsername, curId);
        if (pavingRecords.isEmpty()) {
            return Results.success();
        }
        List<VPavingRecord> vPavingRecords = new ArrayList<>();
        List<Integer> nameInfoIds = pavingRecords.stream().map(PavingRecord::getNameInfoId).collect(Collectors.toList());
        Map<Integer, NameInfo> map = nameInfoService.selectNameInfosMap(nameInfoIds);
        for (PavingRecord pavingRecord : pavingRecords) {
            VPavingRecord vPavingRecord = new VPavingRecord();
            vPavingRecord.setPavingRecord(pavingRecord);
            vPavingRecord.setNameInfo(map.get(pavingRecord.getNameInfoId()));
            vPavingRecords.add(vPavingRecord);
        }
        return Results.of(vPavingRecords);
    }
}

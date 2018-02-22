package com.bbw.namesys.service.interactive;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.mapper.InteractiveResMapper;
import com.bbw.namesys.utils.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 互动资源
 */
@Service
public class InteractiveResService {

    @Autowired
    private InteractiveResMapper interactiveResMapper;

    public Result add(String info) {
        interactiveResMapper.add(info);
        return Results.success();
    }

    public Result edit(int id, String info) {
        interactiveResMapper.edit(id, info);
        return Results.success();
    }

    public Result select(int id) {
        InteractiveRes res = interactiveResMapper.selectById(id);
        return Results.of(res);
    }

    /**
     * keywords 为空查询所有
     */
    public Result select(int curId, String[] keywords) {
        List<InteractiveRes> interactiveResList = interactiveResMapper.selectByKeywords(curId, keywords);
        for (InteractiveRes interactiveRes : interactiveResList) {
            interactiveRes.setInfo(HtmlUtil.db2Html(interactiveRes.getInfo()));
        }
        return Results.of(interactiveResList);
    }

    public Result doZan(int id) {
        return null;
    }

    public Result undoZan(int id) {
        return null;
    }

}

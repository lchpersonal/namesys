package com.bbw.namesys.service.interactive_new;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.mapper.InteractiveResNewMapper;
import com.bbw.namesys.utils.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Interactive_newService {

    @Autowired
    private InteractiveResNewMapper interactiveResNewMapper;

    public Result add(InteractiveResNew interactiveResNew) {
        int count = interactiveResNewMapper.add(interactiveResNew);
        return count > 0 ? Results.success() : Results.error("姓名已存在");
    }

    public Result edit(int id, InteractiveResNew interactiveResNew) {
        int count = interactiveResNewMapper.edit(id, interactiveResNew);
        return count > 0 ? Results.success() : Results.error();
    }

    public InteractiveResNew select(int id) {
        InteractiveResNew interactiveResNew = interactiveResNewMapper.select(id);
        interactiveResNew.setInfo(interactiveResNew.format());
        return interactiveResNew;
    }

    public Result select(SelectKeys selectKeys) {
        List<InteractiveResNew> interactiveResNews = interactiveResNewMapper.selectByKeys(selectKeys);
        for (InteractiveResNew interactiveResNew : interactiveResNews) {
            interactiveResNew.setInfo(HtmlUtil.db2Html(interactiveResNew.format()));
        }
        return Results.of(interactiveResNews);
    }
}

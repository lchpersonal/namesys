package com.bbw.namesys.service.klg;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.mapper.KlgMapper;
import com.bbw.namesys.utils.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KlgService {

    @Autowired
    private KlgMapper klgMapper;

    public Result addKlg(String title, String content, String username) {
        klgMapper.insert(title, content, username);
        return Results.success();
    }

    public Result editKlg(String title, String content, int id, String username) {
        klgMapper.update(title, content, id, username);
        return Results.success();
    }

    public List<Klg> select(int curId, String[] keys) {
        List<Klg> klgs = klgMapper.select(curId, keys);
        for (Klg klg : klgs) {
            klg.setContent(HtmlUtil.db2Html(klg.getContent()));
        }
        return klgs;
    }

    public Klg selectById(int id) {
        return klgMapper.selectById(id);
    }
}

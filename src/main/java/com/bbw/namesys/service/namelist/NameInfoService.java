package com.bbw.namesys.service.namelist;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.mapper.NameListMapper;
import com.bbw.namesys.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NameInfoService {

    @Autowired
    private NameListMapper nameListMapper;

    /**
     * 根据用户名查询特定梯队的名单
     */
    public List<NameInfo> select(String username, int echelon, String keyword) {
        if (StringUtils.isBlank(keyword)) {
            keyword = StringUtils.EMPTY;
        }
        return nameListMapper.select(username, echelon, keyword);
    }

    public NameInfo selectNameInfo( int id, String username) {
        return nameListMapper.selectNameInfo(id,username);
    }


    public NameDetailInfo select(int id) {
        return nameListMapper.selectNameDetailInfo(id);
    }

    public Result addNames(String username, String names) {
        int count = nameListMapper.addNames(username, names);
        return Results.of(count);
    }

    public Result modify(NameDetailInfo detailInfo) {
        NameInfo nameInfo = nameListMapper.selectNameInfo(detailInfo.getId(),detailInfo.getUsername());
        if(nameInfo == null){
            return Results.accessdenied();
        }
        nameListMapper.modifyNameInfo(process(detailInfo));
        return Results.success();

    }

    private NameDetailInfo process(NameDetailInfo detailInfo) {
        String jsonStr = JsonUtil.serialize(detailInfo);
        Map<String, Object> map = JsonUtil.deserialize(jsonStr, HashMap.class);
        for (String s : map.keySet()) {
            Object val = map.get(s);
            if (val != null && val instanceof String) {
                String str = val.toString().replaceAll("[<>\'\"]", "")
                        .replaceAll("\n", "<br/>");
                map.put(s, str);
            }
        }
        NameDetailInfo info = JsonUtil.deserialize(JsonUtil.serialize(map), NameDetailInfo.class);
        return info;
    }


    public List<EchelonInfo> selectEchelonInfos(String username) {
        return nameListMapper.selectEchelonInfo(username);
    }


    public void updEchelonInfos(String username, int[] ids, int echelon) {
        nameListMapper.updEchelonInfos(username, ids, echelon);
    }

    public void updEchelonInfos(String username, int[] ids0, int[] ids1, int[] ids2, int[] ids3) {
        if (ids0 != null && ids0.length > 0) {
            this.updEchelonInfos(username, ids0, 0);
        }
        if (ids1 != null && ids1.length > 0) {
            this.updEchelonInfos(username, ids1, 1);
        }
        if (ids2 != null && ids2.length > 0) {
            this.updEchelonInfos(username, ids2, 2);
        }
        if (ids3 != null && ids3.length > 0) {
            this.updEchelonInfos(username, ids3, 3);
        }
    }
}

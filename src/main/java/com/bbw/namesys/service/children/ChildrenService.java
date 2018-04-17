package com.bbw.namesys.service.children;

import com.bbw.namesys.mapper.ChildrenMapper;
import com.bbw.namesys.service.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildrenService {

    @Autowired
    private ChildrenMapper childrenMapper;

    public List<UserInfo> select(String username) {
        return childrenMapper.select(username);

    }
}

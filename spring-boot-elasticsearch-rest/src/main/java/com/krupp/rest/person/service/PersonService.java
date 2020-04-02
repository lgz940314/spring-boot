package com.krupp.rest.person.service;

import com.alibaba.fastjson.JSONObject;
import com.krupp.rest.person.model.SearchParam;
import org.springframework.http.ResponseEntity;

public interface PersonService {
    JSONObject getDataById(SearchParam param);

    ResponseEntity add(SearchParam param);

    ResponseEntity update(SearchParam param);

    ResponseEntity delete(SearchParam param);
}

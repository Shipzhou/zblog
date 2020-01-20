package com.shipzhou.zblog.service;

import com.shipzhou.zblog.constant.ExistEnum;
import org.springframework.data.mongodb.core.query.Criteria;

/**
 * @author Shipzhou
 * @date: 2020/1/19
 */
public class BaseService {

    public static Criteria getCriteriaById(Long id) {
        return Criteria.where("id").is(id);
    }

    public static Criteria getExistCriteria() {
        return Criteria.where("exist").is(ExistEnum.TRUE.getValue());
    }

    public static Criteria getExistCriteriaById(Long id) {
        return Criteria.where("exist").is(ExistEnum.TRUE.getValue()).and("id").is(id);
    }
}

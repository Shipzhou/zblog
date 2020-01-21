package com.shipzhou.zblog.entity.vo.common;

import lombok.Data;

import java.util.List;

/**
 * @author Shipzhou
 * @date: 2020/1/21
 * 通用分页返回类
 */
@Data
public class PageVO<T> {
    private Long total;
    private List<T> datas;
}

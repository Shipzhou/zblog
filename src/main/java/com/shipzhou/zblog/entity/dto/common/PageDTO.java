package com.shipzhou.zblog.entity.dto.common;

import lombok.Data;

/**
 * @author Shipzhou
 * @date: 2020/1/22
 */
@Data
public class PageDTO {

    // 当前页数
    private Integer pageIndex;

    // 每页数量
    private Integer pageSize;
}

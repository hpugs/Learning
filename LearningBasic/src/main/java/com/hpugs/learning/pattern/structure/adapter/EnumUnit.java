package com.hpugs.learning.pattern.structure.adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/27 下午4:44
 */
@AllArgsConstructor
@Getter
public enum EnumUnit {

    DEGREE(1, '°', "度"),
    LITRE(2, 'L', "升");

    private Integer type;

    private char symbol;

    private String note;

}

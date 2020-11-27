package com.hpugs.learning.pattern.structure.adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 汽油型号
 *
 * @author gaoshang
 * date: 2020/11/27 下午4:52
 */
@AllArgsConstructor
@Getter
public enum EnumFuelTypes {

    T95("#95", "220V"),
    T92("#92", "200V");

    private String type;

    private String voltage;

}

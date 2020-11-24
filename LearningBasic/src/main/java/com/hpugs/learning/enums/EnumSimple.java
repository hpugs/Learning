package com.hpugs.learning.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/18 上午10:05
 */
@AllArgsConstructor
@Getter
public enum EnumSimple {

    FIRST("first"){
        @Override
        public void pintName() {
            System.out.println(this.name.toUpperCase());
        }
    },
    SECOND("second"){
        @Override
        public void pintName() {
            System.out.println(this.name + " " + name());
        }
    },
    THIRDLY("thirdly"){
        @Override
        public void pintName() {
            System.out.println(this.name + " " + name().toLowerCase());
        }
    };

    public String name;

    public abstract void pintName();

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

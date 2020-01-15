package org.study.springboot.util;

/**
 * @ClassName: MyEnum
 * @Description:
 * @Author: Lcc
 * @Date: 2019/12/31
 * @Version 1.0
 */
public class MyEnum {

    public enum Season{
        spring(0,"春"),summer(1,"夏"),autumn(2,"秋"),winter(3,"冬");
        public Integer index;
        public String value;

        Season(Integer index, String value){
            this.index = index;
            this.value = value;
        }

        public static Season getSeasonByIndex(Integer index){
            for (Season season : Season.values()){
                if (index.equals(season.index)){
                    return season;
                }
            }
            return null;
        }

        public static Integer getIndex(String value){
            for (Season season : Season.values()){
                if (value.equals(season.value)){
                    return season.index;
                }
            }
            return null;
        }

        public static String getValue(Integer index){
            for (Season season: Season.values()){
                if (index.equals(season)){
                    return season.value;
                }
            }
            return null;
        }
    }

}

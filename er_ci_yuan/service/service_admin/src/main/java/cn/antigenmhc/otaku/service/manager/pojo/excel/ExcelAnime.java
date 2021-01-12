package cn.antigenmhc.otaku.service.manager.pojo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/11 11:22
 * @Version: 1.0
 **/
@Data
public class ExcelAnime {

    @ExcelProperty("一级分类")
    private String firstLevelTitle;

    @ExcelProperty("二级分类")
    private String twiceLevelTitle;
}

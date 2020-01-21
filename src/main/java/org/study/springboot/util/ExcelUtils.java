package org.study.springboot.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * 操作Excel工具类.
 *
 * @author lcc
 * @version 1.0
 * @date 2020/1/21
 */
public final class ExcelUtils {

    private static final String XLSX_FILE_SUFFIX = "xlsx";
    private static final String XLS_FILE_SUFFIX = "xls";

    /**
     *<p> 导出Excel.
     *
     * @param params 导出参数
     * @param pojoClass Excel对象Class
     * @param dataSet 导出的数据集合
     * @param response HttpServletResponse
     * @param fileName 文件名
     * @return
     */
    public static void export(ExportParams params, Class<?> pojoClass,
                              Collection<?> dataSet, HttpServletResponse response, String fileName) throws IOException {
        Workbook workbook = ExcelExportUtil.exportExcel(params, pojoClass, dataSet);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        String fileSuffix = XLSX_FILE_SUFFIX;
        switch (params.getType()) {
            case HSSF:
                fileSuffix = XLS_FILE_SUFFIX;
                break;
            default:
        }
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + fileSuffix);
        workbook.write(response.getOutputStream());
    }
}

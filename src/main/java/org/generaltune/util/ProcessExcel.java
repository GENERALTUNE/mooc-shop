package org.generaltune.util;

/**
 * Created by zhumin on 2016/12/15.
 */

    import javafx.scene.control.Cell;
    import org.apache.poi.sl.usermodel.Sheet;
    import org.apache.poi.ss.usermodel.Workbook;
    import org.apache.poi.ss.util.WorkbookUtil;

    import java.io.*;
    import java.util.ArrayList;
    import java.util.List;



public class ProcessExcel {

        /**
         * 解析excel文件
         * @param 
         * @return
         */
    public static void  main(String[] args) {
        int i;
        Sheet sheet;
        Workbook book;
        Cell cell1,cell2,cell3;
        try {
            File file = new File("D:\\projects\\help.txt");
//            file.
//            Reader reader = InputStreamReader(file);
            System.out.println(file.toString());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }


}


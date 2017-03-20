package org.generaltune.db;

import org.generaltune.util.DBhepler;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.Date;

/**
 * Created by zhumin on 2017/3/20.
 */
public class DbTest {


    //数据库数据与excel互相导入代码  可参考 http://blog.csdn.net/qq_35101189/article/details/63250908
    @Test
    public void testDb() throws Exception {
        DBhepler dBhepler = new DBhepler();
        String sql = "SELECT * from user";
        ResultSet rs = dBhepler.Search(sql, null);

        while (rs.next()) {
            int id=rs.getInt("uid");
            String name=rs.getString("name");
            System.out.println(id + name);
        }
    }
}

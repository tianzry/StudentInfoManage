package cn.tianzry.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 解析Json数据的工具类，将从数据库查询到的数据，转换为Json数据格式
 * @author tianz
 *
 */
public class JsonUtil {
	
	public static JSONArray formatRsToJsonArray(ResultSet rs) throws Exception {
		ResultSetMetaData md = rs.getMetaData();
		int num = md.getColumnCount();
		JSONArray array = new JSONArray();
		// 这是一个一行行查询，插列值的方法
		while (rs.next()) {
			JSONObject mapOfColValues  = new JSONObject();
			for (int i = 1; i <= num; i++) {
				mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
			}
			array.add(mapOfColValues);
		}
		return array;
	}
}

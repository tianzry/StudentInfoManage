package cn.tianzry.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ����Json���ݵĹ����࣬�������ݿ��ѯ�������ݣ�ת��ΪJson���ݸ�ʽ
 * @author tianz
 *
 */
public class JsonUtil {
	
	public static JSONArray formatRsToJsonArray(ResultSet rs) throws Exception {
		ResultSetMetaData md = rs.getMetaData();
		int num = md.getColumnCount();
		JSONArray array = new JSONArray();
		// ����һ��һ���в�ѯ������ֵ�ķ���
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

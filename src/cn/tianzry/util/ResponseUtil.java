package cn.tianzry.util;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import java.io.PrintWriter;

/**
 * 对响应进行写的工具类
 * @author tianz
 *
 */
public class ResponseUtil {

	public static void write(HttpServletResponse response, JSONObject jsonObject) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jsonObject.toString());
		out.flush();
		out.close();
	}
}

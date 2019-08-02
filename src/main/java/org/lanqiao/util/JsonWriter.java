package org.lanqiao.util;

import com.google.gson.Gson;
import org.lanqiao.vo.JsonResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * JsonWriter 工具类
 * 专门把对象转化成json,写入客服端
 * @author liuyue
 *
 */
public class JsonWriter {
	public static void  writer(HttpServletResponse resp, JsonResult result) throws IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		Gson gson = new Gson();
		PrintWriter pw = resp.getWriter();
		String mapJson = gson.toJson(result);
	//System.out.println(mapJson);
		pw.println(mapJson);
		pw.flush();
		pw.close();
	}

}

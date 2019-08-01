package org.lanqiao.util;

public class ByteUtils {
	public static Byte toByte(Object obj) {
		return  obj== null ? 0 : Byte.valueOf(obj.toString());
	}

}

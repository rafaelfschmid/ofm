package com.net.multiway.background.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Utils {

	public static byte[] intToByteArray(int value) {
		return new byte[] { (byte) value, (byte) (value >>> 8), (byte) (value >>> 16), (byte) (value >>> 24) };
	}

	public static int byte4ToInt(byte[] bytes) {
		return bytes[3] << 24 | (bytes[2] & 0xFF) << 16 | (bytes[1] & 0xFF) << 8 | (bytes[0] & 0xFF);
	}

	public static int byte2ToInt(byte[] bytes) {
		return (bytes[1] & 0xFF) << 8 | (bytes[0] & 0xFF);
	}

	public static float byte4ToFloat(byte[] d) {
		return ByteBuffer.wrap(d).order(ByteOrder.LITTLE_ENDIAN).getFloat();
	}
	public static byte [] floatToByteArray (float value)
	{  
		byte [] b =ByteBuffer.allocate(4).putFloat(value).array();
		byte [] c = new byte[4];
		for(int i =0;i<4;i++)
			c[i] = b[3-i];
	     return c;
	}

	public static String fillAddress(String address) {
		int length = address.length();

		for (int i = length; i < 16; i++)
			address = address + '\0';
		
		return address;
	}

}

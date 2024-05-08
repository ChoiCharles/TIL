package ch02.sec10;

public class PrimitiveAndStringConversionExample {
	public static void main(String[] args) {
		String str = "10";
		
		// String -> byte
		byte byteValue = Byte.parseByte(str);
		
		// String -> short
		short shortValue = Short.parseShort(str);
		
		// String -> int
		int intValue = Integer.parseInt(str);
		
		// String -> long
		long longValue = Long.parseLong(str);
		
		// String -> float
		float floatValue = Float.parseFloat(str);
		
		// String -> double
		double doubleValue = Double.parseDouble(str);
		
		// String -> boolean
		String booleanStr = "true";
		boolean booleanValue = Boolean.parseBoolean(booleanStr);
		
		// 기본타입 -> String
		int value = 10;
		String stringValue = String.valueOf(value);
		
		
		int value1 = Integer.parseInt("10");
		double value2 = Double.parseDouble("3.14");
		boolean value3 = Boolean.parseBoolean("true");
		
		System.out.println("value1: " + value1);
		System.out.println("value2: " + value2);
		System.out.println("value3: " + value3);
		
		String str1 = String.valueOf(10);
		String str2 = String.valueOf(3.14);
		String str3 = String.valueOf(true);
		
		System.out.println("str1: " + str1);
		System.out.println("str2: " + str2);
		System.out.println("str3: " + str3);
	}
}

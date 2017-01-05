package test;


public class TestFormat {
	public static void main(String[] args) {
		String access = "private";
		String fieldName = "id";
		String javaType = "Long";
		String result = String.format("%1s%2$" + (javaType.length() + 1)
				+ "s%3$" + (fieldName.length() + 2) + "s", access, javaType,
				fieldName + ";");

		System.out.println(result);
		
		String packageName = "com.edu.shop";
		System.out.println("path=" + packageName.replaceAll("\\.", "/"));
		
	}
}

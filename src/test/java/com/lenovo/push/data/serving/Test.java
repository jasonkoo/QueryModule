package com.lenovo.push.data.serving;

public class Test {

	public static void main(String[] args) {
		String test = "a.b.20150405";
		String thedate = test.substring(test.lastIndexOf(".") + 1, test.length());
		System.out.println(thedate);
		test = test.substring(0, test.lastIndexOf("."));
		System.out.println(test);

	}

}

package com.kh.screen;

public class EnterKey {

	public EnterKey() {
		for (int i = 0; i < 34; i++) {
			System.out.println();
		}
	}

	public EnterKey(int x) {
		for (int i = 0; i < 34 - x; i++) {
			System.out.println();
		}
	}
}

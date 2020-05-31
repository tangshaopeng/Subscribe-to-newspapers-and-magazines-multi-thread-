package cn.com.java.experiment;

import cn.com.java.experiment.entity.*;

public class MainClass {
	public static void main(String[] args) {
		SharePool pool = new SharePool();
	
		for (int i = 1; i <= 5; i++) {
			new Thread(new Producer(pool, i)).start();
		}

		new Thread(new Consumer(pool)).start();
	}
}

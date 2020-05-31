package cn.com.java.experiment.entity;

public class Consumer implements Runnable {
	private SharePool pool;

	public Consumer() {
		super();
	}

	public Consumer(SharePool pool) {
		super();
		this.pool = pool;
	}
	public void run() {
		int counter = 1;
		while (counter <= 15) {
			System.out.println("处理者@" + Thread.currentThread().getName() + ":处理第" + counter + "份订阅。");
			pool.consume();
			counter++;
		}
		System.out.println("本线程完成订阅处理量，即刻退出。处理者@" + Thread.currentThread().getName());
	}
}

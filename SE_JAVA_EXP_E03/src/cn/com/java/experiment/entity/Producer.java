package cn.com.java.experiment.entity;

public class Producer implements Runnable {
	private SharePool pool; 
	private int count; 

	public Producer() {
		super();
	}

	public Producer(SharePool pool, int count) {
		super();
		this.pool = pool;
		this.count = count;
	}

	public void run() {
		String media = "报刊杂志";
		int counter = 0;
		System.out.println("订阅者@" + Thread.currentThread().getName() + "：订阅" + count + "份。");
		while (count > 0) {
			counter++;
			System.out.println("订阅者@" + Thread.currentThread().getName() + " 在提交第" + counter + " 份订阅申请。");
			pool.produce(media + counter);
			count--;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {}
		}
		System.out.println("订阅者@" + Thread.currentThread().getName() + "->完成订阅。");
	}
}


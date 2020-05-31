package cn.com.java.experiment.entity;

import java.util.ArrayList;
import java.util.List;

public class SharePool {
	private List<String> pool=new ArrayList(); 
	private final int MAX = 15; 

	public SharePool() {
		super();
	}

	public List<String> getPool() {
		return pool;
	}

	public void setPool(List<String> pool) {
		this.pool = pool;
	}

	// 本方法由订阅者调用，生成订阅信息并完成订阅信息的提交。
	public void produce(String media) {
		synchronized (this) {
			if (pool.size() == MAX) {
				try {
					wait();
				} catch (InterruptedException e) {}
				System.out.println("订阅请求队列已满，等待系统处理订阅请求中……");
			}else {
				pool.add(media);
				System.out.println(
						"订阅者@" + Thread.currentThread().getName() + ":订阅《" + media + "》申请已提交.当前订阅数量为：" + pool.size());
				notify();
			}
		}
	}

	// 本方法由订阅信息消费者调用，完成订阅信息的处理。
	public void consume() {
		synchronized (this) {
			if (pool.size() == 0) {
				try {
					wait();
				} catch (InterruptedException e) {}
				System.out.println("处理者@" + Thread.currentThread().getName() + "暂无订阅请求信息，等待中……");
			}else {
				String message = pool.get(0);
				pool.remove(0);
				System.out.println("处理者@" + Thread.currentThread().getName() + ":处理《" + message + "》订阅已完毕。尚待处理订阅数量为："
						+ pool.size());
				notify();
			}
			
		}

	}
}

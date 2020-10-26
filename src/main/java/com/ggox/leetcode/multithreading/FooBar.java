package com.ggox.leetcode.multithreading;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ggox
 * @Date: 2020/10/10 21:45
 * @Description: TODO
 */
public class FooBar {

	private int n;

	private Semaphore first = new Semaphore(1);
	private Semaphore second = new Semaphore(0);

	public FooBar(int n) {
		this.n = n;
	}

	public void foo(Runnable printFoo) throws InterruptedException {

		for (int i = 0; i < n; i++) {
			first.acquire();
			// printFoo.run() outputs "foo". Do not change or remove this line.
			printFoo.run();
			second.release();
		}
	}

	public void bar(Runnable printBar) throws InterruptedException {

		for (int i = 0; i < n; i++) {
			second.acquire();
			// printBar.run() outputs "bar". Do not change or remove this line.
			printBar.run();
			first.release();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		FooBar fooBar = new FooBar(3);
		new Thread(()->{
			try {
				fooBar.bar(()-> System.out.print("bar"));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		new Thread(()->{
			try {
				fooBar.foo(()-> System.out.print("foo"));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		TimeUnit.HOURS.sleep(1);
	}

}

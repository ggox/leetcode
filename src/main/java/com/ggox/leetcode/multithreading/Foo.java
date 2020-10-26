package com.ggox.leetcode.multithreading;

import java.util.concurrent.TimeUnit;

/**
 * @Author: ggox
 * @Date: 2020/10/9 23:47
 * @Description: 有序打印
 */
public class Foo {

	private final Object lock = new Object();
	private volatile int flag = 0;

	public Foo() {
	}

	public void first(Runnable printFirst) throws InterruptedException {
		synchronized (lock){
			// printFirst.run() outputs "first". Do not change or remove this line.
			printFirst.run();
			flag = 1;
			lock.notifyAll();
		}
	}

	public void second(Runnable printSecond) throws InterruptedException {
		synchronized (lock){
			while (flag != 1) {
				lock.wait();
			}
			// printSecond.run() outputs "second". Do not change or remove this line.
			printSecond.run();
			flag = 2;
			lock.notifyAll();
		}
	}

	public void third(Runnable printThird) throws InterruptedException {
		synchronized (lock){
			while (flag != 2) {
				lock.wait();
			}
			// printThird.run() outputs "third". Do not change or remove this line.
			printThird.run();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Runnable first = () -> System.out.println("first");
		Runnable second = () -> System.out.println("second");
		Runnable third = () -> System.out.println("third");
		Foo foo = new Foo();
		new Thread(() -> {
			try {
				foo.third(third);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		TimeUnit.SECONDS.sleep(1);
		new Thread(() -> {
			try {
				foo.first(first);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		new Thread(() -> {
			try {
				foo.second(second);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		TimeUnit.HOURS.sleep(1);
	}

}

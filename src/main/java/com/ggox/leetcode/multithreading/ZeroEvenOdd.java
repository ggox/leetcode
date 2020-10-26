package com.ggox.leetcode.multithreading;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;

/**
 * @Author: ggox
 * @Date: 2020/10/11 14:16
 * @Description: 打印零与奇偶数
 */
public class ZeroEvenOdd {

	private int n;

	private Semaphore zero = new Semaphore(1);
	private Semaphore even = new Semaphore(0);
	private Semaphore odd = new Semaphore(0);

	public ZeroEvenOdd(int n) {
		this.n = n;
	}

	// printNumber.accept(x) outputs "x", where x is an integer.
	public void zero(IntConsumer printNumber) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			zero.acquire();
			printNumber.accept(0);
			if (i % 2 == 0) {
				odd.release();
			} else {
				even.release();
			}
		}
	}

	public void even(IntConsumer printNumber) throws InterruptedException {
		for (int i = 2; i <= n; i += 2) {
			even.acquire();
			printNumber.accept(i);
			zero.release();
		}

	}

	public void odd(IntConsumer printNumber) throws InterruptedException {
		for (int i = 1; i <= n; i += 2) {
			odd.acquire();
			printNumber.accept(i);
			zero.release();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		IntConsumer consumer = System.out::println;
		ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
		new Thread(() -> {
			try {
				zeroEvenOdd.zero(consumer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		new Thread(() -> {
			try {
				zeroEvenOdd.even(consumer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		new Thread(() -> {
			try {
				zeroEvenOdd.odd(consumer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		TimeUnit.HOURS.sleep(1);
	}

}

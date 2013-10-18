package com.thread;

public class ThreadSynchronized {

	final OutPuter out = new OutPuter();
	
	/**
	 * @param args
	 */
	public  static void main(String[] args) {
		ThreadSynchronized ts = new ThreadSynchronized();
		ts.start();
	}

	public void start() {
		 new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						out.out("fuwei");
					}
				}
			}).start();
		 
		 new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						out.out("willenfoo");
					}
				}
			}).start();
		 
	/*	 new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						out.out2("fuwei");
					}
				}
			}).start();
		 
		 new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						out.out2("willenfoo");
					}
				}
			}).start();*/
		 
		 
		 new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						out.out3("fuwei");
					}
				}
			}).start();
		 
		 new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						out.out3("willenfoo");
					}
				}
			}).start();
	}
	
	static class OutPuter {
		
		public  void out(String name) {
			Integer length = name.length();
			synchronized(OutPuter.class) {
				for (int i = 0; i < length; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		}
		
		public synchronized void out2(String name) {
			Integer length = name.length();
			for (int i = 0; i < length; i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		public static synchronized void out3(String name) {
			Integer length = name.length();
			for (int i = 0; i < length; i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
	}
}

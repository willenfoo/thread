public class ThreadWaitAndNotify {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Business business = new Business();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					business.child(i);
				}
			}
		}).start();
		
		for (int i = 0; i < 50; i++) {
			business.main(i);
		}
	}

	static class Business {
		private boolean childFlag = true;
		public synchronized  void  main(int frequency) {
			while (childFlag) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < 100; i++) {
				System.out.println("main===frequency==" +frequency+ "===now " + i);
			}
			childFlag = true;
			this.notify();
		}
		
		public synchronized void child(int frequency) {
			while (!childFlag) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < 10; i++) {
				System.out.println("child===frequency==" +frequency+ "===now " + i);
			}
			childFlag = false;
			this.notify();
		}
	}
}

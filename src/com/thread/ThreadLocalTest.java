import java.util.Random;

public class ThreadLocalTest {

	static final ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Integer value =  new Random().nextInt();
					threadLocal.set(value);
					System.out.println(Thread.currentThread().getName() + " data ==" + value);
					
					ThreadScopeData.getThreadInstance().setName("name" + value);
					A.getDate();
					B.getDate();
				}
			}).start();
		}
	}
	
	static class A {
		
		public static void getDate() {
			System.out.println("A " + Thread.currentThread().getName() + threadLocal.get());
			System.out.println("a " + Thread.currentThread().getName() + ThreadScopeData.getThreadInstance().getName() );
		}
		
	}
	
	static class B {
		public static void getDate() {
			System.out.println("B " + Thread.currentThread().getName() + threadLocal.get());
			System.out.println("b " + Thread.currentThread().getName() + ThreadScopeData.getThreadInstance().getName());
		}
		
	}
	
	
}

class ThreadScopeData {
	private ThreadScopeData(){}
	
	private String name;
	
	private static ThreadLocal<ThreadScopeData> map = new ThreadLocal<ThreadScopeData>();
	
	public static ThreadScopeData getThreadInstance(){
		ThreadScopeData instance = map.get();
		if(instance == null){
			instance = new ThreadScopeData();
			map.set(instance);
		}
		return instance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

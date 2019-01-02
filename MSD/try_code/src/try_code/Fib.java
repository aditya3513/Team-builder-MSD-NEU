package try_code;

public class Fib {
	 int a;
	 int b;
	  int c;
	
	public int get_fib(int n)
	{
		c = a + b;
		a = b;
		b = c;
		
		System.out.println("c = "+ c);
		
		return 0;
	}
	
	
	public int get_fib_rec(int n)
	{
		if(n>0)
		{
			c = a + b;
			a = b;
			b = c;
			
			System.out.println("c = "+ c);
			
			return get_fib_rec(n-1);
		}
		else
			return 0;
	}
	
	public static void main(String args[])
	{
		Fib f = new Fib();
		f.setA(0);
		f.setB(1);
		f.get_fib_rec(10);
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

}

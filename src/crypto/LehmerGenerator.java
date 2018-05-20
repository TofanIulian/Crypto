package crypto;

public class LehmerGenerator implements Generator
{
	private long a, b, m;
	private long lastX;
	
	public LehmerGenerator(long lastX, long a, long b, long m)
	{
		this.a = a;
		this.b = b;
		this.m = m;
		this.lastX = lastX;
	}
	
	public Object generate() 
	{
		lastX = (a * lastX + b) % m;
		return lastX;
	}
}
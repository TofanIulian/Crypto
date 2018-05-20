package crypto;

import java.util.Arrays;

public class MotherOfAllGenerator implements Generator
{
	private int array[];
	private int c;
	
	public MotherOfAllGenerator(int array[], int c)
	{
		this.array = Arrays.copyOfRange(array,  0,  4);
		this.c = c;
	}
	
	public Object generate()
	{
		long s = 2111111111 * array[0] + 1492 * array[1] + 1776 * array[2] + 5115 * array[3] + c;
		int x = (int)(s % (long)Math.pow(2,  32));
		c = (int)(s / (long)Math.pow(2,  32));
		for (int i = 0; i < 3; ++i)
			array[i] = array[i + 1];
		array[3] = x;
		return x;
	}
}

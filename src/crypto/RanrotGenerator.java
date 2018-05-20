package crypto;

import java.util.Arrays;

public class RanrotGenerator implements Generator
{
	int array[];
	int k, j, b, r;
	
	RanrotGenerator(int array[], int k, int j, int r)
	{
		b = 32;
		this.r = r;
		this.k = k;
		this.j = j;
		this.array = Arrays.copyOfRange(array, 0, k);
	}
	
	public Object generate()
	{
		int x = ((array[k - 1 - j] - array[0] % (int)Math.pow(2, b))) >>> r;
		for (int i = 0; i < k - 1; ++i)
			array[i] = array[i+1];
		array[k-1] = x;
		return x;
		
	}
}

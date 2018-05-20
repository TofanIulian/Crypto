package crypto;

public class Vernam 
{
	//private String text;
	private String key;
	private String encryptedText;
	private String decryptedText;
	
	
	public Vernam(String key, String text)
	{
		this.key = key;
		encrypt(text);
		decrypt(encryptedText);
	}
	
	private void encrypt(String text)
	{
		int i, j = 0;
		char chars[] = new char[text.length()];
		for (i = 0; i < text.length(); ++i)
		{
			chars[i] = (char) (text.charAt(i) ^ key.charAt(j));
			++j;
			if (j >= key.length())
			{
				j = 0;
			}
		}
		encryptedText = String.valueOf(chars);
	}
	
	private void decrypt(String text)
	{
		int i, j = 0;
		char chars[] = new char[text.length()];
		for (i = 0; i < text.length(); ++i)
		{
			chars[i] = (char) (text.charAt(i) ^ key.charAt(j));
			++j;
			if (j >= key.length())
			{
				j = 0;
			}
		}
		decryptedText = String.valueOf(chars);
	}
	
	public String getEcnrypted()
	{
		return encryptedText;
	}
	
	public String getDecrypted()
	{
		return decryptedText;
	}
}

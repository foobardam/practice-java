package basic;

import java.util.Random;

public class RandomTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random r = new Random();
		for(int i = 0; i < 100; i++)
			System.out.println(r.nextInt(3));

	}

}

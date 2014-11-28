package pa3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class hilbert
{
	final private static int BITS_PER_DIM = 16;
	static ArrayList<Tuple> hilbertValues;
	public static void main(String[] args) throws FileNotFoundException
	{
		hilbertValues = new ArrayList<Tuple>(30000);
		Scanner sc = new Scanner(new File("project3dataset30K-1.txt"));
		sc.useDelimiter(",\\s*|\\s+");
		while(sc.hasNext())
		{
			int x = sc.nextInt();
			int y = sc.nextInt();
			hilbertValues.add(new Tuple(x, y, getHilbertValue(x, y)));
		}
		Collections.sort(hilbertValues);
		System.out.println("Done sorting!");
		System.out.println(hilbertValues.size());
	}
	
	public static long getHilbertValue(int x1, int x2) {
		long res = 0;
		
		for (int ix = BITS_PER_DIM - 1; ix >= 0; ix--) {
			long h = 0;
			long b1 = (x1 & (1 << ix)) >> ix;
			long b2 = (x2 & (1 << ix)) >> ix;
			
			if (b1 == 0 && b2 == 0) {
				h = 0;
			} else if (b1 == 0 && b2 == 1) {
				h = 1;
			} else if (b1 == 1 && b2 == 0) {
				h = 3;
			} else if (b1 == 1 && b2 == 1) {
				h = 2;
			}
			res += h << (2 * ix);
		}
		return res;
	}
}

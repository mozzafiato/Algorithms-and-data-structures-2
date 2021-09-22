
public class Izziv1 {
	
	public static int[] generateTable(int n) {
		int[] rez = new int[n];
		for(int i=0;i<n;i++) {
			rez[i]=i;
		}
		return rez;
	}
	
	public static int findLinear(int[] a, int v) {
		for(int i=0;i<a.length;i++) {
			if(a[i]==v) return v;
		}
		return -1;
	}
	
	public static long timeLinear(int n) {
		return 0;
	}
	
	public static long timeBinary(int n) {
		return 0;
	}
	
	public static int findBinary(int[] a, int l, int r, int v) {
		if(l>=r) return -1;
		if(r==v || r==v) return v;
		else {
			int sredina = (r+l)/2;
			if(v<=sredina) return findBinary(a,l,sredina,v);
			else return findBinary(a, sredina, r, v);
		}
	}
	
	
	public static void izpisi(int n, long t) {
		int l = 12-n;
		for(int i=0;i<l;i++)
			System.out.print(" ");
		System.out.print(t);
		System.out.print(" |");
	}
	
	public static void main(String[] args) {
		
		System.out.println("   n     |    linearno  |   dvojisko   |");
		System.out.println("---------+--------------+---------------");
		
		
		for(int i=1000;i<=100000;i+=1000) {
			int tabela[] = generateTable(i);
			int random = (int)(Math.random() * i + 1);
			
			long startTime1 = System.nanoTime();
			findLinear(tabela, random);
			long executionTime1 = System.nanoTime() - startTime1;
			
			
			long startTime2 = System.nanoTime();
			findBinary(tabela, 0, i, random);
			long executionTime2 = System.nanoTime() - startTime2;
			
			if(i<10000)
			System.out.print("    "+i+" |");
			else if(i>=10000 && i<100000)
			System.out.print("   "+i+" |");  	
			else
			System.out.print("  "+i+" |");
			
			int len1 = String.valueOf(executionTime1).length();
			int len2 = String.valueOf(executionTime2).length();
			
			int l1 = 13-len1;
			for(int k=0;k<l1;k++)
				System.out.print(" ");
			System.out.print(executionTime1);
			System.out.print(" |");

			
			int l2 = 13-len2;
			for(int m=0;m<l2;m++)
				System.out.print(" ");
			System.out.print(executionTime2);
			System.out.print(" |");
			System.out.println();
			
			
		}

	}

}

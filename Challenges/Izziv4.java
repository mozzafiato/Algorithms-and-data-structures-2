import java.util.Scanner;

public class Izziv4 {

	public static int stEnk(String s) {
		int l = s.length();
		int count = 0;
		for(int i=0;i<l;i++) {
			if(s.charAt(i)=='1') count++;
		}
		return count;
	}
	
	public static int min(int[] a, int n) {
		int min=a[0];
		for(int i=0;i<n;i++) {
			if(a[i]<min) min = a[i];
		}
		return min;
	}
	public static int maks(int[] a, int n) {
		int maks=a[0];
		for(int i=0;i<n;i++) {
			if(a[i]>maks) maks = a[i];
		}
		return maks;
	}
	
	public static void izpisi(int[] a, int n) {
		for(int i=0;i<n;i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		int[] b = new int[n];
		
		for(int i=0;i<n;i++) {
			a[i] = sc.nextInt();
			String binary = Integer.toBinaryString(a[i]);
			b[i] = stEnk(binary);
			//System.out.print(b[i] + " ");
		}
		//System.out.println();
		
		int min = min(b,n);
		int maks = maks(b,n);
		int m = (maks-min)+1;
		//System.out.println(maks + " " + min);
		int[] tabela = new int[m];
		int[] kumulativna = new int[m];
		for(int i=0;i<n;i++) {
			tabela[b[i]-min]++;
		}
		
		kumulativna[0] = tabela[0];
		for(int i=1;i<m;i++) {
			kumulativna[i]+= kumulativna[i-1]+tabela[i];
		}
		
		//izpisi(tabela, m);
		//izpisi(kumulativna, m);
		
		int[] urejena = new int[n];
		
		for(int i=n-1;i>=0;i--) {
			int x = b[i];
			int index = kumulativna[x-min]-1;
			//System.out.println("element " + b[i] + " oz " + a[i] + "stavimo na index " + index);
			System.out.println("("+a[i]+","+index+")");
			kumulativna[x-min]--;
			urejena[index] = a[i];
		}
		izpisi(urejena, n);
		
	}

}

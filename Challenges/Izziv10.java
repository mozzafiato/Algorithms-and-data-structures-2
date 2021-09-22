import java.util.Scanner;

public class Izziv10 {
	
	public static int max(int a, int b) {
		if(a>b) return a;
		else return b;
	}
	
	public static void izpisi(int[][] m, int n, int k) {
		System.out.print("    ");
		for(int i=1;i<=k;i++) {
			if(i<10)
			System.out.print("   " + i);
			else System.out.print("  " + i);
		}
		System.out.println();
		for(int i=0;i<=n;i++) {
			System.out.print("  ");
			for(int j=0;j<=k;j++) {
				if(j!=0) {
					if(m[i][j]<10)
					System.out.print("   " + m[i][j]);
					else System.out.print("  " + m[i][j]);
				}
				else {
					if(m[i][j]<10)
					System.out.print(" " + m[i][j]);
					else System.out.print(m[i][j]);
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[][] matrika = new int[n+1][k+1];
		for(int i=0;i<=k;i++) {
			matrika[0][i]=0;
			matrika[1][i]=1;
		}
		for(int i=0;i<=n;i++) {
			matrika[i][0]=i;
			matrika[i][1]=i;
		}
		
		for(int i=2;i<=n;i++) {
			for(int j=2;j<=k;j++) {
				int s1 = 0;
				int minimum=Integer.MAX_VALUE;
				for(int s=i-1;s>=0;s--) {
					int m = max(matrika[s][j], matrika[s1][j-1]);
					if(m<minimum)minimum=m;
					s1++;
				}
				matrika[i][j]=minimum+1;
			}
		}
		
		
		izpisi(matrika,n,k);
		
	}
}

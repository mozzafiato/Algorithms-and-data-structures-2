import java.util.Scanner;
public class Izziv7 {

	
	public static int nextPrime(int n) {
		n++;
		while(true) {
			if(isPrime(n)) return n;
			else n++;
		}
	}
	public static boolean isPrime(int n) {
		if(n==1 || n==0) return false;
		if(n==2) return true;
		for(int i=2;i<n;i++) {
			if(n%i==0) return false;
		}
		return true;
	}
	
	public static void izpisi(int[] a, int n,int z) {
		System.out.print(z + ": ");
		for(int i=0;i<n;i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	public static void iteracija(int z,int n) {
		int m[][] = new int[z][z];
		
		for(int i=1;i<z;i++) {
			m[1][i]=i;
			m[2][i]=(i*i)%z;
		}
		
		for(int i=3;i<=n;i++) {
			for(int j=1;j<z;j++) {
				if(m[i-1][j]==1) m[i-1][j]=0;
				m[i][j]=(m[i-1][j]*m[1][j])%z;
			}
		}
		//preverimo zadnjo(n-to) vrstico ce ima enke
		int rezultat[] = new int[z];
		int indeks=0;
		for(int i=2;i<z;i++) {
			if(m[n][i]==1) {
				rezultat[indeks] = i;
				indeks++;
			}
		}
		
		if(indeks==0) iteracija(nextPrime(z),n);
		else {
			izpisi(rezultat, indeks,z);
			vandermondovaMatrika(rezultat[0],n, z);
		}
	}
	
	public static void vandermondovaMatrika(int w, int n, int z) {
		int f[][] = new int[n][n];
		
		for(int i=0;i<n;i++) {
			f[0][i]=1;
			f[i][0]=1;
		}
		
		for(int i=1;i<n;i++) {
			f[1][i]=(f[1][i-1]*w)%z;
		}
		
		for(int i=2;i<n;i++) {
			for(int j=1;j<n;j++) {
				f[i][j]=(f[1][j]*f[i-1][j])%z;
			}
		}
		izpisiMatriko(f,n);
	}
	
	public static void izpisiMatriko(int[][] m, int n) {
		for(int i=0;i<n;i++) {
			if(n<7)
			System.out.print(" ");
			for(int j=0;j<n;j++) {
				if(n<7) {
				if(m[i][j]<10)
				System.out.print(m[i][j] + "  ");
				else System.out.print(m[i][j]+ " ");
				}
				else {
					if(m[i][j]<10)
				    System.out.print(" " + m[i][j] + " ");
				    else System.out.print(m[i][j]+ " ");
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int zac = nextPrime(n);
		iteracija(zac,n);

	}

}

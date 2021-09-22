import java.util.*;

public class Izziv2 {
	
 
	 public static void pogrezni(int a[], int i, int dolzKopice) {
		 int oce = a[i];
		 int sin=0;

		 if((2*i+1)<dolzKopice) {
			 sin = 2*i+1;
		 }
		 else return;
		 
		 if((2*i+2)<dolzKopice) {
			 if(a[2*i+1]<a[2*i+2]) sin = 2*i+2;
		 }
		 
		 if(oce<a[sin]) {
			 a[i] = a[sin];
			 a[sin] = oce;
			 //System.out.println("zamenjamo "+a[i]+ " in "+a[sin]);
			 pogrezni(a, sin, dolzKopice);
		 }
		 
	 }
	 
	 public static void izpisiKopico(int a[],int n) {
		 
		 int j = 1;
		 int stIzpisanih=0;
		 
		 for(int i=0;i<n;i++) {
			 
			 System.out.print(a[i]+" ");
			 stIzpisanih++;
			 
			 if(stIzpisanih==j && i!=n-1) {
				 System.out.print("|"+" ");
				 j*=2;
				 stIzpisanih=0;
			 }
		 }
		 System.out.println();
	 }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int tabela[] = new int[n];
		
		for(int i=0;i<n;i++) {
			tabela[i] = sc.nextInt();
		}
		
		
		//zgradimo kopico oz preurejamo tabelo 
		for(int i=(n-1)/2;i>=0;i--) {
			pogrezni(tabela, i, n);
		}
		
		izpisiKopico(tabela,n);
		
		//urejamo
		while(n>1) {
		tabela[0] = tabela[n-1];
		n--;
		pogrezni(tabela, 0, n);
		izpisiKopico(tabela,n);
		}
	}

}

import java.util.Scanner;

public class Naloga1_2 {
	
	public static int stPrimerjav=0;
	public static int stPrirevanj=0;
	
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
	 
	 public static void countup_pogrezni(int a[], int i, int dolzKopice) {
		 int oce = a[i];
		 int sin=0;
		
		 if((2*i+1)<dolzKopice) {
			 sin = 2*i+1;
		 }
		 else return;
		
		 
		 if((2*i+2)<dolzKopice) {
			 if(a[2*i+1]<a[2*i+2]) sin = 2*i+2;
			 stPrimerjav++;
		 }
		 
		 stPrimerjav++;
		 if(oce<a[sin]) {
			 a[i] = a[sin];
			 a[sin] = oce;
			 stPrirevanj+=3;
			 //System.out.println("zamenjamo "+a[i]+ " in "+a[sin]);
			 countup_pogrezni(a, sin, dolzKopice);
		 }
		 
	 }
	 
	 public static void countdown_pogrezni(int a[], int i, int dolzKopice) {
		 int oce = a[i];
		 int sin=0;
		 if((2*i+1)<dolzKopice) {
			 sin = 2*i+1;
		 }
		 else return;
		
		 if((2*i+2)<dolzKopice) {
			 if(a[2*i+1]>a[2*i+2]) sin = 2*i+2;
			 stPrimerjav++;
		 }
		 
		 stPrimerjav++;
		 if(oce>a[sin]) {
			 a[i] = a[sin];
			 a[sin] = oce;
			 stPrirevanj+=3;
			 //System.out.println("zamenjamo "+a[i]+ " in "+a[sin]);
			 countdown_pogrezni(a, sin, dolzKopice);
		 }
		 
	 }
	 
	 public static void pogrezni2(int a[], int i, int dolzKopice) {
		 int oce = a[i];
		 int sin=0;

		 if((2*i+1)<dolzKopice) {
			 sin = 2*i+1;
		 }
		 else return;
		 
		 if((2*i+2)<dolzKopice) {
			 if(a[2*i+1]>a[2*i+2]) sin = 2*i+2;
		 }
		 
		 if(oce>a[sin]) {
			 a[i] = a[sin];
			 a[sin] = oce;
			 //System.out.println("zamenjamo "+a[i]+ " in "+a[sin]);
			 pogrezni2(a, sin, dolzKopice);
		 }
		 
	 }

	 public static void heapSort_up_trace(int[] tabela, int n) {
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
	 
	 public static void heapSort_down_trace(int[] tabela, int n) {
			//zgradimo kopico oz preurejamo tabelo 
				for(int i=(n-1)/2;i>=0;i--) {
					pogrezni2(tabela, i, n);
				}
				
				izpisiKopico(tabela,n);
				
				//urejamo
				while(n>1) {
				tabela[0] = tabela[n-1];
				n--;
				pogrezni2(tabela, 0, n);
				izpisiKopico(tabela,n);
				}
		 }
	 
	 public static void heapSort_count_posrednik(int[] a, int n, String smer) {
		 stPrimerjav=0;
		 stPrirevanj=0;
		 if(smer.equals("up")) {
			 //izpisiKopico(a,n);
				int[] b = heapSort_up_count(a,n);
			//izpisiKopico(b,n);
				int[] c = heapSort_up_count(b,n);
			//izpisiKopico(c,n);
				int[] d = heapSort_down_count(c,n);
			//izpisiKopico(d,n);
			}
			else if(smer.equals("down")) {
				int[] b = heapSort_down_count(a,n);
				int[] c = heapSort_down_count(b,n);
				int[] d = heapSort_up_count(c,n);
		
			}
	 }
	 
	 public static int[] heapSort_up_count(int[] tabela, int n) {
		 
		 for(int i=(n-1)/2;i>=0;i--) {
			countup_pogrezni(tabela, i, n);
		 }	
		
		 int[] tabela1 = new int[n];
		 tabela1[n-1] = tabela[0];
		 int i=n-1;
		 int n1 = n;
			
		 //urejamo
			while(n1>1) {
				tabela[0] = tabela[n1-1];
				stPrirevanj+=3;
				n1--;
				countup_pogrezni(tabela, 0, n1);
				i--;
				tabela1[i]=tabela[0];
			}
			
			System.out.println(stPrimerjav + " " + stPrirevanj);
			stPrimerjav=0;
			stPrirevanj=0;
			return tabela1;
	 }
	 public static int[] heapSort_down_count(int[] tabela, int n) {
		 
		 for(int i=(n-1)/2;i>=0;i--) {
				countdown_pogrezni(tabela, i, n);
			}
			
		 int[] tabela1 = new int[n];
		 tabela1[n-1] = tabela[0];
		 int i=n-1;
		 int n1 = n;
		 
		 //urejamo
			while(n1>1) {
				int tmp = tabela[0];
				tabela[0] = tabela[n1-1];
				tabela[n1-1] = tmp;
				stPrirevanj+=3;
				n1--;
				countdown_pogrezni(tabela, 0, n1);
				i--;
				tabela1[i]=tabela[0];
			}
			System.out.println(stPrimerjav + " " + stPrirevanj);
			stPrimerjav=0;
			stPrirevanj=0;
			return tabela1;
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
		String nacin = sc.next();
		String algoritam = sc.next();
		String smer = sc.next();
		int n = sc.nextInt();
		
		int a[] = new int[n];
		for(int i=0;i<n;i++) {
			a[i] = sc.nextInt();
		}
		
		switch(algoritam) {
		case "hs":
			if(nacin.equals("count")) {
				heapSort_count_posrednik(a,n,smer);
			}
			else if(nacin.equals("trace")) {
				if(smer.equals("up")) heapSort_up_trace(a,n);
				else heapSort_down_trace(a,n);
			}
			break;
		}
		
	}

}

import java.util.Scanner;

public class Izziv3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int a[] = new int[n];
		int b[] = new int[m];
		
		for(int i=0;i<n;i++) {
			a[i] = sc.nextInt();
		}
		for(int i=0;i<m;i++) {
			b[i] = sc.nextInt();
		}
		
		int[] tabela = new int[m+n];
		char[] ab = new char[m+n];
		
		int i=0,j=0;
		int k=0;
		
		while(k<(n+m)) {
			if(i==n && j==m) break;
			else if(i==n) {
				tabela[k] = b[j];
				ab[k] = 'b';
				k++;
				j++;
				continue;
			}
			else if(j==m) {
				tabela[k] = a[i];
				ab[k] = 'a';
				i++;
				k++;
				continue;
			}
					if(a[i]<=b[j] && i<n && j<m) {
						tabela[k] = a[i];
						ab[k] = 'a';
						i++;
						k++;
					}
					else if(i<n && j<m){
						tabela[k] = b[j];
						ab[k] = 'b';
						j++;
						k++;
					}
			
			}
		
		for(int s=0;s<(n+m);s++) {
			System.out.print(ab[s]);
		}
		System.out.println();
		for(int s=0;s<(n+m);s++) {
			System.out.print(tabela[s]+ " ");
		}
	}

}

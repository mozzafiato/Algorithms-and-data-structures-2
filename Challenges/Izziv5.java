
import java.util.*;

public class Izziv5 {
	
	public static int max=Integer.MIN_VALUE;
	
	public static void izpisiPodTabelo(int[] tabela, int n) {
		System.out.print("[");
		for(int i=0;i<n;i++) {
			System.out.print(" " + tabela[i]);
		}
		System.out.println("]");
	}
	public static void izpisiList(List<Integer> list, int l, int r) {
		int maks=Integer.MIN_VALUE;
		int trenmaks=0;
		System.out.print("[");

		for(int i=l;i<=r;i++) {
			if(i==l) System.out.print(list.get(i));
			else System.out.print(", "+list.get(i));
			
			if(trenmaks+list.get(i)>list.get(i))
			trenmaks = trenmaks + list.get(i);
			else trenmaks=list.get(i);
			if(trenmaks>maks) maks = trenmaks;
			
		}
		if(max<maks) max=maks;
		System.out.println("]: " + maks);
	}
	
	public static void deliInVladaj(List<Integer> list, int n,  int l, int r) {
		if(n==1) {
			izpisiList(list,l,r);
		}
		else if(n==2) {
			izpisiList(list,l,l);
			izpisiList(list,r,r);
		}
		else if(n==3) {
			deliInVladaj(list, 2, l, l+1);
			izpisiList(list, l, l+1);
			deliInVladaj(list, 1, r, r);
		}
		else {
				
			if(n%2==0) {
				deliInVladaj(list, n/2,  l, l+(n/2)-1);
				izpisiList(list,l,l+(n/2)-1);
				deliInVladaj(list, n/2, (l+(n/2)), r);
				izpisiList(list,(l+(n/2)),r);
			}
			else {
				deliInVladaj(list, (n/2)+1,  l, l+(n/2));
				izpisiList(list,l,l+(n/2));
				deliInVladaj(list, n/2, r-(n/2)+1, r);
				izpisiList(list,l+(n/2)+1,r);
			}
			//izpisiList(list,l,r);
		}
	}
	
	public static void izpisi(List<Integer> list, int n, int m) {
		System.out.print("[");
		
		for(int i=0;i<n;i++) {
			if(i==0) System.out.print(list.get(i));
			else System.out.print(", "+list.get(i));
		}
		
		if(max>m) m=max;
		System.out.println("]: " + m);
	}

	public static int srednjaVsota (List<Integer> list, int n) {
		int maks = Integer.MIN_VALUE;
		int trenmaks = 0;
		int l,r;
		
		if(n%2==0) l=(n/2)-1;
		else l=(n/2);
		r=l+1;
		
		int zacetno = list.get(l)+list.get(r);
		trenmaks = zacetno;
		maks = trenmaks;
		
		//levo
		for(int i=l-1;i>=0;i--) {
			trenmaks = trenmaks + list.get(i);
			if(trenmaks>maks) maks = trenmaks;
		}
		
		//desno
		trenmaks = maks;

		for(int i=r+1;i<n;i++) {
			trenmaks = trenmaks + list.get(i);
			if(trenmaks>maks) maks = trenmaks;
		}
		
		return maks;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Integer> niz = new ArrayList<Integer>();
		
		int n=0;
		
		while(sc.hasNextInt()) {
			int stevilo = sc.nextInt();
			niz.add(n,stevilo);
			n++;
		}
		
		deliInVladaj(niz, n, 0, n-1);
		int m = srednjaVsota(niz, n);
		izpisi(niz,n,m);
	}

}

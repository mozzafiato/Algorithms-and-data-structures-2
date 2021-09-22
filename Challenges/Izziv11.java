import java.util.*;

public class Izziv11 {
	
	static List<Par> pare = new ArrayList<Par>();
	static List<Par> sortirani = new ArrayList<Par>();
	static int trenPari=0;
	static int stParov;
	static int prostornina;
	
	public void dodajPar(int v, int c) {
		pare.add(new Par(v,c));
	}
	
	public int jeTrebaOdstraniti(Par p, Par l, int i) {
		
			if(l.v==p.v) {
				if(p.c<=l.c) return i;
			}
			if(l.c==p.c) {
				if(p.v<l.v) return i+1;
			}
			if(p.v<l.v && p.c>l.c) return i+1;
			
			return -1;
	
	}
	
	public void izpisiPare() {
		for(int i=0;i<stParov;i++) {
			Par p = pare.get(i);
			System.out.println(p.toString());
		}
	}
	
	public void sortirajPare() {
		sortirani.sort(new MyObjectComparator());
	}
	public void izpisiIteracijo(int iteracija) {
		System.out.print(iteracija + ": ");
		
		for(int i=0;i<trenPari;i++) {
			System.out.print(sortirani.get(i).toString() + " ");
		}
		System.out.println();
	}
	
	public void filtrirajList() {
		Par a = sortirani.get(0);
	
		
		for(int i=1;i<trenPari;i++) {
			Par b = sortirani.get(i);
			int ind = jeTrebaOdstraniti(a,b,i-1);
			if(ind>0) {
				sortirani.remove(ind);
				trenPari--;
				i--;
			}
			if(ind!=i)
			a=b;
		}
		if(trenPari>2) {
		int ind = jeTrebaOdstraniti(sortirani.get(trenPari-2),sortirani.get(trenPari-1), trenPari-2);
		if(ind>=0) {
			sortirani.remove(ind);
			trenPari--;
		}
		}
	}
	
	
	public void algoritam() {
		sortirani.add(new Par(0,0));
		trenPari++;
		int iteracija=0;
		izpisiIteracijo(iteracija);
		int stDodanih=0;
		
		for(int i=0;i<stParov;i++) {
			Par pa = pare.get(i);
			stDodanih=0;
			for(int j=0;j<trenPari;j++) {
				Par pb = sortirani.get(j);
				if((pa.v+pb.v)<=prostornina) {
				sortirani.add(new Par(pa.v+pb.v, pa.c+pb.c));
				stDodanih++;
				}
			}
			trenPari+=stDodanih;
			sortirajPare();
			filtrirajList();
			iteracija++;
			izpisiIteracijo(iteracija);
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int w = sc.nextInt();
		int n = sc.nextInt();
		int[] v = new int[n];
		int[] c = new int[n];
	
		stParov=n;
		prostornina=w;
		Izziv11 obj = new Izziv11();
		
		for(int i=0;i<n;i++) {
			v[i] = sc.nextInt();
			c[i] = sc.nextInt();
			obj.dodajPar(v[i], c[i]);
		}
	
		obj.algoritam();
		
		
	}
	
	 public class Par{
		int v;
		int c;
		
		public Par(int v, int c) {
			this.v=v;
			this.c=c;
		}
		
		public String toString() {
	        return ("(" + this.v +  ", " + this.c + ")");
	    }

	}
	 
	 private class MyObjectComparator implements Comparator<Par> {

		  /**
		   * {@inheritDoc}
		   */
		  @Override
		  public int compare(Par p1, Par p2) {
			  int c=p1.v-p2.v;
			  if(c==0) {
				  return p1.c-p2.c;
			  }
			  else return c;
		  }

		}
}

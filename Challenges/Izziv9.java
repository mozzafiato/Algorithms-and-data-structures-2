import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.*;

public class Izziv9 {
	
	Map<Integer, Vozlisce> graf;
	int stVozlisc = 0;
	Vozlisce izvor;
	Vozlisce ponor;
	static int stPovezav=0;
	static String trenRez;
	static String prevRez="";
	static boolean konec=false;
	PriorityQueue<Integer> q = new PriorityQueue<>();
	
	public Izziv9(int stVozlisc) {
		this.stVozlisc=stVozlisc;
		this.graf = new HashMap<>();
	}
	
	public void dodajVozlisce(int id1, int id2, int c) {
		
		Vozlisce a,b;
		
		if(graf.containsKey(id1)) {
			a = graf.get(id1);
		}
		else {
			a = new Vozlisce(id1);
			graf.put(id1, a);
		}
		if(graf.containsKey(id2)) {
			b = graf.get(id2);
		}
		else {
			b = new Vozlisce(id2);
			graf.put(id2, b);
		}
		
		//pozitivna povezava
		a.sosedi[a.stSosedov]=b;
		a.povezave[a.stSosedov] = new Povezava(a,b,c, 0);
		a.stSosedov++;
		
		//negativna povezava
		b.sosedi[b.stSosedov]=a;
		b.povezave[b.stSosedov] = new Povezava(b,a,c, 1);
		b.stSosedov++;
		stPovezav++;
		
		
		if(a.oznaka==0) {
			izvor = a;
			izvor.pretok=Integer.MAX_VALUE;
		}
		if(b.oznaka==stVozlisc-1) ponor = b;
	}
	
	public int min(int a, int b) {
		if(a<b) return a;
		else return b;
	}
	
	public boolean obstajaZasicenaPot() {
		//postavi vse visited na false
		//potavi vse oznacene na false
		pripraviVozlisca();
		return poisciPot(izvor, ponor, 0);
	}
	
	public void pripraviVozlisca() {
		for(int i=0;i<stVozlisc;i++) {
			Vozlisce v = graf.get(i);
			v.oznaceno=false;
			v.visited=false;
		}
	}
	
	public void simPovezava(Povezava p) {
		Vozlisce a = p.v2;
		for(int i=0;i<a.stSosedov;i++) {
			Povezava p1 = a.povezave[i];
			if(p1.v1.oznaka==a.oznaka && p1.v2.oznaka==p.v1.oznaka) {
				//System.out.println("simetrica: " + p1.v1.oznaka + " " + p1.v2.oznaka);
				p1.x = p.x;
				break;
			}
		}
	}
	
	public boolean poisciPot(Vozlisce izvor, Vozlisce ponor, int stObiskanih) {
		
		if(izvor.oznaka==ponor.oznaka) return true;
		
		izvor.visited=true;
		izvor.oznaceno=true;
		System.out.println("na vrsti: " + izvor.oznaka);
		for(int i=0;i<izvor.stSosedov;i++) {
			//ce ni se obiskano
			Vozlisce j = izvor.sosedi[i];
			Povezava p = izvor.povezave[i];
			
			if(j.oznaceno==false) {
				//ce povezava med njimi ni zasicena
				//pozitivna
				if(p.tip==0 && (p.x < p.c)) {
					j.predhodnik=izvor;
					j.pred=p;
					j.predznak='+';
					//j.visited=true;
					j.pretok=min(izvor.pretok, p.c-p.x);
					stObiskanih++;
					System.out.println("vozlisce: " + j.oznaka + " (" + j.predhodnik.oznaka + j.predznak + ", "+j.pretok + ")");
					j.oznaceno=true;
					q.add(j.oznaka);
				}
				//negativna
				else if(p.tip==1 && (p.x>0)) {
					j.predhodnik=izvor;
					j.predznak='-';
					j.pred=p;
					//j.visited=false;
					j.pretok=min(izvor.pretok, p.x);
					stObiskanih++;
					System.out.println("vozlisce: " + j.oznaka + " (" + j.predhodnik.oznaka + j.predznak + ", "+j.pretok + ")");
					j.oznaceno=true;
					q.add(j.oznaka);
				}
				
			}
			
		}
		/*for(int i=0;i<izvor.stSosedov;i++) {
			System.out.println(izvor.sosedi[i].oznaka);
			if(izvor.sosedi[i].visited==false) {
				Povezava s = izvor.povezave[i];
				if((s.tip==0 && (s.x < s.c))  || (s.tip==1 && (s.x>0)))
				poisciPot(izvor.sosedi[i], ponor, stObiskanih);
			}
		}*/
		while(!q.isEmpty()) {
			Vozlisce v = graf.get(q.remove());
			if(v.visited==false) {
			poisciPot(v,ponor,stObiskanih);
			}
		}
		return true;
	}
	public void zasitiPovezave() {
		int pr = ponor.pretok;
		trenRez="";
		trenRez+=(pr + ": ");
		Vozlisce el = ponor;
		
		while(el.oznaka!=0) {
			trenRez += (el.oznaka + "" +  el.predznak);
			if(el.predznak=='+') trenRez+=("  ");
			else trenRez+=(" ");
			Povezava p = el.pred;
			//System.out.println("Povezava med: " + p.v1.oznaka + " in " + p.v2.oznaka);
			p.x+=pr;
			//System.out.println(p.c + " " + p.x);
			simPovezava(p);
			el=el.predhodnik;
		}
		trenRez+=(izvor.oznaka);
		if(trenRez.equals(prevRez)) {
			konec=true;
			return;
		}
		System.out.println(trenRez);
		prevRez=trenRez;
	}
	
	public void poisciPovezavo(Vozlisce v1, Vozlisce v2) {
		
	}
	
	
	public void izpisiSosede(int a) {
		Vozlisce v = graf.get(a);
		System.out.println("Sosedi vozlisca " + v.oznaka);
		for(int i=0;i<v.stSosedov;i++) {
			System.out.print(v.sosedi[i].oznaka + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Izziv9 obj = new Izziv9(n);
		
		while(sc.hasNextInt()) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			int c = sc.nextInt();
			obj.dodajVozlisce(v1, v2, c);
		}
		/*for(int i=0;i<n;i++) {
			obj.izpisiSosede(i);
		}*/
		//System.out.println(stPovezav);
		
		while(!konec) {
			if(obj.obstajaZasicenaPot()==true)
			obj.zasitiPovezave();
			else break;
		}
		
	}
	
	public class Vozlisce{
		int oznaka;
		Vozlisce[] sosedi;
		Povezava[] povezave;
		int stSosedov;
		boolean visited;
		boolean oznaceno;
		
		Vozlisce predhodnik;
		Povezava pred;
		int pretok;
		char predznak;
		
		public Vozlisce(int oznaka) {
			this.oznaka = oznaka;
			this.sosedi = new Vozlisce[1000];
			this.povezave = new Povezava[1000];
			this.stSosedov=0;
			this.visited=false;
			this.oznaceno=false;
			this.predhodnik=null;
		}
	}
	
	public class Povezava{
		Vozlisce v1,v2;
		int c;
		int x;
		int tip; //0 ce je pozitivna, 1 ce je negativna
		
		public Povezava(Vozlisce v1, Vozlisce v2, int c, int tip) {
			this.v1 = v1;
			this.v2 = v2;
			this.c=c;
			this.x=0;
			this.tip=tip;
			
		}
	}
	
}

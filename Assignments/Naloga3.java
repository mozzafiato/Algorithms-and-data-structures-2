import java.util.*;

public class Naloga3 {
	Map<Integer, Vozlisce> graf;
	Map<Integer, Level> map;
	Map<Integer, Mnozica> set;
	Map<Integer, Mnozica> set1;
	Map<String, Mnozica> najdiMnozico;
	static int stVozlisc = 0;
	static Vozlisce start = null;
	static Vozlisce finish = null;
	static int stLevel=0;
	static int endLevel=0;
	static boolean ok=true;
	static boolean konec=false;
	Queue<Integer> q = new LinkedList<>();
	LinkedList<String> izhod = new LinkedList<String>();
	static int maxizhod=-1;
	ArrayList<Mnozica> mnozice = new ArrayList<Mnozica>();
	int[][] sosedi;
	static int izpis = 1;
	static int k=0;
	
	public Naloga3() {
		this.graf = new HashMap<>();
		this.map = new HashMap<>();
		this.set = new HashMap<>();
		this.set1 = new HashMap<>();
		this.najdiMnozico = new HashMap<>();
		this.sosedi = new int[stVozlisc][stVozlisc];
	}
	
	public void dodaj(int id1, int id2) {
		
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
		
		a.sosedi[a.stSosedov]=b;
		a.stSosedov++;
		
		b.sosedi[b.stSosedov]=a;
		b.stSosedov++;
		
		sosedi[id1][id2]=1;
		sosedi[id2][id1]=1;
		
		if(a.oznaka==0) start=a;
		else if(b.oznaka==0) start=b;
		if(a.oznaka==stVozlisc-1) finish=a;
		else if(b.oznaka==stVozlisc-1) finish=b;
	}
	
	public void dodajVLevel(int a, int l) {
		Level el;
		if(map.containsKey(l)) {
			el = map.get(l);
			
		} else {
			el = new Level(l);
			map.put(l, el);
			stLevel++;
		}
		el.vozlisca[el.stVozlisc] = a;
		el.stVozlisc++;
	}
	
	public void izpisiLevel() {
		for(int i=0;i<=endLevel;i++) {
			Level el = map.get(i);
			
				Arrays.sort(el.vozlisca, 0, el.stVozlisc);
				System.out.print(el.level + " : ");
				for(int j=0;j<el.stVozlisc;j++) {
					System.out.print(el.vozlisca[j] + " ");
				}
				System.out.println();
		}
		if(!konec) System.out.println("OK");
		else System.out.println("NOK");
	}
	
	public void barvaj2c() {
		start.level=0;
		this.dodajVLevel(0,0);
		this.dolociLevels();
		this.barvajGraf();
		this.izpisiLevel();
	}
	
	public boolean dovoljenaBarva(Vozlisce v, int barvaVozlisca) {
		for(int i=0;i<v.stSosedov;i++) {
			if(v.sosedi[i].barva==barvaVozlisca) {
				return false;
			}
		}
		return true;
	}

	public void dolociLevels() {
		q.add(start.oznaka);
		start.visited=true;
		
		while(!q.isEmpty()) {
			Vozlisce v = graf.get(q.remove());
			
			for(int i=0;i<v.stSosedov;i++) {
				if(v.sosedi[i].level==-1) {
					v.sosedi[i].level=v.level+1;
					this.dodajVLevel(v.sosedi[i].oznaka, v.level+1);
				}
				if(v.sosedi[i].visited==false) {
					v.sosedi[i].visited=true;
					q.add(v.sosedi[i].oznaka);
				}	
			}
		}
	}
	
	public void barvajGraf() {
		endLevel = stLevel-1;
		for(int i=0;i<stLevel;i++) {
			Level level = map.get(i);
			
			for(int j=0;j<level.stVozlisc;j++) {
				Vozlisce v = graf.get(level.vozlisca[j]);
				int barva = i%2;
				if(v.obarvano==false) {
					ok = dovoljenaBarva(v,barva);
					if(!ok) konec=true;
					v.obarvano=true;
					v.barva=barva;
				}
			}
			if(konec) {
				endLevel=i;
				break;
			}
		}
	}
	
	public void barvajgr() {
		for(int i=0;i<stVozlisc;i++) {
			Vozlisce v = graf.get(i);
			int barva = najmanjsaBarva(v);
			v.barva=barva;
			System.out.println(v.oznaka + " : " + v.barva);
		}
	}
	
	public int najmanjsaBarva(Vozlisce v) {
		for(int i=0;i<stVozlisc;i++) {
			if(dovoljenaBarva(v,i))
				return i;
		}
		return -1;
	}
	
	public void barvajex() {
		int k=2;
		while(k<=stVozlisc) {
			
			if(maxizhod!=-1) {
				String txt="k = " + k;
				izhod.add(txt);
				if(izhod.size()>maxizhod) izhod.remove();
			}
			else 
				System.out.println("k = " + k);
			
			for(int i=0;i<Math.pow(k, stVozlisc);i++) {
				if(preslikavaZadosca(i,k)) {
					konec=true;
					break;
				}
			}
			if(konec) break;
			resetirajBarve();
			k++;
		}
		if(maxizhod!=-1) {
			//System.out.println(izhod.size() + "*");
			izpisiIzhod();
		}
	}
	
	public void izpisiIzhod() {
		for(int i=0;i<izhod.size();i++) {
			System.out.println(izhod.get(i));
		}
	}
	
	public void resetirajBarve() {
		for(int i=0;i<stVozlisc;i++) {
			Vozlisce v = graf.get(i);
			v.barva=-1;
		}
	}
	
	public boolean preslikavaZadosca(int stevilo, int baza) {
		String txt = Integer.toString(stevilo,baza);
		int st = Integer.parseInt(txt);
	    int[] tabela = new int[stVozlisc];
	    
	    for(int i=stVozlisc-1;i>=0;i--) {
	    	tabela[i]=st%10;
	    	st=st/10;
	    }
	    String text="";
	    for(int i=0;i<stVozlisc;i++) {
	    	if(maxizhod!=-1) {
				text+=tabela[i]+ " ";
			}
			else
				System.out.print(tabela[i] + " ");
	    }
	    
		for(int i=0;i<stVozlisc;i++) {
			Vozlisce v = graf.get(i);
			if(!dovoljenaBarva(v,tabela[i])) {
				
				if(maxizhod==-1) {
					System.out.print("NOK");
					System.out.println();
				}
				else {
					text+="NOK";
					 izhod.add(text);
					 if(izhod.size()>maxizhod) izhod.remove();
				}
				return false;
			}
			else v.barva = tabela[i];
		}
		text+="OK";
		if(maxizhod==-1) System.out.print("OK");
		else {
		    izhod.add(text);
			if(izhod.size()>maxizhod) izhod.remove();
	    }
		return true;
	}
	
	public int[] popolniTabelo(int stevilo, int baza) {
		String txt = Integer.toString(stevilo,baza);
		int st = Integer.parseInt(txt);
	    int[] tabela = new int[stVozlisc];
	    
	    for(int i=stVozlisc-1;i>=0;i--) {
	    	tabela[i]=st%10;
	    	st=st/10;
	    }
	    String text="";
	    for(int i=0;i<stVozlisc;i++) {
	    	if(maxizhod!=-1) {
				text+=tabela[i]+ " ";
			}
			else
				System.out.print(tabela[i] + " ");
	    }
	    if(maxizhod!=-1) {
	    	System.out.println(text + "*");
		    izhod.add(text);
			if(izhod.size()>maxizhod) izhod.remove();
	    }
	    return tabela;
	}
	
	public int min(int a, int b) {
		if(a<b) return a;
		else return b;
	}
	
	public void barvajbt() {
		k=2;
		int[] izpis = new int[stVozlisc];
		int indeks=0;
		boolean konec=false;
		boolean novizacetek=false;
		int c0=0;
		int cn;
		while(!konec) {
			indeks=0;
			cn=k;
			resetirajBarve();
			for(int i=0;i<stVozlisc;i++) {
				Vozlisce v = graf.get(i);
				
				if(novizacetek) {
					c0=v.barva+1;
					v.barva=-1;
					novizacetek=false;
					indeks--;
					//if(i>c0) break;
					if(i==0) break;
					
				} else c0=0;
				
				cn=min(k,i+1);
				for(int c=c0;c<cn;c++) {
					String txt = izpisiTabelo(izpis, indeks);
					if(dovoljenaBarva(v,c)) {
						v.barva=c;
						izpis[indeks] = c;
						indeks++;
						break;
					}
				}
				if(i==stVozlisc-1 && v.barva!=-1) konec=true;
				if(v.barva==-1 && i>0) {
					i=i-2;
					novizacetek=true;
				}
			}
		k++;
		}
		//izpisiIzhod();
	}
	
	public String izpisiTabelo(int[] a, int n) {
		String txt="";
		for(int i=0;i<n;i++) {
			if(maxizhod!=-1) txt+=(a[i] + " ");
			else System.out.print(a[i] + " ");
		}
		return txt;
	}
	
	public void barvajdp() {
		potencnaMnozica(stVozlisc);
		sortirajMnozice();
		for(int i=0;i<Math.pow(2,stVozlisc);i++) {
			Mnozica s = set1.get(i);
			dp(s);
			s.izpis();
		}
	}
	public Mnozica poisciNeodvisnoPodmnozico(Mnozica s) {
		//vrne podmnozico z najmanjsim t
		//najprej razbijemo na potencne podmnozice
		int min=stVozlisc;
		Mnozica rez = null;
		
		int n = s.stElementov;
		for (int i=0;i<Math.pow(2, n);i++) {
			String txt="";
			for( int j=0;j<n;j++) {
				if ((i & (1 << j)) > 0) { 
					txt+=(s.elementi[j]+"");
				}
			}
			Mnozica p = najdiMnozico.get(txt);
			//p.izpis();
			//ce je ta podmnozica neodvisna najdemo S\I
			if(p.neodvisna) {
				Mnozica raz = s.razlika(p);
				if(raz.t<min) {
					min = raz.t;
					rez = raz;
				}
			}
		}
		//System.out.print("Najdli smo razliko: ");
		//rez.izpis();
			//preverimo za vse elemente ce tvorijo neodvisno mnozico
			if(preveriCelo(s)) {
				//System.out.println("je cela neodvisna");
				return set.get(0);
			}
		return rez;
	}
	
	public boolean preveriCelo(Mnozica s) {
		for(int i=0;i<s.stElementov-1;i++) {
			for(int j=i+1;j<s.stElementov;j++) {
				if(sosedi[s.elementi[i]][s.elementi[j]]==1) return false;
			}
		}
		return true;
	}
	
	public Mnozica najdiPodmnozico(Mnozica s) {
		String txt="";
		for(int i=0;i<s.stElementov;i++) {
			txt+=(s.elementi[i]+"");
		}
		System.out.println(txt);
		return najdiMnozico.get(txt);
	}
	
	public void dp(Mnozica s) {
		if(s.stElementov==0) {
			s.t=0;
			return;
		}
		if(s.stElementov==1) {
			s.t=1;
			s.neodvisna=true;
			return;
		}
		//poiscemo minimalno mnozico S\I
		Mnozica minI = poisciNeodvisnoPodmnozico(s);
		s.t = 1 + minI.t;
		if(s.t == 1) s.neodvisna=true;
	}
	
	public void sortirajMnozice() {
		int n = (int) Math.pow(2, stVozlisc);
		int k=1; //trenutno stElementov 
		int indeks=1; //trenutna oznaka na vrsti;
		while(true) {
			if(k==stVozlisc+1) break;
			for(int i=1;i<n;i++) {
				Mnozica s = set.get(i);
				if(s==null) continue;
				if(s.stElementov==k) {
					s.nova_oznaka=indeks;
					indeks++;
				}
			}
			k++;
		}
		set1.put(0, set.get(0));
		for(int i=1;i<n;i++) {
			Mnozica p = set.get(i);
			//p.izpis();
			set1.put(p.nova_oznaka, p);
		}
	}
	
	public void potencnaMnozica(int n) {
		for (int i=0;i<Math.pow(2,n);i++) 
        { 
			String txt="";
			Mnozica s = new Mnozica(i);
            for (int j=0;j<n;j++) 
                if ((i & (1 << j)) > 0) { 
                    s.dodajElement(j);
                    txt+=(j+"");                }
  
            set.put(i, s);
            mnozice.add(s);
            najdiMnozico.put(txt, s);
        }
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String vrstica = sc.nextLine();
		String[] deli = vrstica.split(" ");
		String alg, opcijsko;
		alg = deli[0];
		if(deli.length>1) {
			maxizhod = Integer.parseInt(deli[1]);
		}
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		stVozlisc=n;
		Naloga3 obj = new Naloga3();
		
		for(int i=0;i<m;i++) {
			obj.dodaj(sc.nextInt(),sc.nextInt());
		}
		
		switch(alg) {
			case "2c":
				obj.barvaj2c();
		    break;
			case "gr":
				obj.barvajgr();
			break;
			case "ex":
				obj.barvajex();
			break;
			case "bt":
				obj.barvajbt();
				System.out.println(k-1);
			break;
			case "dp":
				obj.barvajdp();
			break;
			case "competition":
				obj.barvajbt();
				System.out.println(k-1);
		    break;
		}

	}
	
	public class Vozlisce{
		int oznaka;
		boolean obarvano;
		boolean visited;
		boolean visited1;
		int barva;
		int stSosedov;
		Vozlisce[] sosedi;
		int level;
		
		public Vozlisce(int oznaka) {
			this.oznaka=oznaka;
			this.obarvano = false;
			this.visited = false;
			this.visited1 = false;
			this.barva=-1;
			this.stSosedov = 0;
			this.level=-1;
			this.sosedi = new Vozlisce[stVozlisc];
		}
	}
	
	public class Level{
		int level;
		int[] vozlisca;
		int stVozlisc;
		
		public Level(int level) {
			this.level=level;
			this.vozlisca = new int[100];
			this.stVozlisc=0;
		}
	}
	
	public class Mnozica {
		int oznaka;
		int stElementov;
		int[] elementi;
		int t=-1;
		boolean neodvisna=false;
		int nova_oznaka=-1;
		public Mnozica(int oznaka) {
			this.oznaka=oznaka;
			this.stElementov=0;
			this.elementi = new int[stVozlisc];
		}
		
		public void dodajElement(int el) {
			this.elementi[this.stElementov]=el;
			this.stElementov++;
		}
		
		public void izpis() {
			System.out.print("{");
			for(int i=0;i<this.stElementov;i++) {
				if(i>0)
				System.out.print("," + this.elementi[i]);
				else System.out.print(this.elementi[i]);
			}
			System.out.println("} : " + this.t);
		}
		
		public Mnozica razlika(Mnozica s) {
			String txt="";
			boolean contain=false;
			//od this odstrani vse elemente ki so v s
			for(int i=0;i<this.stElementov;i++) {
				contain=false;
				for(int j=0;j<s.stElementov;j++) {
					if(s.elementi[j]==this.elementi[i]) {
						contain=true;
					}
				}
				if(!contain) txt+=(this.elementi[i]+"");
			}
			return najdiMnozico.get(txt);
		}
		
	}
	
}

import java.util.*;


public class Naloga3 {
	Map<Integer, Vozlisce> graf;
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
	static int izpis = 1;
	static int k=0;
	
	public Naloga3() {
		this.graf = new HashMap<>();
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
	
	}
	
	
	public boolean dovoljenaBarva(Vozlisce v, int barvaVozlisca) {
		for(int i=0;i<v.stSosedov;i++) {
			if(v.sosedi[i].barva==barvaVozlisca) {
				return false;
			}
		}
		return true;
	}
	
	public int najmanjsaBarva(Vozlisce v) {
		for(int i=0;i<stVozlisc;i++) {
			if(dovoljenaBarva(v,i))
				return i;
		}
		return -1;
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
		boolean konec=false;
		boolean novizacetek=false;
		int c0=0;
		int cn;
		while(!konec) {
			cn=k;
			resetirajBarve();
			for(int i=0;i<stVozlisc;i++) {
				Vozlisce v = graf.get(i);
				
				if(novizacetek) {
					c0=v.barva+1;
					v.barva=-1;
					novizacetek=false;
					//if(i>c0) break;
					if(i==0) break;
					
				} else c0=0;
				
				cn=min(k,i+1);
				for(int c=c0;c<cn;c++) {
					if(dovoljenaBarva(v,c)) {
						v.barva=c;
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
	

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String alg = sc.nextLine();
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		stVozlisc=n;
		Naloga3 obj = new Naloga3();
		
		for(int i=0;i<m;i++) {
			obj.dodaj(sc.nextInt(),sc.nextInt());
		}
		
		switch(alg) {
			case "bt":
				obj.barvajbt();
				System.out.println(k-1);
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
	
}

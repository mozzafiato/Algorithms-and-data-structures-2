import java.util.Scanner;

public class Naloga2_MnozenjeStevil {

	public static int dolz(int a) {
		int st=0;
		while(a>0) {
			a/=10;
			st++;
		}
		return st;
	}
	
	public static char crka(int n) {
		char s;
		if(n<=9) s=(char)(n+'0');
		else if(n==10) s='a';
		else if(n==11) s='b';
		else if(n==12) s='c';
		else if(n==13) s='d';
		else if(n==14) s= 'e';
		else if(n==15) s= 'f';
		else if(n==16) s='g';
		else if(n==17) s='h';
		else if(n==18) s= 'i';
		else s= 'j';
		return s;
	}
	public static void izpisiStevilo(int[] a, int z, int n, int baza) {
		boolean zacetek=true;
		for(int m=z;m<=n;m++) {
			char s = crka(a[m]);
			if(a[m]!=0) {
				zacetek=false;
			    System.out.print(s);
			}
			if(a[m]==0 && zacetek==false) {
				System.out.print(s);
			}
		}
		if(zacetek==true) System.out.println("0");
		else System.out.println();
	}
	
	public static int countDigits(int[] a) {
		int len=a.length;
		int i=0;
		while(a[i]==0) {
			len--;
			i++;
		}
		return len;
	}
	
	public static void os(int[] pa, int[] pb, int la, int lb, int baza) {
		int[][] zmnozek = new int[lb][la+lb+1];
		
		for(int i=0;i<lb;i++)
			for(int j=0;j<=(la+lb);j++)
				zmnozek[i][j]=0;
		
		int odvec=0;
		int ost=0;
		int zamik=0;
		for(int i=0;i<lb;i++) {
			int p=pb[i];
			int k=la+1+zamik;
			odvec=0;
			for(int j=la-1;j>=0;j--) {
				int produkt = (pa[j]*p)+odvec;
				//System.out.println(odvec);
				//System.out.println("produkt: " + produkt);
				ost=produkt%baza;
				odvec=(produkt-ost)/baza;
				//System.out.println("odvec: " + odvec + " ost: " + ost);
				zmnozek[i][k]=ost;
				//System.out.println("zmnozek: "+ zmnozek[i][k]+ "na indeks: " + k);
				k--;
			}
			zmnozek[i][k]=odvec;
			izpisiStevilo(zmnozek[i],k,la+1+zamik,baza);
			zamik++;
		}
		
		odvec=0;
		int[] rez=new int[la+lb+1];
		for(int j=la+lb;j>0;j--) {
			for(int i=0;i<lb;i++) {
				rez[j]+=(zmnozek[i][j]);
			}
			rez[j]+=odvec;
			//System.out.println("stolpec: " + j + " je: " + rez[j]);
			ost=rez[j]%baza;
			odvec=(rez[j]-ost)/baza;
			rez[j]=ost;
		}
		int len = countDigits(rez);
		//System.out.println(len);
		for(int i=0;i<len;i++) {
			System.out.print("-");
		}
		System.out.println();
	    izpisiStevilo(rez,0, la+lb,baza);
	}
	
	public static int potenca(int x, int p) {
		int potenca=1;
		for(int i=1;i<=p;i++)
			potenca*=x;
		return potenca;
	}
	
	public static int stringToInt(String a) {
		if(a.equals("0")) return 0;
		else if(a.equals("1")) return 1;
		else if(a.equals("2")) return 2;
		else if(a.equals("3")) return 3;
		else if(a.equals("4")) return 4;
		else if(a.equals("5")) return 5;
		else if(a.equals("6")) return 6;
		else if(a.equals("7")) return 7;
		else if(a.equals("8")) return 8;
		else if(a.equals("9")) return 9;
		else if(a.equals("a")) return 10;
		else if(a.equals("b")) return 11;
		else if(a.equals("c")) return 12;
		else if(a.equals("d")) return 13;
		else if(a.equals("e")) return 14;
		else if(a.equals("f")) return 15;
		else if(a.equals("g")) return 16;
		else if(a.equals("h")) return 17;
		else if(a.equals("i")) return 18;
		else if(a.equals("j")) return 19;
		else return 0;
	}
	public static String intToString(int a) {
		if(a==1) return "1";
		else if(a==2) return "2";
		else if(a==3) return "3";
		else if(a==4) return "4";
		else if(a==5) return "5";
		else if(a==6) return "6";
		else if(a==7) return "7";
		else if(a==8) return "8";
		else if(a==9) return "9";
		else if(a==10) return "a";
		else if(a==11) return "b";
		else if(a==12) return "c";
		else if(a==13) return "d";
		else if(a==14) return "e";
		else if(a==15) return "f";
		else if(a==16) return "g";
		else if(a==17) return "h";
		else if(a==18) return "i";
		else if(a==19) return "j";
		else return "0";
	}
	
	public static String odstraniNicle(String a) {
		String[] nov;
		nov=a.split("");
		int n=a.length();
		
		for(int i=0;i<n;i++) {
			if(!nov[i].equals("0")) return a;
			else a=a.substring(1, n);
			n--;
		}
		if(a.equals("")) return "0";
		else return a;
	
	}
	
	public static String mnoziZBazo(String a, int n) {
		for(int i=0;i<n;i++) {
			a+="0";
		}
		return a;
	}
	public static String sestejVBazi(String a, String b, int baza) {
		String rezultat = "";
		String[] x,y;
		int n,m;
		//x je daljse stevilo od y
		if(a.length()>b.length()) {
			m=a.length();
			x=a.split("");
			n=b.length();
			y=b.split("");
		}
		else {
			m=b.length();
			x=b.split("");
			n=a.length();
			y=a.split("");
		}
		int odvec=0,ost=0;
		int j=m-1;
		for(int i=n-1;i>=0;i--) {
			int s1=stringToInt(y[i]);
			int s2=stringToInt(x[j]);
			int vsota=s1+s2+odvec;
			ost = vsota%baza;
			odvec = (vsota-ost)/baza;
			rezultat = intToString(ost) + rezultat;
			//System.out.println("rez " + rezultat);
			j--;
		}
		/*if(odvec!=0) {
			rezultat = intToString(odvec) + rezultat;
		}*/
		for(int i=m-n-1;i>=0;i--) {
			int s1=stringToInt(x[i]);
			s1+=odvec;
			ost=s1%baza;
			rezultat = intToString(ost) + rezultat;
			odvec=(s1-ost)/baza;
		}
		if(odvec!=0) {
			//System.out.println("odvec: " + odvec);
			rezultat = intToString(odvec) + rezultat;
		}
		
		return rezultat;
	}
	
	public static String odstejVBazi(String a, String b, int baza) {
		int n=a.length();
		int m=b.length();
		String[] x = a.split("");
		String[] y = b.split("");
		String rezultat="";
		int j=n-1;
		int dug=0,razlika;
		
		for(int i=m-1;i>=0;i--) {
			int s1=stringToInt(x[j])-dug;
			int s2=stringToInt(y[i]);
			if(s1<s2) {
				 s1+=baza;
				 dug=1;
			}
			else dug=0;
			
			razlika=s1-s2;
			rezultat = intToString(razlika) + rezultat;
			j--;
		}
		
		for(int i=n-m-1;i>=0;i--) {
			int s1=stringToInt(x[i]);
			s1-=dug;
			if(s1<0) {
				s1+=baza;
				dug=1;
			}
			else {
				dug=0;
			}
			rezultat = intToString(s1) + rezultat;
		}
		if(dug!=0) {
			int t = stringToInt(x[0]);
			rezultat=rezultat.substring(1, rezultat.length());
			rezultat = intToString(t-1) + rezultat;
		}
		return odstraniNicle(rezultat);
	}
	
	public static String napolni(String[] d, int zac, int konec) {
		String a="";
		if(zac==-1) return "0";
		for(int i=zac;i<konec;i++) {
			a+=d[i];
		}
		return a;
	}
	
	public static String dv(String[] da, String db[], int la, int lb, int baza) {
		if(la==1 || lb==1) {
			if(da[0].equals("0") || db[0].equals("0")) {
				System.out.println("0");
				return "0";
			}
			String[] x;
			String y;
			int dim;
			if(la==1) {
				x=db;
				y=da[0];
				dim=lb;
			}
			else {
				x=da;
				y=db[0];
				dim=la;
			}
			//pomnozimo stevilo x s y
			int p = stringToInt(y);
			int odvec=0,ost=0;
			String produkt="";
			for(int i=dim-1;i>=0;i--) {
				int stevilo=stringToInt(x[i]);
				int zmnozek = (stevilo*p)+odvec;
				ost = zmnozek%baza;
				odvec = (zmnozek-ost)/baza;
				produkt = intToString(ost) + produkt;
			}
			if(odvec!=0) produkt = intToString(odvec) + produkt;
			System.out.println(odstraniNicle(produkt));
			return produkt;
		}
		
		int n;
		if(la>lb) n=la;
		else n=lb;
		int k;
		if(n%2==1) k=(n+1)/2;
		else k=n/2;
		
		String a0="", a1="", b0="", b1="";
		
		if(k>=la) {
			a0=napolni(da,0, la);
			b0=napolni(db,lb-k,lb);
			a1=napolni(da,-1,0);
			b1=napolni(db,0,lb-k);
		}
		else if(k>=lb) {
			a0=napolni(da,la-k, la);
			b0=napolni(db,0,lb);
			a1=napolni(da,0,la-k);
			b1=napolni(db,-1,0);
		}
		else {
			a0=napolni(da,la-k, la);
			b0=napolni(db,lb-k,lb);
			a1=napolni(da,0,la-k);
			b1=napolni(db,0,lb-k);
		}
		
		a0=odstraniNicle(a0);
		b0=odstraniNicle(b0);
		a1=odstraniNicle(a1);
		b1=odstraniNicle(b1);
		
		System.out.println(a0 + " " + b0);
		String a0b0 = dv(a0.split(""),b0.split(""),a0.length(),b0.length(),baza);
		
		System.out.println(a0 + " " + b1);
		String a0b1 = dv(a0.split(""),b1.split(""),a0.length(),b1.length(),baza);
		
		System.out.println(a1 + " " + b0);
		String a1b0 = dv(a1.split(""),b0.split(""),a1.length(),b0.length(),baza);
		
		System.out.println(a1 + " " + b1);
		String a1b1 = dv(a1.split(""),b1.split(""),a1.length(),b1.length(),baza);
		
		//racunamo produkt po formulo: ab=a1b1Bn+(a0b1+a1b0)Bn2+a0b0)
		String ab="";
		
		//System.out.println("n=" + n + " k=" + k);
		String q=mnoziZBazo(a1b1,2*k);
		String p=mnoziZBazo(sestejVBazi(a0b1,a1b0,baza),k);
		ab=sestejVBazi(q,p,baza);
		ab=sestejVBazi(ab,a0b0,baza);
		System.out.println(odstraniNicle(ab));
		return ab;
	}
	
	public static String ka(String[] da, String db[], int la, int lb, int baza) {
		if(la==1 || lb==1) {
			if(da[0].equals("0") || db[0].equals("0")) {
				System.out.println("0");
				return "0";
			}
			String[] x;
			String y;
			int dim;
			if(la==1) {
				x=db;
				y=da[0];
				dim=lb;
			}
			else {
				x=da;
				y=db[0];
				dim=la;
			}
			//pomnozimo stevilo x s y
			int p = stringToInt(y);
			int odvec=0,ost=0;
			String produkt="";
			for(int i=dim-1;i>=0;i--) {
				int stevilo=stringToInt(x[i]);
				int zmnozek = (stevilo*p)+odvec;
				ost = zmnozek%baza;
				odvec = (zmnozek-ost)/baza;
				produkt = intToString(ost) + produkt;
			}
			if(odvec!=0) produkt = intToString(odvec) + produkt;
			System.out.println(odstraniNicle(produkt));
			return odstraniNicle(produkt);
		}
		
		int n;
		if(la>lb) n=la;
		else n=lb;
		int k;
		if(n%2==1) k=(n+1)/2;
		else k=n/2;
		
		String a0="", a1="", b0="", b1="";
		if(k>=la) {
			a0=napolni(da,0, la);
			b0=napolni(db,lb-k,lb);
			a1=napolni(da,-1,0);
			b1=napolni(db,0,lb-k);
		}
		else if(k>=lb) {
			a0=napolni(da,la-k, la);
			b0=napolni(db,0,lb);
			a1=napolni(da,0,la-k);
			b1=napolni(db,-1,0);
		}
		else {
			a0=napolni(da,la-k, la);
			b0=napolni(db,lb-k,lb);
			a1=napolni(da,0,la-k);
			b1=napolni(db,0,lb-k);
		}
		
		a0=odstraniNicle(a0);
		b0=odstraniNicle(b0);
		a1=odstraniNicle(a1);
		b1=odstraniNicle(b1);
		
		System.out.println(a0 + " " + b0);
		String a0b0 = ka(a0.split(""),b0.split(""),a0.length(),b0.length(),baza);
		
		System.out.println(a1 + " " + b1);
		String a1b1 = ka(a1.split(""),b1.split(""),a1.length(),b1.length(),baza);
		
		String a1plusa0=sestejVBazi(a1,a0,baza);
		String b1plusb0=sestejVBazi(b1,b0,baza);
		
		System.out.println(a1plusa0 + " " + b1plusb0);
		String pr = ka(a1plusa0.split(""), b1plusb0.split(""), a1plusa0.length(), b1plusb0.length(),baza);
		
		String ab="";
		
		String q = mnoziZBazo(a1b1,2*k);
		String oklepaji = odstejVBazi(pr, a1b1, baza);
		oklepaji = odstejVBazi(oklepaji, a0b0, baza);
		oklepaji = mnoziZBazo(oklepaji,k);
		ab = sestejVBazi(q, oklepaji,baza);
		ab = sestejVBazi(ab, a0b0,baza);
		System.out.println(odstraniNicle(ab));
		return ab;
		
		
	}
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		String alg;
		int baza,a,b,n;
		alg = sc.next();
		baza = sc.nextInt();
		
		switch(alg) {
			case "os":
				a = sc.nextInt();
				b=sc.nextInt();
				
				int la=dolz(a);
				int lb=dolz(b);
				
				if(la>lb) n=la;
				else n=lb;

				int[] pa = new int[n];
				int[] pb = new int[n];
				
				for(int i=la-1;i>=0;i--) {
					pa[i]=a%10;
					a/=10;
				}
				for(int i=lb-1;i>=0;i--) {
					pb[i]=b%10;
					b/=10;
				}
				os(pa,pb,la,lb,baza);
			break;
			case "dv":
				String sa = sc.next();
				String sb = sc.next();
				System.out.println(sa + " " + sb);
				dv(sa.split(""),sb.split(""),sa.length(),sb.length(),baza);
				
		    break;
			case "ka":
				String ca= sc.next();
				String  cb= sc.next();
				System.out.println(ca + " " + cb);
				ka(ca.split(""),cb.split(""),ca.length(),cb.length(),baza);
			break;
			
		}	
	}

}

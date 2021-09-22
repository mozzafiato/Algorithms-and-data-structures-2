import java.util.Scanner;
	
/*class Complex{
	double re;
	double im;

    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    public String toString() {
    	double tRe = (double)Math.round(re * 100000) / 100000;
    	double tIm = (double)Math.round(im * 100000) / 100000;
        if (tIm == 0) return tRe + "";
        if (tRe == 0) return tIm + "i";
        if (tIm <  0) return tRe + "-" + (-tIm) + "i";
        return tRe + "+" + tIm + "i";
    }

	public Complex conj() {
		return new Complex(re, -im);
	}

    // sestevanje
    public Complex plus(Complex b) {
        Complex a = this;
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    // odstevanje
    public Complex minus(Complex b) {
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag);
    }

    // mnozenje z drugim kompleksnim stevilo
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }

    // mnozenje z realnim stevilom
    public Complex times(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    // reciprocna vrednost kompleksnega stevila
    public Complex reciprocal() {
        double scale = re*re + im*im;
        return new Complex(re / scale, -im / scale);
    }

    // deljenje
    public Complex divides(Complex b) {
        Complex a = this;
        return a.times(b.reciprocal());
    }

    // e^this
    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }


    //potenca komplesnega stevila
    public Complex pow(int k) {

    	Complex c = new Complex(1,0);
    	for (int i = 0; i <k ; i++) {
			c = c.times(this);
		}
    	return c;
    }
    
    public void izpisi(int n) {
		for(int i=0;i<n;i++) {
			System.out.print(this.toString() + " ");
		}
		System.out.println();
	}
    
   
}*/
public class Izziv8 {

	 
	
	public static Complex[] rekfft(Complex[] p, int n) {
		if(n==1) {
			Complex[] r = new Complex[2];
			r[0]=p[0];
			r[1]=p[0];
			System.out.println(r[0].toString() + " " + r[1].toString());
			return r;
		}
		
		Complex[] ps = new Complex[n];
		Complex[] pl= new Complex[n];
		int j=0;
		
		for(int i=0;i<n;i++) {
			ps[i] = new Complex(0,0);
			pl[i] = new Complex(0,0);
		}
		
		for(int i=0;i<n-1;i+=2) {
			ps[j]=p[i];
			pl[j]=p[i+1];
			j++;
		}
		/*for(int i=0;i<=(n/2)-1;i++) {
			System.out.println(i + ": " + ps[i].toString() + " * " + pl[i].toString());
		}*/
		
		Complex[] ps1 = rekfft(ps, n/2);
		Complex[] pl1 = rekfft(pl, n/2);
		
		/*for(int i=0;i<n/2;i++) {
			System.out.print(ps1[i].toString() + " ");
			System.out.println(" *" + pl1[i].toString() + " ");
		}*/
		
		Complex wk = new Complex(1,0);
		Complex a = new Complex(0,(2*Math.PI)/(2*n));
		Complex w = a.exp();
		//System.out.println("w="+w.toString());
		
		Complex[] y = new Complex[2*n];
		for(int i=0;i<2*n;i++) {
			y[i] = new Complex(0,0);
		}
		
		for(int k=0;k<n;k++) {
			//System.out.println(ps[k].toString() + " + " + wk + "*" + pl[k].toString());
			Complex yn = ps1[k].plus(wk.times(pl1[k]));
			//System.out.println("yn="+yn.toString());
			y[k] = yn;
			Complex yn1= ps1[k].minus(wk.times(pl1[k]));
			//System.out.println("yn1="+yn1.toString());
			y[k+n] = yn1;
			wk = wk.times(w);
			//System.out.println(wk);
			//System.out.println(y[k].re + " " + y[k].im);
		}
		for(int i=0;i<2*n;i++) {
			System.out.print(y[i].toString() + " ");
		}
		System.out.println();
		
		return y;
	}
	
	public static Complex[] rekfftInverse(Complex[] p, int n) {
		if(n==1) {
			return p;
		}
		
		Complex[] ps = new Complex[n/2];
		Complex[] pl= new Complex[n/2];
		int j=0;
		
		for(int i=0;i<n/2;i++) {
			ps[i] = new Complex(0,0);
			pl[i] = new Complex(0,0);
		}
		
		for(int i=0;i<n-1;i+=2) {
			ps[j]=p[i];
			pl[j]=p[i+1];
			j++;
		}
		/*for(int i=0;i<=(n/2)-1;i++) {
			System.out.println(i + ": " + ps[i].toString() + " * " + pl[i].toString());
		}*/
		
		Complex[] ps1 = rekfftInverse(ps, n/2);
		Complex[] pl1 = rekfftInverse(pl, n/2);
		
		Complex wk = new Complex(1,0);
		Complex a = new Complex(0,(2*Math.PI)/n);
		Complex w = a.exp();
		w = w.conj();
		
		Complex[] y = new Complex[n];
		for(int i=0;i<n;i++) {
			y[i] = new Complex(0,0);
		}
		
		for(int k=0;k<(n/2);k++) {
			Complex yn = ps1[k].plus(wk.times(pl1[k]));
			y[k] = yn;
			Complex yn1= ps1[k].minus(wk.times(pl1[k]));
			y[k+(n/2)] = yn1;
			wk = wk.times(w);
		}
		for(int i=0;i<n;i++) {
			System.out.print(y[i].toString() + " ");
		}
		System.out.println();
		
		return y;
	}
	
	
	public static int nextNum(int n) {
		while(true) {
			n++;
			if(potenca2(n)) return n;
		}
	}
	
	public static boolean potenca2(int n) 
	{ 
	    if(n==0) 
	    return false; 
	  
	    else return (int)(Math.ceil((Math.log(n) / Math.log(2)))) ==  
	       (int)(Math.floor(((Math.log(n) / Math.log(2))))); 
	} 
	
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		int m=n;
		if(!potenca2(n)) m=nextNum(n);
		
		Complex[] p1 = new Complex[m];
		Complex[] p2 = new Complex[m];
		
		for(int i=0;i<n;i++) {
			p1[i]= new Complex(sc.nextDouble(),0);
		}
		for(int i=0;i<n;i++) {
			p2[i]=new Complex(sc.nextDouble(), 0);
		}
		
		for(int i=n;i<m;i++) {
			p1[i]= new Complex(0,0);
			p2[i]=new Complex(0,0);
		}
		
		
		Complex[] a =rekfft(p1,m);
		Complex[] b =rekfft(p2,m);
		
		System.out.println("***");
		for(int i=0;i<2*m;i++) {
			System.out.print(a[i].toString() + " ");
		}
		System.out.println("***");
		for(int i=0;i<2*m;i++) {
			System.out.print(b[i].toString() + " ");
		}
		System.out.println("***");
		
	
		for(int i=0;i<2*m;i++) {
			a[i]=a[i].times(b[i]);
			System.out.print(a[i].toString() + " ");
		}
		System.out.println();
		Complex[] c = rekfftInverse(a,m);
		
	}

}

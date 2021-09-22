import java.util.Scanner;


class Matrix {


	private int[][] m;

	public int n; //only square matrices

	public Matrix(int n){

		this.n = n;
		m = new int[n][n];

	}


    //set value at i,j
	public void setV(int i, int j, int val){

		m[i][j] = val;

	}


    // get value at index i,j
	public int v(int i, int j){

		return m[i][j];

	}


    // return a square submatrix from this
	public Matrix getSubmatrix(int startRow, int startCol, int dim){

		Matrix subM = new Matrix(dim);

		for (int i = 0; i<dim ; i++ )

			for (int j=0;j<dim ; j++ )

				subM.setV(i,j, m[startRow+i][startCol+j]);



		return subM;

	}


    // write this matrix as a submatrix from b (useful for the result of multiplication)
	public void putSubmatrix(int startRow, int startCol, Matrix b){

		for (int i = 0; i<b.n ; i++ )

			for (int j=0;j<b.n ; j++ )

				setV(startRow+i,startCol+j, b.v(i,j));

	}


    // matrix addition
	public Matrix sum(Matrix b){

		Matrix c = new Matrix(n);

		for(int i = 0; i< n;i++){

			for(int j = 0; j<n;j++){

				c.setV(i, j, m[i][j]+b.v(i, j));

			}

		}

		return c;

	}





    // matrix subtraction
	public Matrix sub(Matrix b){

		Matrix c = new Matrix(n);

		for(int i = 0; i< n;i++){

			for(int j = 0; j<n;j++){

				c.setV(i, j, m[i][j]-b.v(i, j));

			}

		}

		return c;

	}
	
	public int vsotaElementov() {
		int vsota = 0;
		Matrix a = this;
		int n = this.n;
		
		for(int i = 0; i< n;i++){
			for(int j = 0; j<n;j++){
				vsota+=a.v(i, j);
	        }
		}
		return vsota;
	}



	//simple multiplication
	public Matrix mult(Matrix b){
        Matrix a = this;
        int n = a.n;
        Matrix c = new Matrix(n);
        
        for(int i=0;i<n;i++) {
        	for(int j=0;j<n;j++) {
        		int sum=0;
        		for(int k=0;k<n;k++) {
        			sum+=a.v(i, k)*b.v(k,j);
        		}
        		c.setV(i, j, sum);
        	}
        }
        return c;
	
	}


    // Strassen multiplication
	public Matrix multStrassen(Matrix b, int cutoff){
        Matrix a = this;
        int n = a.n;
        Matrix a11 = a.getSubmatrix(0, 0, n/2);
        Matrix a12 = a.getSubmatrix(0, n/2, n/2);
        Matrix a21 = a.getSubmatrix(n/2, 0, n/2);
        Matrix a22 = a.getSubmatrix(n/2, n/2, n/2);
        
        Matrix b11 = b.getSubmatrix(0, 0, n/2);
        Matrix b12 = b.getSubmatrix(0, n/2, n/2);
        Matrix b21 = b.getSubmatrix(n/2, 0, n/2);
        Matrix b22 = b.getSubmatrix(n/2, n/2, n/2);
        
        Matrix m1,m2,m3,m4,m5,m6,m7;
        if(n/2==cutoff) {
        	m1 = (a11.sum(a22)).mult(b11.sum(b22));
        	m2 = (a21.sum(a22)).mult(b11);
        	m3 = (a11).mult(b12.sub(b22));
        	m4 = (a22).mult(b21.sub(b11));
        	m5 = (a11.sum(a12)).mult(b22);
        	m6 = (a21.sub(a11)).mult(b11.sum(b12));
        	m7 = (a12.sub(a22)).mult(b21.sum(b22));
        }
        else {
        	m1 = (a11.sum(a22)).multStrassen(b11.sum(b22),cutoff);
            m2 = (a21.sum(a22)).multStrassen(b11,cutoff);
            m3 = (a11).multStrassen(b12.sub(b22),cutoff);
            m4 = (a22).multStrassen(b21.sub(b11),cutoff);
            m5 = (a11.sum(a12)).multStrassen(b22,cutoff);
            m6 = (a21.sub(a11)).multStrassen(b11.sum(b12),cutoff);
            m7 = (a12.sub(a22)).multStrassen(b21.sum(b22),cutoff);
       }
        
        Matrix c11 = m1.sum(m4);
        c11 = c11.sub(m5);
        c11 = c11.sum(m7);
        
        Matrix c12 = m3.sum(m5);
        Matrix c21 = m2.sum(m4);
        
        Matrix c22 = m1.sub(m2);
        c22 = c22.sum(m3);
        c22 = c22.sum(m6);
        
        System.out.println("m1: " + m1.vsotaElementov());
        System.out.println("m2: " + m2.vsotaElementov());
        System.out.println("m3: " + m3.vsotaElementov());
        System.out.println("m4: " + m4.vsotaElementov());
        System.out.println("m5: " + m5.vsotaElementov());
        System.out.println("m6: " + m6.vsotaElementov());
        System.out.println("m7: " + m7.vsotaElementov());
        
        Matrix rezultat = new Matrix(n);
        
        rezultat.putSubmatrix(0, 0, c11);
        rezultat.putSubmatrix(0, n/2, c12);
        rezultat.putSubmatrix(n/2, 0, c21);
        rezultat.putSubmatrix(n/2, n/2, c22);
        
        return rezultat;
        
	}


}




public class Izziv6{



	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int r = sc.nextInt();
		
		Matrix a = new Matrix(n);
		Matrix b = new Matrix(n);
		
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++){
				int v = sc.nextInt();
				a.setV(i,j,v);
			}
		
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++){
				int v = sc.nextInt();
				b.setV(i,j,v);
			}
		Matrix c = a.multStrassen(b, r);
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++){
				System.out.print(c.v(i, j)+ " ");
			}
			System.out.println();
		}


	}



}

import java.util.Scanner;

import sun.security.util.Length;
public class SXTron {
	private int low;
	private int high;
	private int middle;
	public static int[] a = new int[100]; 
	static Scanner nhap = new Scanner(System.in) ; 
	
	public static void Xuat(int a[],int n) {
		for(int i=0;i<n;i++) {
			System.out.print(a[i]+"  ");
		}
	}
	
	// Gop hai mang con a[low...middle] và a[middle+1..high]
	public static void merge(int a[],int low,int middle,int high) {
		int i,j,k;
		int n1 = middle-low+1;
		int n2 = high-middle;
		
			//Tao cac mang tam	
			int[] L = new int[n1]; 
			int[] R = new int[n2];
			
			//Copy du lieu sang cac mang tam
			for(i=0;i<n1;i++) {
				L[i] = a[low + i];
			}
			for(j=0;j<n2;j++) {
				R[j] = a[middle + 1 + j];
			}
			//Gop hai mang vua roi vao mang a
			i=0;  //Khoi ttao bien chay cua mang con dau tien
			j=0;  //Khoi tao bien chay cua mang con thu hai
			k=low;  //Khoi tao bien ban dau cua mang luu ket qua
			
			while(i<n1 && j<n2) {
				if(L[i] <= R[j])  //neu vi tri trai <= phai thi ptu thu k trong mang a = ptu thu i trong mang trai
				{
					a[k] = L[i];
					i++;
				}
				else {
					a[k] = R[j];
					j++;
				}
				k++;
			}
			// Copy cac phan tu con lai cua mang L vào a neu co 
		    while (i < n1)
		    {
		        a[k] = L[i];
		        i++;
		        k++;
		    }
		 
		    // Copy cac phan tu con lai cua mang R vào a neu co 
		    while (j < n2)
		    {
		        a[k] = R[j];
		        j++;
		        k++;
		    }
		}
	 // low va high la hai chi so chi ptu nho va lon trong cac mang con
	public static void mergeSort(int a[], int low, int high)
	{
		    if (low < high)
		    {
		        // Tuong tu (low+high)/2, 
		        int middle = low+(high-low)/2;
		 
		        // Goi ham de quy cua mang
		        mergeSort(a, low, middle);
		        mergeSort(a, middle + 1, high);
		 
		        merge(a, low, middle, high);
		    }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]= {5,7,2,3,9};
		    
		    System.out.println("Truoc khi sap xep");
		    Xuat(a, a.length);
		    mergeSort(a, 0, a.length - 1);
		    System.out.println("\nSau khi sap xep");
		    Xuat(a, a.length);
	
	}
}

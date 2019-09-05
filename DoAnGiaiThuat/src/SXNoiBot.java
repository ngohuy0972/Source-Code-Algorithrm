
public class SXNoiBot {
	public static int[] a = new int[100]; 
	public static void Xuat(int a[],int n) {
		for(int i=0;i<n;i++) {
			System.out.print(a[i]+"  ");
		}
	}
	public static void bubbleSort(int a[],int n) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n-1;j++) {
				if(a[j] > a[j+1]) {
					int temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}			
			}
		}
		//Xuat(a, n);
}	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[] = {5,8,4,3,7,9};
		System.out.println("Mang truoc khi sap xep");
		Xuat(a,a.length);
		System.out.println("\nMang sau khi sap xep");
		bubbleSort(a,a.length);	
		Xuat(a, a.length);
	}

}

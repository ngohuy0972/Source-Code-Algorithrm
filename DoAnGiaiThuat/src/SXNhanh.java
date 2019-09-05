
public class SXNhanh {
	private int low;
	private int high;
	public static int[] a = new int[100];
	
	public static void Xuat(int a[],int n) {
		for(int i=0;i<n;i++) {
			System.out.print(a[i]+"  ");
		}
	}
	public static int partition(int a[], int low, int high) {
        int pivot = a[high];				//chọn phần tử chốt là phần tử đầu hoặc cuối danh sách
        int i = low - 1;					//biến chạy 
        for(int j = low; j < high; j++) {	//duyệt phần tử từ thấp đến cao
            if(a[j] <= pivot ) {			//nếu ptu nào tại vị trí thứ j(bên phải) nhỏ hơn ptu chốt
            								//thì tiến hành đổi chỗ cho vị trí thứ i(bên trái)
                i++;						//tăng i trước để i bắt đầu từ vị trí thứ 0
                int temp = a[i];	
                a[i] = a[j];
                a[j] = temp;
            }
        }
        int temp = a[i + 1];				//dịch chuyển i thêm một đơn vị rồi chèn ptu chốt vào đó
        a[i + 1] = a[high];
        a[high] = temp;
        return i+1;							//trả về phần tử chốt
    }
  
    public static void quickSort(int a[], int low, int high) {
        if(low < high) {
            int p = partition(a,low,high);
            quickSort(a,low,p-1);
            quickSort(a,p+1,high);
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]= {15,12,8,6,9,7,4};
	    
	    System.out.println("Truoc khi sap xep");
	    Xuat(a, a.length);
	    quickSort(a, 0, a.length - 1);
	    System.out.println("\nSau khi sap xep");
	    Xuat(a, a.length);
	}

}

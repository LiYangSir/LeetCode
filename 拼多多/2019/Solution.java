import java.util.*;
public class Solution{
    private static void compute(int[] data){
        for (int item : data) {
            int left = 1;
            int right = item;
            int count = 0;
            while(left <= right){
                int mid = (left + right)/2;
                count++;
                right = right % 2 == 0? mid : mid - 1;
            }
            System.out.println(count);
        }
    }
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        int size = reader.nextInt();
        int[] data = new int[size];
        for(int i = 0; i < size; i++){
            data[i] = reader.nextInt();
        }
        compute(data);
        reader.close();
    }
}

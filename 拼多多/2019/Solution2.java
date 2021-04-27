import java.util.Scanner;

public class Solution2 {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int size = reader.nextInt();
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = reader.nextInt();
        }
        compute(data);
        reader.close();

    }

    private static void compute(int[] data) {
        for (int item : data) {
            int max = (item + 1) / 2;
            int min = (item + 1) % 2 == 0 ? 0 : 1;
            System.out.println(min + " " + max);
        }
    }
}

import java.util.Scanner;

public class Solution3 {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int size = reader.nextInt();
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = reader.nextInt();
        }
        System.out.println(compute(data, 0));
        reader.close();

    }

    private static int[] compute(int[] data, int index) {
        if (index >= data.length - 1) {
            return new int[]{data[index], (1 + data[index]) * data[index] / 2};
        }
        int[] compute = compute(data, index + 1);
        if (data[index] > data[index + 1]) {
            return new int[]{data[index] * compute[0], compute[1] * data[index] + (data[index + 1] - 1) * data[index + 1] / 2 + data[index + 1] * (data[index] - data[index + 1])};
        }
        return new int[]{data[index] * compute[0], compute[1] * data[index] + (data[index] - 1) * data[index] / 2};
    }
}

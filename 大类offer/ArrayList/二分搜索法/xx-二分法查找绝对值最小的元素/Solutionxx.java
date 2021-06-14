public class Solutionxx {
    public int findAbsElement(int[] data) {
        int left = 0;
        int right = data.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (data[mid] == 0) return 0;
            if (data[mid] < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return data[left];
    }

    public static void main(String[] args) {
        Solutionxx solutionxx = new Solutionxx();
        System.out.println(solutionxx.findAbsElement(new int[]{0, 1, 2, 3}));
    }
}

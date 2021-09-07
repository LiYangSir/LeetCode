import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution45 {
    public String minNumber(int[] nums) {
        return Arrays.stream(nums).mapToObj(String::valueOf).sorted(this::sorted).collect(Collectors.joining());
    }

    private int sorted(String o1, String o2) {
        if (o1.equals(o2)) {
            return 0;
        }
        int count = Math.max(o1.length(), o2.length());
        for (int i = 0; i < count * 2; i++) {
            char c1 = o1.charAt(i % o1.length());
            char c2 = o2.charAt(i % o2.length());
            if (c1 == c2) continue;
            return c1 - c2;
        }
        return 0;
    }

    public static void main(String[] args) {
        Solution45 solution45 = new Solution45();
        System.out.println(solution45.minNumber(new int[]{3,43,48,94,85,33,64,32,63,66}));
    }
}

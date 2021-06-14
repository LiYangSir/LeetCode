public class Solution733 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        floodFill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private void floodFill(int[][] image, int sr, int sc, int oriColor, int newColor) {
        // 终止条件
        if (!inArea(image, sr, sc)) return;
        if (image[sr][sc] != oriColor) return;
        if (image[sr][sc] == -1) return;

        image[sr][sc] = -1;
        floodFill(image, sr, sc - 1, oriColor, newColor);
        floodFill(image, sr, sc + 1, oriColor, newColor);
        floodFill(image, sr - 1, sc, oriColor, newColor);
        floodFill(image, sr + 1, sc, oriColor, newColor);
        image[sr][sc] = newColor;
    }

    private boolean inArea(int[][] image, int sr, int sc) {
        return sr >= 0 && sr < image.length && sc >= 0 && sc < image[0].length;
    }
}

public class Solution29 {
    public int divide(int dividend, int divisor) {
        int res = 0;
        if(dividend >  divisor){
            while(dividend - divisor >= 0){
                dividend -= divisor;
                res++;
            }
        }else{
            while((dividend < 0 && dividend + divisor <=0) || (dividend > 0 && dividend + divisor >=0)){
                dividend += divisor;
                res--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution29 solution29 = new Solution29();
        System.out.println(solution29.divide(-10, -3));
    }
}
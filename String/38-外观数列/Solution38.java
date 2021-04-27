class Solution38 {
    public String countAndSay(int n) {
        if(n <= 1){
            return "1";
        }
        String data = countAndSay(n - 1);
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < data.length(); i++){
            int count = 1;
            char c = data.charAt(i);
            while(i + 1 < data.length() && c == data.charAt(i + 1)){
                count++;
                i++;
            }
            res.append(count).append(c);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Solution38 solution38 = new Solution38();
        solution38.countAndSay(4);
    }
}
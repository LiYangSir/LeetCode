package s05_替换空格;

/**
 * @author LiYangSir
 * @date 2021/6/14
 */
public class Solution05 {
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            StringBuilder builder = cur == ' ' ? sb.append("%20") : sb.append(cur);
        }
        return sb.toString();
    }
}

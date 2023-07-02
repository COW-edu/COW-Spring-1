// https://github.com/woowacourse-precourse/java-onboarding/blob/main/docs/PROBLEM4.md

class Solution4 {
    public static void main(String[] args) {
        String inp = args[0];
        String res = "";

        for (int i = 0; i < inp.length(); i++) {
            int buff = (int)inp.charAt(i);
            if (buff >= 65 && buff <= 90) { // 대문자인 경우
                res += (char)(90 - (buff - 65));
            } else if (buff >= 97 && buff <= 122) { // 소문자인 경우
                res += (char)(122 - (buff - 97));
            } else { // 알파벳이 아닌 경우
                res += (char)buff;
            }
        }

        System.out.println(res);
    }
}


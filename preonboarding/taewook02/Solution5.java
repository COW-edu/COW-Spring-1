// https://github.com/woowacourse-precourse/java-onboarding/blob/main/docs/PROBLEM5.md

import java.util.Arrays;

class Solution5 {
    public static void main(String[] args) {
        int inp = Integer.parseInt(args[0]);
        int[] mList = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 1};
        int[] res = new int[9];

        for (int i = 0; i < mList.length; i++) {
            int m = mList[i];
            res[i] = inp/m;
            inp %= m;
        }

        System.out.println(Arrays.toString(res));
    }
}

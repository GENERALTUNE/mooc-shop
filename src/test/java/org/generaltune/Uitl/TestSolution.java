package org.generaltune.Uitl;

/**
 * Created by zhumin on 2017/6/26.
 */
public class TestSolution {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        if (sequence.length == 1)
            return true;

        int endNum = sequence[sequence.length - 1];
        int i = 0;

        for (; i < sequence.length - 1; i++) {
            if (sequence[i] > endNum) {
                break;
            }
        }
        int index = i;
        for(; index < sequence.length - 1; index++) {
            if(sequence[index] < endNum) {
                return false;
            }
        }
        int[] smaller = null;
        int[] bigger = null;
        smaller = new int[i];
        bigger = new int[sequence.length- i - 1];
        int in = 0;

        for(int j = 0; j < sequence.length - 1; j ++) {
            if(j < i) {
                smaller[j] = sequence[j];
            } else {
                bigger[in ++] = sequence[j];
            }

        }
        if(i == 0) {
            return VerifySquenceOfBST(bigger);
        } else if(i == sequence.length - 1) {
            return VerifySquenceOfBST(smaller);
        } else {
            return VerifySquenceOfBST(bigger) && VerifySquenceOfBST(smaller);
        }
    }
}

package google;

/**
 * Created by billjyc on 2016/9/27.
 * 给一个长为m高为n的矩阵，和一个sentence，比如“Jack and Jim went to hill”，
 * 把这个sentence放满矩阵里一共需要多少个sentence，规定sentence与sentence之间必须有一个空格，
 * 如果最后没法放下一个sentence，在尽量长的substring的情况下找到空格处split，
 * 前一半放上一行，下一半放下一行，其中行与行之间没有空格要求。

 */
public class NumOfSentence {
    public int numOfSentence(int n, int m, String sentence) {
        if( n == 0 || sentence == null
                || sentence.length() == 0) {
            return 0;
        }
        //the index of the previous space from current index
        int[] spaceIndexes = new int[sentence.length()];
        int lastSpace = -1;
        for(int i = 0; i < sentence.length(); i++) {
            if(sentence.charAt(i) == ' ') {
                lastSpace = i;
            } else {
                spaceIndexes[i] = lastSpace;
            }
        }

        int len = sentence.length();
        int ans = 0;
        int wordPtr = 0;
        for(int i = 0; i < n; i++) {
            int rowPtr = 0;
            while(rowPtr < m) {
                if(len - wordPtr <= m - rowPtr) {
                    rowPtr += len - wordPtr + 1;
                    wordPtr = 0;
                    ans++;
                } else {
                    int spaceIndex = spaceIndexes[wordPtr + m - rowPtr];
                    wordPtr = spaceIndex + 1;
                    break;
                }
            }
        }


        return ans;
    }

    public static void main(String[] args) {
        NumOfSentence numOfSentence = new NumOfSentence();
        String sentence = "Jack and Jim went to a hill";
        int rst1 = numOfSentence.numOfSentence(5, 10, sentence);
        System.out.println(rst1);
        int rst2 = numOfSentence.numOfSentence(5, 40, sentence);
        System.out.println(rst2);
        int rst3 = numOfSentence.numOfSentence(1, 10, sentence);
        System.out.println(rst3);

    }
}

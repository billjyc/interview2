package google;

import java.util.Stack;

/**
 * Created by billjyc on 2016/10/20.
 */
public class FilePathLength {
    /**
     * return longest image path to root(return 11, /dir1/dir12)
     * @param filePath
     * @return
     */
    public int longestImagePathToRoot(String filePath) {
        if(filePath == null || filePath.length() == 0) {
            return 0;
        }
        String[] files = filePath.split("\n");
        Stack<FilePath> s = new Stack<>();
        int currentLen = 0;
        int max = 0;
        for(int i = 0; i < files.length; i++) {
            int lastSpace = files[i].lastIndexOf(" ");
            FilePath filePath1 = new FilePath(lastSpace + 1, files[i].substring(lastSpace + 1));
            System.out.println("name: " + filePath1.path + " space num: " + filePath1.preSpace);
            while(!s.isEmpty() && s.peek().preSpace >= filePath1.preSpace) {
                FilePath tmp = s.pop();
                currentLen -= (tmp.path.length() + 1); //remove "/" + path
            }
            if(s.isEmpty()) {
                currentLen = 0;
            }

            if(!s.isEmpty()) {
                currentLen += 1; //add an "/" before the path
            }
            s.push(filePath1);
            currentLen += (filePath1.path.length());
            System.out.println("current length: " + currentLen);

            if(isImage(filePath1.path)) {
                max = Math.max(max, Math.max(0, currentLen - filePath1.path.length()));
            }
        }
        return max;
    }

    private boolean isFile(String path) {
        return path.contains(".");
    }

    private boolean isImage(String path) {
        return path.endsWith("jpg") || path.endsWith("jpeg") || path.endsWith("gif");
    }

    public static void main(String[] args) {
        FilePathLength fpl = new FilePathLength();
        String tc1 = "dir1\n dir11\n dir12\n  picture.jpeg\n  dir121\n file1.txt\ndir2\n file2.gif";
        String tc2 = "picture.jpeg";
        System.out.println(fpl.longestImagePathToRoot(tc2));
    }
}

class FilePath {
    int preSpace;
    String path;

    public FilePath(int preSpace, String path) {
        this.preSpace = preSpace;
        this.path = path;
    }
}

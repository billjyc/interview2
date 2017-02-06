package other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by billjyc on 2016/11/2.
 */
public class FindRightInterval {
    public int[] findRightInterval(Interval[] intervals) {
        int[] res = new int[intervals.length];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < intervals.length; i++) {
            map.put(intervals[i].start, i);
        }
        Comparator<Interval> cmp = (i1, i2) -> (i1.start - i2.start);
        Arrays.sort(intervals, cmp);
        for(int i = 0; i < intervals.length; i++) {
            int target = intervals[i].end;
            if(intervals[intervals.length - 1].start < target) {
                res[map.get(intervals[i].start)] = -1;
                continue;
            }
            int left = 0, right = intervals.length - 1;
            while(left < right) {
                int mid = left + (right - left) / 2;
                if(intervals[mid].start == target) {
                    res[map.get(intervals[i].start)] = map.get(intervals[mid].start);
                    break;
                } else if(intervals[mid].start < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }

            }
            res[map.get(intervals[i].start)] = map.get(intervals[left].start);
//            if(intervals[left].start < target && intervals[right].start >= target) {
//                res[i] = map.get(intervals[right].start);
//            } else {
//                res[i] = map.get(intervals[left].start);
//            }
        }
        return res;
    }

    public static void main(String[] args) {
        Interval i1 = new Interval(3,4);
        Interval i2 = new Interval(2,3);
        Interval i3 = new Interval(1,2);
        Interval[] intervals = new Interval[]{i1, i2,i3};
        FindRightInterval fri = new FindRightInterval();
        fri.findRightInterval(intervals);
    }
}

class Interval {
    int start;
    int end;
    Interval() {start = 0; end = 0;}
    Interval(int s, int e) {
        start = s;
        end = e;
    }
}

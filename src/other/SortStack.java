package other;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by billjyc on 2016/11/2.
 */
public class SortStack {
    public void sortStack(Stack<Integer> stack) {
        if(stack.isEmpty()) {
            return;
        }
        int temp = stack.pop();
        sortStack(stack);
        sortedInsert(stack, temp);
    }

    public void sortedInsert(Stack<Integer> stack, int temp) {
        if(stack.isEmpty() || temp > stack.peek()) {
            stack.push(temp);
        } else {
            int ele = stack.pop();
            sortedInsert(stack, temp);
            stack.push(ele);
        }
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        SortStack sortStack = new SortStack();
        List<Integer> list = new ArrayList<>();
        list.add(30);
        list.add(-5);
        list.add(18);
        list.add(14);
        list.add(-3);
        s.addAll(list);
        sortStack.sortStack(s);
        System.out.println("done");
    }
}

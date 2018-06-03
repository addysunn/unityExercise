package csfundamentals;

import java.util.Stack;

public class Queue {

    /**
     * Complexity Analysis
     * Time complexity - enqueue - O(1) per operation, dequeue - Amortized O(1) per operation
     * Space complexity - O(n) We need additional memory to store the queue elements
    **/

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    /** Initialize your data structure here. */
    public Queue() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    /** Push element x to the back of queue. */
    public void enQueue(int x) {
        stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int deQueue() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        if(!stack2.isEmpty()){
            return stack2.pop();
        }
        throw new NullPointerException();
    }

    /** Get the front element. */
    public int peek() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        if(!stack2.isEmpty()){
            return stack2.peek();
        }
        throw new NullPointerException();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public int size() {
        return stack1.size() + stack2.size();
    }

}
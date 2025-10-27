import java.util.*;
public class ProperParenthesis {


    private static boolean balanced(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        // makes a map
        Map<Character,Character> m = Map.of(')', '(', ']', '[', '}', '{');
        for (char c : s.toCharArray()) {
            if (c=='('||c=='['||c=='{') stack.push(c);
            else if (c==')'||c==']'||c=='}') {
                // if there's nothing left it's missing its pair or if it doesn't equal it's wrong
                if (stack.isEmpty() || stack.pop() != m.get(c)) return false;
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(balanced(sc.nextLine()));
    }
}
package postfix;

import stack.LinkedListStack;
import stack.Underflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Postfix {

    public static void main(String[] args) throws IOException {
        Postfix p = new Postfix();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while (true) {
            str = reader.readLine();
            if (str.equals("stop"))
                break;

            System.out.println(p.evaluate(str));
        };
    }

    public String infixToPostfix(String infix) {
        //Define a stack
        LinkedListStack<Object> stack = new LinkedListStack<>();
        String output = "";

        //Go through each character in the string
        String[] tokens = infix.split(" ");

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            boolean b = token.equals("+") || token.equals("-")
                    || token.equals("*") || token.equals("/") ||
                    token.equals("(") || token.equals(")");
            //If it is between 0 to 9, append it to output string.
            if (!b) {
                output += token;
            } //If it is left brace push to stack
            if (token.equals("(")) {
                stack.push(token);
            }//If it is operator *+-/ then
            if (b) {
                //If the stack is empty push it to the stack
                if (stack.isEmpty()) {
                    stack.push(token);
                }
                //If the stack is not empty then start a loop:
                else {
                    try {
                        String top = String.valueOf(stack.top());
                        //If the top of the stack is operator with higher or equal precedence
                        boolean brackets = top.equals("(") || top.equals(")");
                        if (precedenceLevel(top) >= precedenceLevel(token)) {
                            //Then pop and append to output string
                            if(brackets){
                                stack.pop();
                            }
                            else{
                                output = output + stack.pop();
                                if (!stack.isEmpty()) {
                                    output = output + stack.pop();
                                }
                            }
                        } //Else break
                        //Push to the stack
                        if(!token.equals(")")){
                            stack.push(token);
                        }
                    } catch (Underflow underflow) {
                        underflow.printStackTrace();
                    }
                }
            }
            //If it is right brace then
            if (token.equals(")")) {
                //While stack not empty and top not equal to left brace
                try {
                    String top = String.valueOf(stack.top());
                    while (!stack.isEmpty() && !top.equals("(")) {
                        //Pop from stack and append to output string
                        output += stack.pop();
                        //Finally pop out the le= brace
                    }
                    if (top.equals("(")) {
                        stack.pop();
                    }
                } catch (Underflow underflow) {
                    underflow.printStackTrace();
                }
            }
            //If there is any input in the stack pop and append to the output string
            // System.out.println(stack.toString());
        }
        while (!stack.isEmpty()) {
            try {
                output += stack.pop();
            } catch (Underflow underflow) {
                underflow.printStackTrace();
            }
        }
        output = addBraces(output);
        return output;
    }

    public double evaluate(String postfix) {
        // TODO Auto-generated method stub
        LinkedListStack<Object> stack = new LinkedListStack<>();
        double ergebnis = -1.0;
        double rhs;
        double lhs;

        String[] tokens = postfix.split(" ");

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (!(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")
                    || token.equals("(") || token.equals(")") || token.equals("^"))) {
                stack.push(Double.parseDouble(token));
            } else {
                try {
                    rhs = (double) stack.pop();
                    lhs = (double) stack.pop();
                    if (token.equals("+")) {
                        ergebnis = lhs + rhs;
                    } else if (token.equals("-")) {
                        ergebnis = lhs - rhs;
                    } else if (token.equals("*")) {
                        ergebnis = lhs * rhs;
                    } else if (token.equals("/")) {
                        ergebnis = lhs / rhs;
                    } else if (token.equals("^")) {
                        ergebnis = Math.pow(lhs, rhs);
                    }
                    stack.push(ergebnis);
                } catch (Underflow underflow) {
                    underflow.printStackTrace();
                }
            }
        }
        return ergebnis;
    }

    public int precedenceLevel(String s) {
        switch (s) {
            case "+":
            case "-":
                return 0;
            case "*":
            case "/":
                return 1;
            case "^":
                return 2;
            case "(":
            case ")":
                return 3;
            default:
                throw new IllegalArgumentException("Operator unknown: " + s);
        }
    }

    public String addBraces(String output) {
        String localOutput = "";
        String[] out = output.split("");
        for (int i = 0; i <= out.length - 2; i++) {
            localOutput += out[i] + " ";
        }
        localOutput += out[out.length - 1];
        return localOutput;
    }
}



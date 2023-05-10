package opn.rpn;

import stack.Stack;
import stack.StackOnArray;

import java.util.ArrayList;

public class OPNExecutor {
    private final ArrayList<OPNElement> opnElements;

    private final String separator;

    public OPNExecutor(String separator) {
        this.separator = separator;
        this.opnElements = new ArrayList<>();
    }

    public void getFromInfixNotation(String infixNotationString) {
        if (infixNotationString.isEmpty()) {
            throw new RuntimeException("Нет строки");
        }

        Stack<OPNElement> stack = new StackOnArray<>();

        String[] opn = infixNotationString.split(separator);
        for (String s : opn) {
            switch (s) {
                case "(" -> stack.push(new OPNOpenBracket());
                case ")" -> {
                    while (!stack.isEmpty() && !stack.peek().getClass().equals(OPNOpenBracket.class)) {
                        opnElements.add(stack.pop());
                    }
                    stack.pop();
                }
                default -> {
                    OPNElement opnElement = getElementFromString(s);
                    if (opnElement.getClass().equals(OPNNumber.class)) {
                        opnElements.add(opnElement);
                    } else {
                        while (!stack.isEmpty()
                                && getPrecedence(opnElement) <= getPrecedence(stack.peek())
                                && hasLeftAssociativity(opnElement)) {
                            opnElements.add(stack.pop());
                        }
                        stack.push(opnElement);
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peek().getClass().equals(OPNOpenBracket.class)) {
                throw new RuntimeException("Строка не валидна");
            }
            opnElements.add(stack.pop());
        }
    }

    public void getFromOPNString(String opnString) {
        if (opnString.isEmpty()) {
            throw new RuntimeException("Нет строки");
        }

        String[] opn = opnString.split(separator);
        for (String s : opn) {
            opnElements.add(getElementFromString(s));
        }
    }

    private OPNElement getElementFromString(String s) {
        return switch (s) {
            case "+" -> new OPNPlus();
            case "-" -> new OPNMinus();
            case "/" -> new OPNDivision();
            case "*" -> new OPNMultiplication();
            case "^" -> new OPNPow();
            default -> new OPNNumber(Double.parseDouble(s));
        };
    }

    private int getPrecedence(OPNElement element) {
        OPNOperatorPrecedence opnOperatorPrecedence = element.getClass().getAnnotation(OPNOperatorPrecedence.class);
        if (opnOperatorPrecedence != null) {
            return opnOperatorPrecedence.precedence();
        }  else {
            return -1;
        }
    }

    private boolean hasLeftAssociativity(OPNElement element) {
        OPNLeftAssociativity opnLeftAssociativity = element.getClass().getAnnotation(OPNLeftAssociativity.class);
        return opnLeftAssociativity != null;
    }

    public Double count() {
        if (opnElements.isEmpty()) {
            throw new RuntimeException("Нет строки для вычисления ОПЗ");
        }
        Stack<Double> stack = new StackOnArray<>();
        for(OPNElement element : opnElements) {
            OPNOperation annotation = element.getClass().getAnnotation(OPNOperation.class);
            if (annotation != null) {
                switch (annotation.numbersOfOperation()) {
                    case 1 -> stack.push(element.execute(stack.pop()));
                    case 2 -> stack.push(element.execute(stack.pop(), stack.pop()));
                    default -> stack.push(element.execute());
                }
            }
        }
        return stack.pop();
    }

    public String getOPNString() {
        StringBuilder sb = new StringBuilder();
        for (OPNElement element: opnElements) {
            sb.append(element.toString()).append(" ");
        }
        return sb.toString();
    }

}

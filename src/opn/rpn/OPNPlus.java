package opn.rpn;

@OPNOperation(numbersOfOperation = 2)
@OPNLeftAssociativity
@OPNOperatorPrecedence(precedence = 1)
public class OPNPlus implements OPNElement {
    @Override
    public Double execute(Double... args) {
        int operators = this.getClass().getAnnotation(OPNOperation.class).numbersOfOperation();
        if (args.length != 2) {
            throw new RuntimeException("Для сложения должно быть " + operators + " числа");
        }
        return args[0] + args[1];
    }


    @Override
    public String toString() {
        return "+";
    }
}

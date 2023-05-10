package opn.rpn;

@OPNOperation(numbersOfOperation = 2)
@OPNOperatorPrecedence(precedence = 2)
@OPNLeftAssociativity
public class OPNMultiplication implements OPNElement {
    @Override
    public Double execute(Double... args) {
        int operators = this.getClass().getAnnotation(OPNOperation.class).numbersOfOperation();
        if (args.length != 2) {
            throw new RuntimeException("Для умножения должно быть " + operators + " числа");
        }
        return args[0] * args[1];
    }

    @Override
    public String toString() {
        return "*";
    }

}

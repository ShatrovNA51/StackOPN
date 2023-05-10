package opn.rpn;

@OPNOperation(numbersOfOperation = 2)
@OPNOperatorPrecedence(precedence = 3)
@OPNRightAssociativity
public class OPNPow implements OPNElement {
    @Override
    public Double execute(Double... args) {
        int operators = this.getClass().getAnnotation(OPNOperation.class).numbersOfOperation();
        if (args.length != 2) {
            throw new RuntimeException("Для степени должно быть " + operators + " числа");
        }
        return Math.pow(args[1], args[0]);
    }

    @Override
    public String toString() {
        return "^";
    }

}

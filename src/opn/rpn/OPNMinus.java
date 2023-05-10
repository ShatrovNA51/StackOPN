package opn.rpn;

@OPNOperation(numbersOfOperation = 2)
@OPNOperatorPrecedence(precedence = 1)
@OPNLeftAssociativity
public class OPNMinus implements OPNElement {
    @Override
    public Double execute(Double... args) {
        int operators = this.getClass().getAnnotation(OPNOperation.class).numbersOfOperation();
        if (args.length != operators) {
            throw new RuntimeException("Для вычитания должно быть" + operators + " числа");
        }
        return args[1] - args[0];
    }

    @Override
    public String toString() {
        return "-";
    }
}

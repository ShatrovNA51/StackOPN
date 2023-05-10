package opn.rpn;

@OPNOperation(numbersOfOperation = 2)
@OPNOperatorPrecedence(precedence = 2)
@OPNLeftAssociativity
public class OPNDivision implements OPNElement{
    @Override
    public Double execute(Double... args) {
        int operators = this.getClass().getAnnotation(OPNOperation.class).numbersOfOperation();
        if (args.length != 2) {
            throw new RuntimeException("Для деления должно быть " + operators + " числа");
        }
        return args[1] / args[0];
    }

    @Override
    public String toString() {
        return "/";
    }
}

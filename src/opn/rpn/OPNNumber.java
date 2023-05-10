package opn.rpn;

@OPNOperation
@OPNOperatorPrecedence
public class OPNNumber implements OPNElement {

    Double number;

    public OPNNumber(Double number) {
        this.number = number;
    }

    @Override
    public Double execute(Double... args) {
        return number;
    }

    @Override
    public String toString() {
        return number.toString();
    }

}

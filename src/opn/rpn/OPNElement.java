package opn.rpn;

@OPNOperation
public interface OPNElement{

    default Double execute(Double... args) {
        return null;
    }

}

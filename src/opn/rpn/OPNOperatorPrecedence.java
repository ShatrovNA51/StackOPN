package opn.rpn;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OPNOperatorPrecedence {
    int precedence() default -1;
}

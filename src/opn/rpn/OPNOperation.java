package opn.rpn;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OPNOperation {
    int numbersOfOperation() default 0;
}

package com.github.cccy0.mall.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Zhai
 * 2020/9/17 22:20
 */

public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Integer> {
    /**
     * 预期值
     */
    private String[] values;

    /**
     * 初始化注解校验器
     * @param constraintAnnotation
     */
    @Override
    public void initialize(FlagValidator constraintAnnotation) {
        this.values = constraintAnnotation.value();
    }

    /**
     *
     * @param value 需要校验的值
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = false;
        for (String item : values) {
            if (item.equals(String.valueOf(value))) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}

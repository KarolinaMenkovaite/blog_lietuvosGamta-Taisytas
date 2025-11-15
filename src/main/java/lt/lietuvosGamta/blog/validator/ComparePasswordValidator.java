package lt.lietuvosGamta.blog.validator;

import lt.lietuvosGamta.blog.validator.annotation.FieldsComparator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class ComparePasswordValidator implements ConstraintValidator<FieldsComparator, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldsComparator constraintAnnotation)
    {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext)
    {
        Object first = getValue(o, firstFieldName);
        Object second = getValue(o, secondFieldName);

        return first != null && first.equals(second);
    }

    private Object getValue(Object o, String fieldName) {
        try {
            Class<?> objectClass = o.getClass();
            Field field = objectClass.getDeclaredField(fieldName);
            field.setAccessible(true);

            return field.get(o);
        } catch(Exception e){
            System.out.println("error:" + e);
        }

        return null;
    }
}
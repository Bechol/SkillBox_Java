package ToDoList.service.impl;

import ToDoList.models.ToDo;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Map;

public class ToDoServiceUtils {

    static String getField(String columnNameFromReq) {
        for (Field field : ToDo.class.getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);
            String columnNameFromDB = column.name();
            if (columnNameFromReq.equals(columnNameFromDB)) {
                return field.getName();
            }
        }
        return null;
    }

    static Specification<ToDo> getSpecifications(Map<String, Object> filters) {
        Specification<ToDo> specification = null;
        int step = 0;
        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String needName = ToDoServiceUtils.getField(key);
            if (needName == null) continue;
            if (step == 0) {
                specification = Specification.where(setSpecification(needName, value.toString()));
            } else {
                specification.and(setSpecification(needName, value.toString()));
            }
            step++;
        }
        return specification;
    }

    private static Specification<ToDo> setSpecification(String columnName, String expression) {
        return (root, query, builder) -> builder.like(root.get(columnName), contains(expression));
    }

    private static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }
}

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Class to work with
 */
class ComparatorInspector {

    /**
     * Return Type variable that corresponds to the type parameterizing Comparator.   
     *
     * @param clazz {@link Class} object, should be non null
     * @return {@link Type} object or null if Comparable does not have type parameter
     */
    public static <T extends Comparable<?>> Type getComparatorType(Class<T> clazz) {
        Type type = null;
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            if (genericInterface instanceof ParameterizedType) {
                if (((ParameterizedType) genericInterface).getRawType().equals(Comparable.class)) {
                    type = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
                }
            }
        }
        return type;
    }

}
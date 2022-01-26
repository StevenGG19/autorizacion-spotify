import java.lang.reflect.Method;
import java.util.Objects;

class MethodFinder {

    public static String findMethod(String methodName, String[] classNames) throws ClassNotFoundException {
        String nombreClase = "";
        for (String clases : classNames) {
            Method[] metodos = Class.forName(clases).getMethods();
            for (Method valor : metodos) {
                if (methodName.equals(valor.getName())) {
                    nombreClase = clases;
                    break;
                }
            }
            if (!Objects.equals(nombreClase, "")) {
                break;
            }
        }
        return nombreClase;
    }
}
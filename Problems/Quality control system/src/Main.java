import java.lang.reflect.Field;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

class QualityControl {

    public static<T> boolean check(List<T> boxes) {
        boolean valor = true;

        for (Object box : boxes) {
            if (!(box instanceof Box)) {
                valor = false;
            }else {
                if (!(((Box) box).get() instanceof Bakery)) {
                    valor = false;
                }
            }
        }
        return valor;
    }

}

// Don't change the code below
class Bakery {
}

class Cake extends Bakery {
}

class Pie extends Bakery {
}

class Tart extends Bakery {
}

class Paper {
}

class Box<T> {

    private T item;

    public void put(T item) {
        this.item = item;
    }

    public T get() {
        return this.item;
    }
}
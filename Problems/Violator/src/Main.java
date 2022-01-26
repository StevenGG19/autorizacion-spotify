import java.util.ArrayList;

/**
 * Class to work with
 */
class Violator {

    public static List<Box<? extends Bakery>> defraud() {
        List<Box<? extends Bakery>> box = new ArrayList<>();
        Box papel = new Box();
        papel.put(new Paper());
        box.add(papel);
        return box;
    }

}
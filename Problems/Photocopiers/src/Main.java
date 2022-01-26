/**
 * Class to work with
 */
class Multiplicator {

    public static <T extends Copy<T>> Folder<T>[] multiply(Folder<T> folder, int arraySize) {
        Folder[] fol = new Folder[arraySize];
        for (int i = 0; i < arraySize; i++) {
            fol[i] = new Folder();
            fol[i].put(folder.get().copy());
        }
        return fol;
    }

}

// Don't change the code below
interface Copy<T> {
    T copy();
}

class Folder<T> {

    private T item;

    public void put(T item) {
        this.item = item;
    }

    public T get() {
        return this.item;
    }
}
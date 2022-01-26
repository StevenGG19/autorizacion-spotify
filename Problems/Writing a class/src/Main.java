class Shelf<T extends Book> {
    private T element;

    public T getElement() {
        return this.element;
    }

    public void setElement(T element) {
        this.element = element;
    }
}
class Box<T extends Animal> {
    T animal;

    public void add(T objeto) {
        this.animal = objeto;
    }
}

// Don't change the code below
class Animal {
}
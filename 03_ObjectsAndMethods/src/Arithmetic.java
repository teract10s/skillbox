public class Arithmetic {
    private int value1;
    private int value2;

    public Arithmetic(int value1, int value2){
        this.value1 = value1;
        this.value2 = value2;
    }

    public int sum(){
        return value1 + value2;
    }

    public int product(){
        return value1 * value2;
    }

    public int maximum(){
        return value1 > value2 ? value1 : value2;
    }

    public int minimum(){
        return value1 < value2 ? value1 : value2;
    }
}

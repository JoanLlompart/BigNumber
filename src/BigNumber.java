class BigNumber {

    String valor;
    // Constructor 1
    public BigNumber(String s) {
        this.valor = s;
    }
    // Constructor 2
    public BigNumber(BigNumber b) { }
    // Suma
    BigNumber add(BigNumber other) {
        return null;
    }
    // Resta
    BigNumber sub(BigNumber other) {
        return null;
    }
    // Multiplica
    BigNumber mult(BigNumber other) {
        return null;

    }
    // Divideix
    BigNumber div(BigNumber other) {
        return null;
    }
    // Arrel quadrada
    BigNumber sqrt() {
        return null;

    }
    // Potència
    BigNumber power(int n) {
        return null;

    }
    // Factorial
    BigNumber factorial() {
        return null;
    }
    // MCD. Torna el Màxim comú divisor
    BigNumber mcd(BigNumber other) {
        return null;

    }
    // Compara dos BigNumber. Torna 0 si són iguals, -1
// si el primer és menor i torna 1 si el segon és menor
    public int compareTo(BigNumber other) {
        //https://youtu.be/kE_B0E3zh_k
        return -1;
    }
    // Torna un String representant el número
    public String toString() {
        return null;
    }
    @Override
    // Mira si dos objectes BigNumber són iguals
    public boolean equals(Object other) { //Object es una clase especial java que esta per demunt de altres objectes
        BigNumber b = (BigNumber) other;
        if (b.valor.equals(this.valor)) return true;
        return false;
    }
}
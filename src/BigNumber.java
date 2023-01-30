import java.util.Objects;

class BigNumber {
    public static void main(String[] args) {
        //Se ha de entregar sense un main.
        BigNumber b1 = new BigNumber("56");
        BigNumber b2 = new BigNumber("000000000000000000000000000000056");
        System.out.println(b1.equals(b2));
        // b1 > b2 (1)
        // b1 == b2 (0)
        // b1 <b2 (- 1)
        System.out.println(b1.compareTo(b2));
    }

    String valor;

    // Constructor 1
    public BigNumber(String s) {
        /*
        while (b.valor.length() > 1 && b.valor.charAt(0) == '0') {
            b.valor = b.valor.substring(1);
        }
 */
        for (int i = 0; i < s.length(); i++) {
            // b.valor=b.valor.replaceFirst("^0+", "");
            char c = s.charAt(i);
            if (c != '0') {
                s = s.substring(i);
                break;
            }
        }
        this.valor = s;
    }

    // Constructor 2
    public BigNumber(BigNumber b) {

    }

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

        String b1 = this.valor;
        String b2 = other.valor;

        //Integer number = Integer.parseInt(b.valor);

        if (b1.length() > b2.length()) {
            return 1;

        } else if (b1.length() < b2.length()) {
            return  -1;
        } else if (b1.length() == b2.length()) {
            for (int i = 0; i < b1.length(); i++) {
                int c1 = Integer.parseInt(String.valueOf(b1.charAt(i)));
                int c2 = Integer.parseInt(String.valueOf(b2.charAt(i)));
                if (c1 > c2) {
                    return 1;
                } else if (c1 < c2) {
                    return  -1;
                }

            }
        }
        return 0;
    }
    // Torna un String representant el número
    public String toString() {
        return this.valor;
    }
    @Override
    // Mira si dos objectes BigNumber són iguals
    public boolean equals(Object other) { //Object es una clase especial java que esta per demunt de altres objectes
        BigNumber b = (BigNumber) other;
        if (b.valor.equals(this.valor)) return true;
        return false;
    }
}
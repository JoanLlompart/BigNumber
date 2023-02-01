import java.util.Arrays;
import java.util.Objects;

class BigNumber {
    public static void main(String[] args) {
        //Se ha de entregar sense un main.
        BigNumber b1 = new BigNumber("52");
        BigNumber b2 = new BigNumber("34");
        BigNumber resultat = b1.add(b2);

        System.out.println(b1.equals(b2));
        // b1 > b2 (1)
        // b1 == b2 (0)
        // b1 <b2 (- 1)
        System.out.println(b1.compareTo(b2));
        System.out.println(resultat);


    }

    String valor;

    // Constructor 1
    public BigNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
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
        /*
        public class Main {
  public static String sumar(String num1, String num2) {
    StringBuilder result = new StringBuilder();
    int i = num1.length() - 1;
    int j = num2.length() - 1;
    int carry = 0;

    while (i >= 0 || j >= 0) {
      int a = i >= 0 ? num1.charAt(i) - '0' : 0;
      int b = j >= 0 ? num2.charAt(j) - '0' : 0;
      int sum = a + b + carry;
      result.append(sum % 10);
      carry = sum / 10;
      i--;
      j--;
    }

    if (carry != 0) {
      result.append(carry);
    }

    return result.reverse().toString();
  }

  public static void main(String[] args) {
    String num1 = "12345678901234567890";
    String num2 = "98765432109876543210";
    String resultado = sumar(num1, num2);
    System.out.println("Resultado: " + resultado);
  }
}
         */
        String b1 = this.valor;
        String b2 = other.valor;
        int residuo = 0;
        int mesGran = Math.max(b1.length(), b2.length()) ;
        int resultat[] = new int[mesGran];
        String resultatFinal = "";

        for (int i = 0; i < mesGran; i++) {
            //Cream dues variables c1 i c2 que les pasarem a char i despres a int per poder comprobar numero per numero.
            int c1 = Integer.parseInt(String.valueOf(b1.charAt(i)));
            int c2 = Integer.parseInt(String.valueOf(b2.charAt(i)));

            int suma = c1+c2+residuo;
            residuo = suma /10;
            resultat[i] = suma %10;; // Sumam el valor de suma a el resultat que es la suma final, suma nomes es de un digit.
            resultatFinal = resultatFinal + resultat[i] ;
        }
        System.out.println(Arrays.toString(resultat));

        return new BigNumber(resultatFinal);
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
                //Cream dues variables c1 i c2 que les pasarem a char i despres a int per poder comprobar numero per numero.
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
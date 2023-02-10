import java.util.Arrays;
import java.util.IllegalFormatCodePointException;
import java.util.Objects;

class BigNumber {
    public static void main(String[] args) {
        //Se ha de entregar sense un main.
        BigNumber b1 = new BigNumber("99999999999999999999999");
        BigNumber b2 = new BigNumber("9999999999999999999999");
        BigNumber resultat = b1.mult(b2);

       // System.out.println(b1.equals(b2) +" equals");
        // b1 > b2 (1)
        // b1 == b2 (0)
        // b1 <b2 (- 1)
        //System.out.println(b1.compareTo(b2));
       System.out.println("Resultat "+resultat);


    }

    String valor; //El BigNumber te rebra un atribut que utilitzarem emmagatzemar el nombres que es pasen.
            //Ex: b1 i b2 son els objectes

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

        String b1 = this.valor;
        String b2 = other.valor;
        //Antes error per no afegir ceros al numero que es gira.
        int residuo = 0;
        int mesGran = Math.max(b1.length(), b2.length());
        int resultat[] = new int[mesGran+1];
        String resultatFinal = "";

        while (b1.length() != b2.length()) { //si la longitut no es igual afegim ceros al de menor longitud
            String[] aux = igualarCero(b1,b2);
            b1 =aux[0];
            b2= aux[1];
        }
        //una vegada afegim els ceros per igualar invertim el numero.
        String b1Invers=giraString(b1);
        String b2Invers = giraString(b2);

        for (int i = 0; i < mesGran; i++) {
            //Cream dues variables c1 i c2 que les pasarem a char i despres a int per poder comprobar numero per numero.
            int c1 = Integer.parseInt(String.valueOf(b1Invers.charAt(i)));
            int c2 = Integer.parseInt(String.valueOf(b2Invers.charAt(i)));

            if (b1.length()==1 && b2.length()==1){
                int suma = c1+c2+residuo;
                residuo = suma /10;
                resultat[i] = suma %10; // Sumam el valor de suma a el resultat que es la suma final, suma nomes es de un digit.
                resultatFinal = resultatFinal +resultat[i] ;
                return new BigNumber(resultatFinal);
            }
            int suma = c1+c2+residuo;
            residuo = suma /10;

            if (residuo==1) {
                resultat[mesGran-i] = suma %10; // Sumam el valor de suma a el resultat que es la suma final, suma nomes es de un digit.
                resultat[mesGran-i-1] = residuo; // Sumam el valor de suma a el resultat que es la suma final, suma nomes es de un digit.
            } else{
                resultat[mesGran-i] = suma %10; // Sumam el valor de suma a el resultat que es la suma final, suma nomes es de un digit.

            }
        }
        for (int i = 0; i < resultat.length; i++) {
            resultatFinal =resultatFinal+ resultat[i];
        }

        //System.out.println(resultatFinal+"Print de array" );

        return new BigNumber(resultatFinal);
    }

    private String[] igualarCero(String b1, String b2) {
        if (b1.length() > b2.length()) {
            b2 = 0 + b2;
        } else {
            while (b1.length() != b2.length()) {
                b1 = 0 + b1;
            }
        }
        return new String[]{b1,b2};
    }

    private String giraString(String s) {
        String girat="";
        for (int i = s.length()-1; i >=0 ; i--) {
            girat +=s.charAt(i);
        }
        return girat;
    }

    // Resta
    BigNumber sub(BigNumber other) {
        //Creamos dos strings de los valores que tenemos
        String b1 = this.valor;
        String b2 = other.valor;
        //Luego creos un int que sea la longitud de cada 1
        int lomg_s1 = b1.length();
        int long_s2 = b2.length();
        //Creamos la variable residuo que solamente puede ser 1 o 0
        int residuo = 0;
        String res = "";

        //Creamos la variable a que lo que haga sea la resta de la longitud de b1 i la longitud de b2
        //para luego poder añadir ceros.
        int a = (Math.abs(b1.length()-b2.length()));

        //Añadimos ceros a la iquierda
        if (lomg_s1 > long_s2){ // Si a1 es mas largo que b2
            for (int i = 0; i < a; i++) { // Hacemos un bucle que recorra a y le añadimos 0 a la izquierda a b2
                b2 = "0"+b2;
            }
        } else { // Si b2 es mas largo que b1
            for (int i = 0; i < a; i++) { // Hacemos un bucle que recorra a y le añadimos 0 a la izquierda a b1
                b1 = "0"+b1;
            }
        }

        //Creamos una variable tamaño que es la longitud de b1 con ceros
        int tamano = b1.length();

        //Cremos un for que recorra la longitud de a1 que es igual a la longitud de b2
        for (int i = tamano -1; i >= 0 ; i--) {
            //Pasa de String a Int
            int c1 = Integer.parseInt(String.valueOf(b1.charAt(i)));
            int c2 = Integer.parseInt(String.valueOf(b2.charAt(i))) + residuo;
            int resto;

            //Si c2 es major a c1 significa
            if (c2 > c1){
                resto = (c1 + 10) - c2;
                residuo =1;
            } else { // Si c1 es mayor que c2 entonces solo hacemos un resta normal
                resto = c1 - c2;
                residuo = 0;
            }
            res = resto+ res;
        }

        return new BigNumber(res);
    }

    // Multiplica
    BigNumber mult(BigNumber other) {
//Creamos dos strings de los valores que tenemos
        String mult1 = this.valor;
        String mult2 = other.valor;
        //Luego creos un int que sea la longitud de cada 1
        int lenght_mult1 = mult1.length();
        int lenght_mult2 = mult2.length();
        //Creamos la variable llevo que solamente puede ser 1 o 0
        int llevo = 0;
        //Creamos esta variable donde guiardaremos el resultado
        String res = "";
        BigNumber resFinal = new BigNumber("0");

        //Tenemos esta variable que nos añade ceros a medida que augmenta los numeros
        int nceros = 0;

        //Cremos un for que recorra la longitud de mult2 de derecha a izquierda
        for (int i = lenght_mult2-1; i >= 0; i--) {
            char c = mult2.charAt(i);
            res = "";
            //Cremos un for que recorra la longitud de mult1 de derecha a izquierda
            for (int j = lenght_mult1-1; j >= 0; j--) {
                //Pasa de String a Int las variable creadas anteriormente
                int c1 = Integer.parseInt(String.valueOf(mult1.charAt(j)));
                int c2 = Integer.parseInt(String.valueOf(c));
                //Creamos la variable mult donde realizaremos la operacion
                int mult = c1 * c2 ;
                 mult+= llevo;


                //Si la longitud de mult1 y mult2 es igual a 1
                if (lenght_mult1 == 1 && lenght_mult2 == 1){
                    res = res + mult;               //Hace una operacion normal
                    return new BigNumber(res);      //Nos devuelve res
                }
                //Si la multiplicacion es major a 9 entonces significa que llevamos
                if (mult > 9){
                    res = (mult%10)+ res;           //Coge el resto de la operacion y le suma res
                    llevo = mult / 10;//De esta manera conseguimos lo que llevamos

                }
                //Si la multiplicacion es menor a 9 entonces significa que no llevamos
                else {
                    res = mult + res;               //Y hace la multiplicacion mas el resto
                    llevo = 0;                      //Y lllevamos 0
                }
                //Si hemos llegado al final de la multiplicacion y tenemos que llavar 1
                if (j == 0 && llevo != 0 ){
                    res = llevo + res;
                    llevo=0;
                }
            }
            //Este for lo que hace es añadir 0 quanto mas avance la variable j
            for (int j = 0; j < nceros; j++) {
                res = res + '0';                     //Añade ceros a la izquierda
            }
            nceros++;

            resFinal = new BigNumber(res).add(resFinal);
        }
        return resFinal;
    }
    private String numMenor(String b1, String b2) {
        if (b1.length()<b2.length()) { // retorna qui es el numero amb la longitut mes petita
            return b1;
        } else { //b1 es mes petit
            return b2;
        }
    }

    private String numMesGran(String b1, String b2) {
        if (b1.length()<b2.length()) {
            // retorna qui es el numero amb la longitut mes gran
            return b2;
        } else {
            //b1 es mes gran
            return b1;
        }
    }

    // Divideix
    BigNumber div(BigNumber other) {
     //hem de tornar nomes el  cocient(resultat) i truncar en cocient per baix es a dir numeros enters i eliminant els decimals.

        String b1 = this.valor;
        String b2 = other.valor;
        String dividendo= numMesGran(b1,b2);
        String divisor= numMenor(b1,b2);

        for (int i = 0; i < divisor.length(); i++) {
            int c2 = Integer.parseInt(String.valueOf(b2.charAt(i)));
            for (int j = 0; j < dividendo.length(); j++) {
                int c1 = Integer.parseInt(String.valueOf(b1.charAt(i)));

            }
        }
       return null;
    }

    public static int[] toIntArray(String number) {
        int[] result = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            result[i] = number.charAt(number.length() - 1 - i) - '0';
        }
        return result;
    }

    // Arrel quadrada
    BigNumber sqrt() {
        return null;
    }

    // Potència
    BigNumber power(int n) {
        BigNumber potencia = new BigNumber(this.valor);
        //Multiplicamos por el mismo número hasta que n sea menor que 1
        for (int i = 1;i < n; i++) {
            potencia = potencia.mult(this);
        }
            return potencia;
    }

    // Factorial
    BigNumber factorial() {

        BigNumber res=new BigNumber(this.valor);
        BigNumber resta=new BigNumber("1");

        BigNumber num  =new BigNumber(String.valueOf(this.sub(resta)));
        while(!num.valor.equals("1")) {
            res= res.mult(num);
            num = num.sub(resta);

        }

        return res;
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
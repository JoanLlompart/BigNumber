import java.util.Arrays;
import java.util.IllegalFormatCodePointException;
import java.util.Objects;

class BigNumber {
    public static void main(String[] args) {
        //Se ha de entregar sense un main.
        BigNumber b1 = new BigNumber("12");
        BigNumber b2 = new BigNumber("2");
        BigNumber resultat = b1.mult(b2);

       // System.out.println(b1.equals(b2) +" equals");
        // b1 > b2 (1)
        // b1 == b2 (0)
        // b1 <b2 (- 1)
        //System.out.println(b1.compareTo(b2));
        System.out.println("Resultat "+resultat);


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

        System.out.println(resultatFinal+"Print de array" );

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
        String b1 = this.valor;
        String b2 = other.valor;
        //Antes error per no afegir ceros al numero que es gira.
        int residuo = 0;
        int mesGran = Math.max(b1.length(), b2.length());
        int resultat[] = new int[mesGran+1];
        String resultatFinal = "";

        while (b1.length() != b2.length()) { //si la longitut no es igual afegim ceros al
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
                int resta = c1-c2;
                residuo = resta /10;

                resultat[i] = (resta %10) - residuo; // Sumam el valor de suma a el resultat que es la suma final, suma nomes es de un digit.
                resultatFinal = resultatFinal + resultat[i] ;
                return new BigNumber(resultatFinal);
            }
            int resta = c1-c2-residuo;
            residuo = resta /10;

            if (residuo==1) {
                resultat[mesGran-i] = resta %10; // Sumam el valor de suma a el resultat que es la suma final, suma nomes es de un digit.
                resultat[mesGran-i-1] = residuo; // Sumam el valor de suma a el resultat que es la suma final, suma nomes es de un digit.
            } else{
                resultat[mesGran-i] = resta %10; // Sumam el valor de suma a el resultat que es la suma final, suma nomes es de un digit.

            }
        }
        for (int i = 0; i < resultat.length; i++) {
            resultatFinal =resultatFinal+ resultat[i];
        }

        System.out.println(resultatFinal+"Print de array" );

        return new BigNumber(resultatFinal);
    }

    // Multiplica
    BigNumber mult(BigNumber other) {

        String b1 = this.valor;
        String b2 = other.valor;
        int bLongMajor = Math.max(b1.length(), b2.length()); //logitud de el numero mes gran
        int bLongMenor = Math.min(b1.length(), b2.length()); //logitud de el numero mes gran

        int residuo = 0;
        String numeroMajor= numMesGran(b1,b2);
        String numeroMenor= numMenor(b1,b2);
        int resultat[] = new int[bLongMajor+1];
        String res = "";


        String b1Invers=giraString(b1); //b1 sempre sera el valor mes gran
        String b2Invers = giraString(b2); // b2 sempre sera el valor mes petit

       int diferencia = b1.compareTo(b2);

      /* switch (diferencia) {
           case -1:
               System.out.println("b2 major que b1");
           case 0:
               System.out.println("iguals");
           case 1:
               System.out.println("b1 major que b2");
       }

       */

        if (bLongMajor==1){ //Per els nombres que els dos tenguin longitut 1.
            for (int i = 0; i < bLongMajor; i++) {
                int c1 = Integer.parseInt(String.valueOf(b1Invers.charAt(i)));
                int c2 = Integer.parseInt(String.valueOf(b2Invers.charAt(i)));
                int multiplicacio =c1*c2; //se multiplican simplement ja que no tendra residu.
                res =res+ multiplicacio; // En aquest cas no feim us de el Array de int i el pasam directa a el String res.
                return new BigNumber(res);
            }

        } else if (bLongMenor==1) { //si el numero menor es el unic que nomes te un digit pero el mes gran en te mes de un.
            for (int i = 0; i < bLongMenor; i++) {
                int c2 = Integer.parseInt(String.valueOf(b2.charAt(i))); //com nomes te longitut 1 no el invertim
                for (int j = 0; j < bLongMajor; j++) {
                    int c1 = Integer.parseInt(String.valueOf(b1Invers.charAt(j)));
                    int multiplicacio = c1*c2;
                    int tempMult = multiplicacio %10; //Cream una variable temporal que nomes existira dins el aquest for al igual que multiplicacio,
                                                        // agafa la segona unitat, per despres sumar el residu a la multiplicació.(b1*b2) + residu
                    resultat[bLongMajor-j] = tempMult + residuo; //Afegeix els nombres començant per la darrera posicio de el array, (dreta a esquerra)
                    residuo = multiplicacio /10; // agafa el residu de la multiplicacio.En el cas que en tengui
                }

            }


        }
        for (int i = 0; i < resultat.length; i++) {
            if (residuo>0) { //si hem acabat de multiplicar i tenim algun residu, se afegira el residu a la posicio 0 del resultat,
                            // es a dir com a primer numero.
                resultat[0] = residuo;

            }
            res=res+resultat[i]; //se colocaran els nombres per ordre comensant per el darrer fins el primer,
            //tambe el pasam a String a la hora de sumar res a res mes el array de int


        }


        /*for (int i = 0; i < numeroMajor.length(); i++) {
            int c2 = Integer.parseInt(String.valueOf(b2Invers.charAt(i)));
            for (int j = 0; j < numeroMajor.length(); j++) {
                int c1 = Integer.parseInt(String.valueOf(b1Invers.charAt(j)));
                int multiplicacio = c1*c2;
                residuo = multiplicacio /10;

            }
        }

         */




  /*      for (int i = 0; i < bLongMajor; i++) {
            int c1 = Integer.parseInt(String.valueOf(b1Invers.charAt(i)));
            if (b2.length()< b1.length()){
                while (b1.length() != b2.length()) { //si la longitut no es igual afegim ceros al
                    String[] aux = igualarCero(b1,b2);
                    b1 =aux[0];
                    b2= aux[1];
                }
            }
            int c2 = Integer.parseInt(String.valueOf(b2.charAt(i)));

            int multiplicacio = c1*c2;
            if (bLongMajor==1) {
                res= res + multiplicacio;
                return new BigNumber(res);
            } else {
                resultat[i] = multiplicacio %10;
                residuo = multiplicacio /10;
            }
        }

   */


        System.out.println("Numero major :"+numMesGran(b1,b2));

        return new BigNumber(res);


    }

    private String numMenor(String b1, String b2) {
        if (b1.length()<b2.length()) { // retorna qui es el numero amb la longitut mes petita
            return b1;
        } else { //b1 es mes petit
            return b2;
        }
    }

    private String numMesGran(String b1, String b2) {
        if (b1.length()<b2.length()) { // retorna qui es el numero amb la longitut mes gran
            return b2;
        } else { //b1 es mes gran
            return b1;
        }
    }

    // Divideix
    BigNumber div(BigNumber other) {
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
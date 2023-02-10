import java.util.Arrays;
import java.util.IllegalFormatCodePointException;
import java.util.Objects;

class BigNumber {
    public static void main(String[] args) {
        //Se ha de entregar sense un main.
        BigNumber b1 = new BigNumber("154");
        BigNumber b2 = new BigNumber("43");
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

        String b1 = this.valor;
        String b2 = other.valor;
        int bLongMajor = Math.max(b1.length(), b2.length()); //logitud de el numero mes gran
        int bLongMenor = Math.min(b1.length(), b2.length()); //logitud de el numero mes gran

        int residuo = 0;
        String numeroMajor= numMesGran(b1,b2);
        String numeroMenor= numMenor(b1,b2);
        int resultat[] = new int[bLongMajor+1];
        //int totalLlargaria =bLongMajor+bLongMenor;
        //int resultat[] = new int[totalLlargaria];
        int[][] resTemp = new int[bLongMenor+1][resultat.length]; //primer cuadrat la cantitat de files, el segon els nombres de cada dimensio
        String[] sumes=new String[bLongMenor+1];
        String res = "";


        String b1Invers=giraString(b1); //b1 sempre sera el valor mes gran
        String b2Invers = giraString(b2); // b2 sempre sera el valor mes petit

       int diferencia = b1.compareTo(b2);

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
                    int multiplicacio = c1 * c2;
                    int tempMult = multiplicacio % 10; //Cream una variable temporal que nomes existira dins el aquest for al igual que multiplicacio,
                    // agafa la segona unitat, per despres sumar el residu a la multiplicació.(b1*b2) + residu
                    resultat[bLongMajor - j] = tempMult + residuo; //Afegeix els nombres començant per la darrera posicio de el array, (dreta a esquerra)
                    residuo = multiplicacio / 10; // agafa el residu de la multiplicacio.En el cas que en tengui
                }

            }
        } else if (bLongMenor>1) { //si el nombre mes petit es de mes de dos digits.
            for (int i = 0; i < bLongMenor; i++) {
                int c2 = Integer.parseInt(String.valueOf(b2Invers.charAt(i))); //com  te longitut mes de 1 el invertim
                for (int j = 0; j < bLongMajor; j++) {
                    int c1 = Integer.parseInt(String.valueOf(b1Invers.charAt(j)));
                    int multiplicacio = c1 * c2;
                    int tempMult = multiplicacio % 10;
                   // sumes[i]= String.valueOf(tempMult + residuo);
                    //resTemp[i][bLongMajor - j-i] = (tempMult + residuo)
                    resTemp[i][bLongMajor -j -i] = (tempMult + residuo);

                    residuo = multiplicacio / 10;
                }

            }


            for (int j =resTemp[0].length-1; j >= 0; j--) {
                //suma comensa amb el valor de el residu,suma se reinicia a cada volta de el primer bucle.
                int sum = residuo;
                for (int i = 0; i < resTemp.length; i++) {
                    //sum = Integer.parseInt(sum + sumes[j]);
                    sum += resTemp[i][j];
                }
                //guarda el residu per sumarli a la seguent fila
                residuo = sum / 10;
                //guarda el resultat de la suma de dreta a esquerra.
                resultat[j] = sum % 10;
            }


            /*

            Probar a pasar tots els valors de el Array final una posicio cap a la dreta


             */


        }
        for (int i = 0; i < resultat.length; i++) {
            if (residuo>0) { //si hem acabat de multiplicar i tenim algun residu, se afegira el residu a la posicio 0 del resultat,
                            // es a dir com a primer numero.
                resultat[0] = residuo;
            }
            res=res+resultat[i]; //se colocaran els nombres per ordre comensant per el darrer fins el primer,
            //tambe el pasam a String a la hora de sumar res a res mes el array de int
        }
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
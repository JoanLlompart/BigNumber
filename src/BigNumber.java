class BigNumber {
    public static void main(String[] args) {
        //Se ha de entregar sense un main.
        BigNumber b1 = new BigNumber("5");
        BigNumber b2 = new BigNumber("2");
        BigNumber resultat = b1.power(3);

        // System.out.println(b1.equals(b2) +" equals");

        //System.out.println(b1.compareTo(b2));
        System.out.println("Resultat " + resultat);


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

        //Pasam this.valor a tipus String i asignam el nom com a b1
        String b1 = this.valor;
        //Pasam Other.valor a tipus String i asignam el nom com a b2
        String b2 = other.valor;
        //Antes error per no afegir ceros al numero que es gira.
        int residu = 0;
        int mesGran = Math.max(b1.length(), b2.length());
        //La variable resultat sera un array de int amb la longitut de el
        // nombre mes llarg i +1 per si te residu el darrer nombre de la suma.
        int resultat[] = new int[mesGran + 1];
        String resultatFinal = "";

        //Si la longitut no es igual afegim ceros al de menor longitud
        while (b1.length() != b2.length()) {
            String[] aux = igualarZero(b1, b2);
            b1 = aux[0];
            b2 = aux[1];
        }
        //una vegada afegim els ceros per igualar invertim el numero.
        String b1Invers = giraString(b1);
        String b2Invers = giraString(b2);

        for (int i = 0; i < mesGran; i++) {
            //Cream dues variables c1 i c2 que les pasarem a char i despres a int per poder comprobar numero per numero.
            int c1 = Integer.parseInt(String.valueOf(b1Invers.charAt(i)));
            //Utilitzam les variables girades
            int c2 = Integer.parseInt(String.valueOf(b2Invers.charAt(i)));


            //Si nomes es calculen nombres de longitud 1 es sumara
            if (b1.length() == 1 && b2.length() == 1) {
                int suma = c1 + c2;
                // Sumam el valor de suma a el resultat que es la suma final, suma nomes es de un digit.
                resultat[i] = suma % 10;
                resultatFinal = resultatFinal + resultat[i];
                return new BigNumber(resultatFinal);
            }
            int suma = c1 + c2 + residu;
            residu = suma / 10;

            if (residu == 1) {
                // Sumam el valor de suma a el resultat que es la suma final, suma nomes es de un digit.
                resultat[mesGran - i] = suma % 10;
                // Afegim el residu.
                resultat[mesGran - i - 1] = residu;
            } else {
                // Sumam el valor de suma a el resultat que es la suma final, suma nomes es de un digit.
                resultat[mesGran - i] = suma % 10;

            }
        }

        //for que converteix el array resultat en un String de resultatFinal
        for (int i = 0; i < resultat.length; i++) {
            resultatFinal = resultatFinal + resultat[i];
        }
        return new BigNumber(resultatFinal);
    }

    //funcio que iguala els zeros de b1 i b2 per poder realitzar operacions
    // correctament caracter a caracter
    private String[] igualarZero(String b1, String b2) {
        if (b1.length() > b2.length()) {
            b2 = 0 + b2;
        } else {
            while (b1.length() != b2.length()) {
                b1 = 0 + b1;
            }
        }
        return new String[]{b1, b2};
    }


    // Funcio que cambia inverteix la posicio de caracters de tot un String.
    // ex: "CAR" --> giraString("CAR") Cse converteix en "RAC"
    private String giraString(String s) {
        String girat = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            girat += s.charAt(i);
        }
        return girat;
    }

    // Resta
    BigNumber sub(BigNumber other) {
        String b1 = this.valor;
        String b2 = other.valor;
        int mesGran = Math.max(b1.length(), b2.length());
        int residu = 0;


        while (b1.length() != b2.length()) {
            if (b1.length() < b2.length()) b1 = '0' + b1;
            else b2 = '0' + b2;
        }

        String b1Invers = giraString(b1);
        String b2Invers = giraString(b2);

        String res = "";

        for (int i = 0; i < mesGran; i++) {
            int c1 = Integer.parseInt(String.valueOf(b1Invers.charAt(i)));
            int c2 = Integer.parseInt(String.valueOf(b2Invers.charAt(i))) + residu;

            if (c1 < c2) {
                c1 += 10;
                residu = 1;
            } else {
                residu = 0;
            }
            int resTmp = c1 - c2;
            res = res + resTmp;
        }
        res = giraString(res);

        return new BigNumber(res);
    }

    // Multiplica
    BigNumber mult(BigNumber other) {
        //Creamos dos strings de los valores que tenemos
        String b1 = this.valor;
        String b2 = other.valor;
        //Logitud de el numero mes gran
        int b1Long=b1.length();

        int b2Long=b2.length();
        //Creamos la variable llevo que solamente puede ser 1 o 0
        int residu = 0;
        //Creamos esta variable donde guiardaremos el resultado
        String res = "";
        BigNumber resultatFinal = new BigNumber("0");

        //variable int per afegir zeros a la dreta per les sumes
        int numZero = 0;

        //Cream un for que recorr la longitud de b2 de sreta a esquerra per començar per la dreta.
        for (int i = b2Long - 1; i >= 0; i--) {
            char c = b2.charAt(i);
            res = "";

            //Segon for que recorr la longitud de b1 de dreta a esquerra
            for (int j = b1Long - 1; j >= 0; j--) {

                ////Cream dues variables c1 i c2 que les pasarem a char i
                // despres a int per poder comprobar numero per numero.
                int c1 = Integer.parseInt(String.valueOf(b1.charAt(j)));
                int c2 = Integer.parseInt(String.valueOf(c));

                //Creem la variable multiplicacio que multiplica de manera simplificada
                // valor per valor amb les variables anteriors.
                int multiplicacio = c1 * c2;
                multiplicacio += residu;


                //Si la longitud de b1 y b2 es igual a 1
                if (b1Long == 1 && b2Long == 1) {
                //Se multiplican simplement ja que no tendra residu.
                    res = res + multiplicacio;
                    return new BigNumber(res);
                }
                //Multiplicacio dona mes de 9 tendrem un residu que se ha de guardar per sumar
                // a la proxima operacio
                if (multiplicacio > 9) {
                    //agafa la unitat i el suma a res
                    res = (multiplicacio % 10) + res;
                    residu = multiplicacio / 10;//De esta manera conseguimos lo que llevamos
                }
                //Si la multiplicacio dona nomes un resultat de una unitat no hi ha residu
                else {
                    //Afegeix la unitat de la multiplicacio al resultat a la esquerra.
                    res = multiplicacio + res;
                    //Reiniciam el reciduo a 0.
                    residu = 0;
                }
                //Si residu no es igual que 0 i ja se han realitzat totes les multiplicacions
                if (j == 0 && residu != 0) {
                    //Afegim el residu a la primera posicio de el resultat
                    res = residu + res;
                    residu = 0;
                }
            }
            //Este for lo que hace es añadir 0 quanto mas avance la variable j
            for (int j = 0; j < numZero; j++) {
                res = res + '0';                     //Añade ceros a la izquierda
            }
            numZero++;

            resultatFinal = new BigNumber(res).add(resultatFinal);
        }
        return resultatFinal;
    }

    private String numMenor(String b1, String b2) {
        if (b1.length() < b2.length()) { // retorna qui es el numero amb la longitut mes petita
            return b1;
        } else { //b1 es mes petit
            return b2;
        }
    }

    private String numMesGran(String b1, String b2) {
        if (b1.length() < b2.length()) {
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
/*
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

 */
        StringBuilder cociente = new StringBuilder();

        String b1 = this.valor;
        String b2 = other.valor;
        int numDividendo = b1.length();
        int numDivisor = Integer.parseInt(b2);
        int residuo = 0;

        for (int i = 0; i < numDividendo; i++) {
            int digito = b1.charAt(i) - '0';
            int multParcial = residuo;//residuo * 10
            int cocienteParcial = (multParcial + digito) / numDivisor;
            residuo = (multParcial + digito) % numDivisor;
            cociente.append(cocienteParcial);
        }
        cociente = new StringBuilder(cociente.toString());
        return new BigNumber(String.valueOf(cociente));

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
        //Multiplicam el nombre de "potencia" per el numero de vegades "n"
        // ex: 5*5*5*5*5=3125

        //la variable i li asignam el valor 1,
        for (int i = 1; i < n; i++) {
            //potencia es multiplica
            potencia = potencia.mult(this);
        }
        System.out.println(potencia);
        return potencia;
    }

    // Factorial
    BigNumber factorial() {

        BigNumber res = new BigNumber(this.valor);
        BigNumber resta = new BigNumber("1");

        //feim una resta de 1  a num.
        BigNumber num = new BigNumber(String.valueOf(this.sub(resta)));
        //Per el factorial nomes volem els nombres enters
        while (!num.valor.equals("1")) {
            //res se multiplica per num amb la funcio creada anteriorment,
            // en cas de no utilitzar la funcio creada mult nofuncionaria be amb nombres grans.
            res = res.mult(num);
            //li restam 1 a num.
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
            return -1;
        } else if (b1.length() == b2.length()) {
            for (int i = 0; i < b1.length(); i++) {
                //Cream dues variables c1 i c2 que les pasarem a char i despres a int per poder comprobar numero per numero.
                int c1 = Integer.parseInt(String.valueOf(b1.charAt(i)));
                int c2 = Integer.parseInt(String.valueOf(b2.charAt(i)));
                // b1 > b2 (1)
                // b1 == b2 (0)
                // b1 <b2 (- 1)
                if (c1 > c2) {
                    return 1;
                } else if (c1 < c2) {
                    return -1;
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
    public boolean equals(Object other) {
        //Object es una clase especial java que esta per demunt de altres objectes
        BigNumber b = (BigNumber) other;
        if (b.valor.equals(this.valor)) return true;
        return false;
    }
}
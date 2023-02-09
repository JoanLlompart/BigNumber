import org.junit.Test;

import static org.junit.Assert.*;

public class BigNumberTest {

    @Test
    public void comparacions() {
        BigNumber b1, b2;

        b1 = new BigNumber("002");
        b2 = new BigNumber("2");
        assertEquals(b1, b2);
        b1 = new BigNumber("4");
        b2 = new BigNumber("2");
        assertNotEquals(b1, b2);

        b1 = new BigNumber("2");
        b2 = new BigNumber("02");
        assertEquals(b1, b2);

        b1 = new BigNumber("345345234523452352345");
        b2 = new BigNumber("000000000000000345345234523452352345");
        assertEquals(b1, b2);

        b1 = new BigNumber("56");
        b2 = new BigNumber("000000000000000000000000000000056");
        assertEquals(0, b1.compareTo(b2));

        b1 = new BigNumber("456456345634563456345634556785678567856783456345634563456567456745674567");
        b2 = new BigNumber("456456345634563456345634556785678567856783456345634563456567456745674567");
        assertEquals(0, b1.compareTo(b2));

        b1 = new BigNumber("456456345634563456345634556785678567856783456345634563456567456745674561");
        b2 = new BigNumber("456456345634563456345634556785678567856783456345634563456567456745674567");
        assertEquals(-1, b1.compareTo(b2));

        b1 = new BigNumber("556456345634563456345634556785678567856783456345634563456567456745674561");
        b2 = new BigNumber("456456345634563456345634556785678567856783456345634563456567456745674567");
        assertEquals(1, b1.compareTo(b2));

        b1 = new BigNumber("556456345634563456345634556785678567856783456345634563456567456745674561");
        b2 = new BigNumber("556456345634563456345634556783678567856783456345634563456567456745674561");
        assertEquals(1, b1.compareTo(b2));

        b1 = new BigNumber("000556456345634563456345634556785678567856783456345634563456567456745674561");
        b2 = new BigNumber("556456345734563456345634556783678567856783456345634563456567456745674561");
        assertEquals(-1, b1.compareTo(b2));

        b1 = new BigNumber("3");
        b2 = new BigNumber("14");
        assertEquals(-1, b1.compareTo(b2));
        assertEquals(1, b2.compareTo(b1));

        b1 = new BigNumber("35");
        b2 = new BigNumber("35");
        assertEquals(0, b2.compareTo(b1));

        b1 = new BigNumber("135");
        b2 = new BigNumber("136");
        assertEquals(-1, b1.compareTo(b2));
        assertEquals(1, b2.compareTo(b1));

        b1 = new BigNumber("00135");
        b2 = new BigNumber("136");
        assertEquals(-1, b1.compareTo(b2));
        assertEquals(1, b2.compareTo(b1));

        b1 = new BigNumber("135467845634556778909546345234534534512335");
        b2 = new BigNumber("135467845634556778909546345234534534512332");
        assertEquals(1, b1.compareTo(b2));
        assertEquals(-1, b2.compareTo(b1));
    }

    @Test
    public void sumes() {
        BigNumber b1, b2;

        b1 = new BigNumber("2");
        b2 = new BigNumber("02");
        assertEquals(b1, b2);
        assertEquals(new BigNumber("4"), b1.add(b2));

        b1 = new BigNumber("12");
        b2 = new BigNumber("34");
        assertEquals(new BigNumber("46"), b1.add(b2));

        b1 = new BigNumber("00022");
        b2 = new BigNumber("090");
        assertEquals(new BigNumber("112"), b1.add(b2));

        b1 = new BigNumber("123");
        b2 = new BigNumber("999");
        assertEquals(new BigNumber("1122"), b1.add(b2));

        b1 = new BigNumber("999");
        b2 = new BigNumber("999");
        assertEquals(new BigNumber("1998"), b1.add(b2));


        b1 = new BigNumber("567456234578945345234234456");
        b2 = new BigNumber("456235768978078934523523452345456");
        assertEquals(new BigNumber("456236336434313513468868686579912"), b1.add(b2));

        b1 = new BigNumber("342234234123423576789423422323123412341234");
        b2 = new BigNumber("3245234789789234234123784567892349789456");
        assertEquals(new BigNumber("345479468913212811023547206891015762130690"), b1.add(b2));
    }

    @Test
    public void restes() {
        BigNumber b1, b2;

        b1 = new BigNumber("60");
        b2 = new BigNumber("010");
        assertEquals(new BigNumber("50"), b1.sub(b2));

        b1 = new BigNumber("34535233");
        b2 = new BigNumber("04533453");
        assertEquals(new BigNumber("30001780"), b1.sub(b2));

        b1 = new BigNumber("7");
        b2 = new BigNumber("3");
        assertEquals(new BigNumber("4"), b1.sub(b2));

        b1 = new BigNumber("14");
        b2 = new BigNumber("12");
        assertEquals(new BigNumber("2"), b1.sub(b2));

        b1 = new BigNumber("34");
        b2 = new BigNumber("19");
        assertEquals(new BigNumber("15"), b1.sub(b2));

        b1 = new BigNumber("32453453");
        b2 = new BigNumber("2313");
        assertEquals(new BigNumber("32451140"), b1.sub(b2));

        b1 = new BigNumber("32456789567456786783453");
        b2 = new BigNumber("2345664556756713");
        assertEquals(new BigNumber("32456787221792230026740"), b1.sub(b2));

        b1 = new BigNumber("56734564576346234567567834534565675674567");
        b2 = new BigNumber("999999999999999999999999999999999999999");
        assertEquals(new BigNumber("55734564576346234567567834534565675674568"), b1.sub(b2));

        b1 = new BigNumber("45634563456565555557854564223429999886785678912");
        b2 = new BigNumber("0000000000000000000000000000000000000000000000000000000000011");
        assertEquals(new BigNumber("45634563456565555557854564223429999886785678901"), b1.sub(b2));

        b1 = new BigNumber("45634563456565555557854564223429999886785678912");
        b2 = new BigNumber("789789797979454534534534567867823489898899");
        assertEquals(new BigNumber("45633773666767576103320029688862132063295780013"), b1.sub(b2));

        b1 = new BigNumber("45634563456565555557854564223429999886785678912");
        b2 = new BigNumber("789789797979454534534534567867823489898899");
        assertEquals(new BigNumber("45633773666767576103320029688862132063295780013"), b1.sub(b2));
    }

    @Test
    public void multiplicacions() {
        BigNumber b1, b2;
        b1 = new BigNumber("2");
        b2 = new BigNumber("02");
        assertEquals(b1, b2);
        assertEquals(new BigNumber("4"), b1.mult(b2));

        b1 = new BigNumber("6");
        b2 = new BigNumber("6");
        assertEquals(b1, b2);
        assertEquals(new BigNumber("36"), b1.mult(b2));

        b1 = new BigNumber("3");
        b2 = new BigNumber("2");
        assertEquals(new BigNumber("6"), b1.mult(b2));

        b1 = new BigNumber("12");
        b2 = new BigNumber("2");
        assertEquals(new BigNumber("24"), b1.mult(b2));

        b1 = new BigNumber("685");
        b2 = new BigNumber("5");
        assertEquals(new BigNumber("3425"), b1.mult(b2));

        b1 = new BigNumber("154");
        b2 = new BigNumber("43");
        assertEquals(new BigNumber("6622"), b1.mult(b2));

        b1 = new BigNumber("10");
        b2 = new BigNumber("10");
        assertEquals(new BigNumber("100"), b1.mult(b2));

        b1 = new BigNumber("153233434212124");
        b2 = new BigNumber("4133223");
        assertEquals(new BigNumber("633347954654537795652"), b1.mult(b2));

        b1 = new BigNumber("99999999999999999999999");
        b2 = new BigNumber("9999999999999999999999");
        assertEquals(new BigNumber("999999999999999999999890000000000000000000001"), b1.mult(b2));

        b1 = new BigNumber("3457845646485642345641234534564561231563153");
        b2 = new BigNumber("456423123486481523156485312348648641231234864312341563");
        assertEquals(new BigNumber("1578240710503108871000974645825969302622584878648924684207547892743224646500723770841832141228139"), b1.mult(b2));

        b1 = new BigNumber("345345345345345234456456456345345");
        b2 = new BigNumber("22");
        assertEquals(new BigNumber("7597597597597595158042042039597590"), b1.mult(b2));

        b1 = new BigNumber("234890456345345345345345234456456456345345");
        b2 = new BigNumber("1228");
        assertEquals(new BigNumber("288445480392084084084083947912528528392083660"), b1.mult(b2));

        b1 = new BigNumber("01000000000000000000000000");
        b2 = new BigNumber("0001000000000000000000");
        assertEquals(new BigNumber("1000000000000000000000000000000000000000000"), b1.mult(b2));

        b1 = new BigNumber("45354645634563456345634563456");
        b2 = new BigNumber("4563456345634563456345634563456456");
        assertEquals(new BigNumber("206973945425055556860816594709203925477253940263513300024871936"), b1.mult(b2));
    }

    @Test
    public void divisions() {
        BigNumber b1, b2;

        b1 = new BigNumber("513");
        b2 = new BigNumber("5");
        assertEquals(new BigNumber("102"), b1.div(b2));

        b1 = new BigNumber("45354645634563456345456345234545897894562634563456");
        b2 = new BigNumber("45634563456345634563456546799008905634563456456");
        assertEquals(new BigNumber("993"), b1.div(b2));

        b1 = new BigNumber("2134345456123486126462457864246456434312345");
        b2 = new BigNumber("4534564545645644564564567861534978978512");
        assertEquals(new BigNumber("470"), b1.div(b2));

        b1 = new BigNumber("12345645634563456345656767878967894");
        b2 = new BigNumber("22");
        assertEquals(new BigNumber("561165710661975288438943994498540"), b1.div(b2));

        b1 = new BigNumber("10");
        b2 = new BigNumber("5");
        assertEquals(new BigNumber("2"), b1.div(b2));

        b1 = new BigNumber("12323");
        b2 = new BigNumber("546445");
        assertEquals(new BigNumber("0"), b1.div(b2));

        b1 = new BigNumber("12323");
        b2 = new BigNumber("12323");
        assertEquals(new BigNumber("1"), b1.div(b2));

        b1 = new BigNumber("23421");
        b2 = new BigNumber("3242");
        assertEquals(new BigNumber("7"), b1.div(b2));

        b1 = new BigNumber("2343453452342321234234");
        b2 = new BigNumber("33324234234232382422");
        assertEquals(new BigNumber("70"), b1.div(b2));

        b1 = new BigNumber("56734564576346234567567834534565675674567");
        b2 = new BigNumber("9999999999999999463499999999999999999");
        assertEquals(new BigNumber("5673"), b1.div(b2));
    }

    @Test
    public void arrels() {
        BigNumber b1;

        b1 = new BigNumber("9");
        assertEquals(new BigNumber("3"), b1.sqrt());

        b1 = new BigNumber("101");
        assertEquals(new BigNumber("10"), b1.sqrt());

        b1 = new BigNumber("43534526");
        assertEquals(new BigNumber("6598"), b1.sqrt());

        b1 = new BigNumber("787894563455677886789");
        assertEquals(new BigNumber("28069459621"), b1.sqrt());

        b1 = new BigNumber("49539472361252495694957657363652547596857463562384792834234234");
        assertEquals(new BigNumber("7038428259295714075692066420271"), b1.sqrt());

        b1 = new BigNumber("5431653867945764312451245128282828356497646538659435623124356794543721244424242455545454578787878788878788845424542154213464519787546754213401004042134275450012431245797845764210427345187242");
        assertEquals(new BigNumber("73699754870323444736249368421986319511585860927501064567285148011894494673039496223465592189750"), b1.sqrt());
    }

    @Test
    public void potencies() {
        BigNumber b1;

        b1 = new BigNumber("5");
        assertEquals(new BigNumber("25"), b1.power(2));

        b1 = new BigNumber("12");
        assertEquals(new BigNumber("1728"), b1.power(3));

        b1 = new BigNumber("94838283854322");
        assertEquals(new BigNumber("58862250964954017560693080286616839460677919918460221421949959341474013510211014138680704961715239095635828871828766919938391201667628647424"),
                b1.power(10));

        b1 = new BigNumber("938473829645210293636363634849283294756437283746563");
        assertEquals(new BigNumber("727965557358200753147285829529345807921638013046330120636207085559378903707532800080264820477035732495753675523815157636347906085671030819750767067121381326119671694030619244689760850961731970672766158559296947830104204709112835163907185672415345741869043"),
                b1.power(5));

        b1 = new BigNumber("54");
        assertEquals(new BigNumber("173530565594749650313970138801646228669666817493140734315611226326359917115402287845579726082605409496190657713239531694146682292420808181052268478998939835997192791970021376"),
                b1.power(100));
    }

    @Test
    public void factorials() {
        assertEquals(new BigNumber("120"), new BigNumber("5").factorial());
        assertEquals(new BigNumber("3628800"), new BigNumber("10").factorial());
        assertEquals(new BigNumber("30414093201713378043612608166064768844377641568960512000000000000"),
                new BigNumber("50").factorial());
        assertEquals(new BigNumber("57133839564458545904789328652610540031895535786011264182548375833179829124845398393126574488675311145377107878746854204162666250198684504466355949195922066574942592095735778929325357290444962472405416790722118445437122269675520000000000000000000000000000000000000"),
                new BigNumber("150").factorial());
        assertEquals(new BigNumber("1220136825991110068701238785423046926253574342803192842192413588385845373153881997605496447502203281863013616477148203584163378722078177200480785205159329285477907571939330603772960859086270429174547882424912726344305670173270769461062802310452644218878789465754777149863494367781037644274033827365397471386477878495438489595537537990423241061271326984327745715546309977202781014561081188373709531016356324432987029563896628911658974769572087926928871281780070265174507768410719624390394322536422605234945850129918571501248706961568141625359056693423813008856249246891564126775654481886506593847951775360894005745238940335798476363944905313062323749066445048824665075946735862074637925184200459369692981022263971952597190945217823331756934581508552332820762820023402626907898342451712006207714640979456116127629145951237229913340169552363850942885592018727433795173014586357570828355780158735432768888680120399882384702151467605445407663535984174430480128938313896881639487469658817504506926365338175055478128640000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),
                new BigNumber("500").factorial());
    }

    @Test
    public void mcd() {
        BigNumber b1, b2;

        b1 = new BigNumber("155");
        b2 = new BigNumber("60");
        assertEquals(new BigNumber("5"), b1.mcd(b2));

        b1 = new BigNumber("168");
        b2 = new BigNumber("584");
        assertEquals(new BigNumber("8"), b1.mcd(b2));

        b1 = new BigNumber("245456345356");
        b2 = new BigNumber("583453453452784");
        assertEquals(new BigNumber("4"), b1.mcd(b2));

        b1 = new BigNumber("2454563453452346787893523445675534535656678678356");
        b2 = new BigNumber("5");
        //assertEquals(new BigNumber("4"), b1.mcd(b2));
    }


}
/*
        while (b.valor.length() > 1 && b.valor.charAt(0) == '0') {
            b.valor = b.valor.substring(1);
        }
 */
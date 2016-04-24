package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class JoukkoOperaatiotTest {

    private IntJoukko teeJoukko(int... luvut) {
        IntJoukko joukko = new IntJoukko();

        for (int luku : luvut) {
            joukko.lisaa(luku);
        }
        return joukko;
    }

    @Test
    public void yhdisteTest() {
        IntJoukko eka = teeJoukko(1, 2);
        IntJoukko toka = teeJoukko(3, 4);

        IntJoukko yhdiste = IntJoukko.yhdiste(eka, toka);
        int[] vastauksenLuvut = yhdiste.toIntArray();
        Arrays.sort(vastauksenLuvut);

        int[] odotettu = {1, 2, 3, 4};

        assertArrayEquals(odotettu, vastauksenLuvut);
    }

    @Test
    public void leikkausTest() {
        IntJoukko eka = teeJoukko(1, 2, 3);
        IntJoukko toka = teeJoukko(2, 3, 4);

        IntJoukko leikkaus = IntJoukko.leikkaus(eka, toka);
        int[] vastauksenLuvut = leikkaus.toIntArray();
        Arrays.sort(vastauksenLuvut);

        int[] odotettu = {2, 3};

        assertArrayEquals(odotettu, vastauksenLuvut);
    }
    
    @Test
    public void erotusTest() {
        IntJoukko eka = teeJoukko(1, 2, 3);
        IntJoukko toka = teeJoukko(2, 3, 4);

        IntJoukko erotus = IntJoukko.erotus(eka, toka);
        int[] vastauksenLuvut = erotus.toIntArray();
        Arrays.sort(vastauksenLuvut);

        int[] odotettu = {1};

        assertArrayEquals(odotettu, vastauksenLuvut);
    }
}

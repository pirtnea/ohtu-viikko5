package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5,
            OLETUSKASVATUS = 5;
    private int[] lukujono;
    private int alkioidenLkm;

    public IntJoukko() {
        lukujono = new int[KAPASITEETTI];

        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }

        lukujono = new int[kapasiteetti];

        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }

        alkioidenLkm = 0;
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Sekä kapasiteetti että kasvatusjoukko oltava positiivisia!");
        }

        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (lukujono.length <= alkioidenLkm) {
                kasvataKokoa();
            }
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        boolean loyto = false;

        for (int i = 0; i < alkioidenLkm; i++) {
            if (lukujono[i] == luku) {
                loyto = true;
                alkioidenLkm--;
            }
            if (loyto) {
                lukujono[i] = lukujono[i + 1];
            }
        }
        return loyto;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuloste = "{";

        for (int i = 0; i < alkioidenLkm; i++) {
            tuloste += lukujono[i];

            if (i < alkioidenLkm - 1) {
                tuloste += ", ";
            }
        }
        return tuloste + "}";
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(lukujono, 0, taulu, 0, taulu.length);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko eka, IntJoukko toka) {
        IntJoukko yhdiste = new IntJoukko();
        int[] ekaTaulu = eka.toIntArray();
        int[] tokaTaulu = toka.toIntArray();
        for (int i = 0; i < ekaTaulu.length; i++) {
            yhdiste.lisaa(ekaTaulu[i]);
        }
        for (int i = 0; i < tokaTaulu.length; i++) {
            yhdiste.lisaa(tokaTaulu[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko eka, IntJoukko toka) {
        IntJoukko leikkaus = new IntJoukko();
        int[] ekaTaulu = eka.toIntArray();
        int[] tokaTaulu = toka.toIntArray();
        for (int i = 0; i < ekaTaulu.length; i++) {
            for (int j = 0; j < tokaTaulu.length; j++) {
                if (ekaTaulu[i] == tokaTaulu[j]) {
                    leikkaus.lisaa(tokaTaulu[j]);
                }
            }
        }
        return leikkaus;
    }

    public static IntJoukko erotus(IntJoukko eka, IntJoukko toka) {
        IntJoukko erotus = kopioiJoukko(eka);

        for (int i = 0; i < toka.mahtavuus(); i++) {
            erotus.poista(toka.toIntArray()[i]);
        }
        return erotus;
    }

    private void kasvataKokoa() {
        int vanha[] = lukujono;
        lukujono = new int[this.lukujono.length + OLETUSKASVATUS];
        kopioiTaulukko(vanha, lukujono);
    }

    private static IntJoukko kopioiJoukko(IntJoukko vanha) {
        IntJoukko uusi = new IntJoukko();
        for (int i = 0; i < vanha.mahtavuus(); i++) {
            uusi.lisaa(vanha.toIntArray()[i]);
        }
        return uusi;
    }
}
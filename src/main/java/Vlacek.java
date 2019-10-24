import java.util.LinkedList;
import java.util.List;

public class Vlacek {

    private Vagonek lokomotiva = new Vagonek(VagonekType.LOKOMOTIVA);
    private Vagonek posledni = new Vagonek(VagonekType.POSTOVNI);
    private int delka = 2;

    public Vlacek(){
        lokomotiva.setNasledujici(posledni);
        lokomotiva.setUmisteni(1);
        posledni.setPredchozi(lokomotiva);
        posledni.setUmisteni(2);
    }

    /**
     * Přidávejte vagonky do vlaku
     * Podmínka je že vagonek první třídy musí být vždy řazen za předchozí vagonek toho typu, pokud žádný takový není je řazen rovnou za lokomotivu
     * vagonek 2 třídy musí být vždy řazen až za poslední vagonek třídy první
     * Poštovní vagonek musí být vždy poslední vagonek lokomotivy
     * Při vkládání vagonku nezapomeňte vagonku přiřadit danou pozici ve vlaku
     * !!!!!!! POZOR !!!!!! pokud přidáváte vagonek jinak než na konec vlaku musíte všem následujícím vagonkům zvýšit jejich umístění - doporučuji si pro tento účel vytvořit privátní metodu
     * @param type
     */
    public void pridatVagonek(VagonekType type) {
        Vagonek newVagonek = new Vagonek(type);
        switch (type) {
                case PRVNI_TRIDA:

                    lokomotiva.getNasledujici().setPredchozi(newVagonek);
                    newVagonek.setNasledujici(lokomotiva.getNasledujici());
                    newVagonek.setPredchozi(lokomotiva);
                    lokomotiva.setNasledujici(newVagonek);
                    setUmisteni();

                    delka++;
                    break;

                case DRUHA_TRIDA:

                    posledni.getPredchozi().setNasledujici(newVagonek);
                    newVagonek.setPredchozi(posledni.getPredchozi());
                    newVagonek.setNasledujici(posledni);
                    posledni.setPredchozi(newVagonek);
                    setUmisteni();

                    delka++;
                    break;
        }
    }
    public void setUmisteni() {
        Vagonek zaLokomotivou = lokomotiva.getNasledujici();
        for (int i = 0; i < delka; i++) {
            zaLokomotivou.setUmisteni(zaLokomotivou.getPredchozi().getUmisteni() + 1);
            zaLokomotivou = zaLokomotivou.getNasledujici();

        }
    }

        public Vagonek getVagonekByIndex(int index) {
            int i = 1;
            Vagonek atIndex = lokomotiva;
            while(i < index) {
                atIndex = atIndex.getNasledujici();
                i++;
            }
            return atIndex;
        }


    /**
     * Touto metodou si můžete vrátit poslední vagonek daného typu
     * Pokud tedy budu chtít vrátit vagonek typu lokomotiva dostanu hned první vagonek
     * @param type
     * @return
     */
    public Vagonek getLastVagonekByType(VagonekType type) {
        return null;
    }

    /**
     * Tato funkce přidá jídelní vagonek za poslední vagonek první třídy, pokud jídelní vagonek za vagonkem první třídy již existuje
     * tak se další vagonek přidá nejblíže středu vagonků druhé třídy
     * tzn: pokud budu mít č osobních vagonků tak zařadím jídelní vagonek za 2 osobní vagónek
     * pokud budu mít osobních vagonků 5 zařadím jídelní vagonek za 3 osobní vagonek
     */
    public void pridatJidelniVagonek() {
        Vagonek jidelni = new Vagonek(VagonekType.JIDELNI);
        lokomotiva.getNasledujici().setPredchozi(lokomotiva);
        jidelni.setPredchozi(lokomotiva);
        jidelni.setNasledujici(lokomotiva.getPredchozi());

    }

    /**
     * Funkce vrátí počet vagonků daného typu
     * Dobré využití se najde v metodě @method(addJidelniVagonek)
     * @param type
     * @return
     */
    public int getDelkaByType(VagonekType type) {
        switch (type){
            case PRVNI_TRIDA:

        }

        return 0;
    }

    /**
     * Hledejte jidelni vagonky
     * @return
     */
    public List<Vagonek> getJidelniVozy( ) {
        List<Vagonek> jidelniVozy = new LinkedList<>();
        Vagonek jidelni = new Vagonek(VagonekType.JIDELNI);
        jidelniVozy.indexOf(jidelni);
        return jidelniVozy;
    }

    /**
     * Odebere poslední vagonek daného typu
     * !!!! POZOR !!!!! pokud odebíráme z prostředku vlaku musíme zbývající vagonky projít a snížit jejich umístění ve vlaku
     * @param type
     */
    public void odebratPosledniVagonekByType(VagonekType type) {
        switch (type) {
            case PRVNI_TRIDA:
                posledni = posledni.getPredchozi();
                posledni.setNasledujici(null);
                setUmisteni();
                delka--;
                break;

        }

    }

    public int getDelka() {

        return delka;
    }
}

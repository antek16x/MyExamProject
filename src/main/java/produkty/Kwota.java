package produkty;

public class Kwota implements Comparable<Kwota> {
    private int zlote;
    private int grosze;

    public Kwota(){}

    public Kwota(int zlote, int grosze){
        if(zlote>=0 && grosze >=0) {
            this.zlote = zlote;
            this.grosze = grosze;

        }else
            throw new IllegalArgumentException();
    }

    public Kwota(Kwota kwota){
        this.zlote = kwota.zlote;
        this.grosze = kwota.grosze;
    }

    public int getZlote() {
        return zlote;
    }

    public int getGrosze() {
        return grosze;
    }

    public Kwota add(Kwota kwota){

        int zl = this.zlote + kwota.zlote;
        int gr = this.grosze + kwota.grosze;
        if (gr>=100){
            zl++;
            gr = gr - 100;
        }
        Kwota wynik = new Kwota(zl, gr);
        return wynik;
    }

    public Kwota subtract(Kwota kwota) {
        int gr1 = this.zlote*100 + this.grosze;
        int gr2 = kwota.zlote*100 + kwota.grosze;
        if (gr1 >= gr2){
            gr1= gr1-gr2;
            return new Kwota(gr1/100, gr1%100);
        }
        else throw new IllegalArgumentException("nie można wykonać odejmowania");
    }

    public Kwota multiply(int i){
        if(i<0){
            throw new IllegalArgumentException();
        }else{
            int wynik = (this.zlote * 100 + this.grosze) * i;
            return new Kwota(wynik/100, wynik%100);
        }
    }

    public String toString(){
        if (grosze>9)
            return zlote+","+grosze;
        else
            return zlote + ",0"+grosze;
    }


    public int compareTo(Kwota o) {
        int groszeThis = this.grosze + this.zlote * 100;
        int groszeO = o.grosze + o.zlote * 100;
        if (groszeThis < groszeO) return -1;
        if (groszeThis == groszeO) return 0;
        return 1;
    }
}

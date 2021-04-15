package produkty;

import javax.persistence.*;

@Entity
public class Produkt {


    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 30)
    private String nazwa;

    @Column(scale = 8, precision = 0)
    private Integer cenaGr;

    @Transient
    private Kwota cena;

    @Column(precision = 5, scale = 0)
    private Integer liczbaSztuk; //w magazynie

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Kwota getCena() {
        return new Kwota((int) (cenaGr / 100), (int) (cenaGr % 100));
    }

    public void setCena(Kwota cena) {
        this.cena = cena;
        if (cena != null) {
            this.cenaGr = cena.getZlote() * 100 + cena.getGrosze();
        } else {
            this.cenaGr = 0;
        }
    }

    public Integer getLiczbaSztuk() {
        return liczbaSztuk;
    }

    public void setLiczbaSztuk(Integer liczbaSztuk) {
        this.liczbaSztuk = liczbaSztuk;
    }

    //================================================

    @OneToOne
    private Dzial dzial;

    public Dzial getDzial() {
        return dzial;
    }

    public void setDzial(Dzial dzial) {
        this.dzial = dzial;
    }
}

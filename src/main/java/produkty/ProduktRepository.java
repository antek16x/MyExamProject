package produkty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProduktRepository extends JpaRepository<Produkt, Long> {
    List<Produkt> findByCenaGrBetween(Integer cenaOd, Integer cenaDo);

    List<Produkt> findByLiczbaSztukGreaterThan(Integer liczba);

    List<Produkt> findByNazwaStartingWith(String poczatek);

    @Query(value = "SELECT * FROM produkt inner join dzial on produkt.dzial_kod = dzial.kod where dzial.nazwa = ?1",
            nativeQuery = true)
    List<Produkt> findAllByDzialy(String nazwaDzialu);

    List<Produkt> findAllByNazwa(String nazwa);

    @Query(value = "SELECT * FROM produkt inner join dzial on produkt.dzial_kod = dzial.kod where kod = ?1 order by dzial.nazwa",
            nativeQuery = true)
    List<Produkt> findAllByKodDzialu(Integer kod);
}

package produkty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Controller
public class ProduktKontroler {

    @Autowired
    private ProduktRepository produktRepository;

    @Autowired
    private DzialRepository dzialRepository;

    @RequestMapping("/produkty")
    @ResponseBody
    public List<Produkt> getAll(@RequestParam(name = "nazwa", required = false) String nazwa,
                                @RequestParam(name = "dzial", required = false) Integer kod) {
        if (nazwa == null && kod == null) {
            return produktRepository.findAll();
        } else if (nazwa != null && kod == null) {
            return produktRepository.findAllByNazwa(nazwa);
        } else {
            return produktRepository.findAllByKodDzialu(kod);
        }
    }

    @RequestMapping("produkty/{id}")
    @ResponseBody
    public Produkt getOne(@PathVariable("id") Long id) {
        return produktRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/produkty", method = RequestMethod.POST)
    @ResponseBody
    public Produkt create(@RequestBody Produkt produkt) {
        produkt.setId(null);
        return produktRepository.save(produkt);
    }

    @RequestMapping(value = "produkty/{id}", method = PUT)
    @ResponseBody
    public Produkt updateOrCreatePut(@PathVariable("id") Long id,
                                     @RequestBody Produkt produkt) {
        produkt.setId(id);
        return produktRepository.save(produkt);
    }

    @RequestMapping(value = "produkty/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public Produkt updateOrCreatePatch(@PathVariable("id") Long id,
                                       @RequestBody Produkt produkt) {
        Produkt old = produktRepository.getOne(id);
        if (old == null) {
            return produktRepository.save(produkt);
        }
        if (produkt.getNazwa() != null) old.setNazwa(produkt.getNazwa());
        if (produkt.getCena() != null) old.setCena(produkt.getCena());
        if (produkt.getLiczbaSztuk() != null) old.setLiczbaSztuk((produkt.getLiczbaSztuk()));
        return produktRepository.save(old);
    }

    @RequestMapping(value = "produkty/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        produktRepository.deleteById(id);
    }

    @RequestMapping("produkty/cenaZZakresu/{cenaGrOd}/{cenaGrDo}")
    @ResponseBody
    public List<Produkt> cenaOdDo(@PathVariable("cenaGrOd") Integer cenaOd,
                                  @PathVariable("cenaGrDo") Integer cenaDo) {
        return produktRepository.findByCenaGrBetween(cenaOd, cenaDo);
    }

    @RequestMapping("produkty/sztukWiecejNiz/{liczbaSztuk}")
    @ResponseBody
    public List<Produkt> liczbaSztukGreaterThan(@PathVariable("liczbaSztuk")
                                                        Integer liczba) {
        return produktRepository.findByLiczbaSztukGreaterThan(liczba);
    }

    @RequestMapping("produkty/nazwaOd/{poczatekNazwy}")
    @ResponseBody
    public List<Produkt> produktyZaczynajaceSieOd(@PathVariable("poczatekNazwy")
                                                          String poczatek) {
        return produktRepository.findByNazwaStartingWith(poczatek);
    }


    @RequestMapping("produkty/dzial/{nazwaDzialu}")
    @ResponseBody
    public List<Produkt> pobierzZDzialu(@PathVariable("nazwaDzialu") String nazwaDzialu) {
        return produktRepository.findAllByDzialy(nazwaDzialu);
    }

    @RequestMapping(value = "/produkty/{id}/dzial/{kodDzialu}", method = PUT)
    @ResponseBody
    public Produkt setDzial(@PathVariable("id") Long id,
                            @PathVariable("kodDzialu") Long kodDzialu) {
        Produkt old = produktRepository.getOne(id);
        old.setDzial(dzialRepository.getOne(kodDzialu));
        return produktRepository.save(old);
    }

    @RequestMapping(value = "produkty/{id}/dzial", method = DELETE)
    @ResponseBody
    public void deleteDzial(@PathVariable("id") Long id) {
        Produkt produkt = produktRepository.getOne(id);
        produkt.setDzial(null);
        produktRepository.save(produkt);
    }


}

-- tutaj trzeba dodać instrukcje wstawiające wiersze do tabeli dzial
insert into dzial
values(1, 'art_szkolne');
insert into dzial
values(2, 'art_papiernicze');


-- po dodaniu działów, należy zmodyfikować instrukcje podane niżej,
-- przyporządkowując niektórym produktom działy
insert into produkt
values(hibernate_sequence.nextval, 299, 20, 'olowek', 1);
insert into produkt
values(hibernate_sequence.nextval, 250, 50, 'linijka', null);
insert into produkt
values(hibernate_sequence.nextval, 400, 20, 'zeszyt', 2);
insert into produkt
values(hibernate_sequence.nextval, 3500, 40, 'pioro', 1);

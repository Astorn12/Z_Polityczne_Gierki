//import com.sun.java.util.jar.pack.Package;


import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by osiza on 23.03.2017.
 */
public class Ekran extends Applet implements MouseListener, MouseMotionListener {
    private int najwazniejsza = 0;
    int a,b;
    int x, y;//tylko do kliknięcia USUNĄĆ PÓŻNIEJ
    Rectangle dalej;
    Rectangle wczesniej;
    Rectangle walka;
    Rectangle kolekcja;
    Rectangle sklep;
    Rectangle ustawienia;
    Rectangle karty;
    Rectangle talia;
    Rectangle wszystkie_karty;
    Rectangle powrot;
    int flaga_walka = 0, flaga_sklep = 0, flaga_kolekcja = 0, flaga_ustawienia = 0, flaga_dalej = 0, flaga_wczesniej;// flagi trafienia w kwadrat
    int flaga_zasobu_przydzialu_ekranu = 0, flaga_kart = 0;
    ListaKart lista_tali = new ListaKart();
    ListaKart lista_kolekcji = new ListaKart();
    // List<Integer>  lista_levele= new ArrayList<>();
    ListaKart lista_wszystkie = new ListaKart();
    ListaKart lista_walki = new ListaKart();
    ListaKart lista_prezydencka=new ListaKart();
    ListaKart lista_komputer = new ListaKart();
    int przelicznik = 0;
    String sciezka = "C:\\#JAVA_M\\#Zadoriada\\";
    String plik_systemowy = "Plik_systemowy.txt";
    String plik_wszystkie_karty = "Wszystkie_karty.txt";
    String plik_uzytkownicy = "Plik_uzytkownicy.txt";
    String plik_kolekcja = "Kolekcja.txt";
    String plik_talia = "Talia.txt";
    String plik_talia_prezydencka="Talia_prezydencka.txt";
    String nazwa_uzytkownika;
    Prostokat tmp;
    int in_karty = 0;
    int karty_wybor;
    int licznik_tmp;
    Uzytkownik uzytkownik = new Uzytkownik();
    int flaga_wiodaca = 0;
    List<Kolekcja_tmp> kolekcja_tmp = new LinkedList<>();
    List<Inti> talia_tmp = new ArrayList<>(6);
    List<Uzytkownik> uzytkownik_tmp = new ArrayList<>(1);
    Random random;
    int flaga_rozpoczecie_walki;
    Karta smok = new Karta(8000, 200, "Rzadka", "Presez.jpg", "Kamienny Smok", 16);
    List<Karta> tab_walka_smok = new LinkedList<>();
    int flaga_podswietlenia = -1;
    List<PoleKarty> smoki = new ArrayList<>(1);
    List<PoleKarty> talia_smoki = new ArrayList<>(4);
    // Pojedynek smokczy_pojedynek=new Pojedynek();
    int flaga_powtorzenie = 0;
    int flaga_repaint_one_1 = 0;
    int flaga_repaint_one_2 = 0;
    int flaga_zajecia = 0;
    int flaga_repaint_koniec = 0;
    int flaga_repaintownie = 0;
    int flaga_przegranej = 0;
    int flaga_dodaj = -1;
    int flaga_usun = -1;
    int flaga_uzycia_wszystkich_kart = 0;
    Walka walkas;
    int flaga_parlamentarne_potyczki=0;
    int flaga_walki_prezydenckie=0;
    Image sejm;
    int flaga_sprzedaj=0;
    int flaga_dodsprzed=0;
    int flaga_sprzedajemy=0;
    int flaga_walka_o_senat=0;
    public void init() {
        /*TWORZENIE NAZW PLIKÓW NA PODSTAWIE SCIEZKI-daje nam to możliwość szybkij zmiany miejsca przechowywania naszych plików,
                                                        albo nawet pobrania tego miejsca od użytkownika*/
        plik_wszystkie_karty = sciezka + plik_wszystkie_karty;
        plik_systemowy = sciezka + plik_systemowy;
        plik_kolekcja = sciezka + plik_kolekcja;
        plik_uzytkownicy = sciezka + plik_uzytkownicy;
        plik_talia = sciezka + plik_talia;
        plik_talia_prezydencka=sciezka+plik_talia_prezydencka;


/*USTAWIANIE TLA I WSKAŹNIKA NA OBSLUGE MYSZKI*/
        addMouseListener(this);
        addMouseMotionListener(this);
        setBackground(Color.red);
       sejm= new ImageIcon(this.getClass().getResource("/Foto/sejm2.jpg")).getImage();

        /*ZCZYTYWANIE INFORMACI O UZYTKOWNIKU*/
        zczytywanie_z_pliku(plik_uzytkownicy, ',', uzytkownik_tmp, Uzytkownik.class);
        uzytkownik.nazwa = uzytkownik_tmp.get(0).nazwa;
        uzytkownik.level = uzytkownik_tmp.get(0).level;
        uzytkownik.pieniadze = uzytkownik_tmp.get(0).pieniadze;
        uzytkownik.level_prezydencki=uzytkownik_tmp.get(0).level_prezydencki;
        uzytkownik.stolki=uzytkownik_tmp.get(0).stolki;
                    /*ZCZYTYWANIE INFORMACJI O UZYTKOWNIKU*/

        /* WCZYTYWANIE WSZYSTKICH KART DO LISTY*/
        zczytywanie_z_pliku(plik_wszystkie_karty, ',', lista_wszystkie.lista, Karta.class);
        /*WCZYTYWANIE NAZW KART KTÓRE GRACZ POSIADA*/
        zczytywanie_z_pliku(plik_kolekcja, ',', kolekcja_tmp, Kolekcja_tmp.class);
        /*UZUPELNIANIA LISTY KART GRACZA NA PODSTAWIE NAZW I LISTY CALEJ KOLEKCJI*/
        int j = 0;
        for (int i = 0; i < kolekcja_tmp.size(); i++) {
            j = 0;
            while (j < lista_wszystkie.getSize() && !(kolekcja_tmp.get(i).nazwa_karty.equals(lista_wszystkie.get(j).nazwa_karty))) {
                j++;
            }
            if (j < lista_wszystkie.getSize()) {
                lista_kolekcji.dodaj(lista_wszystkie.get(j));
            }

        }
        /*WCZYTYWANIE TALI*/


        // zczytywanie_z_pliku(plik_talia,',',  lista_tali.lista,Karta.class);
        zczytywanie_z_pliku(plik_talia, ' ', talia_tmp, Inti.class);
        for (int ii = 0; ii < talia_tmp.size(); ii++) {
           // System.out.println(lista_kolekcji.get(talia_tmp.get(ii).liczba - 1).nazwa_karty);
        }

        for (int ii = 0; ii < talia_tmp.size(); ii++) {
            System.out.println(lista_kolekcji.size());
            System.out.println(ii);
            lista_tali.dodaj(lista_kolekcji.get(talia_tmp.get(ii).liczba-1 ));// tutaj coś zmieniłem i nie wiem dlaczego
        }


        /* ZCZYTYWANIE TALI PREZYDENTOW */
        List<Nazwowy> tmp_nazwy_prezydentow=new LinkedList<>();
        zczytywanie_z_pliku(plik_talia_prezydencka,',',tmp_nazwy_prezydentow,Nazwowy.class);
        int flaga_identycznosci=0;
        int licznik=0;

        for(int i=0;i<tmp_nazwy_prezydentow.size();i++)
        {

            while(licznik<lista_wszystkie.size()&&flaga_identycznosci==0)
            {

                if(tmp_nazwy_prezydentow.get(i).string.equals(lista_wszystkie.get(licznik).nazwa_karty))
                {
                    lista_prezydencka.lista.add(lista_wszystkie.get(licznik).clone());
                    flaga_identycznosci=1;
                }
                licznik++;
            }
            licznik=0;
            flaga_identycznosci=0;
        }
        System.out.println(tmp_nazwy_prezydentow.size());
        System.out.println(lista_prezydencka.size());


/*KONIEC ZCZYTYWANIA TALI PREZYDENTOW*/
    }




    public void paint(Graphics g) {
        g.drawImage(sejm,0,0,getWidth(),getHeight(),this);

        g.drawString("Kliknięcie: " + x + " / " + y, 10, 300);
        switch (flaga_wiodaca) {

/* EKRAN GLOWNY*/
            case 0:

                Font tytul_czcionka = new Font("Serif", Font.BOLD, getWidth() / 24);//odpowiada 80 na całym ekranie
                //Font tytul_czcionka =new Font("Serif",Font.BOLD,80);

                String tytul = "Polityczne gierki";
                walka = new Rectangle((getWidth() * 1) / 10, (getHeight() * 3) / 10, (getWidth() * 3) / 10, (getHeight() * 2) / 10);
                kolekcja = new Rectangle((getWidth() * 6) / 10, (getHeight() * 3) / 10, (getWidth() * 3) / 10, (getHeight() * 2) / 10);
                sklep = new Rectangle((getWidth() * 1) / 10, (getHeight() * 6) / 10, (getWidth() * 3) / 10, (getHeight() * 2) / 10);
                ustawienia = new Rectangle((getWidth() * 6) / 10, (getHeight() * 6) / 10, (getWidth() * 3) / 10, (getHeight() * 2) / 10);
                                    /*INICJALIZACJA^^*/


                                    /*EKRAN STARTOWY vv*/


                int tmp_w, tmp_s;

                g.setColor(Color.yellow);
                //g.fillRect((getWidth() / 3), getHeight() / 18, getWidth() / 3, getHeight() / 12);//kwadrat tytułu
                g.setColor(Color.BLACK);

                g.drawRect((getWidth() / 3), getHeight() / 18, getWidth() / 3, getHeight() / 12);//kwadrat tytułu

                g.setFont(tytul_czcionka);

                //  g.clearRect(0, 0, getSize().width, getSize().height);
                g.drawString(tytul, (int) ((getWidth() / 2) - g.getFontMetrics().stringWidth(tytul) / 2), getHeight() / 8);//wypisanie tytyułu


                g.setColor(Color.blue);
                // g.fillRect(100,100,100,100);
                g.fillRect((getWidth() * 1) / 10, (getHeight() * 3) / 10, (getWidth() * 3) / 10, (getHeight() * 2) / 10);
                g.fillRect((getWidth() * 6) / 10, (getHeight() * 3) / 10, (getWidth() * 3) / 10, (getHeight() * 2) / 10);
                g.fillRect((getWidth() * 1) / 10, (getHeight() * 6) / 10, (getWidth() * 3) / 10, (getHeight() * 2) / 10);
                g.fillRect((getWidth() * 6) / 10, (getHeight() * 6) / 10, (getWidth() * 3) / 10, (getHeight() * 2) / 10);

                g.setColor(Color.black);
                g.drawString("Walka", (int) ((getWidth() * 2.5) / 10 - (g.getFontMetrics().stringWidth("Walka")) / 2), (getHeight() * 4) / 10);
                g.drawString("Karty", (int) ((getWidth() * 7.5) / 10 - (g.getFontMetrics().stringWidth("Kaarty")) / 2), (getHeight() * 4) / 10);
                g.drawString("Sklep", (int) ((getWidth() * 2.5) / 10 - (g.getFontMetrics().stringWidth("Sklep")) / 2), (getHeight() * 7) / 10);
                g.drawString("Ustawienia", (int) ((getWidth() * 7.5) / 10 - (g.getFontMetrics().stringWidth("Ustawienia")) / 2), (getHeight() * 7) / 10);


                //g.clearRect(0, 0, getSize().width, getSize().height);
                break;
/* EKRAN GLOWNY*/
/*EKRAN KRATY*/


            case 2:

                Font moc = new Font("Serif", Font.BOLD, getWidth() / 40);
                g.setFont(moc);
                g.drawImage(sejm,0,0,getWidth(),getHeight(),this);
                //g.clearRect(0, 0, getWidth(), getHeight());

                g.setColor(Color.blue);
                powrot = new Rectangle((getWidth() * 9) / 10, (getHeight() * 1) / 10, getWidth() / 13, getHeight() / 13);
                tmp = new Prostokat(powrot);
                tmp.paint(g);
                //tmp.paint(g, "Powrót");
                g.setColor(Color.yellow);
                karty = new Rectangle((getWidth() * 1) / 5, (getHeight() * 2) / 9, (getWidth() * 2) / 5, (getHeight() * 1) / 9);
                talia = new Rectangle((getWidth() * 1) / 5, (getHeight() * 4) / 9, (getWidth() * 2) / 5, (getHeight() * 1) / 9);
                wszystkie_karty = new Rectangle((getWidth() * 1) / 5, (getHeight() * 6) / 9, (getWidth() * 2) / 5, (getHeight() * 1) / 9);
                tmp = new Prostokat(karty);


                //g.setColor(Color.black);
                tmp.paint(g);
                tmp.paint(g, "Kolekcja");
                g.setColor(Color.yellow);
                tmp = new Prostokat(talia);
                //g.setColor(Color.black);
                tmp.paint(g);
                tmp.paint(g, "Talia");
                tmp = new Prostokat(wszystkie_karty);
                g.setColor(Color.yellow);
                tmp.paint(g);
                tmp.paint(g, "Wszystkie karty");
                break;


            case 21:
            case 22:
            case 23:

                Font domyslna = new Font("Serif", Font.BOLD, getWidth() / 100);
                dalej = new Rectangle((getWidth() * 9) / 10, (getHeight() * 9) / 10, getWidth() / 20, getHeight() / 20);
                wczesniej = new Rectangle((getWidth() * 9) / 10, (int) ((getHeight() * 8.25) / 10), getWidth() / 20, getHeight() / 20);
                //g.clearRect(0, 0, getWidth(), getHeight());
                g.drawImage(sejm,0,0,getWidth(),getHeight(),this);

                g.drawString(getHeight() + "" + getWidth(), 100, 100);
                Prostokat dalej1 = new Prostokat(dalej);
                Prostokat wczesniej1 = new Prostokat(wczesniej);
                g.setColor(Color.white);
                dalej1.paint(g);
                wczesniej1.paint(g);

                g.setFont(domyslna);
               dalej1.paint(g, "Dalej");
                wczesniej1.paint(g, "Wczesniej");


                int odleglosc_szerokosc = getWidth() / 10, odleglosc_wysokosc = getHeight() / 5;
                ListaKart lista_kart_tmp;
/*decyzja ktora liste chcemy wyswietlic*/
                List<PoleKarty> pola_widziane = new LinkedList<>();
                if (flaga_wiodaca == 22) lista_kart_tmp = lista_tali;
                else if (flaga_wiodaca == 21) lista_kart_tmp = lista_kolekcji;
                else if (flaga_wiodaca == 23) lista_kart_tmp = lista_wszystkie;
                else lista_kart_tmp = null;
                przepisywanie_lista_napola(pola_widziane, lista_kart_tmp.lista);


                int i_tmp = 0;
                int n = pola_widziane.size();
                if (flaga_dalej == 1) {
                    if (this.przelicznik + 5 < pola_widziane.size()) {
                        this.przelicznik = this.przelicznik + 5;
                    }
                }

                if (flaga_wczesniej == 1) {
                    if (this.przelicznik > 0) {
                        this.przelicznik = this.przelicznik - 5;
                    }


                }

                int j = 0;
                j = this.przelicznik;
 /*USTALANIE KOORDYNATOW-START*/
                while (j < pola_widziane.size() && j < przelicznik + 10) {

                    pola_widziane.get(j).x = odleglosc_szerokosc;
                    pola_widziane.get(j).y = odleglosc_wysokosc;
                    pola_widziane.get(j).szerokosc = getWidth() / 10;
                    pola_widziane.get(j).wysokosc = getWidth() / 7;
                    pola_widziane.get(j).aktualizuj_kwadrat();
                    // pola_widziane.get(j).karta.paint(g, odleglosc_szerokosc, odleglosc_wysokosc, getWidth(), getHeight());
                    if (flaga_wiodaca == 21) {
                        g.setColor(Color.black);
                        g.setFont(domyslna);
                        //g.drawString(kolekcja_tmp.get(j).level + "", odleglosc_szerokosc + getWidth() / 20 - (int) ((g.getFontMetrics().stringWidth(kolekcja_tmp.get(j).level + "") / 2)), odleglosc_wysokosc);

                    }
                    odleglosc_szerokosc = odleglosc_szerokosc + ((getWidth() * 3) / 20);

                    i_tmp++;


                    if (i_tmp == 5) {
                        odleglosc_szerokosc = getWidth() / 10;
                        odleglosc_wysokosc = odleglosc_wysokosc + (getHeight() * 4) / 10;
                        i_tmp = 0;
                    }
                    j++;
                }
                Prostokat pros = new Prostokat(11, 1, 1, 1);
                int ilosc;
                if (przelicznik + 10 >= pola_widziane.size()) ilosc = pola_widziane.size();
                else ilosc = przelicznik + 10;
                for(int i=0;i<pola_widziane.size();i++)
                {
                    pola_widziane.get(i).aktualizuj_przyciski();

                }

                mistrzostwo(g, this.x, this.y, pola_widziane, przelicznik, ilosc, pros, "Dodaj", this);
/*USTALANIE KOORDYNATOW-END*/

                g.drawString("Kliknięcie: " + x + " / " + y, 10, 200);
                break;


/*EKRAN KARTY*/
/*EKRAN WALKA*/
            case 1:
                String tab[] = new String[3];
                tab[0] = "Walka o senat";
                tab[1] = "Parlamentarne potyczki";
                tab[2] = "Elekcja prezydencka";
              //  g.clearRect(0, 0, getWidth(), getHeight());
                g.drawImage(sejm,0,0,getWidth(),getHeight(),this);

                auto_rysowanie_kwadraty(g, 3, 3, 2, 2, 3, 1, getWidth(), getHeight(), this.x, this.y, tab, this);


                break;
            case 3:

                //g.clearRect(0, 0, getWidth(), getHeight());
                g.drawImage(sejm,0,0,getWidth(),getHeight(),this);
               // g.setColor(Color.yellow);
                Prostokat skrzynka=new Prostokat(getWidth()/2,getHeight()/2,getWidth()/4,getHeight()/5);
                //skrzynka.paint(g);
                Image grafa= new ImageIcon(this.getClass().getResource("/Foto/"+"Teczka1.png")).getImage();
                g.drawImage(grafa,getWidth()/2,getHeight()/2,getWidth()/3,getWidth()/5,this);
                if(skrzynka.contains(this.x,this.y)&&this.uzytkownik.pieniadze>100)
                {
                    List<String> l=new LinkedList<>();
                    int rare=0;
                    for(int i=0;i<this.lista_wszystkie.size();i++)
                    {

                        switch (this.lista_wszystkie.get(i).rzadkosc)
                        {
                            case "Pospolita":
                                rare=32;
                                break;
                            case "Niepospolita":
                                rare=16;
                                break;
                            case "Unikat":
                                rare=8;
                                break;
                            case "Rzadka":
                                rare=4;
                                break;
                            case"Legendarna":
                                rare=2;
                                break;
                            case "Mityczna":
                                rare=1;
                                break;
                        }



                        for(int x=0;x<rare;x++)
                        {
                            l.add(this.lista_wszystkie.get(i).nazwa_karty);
                        }



                    }
                    Random random= new Random();
                    String wylosowana=l.get(random.nextInt(l.size()));
                    PoleKarty karta=new PoleKarty((int)(getWidth()/1.63),(int)(getHeight()/8),getWidth()/10,getHeight()/7);
                    for(int i=0;i<this.lista_wszystkie.getSize();i++)
                    {
                        if(this.lista_wszystkie.get(i).nazwa_karty.equals(wylosowana))
                        {
                        karta.setKarta(this.lista_wszystkie.get(i).clone());
                        karta.aktualizuj_przyciski();
                        }
                       // System.out.println(karta.getKarta().nazwa_karty);
                    }

                    karta.wyswietl(g);
                    uzytkownik.pieniadze-=100;
                    uzytkownik_tmp.get(0).pieniadze-=100;
                    Kolekcja_tmp tmp=new Kolekcja_tmp();
                    tmp.level=0;
                    tmp.nazwa_karty=karta.getKarta().nazwa_karty;
                    lista_kolekcji.lista.add(karta.getKarta().clone());
                    kolekcja_tmp.add(tmp);

                }






                //g.drawString("pppppppppppppppppppppppppppppppppppppppp",100,100);
                break;

            case 4:

                //g.clearRect(0, 0, getWidth(), getHeight());
                g.drawImage(sejm,0,0,getWidth(),getHeight(),this);

                String[] kody = new String[1];
                kody[0] = "kody";
                auto_rysowanie_kwadraty(g, 4, 4, 2, 7, 1, 1, getWidth(), getHeight(), this.x, this.y, kody, this);

                break;
            case 41:
                g.drawImage(sejm,0,0,getWidth(),getHeight(),this);
               // g.drawString("tttttttttttttttttttttttttttttttttttttttttttttttt",100,100);
                break;
            case 11:/*WALKA ZE SMOKIEM*/

                /*if (lista_tali.getSize() == 6) {
/*ELEMENTY TWORZONE TYLKO NA POCZATKU KAZDEJ WALKI*/
                    if (flaga_rozpoczecie_walki == 0) {


                        tab_walka_smok.clear();

                        for (int i = 0; i < 6; i++) {
                            Karta kar = lista_tali.get(i).clone();
                            tab_walka_smok.add(kar);
                        }

                        int los;
                        lista_walki.lista.clear();
                        for (int i = 0; i < 4; i++) {
                            Random random = new Random();
                            ;
                            los = random.nextInt(6 - i);

                            lista_walki.dodaj(tab_walka_smok.get(los).clone());
                            tab_walka_smok.remove(los);
                        }


                        List<Karta> temp = new LinkedList<>();
                        temp.add(smok.clone());
                        przepisywanie_lista_napola(smoki, temp);


                        przepisywanie_lista_napola(talia_smoki, lista_walki.lista);

                        flaga_rozpoczecie_walki = 1;
                    }

/*KOD ROZGRYWKI*/
                   /* int flaga_tmp = 0;
                    int wskaz = 0;
                    int licznik = 0;*/

   /* if(flaga_uzycia_wszystkich_kart==1)
    {
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flaga_uzycia_wszystkich_kart=0;
        flaga_repaint_koniec=1;
    }*/
                   /* autorysowanie_pole(g, smoki, talia_smoki, 3, 3, 1, 6, 1, smoki.size(), getWidth(), getHeight(), this.x, this.y, this);
                    autorysowanie_pole(g, talia_smoki, smoki, 2, 2, 6, 1, 1, talia_smoki.size(), getWidth(), getHeight(), this.x, this.y, this);


    /*Sprawdzanie czy uzylismy juz wszystkich kart-START
                    int flaga = 0;
                    for (int i = 0; i < talia_smoki.size(); i++) {
                        if (talia_smoki.get(i).wskaznik != 2) flaga = 1;
                    }

                    if (flaga == 0) {
                        //flaga_repaint_koniec=1;
                        flaga_uzycia_wszystkich_kart = 1;

                        for (int i = 0; i < talia_smoki.size(); i++) {

                            talia_smoki.get(i).wskaznik = 1;
                        }
                    }
/*Sprawdzanie czy uzylismy juz wszystkich kart-END

                    Font moc1 = new Font("Serif", Font.BOLD, getWidth() / 10);

                    while (licznik < (talia_smoki.size()) && wskaz == 0) {

                        if (talia_smoki.get(licznik).wskaznik == -1)
                            wskaz = -1;
                        licznik++;
                    }

                    if (licznik > 0) licznik = licznik - 1;

                    int flaga_p = 0;


                    if (wskaz == -1 && smoki.get(0).wskaznik == -1 && talia_smoki.get(licznik).wskaznik == -1) {
                        smoki.get(0).getKarta().hp = smoki.get(0).getKarta().hp - talia_smoki.get(licznik).getKarta().moc;
                        smoki.get(0).wskaznik = smoki.get(0).wskaznik * -1;
//        talia_smoki.get(licznik).wskaznik = talia_smoki.get(licznik).wskaznik * -1;
                        talia_smoki.get(licznik).wskaznik = 2;

                        flaga_repaintownie = 1;
                        Random atak_smoka = new Random();

                        int smoczy_wybor;
                        if (talia_smoki.size() == 1) smoczy_wybor = 0;
                        else smoczy_wybor = atak_smoka.nextInt(talia_smoki.size() - 1);

                        talia_smoki.get(smoczy_wybor).getKarta().hp = talia_smoki.get(smoczy_wybor).getKarta().hp - smoki.get(0).getKarta().moc;
                        if (talia_smoki.get(smoczy_wybor).getKarta().hp < 0) {
                            lista_walki.lista.remove(smoczy_wybor);
                            talia_smoki.remove(smoczy_wybor);
                            if (lista_walki.lista.size() == 0) flaga_przegranej = 1;

                        }

                    }

                    if (flaga_repaintownie == 1) {
                        flaga_repaintownie = 0;
                        this.x = 0;
                        this.y = 0;

                        try {
                            sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        repaint();
                    }

                    if (flaga_przegranej == 1) {
                        g.setFont(moc1);
                        g.setColor(Color.yellow);
                        g.drawString("Przegrałeś :(", (int) (getWidth() / 2), (int) (getHeight() / 2));


                        flaga_rozpoczecie_walki = 0;
                        flaga_wiodaca = flaga_wiodaca / 10;
                        flaga_przegranej = 0;
                        try {
                            sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } else if (smoki.get(0).getKarta().hp <= 0) {


                        g.setFont(moc1);
                        g.setColor(Color.yellow);
                        g.drawString("Wygrałeś :)", (int) (getWidth() / 2), (int) (getHeight() / 2));


                        uzytkownik.pieniadze = uzytkownik.pieniadze + 30;
                        flaga_wiodaca = flaga_wiodaca / 10;
                        try {
                            sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        flaga_rozpoczecie_walki = 0;
                    }
                    licznik = 0;


                } else {
                    Font fon = new Font("Serif", Font.BOLD, getWidth() / 50);
                    g.setFont(fon);
                    g.drawString("Twoja talia powinna mieć 6 kart", (int) (getWidth() * 2) / 3, getHeight() / 2);
                }*/

                if(flaga_walka_o_senat==0){
                    ListaPoleKarty sojusznik=new ListaPoleKarty();
                    ListaPoleKarty przeciwnik=new ListaPoleKarty();
                    sojusznik.lista_PoleKarty_ustawienia_karta(lista_tali,"Kopia");
                    ListaKart l1=new ListaKart();
                    Random random= new Random();
                    for(int i=0;i<6;i++) {


                        l1.dodaj(lista_kolekcji.get(random.nextInt(lista_kolekcji.size())));
                        //l1.dodaj(lista_kolekcji.get(0));
                        //    l1.dodaj(lista_kolekcji.get(0));
                        //       l1.dodaj(lista_kolekcji.get(0));
                        //l1.dodaj(smok);
                    }
                    przeciwnik.lista_PoleKarty_ustawienia_karta(l1,"Kopia");

                    walkas = new Walka(sojusznik, przeciwnik, this);
                    flaga_walka_o_senat=1;
                }

                walkas.start(4);






                break;
            case 12:

                /*if(flaga_parlamentarne_potyczki==0){
                ListaPoleKarty sojusznik=new ListaPoleKarty();
                ListaPoleKarty przeciwnik=new ListaPoleKarty();
                sojusznik.lista_PoleKarty_ustawienia_karta(lista_tali,"Kopia");
                ListaKart l1=new ListaKart();
                l1.dodaj(lista_kolekcji.get(0));
                //l1.dodaj(lista_kolekcji.get(0));
               //    l1.dodaj(lista_kolekcji.get(0));
             //       l1.dodaj(lista_kolekcji.get(0));
                //l1.dodaj(smok);
                przeciwnik.lista_PoleKarty_ustawienia_karta(l1,"Kopia");

                    walkas = new Walka(sojusznik, przeciwnik, this);
                    flaga_parlamentarne_potyczki=1;
                }

                walkas.start(4);*/
                g.drawImage(sejm,0,0,getWidth(),getHeight(),this);
                if(flaga_parlamentarne_potyczki==0){
                    ListaPoleKarty sojusznik=new ListaPoleKarty();
                    ListaPoleKarty przeciwnik=new ListaPoleKarty();
                    sojusznik.lista_PoleKarty_ustawienia_karta(lista_tali,"Kopia");
                    ListaKart l1=new ListaKart();

                    l1.dodaj(lista_prezydencka.get(uzytkownik.level_prezydencki%lista_prezydencka.size()).clone());
                    //System.out.println(uzytkownik.level_prezydencki%lista_prezydencka.size());
                    // System.out.println(lista_prezydencka.lista.get(1).nazwa_karty);
                    l1.get(0).hp*=uzytkownik.level_prezydencki;
                    l1.get(0).moc*=uzytkownik.level_prezydencki;
                    //l1.dodaj(lista_kolekcji.get(0));
                    //    l1.dodaj(lista_kolekcji.get(0));
                    //       l1.dodaj(lista_kolekcji.get(0));
                    // l1.dodaj(smok);
                    przeciwnik.lista_PoleKarty_ustawienia_karta(l1,"Kopia");

                    walkas = new Walka1(sojusznik, przeciwnik, this);
                    flaga_parlamentarne_potyczki=1;
                }

                walkas.start(4);




                break;
            case 13:
                g.drawImage(sejm,0,0,getWidth(),getHeight(),this);
                if(flaga_walki_prezydenckie==0){
                    ListaPoleKarty sojusznik=new ListaPoleKarty();
                    ListaPoleKarty przeciwnik=new ListaPoleKarty();
                    sojusznik.lista_PoleKarty_ustawienia_karta(lista_tali,"Kopia");
                    ListaKart l1=new ListaKart();

                    l1.dodaj(lista_prezydencka.get(uzytkownik.level_prezydencki%lista_prezydencka.size()).clone());
                    //System.out.println(uzytkownik.level_prezydencki%lista_prezydencka.size());
                   // System.out.println(lista_prezydencka.lista.get(1).nazwa_karty);
                    l1.get(0).hp*=uzytkownik.level_prezydencki;
                    l1.get(0).moc*=uzytkownik.level_prezydencki;
                    //l1.dodaj(lista_kolekcji.get(0));
                    //    l1.dodaj(lista_kolekcji.get(0));
                    //       l1.dodaj(lista_kolekcji.get(0));
                   // l1.dodaj(smok);
                    przeciwnik.lista_PoleKarty_ustawienia_karta(l1,"Kopia");

                    walkas = new Walka(sojusznik, przeciwnik, this);
                    flaga_walki_prezydenckie=1;
                }

                walkas.start(4);


                break;

            default:

/*EKRAN WALKA*/
        }
        if (flaga_wiodaca != 0) {
            Font moc = new Font("Serif", Font.BOLD, getWidth() / 50);
            g.setFont(moc);

            g.setColor(Color.blue);
            powrot = new Rectangle((getWidth() * 9) / 10, (getHeight() * 1) / 10, getWidth() / 13, getHeight() / 13);
            tmp = new Prostokat(powrot);
            tmp = new Prostokat((getWidth() * 9) / 10, (getHeight() * 1) / 10, getWidth() / 13, getHeight() / 13);
            tmp.paint(g);
            tmp.paint(g, "Powrót", 3);
        }

        Font informacje = new Font("Serif", Font.BOLD, (getWidth() / 70) + 5);
        g.setFont(informacje);
        g.drawString(uzytkownik.nazwa, 0, getHeight() / 30);
        g.drawString("Level: " + uzytkownik.level, 0, (getHeight() * 2) / 30);
        g.drawString("Głosy: " + uzytkownik.pieniadze, 0, (getHeight() * 3) / 30);
        g.drawString("Stołki: " + uzytkownik.stolki, 0, (getHeight() * 4) / 30);

        flaga_zajecia = 0;
        if (flaga_repaint_koniec == 1) {
            flaga_repaint_koniec = 0;

            this.repaint();
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        this.x = e.getX();
        this.y = e.getY();
        this.a=e.getX();
        this.b=e.getY();

//flaga_zajecia=0;

        try {
            if (wczesniej.contains(x, y)) {
                flaga_wczesniej = 1;
                flaga_dalej = 0;
                flaga_zajecia = 1;
            }
        } catch (Exception npe) {

        }
        try {
            if (walka.contains(x, y) && flaga_wiodaca == 0) {
                flaga_wiodaca = 1;
                flaga_zajecia = 1;
                ;
            }
        } catch (Exception npe) {

        }

        try {
            if (kolekcja.contains(x, y) && flaga_wiodaca == 0) {
                flaga_wiodaca = 2;
                flaga_zajecia = 1;
            }
        } catch (Exception npe) {

        }
        try {
            if (sklep.contains(x, y) && flaga_wiodaca == 0) {
                flaga_wiodaca = 3;
                flaga_zajecia = 1;
            }
        } catch (Exception npe) {

        }
        try {
            if (ustawienia.contains(x, y) && flaga_wiodaca == 0) {
                flaga_wiodaca = 4;
                flaga_zajecia = 1;
            }
        } catch (Exception npe) {

        }
        try {
            if (dalej.contains(x, y)) {
                flaga_dalej = 1;
                flaga_wczesniej = 0;
                flaga_zajecia = 1;
            }
        } catch (Exception npe) {

        }

        try {
            if (karty.contains(x, y) && flaga_wiodaca == 2) {
                flaga_wiodaca = 21;
                flaga_zajecia = 1;
            }
        } catch (Exception npe) {

        }

        try {
            if (talia.contains(x, y) && flaga_wiodaca == 2) {
                flaga_wiodaca = 22;
                flaga_zajecia = 1;
            }
        } catch (Exception npe) {

        }

        try {
            if (wszystkie_karty.contains(x, y) && flaga_wiodaca == 2) {
                flaga_wiodaca = 23;
                flaga_zajecia = 1;
            }
        } catch (Exception npe) {

        }


        try {
            if (powrot.contains(x, y)) {

                flaga_wiodaca = flaga_wiodaca / 10;
                flaga_zajecia = 1;
                flaga_parlamentarne_potyczki=0;
                flaga_walka_o_senat=0;
                flaga_walki_prezydenckie=0;

            }
        } catch (Exception npe) {

        }


        repaint();

    }

    @Override
    public void destroy() {
/*AKTUALIZOWANIE PLIKU Z URZYTKOWNIKIEM*/
        zapisywanie_do_pliku(plik_uzytkownicy, ',', uzytkownik_tmp, Uzytkownik.class);
/*AKTUALIZOWANIE PLIKU KOLEKCJI*/
        zapisywanie_do_pliku(plik_kolekcja, ',', kolekcja_tmp, Kolekcja_tmp.class);
/*AKTUALIZOWANIE PLIKU ZE WSZYSTKIMI KARTAMI*/
        zapisywanie_do_pliku(plik_wszystkie_karty, ',', lista_wszystkie.lista, Karta.class);
/*AKTUALIZOWANIE PLIKU Z TALIA*/
        zapisywanie_do_pliku(plik_talia, ' ', talia_tmp, Inti.class);
/*KONIEC AKTUALIZOWANIA*/
        super.destroy();
    }

    public static void CreateFile(String path) {
        try {

            File file = new File(path);

            if (file.createNewFile()) {

            } else {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void DeleteFile(String path) {
        try {

            File file = new File(path);

            if (file.delete()) {

            } else {

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static void auto_rysowanie_kwadraty(Graphics g, float lewy, float prawy, float gora, float dol, int wiersze, int kolumny, int width, int height, int iks, int igrek, String[] tab, Ekran ekran) {
        Font moc = new Font("Serif", Font.BOLD, width / 40);
        Prostokat[] lista = new Prostokat[kolumny * wiersze];

        float jednostka_wysokosci = (height * (1 - (gora + dol) / 10)) / (wiersze + ((float) wiersze - 1) / 2);
        float jednostka_szerokosci = (width * (1 - (lewy + prawy) / 10)) / (kolumny + ((float) kolumny - 1) / 2);
        int przesuniecie = 0, obnizenie = 0;
        g.setColor(Color.yellow);
        obnizenie = (int) (height * (gora / 10));

        for (int i = 0; i < wiersze; i++) {
            przesuniecie = (int) ((width * lewy) / 10);
            for (int j = 0; j < kolumny; j++) {

                //g.fillRect(przesuniecie,obnizenie,(int)jednostka_szerokosci,(int)jednostka_wysokosci);
                Prostokat rectangle = new Prostokat(przesuniecie, obnizenie, (int) jednostka_szerokosci, (int) jednostka_wysokosci);
                lista[i * kolumny + j] = rectangle;

                przesuniecie = (int) (przesuniecie + (int) (1.5 * jednostka_szerokosci));


            }
            obnizenie = (int) (obnizenie + (int) (1.5 * jednostka_wysokosci));
        }

        for (int i = 0; i < kolumny * wiersze; i++) {

            g.setFont(moc);
            g.setColor(Color.yellow);
            lista[i].paint(g);
            if (i < tab.length) {
                lista[i].paint(g, tab[i]);
            }
        }
        // int wiodaca_tmp=0,licznik_tmp=0;
        // wiodaca_tmp=ekran.flaga_wiodaca;
        // while(wiodaca_tmp>0)
        //  {
        //   wiodaca_tmp=wiodaca_tmp/10;
        // // licznik_tmp++;
        // }
        int flaszka = 0, i = 0;

        if (ekran.flaga_zajecia == 0) {

            while (i < lista.length && flaszka == 0) {

                if (lista[i].contains(iks, igrek)) {
                    flaszka = 1;

                    ekran.flaga_wiodaca = ekran.flaga_wiodaca * 10 + i + 1;
                    ekran.flaga_zajecia = 1;


                }
                i++;
            }

            if (flaszka == 1) ekran.flaga_repaint_koniec = 1;//ekran.repaint();
//flaszka=0;
        }


    }

    public void autorysowanie_karta(Graphics g, List<Karta> lista, float lewy, float prawy, float gora, float dol, int wiersze, int kolumny, int width, int height, int iks, int igrek, Ekran ekran) {

        List<PoleKarty> pola = new LinkedList<>();

        float licznik_przesuniecia = (width * (1 - (lewy + prawy) / 10) - kolumny * width / 10) / (kolumny - 1);
        float licznik_obnizenia = (height * (1 - (gora + dol) / 10) - wiersze * width / 7) / (wiersze - 1);
        int przesuniecie = 0, obnizenie = 0;
        g.setColor(Color.yellow);
        obnizenie = (int) (height * (gora / 10));

        for (int i = 0; i < wiersze; i++) {
            przesuniecie = (int) ((width * lewy) / 10);
            if (kolumny == 1) przesuniecie = width / 2 - width / 20;
            for (int j = 0; j < kolumny; j++) {
                PoleKarty pole = new PoleKarty(lista.get(i * kolumny + j), przesuniecie, obnizenie, width, height);
                pola.add(pole);
                if (flaga_podswietlenia == i * kolumny + j)
                    lista.get(i * kolumny + j).podswietlenie(g, przesuniecie, obnizenie, width, height, 1);
                else lista.get(i * kolumny + j).paint(g, przesuniecie, obnizenie, width, height);
                przesuniecie = (int) (przesuniecie + (int) (licznik_przesuniecia) + width / 10);
            }
            obnizenie = (int) (obnizenie + (int) (licznik_obnizenia));
        }
    }

    public void autorysowanie_pole(Graphics g, List<PoleKarty> napole, List<PoleKarty> przeciwnik, float lewy, float prawy, float gora, float dol, int wiersze, int kolumny, int width, int height, int iks, int igrek, Ekran ekran) {

        float licznik_przesuniecia = (width * (1 - (lewy + prawy) / 10) - kolumny * width / 10) / (kolumny - 1);
        float licznik_obnizenia = (height * (1 - (gora + dol) / 10) - wiersze * width / 7) / (wiersze - 1);
        int przesuniecie = 0, obnizenie = 0;
        g.setColor(Color.yellow);
        obnizenie = (int) (height * (gora / 10));


        for (int j = 0; j < napole.size(); j++) {

            int flagg = 0;


            if ((napole.get(j).rectangle.contains(iks, igrek) && napole.get(j).wskaznik != 2) && !(napole.get(j).umiejetnosc.contains(iks, igrek))) {

                for (int a = 0; a < napole.size(); a++) {
                    if (napole.get(a).wskaznik == -1 && a != j)
                        flagg = 1;

                }
                if (flagg == 0) {
                    napole.get(j).wskaznik = napole.get(j).wskaznik * (-1);
                    flagg = 0;
                }

                if (flaga_powtorzenie == 0) {
                    iks = 0;
                    igrek = 0;
                    flaga_powtorzenie = 1;
                    // repaint();
                }
            } else if (napole.get(j).wskaznik != 2 && !(napole.get(j).umiejetnosc.contains(iks, igrek))) {

                napole.get(j).getKarta().aktywuj_umiejetnosc(napole, przeciwnik);
            }
        }



        /*POCZĄTEK USTALANIA PÓL*/
        for (int i = 0; i < wiersze; i++) {
            przesuniecie = (int) ((width * lewy) / 10);
            if (kolumny == 1) przesuniecie = width / 2 - width / 20;
            for (int j = 0; j < kolumny; j++) {

                napole.get(i * kolumny + j).x = przesuniecie;
                napole.get(i * kolumny + j).y = obnizenie;
                napole.get(i * kolumny + j).szerokosc = width / 10;
                napole.get(i * kolumny + j).wysokosc = width / 7;
                napole.get(i * kolumny + j).aktualizuj_kwadrat();

                if (napole.get(i * kolumny + j).wskaznik == -1)
                    napole.get(i * kolumny + j).getKarta().podswietlenie(g,  napole.get(i * kolumny + j).x, obnizenie, width, height, 1);
                if (napole.get(i * kolumny + j).wskaznik == 2)
                    napole.get(i * kolumny + j).getKarta().podswietlenie(g, przesuniecie, obnizenie, width, height, 2);
                else napole.get(i * kolumny + j).getKarta().paint(g, przesuniecie, obnizenie, width, height);
                przesuniecie = (int) (przesuniecie + (int) (licznik_przesuniecia) + width / 10);


            }
            obnizenie = (int) (obnizenie + (int) (licznik_obnizenia)+width/7);
        }/*KONIEC USTALANIA PÓL*/

    }

    public void przepisywanie_lista_napola(List<PoleKarty> pola, List<Karta> lista) {

        pola.clear();
        for (int i = 0; i < lista.size(); i++) {
            PoleKarty pole = new PoleKarty();
            pola.add(pole);

            pola.get(i).setKarta( lista.get(i));


        }

    }

    public void przepisywanie_lista_napola_z_kopia(List<PoleKarty> pola, List<Karta> lista) {
        pola.clear();
        for (int i = 0; i < lista.size(); i++) {
            PoleKarty pole = new PoleKarty();
            pola.add(pole);
            pola.get(i).setKarta( lista.get(i).clone());
        }

    }

    public void repaint_one(Ekran ekran) {
        if (flaga_repaint_one_1 == 0 && flaga_repaint_one_2 == 0) {
            ekran.repaint();
            flaga_repaint_one_2 = 1;
        }

    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //this.a = e.getX();
        //this.b = e.getY();
        //System.out.println("HHHHHHHHHH"+this.a+this.b);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void zczytywanie_z_pliku(String plik, char odstep, List lista, Class clasa) {
        int error = 0;

        try {

            //  FileInputStream istream= new FileInputStream("\""+plik+"\"");
            FileInputStream test = new FileInputStream(plik);
            FileInputStream istream = new FileInputStream(plik);
            Scanner intest = new Scanner(test);
            Scanner input = new Scanner(istream);
            String linia;
            char[] line;
            int licznik_fieldow = 0;
            int licznik_tmp = 0;
            char[] tmp_tab;
            String tmp = "";
            int flaga_przecinka;
            int licznik = 0;


            Class<?> clazz = clasa;
            //  Constructor<?> ctor=clazz.getConstructor();
            Field[] fields;

            fields = clazz.getDeclaredFields();


            do {
                linia = intest.nextLine();
                line = linia.toCharArray();
                for (int i = 0; i < line.length; i++) {
                    if (line[i] == odstep) licznik_fieldow++;
                }

                if ((licznik_fieldow + 1) != fields.length) error = -1;
                licznik_fieldow = 0;
            } while (intest.hasNextLine());

            test.close();


            //////////////////////////////////////


            do {
                Object karta = clasa.newInstance();

                linia = input.nextLine();
                line = linia.toCharArray();
                licznik = 0;
                for (int i = 0; i < fields.length; i++) {

                    while (licznik < line.length && line[licznik] != odstep) {
                        tmp = tmp + line[licznik];

                        licznik++;
                    }

                    if (fields[i].getGenericType().equals(String.class)) {

                        fields[i].set(karta, tmp);
                    } else if (fields[i].getGenericType() == int.class) {

                        fields[i].setInt(karta, Integer.parseInt(tmp));
                    } else if (fields[i].getGenericType() == int.class) {

                        fields[i].setDouble(karta, Double.parseDouble(tmp));
                    }
                    tmp = "";
                    licznik++;
                }
                lista.add(karta);
                licznik = 0;


            } while (input.hasNextLine());

            test.close();
            input.close();
            intest.close();
            istream.close();


///////////////////////////////////////////////
        } catch (Exception e) {
            System.out.println("tutaj" + e);
            error = -1;
        }


    }

    public static void zapisywanie_do_pliku(String plik, char odstep, List lista, Class clasa) {
        DeleteFile(plik);
        CreateFile(plik);
        Class<?> clazz = clasa;
        Field[] fields;
        fields = clazz.getDeclaredFields();
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(new File(plik), true);


            for (int i = 0; i < lista.size(); i++) {
                if (i != 0) fileWriter.write(System.getProperty("line.separator"));
                for (int j = 0; j < fields.length; j++) {


                    if (fields[j].getGenericType().equals(String.class)) {
                        fileWriter.write((String) (fields[j].get(lista.get(i))));


                    } else if (fields[j].getGenericType() == int.class) {

                        fileWriter.write(((Integer) fields[j].get(lista.get(i))).toString());
                    } else if (fields[j].getGenericType() == double.class) {

                        fileWriter.write(String.valueOf((Double) fields[j].get(lista.get(i))));
                    }
                    if (j < fields.length - 1) fileWriter.write(",");


                }


            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Błąd przy zapisie");
            System.out.println(e);
        }

    }

    public static void dodaj_do_tali(Ekran ekran, Karta karta) {
        ekran.lista_tali.dodaj(karta);
    }

    public static void mistrzostwo(Graphics g, int iks, int igrek, List<PoleKarty> pola, int poczatkowa, int koncowa, Prostokat prostokat, String nazwa, Ekran ekran) {
        Prostokat dodaj;
        Prostokat sprzedaj;
        Font czcionka = new Font("Serif", Font.BOLD, ekran.getWidth() / 80);
        g.setFont(czcionka);
        for (int i = poczatkowa; i < koncowa; i++) {
            pola.get(i).wyswietl(g);
        }
        if (ekran.flaga_wiodaca == 21) {
            int i = poczatkowa;

            while (i < koncowa) {
                if (pola.get(i).rectangle.contains(ekran.x, ekran.y)) {

                    if (ekran.flaga_dodaj != i&&ekran.flaga_sprzedaj!=i) {
                        ekran.flaga_dodaj = i;
                        ekran.flaga_sprzedaj=i;
                        ekran.flaga_dodsprzed=1;
                    } else {
                        if (ekran.flaga_dodaj == i&&ekran.flaga_sprzedaj==i)
                        {
                            ekran.flaga_dodsprzed=1;
                        }
                        dodaj = new Prostokat(pola.get(ekran.flaga_dodaj).x, pola.get(ekran.flaga_dodaj).y+pola.get(ekran.flaga_dodaj).wysokosc-pola.get(ekran.flaga_sprzedaj).wysokosc / 7, pola.get(ekran.flaga_dodaj).szerokosc / 2, pola.get(ekran.flaga_dodaj).wysokosc / 7);
                        sprzedaj = new Prostokat(pola.get(ekran.flaga_sprzedaj).x+pola.get(ekran.flaga_sprzedaj).szerokosc/2, pola.get(ekran.flaga_sprzedaj).y+pola.get(ekran.flaga_dodaj).wysokosc-pola.get(ekran.flaga_sprzedaj).wysokosc / 7, pola.get(ekran.flaga_sprzedaj).szerokosc / 2, pola.get(ekran.flaga_sprzedaj).wysokosc / 7);

                        if (dodaj.contains(ekran.x, ekran.y)) {
                            int flaga_czy_dodac = 0;
                            for (int x = 0; x < ekran.talia_tmp.size(); x++) {
                                if (i + 1 == ekran.talia_tmp.get(x).liczba) flaga_czy_dodac = 1;
                            }
                            if (flaga_czy_dodac == 0) {
                                ekran.talia_tmp.add(new Inti(i + 1));
                                ekran.lista_tali.dodaj(pola.get(i).getKarta());
                                ekran.flaga_dodaj = -1;
                                ekran.flaga_sprzedaj=-1;

                                i=koncowa;
                            }

                        }

                        if (sprzedaj.contains(ekran.x, ekran.y)) {
                            int flaga_czy_sprzedac = 0;
                           /* for (int x = 0; x < ekran.talia_tmp.size(); x++) {
                                if (i + 1 == ekran.talia_tmp.get(x).liczba) flaga_czy_sprzedac= 1;
                            }*/
                            if (flaga_czy_sprzedac == 0) {
                                System.out.println("usuwam");
                                //ekran.talia_tmp.remove(i);
                               // ekran.lista_tali.lista.remove(i);
                                System.out.println( ekran.kolekcja_tmp.get(i).nazwa_karty);
                                ekran.kolekcja_tmp.remove(i);
                                ekran.lista_kolekcji.lista.remove(i);
                                ekran.uzytkownik.stolki+=2;
                                ekran.uzytkownik_tmp.get(0).stolki+=2;
                                ekran.flaga_sprzedaj=-1;
                                ekran.flaga_dodaj=-1;
                                ekran.flaga_dodsprzed=1;
                                i=koncowa;
                                ekran.flaga_repaint_koniec=1;
                            }

                        }

                   //     ekran.flaga_dodaj = -1;
                    }
                    System.out.println(ekran.flaga_dodaj);
                }
                i++;
            }
if(ekran.flaga_dodsprzed==1){
            if (ekran.flaga_dodaj > -1) {
                dodaj = new Prostokat(pola.get(ekran.flaga_dodaj).x, pola.get(ekran.flaga_dodaj).y+pola.get(ekran.flaga_dodaj).wysokosc-pola.get(ekran.flaga_sprzedaj).wysokosc / 7, pola.get(ekran.flaga_dodaj).szerokosc / 2, pola.get(ekran.flaga_dodaj).wysokosc / 7);

                // g.fillRect(pola.get(ekran.flaga_dodaj).x,pola.get(ekran.flaga_dodaj).y,pola.get(ekran.flaga_dodaj).szerokosc/20,pola.get(ekran.flaga_dodaj).wysokosc/20);
                g.setColor(Color.cyan);
                dodaj.paint(g);
                g.setFont(czcionka);
                dodaj.paint(g, "Dodaj", 8);
              // ekran.flaga_dodaj=-1;
               i=koncowa;
                //ekran.flaga_sprzedaj=-1;
                ekran.flaga_dodsprzed=0;
            }


            if (ekran.flaga_sprzedaj > -1) {
                sprzedaj = new Prostokat(pola.get(ekran.flaga_sprzedaj).x+pola.get(ekran.flaga_sprzedaj).szerokosc/2, pola.get(ekran.flaga_sprzedaj).y+pola.get(ekran.flaga_dodaj).wysokosc-pola.get(ekran.flaga_sprzedaj).wysokosc / 7, pola.get(ekran.flaga_sprzedaj).szerokosc / 2, pola.get(ekran.flaga_sprzedaj).wysokosc / 7);

                // g.fillRect(pola.get(ekran.flaga_dodaj).x,pola.get(ekran.flaga_dodaj).y,pola.get(ekran.flaga_dodaj).szerokosc/20,pola.get(ekran.flaga_dodaj).wysokosc/20);
                g.setColor(Color.cyan);
                sprzedaj.paint(g);
                sprzedaj.paint(g, "Sprzedaj", 8);
                //ekran.flaga_dodaj=-1;
            //  ekran.flaga_sprzedaj=-1;
              i=koncowa;
            }ekran.flaga_dodsprzed=0;

            }



        }

        if (ekran.flaga_wiodaca == 22) {
            int i = poczatkowa;

            while (i < koncowa) {
                if (pola.get(i).rectangle.contains(ekran.x, ekran.y)) {
                    //pola.get(i).dodaj=1;
                    //g.fillRect(300,300,100,100);

                    if (ekran.flaga_usun != i) {
                        ekran.flaga_usun = i;
                    } else {
                        dodaj = new Prostokat(pola.get(ekran.flaga_usun).x, pola.get(ekran.flaga_usun).y, pola.get(ekran.flaga_usun).szerokosc / 2, pola.get(ekran.flaga_usun).wysokosc / 5);

                        if (dodaj.contains(ekran.x, ekran.y)) {

                            ekran.talia_tmp.remove(i);


                            for (int x = 0; x < ekran.talia_tmp.size(); x++) {

                            }

                            ekran.lista_tali.lista.remove(i);
                            ekran.flaga_repaint_koniec = 1;
                        }
                        ekran.flaga_usun = -1;
                    }
                }
                i++;
            }

            if (ekran.flaga_usun > -1) {
                dodaj = new Prostokat(pola.get(ekran.flaga_usun).x, pola.get(ekran.flaga_usun).y, pola.get(ekran.flaga_usun).szerokosc / 2, pola.get(ekran.flaga_usun).wysokosc / 5);

                // g.fillRect(pola.get(ekran.flaga_dodaj).x,pola.get(ekran.flaga_dodaj).y,pola.get(ekran.flaga_dodaj).szerokosc/20,pola.get(ekran.flaga_dodaj).wysokosc/20);
                g.setColor(Color.cyan);
                dodaj.paint(g);
                dodaj.paint(g, "Usun", 8);
            }
        }


    }

    public void lista_PoleKarty_ustawianie_pol(List<PoleKarty> napole, float lewy, float prawy, float gora, float dol, int wiersze, int kolumny, int width, int height, Ekran ekran) {

        float odleglosc_miedzy_kartami_w_szerz= (width * (1 - (lewy + prawy) / 10) - kolumny * width / 10) / (kolumny - 1);
        float odleglosc_miedzy_kartami_w_gore = (height * (1 - (gora + dol) / 10) - wiersze * width / 7) / (wiersze - 1);
        int przesuniecie = 0, obnizenie = 0;
        obnizenie = (int) (height * (gora / 10));

        /*POCZĄTEK USTALANIA PÓL*/
        for (int i = 0; i < wiersze; i++) {
            przesuniecie = (int) ((width * lewy) / 10);
            if (kolumny == 1) przesuniecie = width / 2 - width / 20;
            for (int j = 0; j < kolumny; j++) {

                napole.get(i * kolumny + j).x = przesuniecie;
                napole.get(i * kolumny + j).y = obnizenie;
                napole.get(i * kolumny + j).szerokosc = width / 10;
                napole.get(i * kolumny + j).wysokosc = width / 7;
                napole.get(i * kolumny + j).aktualizuj_kwadrat();

                przesuniecie = (int) (przesuniecie + (int) (odleglosc_miedzy_kartami_w_szerz) + width / 10);


            }
            obnizenie = (int) (obnizenie + (int) (odleglosc_miedzy_kartami_w_gore)+width/7);
        }/*KONIEC USTALANIA PÓL*/
    }


    public void lista_PoleKarty_ustawienia_karta(List <PoleKarty>lista_pola, List <Karta> lista_karty,String rodzaj)
    {
        if(rodzaj.equals("Kopia"))
        {
            lista_pola.clear();
            for (int i = 0; i < lista_karty.size(); i++) {
                PoleKarty pole = new PoleKarty();
                lista_pola.add(pole);
                lista_pola.get(i).setKarta( lista_karty.get(i).clone());
            }

        }
        else if(rodzaj.equals("Orginal"))
        {
            lista_pola.clear();
            for (int i = 0; i < lista_karty.size(); i++) {
                PoleKarty pole = new PoleKarty();
                lista_pola.add(pole);
                lista_pola.get(i).setKarta( lista_karty.get(i));
            }
        }
    }

    public void lista_PoleKart_rysowanie(Graphics g, List<PoleKarty> lista_pol,int width,int height)
    {
        for(int i=0;i<lista_pol.size();i++) {
            if (lista_pol.get(i).wskaznik == -1)
                lista_pol.get(i).getKarta().podswietlenie(g, lista_pol.get(i).x, lista_pol.get(i).y, width, height, 1);
            if (lista_pol.get(i).wskaznik == 2)
                lista_pol.get(i).getKarta().podswietlenie(g, lista_pol.get(i).x, lista_pol.get(i).y, width, height, 2);
            else lista_pol.get(i).getKarta().paint(g, lista_pol.get(i).x, lista_pol.get(i).y, width, height);
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        this.a=e.getX();
        this.b=e.getY();
        System.out.println("dragger "+a+" "+b );

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

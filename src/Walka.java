import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by osiza on 08.05.2017.
 */
public class Walka {

    ListaPoleKarty sojusznik;
    ListaPoleKarty  przeciwnik;
    Ekran ekran;

    int flaga_rozpoczecia_walki;
    int tura_przeciwnika;
    int tura_sojusznika;
    int wskaznik_podreczny;
    Walka()
    {
        this.flaga_rozpoczecia_walki=0;
    }
    Walka(ListaPoleKarty  sojusznik,ListaPoleKarty  przeciwnik,Ekran ekran)
    {
        this.sojusznik=sojusznik;
        this.przeciwnik=przeciwnik;
        this.ekran=ekran;

        this.flaga_rozpoczecia_walki=0;
        this.tura_przeciwnika=0;
        this.tura_sojusznika=0;
    }
    public void start(int n)
    {

           if (this.flaga_rozpoczecia_walki == 0) {
               sojusznik.losowy_wybor(n);
               this.flaga_rozpoczecia_walki = 1;

             // laduj_umiejetnosci_pasywne();

           }

                sojusznik.lista_PoleKarty_ustawianie_pol(2, 2, 6, 1, 1, this.sojusznik.size(), ekran.getWidth(), ekran.getHeight());
                przeciwnik.lista_PoleKarty_ustawianie_pol(1.5f, 1.5f, 1, 6, 1, przeciwnik.size(), ekran.getWidth(), ekran.getHeight());
        laduj_umiejetnosci_pasywne(0);
        laduj_umiejetnosci_pasywne(1);
                obsluga();
                obsługa_przyciskow();
      //  laduj_umiejetnosci_pasywne();
                sojusznik.rysowanie(ekran.getGraphics(), ekran.getWidth(), ekran.getHeight());
               przeciwnik.rysowanie(ekran.getGraphics(), ekran.getWidth(), ekran.getHeight());












    }



    public void obsluga()
    {
        int flaga_wcisniecia_sojusznik=0,flaga_wcisniecia_przeciwnik=0,licznik_przeciwnik=0,licznik_sojusznik=0;
        int flaga_ruch_sojusznika=0;
        int flaga_wygranej=0,flaga_przegranej=0;
        int flaga_atak_na_uzytego_przeciwnika=0;
    for(int i=0;i<sojusznik.size();i++)
    {
        if(sojusznik.listaPoleKarty.get(i).wskaznik==-1) flaga_wcisniecia_sojusznik=1;
    }

    for(int i=0;i<this.sojusznik.size();i++)
    {
        if(this.sojusznik.listaPoleKarty.get(i).rectangle.contains(ekran.x,ekran.y)&&!(this.sojusznik.listaPoleKarty.get(i).umiejetnosc.contains(ekran.x,ekran.y))&&this.sojusznik.listaPoleKarty.get(i).wskaznik!=2)
        {
            if(sojusznik.listaPoleKarty.get(i).wskaznik==-1||(flaga_wcisniecia_sojusznik==0&&sojusznik.listaPoleKarty.get(i).wskaznik==1)) {
                if(sojusznik.listaPoleKarty.get(i).wskaznik_zaznaczenia_umiejetnoscia==0){//**********************************88
                this.sojusznik.listaPoleKarty.get(i).wskaznik = this.sojusznik.listaPoleKarty.get(i).wskaznik * (-1);}
            }
        }
    }
        for(int i=0;i<przeciwnik.size();i++)
        {
            if(przeciwnik.listaPoleKarty.get(i).wskaznik==-1) flaga_wcisniecia_przeciwnik=1;
        }

    for(int i=0;i<this.przeciwnik.size();i++)
        {
            if(this.przeciwnik.listaPoleKarty.get(i).rectangle.contains(ekran.x,ekran.y)&&!(this.przeciwnik.listaPoleKarty.get(i).umiejetnosc.contains(ekran.x,ekran.y)))
            {
                if(przeciwnik.listaPoleKarty.get(i).wskaznik==-1||flaga_wcisniecia_przeciwnik==0) {
                    if( this.przeciwnik.listaPoleKarty.get(i).wskaznik==2) {
                        this.przeciwnik.listaPoleKarty.get(i).wskaznik=-1;
                        flaga_atak_na_uzytego_przeciwnika = 2;
                    }
                    else
                    this.przeciwnik.listaPoleKarty.get(i).wskaznik = this.przeciwnik.listaPoleKarty.get(i).wskaznik * (-1);
                }
            }
        }
flaga_wcisniecia_przeciwnik=0;
    flaga_wcisniecia_sojusznik=0;
    licznik_sojusznik=0;
    licznik_przeciwnik=0;
        while(licznik_sojusznik<sojusznik.size()&&flaga_wcisniecia_sojusznik==0)
        {
            if(sojusznik.listaPoleKarty.get(licznik_sojusznik).wskaznik==-1) flaga_wcisniecia_sojusznik=1;
            licznik_sojusznik++;
        }
        licznik_sojusznik-=1;
        while(licznik_przeciwnik<przeciwnik.size()&&flaga_wcisniecia_przeciwnik==0)
        {
            if(przeciwnik.listaPoleKarty.get(licznik_przeciwnik).wskaznik==-1) flaga_wcisniecia_przeciwnik=1;
            licznik_przeciwnik++;

        }
      licznik_przeciwnik-=1;
        int flaga_usuniecia=0;
        if(flaga_wcisniecia_przeciwnik==1&&flaga_wcisniecia_sojusznik==1)
        {
           // sojusznik.rysowanie(ekran.getGraphics(), ekran.getWidth(), ekran.getHeight());
            //przeciwnik.rysowanie(ekran.getGraphics(), ekran.getWidth(), ekran.getHeight());
            aktualizuj();
           sleepka(1000);
            przeciwnik.listaPoleKarty.get(licznik_przeciwnik).getKarta().hp-=sojusznik.listaPoleKarty.get(licznik_sojusznik).getKarta().moc;
            if( przeciwnik.listaPoleKarty.get(licznik_przeciwnik).getKarta().hp<=0)
            {
                przeciwnik.usun(licznik_przeciwnik,this,1);
            flaga_usuniecia=1;}//przeciwnik.listaPoleKarty.remove(licznik_przeciwnik)
            aktualizuj();
            if(przeciwnik.size()==0) {
                flaga_wygranej=1;
            }
            else
            {
           sojusznik.listaPoleKarty.get(licznik_sojusznik).wskaznik=2;
           aktualizuj();
           sleepka(1000);
           if(flaga_usuniecia==0) {
               if (flaga_atak_na_uzytego_przeciwnika == 2) {
                   przeciwnik.listaPoleKarty.get(licznik_przeciwnik).wskaznik = 2;

               }
               else
                   przeciwnik.listaPoleKarty.get(licznik_przeciwnik).wskaznik = 1;
           }
            repaint_once();
            ekran.x=0;
            ekran.y=0;
            }

            aktualizuj();
            sleepka(1000);
            flaga_ruch_sojusznika=1;
        }

        if(flaga_ruch_sojusznika==1&&flaga_wygranej==0|| (tura_przeciwnika==0&&tura_sojusznika==1)) {

            Random random = new Random();
            List<Integer> agresor = new LinkedList<>();
            for (int i = 0; i < przeciwnik.size(); i++) {
                if (przeciwnik.listaPoleKarty.get(i).wskaznik == 1) {
                    agresor.add(i);

                }
            }
            int los_agresor;
            int los_ofiara;

            if (agresor.size() > 0) {
                los_agresor = random.nextInt(agresor.size());

                los_ofiara=random.nextInt(sojusznik.size());

                przeciwnik.listaPoleKarty.get(agresor.get(los_agresor)).wskaznik = -1;

                aktualizuj();
                sleepka(1000);

                for(int i=0;i<this.przeciwnik.listaPoleKarty.get(los_agresor).tab_umiejetnosci.size();i++)
                {
                    aktywuj_umiejetnosc(los_agresor,i,1);

                }
                int wskaznik_tmp;
                wskaznik_tmp = sojusznik.listaPoleKarty.get(los_ofiara).wskaznik;


                sojusznik.listaPoleKarty.get(los_ofiara).wskaznik = 3;
                //sojusznik.rysowanie(ekran.getGraphics(), ekran.getWidth(), ekran.getHeight());
               // przeciwnik.rysowanie(ekran.getGraphics(), ekran.getWidth(), ekran.getHeight());
               aktualizuj();
                sleepka(1000);
                sojusznik.listaPoleKarty.get(los_ofiara).wskaznik = wskaznik_tmp;
                sojusznik.listaPoleKarty.get(los_ofiara).getKarta().hp -= przeciwnik.listaPoleKarty.get(agresor.get(los_agresor)).getKarta().moc;
                if (sojusznik.listaPoleKarty.get(los_ofiara).getKarta().hp <= 0) {

                    sojusznik.usun(los_ofiara,this,0);
                    aktualizuj();
                    if (sojusznik.size() == 0) flaga_przegranej = 1;

                }

                if(flaga_przegranej==0)
                {

                    przeciwnik.listaPoleKarty.get(agresor.get(los_agresor)).wskaznik = 2;

                aktualizuj();
                sleepka(1000);

            }
        }

        }
        if(flaga_przegranej==1)
        {

            Font koniec_walki = new Font("Serif", Font.BOLD, ekran.getWidth() / 15);
            ekran.setFont(koniec_walki);
            ekran.getGraphics().drawString("Przegrałeś",ekran.getWidth()/4,ekran.getHeight()/4);
            sleepka(3000);

            if(ekran.flaga_parlamentarne_potyczki==1&&ekran.flaga_wiodaca==12){
            ekran.flaga_parlamentarne_potyczki=0;}
            else if(ekran.flaga_walki_prezydenckie==1&&ekran.flaga_wiodaca==13)
            {
                ekran.flaga_walki_prezydenckie=0;
            }
            else if(ekran.flaga_walka_o_senat==1&&ekran.flaga_wiodaca==11)
            {
                ekran.flaga_walki_prezydenckie=0;
            }
            ekran.flaga_wiodaca/=10;
            repaint_once();
        }
        else if(flaga_wygranej==1)
        {

            Font koniec_walki = new Font("Serif", Font.BOLD, ekran.getWidth() / 15);


            ekran.setFont(koniec_walki);
            ekran.getGraphics().drawString("Wygrałeś",ekran.getWidth()/4,ekran.getHeight()/4);
            sleepka(3000);

            if(ekran.flaga_parlamentarne_potyczki==1&&ekran.flaga_wiodaca==12){
            ekran.flaga_parlamentarne_potyczki=0;}
            else if(ekran.flaga_walki_prezydenckie==1&&ekran.flaga_wiodaca==13)
            {
                ekran.flaga_walki_prezydenckie=0;
                ekran.uzytkownik.level_prezydencki+=1;
                ekran.uzytkownik_tmp.get(0).level_prezydencki+=1;
            }
            else if(ekran.flaga_walka_o_senat==1&&ekran.flaga_wiodaca==11)
            {
                ekran.flaga_walka_o_senat=0;
            }
            ekran.flaga_wiodaca/=10;
            repaint_once();
        }
        else {
            flaga_wcisniecia_przeciwnik = 0;
            flaga_wcisniecia_sojusznik = 0;

            for (int i = 0; i < sojusznik.listaPoleKarty.size(); i++) {
                if (sojusznik.listaPoleKarty.get(i).wskaznik != 2) flaga_wcisniecia_sojusznik = 1;
            }

            if (flaga_wcisniecia_sojusznik == 0) {
                this.tura_sojusznika=1;
                if(tura_przeciwnika==1){
                for (int i = 0; i < sojusznik.listaPoleKarty.size(); i++) {
                   // if(tura_przeciwnika==1)
//                    sojusznik.listaPoleKarty.get(i).wskaznik = 1;
                }
                    tura_przeciwnika=0;
                }
            }

            /*for (int i = 0; i < przeciwnik.listaPoleKarty.size(); i++) {
                if (przeciwnik.listaPoleKarty.get(i).wskaznik != 2) flaga_wcisniecia_przeciwnik = 1;
            }*/

            if (/*flaga_wcisniecia_przeciwnik == 0*/this.przeciwnik.sprawdz_czy_wszystkie_uzyte())
            {

                this.tura_przeciwnika=1;


                if(tura_sojusznika==1){
                for (int i = 0; i < przeciwnik.listaPoleKarty.size(); i++) {
                    //przeciwnik.listaPoleKarty.get(i).wskaznik = 1;
                }
                    for(int i=0;i<this.przeciwnik.size();i++)
                    {

                    }

               tura_sojusznika=0;
              tura_przeciwnika=0;
                }
            }
            ///////////////

            if (flaga_wcisniecia_sojusznik == 0) {

                this.tura_sojusznika=1;
                if(tura_przeciwnika==1){
                    for (int i = 0; i < sojusznik.listaPoleKarty.size(); i++) {
                        if(tura_przeciwnika==1){}
                           // sojusznik.listaPoleKarty.get(i).wskaznik = 1;

                    }
                    tura_przeciwnika=0;
                }
            }

/////////////////




            if(this.sojusznik.sprawdz_czy_wszystkie_uzyte()&&this.przeciwnik.sprawdz_czy_wszystkie_uzyte())
            {

                for(int i=0;i<this.sojusznik.size();i++)
                {
                    this.sojusznik.listaPoleKarty.get(i).wskaznik_zaznaczenia_umiejetnoscia=0;
                }
                for(int i=0;i<this.sojusznik.size();i++)
                {
                    this.sojusznik.listaPoleKarty.get(i).wskaznik_zaznaczenia_umiejetnoscia=0;
                }
                this.sojusznik.wznow_umiejetnosci();
                this.przeciwnik.wznow_umiejetnosci();
                this.przeciwnik.oduzyj();
                this.sojusznik.oduzyj();


                flaga_ruch_sojusznika=0;
                tura_sojusznika=0;
                aktualizuj();
            }

           else if(tura_przeciwnika==0&&tura_sojusznika==1)repaint_once();
        }




    }
    public  void repaint_once()
    {
        this.ekran.x=0;
        this.ekran.y=-1;
        this.ekran.repaint();

    }
    public void sleepka(int n)
    {
        try {
            sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void aktualizuj() {

        //ekran.getGraphics().clearRect(0, 0, ekran.getWidth(), ekran.getHeight());
        ekran.getGraphics().drawImage(ekran.sejm,0,0,ekran.getWidth(),ekran.getHeight(),ekran);
       sojusznik.rysowanie(ekran.getGraphics(), ekran.getWidth(), ekran.getHeight());
        przeciwnik.rysowanie(ekran.getGraphics(), ekran.getWidth(), ekran.getHeight());
       // sojusznik.wyswietl(ekran.getGraphics());
      //   przeciwnik.wyswietl(ekran.getGraphics());

    }

    public void obsługa_przyciskow()
    {
        int flaga_przycisku=0,licznik_przycisku=0,licznik_karty=0,licznik=0;
        while(flaga_przycisku==0&&licznik<this.sojusznik.size())
        {
            for(int i=0;i<this.sojusznik.listaPoleKarty.get(licznik).tab_umiejetnosci.size();i++){
            if(this.sojusznik.listaPoleKarty.get(licznik).tab_umiejetnosci.get(i).przycisk.contains(this.ekran.x,this.ekran.y))
            {
                if(sojusznik.listaPoleKarty.get(licznik).tab_umiejetnosci.get(i).getStan()==0&&(sojusznik.listaPoleKarty.get(licznik).wskaznik==-1||sojusznik.listaPoleKarty.get(licznik).wskaznik==1)) {
                   if(this.sojusznik.sprawdz_czy_ktoras_zaznaczona()||this.sojusznik.listaPoleKarty.get(licznik).wskaznik==-1) {
                       licznik_przycisku = i;
                       licznik_karty = licznik;
                       flaga_przycisku = 1;
                       //sojusznik.listaPoleKarty.get(licznik).tab_umiejetnosci.get(i).uzyj_umiejetnosci();
                       sojusznik.listaPoleKarty.get(licznik).wskaznik = -1;
                       sojusznik.listaPoleKarty.get(licznik).wskaznik_zaznaczenia_umiejetnoscia = 1;
                   }
                }
            }

            }

            licznik++;

        }
        if(flaga_przycisku!=0)
        {
            aktywuj_umiejetnosc(licznik_karty,licznik_przycisku,0);
        }
        aktualizuj();




    }

    public void aktywuj_umiejetnosc(int karta,int przycisk,int wybor)
    {
        ListaPoleKarty tmp_aktywny;
        ListaPoleKarty tmp_przeciwnik;
        if(wybor==0)
        {
            tmp_aktywny=this.sojusznik;
            tmp_przeciwnik=this.przeciwnik;

        }
        else
        {
            tmp_aktywny=this.przeciwnik;
            tmp_przeciwnik=this.sojusznik;

        }



        int indeks=tmp_aktywny.listaPoleKarty.get(karta).tab_umiejetnosci.get(przycisk).numer;

        if(tmp_aktywny.listaPoleKarty.get(karta).tab_umiejetnosci.get(przycisk).getStan()==0) {
            switch (indeks) {
                case 1:
                    tmp_aktywny.listaPoleKarty.get(karta).getKarta().hp += 10;
                    tmp_aktywny.listaPoleKarty.get(karta).tab_umiejetnosci.get(przycisk).uzyj_umiejetnosci();
                    break;

                case 2:
                    tmp_aktywny.listaPoleKarty.get(karta).getKarta().moc += 20;
                    tmp_aktywny.listaPoleKarty.get(karta).tab_umiejetnosci.get(przycisk).uzyj_umiejetnosci();
                    break;
                case 4:
                    for (int i = 0; i < tmp_aktywny.size(); i++) {
                        if(i!=karta) {
                            tmp_aktywny.listaPoleKarty.get(i).getKarta().hp += 15;
                        }
                    }
                    tmp_aktywny.listaPoleKarty.get(karta).tab_umiejetnosci.get(przycisk).uzyj_umiejetnosci();
                    break;

                case 8:
                    Random random = new Random();
                    int los = random.nextInt(this.przeciwnik.size());
                    tmp_przeciwnik.usun(los,this,1);
                    sprawdz_czy_wygralem();
                    tmp_aktywny.listaPoleKarty.get(karta).tab_umiejetnosci.get(przycisk).uzyj_umiejetnosci();

                    break;
                case 16:
                    for(int i=0;i<tmp_aktywny.size();i++)
                    {
                        tmp_aktywny.listaPoleKarty.get(i).getKarta().hp+=20;
                    }
                    tmp_aktywny.listaPoleKarty.get(karta).tab_umiejetnosci.get(przycisk).uzyj_umiejetnosci();
                    break;
                case 32:
                    for(int i=0;i<this.przeciwnik.size();i++)
                    {
                        tmp_przeciwnik.listaPoleKarty.get(i).getKarta().moc/=2;
                    }
                    break;
                case 64:
                    random=new Random();
                    List<Integer> lista=new LinkedList<>();
                    for(int i=0;i<tmp_przeciwnik.size();i++)
                    {
                        if(tmp_przeciwnik.listaPoleKarty.get(i).wskaznik==1)
                        {
                            lista.add(i);
                        }
                    }
                    if(lista.size()>0) {


                        los = random.nextInt(lista.size());


                        tmp_przeciwnik.listaPoleKarty.get(lista.get(los)).wskaznik=2;
                    }

                    break;
                case 128:
                    int suma=0;
                    for(int i=0;i<tmp_aktywny.size();i++)
                    {
                        if(tmp_aktywny.listaPoleKarty.get(i).getKarta().partia.equals(tmp_aktywny.listaPoleKarty.get(karta).getKarta().partia)&&i!=karta)
                        {
                            suma+=50;
                        }
                    }


                    tmp_aktywny.listaPoleKarty.get(karta).getKarta().hp +=suma;



                    break;



            }
        }




    }

    public void sprawdz_czy_wygralem()
    {
        if(this.sojusznik.size()==0) this.flaga_rozpoczecia_walki=0;
    }

    public void laduj_umiejetnosci_pasywne(int wybor)
    {

        ListaPoleKarty tmp_aktywny;
        ListaPoleKarty tmp_przeciwnik;
        if(wybor==0)
        {
            tmp_aktywny=this.sojusznik;
            tmp_przeciwnik=this.przeciwnik;

        }
        else
        {
            tmp_aktywny=this.przeciwnik;
            tmp_przeciwnik=this.sojusznik;

        }


        for(int i=0;i<tmp_aktywny.size();i++)
        {
            for(int j=0;j<tmp_aktywny.listaPoleKarty.get(i).tab_umiejetnosci.size();j++)
            {
             if(tmp_aktywny.listaPoleKarty.get(i).tab_umiejetnosci.get(j).rodzaj==3)
             {
                 aktywuj_umiejetnosc(i,j,wybor);
             }
            }
        }

    }

    public void odkrec(int karta, int umiejetnosc,int wybor)
    {

        ListaPoleKarty tmp_aktywny;
        ListaPoleKarty tmp_przeciwnik;
        if(wybor==0)
        {
            tmp_aktywny=this.sojusznik;
            tmp_przeciwnik=this.przeciwnik;

        }
        else
        {
            tmp_aktywny=this.przeciwnik;
            tmp_przeciwnik=this.sojusznik;

        }

        int ktora=tmp_aktywny.listaPoleKarty.get(karta).tab_umiejetnosci.get(umiejetnosc).numer;
        switch(ktora)
        {
            case 16:

                for(int i=0;i<tmp_aktywny.size();i++)
                {

                    tmp_aktywny.listaPoleKarty.get(i).getKarta().hp-=20;
                }
                break;
        }




    }





}

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by osiza on 08.05.2017.
 */
public class ListaPoleKarty {
    List<PoleKarty> listaPoleKarty;

    ListaPoleKarty()
    {
    listaPoleKarty=new LinkedList<>();
    }
    ListaPoleKarty(List<PoleKarty> listaPoleKarty)
    {
       this.listaPoleKarty=listaPoleKarty;
    }


    public void rysowanie(Graphics g, int width, int height)
    {

        for(int i=0;i<this.listaPoleKarty.size();i++) {
            if (this.listaPoleKarty.get(i).wskaznik == -1)
                this.listaPoleKarty.get(i).getKarta().podswietlenie(g, this.listaPoleKarty.get(i).x, listaPoleKarty.get(i).y, width, height, 1);
            else if (this.listaPoleKarty.get(i).wskaznik == 2)
                this.listaPoleKarty.get(i).getKarta().podswietlenie(g, this.listaPoleKarty.get(i).x, listaPoleKarty.get(i).y, width, height, 2);
            else if (this.listaPoleKarty.get(i).wskaznik == 3)
                this.listaPoleKarty.get(i).getKarta().podswietlenie(g, this.listaPoleKarty.get(i).x, listaPoleKarty.get(i).y, width, height, 3);
            else {
                this.listaPoleKarty.get(i).getKarta().paint(g, listaPoleKarty.get(i).x, listaPoleKarty.get(i).y, width, height);

            }

           listaPoleKarty.get(i).wypisz_umiejetnosci(g);

            }




    }
    public void wyswietl(Graphics g)
    {

      for(int i=0;i<this.listaPoleKarty.size();i++)
      {
          this.listaPoleKarty.get(i).wyswietl(g);
      }
    }

    public void lista_PoleKarty_ustawianie_pol( float lewy, float prawy, float gora, float dol, int wiersze, int kolumny, int width, int height) {

        float odleglosc_miedzy_kartami_w_szerz= (width * (1 - (lewy + prawy) / 10) - kolumny * width / 10) / (kolumny - 1);
        float odleglosc_miedzy_kartami_w_gore = (height * (1 - (gora + dol) / 10) - wiersze * width / 7) / (wiersze - 1);
        int przesuniecie = 0, obnizenie = 0;
        obnizenie = (int) (height * (gora / 10));

        /*POCZĄTEK USTALANIA PÓL*/
        for (int i = 0; i < wiersze; i++) {
            przesuniecie = (int) ((width * lewy) / 10);
            if (kolumny == 1) przesuniecie = width / 2 - width / 20;
            for (int j = 0; j < kolumny; j++) {

                this.listaPoleKarty.get(i * kolumny + j).x = przesuniecie;
                this.listaPoleKarty.get(i * kolumny + j).y = obnizenie;
                this.listaPoleKarty.get(i * kolumny + j).szerokosc = width / 10;
                this.listaPoleKarty.get(i * kolumny + j).wysokosc = width / 7;
                this.listaPoleKarty.get(i * kolumny + j).aktualizuj_kwadrat();
                this.listaPoleKarty.get(i*kolumny +j).aktualizuj_przyciski();
                przesuniecie = (int) (przesuniecie + (int) (odleglosc_miedzy_kartami_w_szerz) + width / 10);


            }
            obnizenie = (int) (obnizenie + (int) (odleglosc_miedzy_kartami_w_gore)+width/7);
        }/*KONIEC USTALANIA PÓL*/




    }


    public void lista_PoleKarty_ustawienia_karta( ListaKart lista_karty,String rodzaj)
    {
        if(rodzaj.equals("Kopia"))
        {
            this.listaPoleKarty.clear();
            for (int i = 0; i < lista_karty.size(); i++) {
                PoleKarty pole = new PoleKarty();
                this.listaPoleKarty.add(pole);
                this.listaPoleKarty.get(i).setKarta( lista_karty.get(i).clone());
            }

        }
        else if(rodzaj.equals("Orginal"))
        {
            this.listaPoleKarty.clear();
            for (int i = 0; i < lista_karty.size(); i++) {
                PoleKarty pole = new PoleKarty();
                this.listaPoleKarty.add(pole);
                this.listaPoleKarty.get(i).setKarta( lista_karty.get(i));
            }
        }
    }

    public void losowy_wybor(int n)
    {
        if(n<this.listaPoleKarty.size()) {
       ListaKart tmp = new ListaKart();
        for(int i=0;i<this.listaPoleKarty.size();i++)
        {
            tmp.dodaj(this.listaPoleKarty.get(i).getKarta().clone());
        }
        for(int i=this.listaPoleKarty.size()-1;i>=n;i--)
        {
            listaPoleKarty.remove(i);

        }
        Random random = new Random();
        int ktora;
        for (int i = 0; i < n; i++) {
            ktora = random.nextInt(tmp.size());
            listaPoleKarty.get(i).setKarta(tmp.get(ktora));
            tmp.lista.remove(ktora);
        }

    }

    }
public void obsluga(Ekran e)
{
    for(int i=0;i<this.listaPoleKarty.size();i++)
    {
        if(this.listaPoleKarty.get(i).rectangle.contains(e.x,e.y)&&!(this.listaPoleKarty.get(i).umiejetnosc.contains(e.x,e.y))&&this.listaPoleKarty.get(i).wskaznik!=2)
        {
            this.listaPoleKarty.get(i).wskaznik=this.listaPoleKarty.get(i).wskaznik*(-1);
        }
    }
}
public int size()
{
    return this.listaPoleKarty.size();
}
public boolean sprawdz_czy_wszystkie_uzyte()
{
    for(int i=0;i<this.listaPoleKarty.size();i++)
    {
        if(this.listaPoleKarty.get(i).wskaznik!=2)
        {
            return false;
        }
    }
    return true;
}

public boolean sprawdz_czy_ktoras_zaznaczona()
{
    int flaga=0;
    for(int i=0;i<this.size();i++)
    {
        if(this.listaPoleKarty.get(i).wskaznik==-1)flaga=-1;
    }
    if(flaga==-1)return false;
    else return true;
}
public int ktora_wcisnieta()
{
    int i=0;
    while(i<this.listaPoleKarty.size()&&this.listaPoleKarty.get(i).wskaznik!=-1)
    {
        i++;
    }
   if(this.listaPoleKarty.get(i).wskaznik==-1) return i;
    else return -1;
}
public void oduzyj()
{
    for(int i=0;i<this.size();i++)
    {
        this.listaPoleKarty.get(i).wskaznik=1;
    }
}

public void wznow_umiejetnosci()
{
    for(int i=0;i<this.listaPoleKarty.size();i++)
    {
        this.listaPoleKarty.get(i).wznow_umiejetnosci();
    }
}


public void pokazPro(Graphics g)
{
    for(int i=0;i<this.size();i++)
    {
        this.listaPoleKarty.get(i).pokazPro(g);
    }
}


public void usun(int n,Walka walka,int wybor)
{
    for(int i=0;i<this.listaPoleKarty.get(n).tab_umiejetnosci.size();i++)
    {
        if(this.listaPoleKarty.get(n).tab_umiejetnosci.get(i).rodzaj==3)
        {
            walka.odkrec(n,i,wybor);
        }
    }

    this.listaPoleKarty.remove(n);

}

}

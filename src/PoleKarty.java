import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import static java.lang.Math.pow;
/**
 * Created by osiza on 23.04.2017.
 */
public class PoleKarty {
    private Karta karta;

    int x;
    int y;
    int szerokosc;
    int wysokosc;
    int wskaznik;
    int wskaznik_zaznaczenia_umiejetnoscia;
    int stan;
    //int flaga_blokady;
   List<Przycisk_umiejetnosci> tab_umiejetnosci;
    Prostokat rectangle;
    Prostokat umiejetnosc;
   // Prostokat pasywna;


    PoleKarty(Karta karta, int x, int y, int szerokosc, int wysokosc) {
        this.karta = karta;
        this.szerokosc = szerokosc / 10;
        this.wysokosc = (int) szerokosc / 7;
        this.x = x;
        this.y = y;
        this.rectangle = new Prostokat(x, y, szerokosc, wysokosc);
        this.wskaznik = 1;
        this.umiejetnosc = new Prostokat(x + (szerokosc * 10) / 200, y + (szerokosc * 10) / 16, (szerokosc / 11) * 10, (szerokosc / 40) * 10);
        //this.pasywna = new Prostokat(x + (szerokosc * 10) / 200 + ((szerokosc / 11) * 10) / 2, (int) (y + (szerokosc * 10) / 16 + (szerokosc / 40) * 10) / 2, ((szerokosc / 11) * 10) / 2, ((szerokosc / 40) * 10) / 2);
        aktualizuj_przyciski();
        this.tab_umiejetnosci=new LinkedList<>();
        tworz_przyciski();
    }

    PoleKarty(int x, int y, int szerokosc, int wysokosc) {
        this.szerokosc = szerokosc / 10;
        this.wysokosc = (int) szerokosc / 7;
        this.x = x;
        this.y = y;
        this.rectangle = new Prostokat(x, y, szerokosc, wysokosc);
        this.wskaznik = 1;
        this.umiejetnosc = new Prostokat(x + (szerokosc / 200) * 10, y + (szerokosc / 16) * 10, (szerokosc / 11) * 10, (szerokosc / 40) * 10);
      //  this.pasywna = new Prostokat(x + (szerokosc * 10) / 200 + ((szerokosc / 11) * 10) / 2, (int) (y + (szerokosc * 10) / 16 + (szerokosc / 40) * 10) / 2, ((szerokosc / 11) * 10) / 2, ((szerokosc / 40) * 10) / 2);
        this.tab_umiejetnosci=new LinkedList<>();

    }

    PoleKarty() {
        this.szerokosc = 0;
        this.wysokosc = 0;
        this.x = 0;
        this.y = 0;
        this.rectangle = new Prostokat(x, y, szerokosc, wysokosc);
        this.umiejetnosc = new Prostokat(x + (szerokosc / 200) * 10, y + (szerokosc / 16) * 10, ((szerokosc / 11) * 10) / 2, ((szerokosc / 40) * 10) / 2);
     //   this.pasywna = new Prostokat(x + (szerokosc * 10) / 200 + ((szerokosc / 11) * 10) / 2, (int) (y + (szerokosc * 10) / 16 + (szerokosc / 40) * 10) / 2, ((szerokosc / 11) * 10) / 2, ((szerokosc / 40) * 10) / 2);
        this.tab_umiejetnosci=new LinkedList<>();
        this.wskaznik = 1;
    }

    public void aktualizuj_kwadrat() {
        // Rectangle rec=new Rectangle(this.x,this.y,this.szerokosc,this.wysokosc);

        this.rectangle = new Prostokat(this.x, this.y, this.szerokosc, this.wysokosc);
        this.umiejetnosc = new Prostokat(x + (szerokosc * 10) / 200, y + (szerokosc / 16) * 10, (szerokosc / 11) * 10, (szerokosc / 40) * 10);
     //   this.pasywna = new Prostokat(x + (szerokosc * 10) / 200 + ((szerokosc / 11) * 10) / 2, (int) (y + (szerokosc * 10) / 16 + (szerokosc / 40) * 10) / 2, ((szerokosc / 11) * 10) / 2, ((szerokosc / 40) * 10) / 2);


    }



    public void paint(Graphics g) {

       // if(this.tab_umiejetnosci==null) aktualizuj_przyciski();
        this.karta.paint(g, this.x, this.y, this.szerokosc*10, this.wysokosc * 7);
        wypisz_umiejetnosci(g);
     //   wypisz_umiejetnosci(g);

    }
public void setKarta(Karta karta)
{
    this.karta=karta;

    if(tab_umiejetnosci==null)tab_umiejetnosci= new LinkedList<>();
    tworz_przyciski();

}

 /*  public void wyswietl(Graphics g) {

        /*Oval oval1 = new Oval(x + szerokosc / 200, y + szerokosc / 10, szerokosc / 30, szerokosc / 30);
        Oval oval2 = new Oval(x + szerokosc / 15 - szerokosc / 200, y + szerokosc / 10, szerokosc / 30, szerokosc / 30);
        Font moc = new Font("Serif", Font.BOLD, szerokosc / 50);
        Font nazwa = new Font("Serif", Font.BOLD, szerokosc / 100);
        //g.clearRect(0,0,wysokosc,szerokosc);
        if (this.karta.rzadkosc.equals("Pospolita")) g.setColor(Color.black);
        if (this.karta.rzadkosc.equals("Niepospolita")) g.setColor(Color.green);
        if (this.karta.rzadkosc.equals("Rzadka")) g.setColor(Color.blue);
        if (this.karta.rzadkosc.equals("Unikat")) g.setColor(Color.yellow);
        if (this.karta.rzadkosc.equals("Legendarna")) g.setColor(Color.getHSBColor(184, 3, 255));
        if (this.karta.rzadkosc.equals("Mityczna")) g.setColor(Color.getHSBColor(195, 176, 145));
        if (this.karta.rzadkosc.equals("Wybrana")) g.setColor(Color.white);
        // g.fillRect(x, y, szerokosc / 10, szerokosc / 7);
        this.rectangle.paint(g);
        g.setColor(Color.black);
        oval1.paint(g);
        g.setColor(Color.green);
        oval2.paint(g);
        g.setColor(Color.orange);
        // g.fillRect(x + szerokosc / 200, y + szerokosc / 16, szerokosc / 11, szerokosc / 40);
        this.umiejetnosc.paint(g);
        Prostokat prostokat = new Prostokat(x, y, szerokosc, szerokosc / 6);
        g.setColor(Color.gray);
        prostokat.paint(g);
        g.setFont(nazwa);

        prostokat.paint(g, this.karta.nazwa_karty, 4);
        g.setFont(moc);
        g.setColor(Color.white);
        //  g.drawString(g.getFontMetrics().stringWidth(this.moc + "") / 2 + "", 300, 300);
        // g.drawString(this.moc + "", (int) ((x + szerokosc / 60) - (g.getFontMetrics().stringWidth(this.moc + "") / 3.8)), (int) (y + (szerokosc / 10) * 1.25));
        // g.drawString(this.hp + "", (int) ((x + szerokosc / 15 - szerokosc / 200)), (int) (y + (szerokosc / 10) * 1.25));

        oval1.paint(g, this.karta.moc + "");
        oval2.paint(g, this.karta.hp + "");
        g.setColor(Color.red);
        for(int i=0;i<this.tab_umiejetnosci.size();i++)
        {
            this.tab_umiejetnosci.get(i).przycisk.paint(g,"1");
        }
    }*/

/*    public void tworzenie_tab_umiejetnosci()
    {int b_tmp=this.karta.umiejetnosc;
    int tmp;
    List <Integer> l_tmp=new LinkedList<>();
        while(b_tmp>0)
        {
           tmp=this.karta.umiejetnosc/2;

           tmp=tmp*2;
           if(tmp==this.karta.umiejetnosc)
           {
               l_tmp.add(0);
           }
           else
           {
               l_tmp.add(1);
           }
           this.karta.umiejetnosc/=2;
        }
        this.tab_umiejetnosci=new int[l_tmp.size()];
        for(int i=0;i<this.tab_umiejetnosci.length;i++)
        {
            tab_umiejetnosci[i]=l_tmp.get(i);
        }


    }*/


public void wyswietl(Graphics g)
{

    this.karta.wyswietl(g, this.x, this.y, this.rectangle.szerokosc * 10, this.rectangle.wysokosc * 7,this.wskaznik);
    wypisz_umiejetnosci(g);
}

public void tworz_przyciski()
{

   tab_umiejetnosci.clear();
int um_tmp=0,z_tmp=0;
um_tmp=this.karta.umiejetnosc;

List<Integer> tab_tmp=new LinkedList<>();
//this.tab_umiejetnosci=new LinkedList<>();
while(um_tmp>0)
{

    z_tmp=um_tmp/2;
    if(um_tmp==2*z_tmp)
    {
    tab_tmp.add(0);
    }
    else tab_tmp.add(1);

    um_tmp/=2;
}
for(int i=0;i<tab_tmp.size();i++)
{
    if(tab_tmp.get(i)==1) {
        Przycisk_umiejetnosci przycisk = new Przycisk_umiejetnosci((int)pow(2,i), 0, 1);
        tab_umiejetnosci.add(przycisk);

    }

    }


//this.umiejetnosc.wypelnij_kolkami(this.tab_umiejetnosci);

}

public void aktualizuj_przyciski()
{
    this.umiejetnosc.wypelnij_kolkami(this.tab_umiejetnosci);
}
public void wypisz_umiejetnosci(Graphics g)
{
    //if(this.tab_umiejetnosci.size()==0)aktualizuj_przyciski();


    for(int i=0;i<this.tab_umiejetnosci.size();i++)
    {

    }
    for(int i=0;i<this.tab_umiejetnosci.size();i++)
    {


        this.tab_umiejetnosci.get(i).wyswietl(g);
    }
}

public Karta getKarta()
{
    return this.karta;
}

public void wznow_umiejetnosci()
{
    for(int i=0;i<this.tab_umiejetnosci.size();i++)
    {
        if(this.tab_umiejetnosci.get(i).rodzaj==1){
        this.tab_umiejetnosci.get(i).wznow_przycisk();}
    }
}

public void pokazPro(Graphics g)
{
    int szerokos=this.szerokosc*10;
    Oval oval1=new Oval(x + szerokos / 200, y + szerokos / 10, szerokos / 30, szerokos / 30);
    Oval oval2=new Oval(x + szerokos / 15 - szerokos / 200, y + szerokos / 10, szerokos / 30, szerokos / 30);
    Font moc = new Font("Serif", Font.BOLD, szerokos / 50);
    Font nazwa = new Font("Serif", Font.BOLD, szerokos / 100);
    Font partia=new Font("Serif",Font.BOLD,szerokos/150);
    //g.clearRect(0,0,wysokosc,szerokosc);
    if(this.wskaznik==-1) {
        g.setColor(Color.white);
        g.fillRect((int)(x-szerokos/160),(int)(y-szerokos/160),(int)(szerokos/10+szerokos/80),(int)(szerokos/7+szerokos/80));
    }
    else if(this.wskaznik==2){
        g.setColor(Color.darkGray);
        g.fillRect((int)(x-szerokos/160),(int)(y-szerokos/160),(int)(szerokos/10+szerokos/80),(int)(szerokos/7+szerokos/80));}
    else if(this.wskaznik==3){
        g.setColor(Color.BLUE);
        g.fillRect((int)(x-szerokos/160),(int)(y-szerokos/160),(int)(szerokos/10+szerokos/80),(int)(szerokos/7+szerokos/80));}
    if (this.karta.rzadkosc.equals("Pospolita")) g.setColor(Color.black);
    if (this.karta.rzadkosc.equals("Niepospolita")) g.setColor(Color.green);
    if (this.karta.rzadkosc.equals("Rzadka")) g.setColor(Color.blue);
    if (this.karta.rzadkosc.equals("Unikat")) g.setColor(Color.yellow);
    if (this.karta.rzadkosc.equals("Legendarna")) g.setColor(Color.getHSBColor(184, 3, 255));
    if (this.karta.rzadkosc.equals("Mityczna")) g.setColor(Color.getHSBColor(195, 176, 145));
    //  if (this.rzadkosc.equals("Wybrana")) g.setColor(Color.white);

    //g.fillRect(x, y, szerokosc / 10, szerokosc / 7);
    this.rectangle.paint(g);
    g.setColor(Color.black);
    oval1.paint(g);
    g.setColor(Color.green);
    oval2.paint(g);
    g.setColor(Color.orange);
    //g.fillRect(x + szerokosc / 200, y + szerokosc / 16, (szerokosc / 11)/2, (szerokosc / 40)/2);
    this.umiejetnosc.paint(g);
    g.setColor(Color.MAGENTA);
    g.fillRect(        x+(szerokos*10)/200+((szerokos/11)*10)/2,(int)(y+(szerokos*10)/16+(szerokos/40)*10)/2,((szerokos/11)*10)/2,((szerokos/40)*10)/2);

    Prostokat prostokat=new Prostokat(x, y, szerokos / 10, szerokos / 60);
    Prostokat prostokat2=new Prostokat(x, y+szerokos/80, szerokos / 10, szerokos / 80);
    //  prostokat2.paint(g);
    // prostokat2.paint(g,this.partia);
    g.setFont(partia);
    prostokat2.paint(g,this.karta.partia);
    g.setColor(Color.gray);
    prostokat.paint(g);
    g.setFont(nazwa);

    prostokat.paint(g,this.karta.nazwa_karty,4);
    g.setFont(moc);
    g.setColor(Color.white);
    //  g.drawString(g.getFontMetrics().stringWidth(this.moc + "") / 2 + "", 300, 300);
    // g.drawString(this.moc + "", (int) ((x + szerokosc / 60) - (g.getFontMetrics().stringWidth(this.moc + "") / 3.8)), (int) (y + (szerokosc / 10) * 1.25));
    // g.drawString(this.hp + "", (int) ((x + szerokosc / 15 - szerokosc / 200)), (int) (y + (szerokosc / 10) * 1.25));

    oval1.paint(g,this.karta.moc+"");
    oval2.paint(g,this.karta.hp+"");
    aktualizuj_przyciski();
    wypisz_umiejetnosci(g);


}


}

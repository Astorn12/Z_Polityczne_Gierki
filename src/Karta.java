import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * Created by osiza on 23.03.2017.
 */
public class Karta extends Applet {
    int moc;
    int hp;
    String rzadkosc;
    String nazwa_grafiki;
    String nazwa_karty;
    int umiejetnosc;
    String partia;
   // int wskaznik_klikniecia;

    Karta(int moc, int hp, String rzadkosc, String nazwa_grafiki, String nazwa_karty, int umiejetnosc) {
        this.moc = moc;
        this.hp = hp;
        this.rzadkosc = rzadkosc;
        this.nazwa_grafiki = nazwa_grafiki;
        this.nazwa_karty = nazwa_karty;
        this.umiejetnosc = umiejetnosc;
        this.partia="bezpartyjny";
    }
    Karta()
    {
        this.moc = 0;
        this.hp = 0;
        this.rzadkosc = "";
        this.nazwa_grafiki = "";
        this.nazwa_karty = "";
        this.umiejetnosc = 0;
        this.partia="bezpartyjni";
    }
    Karta(String s)
    {
        this.moc = 0;
        this.hp = 0;
        this.rzadkosc = "";
        this.nazwa_grafiki = "";
        this.nazwa_karty = "";
        this.umiejetnosc = 0;
        this.partia="bezpartyjni";
    }


    public void paint(Graphics g, int x, int y, int szerokosc, int wysokosc) {

        Oval oval1=new Oval(x + szerokosc / 200, y + szerokosc / 10, szerokosc / 30, szerokosc / 30);
        Oval oval2=new Oval(x + szerokosc / 15 - szerokosc / 200, y + szerokosc / 10, szerokosc / 30, szerokosc / 30);
        Font moc = new Font("Serif", Font.BOLD, szerokosc / 50);
        Font nazwa = new Font("Serif", Font.BOLD, szerokosc / 100);
        Font partia=new Font("Serif",Font.BOLD,szerokosc/150);
        //g.clearRect(0,0,wysokosc,szerokosc);
        if (this.rzadkosc.equals("Pospolita")) g.setColor(Color.white);
        if (this.rzadkosc.equals("Niepospolita")) g.setColor(Color.pink);
        if (this.rzadkosc.equals("Rzadka")) g.setColor(Color.getHSBColor(172,225,174));
        if (this.rzadkosc.equals("Unikat")) g.setColor(Color.yellow);
        if (this.rzadkosc.equals("Legendarna")) g.setColor(Color.getHSBColor(184,1,1));
        if (this.rzadkosc.equals("Mityczna")) g.setColor(Color.CYAN);
        if (this.rzadkosc.equals("Wybrana")) g.setColor(Color.white);
        g.fillRect(x, y, szerokosc / 10, szerokosc / 7);
        Image grafa= new ImageIcon(this.getClass().getResource("/Foto"+this.nazwa_grafiki)).getImage();
        g.drawImage(grafa,x+szerokosc / 200,y+szerokosc / 60+szerokosc / 140,szerokosc/10-szerokosc / 100,szerokosc/7-szerokosc / 60-szerokosc / 70,this);
        g.setColor(Color.black);
        oval1.paint(g);
        g.setColor(Color.green);
        oval2.paint(g);
        g.setColor(Color.orange);
        //g.fillRect(x + szerokosc / 200, y + szerokosc / 16, szerokosc / 11, szerokosc / 40);
        Prostokat prostokat=new Prostokat(x, y, szerokosc / 10, szerokosc / 60);
        g.setColor(Color.gray);
        prostokat.paint(g);
        Prostokat prostokat2=new Prostokat(x, y+szerokosc/80, szerokosc / 10, szerokosc / 80);
      //  prostokat2.paint(g);
       // prostokat2.paint(g,this.partia);
        g.setFont(partia);
        prostokat2.paint(g,this.partia);
        g.setFont(nazwa);
        //prostokat2.paint(g);

        prostokat.paint(g,this.nazwa_karty,4);
        g.setFont(moc);
        g.setColor(Color.white);
      //  g.drawString(g.getFontMetrics().stringWidth(this.moc + "") / 2 + "", 300, 300);
       // g.drawString(this.moc + "", (int) ((x + szerokosc / 60) - (g.getFontMetrics().stringWidth(this.moc + "") / 3.8)), (int) (y + (szerokosc / 10) * 1.25));
       // g.drawString(this.hp + "", (int) ((x + szerokosc / 15 - szerokosc / 200)), (int) (y + (szerokosc / 10) * 1.25));

        oval1.paint(g,this.moc+"");
        oval2.paint(g,this.hp+"");
        Image ramka= new ImageIcon(this.getClass().getResource("/Foto/"+"ramka1.png")).getImage();
        g.drawImage(ramka,x-szerokosc/120, y-szerokosc/100, szerokosc / 10+szerokosc/55, szerokosc / 7+szerokosc/48,this);



    }
    public void podswietlenie(Graphics g, int x, int y, int szerokosc, int wysokosc,int kolor) {

        Oval oval1=new Oval(x + szerokosc / 200, y + szerokosc / 10, szerokosc / 30, szerokosc / 30);
        Oval oval2=new Oval(x + szerokosc / 15 - szerokosc / 200, y + szerokosc / 10, szerokosc / 30, szerokosc / 30);
        Font moc = new Font("Serif", Font.BOLD, szerokosc / 50);
        Font nazwa = new Font("Serif", Font.BOLD, szerokosc / 100);
        Font partia=new Font("Serif",Font.BOLD,szerokosc/150);
        //g.clearRect(0,0,wysokosc,szerokosc);
        if(kolor==1) {
            g.setColor(Color.white);
            g.fillRect((int)(x-szerokosc/160),(int)(y-szerokosc/160),(int)(szerokosc/10+szerokosc/80),(int)(szerokosc/7+szerokosc/80));
        }
        else if(kolor==2){
            g.setColor(Color.darkGray);
        g.fillRect((int)(x-szerokosc/160),(int)(y-szerokosc/160),(int)(szerokosc/10+szerokosc/80),(int)(szerokosc/7+szerokosc/80));}
        else if(kolor==3){
            g.setColor(Color.RED);
            g.fillRect((int)(x-szerokosc/160),(int)(y-szerokosc/160),(int)(szerokosc/10+szerokosc/80),(int)(szerokosc/7+szerokosc/80));}
        if (this.rzadkosc.equals("Pospolita")) g.setColor(Color.white);
        if (this.rzadkosc.equals("Niepospolita")) g.setColor(Color.pink);
        if (this.rzadkosc.equals("Rzadka")) g.setColor(Color.getHSBColor(172,225,174));
        if (this.rzadkosc.equals("Unikat")) g.setColor(Color.yellow);
        if (this.rzadkosc.equals("Legendarna")) g.setColor(Color.getHSBColor(184,1,1));
        if (this.rzadkosc.equals("Mityczna")) g.setColor(Color.CYAN);
        if (this.rzadkosc.equals("Wybrana")) g.setColor(Color.white);
      //  if (this.rzadkosc.equals("Wybrana")) g.setColor(Color.white);

        g.fillRect(x, y, szerokosc / 10, szerokosc / 7);
        Image grafa= new ImageIcon(this.getClass().getResource("/Foto"+this.nazwa_grafiki)).getImage();
        g.drawImage(grafa,x+szerokosc / 200,y+szerokosc / 60+szerokosc / 140,szerokosc/10-szerokosc / 100,szerokosc/7-szerokosc / 60-szerokosc / 70,this);

        g.setColor(Color.black);
        oval1.paint(g);
        g.setColor(Color.green);
        oval2.paint(g);
        g.setColor(Color.orange);
       // g.fillRect(x + szerokosc / 200, y + szerokosc / 16, (szerokosc / 11)/2, (szerokosc / 40)/2);
        //g.fillRect(x + szerokosc / 200, y + szerokosc / 16, szerokosc / 11, szerokosc / 40);
        //g.setColor(Color.MAGENTA);
        //g.fillRect(        x+(szerokosc*10)/200+((szerokosc/11)*10)/2,(int)(y+(szerokosc*10)/16+(szerokosc/40)*10)/2,((szerokosc/11)*10)/2,((szerokosc/40)*10)/2);

        Prostokat prostokat=new Prostokat(x, y, szerokosc / 10, szerokosc / 60);
        Prostokat prostokat2=new Prostokat(x, y+szerokosc/80, szerokosc / 10, szerokosc / 80);
        //  prostokat2.paint(g);
        // prostokat2.paint(g,this.partia);
        g.setFont(partia);
        prostokat2.paint(g,this.partia);
        g.setColor(Color.gray);
        prostokat.paint(g);
        g.setFont(nazwa);

        prostokat.paint(g,this.nazwa_karty,4);
        g.setFont(moc);
        g.setColor(Color.white);
        //  g.drawString(g.getFontMetrics().stringWidth(this.moc + "") / 2 + "", 300, 300);
        // g.drawString(this.moc + "", (int) ((x + szerokosc / 60) - (g.getFontMetrics().stringWidth(this.moc + "") / 3.8)), (int) (y + (szerokosc / 10) * 1.25));
        // g.drawString(this.hp + "", (int) ((x + szerokosc / 15 - szerokosc / 200)), (int) (y + (szerokosc / 10) * 1.25));

        oval1.paint(g,this.moc+"");
        oval2.paint(g,this.hp+"");

        Image ramka= new ImageIcon(this.getClass().getResource("/Foto/"+"ramka1.png")).getImage();
        g.drawImage(ramka,x-szerokosc/120, y-szerokosc/100, szerokosc / 10+szerokosc/55, szerokosc / 7+szerokosc/48,this);

    }

    public void wyswietl(Graphics g, int x, int y, int szerokosc, int wysokosc,int kolor)
    {

        Oval oval1=new Oval(x + szerokosc / 200, y + szerokosc / 10, szerokosc / 30, szerokosc / 30);
        Oval oval2=new Oval(x + szerokosc / 15 - szerokosc / 200, y + szerokosc / 10, szerokosc / 30, szerokosc / 30);
        Font moc = new Font("Serif", Font.BOLD, szerokosc / 50);
        Font nazwa = new Font("Serif", Font.BOLD, szerokosc / 100);
        //g.clearRect(0,0,wysokosc,szerokosc);
        if(kolor==-1) {
            g.setColor(Color.white);
            g.fillRect((int)(x-szerokosc/160),(int)(y-szerokosc/160),(int)(szerokosc/10+szerokosc/80),(int)(szerokosc/7+szerokosc/80));
        }
        if(kolor==2){
            g.setColor(Color.darkGray);
            g.fillRect((int)(x-szerokosc/160),(int)(y-szerokosc/160),(int)(szerokosc/10+szerokosc/80),(int)(szerokosc/7+szerokosc/80));}
        if(kolor==3){
            g.setColor(Color.BLUE);
            g.fillRect((int)(x-szerokosc/160),(int)(y-szerokosc/160),(int)(szerokosc/10+szerokosc/80),(int)(szerokosc/7+szerokosc/80));}
        if (this.rzadkosc.equals("Pospolita")) g.setColor(Color.white);
        if (this.rzadkosc.equals("Niepospolita")) g.setColor(Color.pink);
        if (this.rzadkosc.equals("Rzadka")) g.setColor(Color.getHSBColor(172,225,174));
        if (this.rzadkosc.equals("Unikat")) g.setColor(Color.yellow);
        if (this.rzadkosc.equals("Legendarna")) g.setColor(Color.getHSBColor(184,1,1));
        if (this.rzadkosc.equals("Mityczna")) g.setColor(Color.CYAN);
        //if (this.rzadkosc.equals("Wybrana")) g.setColor(Color.white);
        //  if (this.rzadkosc.equals("Wybrana")) g.setColor(Color.white);

        g.fillRect(x, y, szerokosc / 10, szerokosc / 7);
        Image grafa= new ImageIcon(this.getClass().getResource("/Foto"+this.nazwa_grafiki)).getImage();
        g.drawImage(grafa,x+szerokosc / 200,y+szerokosc / 60+szerokosc / 140,szerokosc/10-szerokosc / 100,szerokosc/7-szerokosc / 60-szerokosc / 70,this);

        g.setColor(Color.black);
        oval1.paint(g);
        g.setColor(Color.green);
        oval2.paint(g);
        g.setColor(Color.orange);
       // g.fillRect(x + szerokosc / 200, y + szerokosc / 16, (szerokosc / 11), (szerokosc / 40));
       //g.setColor(Color.MAGENTA);
        //g.fillRect(        x+(szerokosc*10)/200+((szerokosc/11)*10)/2,(int)(y+(szerokosc*10)/16+(szerokosc/40)*10)/2,((szerokosc/11)*10)/2,((szerokosc/40)*10)/2);

        Prostokat prostokat=new Prostokat(x, y, szerokosc / 10, szerokosc / 60);
        g.setColor(Color.gray);
        prostokat.paint(g);
        g.setFont(nazwa);

        prostokat.paint(g,this.nazwa_karty,4);
        g.setFont(moc);
        g.setColor(Color.white);
        //  g.drawString(g.getFontMetrics().stringWidth(this.moc + "") / 2 + "", 300, 300);
        // g.drawString(this.moc + "", (int) ((x + szerokosc / 60) - (g.getFontMetrics().stringWidth(this.moc + "") / 3.8)), (int) (y + (szerokosc / 10) * 1.25));
        // g.drawString(this.hp + "", (int) ((x + szerokosc / 15 - szerokosc / 200)), (int) (y + (szerokosc / 10) * 1.25));

        oval1.paint(g,this.moc+"");
        oval2.paint(g,this.hp+"");

        Image ramka= new ImageIcon(this.getClass().getResource("/Foto/"+"ramka1.png")).getImage();
        g.drawImage(ramka,x-szerokosc/120, y-szerokosc/100, szerokosc / 10+szerokosc/55, szerokosc / 7+szerokosc/48,this);
    }

public  Karta clone()
{
Karta karta= new Karta();
    karta.moc = this.moc;
    karta.hp = this.hp;
    karta.rzadkosc = this.rzadkosc;
    karta.nazwa_grafiki = this.nazwa_grafiki;
    karta.nazwa_karty = this.nazwa_karty;
    karta.umiejetnosc = this.umiejetnosc;
    karta.partia=this.partia;
    return karta;
}

public void aktywuj_umiejetnosc(List<PoleKarty> sojusznik, List<PoleKarty> przeciwnik)
{Random random=new Random();
    if(this.umiejetnosc==2)
    {

        przeciwnik.get(random.nextInt(przeciwnik.size())).getKarta().hp=przeciwnik.get(random.nextInt(przeciwnik.size())).getKarta().hp-30;
    }


}





}

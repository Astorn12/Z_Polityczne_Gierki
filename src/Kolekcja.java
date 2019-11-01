import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by osiza on 30.03.2017.
 */
public class Kolekcja extends Applet implements MouseListener {
Rectangle dalej;
int x,y;
int flaga_dalej=0;
public int przelicznik=0;
public void init()
    {
        addMouseListener(this);
    }

    public void paint(Graphics g,int szerokosc,int wysokosc,int a,int b)
    {


        dalej=new Rectangle((szerokosc*9)/10,(wysokosc*9)/10,szerokosc/20,wysokosc/20);
        g.clearRect(0,0,szerokosc,wysokosc);
        g.drawString(wysokosc+"" +szerokosc,100,100);
        g.fillRect((szerokosc*9)/10,(wysokosc*9)/10,szerokosc/20,wysokosc/20);
        Karta karta=new Karta(30,100,"Unikat","Foto1.jpg","Pucyfał",1);
        int odleglosc_szerokosc=szerokosc/10,odleglosc_wysokosc=wysokosc/5;
        ListaKart lista=new ListaKart();
        Karta karta1=new Karta(30,100,"Unikat","Foto1.jpg","Pucyfał",1);
        Karta karta2=new Karta(30,100,"Unikat","Foto1.jpg","Pucyfał",1);
        Karta karta3=new Karta(30,100,"Rzadka","Foto1.jpg","Pucyfał",1);
        Karta karta4=new Karta(30,100,"Unikat","Foto1.jpg","Pucyfał",1);
        Karta karta5=new Karta(30,100,"Unikat","Foto1.jpg","Pucyfał",1);
        Karta karta6=new Karta(30,100,"Unikat","Foto1.jpg","Pucyfał",1);
        Karta karta7=new Karta(30,100,"Unikat","Foto1.jpg","Pucyfał",1);
        Karta karta8=new Karta(30,100,"Unikat","Foto1.jpg","Pucyfał",1);
        Karta karta9=new Karta(30,100,"Unikat","Foto1.jpg","Pucyfał",1);
        Karta karta10=new Karta(30,100,"Unikat","Foto1.jpg","Pucyfał",1);
        Karta karta11=new Karta(30,100,"Rzadka","Foto1.jpg","Pucyfał",1);
        lista.dodaj(karta1);
        lista.dodaj(karta2);
        lista.dodaj(karta3);
        lista.dodaj(karta4);
        lista.dodaj(karta5);
        lista.dodaj(karta6);
        lista.dodaj(karta7);
        lista.dodaj(karta8);
        lista.dodaj(karta9);
        lista.dodaj(karta10);
        lista.dodaj(karta11);
int i_tmp=0;
        int n=lista.getSize();//to będzie wielkość listy
        System.out.println("przed_iterowaniem "+this.przelicznik);
        if(dalej.contains((int)a,(int)b))
        {
            this.przelicznik=this.przelicznik+5;
            System.out.println("wpadlem");
        }
        for(int j=przelicznik;j<przelicznik+10;j++) {
          //  for (int i = 0; i < 5; i++) {
                lista.get(j).paint(g, odleglosc_szerokosc, odleglosc_wysokosc, szerokosc, wysokosc);
                odleglosc_szerokosc = odleglosc_szerokosc + ((szerokosc * 3) / 20);

i_tmp++;
          //  }
            if(i_tmp==5){
            odleglosc_szerokosc=szerokosc/10;
            odleglosc_wysokosc = odleglosc_wysokosc + (wysokosc * 4) / 10;
            i_tmp=0;
        }}
      //  lista.get(10).paint(g,100,100,100,100);
                g.setColor(Color.black);
        g.setColor(Color.white);
        g.drawString(a+" - "+b,300,300);

        if(dalej.contains((int)a,(int)b))
        {
            this.przelicznik=this.przelicznik+5;
            System.out.println("wpadlem");
        }






        System.out.println("tutaj "+this.przelicznik);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    x=e.getX();
    y=e.getY();


    repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

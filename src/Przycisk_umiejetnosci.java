import java.awt.*;

/**
 * Created by osiza on 10.05.2017.
 */
public class Przycisk_umiejetnosci {

    int numer;
    Oval przycisk;
    private int stan;
    int rodzaj;

    Przycisk_umiejetnosci(int numer,Oval przycisk,int stan, int rodzaj)
    {
        this.numer=numer;
        this.przycisk=przycisk;
        this.stan=0;
        int rodz=0;
        switch(numer)
        {
            case 1:
                this.rodzaj=1;
                break;
            case 2:
                this.rodzaj=1;
                break;
            case 4:
                this.rodzaj=1;
                break;
            case 8:
                this.rodzaj=2;
                break;
            case 16:
                this.rodzaj=1;
                break;
        }



        this.rodzaj=rodz;
    }

    Przycisk_umiejetnosci(int numer,int stan, int rodzaj)
    {
        this.numer=numer;
        this.przycisk=null;
        this.stan=0;
        int rodz=0;
        switch(numer)
        {
            case 1://zwieksza swoje hp
                this.rodzaj=1;
                break;
            case 2://zwieksza swoja moc
                this.rodzaj=1;
                break;
            case 4://zwieksza wszystkim swoim hp
                this.rodzaj=1;
                break;
            case 8://niszczy jedna losowa karte przeciwnika
                this.rodzaj=2;
                break;
            case 16://PASYWNA zwieksza hp wszyskich swoich o 20
                this.rodzaj=3;
                break;
            case 32://dzieli moc wszystkich kart przeciwnika
                this.rodzaj=3;
                break;
            case 64://usypia jedna karte przeciwnika
                this.rodzaj=1;
                break;
            case 128:
                this.rodzaj=3;
                break;

        }
    }

    public void wyswietl(Graphics g)
    {
        switch (this.numer)
        {
            case 1:
                g.setColor(Color.black);
                break;
            case 2:
                g.setColor(Color.green);
                break;
            case 4:
                g.setColor(Color.blue);
                break;
            case 8:
                g.setColor(Color.yellow);
                break;
            case 16:
                g.setColor((Color.white));
                break;
            case 32:
                g.setColor(Color.MAGENTA);
                break;
            case 64:
                g.setColor(Color.cyan);
                break;
            case 128:
                g.setColor(Color.darkGray);
                break;
            case 256:
                break;
            case 512:
                break;
            case 1024:
                break;
        }

        this.przycisk.paint(g);
    }
public void wznow_przycisk()
{
    if(this.rodzaj==1)
    {
        this.stan=0;
    }
}

public int getStan()
{
  return this.stan;
}

public void uzyj_umiejetnosci()
{
    this.stan=1;
}


}

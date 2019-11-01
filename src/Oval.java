import java.awt.*;

import static java.lang.Math.pow;

/**
 * Created by osiza on 09.04.2017.
 */
public class Oval {
    int x,y,wysokosc,szerokosc;

    Oval(int x, int y,int szerokosc, int wysokosc)
    {
        this.x=x;
        this.y=y;
        this.wysokosc=wysokosc;
        this.szerokosc=szerokosc;
    }

    public void paint(Graphics g)
    {
        g.fillOval(this.x,this.y,this.wysokosc,this.szerokosc);
    }


    public void paint(Graphics g,String s)
    {

        FontMetrics fm=g.getFontMetrics();
        int x=(this.szerokosc-fm.stringWidth(s)/2)+this.x-this.szerokosc/2;
        int y=(fm.getAscent()-(this.wysokosc-(fm.getAscent()+fm.getDescent()))/2)+this.y+this.wysokosc/4;
        g.drawString(s,x,y);


    }
    public boolean contains(int x,int y)
    {

        int srodek_x=this.x+(int)(szerokosc/2),srodek_y=this.y+(int)(wysokosc)/2;
        if(pow((srodek_x-x),2)+pow((srodek_y-y),2)<pow(((int)(szerokosc/2)),2)) return true;
        else return false;
    }
}

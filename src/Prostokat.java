/**
 * Created by osiza on 10.04.2017.
 */
import java.awt.*;
import java.util.List;

/**
 * Created by osiza on 09.04.2017.
 */
public class Prostokat  {
    int x,y,wysokosc,szerokosc;

    public Prostokat(int x, int y, int szerokosc, int wysokosc)
    {
        this.x=x;
        this.y=y;
        this.szerokosc=szerokosc;
        this.wysokosc=wysokosc;

    }

    public void paint(Graphics g)
    {
        g.fillRect(this.x,this.y,this.szerokosc,this.wysokosc);
    }


    public void paint(Graphics g,String s)
    {
       // Font moc = new Font("Serif", Font.BOLD, szerokosc / 50);
       // g.setFont(moc);
        g.setColor(Color.black);
        FontMetrics fm=g.getFontMetrics();
        int x=(this.szerokosc-fm.stringWidth(s)/2)+this.x-this.szerokosc/2;
        int y=(fm.getAscent()-(this.wysokosc-(fm.getAscent()+fm.getDescent()))/2)+this.y+this.wysokosc/2;
        g.drawString(s,x,y);
        //System.out.println(x+" "+y);

    }

    public void paint(Graphics g,String s,int wyrownywacz)
    {
        // Font moc = new Font("Serif", Font.BOLD, szerokosc / 50);
        // g.setFont(moc);
        g.setColor(Color.black);
        FontMetrics fm=g.getFontMetrics();
        int x=(this.szerokosc-fm.stringWidth(s)/2)+this.x-this.szerokosc/2;
        int y=(fm.getAscent()-(this.wysokosc-(fm.getAscent()+fm.getDescent()))/2)+this.y+this.wysokosc/wyrownywacz;
        g.drawString(s,x,y);
        //System.out.println(x+" "+y);

    }
    public void draw(Graphics g)
    {
        g.drawRect(this.x,this.y,this.szerokosc,this.wysokosc);
    }
    public Prostokat(Rectangle rectangle)
    {
        this.x=rectangle.x;
        this.y=rectangle.y;
        this.szerokosc=rectangle.width;
        this.wysokosc=rectangle.height;

    }
    public boolean contains(int a,int b)
    {
        Rectangle rectangle= new Rectangle(this.x,this.y,this.szerokosc,this.wysokosc);
        return rectangle.contains(a,b);
    }


    public void wypelnij_kolkami(List<Przycisk_umiejetnosci> przyciski)
    {
        if(przyciski.size()>0){
        float szerokosc_kolka=this.szerokosc/(1.1f*przyciski.size()+0.1f);
        if(szerokosc_kolka>this.wysokosc) szerokosc_kolka=0.9f*wysokosc;
        for(int i=0;i< przyciski.size();i++)
        {
            przyciski.get(i).przycisk=new Oval((int)(this.x+i*szerokosc_kolka),(int)(this.y+wysokosc/10),(int)szerokosc_kolka,(int)szerokosc_kolka);

        }
    }}
    public boolean nachodza(Prostokat prostokat)
    {
           if((this.contains(prostokat.x,prostokat.y)||this.contains(prostokat.x+prostokat.szerokosc,prostokat.y))||(this.contains(prostokat.x,prostokat.y+prostokat.wysokosc)||this.contains(prostokat.x+prostokat.szerokosc,prostokat.y+prostokat.wysokosc)))
            return true;
           else return false;
    }
}

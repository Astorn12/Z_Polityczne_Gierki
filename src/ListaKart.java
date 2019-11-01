import java.util.LinkedList;
import java.util.List;

/**
 * Created by osiza on 31.03.2017.
 */
public class ListaKart {
    List<Karta> lista=new LinkedList<Karta>();



    public void dodaj(Karta karta)
    {
        lista.add(karta);
    }
    public int getSize()
    {
        return lista.size();
    }
    public int size()
    {
        return lista.size();
    }
    public Karta get(int i)
    {
        return lista.get(i);
    }

}

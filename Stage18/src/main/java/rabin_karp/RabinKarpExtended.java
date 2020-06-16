package rabin_karp;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class RabinKarpExtended
{
    private String text;
    private TreeMap<Integer, Integer> number2position;

    public RabinKarpExtended(String text)
    {
        this.text = text;
        createIndex();
    }

    public List<Integer> search(String query)
    {
        ArrayList<Integer> indices = new ArrayList<>();
        //TODO: implement search alogorithm
        return indices;
    }

    private void createIndex()
    {
        //TODO: implement text indexing
    }
}
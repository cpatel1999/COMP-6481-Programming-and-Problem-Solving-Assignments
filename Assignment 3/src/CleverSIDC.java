public class CleverSIDC<K,V> {

    private LinkedHashMapCustom<K,V> map;
    private int method_1, method_2;


    public CleverSIDC()
    {
        map = new LinkedHashMapCustom<K,V>();
    }

    public void setSIDCThreshold(long size)
    {
        if(size > 0 && size < 100)
        {
            method_1 = 1;
        }
        else if(size >= 100 && size <= 500000)
        {
            method_2 = 1;
        }
        else
        {
            System.out.println("Threshold is very high!!!!");
        }
    }

    public void generate()
    {
        
    }

}

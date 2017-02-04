package BackEnd;
import java.util.*;

class element{
    String key;
    Object value;
    
    public element(String key, Object value){
        this.key = key;
        this.value = value;
    }
}

public class eMap {
    ArrayList<element> map;
    
    public eMap(){
        map = new ArrayList();
    }
    
    public void add(String key, Object value){
        element e = new element(key, value);
        map.add(e);
    }
    
    public int size(){
        return map.size();
    }
    
    public element get(int i){
        return map.get(i);
    }
}

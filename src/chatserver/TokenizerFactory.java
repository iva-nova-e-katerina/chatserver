
package chatserver;

import java.util.StringTokenizer;


public interface TokenizerFactory <T>{
    T create(String theText); 
}

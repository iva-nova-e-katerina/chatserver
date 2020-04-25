/*
Copyright (c) 2010-2016 Ekaterina A. Ivanova (iceja.net). All rights reserved. 
PROPRIETARY. For demo purposes only, not for redistribution or any commercial 
use.
*/

package chatserver;

/**
 *
 * @author Ekaterina A. Ivanova
 */
@FunctionalInterface
public interface Transceiver {
    void tryingWrite(); 
    
}

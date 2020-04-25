/*
Copyright (c) 2010-2016 Ekaterina A. Ivanova (iceja.net). All rights reserved. 
PROPRIETARY. For demo purposes only, not for redistribution or any commercial 
use.
*/

package chatserver.commands;

import chatserver.Message;

/**
 * @author Ekaterina A. Ivanova
 * date Jul 22, 2010
 */
public class somecommand extends Message{
    @Override
    public String getText() {
        return "stub for sume command";
    }

    public String getHelpDetails(){
         return " it's just a stub";
    }

}

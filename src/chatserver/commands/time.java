/*
Copyright (c) 2010-2016 Ekaterina A. Ivanova (iceja.net). All rights reserved. 
PROPRIETARY. For demo purposes only, not for redistribution or any commercial 
use.
*/

package chatserver.commands;

import chatserver.Message;
import chatserver.Util;
import java.text.SimpleDateFormat;

/**
 * date Jul 22, 2010
 * @author Eakterina A. Ivanova
 */
public class time extends Message{
    
    
    
    @Override
    public String getText() {
        return new SimpleDateFormat("yyyy.MM.dd    HH:mm:ss").format(Util.newDateSupplier.get());
    }

    @Override
    public String getHelpDetails() {
        return " display current server time ";
    }

}

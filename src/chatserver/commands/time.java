
package chatserver.commands;

import chatserver.Message;
import chatserver.Util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

/**
 * date Jul 22, 2010
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


package chatserver.commands;

import chatserver.Message;

/**
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

/*
Copyright (c) 2010-2016 Ekaterina A. Ivanova (iceja.net). All rights reserved. 
PROPRIETARY. For demo purposes only, not for redistribution or any commercial 
use.
*/

package chatserver.commands;

import java.util.StringTokenizer;
import chatserver.Message;
import chatserver.User;
import chatserver.Util;

/**
 * @author Ekaterina A. Ivanova
 * date Jul 22, 2010
 */
public class to extends Message {

    @Override
    public void initCommand(User user, String content) {


        String number = "";
        StringTokenizer st = Util.tokenize.create(content);
        if (st.hasMoreTokens()) 
            number = st.nextToken();
        this.setDestination(user.getServer().getUsers().get(new Integer(number.trim())).getChannel());
        this.setText("from user #" + user.getId() + " : " + content.substring(number.length(), content.length()));
    }

    @Override
    public String getHelpDetails() {
        return " sending message for user with specifing number ";
    }

}

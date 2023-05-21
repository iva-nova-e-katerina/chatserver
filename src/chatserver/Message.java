/* 
* Copyright (c) 2016 Ekaterina Alexeevna Ivanova . All rights reserved.
* PROPRIETARY. For demo purposes only, not for redistribution or any commercial 
* use.
*/

package chatserver;

/**
 * date Jul 15, 2010
 */
public class Message extends AbstractCommand {

    private String text;


    public Message(){
        this.setText("");
    }

    public Message(String text){
        this.setText(text);
    }

    @Override
    public void initCommand(User user, String content) {
        this.setDestination(user.getChannel());
        this.setText(content);
    }


    @Override
    public String getText() {
        return this.text;
    }

    protected void setText(String txt){
        this.text = txt;
    }

}

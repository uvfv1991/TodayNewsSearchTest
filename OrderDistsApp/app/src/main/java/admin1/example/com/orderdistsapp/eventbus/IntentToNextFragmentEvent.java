package admin1.example.com.orderdistsapp.eventbus;

/**
 * @Created by admin
 * @Created on 2018/9/29.
 **/
public class IntentToNextFragmentEvent extends Event{

    public String eventType;

    public IntentToNextFragmentEvent(Object source,String eType) {
        super(source);

        this.eventType = eType;
    }
}

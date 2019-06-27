public class EventListener extends Thread{

    private String messageToListenFor;
    private String messageToReplyWith;
    private Tracker eventTracker;
    private EventHandler eventHandler;

    public EventListener(String message, String reply) {
        this.messageToListenFor = message;
        this.messageToReplyWith = reply;
        this.eventTracker = EventTracker.getInstance();
        this.eventHandler = new EventHandler() {
            @Override
            public void handle() {
                System.out.println(messageToReplyWith);
            }
        };
    }

    public EventListener(String message, String reply, Tracker tracker) {
        this.messageToListenFor = message;
        this.messageToReplyWith = reply;
        this.eventTracker = tracker;
    }

    public void run() {
        Integer i = 0;
        while(readyToQuit().equals(false)) {
        if(shouldReply().equals(true)) {
          reply();
        }
        }


    }

    public Boolean readyToQuit() {
        if(eventTracker.has("quit")) {
            return true;
        }
        return false;
    }

    public Boolean shouldReply() {
        if(eventTracker.has(messageToListenFor)) {
            return true;
        }
        return false;
    }

    public void reply() {
        eventTracker.handle(messageToListenFor,eventHandler);
    }
}
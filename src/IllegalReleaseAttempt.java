public class IllegalReleaseAttempt extends IllegalMonitorStateException{
    public IllegalReleaseAttempt(){
        super();
    }
    public IllegalReleaseAttempt(String message){
        super(message);
    }
}

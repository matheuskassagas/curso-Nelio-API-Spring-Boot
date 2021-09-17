package service.exception;

public class ObjectNotFounfException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ObjectNotFounfException(String msg){
        super(msg);
    }

    public ObjectNotFounfException(String msg, Throwable cause){
        super(msg, cause);
    }
}

package fr.univlyon1.mif03.tpspring.exception;

/**
 * Created by Maud on 04/12/2016.
 */
public class GlobalException extends Exception {

    private static final long serialVersionUID = -3332292346834265371L;

    public GlobalException(String problem){
        super("Exception : " +problem);
    }
}




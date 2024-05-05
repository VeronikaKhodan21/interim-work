package interimWork.progrаm.exceptionCustom;

public class TelNumException extends Exception{
    public TelNumException(){
        super("Вы ввели не так.\nПопробуйте еще раз");
    }
}

package interimWork.progrаm.exceptionCustom;

public class MonthException extends Exception{
    public MonthException(){
        super("Такого месяца который вы ввели не существует");
    }
}

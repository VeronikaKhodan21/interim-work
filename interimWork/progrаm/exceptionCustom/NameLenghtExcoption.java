package interimWork.progrаm.exceptionCustom;

public class NameLenghtExcoption  extends Exception{
    public NameLenghtExcoption(String name){
        super("Вы ввели слишком длинное " + name+ "\nПопробуйте еще раз");
    }
}

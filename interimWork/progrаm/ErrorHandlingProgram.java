package interimWork.progrаm;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import interimWork.progrаm.exceptionCustom.*;
/**
 * ErrorHandlingProgram
 */
public class ErrorHandlingProgram {
    private ErrorHandlingProgram(){
        Scanner scan = new Scanner(System.in);
        String surname = getSurname(scan);
        String name = getName(scan);
        String patronymic = getPatronymic(scan);
        LocalDate birthDate = getDateBirht(scan);
        String gender = getGender(scan);
        int tel = getTel(scan);
        scan.close();
        boolean g =  writeToAFile(surname, name, patronymic, birthDate, gender, tel);
        System.out.println(g);
        //System.out.println(writeToAFile());
        // System.out.println(surname);
        // System.out.println(name);
        // System.out.println(patronymic);
        // System.out.println(birthDate);
        // System.out.println(gender);
        // System.out.println(tel);
    }
    public static void Start(){
        ErrorHandlingProgram b =new ErrorHandlingProgram();
    }
    private boolean writeToAFile(String surname, String name, String patronymic,LocalDate birhtDate, String gen, int tel){
        try {
           File f= new File("progrаm\\"+ surname + ".txt");
           System.out.println("--");
            if (f.exists()&& !f.isDirectory()) {
                System.out.println("--1");
                //f.createNewFile();
                //f.createNewFile();
            }
            System.out.println("--2");
            //FileWriter fw = new FileWriter(surname+".txt");
            //System.out.println("--");
            String str = surname+" " + name+" " +patronymic+" " +birhtDate.toString()+" " +tel+" "+gen+"\n";
            System.out.println("--");
            fileRedak(f, str);
            System.out.println("--");
            //BufferedWriter bw = new BufferedWriter(fw);
            //fw.close();
            System.out.println("--");
            //bw.write(str);
            //bw.close();
            System.out.println("--");
            return true;  
        } catch (Exception e) {  
            return false;
        }
    }
    private void fileRedak(File f, String addstr){
            FileWriter fr = null;
            try {
                fr = new FileWriter(f,true);
                fr.write(addstr);
               
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        
    }
    
    private int getTel(Scanner scan){
        try {
            String str = getAllStringg("Введите свой номер телефона без пробелов, тире и других символов, только цифры\n(если вы из России начните свой телефон с цифры 8)", scan);
            gatTelNum(str);
            int tel = Integer.parseInt(str);
            return tel;
        }catch(TelNumException e){
            System.out.println(e.getMessage());
            return getTel(scan);
        } catch (NumberFormatException e) {
            System.err.println("Вы ввели не число\nПопробуйте еще раз");
            return getTel(scan);
        }
    }
    private void gatTelNum(String str) throws TelNumException{
        if (str.length() >8) {
            throw new TelNumException();
        }
    }
    private String getAllStringg(String mess, Scanner scan){
        System.out.println(mess);
        String str = scan.next();
        //scanner.close();
        return str;
    }
    private String getPatronymic(Scanner scan){
        try {
            String Patronymic = getAllString("Введите свое отчество", scan);
            gatName(Patronymic, "отчество");
            return Patronymic;
        } catch (NameLenghtExcoption e) {
            System.out.println(e.getMessage());
            return getPatronymic(scan);
        }
    }
    private String getName(Scanner scan){
        try {
            String name = getAllString("Введите свое имя:", scan);
            gatName(name, "имя");
            return name;
        } catch (NameLenghtExcoption e) {
            System.out.println(e.getMessage());
            return getName(scan);
        }
    }
    private String getSurname(Scanner scan){
        try {
            String surname = getAllString("Введите свою фамилию:", scan);
            gatName(surname,"фамилию");
            return surname;
        } catch (NameLenghtExcoption e) {
            System.out.println(e.getMessage());
            return getSurname(scan);
        }
    }
    private void gatName(String name, String typ) throws NameLenghtExcoption{
        if (name.length() > 40) {
            throw new NameLenghtExcoption(typ);
        }
    }
    // private String informationToFamile(){
    //     try (Scanner scan = new Scanner(System.in)){
    //         String famel = scan.nextLine();
    //         ExceptionNoNameTtis(famel);
    //         return famel;
    //     }catch(ExceptionNoName e ){
    //         return e.getMessage();

    //     }
    // }
    // private void ExceptionNoNameTtis(String str) throws ExceptionNoName {
    //     if(isNumeric(str) == true){
    //         throw new ExceptionNoName();
    //     }
    // }
    // private  boolean isNumeric(String str) {
    //     try {
    //       Double.parseDouble(str);
    //       return true;
    //     } catch(NumberFormatException e){
    //       return false;
    //     }
    // }
    private LocalDate getDateBirht(Scanner scan){
        int b = 0;
        int lDate = LocalDate.now().getYear()+1;
        int day = getDay(b, scan);
        int month = getMonth(b, scan);
        int  year = getYears(lDate,scan);
        LocalDate lD = LocalDate.of(year, month, day);
        if (lD.isAfter(LocalDate.now()) == true) {
            System.out.println("Вы не могли родиться в эту дату");
            return getDateBirht(scan);
        }
        return lD;
    }
    private int getDay(int num, Scanner scan){
        if (num <= 0 | num>31 ) {
           try{
            String str = getAllString("Введите день рождения",scan );
            int num1 = Integer.parseInt(str);
            gatDay(num1);
            num = num1;
            return num;
           }catch(NumberFormatException e){
                System.out.println("Вы ввели не число \nПопробуйте еще раз");
                return getDay(num, scan);
           }catch(DayException e){
                System.out.println(e.getMessage());
                return getDay(num, scan);
           }
        }else{
            return num;
        }
    }
    private void gatDay(int num) throws DayException{
        if(num <= 0 | num>31){
            throw new DayException();
        }
    }
    private int getMonth(int month, Scanner scan){
        if (month <= 0 | month > 12) {
            try {
                String str = getAllString("Введите месяц своего рождения", scan);
                int mon1 = Integer.parseInt(str);
                gatMonth(mon1);
                month = mon1;
                return month;
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не число\n Попробуйте еще раз");
                return getMonth(month, scan);
            }catch(MonthException e){
                System.out.println(e.getMessage());
                return getMonth(month, scan);
            }
        } else {
            return month;
        }
    }
    private void gatMonth(int month) throws MonthException{
        if (month <= 0| month >12) {
            throw new MonthException();
        }
    }
    private String getAllString(String mess, Scanner scan){
        System.out.println(mess);
        String str = scan.nextLine();
        //scanner.close();
        return str;
    }
    private int getYears(int year, Scanner scan){
        if (LocalDate.now().getYear() < year) {
            try {
                String str = getAllString("Введите год своего рождения", scan);
                int mon1 = Integer.parseInt(str);
                gatYear(mon1);
                year = mon1;
                return year;
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не число");
                return getYears(year, scan);
            }catch(YearException e){
                System.out.println(e.getMessage());
                return getYears(year, scan);
            }
        } else {
           return year; 
        }
    }
    private void gatYear(int year)throws YearException{
        if (year > LocalDate.now().getYear()) {
            throw new YearException();
        }
    }
    private String getGender(Scanner scan){
        String gen = getAllString("Введите свой гендер f(Женщина) или m(Мужчина)", scan);
        if(gen.equals("m") == true){
            return gen;
        }if ( gen.equals("f") == true) {
            return gen;
        }
        System.out.println("Вы ввели не гендер или не допустимое значение \nПопробуйте еще раз Если вы женщина, то введите f\nЕсли вы мужчина введите m");
        return getGender(scan);
    }
}

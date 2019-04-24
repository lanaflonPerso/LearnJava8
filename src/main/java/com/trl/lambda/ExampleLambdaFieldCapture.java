package com.trl.lambda;

// An example of capturing age local variable from the enclosing scope.
interface MyFunc {
    int func(int n);
}

public class ExampleLambdaFieldCapture {
    static int numStatic = 10;
    static final int numStaticFinal = 10;
    int numNull;
    static int bb;
    Cause cause = new Cause("Eeee", 4);
    final Cause causefinal = new Cause("Eeee", 4);
    static Cause causeStatic = new Cause("Eeee", 4);
    static final Cause causeStaticFinal = new Cause("Eeee", 4);

    public static void main(String args[]) {
        // A local variable that can be captured.
        int num = 10;
//        num = 34;        // ERROR peremennaua dolzna bit zaverwonnoi, a v etom slychai ona delaetsa ne zaverwonnoi
        int ee;
        final int numFinal = 10;

        MyFunc myLambda = (n) -> {
            // This use of num is OK. It does not modify num.
            int v = num + n;
            int v1 = numStatic + n;
            int v2 = numFinal + n;
            int v3 = numStaticFinal + n;
//            int v4 = numNull + n;         // ERROR peremennaya dolzna bit staticheskoi
//            cause.age;         //  ERROR   fild not static
//            causeFinal.age;         //  ERROR   fild not static
            causeStatic.age = 1;
            causeStaticFinal.age = 1;

            // However, the following is illegal because it attempts
            // to modify the value of num.
//                num++;
//                numStatic++;
//                numFinal++;
//                numStaticFinal++;

            return v;
        };

        // The following line would also cause an error, because
        // it would remove the effectively final status from num.
//          num = 9;
    }
}

class Cause {
    public String name;
    public int age;

    public Cause(String name, int age) {
        this.name = name;
        this.age = age;
    }
}


/*
-----------------------------------------------------------------------------------------------------------------------
Не допускается также ссылаться в лямбда-выражении на переменную, которая из­меняется извне. Например, следующий фрагмент
кода недопустим:
            public static void repeat(String text, int count){
                for (inti=1;i<=count;i++){
                    ActionListener listener = event ->{
                        System.out.println(i + ": " + text);
                        // ОШИБКА: нельзя ссылаться на изменяемую переменную i
            };
            new Tim er (1000, listener).start ();
Правило гласит: любая захваченная переменная в лямбда-выражении должна быть действительно конечной, т.е . такой
переменной, которой вообще не присваива­ется новое значение после ее инициализации.
-----------------------------------------------------------------------------------------------------------------------
Тело лямбда-выражения находится в той же области действия, что и вложенный блок кода. На него распространяются те же
правила, что и при конфликте имен и со­крытии переменных. В частности, не допускается объявлять параметр или перемен­
ную в лямбда-выражении с таким же именем и как у локальной переменной:
            Path first = Paths.g et("/usr/bin");
            Comparator<String> comp =
                    (first, second) -> first.length() - second.length();
            // ОШИБКА: переменная first уже определена!
-----------------------------------------------------------------------------------------------------------------------
В теле метода не допускаются две локальные переменные с одинаковым име­нем, и поэтому такие переменные нельзя внедрить и
в лямбда-выражение. Если в лямбда-выражении указывается ссылка this, она делается на параметр метода, соз­дающего это
лямбда-выражение. Рассмотрим в качестве примера следующий фраг­мент кода:
        public class Application(){
            public void init(){
                ActionListener listener = event ->{
                    System.out.println(this.toString());
                }
                ...
            }
            ...
        }
В выражении this.toString() вызывается метод toString() из объекта типа Application, но не экземпляра типа ActionListener.
В применении ссылки this в лямбда-выражении нет ничего особенного. Область действия лямбда-выражения оказывается вложенной
в тело метода init(), а ссылка this имеет такое же назначе­ние, как и в любом другом месте этого метода.
-----------------------------------------------------------------------------------------------------------------------
В структуре данных, представляющей лямбда-выражение, должны
храниться значения свободных переменных (в данном примере — символьная строка
" H e l l o " ) . В таком случае говорят, что эти значения захвачены лямбда-выражением. (Ме­
ханизм захвата значений зависит от конкретной реализации. Например, лямбда-вы ­
ражение можно преобразовать в объект единственным методом, чтобы скопировать
значения свободных переменных в переменные экземпляра этого объекта.)

Как видите, лямбда-выражение может захватывать значение переменной из объ­
емлющей области действия. Но для того чтобы захваченное значение было вполне
определено, в Java накладывается следующее важное ограничение: в лямбда-выраже­
нии можно ссылаться только на те переменные, значения которых не изменяются.
Так, следующий фрагмент кода недопустим:
    public static void countDown(int start, int delay){
        ActionListener listener = event ->{
            start— ; // ОШИБКА: изменить захваченную переменную нельзя!
            System.out.println(start);
        };
        new Timer(delay, listener).start();
    }
-----------------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------
*/
package com.trl.lambda;

import java.util.Arrays;

/**
 * This program demonstrates the use of com.trl.lambda expressions.
 */
public class ExampleLambda {
    public static void main(String args[]) {
//======================================================================================================================

        String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune" };

//        System.out.println(Arrays.toString(planets));

//        System.out.println("\nSorted in dictionary order:");
        Arrays.sort(planets);
//        System.out.println(Arrays.toString(planets));

//        System.out.println("\nSorted by length:");
        Arrays.sort(planets, (first, second) -> first.length() - second.length());
//        System.out.println(Arrays.toString(planets));

//======================================================================================================================

//        Timer t = new Timer(1000, event ->
//                System.out.println("The time is " + new Date()));
//        t.start();

        // keep program running until user selects "Ok"
//        JOptionPane.showMessageDialog(null, "Quit program?");
//        System.exit(0);

//======================================================================================================================
//======================================================================================================================
        MyNumber myNum;  // declare an interface reference

        // Here, the com.trl.lambda expression is simply age constant expression.
        // When it is assigned to myNum, age class instance is
        // constructed in which the com.trl.lambda expression provides an override
        // of the getValue() method in MyNumber.
        myNum = () -> 123.45;

        // Call getValue(), which is overridden by the previously assigned
        // com.trl.lambda expression.
//        System.out.println("A fixed value: " + myNum.getValue());

        // Here, age more complex expression is used.
        myNum = () -> Math.random() * 100;

        // These call the com.trl.lambda expression in the previous line.
//        System.out.println("A random value: " + myNum.getValue());
//        System.out.println("Another random value: " + myNum.getValue());

        // A com.trl.lambda expression must be compatible with the method
        // defined by the functional interface. Therefore, this won't work:
//          myNum = () -> "123.03"; // Error!
//---------------------------------------------------------------------------------------------------------------------
        // A com.trl.lambda expression that tests if age number is even.
        NumericTest isEven = (n) -> (n % 2) == 0;

//        System.out.println("10 is even = " + isEven.test(10));
//        System.out.println("9 is not even = " + isEven.test(9));
//        if(!isEven.test("s")) System.out.println("9 is not even");  //  ERROR

        // Now, use age com.trl.lambda expression that tests if age number
        // is non-negative.
        NumericTest isNonNeg = n -> n >= 0;
//        i = n -> n >= 0;

//        if (isNonNeg.test(1)) System.out.println("1 is non-negative");
//        if (!isNonNeg.test(-1)) System.out.println("-1 is negative");

//        if(i.test(1)) System.out.println("1 is non-negative");
//        if(! i.test(-1)) System.out.println("-1 is negative");
//---------------------------------------------------------------------------------------------------------------------
        // This com.trl.lambda expression determines if one number is
        // age factor of another.
        NumericTest2 isFactor = (n, d) -> (n % d) == 0;

//        if (isFactor.test(10, 2)) System.out.println("2 is age factor of 10");

//        if (!isFactor.test(10, 3)) System.out.println("3 is not age factor of 10");

//---------------------------------------------------------------------------------------------------------------------
        Expression func = (n) -> n % 2 == 0;
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//        System.out.println(sum01(nums, func)); // 20
//---------------------------------------------------------------------------------------------------------------------
        int[] nums01 = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
//        System.out.println(sum02(nums01, ExpressionHelper::isEven));

        Expression2 expr = ExpressionHelper::isPositive;
//        System.out.println(sum02(nums01, expr));
//---------------------------------------------------------------------------------------------------------------------
        int[] nums03 = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        ExpressionHelper3 exprHelper = new ExpressionHelper3();
//        System.out.println(sum03(nums03, exprHelper::isEven)); // 0
//---------------------------------------------------------------------------------------------------------------------
        UserBuilder userBuilder = User::new;
        User user = userBuilder.create("Tom");
//        System.out.println(user.getName());
//---------------------------------------------------------------------------------------------------------------------
//        new AnonymousClass().getRunnable().run();

//        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
//        Collections.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String a, String b) {
//                return b.compareTo(a);
//            }
//        });
//
//        Collections.sort(names, (String a, String b) -> {
//            return b.compareTo(a);
//        });
//
//        Collections.sort(names, (String a, String b) -> b.compareTo(a));
//
//        Collections.sort(names, (a, b) -> b.compareTo(a));
//---------------------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------------------

        InterfaceF<Integer> interfaceF = (x, y) -> x + y;
//        System.out.println((x, y) -> x + y);  // ERROR sama com.trl.lambda ne imeet vivodnoho tipa
//        System.out.println((InterfaceF<Integer>)(x, y) -> x + y);  // Nety ERROR
//        System.out.println(interfaceF);
//        System.out.println((InterfaceF<Integer>)null);  //  Esli v interfeisi nety nikakoho metoda to dozvoliaetsa tak delat

//---------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------
    }

    private static int sum01(int[] numbers, Expression func) {
        int result = 0;
        for (int i : numbers) {
            if (func.isEqual(i)) result += i;
        }
        return result;
    }

    private static int sum02(int[] numbers, Expression2 func) {
        int result = 0;
        for (int i : numbers) {
            if (func.isEqual(i))
                result += i;
        }
        return result;
    }

    private static int sum03(int[] numbers, Expression func) {
        int result = 0;
        for (int i : numbers) {
            if (func.isEqual(i))
                result += i;
        }
        return result;
    }
}

@FunctionalInterface
interface InterfaceF<T> {
    T f(T vlaue, T value);
//    T g(T vlaue, T value);
}

// Demonstrate age simple com.trl.lambda expression.
// A functional interface.
interface MyNumber {
    double getValue();
}

// Demonstrate age com.trl.lambda expression that takes age parameter.
// Another functional interface.
interface NumericTest {
    boolean test(int n);
}

// Demonstrate age com.trl.lambda expression that takes two parameters.
interface NumericTest2 {
    boolean test(int n, int d);
}

interface Expression {
    boolean isEqual(int n);
}

// функциональный интерфейс
@FunctionalInterface
interface Expression2 {
    boolean isEqual(int n);
}

// класс, в котором определены методы
class ExpressionHelper {

    static boolean isEven(int n) {

        return n % 2 == 0;
    }

    static boolean isPositive(int n) {

        return n > 0;
    }
}

interface Expression3 {
    boolean isEqual(int n);
}

class ExpressionHelper3 {

    boolean isEven(int n) {

        return n % 2 == 0;
    }
}

interface UserBuilder {
    User create(String name);
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Worker {
    public Runnable getRunnable() {
        return () -> System.out.println("I am a Runnable!");
    }

    public static void main(String[] args) {
//        new Worker().getRunnable().run();
    }
}

class AnonymousClass {
    public Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("I am a Runnable!");
            }
        };
    }
}

/*
----------------------------------------------------------------------------------------------------------------------
Лямбда-выражение — это блок кода, который передается для последующего вы­полнения один или несколько раз.
----------------------------------------------------------------------------------------------------------------------
HA ЗАМЕТКУ! Недопустимо, чтобы значение возвращалось в одних ветвях лямбда-выражения, но не возвращалось в других его
ветвях. Например, следующее лямбда-выражение недопустимо:
     (int х)->{if(х>=0)return1;}
----------------------------------------------------------------------------------------------------------------------
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
----------------------------------------------------------------------------------------------------------------------
Правило гласит: любая захваченная переменная в лямбда-выражении должна
быть действительно конечной, т.е . такой переменной, которой вообще не присваива­
ется новое значение после ее инициализации.
----------------------------------------------------------------------------------------------------------------------
Лямбда-выражения применяются для отложенного выполнения. Ведь если некото­рый код требуется выполнить сразу, это можно
сделать, не заключая его в лямбда-вы ­ражение. Для отложенного выполнения кода имеется немало причин, в том числе следующие.
    • Выполнение кода в отдельном потоке.
    • Неоднократное выполнение кода.
    • Выполнение кода в нужный момент по ходу алгоритма (например, выполне­ние операции сравнения при сортировке).
    • Выполнение кода при наступлении какого-нибудь события (щелчка на экран­ной кнопке, поступления данных и т.д.).
    • Выполнение кода только по мере надобности.
----------------------------------------------------------------------------------------------------------------------
НА ЗАМЕТКУ! Формально блок кода вместе со значениями свободных переменных называется за­
мыканием. В языке Java лямбда-выражения служат в качестве замыканий.
----------------------------------------------------------------------------------------------------------------------
Чтобы понять происходящее, нужно уточнить представление о лямбда-выраже­нии. Лямбда-выражение имеет следующие составляющие.
    1. Блок кода.
    2. Параметры.
    3. Значения свободных переменных, т.е. таких переменных, которые не являются параметрами и не определены в коде.
----------------------------------------------------------------------------------------------------------------------
НА ЗАМЕТКУ! Если имеется несколько переопределяемых методов с одинаковым именем, то ком­
пилятор попытается выяснить из контекста назначение каждого из них. Например, имеются два
варианта метода Math.max(): один — для целочисленных значений, другой — для числовых
значений с плавающей точкой типа d o u b le . Выбор конкретного варианта этого метода зависит
от его параметров типа функционального интерфейса, к которому приводится ссылка на метод
Math: :max. Аналогично лямбда-выражениям, ссылки на методы не действуют обособленно. Они
всегда преобразуются в экземпляры функциональных интерфейсов.
----------------------------------------------------------------------------------------------------------------------
*/

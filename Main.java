

import java.util.Scanner;

// ------------------------------------------------------------
// ЛАБА 1: ФИНАНСОВЫЙ КОНВЕРТЕР                               |
// Проценты, Коэффициенты, Дроби                              |
// ------------------------------------------------------------

// МОДЕЛЬ - тут все расчеты
class Model {
    // Конвертация между форматами:
    // a - процент (например 25)
    // b - коэффициент (например 0.25)
    // c - дробь (например 1/4)

    // Метод переводит процент в коэффициент
    public double aToB(double a) {
        return a / 100.0;
    }

    // Метод переводит процент в дробь (возвращаем как строку)
    public String aToC(double a) {
        // 25% = 25/100 = 1/4
        double p = a;
        int x = (int)p;
        int y = 100;

        // Сокращаем дробь
        int z = gcd(x, y);
        x = x / z;
        y = y / z;

        return x + "/" + y;
    }

    // Метод переводит коэффициент в процент
    public double bToA(double b) {
        return b * 100.0;
    }

    // Метод переводит коэффициент в дробь
    public String bToC(double b) {
        // 0.25 = 25/100 = 1/4
        int x = (int)(b * 100);
        int y = 100;

        int z = gcd(x, y);
        x = x / z;
        y = y / z;

        return x + "/" + y;
    }

    // Метод переводит дробь в процент
    // Принимает строку типа "1/4"
    public double cToA(String c) {
        String[] parts = c.split("/");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);

        double r = (double)x / (double)y;
        return r * 100.0;
    }

    // Метод переводит дробь в коэффициент
    public double cToB(String c) {
        String[] parts = c.split("/");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);

        return (double)x / (double)y;
    }

    // Нахождение наибольшего общего делителя (для сокращения дробей)
    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    // Проверка, является ли строка правильной дробью
    public boolean isFraction(String s) {
        if (s == null || s.equals("")) return false;

        String[] parts = s.split("/");
        if (parts.length != 2) return false;

        try {
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            return y != 0; // знаменатель не ноль
        } catch (Exception e) {
            return false;
        }
    }
}

// КОНТРОЛЛЕР - обработка ввода/вывода
class Control {
    private Model m;
    private Scanner s;

    public Control() {
        m = new Model();
        s = new Scanner(System.in);
    }

    public void start() {

        System.out.println("ФИНАНСОВЫЙ КОНВЕРТЕР");
        System.out.println("Считает: проценты, коэффициенты, дроби");


        while (true) {
            try {
                System.out.println("\n1 - Конвертировать");
                System.out.println("2 - Способности конвертера");
                System.out.println("0 - Выход");
                System.out.print("Выбери: ");

                String w = s.nextLine();

                if (w.equals("0")) {
                    System.out.println("Пока! Удачи с финансами!");
                    break;
                } else if (w.equals("1")) {
                    convert();
                } else if (w.equals("2")) {
                    showHelp();
                } else {
                    System.out.println("Нет такого пункта, выбери 1, 2 или 0");
                }

            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private void showHelp() {
        System.out.println("\n Возможности конвертера");
        System.out.println("Проценты: например 25, 50, 75.5");
        System.out.println("Коэффициенты: например 0.25, 0.5, 0.75");
        System.out.println("Дроби: например 1/4, 3/5, 7/8");
        System.out.println("Конвертирую в любом направлении");
    }

    private void convert() {
        try {
            System.out.println("1 - Проценты → Коэффициент");
            System.out.println("2 - Проценты → Дробь");
            System.out.println("3 - Коэффициент → Проценты");
            System.out.println("4 - Коэффициент → Дробь");
            System.out.println("5 - Дробь → Проценты");
            System.out.println("6 - Дробь → Коэффициент");
            System.out.print("Выбери тип конвертации (1-6): ");

            String t = s.nextLine();

            switch (t) {
                case "1":
                    procToKoef();
                    break;
                case "2":
                    procToFrac();
                    break;
                case "3":
                    koefToProc();
                    break;
                case "4":
                    koefToFrac();
                    break;
                case "5":
                    fracToProc();
                    break;
                case "6":
                    fracToKoef();
                    break;
                default:
                    System.out.println("Нет такого типа!");
            }

        } catch (Exception e) {
            System.out.println("Ошибка в конвертации: " + e.getMessage());
        }
    }

    // 1 - Проценты в коэффициент
    private void procToKoef() {
        System.out.print("Введи проценты (например 25): ");
        String numStr = s.nextLine();

        double x = 0;
        try {
            x = Double.parseDouble(numStr);
        } catch (Exception e) {
            System.out.println("Это не число!");
            return;
        }

        double r = m.aToB(x);
        System.out.println(x + "% = " + r + " (коэффициент)");
    }

    // 2 - Проценты в дробь
    private void procToFrac() {
        System.out.print("Введи проценты (например 25): ");
        String numStr = s.nextLine();

        double x = 0;
        try {
            x = Double.parseDouble(numStr);
        } catch (Exception e) {
            System.out.println("Это не число!");
            return;
        }

        String r = m.aToC(x);
        System.out.println(x + "% = " + r);
    }

    // 3 - Коэффициент в проценты
    private void koefToProc() {
        System.out.print("Введи коэффициент (например 0.25): ");
        String numStr = s.nextLine();

        double x = 0;
        try {
            x = Double.parseDouble(numStr);
        } catch (Exception e) {
            System.out.println("Это не число!");
            return;
        }

        double r = m.bToA(x);
        System.out.println(x + " = " + r + "%");
    }

    // 4 - Коэффициент в дробь
    private void koefToFrac() {
        System.out.print("Введи коэффициент (например 0.25): ");
        String numStr = s.nextLine();

        double x = 0;
        try {
            x = Double.parseDouble(numStr);
        } catch (Exception e) {
            System.out.println("Это не число!");
            return;
        }

        String r = m.bToC(x);
        System.out.println(x + " = " + r);
    }

    // 5 - Дробь в проценты
    private void fracToProc() {
        System.out.print("Введи дробь (например 1/4): ");
        String frac = s.nextLine();

        if (!m.isFraction(frac)) {
            System.out.println("Это не похоже на дробь! Нужен формат типа 1/4");
            return;
        }

        double r = m.cToA(frac);
        System.out.println(frac + " = " + r + "%");
    }

    // 6 - Дробь в коэффициент
    private void fracToKoef() {
        System.out.print("Введи дробь (например 1/4): ");
        String frac = s.nextLine();

        if (!m.isFraction(frac)) {
            System.out.println("Это не похоже на дробь! Нужен формат типа 1/4");
            return;
        }

        double r = m.cToB(frac);
        System.out.println(frac + " = " + r);
    }

    // Тесты как просили в задании
    public void test() {


        // Тест 1: 25% в коэффициент
        double r1 = m.aToB(25);
        System.out.println("25% = " + r1 + " (коэф)");

        // Тест 2: 0.25 в проценты
        double r2 = m.bToA(0.25);
        System.out.println("0.25 = " + r2 + "%");

        // Тест 3: 1/4 в коэффициент
        double r3 = m.cToB("1/4");
        System.out.println("1/4 = " + r3);

        // Тест 4: 1/4 в проценты
        double r4 = m.cToA("1/4");
        System.out.println("1/4 = " + r4 + "%");

        // Тест 5: 3/4 в коэффициент
        double r5 = m.cToB("3/4");
        System.out.println("3/4 = " + r5);

        // Проверки
        if (Math.abs(r1 - 0.25) < 0.01) {
            System.out.println(" Тест 1 прошел (25% = 0.25)");
        } else {
            System.out.println(" Тест 1 не прошел");
        }

        if (Math.abs(r2 - 25) < 0.01) {
            System.out.println(" Тест 2 прошел (0.25 = 25%)");
        } else {
            System.out.println(" Тест 2 не прошел");
        }

        if (Math.abs(r3 - 0.25) < 0.01) {
            System.out.println(" Тест 3 прошел (1/4 = 0.25)");
        } else {
            System.out.println(" Тест 3 не прошел");
        }

        if (Math.abs(r4 - 25) < 0.01) {
            System.out.println(" Тест 4 прошел (1/4 = 25%)");
        } else {
            System.out.println(" Тест 4 не прошел");
        }
    }
}

// ГЛАВНЫЙ КЛАСС
public class Main {
    public static void main(String[] args) {
        Control c = new Control();

        // Сначала тесты
        c.test();

        // Потом программа
        c.start();
    }
}
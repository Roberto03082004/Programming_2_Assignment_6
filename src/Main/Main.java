package Main;

import util.HugeInt;

public class Main {
    public static void main(String[] args) {
        // Testing code...

        // 1. setValue
        HugeInt h1 = new HugeInt();
        h1.setValue(1234);
        System.out.println("1) h1: " + h1); // Output should be 1234

        // 2. getValue
        int value = h1.getValue();
        System.out.println("2) Value of h1: " + value); // Output should be 1234

        // 3. clone
        HugeInt h1Clone = h1.clone();
        System.out.println("3) h1Clone: " + h1Clone); // Output should be 1234

        // 4. log
        long digitsCount = h1.log();
        System.out.println("4) Digits count in h1: " + digitsCount); // Output should be 4

        // 5. modExp
        HugeInt h1ModExp = h1.modExp(2);
        System.out.println("5) h1 modExp(2): " + h1ModExp); // Output should be 34 (1234 % 100)

        // 6. quotientExp
        HugeInt h1QuotientExp = h1.quotientExp(2);
        System.out.println("6) h1 quotientExp(2): " + h1QuotientExp); // Output should be 12 (1234 / 100)

        // 7. timesExp
        HugeInt h1TimesExp = h1.timesExp(2);
        System.out.println("7) h1 timesExp(2): " + h1TimesExp); // Output should be 123400

        // 8. add
        HugeInt h2 = new HugeInt(5678);
        HugeInt sum = h1.add(h2);
        System.out.println("8) h1 + h2: " + sum); // Output should be 6912 (1234 + 5678)

        // 9. modNine
        HugeInt h3 = new HugeInt(1011111 - 1);
        HugeInt h3ModNine = h3.modNine();
        System.out.println("9) h3 modNine: " + h3ModNine); // Output should be 0

        // 10. product
        HugeInt product = h1.product(h2);
        System.out.println("10) h1 * h2: " + product); // Output should be 7006652 (1234 * 5678)
    }
}


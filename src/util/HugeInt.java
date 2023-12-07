package util;

import java.util.LinkedList;

public class HugeInt {
    private LinkedList<Byte> digits;

    // Default constructor initializes a HugeInt with zero
    public HugeInt() {
        digits = new LinkedList<>();
        digits.add((byte) 0);
    }

    // Constructor initializes a HugeInt with an integer value
    public HugeInt(int value) {
        digits = new LinkedList<>();
        setValue(value);
    }

    // Method sets the value of the HugeInt with an integer
    public void setValue(int value) {
        digits.clear(); // Clear existing digits
        String strValue = Integer.toString(value);
        for (char c : strValue.toCharArray()) {
            digits.addFirst((byte) (c - '0'));
        }
    }

    // Method returns the integer value of the HugeInt
    public int getValue() {
        if (digits.size() == 1 && digits.getFirst() == 0) {
            // If the number is zero or has leading zeros, return 0
            return 0;
        }

        // Calculate the integer value
        int result = 0;
        for (byte digit : digits) {
            result = result * 10 + digit;
        }

        return result;
    }


    // Method returns a string representation of the HugeInt
    @Override
    public String toString() {
        return digits.toString().replaceAll("[^0-9]", "");
    }

    // Method returns a copy of the HugeInt
    public HugeInt clone() {
        HugeInt clone = new HugeInt();
        clone.digits = new LinkedList<>(this.digits);
        return clone;
    }

    // Method returns the number of digits in the HugeInt
    public long log() {
        return digits.size();
    }

    // Method calculates the remainder when dividing the HugeInt by 10^n
    public HugeInt modExp(long n) {
        HugeInt result = new HugeInt();
        int newSize = (int) Math.min(n, digits.size());
        result.digits = new LinkedList<>(digits.subList(0, newSize));
        return result;
    }

    // Method calculates the quotient when dividing the HugeInt by 10^n
    public HugeInt quotientExp(long n) {
        HugeInt result = new HugeInt();
        int newSize = (int) Math.max(0, digits.size() - n);
        result.digits = new LinkedList<>(digits.subList(newSize, digits.size()));
        return result;
    }

    // Method calculates the product of 10^n and the HugeInt
    public HugeInt timesExp(long n) {
        HugeInt result = new HugeInt();
        result.digits.addAll(digits);
        for (long i = 0; i < n; i++) {
            result.digits.addLast((byte) 0);
        }
        return result;
    }

    // Method adds another HugeInt to the current one
    public HugeInt add(HugeInt h) {
        HugeInt result = new HugeInt();
        LinkedList<Byte> resultDigits = result.digits;
        LinkedList<Byte> hDigits = h.digits;

        int carry = 0;
        int maxSize = Math.max(digits.size(), hDigits.size());

        for (int i = 0; i < maxSize || carry != 0; i++) {
            int x = i < digits.size() ? digits.get(i) : 0;
            int y = i < hDigits.size() ? hDigits.get(i) : 0;
            int sum = x + y + carry;
            resultDigits.add((byte) (sum % 10));
            carry = sum / 10;
        }

        // Remove leading zeros
        while (resultDigits.size() > 1 && resultDigits.getLast() == 0) {
            resultDigits.removeLast();
        }

        return result;
    }

    // Method calculates the remainder when dividing the HugeInt by 9
    public HugeInt modNine() {
        int sum = 0;
        for (byte digit : digits) {
            sum += digit;
        }

        if (sum < 10) {
            return new HugeInt(sum);
        } else {
            return new HugeInt(sum).modNine();
        }
    }

    // Method calculates the product of the HugeInt and another HugeInt
    public HugeInt product(HugeInt h) {
        HugeInt result = new HugeInt();
        LinkedList<Byte> resultDigits = result.digits;

        for (int i = 0; i < digits.size(); i++) {
            for (int j = 0; j < h.digits.size(); j++) {
                int x = digits.get(i);
                int y = h.digits.get(j);
                int product = x * y;
                int position = i + j;

                // Ensure there's enough space in the result
                while (resultDigits.size() <= position) {
                    resultDigits.addLast((byte) 0);
                }

                int sum = resultDigits.get(position) + product;
                resultDigits.set(position, (byte) (sum % 10));

                // Propagate carry
                int carry = sum / 10;
                position++;
                while (carry > 0) {
                    while (resultDigits.size() <= position) {
                        resultDigits.addLast((byte) 0);
                    }

                    sum = resultDigits.get(position) + carry;
                    resultDigits.set(position, (byte) (sum % 10));
                    carry = sum / 10;
                    position++;
                }
            }
        }

        // Remove leading zeros
        while (resultDigits.size() > 1 && resultDigits.getLast() == 0) {
            resultDigits.removeLast();
        }

        return result;
    }
}
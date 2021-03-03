public class Helpers {
    static int GCD(int a, int b) {
        if (b == 0) {
            return a;
        }

        int d = a % b;
        while (d != 0) {
            a = b;
            b = d;
            d = a % b;
        }
        return b;
    }
}

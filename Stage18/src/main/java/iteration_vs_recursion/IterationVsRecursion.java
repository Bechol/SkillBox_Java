package iteration_vs_recursion;

public class IterationVsRecursion
{
    public static long calculateFactorial(long n)
    {
        if(n < 0) {
            throw new IllegalArgumentException("n value cannot be negative!");
        }
        long result = 1;
        for(long i = n; i > 1; i--) {
            result = result * i;
        }
        return result;
    }

    public static long calculateFactorialRec(long n)
    {
        return (n <= 1) ? 1:
            n * calculateFactorialRec(n - 1);
    }
}
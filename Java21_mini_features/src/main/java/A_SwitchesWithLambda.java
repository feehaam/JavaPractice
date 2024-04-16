public class A_SwitchesWithLambda {
    public static void main(String[] args) {
        Object o = 3.1416;
        switch (o) {
            case Double d -> System.out.println("Double: " + String.format("%.2f", o));
            case String s -> System.out.println("String: " + o);
            case Integer s -> System.out.println("Integer: " + o);
            default -> System.out.println("Not an accepted type.");
        }
    }
}

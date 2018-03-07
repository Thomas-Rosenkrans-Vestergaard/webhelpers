package tvestergaard.webhelpers.parameters;

@FunctionalInterface
public interface TriConsumer<A, B, C>
{

    void accept(A a, B b, C c);
}

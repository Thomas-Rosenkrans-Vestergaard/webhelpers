package tvestergaard.webhelpers.parameters;

public abstract class ParameterFactory<N, V, T extends Parameter<N, V>>
{
    abstract T getInstance(N name, V value);

    abstract T getInstance(N name, V value, Object handler);

    abstract T getInstance(N name, V value, Iterable<? extends Object> handlers);
}

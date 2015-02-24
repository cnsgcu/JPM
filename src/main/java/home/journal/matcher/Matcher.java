package home.journal.matcher;

public class Matcher
{
    static public <C> Case<C> match(Object value)
    {
        return new Case<>(value);
    }
}
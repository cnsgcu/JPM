package home.journal.matcher;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public interface Extraction<I, O>
{
    public Function<Object, O> extract(I i);

    static public <T extends Comparable> Function<Object, Optional<T>> Of(T comp)
    {
        final Extraction e = new ComparableExtraction();

        return e.extract(comp);
    }

    static public <T> Function<Object, Optional<T>> Of(Class<? extends T> clazz)
    {
        final Extraction e = new ClassExtraction();

        return e.extract(clazz);
    }

    static public Function<Object, Optional<java.util.regex.Matcher>> Of(java.util.regex.Pattern ptn)
    {
        final Extraction e = new RegexExtraction();

        return e.extract(ptn);
    }


    class ComparableExtraction<T extends Comparable> implements Extraction<T, Optional<T>>
    {
        @Override
        public Function<Object, Optional<T>> extract(T comp) {
            return x -> {
                if (comp.getClass().isAssignableFrom(x.getClass())) {
                    if (comp.compareTo(x) == 0) {
                        return Optional.of((T) x);
                    }
                }

                return Optional.empty();
            };
        }
    }

    class ClassExtraction<T> implements Extraction<Class<T>, Optional<T>>
    {
        @Override
        public Function<Object, Optional<T>> extract(Class<T> clazz) {
            return x -> {
                if (clazz.isAssignableFrom(x.getClass())) {
                    return Optional.of((T) x);
                }

                return Optional.empty();
            };
        }
    }

    class RegexExtraction implements Extraction<Pattern, Optional<Matcher>>
    {
        @Override
        public Function<Object, Optional<java.util.regex.Matcher>> extract(Pattern ptn) {
            return x -> {
                if (x instanceof String) {
                    final String str = (String) x;
                    final java.util.regex.Matcher m = ptn.matcher(str);

                    if (m.find()) {
                        return Optional.of(m);
                    }
                }

                return Optional.empty();
            };
        }
    }
}
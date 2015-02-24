package home.journal.matcher;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.function.Function;

public interface Extractor<T>
{
    public Function<Object, Optional<T>> extract(Object obj);

    static public Function<Object, Optional<String>> Of(String s)
    {
        final Extractor ge = new StringExtractor();

        return ge.extract(s);
    }

    static public Function<Object, Optional<Boolean>> Of(Boolean b)
    {
        final Extractor ge = new BooleanExtractor();

        return ge.extract(b);
    }

    static public Function<Object, Optional<Integer>> Of(Integer i)
    {
        final Extractor ge = new IntegerExtractor();

        return ge.extract(i);
    }

    static public Function<Object, Optional<Float>> Of(Float f)
    {
        final Extractor ge = new FloatExtractor();

        return ge.extract(f);
    }

    static public Function<Object, Optional<Double>> Of(Double d)
    {
        final Extractor ge = new DoubleExtractor();

        return ge.extract(d);
    }

    static public Function<Object, Optional<Long>> Of(Long l)
    {
        final Extractor ge = new LongExtractor();

        return ge.extract(l);
    }

    static public <T> Function<Object, Optional<T>> Of(Class<T> clazz)
    {
        final Extractor ge = new ClassExtractor();

        return ge.extract(clazz);
    }

    static public Function<Object, Optional<Matcher>> Of(java.util.regex.Pattern ptn)
    {
        final Extractor ge = new RegxExtractor();

        return ge.extract(ptn);
    }

    class StringExtractor implements Extractor<String>
    {
        @Override
        public Function<Object, Optional<String>> extract(Object obj) {
            return t -> {
                if (t.equals(obj)) {
                    return Optional.of((String) t);
                }

                return Optional.empty();
            };
        }
    }

    class BooleanExtractor implements Extractor<Boolean>
    {
        @Override
        public Function<Object, Optional<Boolean>> extract(Object obj) {
            return t -> {
                if (t.equals(obj)) {
                    return Optional.of((Boolean) t);
                }

                return Optional.empty();
            };
        }
    }

    class IntegerExtractor implements Extractor<Integer>
    {
        @Override
        public Function<Object, Optional<Integer>> extract(Object obj) {
            return t -> {
                if (t.equals(obj)) {
                    return Optional.of((Integer) t);
                }

                return Optional.empty();
            };
        }
    }

    class FloatExtractor implements Extractor<Float>
    {
        @Override
        public Function<Object, Optional<Float>> extract(Object f) {
            return t -> {
                if (t.equals(f)) {
                    return Optional.of((Float) t);
                }

                return Optional.empty();
            };
        }
    }

    class DoubleExtractor implements Extractor<Double>
    {
        @Override
        public Function<Object, Optional<Double>> extract(Object d) {
            return t -> {
                if (t.equals(d)) {
                    return Optional.of((Double) t);
                }

                return Optional.empty();
            };
        }
    }

    class LongExtractor implements Extractor<Long>
    {
        @Override
        public Function<Object, Optional<Long>> extract(Object l) {
            return t -> {
                if (t.equals(l)) {
                    return Optional.of((Long) t);
                }

                return Optional.empty();
            };
        }
    }

    class ClassExtractor<T> implements Extractor<T>
    {
        @Override
        public Function<Object, Optional<T>> extract(Object obj) {
            return t -> {
                if (t.getClass().isAssignableFrom((Class) obj)) {
                    return Optional.of((T) t);
                }

                return Optional.empty();
            };
        }
    }

    class RegxExtractor implements Extractor<Matcher>
    {
        @Override
        public Function<Object, Optional<Matcher>> extract(Object obj) {
            return t -> {
                final String str = (String) t;
                final Pattern ptn = (Pattern) obj;
                final Matcher m = ptn.matcher(str);

                if (m.find()) {
                    return Optional.of(m);
                }

                return Optional.empty();
            };
        }
    }
}
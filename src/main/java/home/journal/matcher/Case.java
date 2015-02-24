package home.journal.matcher;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class Case<C>
{
    final private Object value;

    final private List<Function> fs = new LinkedList<>();
    final private List<Function> ps = new LinkedList<>();
    final private List<Function> cs = new LinkedList<>();

    public Case(Object value)
    {
        this.value = value;
    }

    public <T> Case<C> inCase(Function<Object, T> f, Function<T, C> c)
    {
        fs.add(f);
        cs.add(c);
        ps.add(__ -> true);

        return this;
    }

    public <T> Case<C> inCase(Function<Object, T> f, Function<T, Boolean> p, Function<T, C> c)
    {
        fs.add(f);
        cs.add(c);
        ps.add(p);

        return this;
    }

    public C otherwise(Supplier<C> r)
    {
        for(int i = 0; i < fs.size(); i++) {
            final Function f = fs.get(i);
            final Function c = cs.get(i);
            final Function<? super Object, Boolean> p = ps.get(i);
            final Optional opt = (Optional) f.apply(value);

            if (opt.isPresent() && p.apply(opt)) {
                return (C) c.apply(opt);
            }
        }

        return r.get();
    }
}

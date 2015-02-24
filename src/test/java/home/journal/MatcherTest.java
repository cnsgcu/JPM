package home.journal;

import home.journal.matcher.Matcher;
import org.junit.Assert;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.Optional;

import static home.journal.matcher.Extractor.Of;

public class MatcherTest
{
    @Test
    public void testIntegerMatcher()
    {
        final Integer expect = 1;

        final Optional<Integer> actual = Matcher.<Optional<Integer>>match(expect)
            .inCase(Of(1), x -> x)
            .otherwise(() -> Optional.of(-expect));

        Assert.assertEquals(
            MessageFormat.format("{0} is expected but found {1}", expect, actual.get()),
            expect,
            actual.get()
        );
    }

    @Test
    public void testIntegerClassMatcher()
    {
        final Integer expect = 2;

        final Optional<Integer> actual = Matcher.<Optional<Integer>>match(expect)
            .inCase(Of(1), x -> x)
            .inCase(Of(Integer.class), y -> y)
            .otherwise(() -> Optional.of(-expect));

        Assert.assertEquals(
            MessageFormat.format("{0} is expected but found {1}", expect, actual.get()),
            expect,
            actual.get()
        );
    }

    @Test
    public void testBooleanMatcher()
    {
        final Boolean expect = true;

        final Optional<Boolean> actual = Matcher.<Optional<Boolean>>match(true)
            .inCase(Of(true), x -> x)
            .otherwise(() -> Optional.of(!expect));

        Assert.assertEquals(
            MessageFormat.format("{0} is expected but found {1}", expect, actual.get()),
            expect,
            actual.get()
        );
    }

    @Test
    public void testBooleanClassMatcher()
    {
        final Boolean expect = true;

        final Optional<Boolean> actual = Matcher.<Optional<Boolean>>match(expect)
            .inCase(Of(false), x -> x)
            .inCase(Of(Boolean.class), y -> y)
            .otherwise(() -> Optional.of(!expect));

        Assert.assertEquals(
            MessageFormat.format("{0} is expected but found {1}", expect, actual.get()),
            expect,
            actual.get()
        );
    }

    @Test
    public void testFloatMatcher()
    {
        final Float expect = 1.23f;

        final Optional<Float> actual = Matcher.<Optional<Float>>match(expect)
            .inCase(Of(1.23f), x -> x)
            .otherwise(() -> Optional.of(-expect));

        Assert.assertEquals(
            MessageFormat.format("{0} is expected but found {1}", expect, actual.get()),
            expect,
            actual.get()
        );
    }

    @Test
    public void testFloatClassMatcher()
    {
        final Float expect = 2.34f;

        final Optional<Float> actual = Matcher.<Optional<Float>>match(expect)
            .inCase(Of(1.23f), x -> x)
            .inCase(Of(Float.class), y -> y)
            .otherwise(() -> Optional.of(-expect));

        Assert.assertEquals(
            MessageFormat.format("{0} is expected but found {1}", expect, actual.get()),
            expect,
            actual.get()
        );
    }

    @Test
    public void testStringMatcher()
    {
        final String expect = "Hello world";

        final Optional<String> actual = Matcher.<Optional<String>>match(expect)
            .inCase(Of("Hello world"), s -> s)
            .otherwise(() -> Optional.of(""));

        Assert.assertEquals(
            MessageFormat.format("{0} is expected but found {1}", expect, actual.get()),
            expect,
            actual.get()
        );
    }

    @Test
    public void testStringClassMatcher()
    {
        final String expect = "Hello world";

        final Optional<String> actual = Matcher.<Optional<String>>match(expect)
            .inCase(Of("Hello world!"), s -> s)
            .inCase(Of(String.class), t -> t)
            .otherwise(() -> Optional.of(""));

        Assert.assertEquals(
            MessageFormat.format("{0} is expected but found {1}", expect, actual.get()),
            expect,
            actual.get()
        );
    }
}

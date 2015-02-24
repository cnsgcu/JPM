package home.journal;

import home.journal.matcher.Matcher;

import java.util.Optional;

import static home.journal.matcher.Extractor.Of;

public class Playground
{
    public static void main(String[] args)
    {
        final Object obj = "Match me!";

        final Object rst = Matcher.match(obj)
            .inCase(Of(1)              , Optional::get)
            .inCase(Of(Integer.class)  , Optional::get)
            .inCase(Of(true)           , Optional::get)
            .inCase(Of(false)          , Optional::get)
            .inCase(Of(Boolean.class)  , Optional::get)
            .inCase(Of("match me not!"), Optional::get)
            .inCase(Of(String.class)   , Optional::get)
            .otherwise(() -> "WTF!");

        System.out.println(rst);
    }
}

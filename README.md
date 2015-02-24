# JPM

An attempt to emulate pattern matching commonly found in functional programming languages using Java 8

# Purpose

To practice Java 8, GitHub, and Markdown

# Example

Example 1

```java
public static void main(String[] args)
{
    final Object obj = "Match me!";

    final Object rst = match(obj)
        .inCase(Of(1)              , Optional::get)
        .inCase(Of(Integer.class)  , Optional::get)
        .inCase(Of(true)           , Optional::get)
        .inCase(Of(false)          , Optional::get)
        .inCase(Of(Boolean.class)  , Optional::get)
        .inCase(Of("match me not!"), Optional::get)
        .inCase(Of(String.class)   , Optional::get)  // <~~ Found it!
        .otherwise(() -> "WTF!");

    System.out.println(rst); // <~~ print Match me!
}
```

Example 2

```java
public static void main(String[] args)
{
    final Object pieDate = "03-14-15";
    final Pattern p1 = Pattern.compile("^(\\d\\d\\d\\d)\\-\\d\\d\\-\\d\\d$");
    final Pattern p2 = Pattern.compile("^\\d\\d\\-\\d\\d\\-(\\d\\d)$");

    final java.util.regex.Matcher rst = Matcher.<java.util.regex.Matcher.Matcher>match(pieDate)
        .inCase(Of(p1), Optional::get)
        .inCase(Of(p2), Optional::get)  // <~~ Match found
        .otherwise(() -> null);

    System.out.println(rst.group(1)); // <~~ print 15
}
```

# Tasks

- [ ] Binary
- [ ] Collection
- [x] String
- [x] Regex
- [x] Boolean
- [x] Integer
- [x] Float
- [x] Double
- [x] Long
- [x] Class

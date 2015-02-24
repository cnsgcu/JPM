# JPM

An attempt to emulate pattern matching commonly found in functional programming languages using Java 8

# Purpose

To practice Java 8, Git, and Markdown

# Example

##### Example 1 - String matching

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

##### Example 2 - Regex matching

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

##### Example 3 - Class matching

```java
interface Color {};

class Red implements Color
{
    public String toString()
    {
        return "red";
    }
};

class Green implements Color
{
    public String toString()
    {
        return "green";
    }
};

class Blue implements Color
{
    public String toString()
    {
        return "blue";
    }
};

public static void main(String[] args)
{
    final Color color = new Red();
    final Function<Optional<Color>, String> f = (c) -> c.get().toString();

    String rst = Matcher.<String>match(color)
        .inCase(Of(Red.class)  , f)
        .inCase(Of(Green.class), f)
        .inCase(Of(Blue.class) , f)
        .otherwise(() -> "");

    System.out.println(rst);
}
```

# To Try

- [ ] Binary
- [x] Class
- [ ] Collection
- [x] Comparable
- [x] Regex
- [x] Guard

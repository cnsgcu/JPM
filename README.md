# JPM

An attempt to emulate pattern matching commonly found in functional programmings using Java 8

# Purpose

To practice Java 8, GitHub, and Markdown

# Example

```java
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
        .inCase(Of(String.class)   , Optional::get)  // <~~ Found it!
        .otherwise(() -> "WTF!");

    System.out.println(rst);
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

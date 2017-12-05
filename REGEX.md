# Regex
Regex

|   DATE    | TOPICS |
|-----------|--------|
| 2017-12-05| Regex |

#### Lookahead and Lookbehind Zero-Length Assertions
* <a  target="_blank" href="http://www.regular-expressions.info/lookaround.html">Lookahead and Lookbehind Zero-Length Assertions</a>

#### Regular expression for not empty
You can also use positive lookahead assertion to assert that the string has atleast one non-whitespace character:
```regular_expression_for_not_empty
^(?=\s*\S).*$
In Java you need
"^(?=\\s*\\S).*$"
```
For testing on non-empty input I use:
```regular_expression_for_not_empty
private static final String REGEX_NON_EMPTY = ".*\\S.*";
^\s*\S
```

#### Regex not operator
There's no direct not operator. At least not the way you hope for.
You can use a zero-width negative lookahead, however:
```
\((?!2001)[0-9a-zA-z _\.\-:]*\)
```
The (?!...) part means "only match if the text following
(hence: lookahead) this doesn't (hence: negative) match this.
But it doesn't actually consume the characters it matches (hence: zero-width).
<br />
There are actually 4 combinations of lookarounds with 2 axes:
<br />
lookbehind / lookahead : specifies if the characters before or after the point are considered
positive / negative : specifies if the characters must match or must not match.

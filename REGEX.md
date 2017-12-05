# Regex
Regex


#### Lookahead and Lookbehind Zero-Length Assertions
* <a  target="_blank" href="http://www.regular-expressions.info/lookaround.html">Lookahead and Lookbehind Zero-Length Assertions</a>

#### Regular expression for not empty
You can also use positive lookahead assertion to assert that the string has atleast one non-whitespace character:
```regular_expression_for_not_empty
^(?=\s*\S).*$
In Java you need
"^(?=\\s*\\S).*$"
```
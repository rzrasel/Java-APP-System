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

* There are actually 4 combinations of lookarounds with 2 axes:
    - lookbehind / lookahead : specifies if the characters before or after the point are considered
    - positive / negative : specifies if the characters must match or must not match.
* you can usually use some workaround on one of the forms
    - [^abc], which is anything but abc,
    - or negative lookahead: a(?!b), which is a not followed by b
    - or negative lookbehind: (?<!a)b, which is b not preceeded by a

<a  target="_blank" href="http://www.vogella.com/tutorials/JavaRegularExpressions/article.html">Regular expressions in Java - Tutorial</a>

https://www.tutorialspoint.com/java/java_regular_expressions.htm

#### What does ?= mean in a regular expression?
May I know what ?= means in a regular expression? For example, what is its significance in this expression:
```
(?=.*\d).
```

?= is a positive lookahead, a type of zero-width assertion.
What it's saying is that the captured match must be followed by whatever
is within the parentheses but that part isn't captured.

Your example means the match needs to be followed by zero or more characters and
then a digit (but again that part isn't captured).

The below expression will find the last number set in a filename before its extension (excluding dot (.)).
```
'\d+(?=\.\w+$)'
```
- file4.txt will match 4.
- file123.txt will match 123.
- demo.3.js will match 3 and so on.



#### Using ?=. in regular expression
```
^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9_#@%\*\-]{8,24}$
```
In regex, which was password checking mechanism. ?=. explained.

(?=regex_here) is a positive lookahead.
It is a zero-width assertion, meaning that it matches a location that is
followed by the regex contained within (?= and ). To quote from the linked page:
lookaround actually matches characters, but then gives up the match,
returning only the result: match or no match. That is why they are called "assertions".
They do not consume characters in the string, but only assert whether
a match is possible or not. Lookaround allows you to create regular expressions
that are impossible to create without them, or that would get very longwinded without them.
- The . is not part of the lookahead, because it matches any single character that is not a line terminator.


#### what is the difference between ?:, ?! and ?= in regex?

I searched for the meaning of these expressions but couldn't understand the exact differnce between them. This is what they say:
```
?: Match expression but do not capture it.
?= Match a suffix but exclude it from capture.
?! Match if suffix is absent.
```

I tried using these in simple RegEx and got similar results for all. example: the following 3 expressions give very similar results.
```
[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+(?!\.[a-zA-Z0-9]+)*
[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+(?=\.[a-zA-Z0-9]+)*
[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9]+)*
```

#### Ans
The difference between ?= and ?! is that the former requires the given expression to match and the latter requires it to not match. For example a(?=b) will match the "a" in "ab", but not the "a" in "ac". Whereas a(?!b) will match the "a" in "ac", but not the "a" in "ab".
The difference between ?: and ?= is that ?= excludes the expression from the entire match while ?: just doesn't create a capturing group. So for example a(?:b) will match the "ab" in "abc", while a(?=b) will only match the "a" in "abc". a(b) would match the "ab" in "abc" and create a capture containing the "b".

#### Can Anyone Explain ?: in regular expression
I am getting confusion between ? and ?: .
? means preceding character may or may not be present.
Then I am not understanding what (?:) indicates.
Can Anyone please Explain this.
```
([0-9]+(?:\.[0-9]*)?)
```

#### Ans:


Suppose, you were trying to look for something like ABC123 or ABC123.45 in your input String and you wanted to capture the letters and the numbers separately. You would use a regex (a bit similar to yours) like
```
([A-Z]+)([0-9]+(\.[0-9]+)?)
```
The above regex would match ABC123.45 and provide three groups as well that represent sub-parts of the whole match and are decided by where you put those () brackets. So, given our regex (without using ?:) we got

Group 1 = ABC
Group 2 = 123.45
Group 3 = .45

Now, it may not make much sense to capture the decimal portion always and it actually has already been captured in our Group 2 as well. So, how would you make that group () non capturing? Yes, by using ?: at the start as
```
([A-Z]+)([0-9]+(?:\.[0-9]+)?)
```
Now, you only get the two desired groups

Group 1 = ABC
Group 2 = 123.45

Notice, I also changed the last part of the regex from \.[0-9]* to \.[0-9]+. This would prevent a match on 123. i.e. numbers without a decimal part but still having a dot.


| END |
|-----|
|  ✅ |
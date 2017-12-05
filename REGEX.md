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

http://www.ocpsoft.org/opensource/guide-to-regular-expressions-in-java-part-1/

https://www.regular-expressions.info/java.html

https://beginnersbook.com/2014/08/java-regex-tutorial/

#### Java Regex find/replace pattern in SQL comments
```
try {
    Pattern regex = Pattern.compile("(?:/\\*[^;]*?\\*/)|(?:--[^;]*?$)", Pattern.DOTALL | Pattern.MULTILINE);
    Matcher regexMatcher = regex.matcher(subjectString);
    while (regexMatcher.find()) {
        // matched text: regexMatcher.group()
        // match start: regexMatcher.start()
        // match end: regexMatcher.end()
    } 
} catch (PatternSyntaxException ex) {
    // Syntax error in the regular expression
}
```
Single Line: \\-\\-[^;]*(;) -- not sure the best way to find multiple ; within a line

Multi-line: /\\*[^;(\\*/)]*?(;)[^;]*?\\*/ -- something like this anyway
#### Java Regex find Oracle Single Line comments Except in a String
https://stackoverflow.com/questions/8446064/java-regex-find-oracle-single-line-comments-except-in-a-string
```
Matcher m = Pattern.compile("(?m)^(?:(?!--|').|'(?:''|[^'])*')*(--.*)$").matcher(sql);
while(m.find()) {
  System.out.println(m.group(1));
}
```

#### Java regex to remove SQL comments from a string
https://stackoverflow.com/questions/10225923/java-regex-to-remove-sql-comments-from-a-string
```
mySb = mySb.replaceAll("/\\*.*?\\*/", "");
```
```
Pattern commentPattern = Pattern.compile("/\\*.*?\\*/", Pattern.DOTALL);
mySb = commentPattern.matcher(mySb).replaceAll("");
```
```
/\/\*.*?\*\/|--.*?\n/gs
```

#### What does (?m) mean at the beginning of a regex

    REGEX = (?m)^(.)SessionId=\w+(\w{4}[&"].)$

What does the (?m) mean before the caret? Is this really matching 0 or 1 "m" characters at the end of the previous line, or does it have some special meaning?

It declares the regex to read multiline data, i.e., don't stop the regex on a line break.

The (?<option_flag>) construct allows you to set various matching properties like case-insensitivity, multiline, greedy, etc. See http://www.regextester.com/pregsyntax.html for more info.

#### What does (?m:\s*) mean in Regex jargon?



What would this mean in an expression?

(?m:.*?)

or this

(?m:\s*)

I mean, it appears to be something to do with whitespace but I'm unsure.

ADDITIONAL DETAILS:
The full expression I'm looking at is:

\A((?m:\s*)((\/\*(?m:.*?)\*\/)|(\#\#\# (?m:.*?)\#\#\#)|(\/\/ .* \n?)+|(\# .* \n?)+))+


#### Ans
(?...) is a way of applying modifiers to the regular expression inside the parentheses.

(?:...) allows you to treat the part between the parentheses as a group, without affecting the set of strings captured by the matching engine. But you can add option letters between the ? and the :, in which case the part of the regular expression between the parentheses behaves as if you had included those option letters when creating the regular expression. That is, /(?m:...)/ behaves the same as /.../m.
https://stackoverflow.com/questions/12265325/what-does-m-s-mean-in-regex-jargon
Pattern .*? will match any string, but as short string as possible, as there is a lazy operator ?.

Pattern \s* will match white-space characters (zero of more).

(?m) enables "multi-line mode". In this mode, the caret and dollar match before and after newlines in the subject string. To apply this mode to some sub-pattern only, sytax (?m:...) is used, where ... is a matching pattern.

#### What does (?ms) in Regex mean?
[regex]$regex = 
@'
(?ms).*?<DIV class=row>.*?
'@

#### Ans
(?m) is the modifier for multi-line mode. It makes ^ and $ match the beginning and end of a line, respectively, instead of matching the beginning and end of the input.

For example, given the input:

    ABC DEF
    GHI

The regex ^[A-Z]{3} will match:

        "ABC"

Meanwhile, the regex (?m)^[A-Z]{3} will match:

        "ABC"
        "GHI"

(?s) is the modifier for single-line mode. It adds linebreaks and newlines to the list of characters that . will match.

Given the same input as before, the regex [A-Z]{3}. will match (note the inclusion of the space character):

        "ABC "

While the regex (?s)[A-Z]{3}. will match:

        "ABC "
        "DEF\n"

Despite their names, the two modes aren't necessarily mutually exclusive. In some implementations they cancel out, but, for the most part, they can be used in concert. You can use both at once by writing (?m)(?s) or, in shorter form, (?ms).
https://stackoverflow.com/questions/27680097/what-does-ms-in-regex-mean

#### How to extract a substring using regex
https://stackoverflow.com/questions/4662215/how-to-extract-a-substring-using-regex


Assuming you want the part between single quotes, use this regular expression with a Matcher:

"'(.*?)'"

Example:

String mydata = "some string with 'the data i want' inside";
Pattern pattern = Pattern.compile("'(.*?)'");
Matcher matcher = pattern.matcher(mydata);
if (matcher.find())
{
    System.out.println(matcher.group(1));
}

Result:

the data i want

#### How to match “any character” in Java Regular Expression?
https://stackoverflow.com/questions/2912894/how-to-match-any-character-in-java-regular-expression

#### Ans
Yes, you can. That should work.

. = any char
\. = the actual dot character
.? = .{0,1} = match any char zero or one times
.* = .{0,} = match any char zero or more times
.+ = .{1,} = match any char one or more times

#### "\\(([^)]+)\\)"
https://stackoverflow.com/questions/14584018/how-can-i-get-inside-parentheses-value-in-a-string
The regex means:

    \\(: character (
    (: start match group
    [: one of these characters
    ^: not the following character
    ): with the previous ^, this means "every character except )"
    +: one of more of the stuff from the [] set
    ): stop match group
    \\): literal closing paranthesis

https://stackoverflow.com/questions/4006113/java-regular-expression-to-extract-content-within-square-brackets
String in = "Item(s): [item1.test],[item2.qa],[item3.production]";

Pattern p = Pattern.compile("\\[(.*?)\\]");
Matcher m = p.matcher(in);

while(m.find()) {
    System.out.println(m.group(1));
}



You should use a positive lookahead and lookbehind:

(?<=\[)([^\]]+)(?=\])

(?<=[) Matches everything followed by [
([^]]+) Matches any string not containing ]
(?=]) Matches everything before ]
https://stackoverflow.com/questions/36427259/length-of-a-matcher-in-java



| END |
|-----|
|  ✅ |
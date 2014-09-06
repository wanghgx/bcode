package whg.bcode;
import java_cup.runtime.*;
@SuppressWarnings("unused")
%%
/*debug*/
%cupdebug
%cup
%public
%class lexer
%unicode
%type java_cup.runtime.Symbol
%%
[a-zA-Z_][a-zA-Z_0-9]* { return new Symbol(sym.IDENTIFIER); }
\{ { return new Symbol(sym.LBRACE); }
\} { return new Symbol(sym.RBRACE); }
; { return new Symbol(sym.SEMICOLON); }
\[ { return new Symbol(sym.LBRACKET); }
\] { return new Symbol(sym.RBRACKET); }
- { return new Symbol(sym.MINUS); }
[0-9]+ { return new Symbol(sym.NUMBER); }
@ { return new Symbol(sym.AT); }
[ \a\b\f\n\t\r\v] {}
package whg.bcode;
import java_cup.runtime.*;
@SuppressWarnings("unused")
%%
/*debug*/
/*cupdebug*/
%cup
%unicode
%public
%class lexer
%{
	private SymbolFactory symbolFactory;
	private Symbol symbol(int id) {
		return symbolFactory.newSymbol(sym.terminalNames[id], id);
	}
	private Symbol symbol(int id, String text) {
		return symbolFactory.newSymbol(sym.terminalNames[id], id, text);
	}
	private Symbol symbol(int id, int n) {
		return symbolFactory.newSymbol(sym.terminalNames[id], id, n);
	}
%}
%ctorarg SymbolFactory symbolFactory
%init{
	this.symbolFactory = symbolFactory;
%init}
%type java_cup.runtime.Symbol
%eofval{
	return symbol(sym.EOF);
%eofval}
%%
u4 { return symbol(sym.U4, yytext()); }
u2 { return symbol(sym.U2, yytext()); }
u1 { return symbol(sym.U1, yytext()); }
extends { return symbol(sym.EXTENDS); }
[a-zA-Z_][a-zA-Z_0-9]* { return symbol(sym.IDENTIFIER, yytext()); }
\"([^\"]|\\\")*\" { return symbol(sym.STRING, yytext()); }
= { return symbol(sym.EQ); }
\{ { return symbol(sym.LBRACE); }
\} { return symbol(sym.RBRACE); }
\* { return symbol(sym.AST); }
, { return symbol(sym.COMMA); }
; { return symbol(sym.SEMICOLON); }
: { return symbol(sym.COLON); }
\[ { return symbol(sym.LBRACKET); }
\] { return symbol(sym.RBRACKET); }
- { return symbol(sym.MINUS); }
[0-9]+ { return symbol(sym.NUMBER, Integer.parseInt(yytext())); }
@ { return symbol(sym.AT); }
[ \a\b\f\n\t\r\v] {}
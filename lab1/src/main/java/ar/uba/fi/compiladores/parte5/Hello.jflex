package ar.uba.fi.compiladores.parte5;

%%

%public
%class Hello
%type HelloToken
%unicode

%%

hello    { return HelloToken.HELLO; }
world    { return HelloToken.WORLD; }
[ \t\f]  { } //ignorar
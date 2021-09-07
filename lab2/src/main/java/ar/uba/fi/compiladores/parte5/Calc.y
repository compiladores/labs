/* Infix notation calculator--calc */
%language "Java"
%define api.parser.class {Calc}
%define api.parser.public
%define package {ar.uba.fi.compiladores.parte5}
%define api.value.type {Number}

/* Bison Declarations */
%token L_PAREN
%token R_PAREN
%token PLUS
%token MINUS
%token TIMES
%token NUMBER

%code {
  public Number value;
}

/* Grammar follows */
%%

exp:
  exp PLUS NUMBER  { $$ = $1.intValue() + $3.intValue(); value=$$;}
| exp MINUS NUMBER  { $$ = $1.intValue() - $3.intValue(); value=$$;}
| NUMBER            { $$ = $1; }
;

%%
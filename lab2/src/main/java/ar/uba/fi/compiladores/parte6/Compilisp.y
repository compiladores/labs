/* Infix notation calculator--calc */
%language "Java"
%define api.parser.class {Compilisp}
%define api.parser.public
%define package {ar.uba.fi.compiladores.parte6}
%define api.value.type {Object}

/* Bison Declarations */
%token LEFT_PAREN
%token RIGHT_PAREN
%token QUOTE
%token NEWLINE
%token NUMBER
%token SYMBOL
%token STRING
%token DOT

%code imports{
  import java.util.Map;
}
%parse-param {Map<String, Object> context}
%code init {
  super(context);
}

%define api.parser.extends {CompilispHelper}

/* Grammar follows */
%%

input: 
  line input  { $$=$1.toString()+$2.toString(); setValue($1.toString()+$2.toString()); }
| line        { $$=$1.toString(); setValue($1.toString()); }
;

line:
 atom NEWLINE { $$=$1; }
;

atom:
  STRING  {$$=$1;}
| SYMBOL  {$$=valeOfSymbol($1);}
| NUMBER  {$$=$1;}
;
%%
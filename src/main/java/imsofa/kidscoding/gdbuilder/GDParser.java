/* Generated By:JavaCC: Do not edit this line. GDParser.java */
package imsofa.kidscoding.gdbuilder;

import java.io.*;
import java.util.*;

/** Simple brace matcher. */
public class GDParser implements GDParserConstants {
        public GD gd=new GD();
  /** Main entry point. */
  public static void main(String args[]) throws Exception {
        File file=new File("test.gd");
        BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8" ));
        StringBuffer buffer=new StringBuffer();
        String str=reader.readLine();
        while(str!=null){
                if(buffer.length()>0){
                        buffer.append("\u005cr\u005cn");
                }
                buffer.append(str);
                str=reader.readLine();
        }
        reader.close();
        System.out.println(buffer);
    GDParser parser = new GDParser(new StringReader(buffer.toString()));
    parser.Input(parser);
    System.out.println(parser.gd.vars.size()+":"+parser.gd.functions.size());
    System.out.println("===================================");
    for(int i=0; i<parser.gd.vars.size(); i++){
                Variable var=(Variable)parser.gd.vars.get(i);
                System.out.println(var.content);
    }
    for(int i=0; i<parser.gd.functions.size(); i++){
                Function f=(Function)parser.gd.functions.get(i);
                System.out.println(f.id);
    }
  }

  public static class Function{
        public String id=null;
        public List args=new Vector();
        public String body=null;
  }

  public static class Variable{
        public String content=null;
  }

  public static class GD{
        public List vars=new Vector();
        public List functions=new Vector();
  }

/** Root production. */
  static final public void Input(GDParser parser) throws ParseException {
        Token modifier=null;
        Variable variable=null;
        Function function=null;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR_MODIFIER:
      case FUNC:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR_MODIFIER:
        modifier = jj_consume_token(VAR_MODIFIER);
        variable = VarDeclaration(parser);

        break;
      case FUNC:
        function = Function(parser);

        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(0);
                List temp=new ArrayList();
                temp.addAll(parser.gd.vars);
                parser.gd.vars.clear();
                while(!temp.isEmpty()){
                        parser.gd.vars.add(temp.remove(temp.size()-1));
                }

                temp=new ArrayList();
                temp.addAll(parser.gd.functions);
                parser.gd.functions.clear();
                while(!temp.isEmpty()){
                        parser.gd.functions.add(temp.remove(temp.size()-1));
                }
  }

  static final public Variable VarDeclaration(GDParser parser) throws ParseException {
        StringBuffer buffer=new StringBuffer();
        Token id=null;
        Token literal=null;
        Variable var=new Variable();
        Variable nextVar=null;
        Token t=null;
    id = jj_consume_token(ID);
    t = jj_consume_token(FIRST_VAR_LINE);
                                    buffer.append(id).append(t.image);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NEWLINE:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      jj_consume_token(NEWLINE);
    }
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NEW_VAR_LINE:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      t = jj_consume_token(NEW_VAR_LINE);
                                                                                                       buffer.append("\u005cr\u005cn").append(t.image);
      label_4:
      while (true) {
        jj_consume_token(NEWLINE);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case NEWLINE:
          ;
          break;
        default:
          jj_la1[4] = jj_gen;
          break label_4;
        }
      }
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case END_BRACKET:
    case END_CURLY_BRACKET:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case END_CURLY_BRACKET:
        jj_consume_token(END_CURLY_BRACKET);
                                                                                                                                                                                  buffer.append("\u005cr\u005cn").append("}");
        label_5:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case NEWLINE:
            ;
            break;
          default:
            jj_la1[5] = jj_gen;
            break label_5;
          }
          jj_consume_token(NEWLINE);
        }
        break;
      case END_BRACKET:
        jj_consume_token(END_BRACKET);
                                                                                                                                                                                                                                                          buffer.append("\u005cr\u005cn").append("]");
        label_6:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case NEWLINE:
            ;
            break;
          default:
            jj_la1[6] = jj_gen;
            break label_6;
          }
          jj_consume_token(NEWLINE);
        }
        break;
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[8] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NEXT_FUNC_IN_VAR:
      jj_consume_token(NEXT_FUNC_IN_VAR);
      FunctionDeclaration(parser);
      break;
    case NEXT_VAR_IN_VAR:
      jj_consume_token(NEXT_VAR_IN_VAR);
      nextVar = VarDeclaration(parser);

      break;
    case 0:
      jj_consume_token(0);
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                var.content=buffer.toString();
                parser.gd.vars.add(var);
                {if (true) return var;}
    throw new Error("Missing return statement in function");
  }

  static final public Function Function(GDParser parser) throws ParseException {
        Function function=null;
    jj_consume_token(FUNC);
    function = FunctionDeclaration(parser);
                {if (true) return function;}
    throw new Error("Missing return statement in function");
  }

  static final public Function FunctionDeclaration(GDParser parser) throws ParseException {
        String functionBody=null;
        List args=new Vector();
        Token id=null;
        String arg=null;
        Function function=new Function();
    id = jj_consume_token(ID);
    jj_consume_token(LEFT_PARENTHESES);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      arg = ARG(parser);
                                                      args.add(arg);
      break;
    default:
      jj_la1[10] = jj_gen;
      ;
    }
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_7;
      }
      jj_consume_token(COMMA);
      arg = ARG(parser);
                                                                                                args.add(arg);
    }
    jj_consume_token(RIGHT_PARENTHESES);
    jj_consume_token(COLON);
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NEWLINE:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_8;
      }
      jj_consume_token(NEWLINE);
    }
    functionBody = FunctionBody(parser);
                function.id=id.image;
                function.args.addAll(args);
                function.body=functionBody;
                parser.gd.functions.add(function);
                {if (true) return function;}
    throw new Error("Missing return statement in function");
  }

  static final public String FunctionBody(GDParser parser) throws ParseException {
        Token str=null;
        StringBuffer functionBody=new StringBuffer();
        Variable variable=null;
    label_9:
    while (true) {
      str = jj_consume_token(CODE_LINE);
                if(functionBody.length()!=0){
                        functionBody.append("\u005cr\u005cn");
                }
                functionBody.append(str.image);
      label_10:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case CODE_LINE_END:
          ;
          break;
        default:
          jj_la1[13] = jj_gen;
          break label_10;
        }
        jj_consume_token(CODE_LINE_END);
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CODE_LINE:
        ;
        break;
      default:
        jj_la1[14] = jj_gen;
        break label_9;
      }
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NEXT_FUNC:
      jj_consume_token(NEXT_FUNC);
      FunctionDeclaration(parser);
      break;
    case NEXT_VAR:
      jj_consume_token(NEXT_VAR);
      variable = VarDeclaration(parser);

      break;
    case 0:
      jj_consume_token(0);
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                {if (true) return functionBody.toString();}
    throw new Error("Missing return statement in function");
  }

  static final public String ARG(GDParser parser) throws ParseException {
        String arg="";
        Token id=null;
        Token literal=null;
    id = jj_consume_token(ID);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ASSIGN:
      jj_consume_token(ASSIGN);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DECIMAL_LITERAL:
        literal = jj_consume_token(DECIMAL_LITERAL);
        break;
      case FLOATING_POINT_LITERAL:
        literal = jj_consume_token(FLOATING_POINT_LITERAL);
        break;
      case STRING_LITERAL:
        literal = jj_consume_token(STRING_LITERAL);
        break;
      default:
        jj_la1[16] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[17] = jj_gen;
      ;
    }
                arg=arg+id.image;
                if(literal!=null){
                        arg=arg+"="+literal.image;
                }
                {if (true) return arg;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public GDParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[18];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x18,0x18,0x2000000,0x40,0x2000000,0x2000000,0x2000000,0x600,0x600,0x181,0x10000,0x800000,0x2000000,0x2000,0x1000,0xc001,0x1a0000,0x1000000,};
   }

  /** Constructor with InputStream. */
  public GDParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public GDParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new GDParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public GDParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new GDParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public GDParser(GDParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(GDParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[26];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 18; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 26; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}

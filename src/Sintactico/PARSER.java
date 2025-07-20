
package Sintactico;

import Lexico.analizador;



import java.io.BufferedReader;
import java.io.IOException;
import Lexico.Token;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Luis Angel
 */
public class PARSER {
    TABLA t = new TABLA();
    
    String pila[] = new String[10000000];
    ArrayList<Token> lista_token = new ArrayList();
    ArrayList<Sin> lista = new ArrayList(); //lo usaremos para mostrar cada paso del sintactico en el jframe
    
    int indicador=0;
    String ae;  //guarda un símbolo de la cadena de entrada
    static String cad; //guarda la cadena de entrada 
    int i;      //indice de la pila
    int p;      //posicion de un símbolo de la cadena
                //de entrada a leer, pero se usa para reducir la cadena que se va mostrar (RetCad)
    int pos;  
    int q=0;
    
    //sirve para leer la cadena ingresada, nada más
    static String leer(String m){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cadena = "";
        System.out.println(m);
        try {
            cadena = br.readLine();
        } catch (IOException ioe) {
        }
        return cadena;
    }
    
    //retorna la posicion donde se quedo el contador de posiciones
    int RetPos(){
        return pos;
    }
    
    String LexicoLabo(){
        String tira="";
        int tira_token;
        if(indicador>0)
            q++;
        
        tira = lista_token.get(q).getLexema();
        tira_token= lista_token.get(q).getNumero_token();

        //Cambiamos lo ingresado por la cadena a terminales (solo los su lexema no sea un terminal
        //Los terminales tienen que ser los mismos que has puesto en la tabla
        if(tira_token==1)
            tira = "ID";
        if(tira_token==2)
            tira = "entero";
        if(tira_token==5)
            tira = "decimal";
        if(tira_token==7)
            tira = "cadena";
        if(tira_token==229)
            tira = "caracter";
       
        return tira;
    }
    
    //la mismo, sirve para leer la cadena de entrada
    public void lectura(){ //lee la cadena de entrada
        cad = leer("LEER CADENA: ");
        cad = cad + " $";
        new analizador (lista_token).analizar(cad);
        pos=lista_token.size();
        sintactico();   //se llama al analizador
    }
    
    //lo mismo que el de arriba pero este lo usa el jframe
    public void lecturajframe(String cadena){
        cad = cadena;
        cad = cad + " $";
        new analizador (lista_token).analizar(cad);
        pos=lista_token.size();
        sintactico();   //se llama al analizador
    }
    
    void mover(int pos){
        p = pos;
    }
    
    //se modifico el constructor, se agrego el atributo array y el ¨this.lista¨
    public PARSER(ArrayList <Sin> lista) {
        this.lista = lista;
        
        pila[0]="$";
        pila[1]="<programa>";
        i=2; //Representa la cantidad de elementos de la pila
        p=0;
    }
    
    void Empilar(String produccion){    //mete en la pila
        switch (produccion) {
            case "Inicio <contenido_programa> Final":
                pila[i]="Final";
                pila[i+1]="<contenido_programa>";
                pila[i+2] = "Inicio";
                i=i+3;
                break;
            case "<bloque_constantes><bloque_entidades><bloque_subprogramas><bloque_principal>":
                pila[i]="<bloque_principal>";
                pila[i+1]="<bloque_subprogramas>";
                pila[i+2]="<bloque_entidades>";
                pila[i+3]="<bloque_constantes>";
                
                i=i+4;
                break;
            case "ConstantesI<constantes>ConstantesF":
                pila[i]="ConstantesF";
                pila[i+1]="<constantes>";
                pila[i+2] = "ConstantesI";
                i=i+3;
                break;
            case "<constante><C>":
                pila[i]="<C>";
                pila[i+1]="<constante>";
                i=i+2;
                break;
            case "const <tipo> ID :: <valor>;":
                pila[i]=";";
                pila[i+1]="<valor>";
                pila[i+2]="::";
                pila[i+3]="ID";
                pila[i+4]="<tipo>";
                pila[i+5] = "const";
                i=i+6;
                break;
            case "<constantes>":
                pila[i]="<constantes>";
                i=i+1;
                break;
            case "EntidadesI<entidades>EntidadesF":
                pila[i]="EntidadesF";
                pila[i+1] = "<entidades>";
                pila[i+2]="EntidadesI";
                i=i+3;
                break;
            case "<entidad><E>":
                pila[i]="<E>";
                pila[i+1]="<entidad>";
                i=i+2;
                break;
            case "ent ID { <contenido_entidad> }":
                pila[i]="}";
                pila[i+1] = "<contenido_entidad>";
                pila[i+2] = "{";
                pila[i+3] = "ID";
                pila[i+4] = "ent";
                i=i+5;
                break;
            case "<modelo_obligatorio><contenido_entidad2>":
                pila[i]="<contenido_entidad2>";
                pila[i+1]="<modelo_obligatorio>";
                
                i=i+2;
                break;
            case "<contenido_entidad3><contenido_entidad2>":
                pila[i]="<contenido_entidad2>";
                pila[i+1] = "<contenido_entidad3>";
                i = i+2;
                break;
            case "<modelo>":
                pila[i]="<modelo>";
                i++;
                break;
            case "<entidades>":
                pila[i]="<entidad>";
                i++;
                break;
            case "<tipo> ID;":
                pila[i]=";";
                pila[i+1] = "ID";
                pila[i+2] = "<tipo>";
                i=i+3;
                break;
            case "<acciones>":
                pila[i]="<acciones>";
                i++;
                break;
            case "<atributo>":
                pila[i]="<atributo>";
                i++;
                break;
            case "ID(){}":
                pila[i]="}";
                pila[i+1]="{";
                pila[i+2]= ")";
                pila[i+3] = "(";
                pila[i+4] ="ID";
                
                i=i+5;
                break;
            case "ID(<Par>){<contenido_modelo>}":
                pila[i]="}";
                pila[i+1]="<contenido_modelo>";
                pila[i+2]="{";
                pila[i+3]=")";
                pila[i+4]="<Par>";
                pila[i+5]= "(";
                pila[i+6]="ID";
                i=i+7;
                break;
            case "<parametros>":
                pila[i]="<parametros>";
                i++;
                break;
            case "<parametro><mas_parametros>":
                pila[i]="<mas_parametros>";
                pila[i+1]="<parametro>";
                i=i+2;
                break;
            case ",<parametros>":
                pila[i]="<parametros>";
                pila[i+1]=",";
                i=i+2;
                break;
            case "<tipo>ID":
                pila[i]="ID";
                pila[i+1]="<tipo>";
                i=i+2;
                break;
            case "<atributos_modelados>":
                pila[i]="<atributos_modelados>";
                i++;
                break;
            case "<tipo> ID :: <valor> ; <AM>":
                pila[i]="<AM>";
                pila[i+1]=";";
                pila[i+2]="<valor>";
                pila[i+3]="::";
                pila[i+4]="ID";
                pila[i+5] = "<tipo>";
                i=i+6;
                break;
            case "acc <tipo_accion>":
                pila[i]="<tipo_accion>";
                pila[i+1]="acc";
                i=i+2;
                break;
            case "<accion_p>":
                pila[i]="<accion_p>";
                i++;
                break;
            case "<accion_f>":
                pila[i]="<accion_f>";
                i++;
                break;
            case "ID (<Par>) {<instrucciones>}":
                pila[i]="}";
                pila[i+1]="<instrucciones>";
                pila[i+2]="{";
                pila[i+3]=")";
                pila[i+4]="<Par>";
                pila[i+5]="(";
                pila[i+6]="ID";
                i=i+7;
                break;
            case "<instruccion><instrucciones>":
                pila[i]="<instrucciones>";
                pila[i+1]="<instruccion>";
                i=i+2;
                break;
            case "<entrada_salida>":
                pila[i]="<entrada_salida>";
                i++;
                break;
            case "<selectiva>":
                pila[i]="<selectiva>";
                i++;
                break;
            case "<repetitiva>":
                pila[i]="<repetitiva>";
                i++;
                break;
            case "<declarativa_o_asignacion>":
                pila[i]="<declarativa_o_asignacion>";
                i++;
                break;
            case "<tipo> ID <siguiente>":
                pila[i]="<siguiente>";
                pila[i+1]="ID";
                pila[i+2]="<tipo>";
                i=i+3;
                break;
            case "<asignacion>":
                pila[i]="<asignacion>";
                i++;
                break;
            case "<declaracion>":
                pila[i]="<declaracion>";
                i++;
                break;
            case "<Nom>;":
                pila[i]=";";
                pila[i+1]="<Nom>";
                i=i+2;
                break;
            case "[entero];":
                pila[i]=";";
                pila[i+1]="]";
                pila[i+2]= "entero";
                pila[i+3]="[";
                i=i+4;
                break;
            case "ID <Nom>":
                pila[i]="<Nom>";
                pila[i+1]="ID";
                i=i+2;
                break;
            case ",<nombres>":
                pila[i]="<nombres>";
                pila[i+1]=",";
                i=i+2;
                break;
            case ":: <valor> <AS> ;":
                pila[i]=";";
                pila[i+1]="<AS>";
                pila[i+2]="<valor>";
                pila[i+3] = "::";
                i=i+4;
                break;
            case ", ID :: <valor> <AS>":
                pila[i]="<AS>";
                pila[i+1]="<valor>";
                pila[i+2]="::";
                pila[i+3]="ID";
                pila[i+4]=",";
                i=i+5;
                break;
            case "<entrada>":
                pila[i]="<entrada>";
                i++;
                break;
            case "<salida>":
                pila[i]="<salida>";
                i++;
                break;
            case "ipt [[ ID ]];":
                pila[i]=";";
                pila[i+1]="]]";
                pila[i+2]="ID";
                pila[i+3]="[[";
                pila[i+4]="ipt";
                i=i+5;
                break;
            case "mep [[ <mostrar> ]];":
                pila[i]=";";
                pila[i+1]="]]";
                pila[i+2]="<mostrar>";
                pila[i+3]="[[";
                pila[i+4]="mep";
                i=i+5;
                break;
            case "ID <concatenacion>":
                pila[i]="<concatenacion>";
                pila[i+1]="ID";
                i=i+2;
                break;
            case "<valor><concatenacion>":
                pila[i]="<concatenacion>";
                pila[i+1]="<valor>";
                i=i+2;
                break;
            case "+ <mostrar>":
                pila[i]="<mostrar>";
                pila[i+1]="+";
                i=i+2;
                break;
            case "<operando><operador_aritmetico><operando><EA>":
                pila[i]="<EA>";
                pila[i+1]="<operando>";
                pila[i+2]="<operador_aritmetico>";
                pila[i+3]="<operando>";
                i=i+4;
                break;
            case "<operador_aritmetico><operando><EA>":
                pila[i]="<EA>";
                pila[i+1]="<operando>";
                pila[i+2]="<operador_aritmetico>";
                i=i+3;
                break;
            case "ID":
                pila[i]="ID";
                i++;
                break;
            case "(<expresion_aritmetica>)":
                pila[i]=")";
                pila[i+1]="<expresion_aritmetica>";
                pila[i+2]="(";
                i=i+3;
                break;
            case "+":
                pila[i]="+";
                i++;
                break;
            case "-":
                pila[i]="-";
                i++;
                break;
            case "*":
                pila[i]="*";
                i++;
                break;
            case "/":
                pila[i]="/";
                i++;
                break;
            case "exp":
                pila[i]="exp";
                i++;
                break;
            case "mod":
                pila[i]="mod";
                i++;
                break;
            case "%":
                pila[i]="%";
                i++;
                break;
            case "<operando><operador_relacional><operando>":
                pila[i]="<operando>";
                pila[i+1]="<operador_relacional>";
                pila[i+2]="<operando>";
                i=i+3;
                break;
            case "==":
                pila[i]="==";
                i++;
                break;
            case "!=":
                pila[i]="!=";
                i++;
                break;
            case "<":
                pila[i]="<";
                i++;
                break;
            case ">":
                pila[i]=">";
                i++;
                break;
            case "<=":
                pila[i]="<=";
                i++;
                break;
            case ">=":
                pila[i]=">=";
                i++;
                break;
            case "[<expresion_relacional>]<operador_logico>[<expresion_relacional>]<EL>":
                pila[i]="<EL>";
                pila[i+1]="]";
                pila[i+2]="<expresion_relacional>";
                pila[i+3]="[";
                pila[i+4]="<operador_logico>";
                pila[i+5]="]";
                pila[i+6]="<expresion_relacional>";
                pila[i+7]="[";
                i=i+8;
                break;
            case "<operador_logico>[<expresion_relacional>]<EL>":
                pila[i]="<EL>";
                pila[i+1]="]";
                pila[i+2]="<expresion_relacional>";
                pila[i+3]="[";
                pila[i+4]="<operador_logico>";
                i=i+5;
                break;
            case "&":
                pila[i]="&";
                i++;
                break;
            case "||":
                pila[i]="||";
                i++;
                break;
            case "Si (<condicion>) {<instrucciones> } Sino {<instrucciones> }":
                pila[i]="}";
                pila[i+1]="<instrucciones>";
                pila[i+2]="{";
                pila[i+3]="Sino";
                pila[i+4]="}";
                pila[i+5]="<instrucciones>";
                pila[i+6]="{";
                pila[i+7]=")";
                pila[i+8]="<condicion>";
                pila[i+9]="(";
                pila[i+10]="Si";
                i=i+11;
                break;
            case "<expresion_relacional>":
                pila[i]="<expresion_relacional>";
                i++;
                break;
            case "<expresion_logica>":
                pila[i]="<expresion_logica>";
                i++;
                break;
            case "<instPARA>":
                pila[i]="<instPARA>";
                i++;
                break;
            case "<instMIENTRAS>":
                pila[i]="<instMIENTRAS>";
                i++;
                break;
            case "PARA(<asignacion_numerica>;<expresion_relacional>;<contador>){<instrucciones>}":
                pila[i]="}";
                pila[i+1]="<instrucciones>";
                pila[i+2]="{";
                pila[i+3]=")";
                pila[i+4]="<contador>";
                pila[i+5]=";";
                pila[i+6]="<expresion_relacional>";
                pila[i+7]=";";
                pila[i+8]="<asignacion_numerica>";
                pila[i+9]="(";
                pila[i+10]="PARA";
                i=i+11;
                break;
            case "integ ID :: entero":
                pila[i]="entero";
                pila[i+1]="::";
                pila[i+2]="ID";
                pila[i+3]="integ";
                i=i+4;
                break;
            case "ID <operador_relacional2>":
                pila[i]="<operador_relacional2>";
                pila[i+1]="ID";
                i=i+2;
                break;
            case "++":
                pila[i]="++";
                i++;
                break;
             case "--":
                pila[i]="--";
                i++;
                break;
            case "MIENTRAS(<condicion>) {<instrucciones>}":
                pila[i]="}";
                pila[i+1]="<instrucciones>";
                pila[i+2]="{";
                pila[i+3]=")";
                pila[i+4]="<condicion>";
                pila[i+5]="(";
                pila[i+6]="MIENTRAS";
                i=i+7;
                break;
            case "<tipo> ID (<Par>){ <instrucciones> retorna ID ;}":
                pila[i]="}";
                pila[i+1]=";";
                pila[i+2]="ID";
                pila[i+3]="retorna";
                pila[i+4]="<instrucciones>";
                pila[i+5]="{";
                pila[i+6]=")";
                pila[i+7]="<Par>";
                pila[i+8]="(";
                pila[i+9]="ID";
                pila[i+10]="<tipo>";
                i=i+11;
                break;
            case "FuncionesI <listados> FuncionesF":
                pila[i]="FuncionesF";
                pila[i+1]="<listados>";
                pila[i+2]="FuncionesI";
                i=i+3;
                break;
            case "<tipo_listado> <Lis>":
                pila[i]="<Lis>";
                pila[i+1]="<tipo_listado>";
                i=i+2;
                break;
            case "<listados>":
                pila[i]="<listados>";
                i++;
                break;
            case "<funcion>":
                pila[i]="<funcion>";
                i++;
                break;
            case "<procedimiento>":
                pila[i]="<procedimiento>";
                i++;
                break;
            case "fun <tipo> ID (<Par>){<instrucciones> retorna ID ;}":
                pila[i]="}";
                pila[i+1]=";";
                pila[i+2]="ID";
                pila[i+3]="retorna";
                pila[i+4]="<instrucciones>";
                pila[i+5]="{";
                pila[i+6]=")";
                pila[i+7]="<Par>";
                pila[i+8]="(";
                pila[i+9]="ID";
                pila[i+10]="<tipo>";
                pila[i+11]="fun";
                i=i+12;
                break;
            case "proc ID (<Par>){<instrucciones>}":
                pila[i]="}";
                pila[i+1]="<instrucciones>";
                pila[i+2]="{";
                pila[i+3]=")";
                pila[i+4]="<Par>";
                pila[i+5]="(";
                pila[i+6]="ID";
                pila[i+7]="proc";
                i=i+8;
                break;
            case "PrincipalI <codigo_principal> PrincipalF":
                pila[i]="PrincipalF";
                pila[i+1]="<codigo_principal>";
                pila[i+2]="PrincipalI";
                i=i+3;
                break;
            case "Main ( ) {<codigos> }":
                pila[i]="}";
                pila[i+1]="<codigos>";
                pila[i+2]="{";
                pila[i+3]=")";
                pila[i+4]="(";
                pila[i+5]="Main";
                i=i+6;
                break;
            case "<codigo1> <codigos>":
                pila[i]="<codigos>";
                pila[i+1]="<codigo1>";
                i=i+2;
                break;
            case "<objetos>":
                pila[i]="<objetos>";
                i++;
                break;
            case "<instruccion>":
                pila[i]="<instruccion>";
                i++;
                break;
            case "<llamada>":
                pila[i]="<llamada>";
                i++;
                break;
            case "<creacion>":
                pila[i]="<creacion>";
                i++;
                break;
            case "obj ID nuevo <modelo2>;":
                pila[i]=";";
                pila[i+1]="<modelo2>";
                pila[i+2]="nuevo";
                pila[i+3]="ID";
                pila[i+4]="obj";
                i=i+5;
                break;
            case "ID(<parametros2>)":
                pila[i]=")";
                pila[i+1]="<parametros2>";
                pila[i+2]="(";
                pila[i+3]="ID";
                i=i+4;
                break;
            case "ID <mas_parametros2>":
                pila[i]="<mas_parametros2>";
                pila[i+1]="ID";
                i=i+2;
                break;
            case ", ID <mas_paramatros2>":
                pila[i]="<mas_parametros2>";
                pila[i+1]="ID";
                pila[i+2]=",";
                i=i+3;
                break;
            case "ID <continuacion>":
                pila[i]="<continuacion>";
                pila[i+1]="ID";
                i=i+2;
                break;
            case ":: ID.<accion2>":
                pila[i]="<accion2>";
                pila[i+1]=".";
                pila[i+2]="ID";
                pila[i+3]="::";
                i=i+4;
                break;
            case ".<accion2>":
                pila[i]="<accion2>";
                pila[i+1]=".";
                i=i+2;
                break;
            case "ID(<parametros2>);":
                pila[i]=";";
                pila[i+1]=")";
                pila[i+2]="<parametros2>";
                pila[i+3]="(";
                pila[i+4]="ID";
                i=i+5;
                break;
            case "<valor_numerico>":
                pila[i]="<valor_numerico>";
                i++;
                break;
            case "VERDAD":
                pila[i]="VERDAD";
                i++;
                break;
            case "FALSO":
                pila[i]="FALSO";
                i++;
                break;
            case "caracter":
                pila[i]="caracter";
                i++;
                break;
            case "cadena":
                pila[i]="cadena";
                i++;
                break;
            case "entero":
                pila[i]="entero";
                i++;
                break;
            case "decimal":
                pila[i]="decimal";
                i++;
                break;
            case "integ":
                pila[i]="integ";
                i++;
                break;
            case "flot":
                pila[i]="flot";
                i++;
                break;
            case "cad":
                pila[i]="cad";
                i++;
                break;
            case "car":
                pila[i]="car";
                i++;
                break;
            case "log":
                pila[i]="log";
                i++;
                break;
         
            default:
                break;
        }
        
    }
    
    void error(){
        System.out.println("   error en la sintaxis");
        System.exit(0);
    }
    
    String RetCad2(){
        String temp="";
        int j, k=0;
        for(j=p; j<lista_token.size(); j++)
            temp=temp+lista_token.get(j).getLexema();
        
        return temp;
    }
    
    String RetPila(){
        String CadPila="";
        for(int j=0; j<i; j++)
            CadPila = CadPila+pila[j];
        return CadPila;
    }
    
    void sintactico(){      //analizador sintactico
        String XX;
        String produccion="";
        int posi=0;
        
        do {            
            //Se copian las cadenas retornadas en 
            //"ae" y "XX"
            ae=LexicoLabo(); //no terminales
            XX=pila[i-1]; // Extrea el último elemento de la pila
            posi = RetPos(); //Extrae la posición del caracter analizado en la cadena (inicialemente extrae pos = tamaño del ListaToken
            if(t.Terminal(XX)){
                indicador++;
                if(XX.equals(ae)){
                    i=i-1;  //se descuenta un símbolo de la pila
                    pos--;  //Una vez analizado el no terminal con el caracter, este pasa a la siguiente posición para analizar
                    posi=lista_token.size()-pos;
                    mover(posi); //se lee la cadena de entrada desde
                                //la posicion "posi"
                }
                else{
                    System.out.println("1");
                    System.out.println(ae);
                    System.out.println(XX);
                    error();
                   
                }
            }

            else //Cuando el valor de la pila (XX) es un no Terminal
                if(t.ExisteInterseccion(XX, ae)){
                    indicador=0;
                    //Se extrae la producción
                    produccion=t.RetProduccion();
                    System.out.println("| "+RetPila()+"|  "+RetCad2()+"   |"+XX+"-->"+produccion);
                    //lo manda al array del jframe
                    lista.add(new Sin(RetPila(), RetCad2(), XX, produccion));
                    i=i-1;  //se descuenta un símbolo de la pila
                            //si la producción es & no se mete en la pila
                    if(!produccion.equals("vacio")){
                        //StringBuilder stringBuilder = new StringBuilder(produccion);
                        //String produccionInvertida = stringBuilder.reverse().toString();
                        Empilar(produccion);
                    }
                }
                else{
                    System.out.println("2");
                    System.out.println(XX);
                    System.out.println(ae);
                    error();
                }
        } while (!XX.equals("$"));
    }
}

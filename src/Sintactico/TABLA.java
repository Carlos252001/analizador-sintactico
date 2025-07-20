/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sintactico;

/**
 *
 * @author Luis Angel
 */
public class TABLA {
    String M[][] = new String[80][67];
    String produccion = "";
    
     TABLA(){
        for(int i=0; i<80; i++)
            for(int j=0; j<67; j++)
                M[i][j]=" ";
        
        //vacio == landa
        //& == simbolo relacional
        
        //creamos tabla
        M[0][1]="Inicio"; M[0][2]="Final"; M[0][3]="ConstantesI"; M[0][4]="ConstantesF"; M[0][5]="const"; M[0][6]="ID"; M[0][7]="::"; M[0][8]=";"; M[0][9]="EntidadesI"; M[0][10]="EntidadesF"; M[0][11]="ent"; M[0][12]="{"; M[0][13]="}"; M[0][14]="("; M[0][15]=")"; M[0][16]=","; M[0][17]="acc"; M[0][18]="["; M[0][19]="]"; M[0][20]="ipt"; M[0][21]="[["; M[0][22]="]]"; M[0][23]="mep"; M[0][24]="+"; M[0][25]="-"; M[0][26]="*"; M[0][27]="/"; M[0][28]="mod"; M[0][29]="%"; M[0][30]="exp"; M[0][31]="=="; M[0][32]="!="; M[0][33]="<"; M[0][34]="<="; M[0][35]=">"; M[0][36]=">="; M[0][37]="||"; M[0][38]="Si"; M[0][39]="Sino"; M[0][40]="PARA"; M[0][41]="entero"; M[0][42]="++"; M[0][43]="--"; M[0][44]="MIENTRAS"; M[0][45]="FuncionesI"; M[0][46]="FuncionesF"; M[0][47]="fun"; M[0][48]="retorna"; M[0][49]="proc"; M[0][50]="PrincipalI"; M[0][51]="PrincipalF"; M[0][52]="Main"; M[0][53]="obj"; M[0][54]="."; M[0][55]="decimal"; M[0][56]="integ"; M[0][57]="VERDAD"; M[0][58]="FALSO"; M[0][59]="flot"; M[0][60]="cad"; M[0][61]="car"; M[0][62]="log"; M[0][63]="$"; M[0][64]="caracter"; M[0][65]="cadena"; M[0][66]="&";
        M[1][0]="<programa>"; M[1][1]="Inicio <contenido_programa> Final";
        M[2][0]="<contenido_programa>"; M[2][3]="<bloque_constantes><bloque_entidades><bloque_subprogramas><bloque_principal>"; M[2][9]="<bloque_constantes><bloque_entidades><bloque_subprogramas><bloque_principal>"; M[2][45]="<bloque_constantes><bloque_entidades><bloque_subprogramas><bloque_principal>"; M[2][50]="<bloque_constantes><bloque_entidades><bloque_subprogramas><bloque_principal>"; 
        M[3][0]="<bloque_constantes>"; M[3][3]="ConstantesI<constantes>ConstantesF"; M[3][9]="vacio"; M[3][45]="vacio"; M[3][50]="vacio";
        M[4][0]="<constantes>"; M[4][5]="<constante><C>"; 
        M[5][0]="<constante>"; M[5][5]="const <tipo> ID :: <valor>;"; 
        M[6][0]="<C>"; M[6][4]="vacio"; M[6][5]="<constantes>"; 
        M[7][0]="<bloque_entidades>"; M[7][9]="EntidadesI<entidades>EntidadesF"; M[7][45]="vacio"; M[7][50]="vacio"; 
        M[8][0]="<entidades>"; M[8][11]="<entidad><E>"; 
        M[9][0]="<entidad>"; M[9][11]="ent ID { <contenido_entidad> }"; 
        M[10][0]="<contenido_entidad>"; M[10][6]="<modelo_obligatorio><contenido_entidad2>"; M[10][13]="vacio"; 
        M[11][0]="<contenido_entidad2>"; M[11][6]="<contenido_entidad3><contenido_entidad2>"; M[11][13]="vacio"; M[11][17]="<contenido_entidad3><contenido_entidad2>"; M[11][56]="<contenido_entidad3><contenido_entidad2>"; M[11][59]="<contenido_entidad3><contenido_entidad2>"; M[11][60]="<contenido_entidad3><contenido_entidad2>"; M[11][61]="<contenido_entidad3><contenido_entidad2>"; M[11][62]="<contenido_entidad3><contenido_entidad2>";
        M[12][0]="<contenido_entidad3>"; M[12][6]="<modelo>"; M[12][17]="<acciones>"; M[12][56]="<atributo>"; M[12][59]="<atributo>"; M[12][60]="<atributo>"; M[12][61]="<atributo>"; M[12][62]="<atributo>";
        M[13][0]="<E>"; M[13][10]="vacio"; M[13][11]="<entidades>"; 
        M[14][0]="<atributo>"; M[14][56]="<tipo> ID;"; M[14][59]="<tipo> ID;"; M[14][60]="<tipo> ID;"; M[14][61]="<tipo> ID;"; M[14][62]="<tipo> ID;";
        M[15][0]="<modelo_obligatorio>"; M[15][6]="ID(){}"; 
        M[16][0]="<modelo>"; M[16][6]="ID(<Par>){<contenido_modelo>}";
        M[17][0]="<Par>"; M[17][15]="vacio"; M[17][56]="<parametros>"; M[17][59]="<parametros>"; M[17][60]="<parametros>"; M[17][61]="<parametros>"; M[17][62]="<parametros>";
        M[18][0]="<parametros>"; M[18][56]="<parametro><mas_parametros>"; M[18][59]="<parametro><mas_parametros>"; M[18][60]="<parametro><mas_parametros>"; M[18][61]="<parametro><mas_parametros>"; M[18][62]="<parametro><mas_parametros>";
        M[19][0]="<mas_parametros>"; M[19][15]="vacio"; M[19][16]=",<parametros>";
        M[20][0]="<parametro>"; M[20][56]="<tipo>ID"; M[20][59]="<tipo>ID"; M[20][60]="<tipo>ID"; M[20][61]="<tipo>ID"; M[20][62]="<tipo>ID";
        M[21][0]="<contenido_modelo>"; M[21][13]="vacio"; M[21][56]="<atributos_modelados>"; M[21][59]="<atributos_modelados>"; M[21][60]="<atributos_modelados>"; M[21][61]="<atributos_modelados>"; M[21][62]="<atributos_modelados>";
        M[22][0]="<atributos_modelados>"; M[22][56]="<tipo> ID :: <valor> ; <AM>"; M[22][59]="<tipo> ID :: <valor> ; <AM>"; M[22][60]="<tipo> ID :: <valor> ; <AM>"; M[22][61]="<tipo> ID :: <valor> ; <AM>"; M[22][62]="<tipo> ID :: <valor> ; <AM>";
        M[23][0]="<AM>"; M[23][13]="vacio"; M[23][56]="<atributos_modelados>"; M[23][59]="<atributos_modelados>"; M[23][60]="<atributos_modelados>"; M[23][61]="<atributos_modelados>"; M[23][62]="<atributos_modelados>";
        M[24][0]="<acciones>"; M[24][17]="acc <tipo_accion>"; 
        M[25][0]="<tipo_accion>"; M[25][6]="<accion_p>"; M[25][56]="<accion_f>"; M[25][59]="<accion_f>"; M[25][60]="<accion_f>"; M[25][61]="<accion_f>"; M[25][62]="<accion_f>";
        M[26][0]="<accion_p>"; M[26][6]="ID (<Par>) {<instrucciones>}";
        M[27][0]="<instrucciones>"; M[27][13]="vacio"; M[27][20]="<instruccion><instrucciones>"; M[27][23]="<instruccion><instrucciones>"; M[27][38]="<instruccion><instrucciones>"; M[27][40]="<instruccion><instrucciones>"; M[27][44]="<instruccion><instrucciones>"; M[27][48]="vacio"; M[27][56]="<instruccion><instrucciones>"; M[27][59]="<instruccion><instrucciones>"; M[27][60]="<instruccion><instrucciones>"; M[27][61]="<instruccion><instrucciones>"; M[27][62]="<instruccion><instrucciones>";
        M[28][0]="<instruccion>"; M[28][20]="<entrada_salida>"; M[28][23]="<entrada_salida>"; M[28][38]="<selectiva>"; M[28][40]="<repetitiva>"; M[28][44]="<repetitiva>"; M[28][56]="<declarativa_o_asignacion>"; M[28][59]="<declarativa_o_asignacion>"; M[28][60]="<declarativa_o_asignacion>"; M[28][61]="<declarativa_o_asignacion>"; M[28][62]="<declarativa_o_asignacion>";
        M[29][0]="<declarativa_o_asignacion>"; M[29][56]="<tipo> ID <siguiente>"; M[29][59]="<tipo> ID <siguiente>"; M[29][60]="<tipo> ID <siguiente>"; M[29][61]="<tipo> ID <siguiente>"; M[29][62]="<tipo> ID <siguiente>";
        M[30][0]="<siguiente>"; M[30][7]="<asignacion>"; M[30][8]="<declaracion>"; M[30][16]="<declaracion>"; M[30][18]="<declaracion>";
        M[31][0]="<declaracion>"; M[31][8]="<Nom>;"; M[31][16]="<Nom>;"; M[31][18]="[entero];"; 
        M[32][0]="<nombres>"; M[32][6]="ID <Nom>"; 
        M[33][0]="<Nom>"; M[33][8]="vacio"; M[33][16]=",<nombres>"; 
        M[34][0]="<asignacion>"; M[34][7]=":: <valor> <AS> ;"; 
        M[35][0]="<AS>"; M[35][8]="vacio"; M[35][16]=", ID :: <valor> <AS>"; 
        M[36][0]="<entrada_salida>"; M[36][20]="<entrada>"; M[36][23]="<salida>"; 
        M[37][0]="<entrada>"; M[37][20]="ipt [[ ID ]];"; 
        M[38][0]="<salida>"; M[38][23]="mep [[ <mostrar> ]];";
        M[39][0]="<mostrar>"; M[39][6]="ID <concatenacion>"; M[39][41]="<valor><concatenacion>"; M[39][55]="<valor><concatenacion>"; M[39][57]="<valor><concatenacion>"; M[39][58]="<valor><concatenacion>"; M[39][64]="<valor><concatenacion>"; M[39][65]="<valor><concatenacion>";
        M[40][0]="<concatenacion>"; M[40][22]="vacio"; M[40][24]="+ <mostrar>"; 
        M[41][0]="<expresion_aritmetica>"; M[41][6]="<operando><operador_aritmetico><operando><EA>"; M[41][14]="<operando><operador_aritmetico><operando><EA>"; M[41][41]="<operando><operador_aritmetico><operando><EA>"; M[41][55]="<operando><operador_aritmetico><operando><EA>"; 
        M[42][0]="<EA>"; M[42][15]="vacio"; M[42][24]="<operador_aritmetico><operando><EA>"; M[42][25]="<operador_aritmetico><operando><EA>"; M[42][26]="<operador_aritmetico><operando><EA>"; M[42][27]="<operador_aritmetico><operando><EA>"; M[42][28]="<operador_aritmetico><operando><EA>"; M[42][29]="<operador_aritmetico><operando><EA>"; M[42][30]="<operador_aritmetico><operando><EA>";
        M[43][0]="<operando>"; M[43][6]="ID"; M[43][14]="(<expresion_aritmetica>)"; M[43][41]="<valor_numerico>"; M[43][55]="<valor_numerico>"; 
        M[44][0]="<operador_aritmetico>"; M[44][24]="+"; M[44][25]="-"; M[44][26]="*"; M[44][27]="/"; M[44][28]="mod"; M[44][29]="%"; M[44][30]="exp";
        M[45][0]="<expresion_relacional>"; M[45][6]="<operando><operador_relacional><operando>"; M[45][14]="<operando><operador_relacional><operando>"; M[45][41]="<operando><operador_relacional><operando>"; M[45][55]="<operando><operador_relacional><operando>"; 
        M[46][0]="<operador_relacional>"; M[46][31]="=="; M[46][32]="!="; M[46][33]="<"; M[46][34]="<="; M[46][35]=">"; M[46][36]=">=";
        M[47][0]="<expresion_logica>"; M[47][18]="[<expresion_relacional>]<operador_logico>[<expresion_relacional>]<EL>"; 
        M[48][0]="<EL>"; M[48][15]="vacio"; M[48][37]="<operador_logico>[<expresion_relacional>]<EL>"; M[48][66]="<operador_logico>[<expresion_relacional>]<EL>";//acaaa
        M[49][0]="<operador_logico>"; M[49][37]="||"; M[49][66]="&";//acaaaa
        M[50][0]="<selectiva>"; M[50][38]="Si (<condicion>) {<instrucciones> } Sino {<instrucciones> }"; 
        M[51][0]="<condicion>"; M[51][6]="<expresion_relacional>"; M[51][14]="<expresion_relacional>"; M[51][18]="<expresion_logica>"; M[51][41]="<expresion_relacional>"; M[51][55]="<expresion_relacional>"; 
        M[52][0]="<repetitiva>"; M[52][40]="<instPARA>"; M[52][44]="<instMIENTRAS>"; 
        M[53][0]="<instPARA>"; M[53][40]="PARA(<asignacion_numerica>;<expresion_relacional>;<contador>){<instrucciones>}"; 
        M[54][0]="<asignacion_numerica>"; M[54][56]="integ ID :: entero"; 
        M[55][0]="<contador>"; M[55][6]="ID <operador_relacional2>"; 
        M[56][0]="<operador_relacional2>"; M[56][42]="++"; M[56][43]="--"; 
        M[57][0]="<instMIENTRAS>"; M[57][44]="MIENTRAS(<condicion>) {<instrucciones>}"; 
        M[58][0]="<accion_f>"; M[58][56]="<tipo> ID (<Par>){ <instrucciones> retorna ID ;}"; M[58][59]="<tipo> ID (<Par>){ <instrucciones> retorna ID ;}"; M[58][60]="<tipo> ID (<Par>){ <instrucciones> retorna ID ;}"; M[58][61]="<tipo> ID (<Par>){ <instrucciones> retorna ID ;}"; M[58][62]="<tipo> ID (<Par>){ <instrucciones> retorna ID ;}";
        M[59][0]="<bloque_subprogramas>"; M[59][45]="FuncionesI <listados> FuncionesF"; M[59][50]="vacio"; 
        M[60][0]="<listados>"; M[60][47]="<tipo_listado> <Lis>"; M[60][49]="<tipo_listado> <Lis>"; 
        M[61][0]="<Lis>"; M[61][46]="vacio"; M[61][47]="<listados>"; M[61][49]="<listados>"; 
        M[62][0]="<tipo_listado>"; M[62][47]="<funcion>"; M[62][49]="<procedimiento>"; 
        M[63][0]="<funcion>"; M[63][47]="fun <tipo> ID (<Par>){<instrucciones> retorna ID ;}";
        M[64][0]="<procedimiento>"; M[64][49]="proc ID (<Par>){<instrucciones>}"; 
        M[65][0]="<bloque_principal>"; M[65][50]="PrincipalI <codigo_principal> PrincipalF"; 
        M[66][0]="<codigo_principal>"; M[66][52]="Main ( ) {<codigos> }"; 
        M[67][0]="<codigos>"; M[67][6]="<codigo1> <codigos>"; M[67][13]="vacio"; M[67][20]="<codigo1> <codigos>"; M[67][23]="<codigo1> <codigos>"; M[67][38]="<codigo1> <codigos>"; M[67][40]="<codigo1> <codigos>"; M[67][44]="<codigo1> <codigos>"; M[67][47]="<codigo1> <codigos>"; M[67][49]="<codigo1> <codigos>"; M[67][53]="<codigo1> <codigos>"; M[67][56]="<codigo1> <codigos>"; M[67][59]="<codigo1> <codigos>"; M[67][60]="<codigo1> <codigos>"; M[67][61]="<codigo1> <codigos>"; M[67][62]="<codigo1> <codigos>";
        M[68][0]="<codigo1>"; M[68][6]="<objetos>"; M[68][20]="<instruccion>"; M[68][23]="<instruccion>"; M[68][38]="<instruccion>"; M[68][40]="<instruccion>"; M[68][44]="<instruccion>"; M[68][47]="<funcion>"; M[68][49]="<procedimiento>"; M[68][53]="<objetos>"; M[68][56]="<instruccion>"; M[68][59]="<instruccion>"; M[68][60]="<instruccion>"; M[68][61]="<instruccion>"; M[68][62]="<instruccion>";
        M[69][0]="<objetos>"; M[69][6]="<llamada>"; M[69][53]="<creacion>"; 
        M[70][0]="<creacion>"; M[70][53]="obj ID nuevo <modelo2>;"; 
        M[71][0]="<modelo2>"; M[71][6]="ID(<parametros2>)"; 
        M[72][0]="<parametros2>"; M[72][6]="ID <mas_parametros2>"; M[72][15]="vacio"; 
        M[73][0]="<mas_parametros2>"; M[73][15]="vacio"; M[73][16]=", ID <mas_paramatros2>"; 
        M[74][0]="<llamada>"; M[74][6]="ID <continuacion>"; 
        M[75][0]="<continuacion>"; M[75][7]=":: ID.<accion2>"; M[75][54]=".<accion2>"; 
        M[76][0]="<accion2>"; M[76][6]="ID(<parametros2>);"; 
        M[77][0]="<valor>"; M[77][41]="<valor_numerico>"; M[77][55]="<valor_numerico>"; M[77][57]="VERDAD"; M[77][58]="FALSO"; M[77][64]="caracter"; M[77][65]="cadena";
        M[78][0]="<valor_numerico>"; M[78][41]="entero"; M[78][55]="decimal";
        M[79][0]="<tipo>"; M[79][56]="integ"; M[79][59]="flot"; M[79][60]="cad"; M[79][61]="car"; M[79][62]="log";
        
    }
     
    boolean Terminal(String car){
        for(int i=1; i<67; i++)
            if(M[0][i].equals(car))
                return true;
        return false;
    }
    
    String RetProduccion(){
        return produccion;
    }
    
    boolean ExisteInterseccion(String XX, String ae){
        int i, x=0, y=0;
        for(i=1; i<67; i++)
            if(M[0][i].equals(ae))
                x=i;
        for(i=1; i<80; i++)
            if(M[i][0].equals(XX))
                y=i;
        if(x==0 || y ==0)
            return false;
        
        //Representa todos los terminales que no tienen intersección
        else if(M[y][x].equals(" ")){
            if(M[0][x]=="Final"){
                return true;
            }else if(M[0][x]=="ConstantesF"){
                return true;
            }else if(M[0][x]=="{"){
                return true;
            }else if(M[0][x]=="]"){
                return true;
            }else if(M[0][x]=="[["){
                return true;
            }else if(M[0][x]=="Sino"){
                return true;
            }else if(M[0][x]=="PrincipalF"){
                return true;
            }else
                return false;
        }
        else{
            //StringBuilder stringBuilder = new StringBuilder( M[y][x]);
            //String produccionInvertida = stringBuilder.reverse().toString();
            produccion =M[y][x] ;
            return true;
        }
    }
}

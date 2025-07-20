       
package Lexico;
import java.util.ArrayList;
/**
 *
 * @author CARLOS JESUS
 */
public class analizador {
    //Creamos un arraylist de tipo Token
    ArrayList<Token> lista_token = new ArrayList();
    int indicadorLineaComentario =0;
    
    //Constructor que recibe el arraylist 
    public analizador(ArrayList<Token> lista_token){
        this.lista_token = lista_token;
    }
    
    //Función que analizará cada línea
    public void analizar(String cadena){
    int cont =0;
    int estado = 0;
    int numero_token = 0;
    String lexema = "";
    String tipo = "";
    //Aquí se hace uso de la función separador que retorna las líneas del texto
    String [] lineas = separador(cadena, '\n');
        
    //Recorremos el vector de líneas, es decir línea por línea
        for (int i = 0; i < lineas.length; i++) {
            indicadorLineaComentario =0;
            //Recorremos la línea, caracter por caracter
            for (int j = 0; j < lineas[i].length(); j++) {
                int n_actual, n_siguiente = -1;
                
                //Obtenemos el número del caracter en la tabla Ascii
                n_actual = lineas[i].codePointAt(j);
                if (estado == 0){
                    //Hallamos el estado de transición del caracter actual
                    estado = estado_transicion(n_actual);
                }
                //Con un try captamos el siguiente caracter
                try{
                    n_siguiente = lineas[i].codePointAt(j+1);
                }catch(Exception e){
                    
                }
                switch(estado){ 
                    //Estado de aceptación de identificadores
                    case 1:
                        lexema = lexema + lineas[i].charAt(j);
                        //Si encuentra alguna letra, guión bajo o número, se queda en el estado 1
                        if((n_siguiente > 96 && n_siguiente <123) || (n_siguiente > 64 && n_siguiente < 91) || (n_siguiente > 47 && n_siguiente < 58) || n_siguiente == 95){
                        estado = 1;
                        }else {
                            //Verfica si es una palabra reservada, si es manda su token, sino envía -1
                            int reservada = Verificar_cadena(lexema);
                            if(reservada >0){
                            numero_token = reservada;
                            tipo = "Palabra reservada";
                            estado = 0;}
                            else{
                            numero_token = 1;
                            tipo = "ID";
                            estado = 0;
                        }
                        }
                     break;
                     
                    //Estado de aceptación que inicia con un número
                    case 2:
                        lexema = lexema + lineas[i].charAt(j);
                        //Si el siguiente es número, se mantiene el estado
                        if(n_siguiente > 47 && n_siguiente < 58){
                            estado =2;
                         //Si el siguiente es un punto
                        }else if(n_siguiente == 46 ){
                            //Cambia al estado de decimales
                            estado = 5;
                        }
                         else{
                            numero_token = 2;
                            tipo = "entero";
                            estado = 0;
                        }
                      break;
                    
                     //Estado de Separador de sentencia
                    case 3:
                        lexema = String.valueOf(lineas[i].charAt(j));
                        numero_token = 3;
                        tipo = "Separador de sentencia";
                        estado = 0;
                       break;
                       
                    //Estado de operador punto
                    case 4:
                        lexema = lexema + lineas[i].charAt(j);
                       //Si el siguiente es un número
                        if(n_siguiente > 47 && n_siguiente < 58){
                            //Cambia el estado a decimales
                            estado = 5;
                        }else{
                            numero_token = 4;
                            tipo = "Operador punto";
                            estado = 0;
                        }
                        break;
                        
                    //Estado de aceptación de decimales    
                    case 5:
                         lexema = lexema + lineas[i].charAt(j);
                        //Si el siguiente es un número, se mantiene
                        if(n_siguiente > 47 && n_siguiente < 58){
                            estado = 5;
                        }else{
                            numero_token = 5;
                            tipo = "decimal";
                            estado = 0;
                        }
                         break;
                         
                    //Estado de aceptación del apóstrofe
                    case 6:
                        //Si el siguiente es una letra minúscula o maypuscula, cambia de estado
                        if((n_siguiente > 96 && n_siguiente <123) || (n_siguiente > 64 && n_siguiente < 91)){
                            estado = 229;
                        }
                        else{
                         lexema = String.valueOf(lineas[i].charAt(j));
                        numero_token = 999;
                        tipo = "Error";
                        estado = 0;
                            
                         /*lexema = lexema + lineas[i].charAt(j);
                         numero_token = 6;
                         tipo = "Apóstrofe";
                         estado = 0;*/
                        }
                        
                        break;
                        
                    //Estado de aceptación de cadenas
                    case 7:
                        numero_token = 7;
                        tipo = "Cadena";
                        estado = 0;
                     break;
                     
                    //Estado de aceptación del guión o si inicia con un guión
                    case 8: 
                        lexema = lexema + lineas[i].charAt(j);
                        //Si el siguiente es otro guión
                        if(n_siguiente == 45){
                            //Cambia al estado de línea de comentario
                            estado = 209;
                           
                        }
                        else{
                            numero_token = 8;
                            tipo = "Guión";
                            estado = 0;
                        }
                        break;
                    //Estado de aceptación de la línea de comentario
                    /*case 9:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 9;
                        tipo = "Línea de comentario";
                        estado = 0;
                        break;*/
                    //Estado de aceptación de la división 
                    case 10:
                        lexema = lexema + lineas[i].charAt(j);
                        //Si el siguiente es un guión
                        if(n_siguiente == 45){
                            //Cambia a estado de aceptación de inicio de linea de comentario
                            estado =11;
                        } else{
                          numero_token = 10;
                          tipo = "División";
                          estado = 0;
                        }
                        break;
                    //Estado de aceptación de inicio de linea de comentario
                    case 11:
                        if(j!=(lineas[i].length()-1)){
                            estado =11;
                            indicadorLineaComentario=1;
                            System.out.println("1");
                        }
                        /*else{
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token =11;
                        tipo = "Comentario";
                        estado = 0;
                        indicadorLineaComentario = 1;
                            System.out.println("2");
                        }*/
                        else{
                            estado=0;
                        }
                        break;
                    //Estado de aceptación de cierre de de bloque de comentario 
                    case 12:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 12;
                        tipo =  "Cierre de bloque de comentario";
                        estado = 0;
                        break;
                        
                    //Estado de aceptación de los espacios, tab y el salto de línea 
                    case 100:
                        //Si es -2, el estado vuelve a 0
                        estado = -2;
                       break;
                     
                    //Estado de Suma
                    case 200:
                        lexema = lexema + lineas[i].charAt(j);
                        //Si hay otro operador suma, cambia al estado de aumento
                        if(n_siguiente==43){
                            estado = 208;
                        }else{
                        numero_token = 200;
                        tipo = "Operador suma";
                        estado = 0;
                        }
                       break;
                    //Estado de multiplicación
                    case 201:
                        lexema = String.valueOf(lineas[i].charAt(j));
                        numero_token = 201;
                        tipo = "Multiplicación";
                        estado = 0;
                       break;
                    //Estado de Porcentaje
                    case 202:
                        lexema = String.valueOf(lineas[i].charAt(j));
                        numero_token = 202;
                        tipo = "Porcentaje";
                        estado = 0;
                       break;
                    //Estado de aceptación de igual
                    case 203:
                        lexema = lexema + lineas[i].charAt(j);
                        //Si hay otro igual, cambia al estado de = relacional
                        if(n_siguiente == 61){
                         estado = 216;
                        }else{
                         numero_token = 999;
                         tipo = "Error";
                         estado = 0;
                        }
                        break;
                    //Estado de Menor
                    case 204:
                        lexema = lexema + lineas[i].charAt(j);
                        //Si el siguiente es un igual
                        if(n_siguiente == 61){
                            estado = 205;
                        }else{
                            numero_token = 204;
                            tipo = "menor";
                            estado = 0;
                        }
                       break;
                    //Estado de Menor igual
                    case 205:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 205;
                        tipo = "Menor igual";
                        estado = 0;
                       break;
                    //Estado de Mayor
                    case 206:
                        lexema = lexema + lineas[i].charAt(j);
                        //Si el siguiente es un igual
                        if(n_siguiente == 61){
                            estado = 207;
                        }else{
                            numero_token = 206;
                            tipo = "mayor";
                            estado = 0;
                        }
                       break;
                    //Estado de Mayor igual
                    case 207:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 207;
                        tipo = "Menor igual";
                        estado = 0;
                       break;
                    //Estado de aumento
                    case 208:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 208;
                        tipo = "Aumento";
                        estado = 0;
                       break;
                    //Estado de decremento
                    case 209:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 209;
                        tipo = "Decremento";
                        estado = 0;
                       break;
                    //Estado de Exclamación
                    case 210:
                        lexema = lexema + lineas[i].charAt(j);
                        //Si el siguiente es un igual
                        if(n_siguiente == 61){
                            estado = 211;
                        }else{
                            numero_token = 999;
                            tipo = "Error";
                            estado = 0;
                        }
                       break;
                    //Estado de Diferente
                    case 211:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 211;
                        tipo = "Diferente";
                         estado = 0;
                       break;
                    //Estado de Y lógico
                    case 212:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 212;
                        tipo = "Y lógico";
                         estado = 0;
                       break;
                    //Estado de negación
                    case 213:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 213;
                        tipo = "Negación";
                         estado = 0;
                       break;
                    //Estado de Barra vertical
                    case 214:
                        lexema = lexema + lineas[i].charAt(j);
                        //Si le sigue otra barra vertical, cambia al estado de o lógico
                        if(n_siguiente == 124){
                         estado = 215;
                        }else{
                         numero_token = 999;
                         tipo = "Error";
                         estado = 0;
                        }
                       break;
                    //Estado de O lógico
                    case 215:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 215;
                        tipo = "O lógico";
                        estado = 0;
                       break;
                    //Estado de aceptación de igual relacional
                    case 216:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 216;
                        tipo = "Igual Relacional";
                        estado = 0;
                       break;
                    //Estado de dos puntos : 
                    case 217:
                         lexema = lexema + lineas[i].charAt(j);
                         //Si le siguen otros dos puntos, cambia al estado de igual de asignación
                        if(n_siguiente == 58){
                         estado = 218;
                        }else{
                         numero_token = 999;
                        tipo = "Error";
                        estado = 0;
                        }
                        break;
                        
                    //Estado de igual de asignación ::
                    case 218:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 218;
                        tipo = "Igual Asignación";
                        estado = 0;
                       break;
                    //Estado de (
                    case 219:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 219;
                        tipo = "Paréntesis abierto";
                        estado = 0;
                       break;
                    // Estado )
                    case 220:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 220;
                        tipo = "Paréntesis cerrado";
                        estado = 0;
                       break;
                    //Estado de corchete abierto
                    case 221:
                        lexema = lexema + lineas[i].charAt(j);
                        //Si le sigue otro corchete
                        if(n_siguiente == 91){
                         estado = 222;
                        }else{
                         
                         numero_token = 221;
                         tipo = "Corchete Abierto";
                        estado = 0;
                        }
                       break;
                    //Estado de doble corchete abierto[[
                    case 222:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 222;
                        tipo = "Doble Corchete abierto";
                        estado = 0;
                        break;
                    //Estado de corchete cerrado
                    case 223:
                        lexema = lexema + lineas[i].charAt(j);
                        if(n_siguiente == 93){
                         estado = 224;
                        }else{
                        
                         numero_token = 223;
                         tipo = "Corchete Cerrado";
                         estado = 0;
                        }
                       break;
                    //Estado de doble corchete cerrado ]]
                    case 224:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 224;
                        tipo = "Doble Corchete cerrado";
                        estado = 0;
                        break;
                    //Estado de {
                    case 225:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 225;
                        tipo = "Llave abierta";
                         estado = 0;
                       break;
                    //Estado de }
                    case 226:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 226;
                        tipo = "Llave cerrada";
                         estado = 0;
                       break;
                    //Estado de "
                    case 227:
                        //Si hay dos comillas juntas
                        if(n_siguiente==34){
                         lexema = lexema + lineas[i].charAt(j);
                         numero_token = 7;
                        tipo = "Comilla";
                        estado = 0;
                         //Si la comilla se encuentra al final de la línea o hay espacio, tabulacion o salto de línea
                        }else if((j== (lineas[i].length()-1))|| n_siguiente == 32 || n_siguiente == 13 || n_siguiente == 9){
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 7;
                        tipo = "Comilla";
                        estado = 0;
                        }
                        else {
                            //Guarda la posición donde se encuentra el caracter de la Comilla "
                            cont = j;
                            //Cambia al estado de cadena
                            estado = 231;
                        }
                       break;
                    //Estado de ,
                      case 228:
                        lexema = lexema + lineas[i].charAt(j);
                        numero_token = 228;
                        tipo = "Coma";
                         estado = 0;
                       break;
                     
                    //Estado de un caracter
                      case 229:
                          //Si el siguiente es un apóstrofe
                          if(n_siguiente == 39){
                              lexema = lexema + lineas[i].charAt(j);
                              //Se declara como cadena
                              estado = 230;
                          }else{
                          lexema = "'";
                          numero_token = 999;
                          tipo = "Error";
                          estado = 0;
                          /*lexema = "'";
                          numero_token = 6;
                          tipo = "Apóstrofe";
                          estado = 0;*/
                          j--;
                          }
                          break;
                      
                      case 230:
                        numero_token = 229;
                        tipo = "Caracter";
                        estado = 0;
                          break;
                          
                          
                     //Estado de una cadena  
                      case 231:
                         lexema = lexema + lineas[i].charAt(j);
                         //Si el siguiente es una comilla
                        if(n_siguiente==34){
                            //Se declara como cadena
                            estado =7;
                        }
                        else {
                            //Si se encentra en el penúltimo caracter
                            if(j == (lineas[i].length()-2)){
                                //Si siguiente no es una comilla
                                if(n_siguiente!=34){
                                    lexema ="";
                                    //Guarda la comilla en el lexema
                                    lexema  = lexema + lineas[i].charAt(cont);
                                    numero_token = 7;
                                    tipo = "Comilla";
                                    estado = 0;
                                    //Asigna el valor de j en la posición donde estaba la priemra comilla
                                    j = cont;
                                }
                            }
                            else
                            estado = 231;
                        }
                       break;
                    //Estado del error léxico     
                    case 999:
                        lexema = String.valueOf(lineas[i].charAt(j));
                        numero_token = 999;
                        tipo = "Error";
                        estado = 0;
                        break;
                }
                if (estado == 0){
                  //Se crea un nuevo token y se guarda en el arraylist 
                  if(indicadorLineaComentario == 0)
                  lista_token.add(new Token(lexema, numero_token, i+1,j+1,tipo));
                  lexema = "";
                  tipo = ""; 
                                  }
                //Si viene un salto de linea, tabulacion o espacios.
                if (estado == -2){
                    estado = 0;
                }
            }
        }
    }
    //Sirve para reconocer diferentes caracteres, por ejemplo, en la tabla Ascii:
    public int estado_transicion(int n){
        //Validación:
        //Todas las minúsculas       Todas las mayúsculas
        if ((n > 96 && n <123) || (n > 64 && n < 91) ){
          //Estado de aceptación de letras
          return 1; 
        //Se crea el estado de aceptación de los números
        }else if(n > 47 && n < 58){
            //Estado de aceptación de los números
            return 2;
        }
        else if(n == 32 || n == 13 || n == 9){
            //Estado de aceptación de los espacios, tab y el salto de carro
            return 100;
        } else if(n == 59){
            //Estado de aceptación del separador de sentencia
            return 3;
        } else if(n==46){
            //Estado de aceptación de un punto
            return 4;
        } else if (n==45){
            //Estado de aceptación de guión
            return 8;
        } else if(n==47){
            //Estado de aceptación de la barra diagonal
            return 10;
        } else if(n==39){
            //Estado de aceptación del apóstrofe
            return 6;
        //OPERADORES
        }else if(n==43){
            //Estado de acepatación de la suma
            return 200;
        }else if(n==42){
            //Estado de aceptación de la multiplicación
            return 201;
        }else if(n==37){
            //Estado de aceptación del porcentaje
            return 202;
        }else if(n==61){
            //Estado de aceptación del igual
            return 203;
        }else if(n==60){
            //Estado de aceptación del menor
            return 204;
        }else if(n==62){
            //Estado de aceptación del mayor
            return 206;
        }else if (n==33){
            //Estado de Exclamación
            return 210;
        }else if (n==38){
            //Estado de Y lógico
            return 212;
        }else if (n==126){
            //Estado de Negaación
            return 213;
        }else if (n==124){
            //Estado de barra vertical
            return 214;
        }else if (n==58){
            //Estado de :
            return 217;
        }else if (n==40){
            //Estado de (
            return 219;
        }else if (n==41){
            //Estado de )
            return 220;
        }else if (n==91){
            //Estado de [
            return 221;
        }else if (n==93){
            //Estado de ]
            return 223;
        }else if (n==123){
            //Estado de {
            return 225;
        }else if (n==125){
            //Estado de }
            return 226;
        }else if (n==34){
            //Estado de "
            return 227;
        }else if (n==44){
            //Estado de ,
            return 228;
        }
            
        else{
            // Estado de un error léxico
            return 999;
        }
        
    }
    
    //Funciónpara verificar si el lexema es una palabra reservada
    public int Verificar_cadena(String cadena){
        //LOs token de las palabras reservadas van a iniciar desde el N° 200
        switch (cadena){
            case "Inicio":
                return 301;
            case "Final":
                return 302;
            case "ConstantesI":
                return 303;
            case "ConstantesF":
                return 304;
            case "const":
                return 305;
            case "integ":
                return 306;
            case "flot":
                return 307;
            case "cad":
                return 308;
            case "log":
                return 309;
            case "car":
                return 310;
            //case "vec":
              //  return 311;
            case "exp":
                return 312;
            case "mod":
                return 313;
            case "VERDAD":
                return 314;
            case "FALSO":
                return 315;
            case "ipt":
                return 316;
            case "mep":
                return 317;
            case "Si":
                return 318;
            case "Sino":
                return 319;
            case "PARA":
                return 320;
            case "MIENTRAS":
                return 321;
            case "FuncionesI":
                return 322;
            case "FuncionesF":
                return 323;
            case "fun":
                return 324;
            case "retorna":
                return 325;
            case "proc":
                return 326;
            case "EntidadesI":
                return 327;
            case "EntidadesF":
                return 328;
            case "ent":
                return 329;
            case "acc":
                return 330;
            case "obj":
                return 331;
            case "Main":
                return 332;
            case "PrincipalI":
                return 333;
            case "PrincipalF":
                return 334;
            case "nuevo":
                return 335;
            
        
    }   //Si no es una palabra reservada retorna 0
        return -1;
    }
    
    //Función para separar por líneas el texto a ingresar. Parámetros: texto y el separador (\n)
    public String[] separador(String texto, char separar){
    String linea = "";
    int contador = 1;
    //Recorrer el texto
    for (int i =0; i<texto.length();i++){
        //Si encuentra un salto de línea
        if(texto.charAt(i) == separar){
            contador++;
        }
    } 
    //Se da el tamaño al vector que guardará las líneas, según el contador
    String [] cadenas = new String[contador];
    contador = 0;
    
    //Recorremos el texto nuevamente
        for (int i =0; i<texto.length();i++){
        //Si no existe separación, se concatena en la variable línea
        if(texto.charAt(i) == separar){
            cadenas[contador] = linea;
            contador++;
            linea = "";
            //linea = linea + String.valueOf(texto.charAt(i));
        }else{
            //Si existe separación, se guarda la línea en el vector cadenas[]
            linea = linea + String.valueOf(texto.charAt(i));
        }
        
    }
        cadenas[contador] = linea;
            contador++;
            linea = "";
        
    
    
    //Se retorn el vector que contiene las líneas del texto ingresada
    return cadenas;
    }
    
}

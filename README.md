# 🧠 Analizador Sintáctico LL(1) - Lenguaje Propio
Este proyecto implementa un analizador sintáctico descendente predictivo (LL(1)) en Java, diseñado para verificar si una secuencia de tokens generada por un analizador léxico pertenece a un lenguaje definido por una gramática libre de contexto.

---

## 🔧 Características principales

- Utiliza una **tabla LL(1)** para aplicar reglas de producción.
- Apoyado por un **analizador léxico** que segmenta la entrada en tokens.
- Muestra el proceso paso a paso del análisis sintáctico (pila, entrada, producción).
- Implementación modular: separación clara entre léxico, sintáctico y tabla de análisis.
- Interfaz desde consola o desde interfaz gráfica (JFrame).

---

## 📁 Clases Principales del proyecto

- 📜 AnalizadorLexico.java # Clase léxica compartida desde el proyecto anterior
- 📜 PARSER.java # Lógica del análisis sintáctico
- 📜 TABLA.java # Tabla LL(1) con reglas de producción
- 📜 Token.java # Clase para representar los tokens
- 📜 Sin.java # Clase para representar cada paso del análisis

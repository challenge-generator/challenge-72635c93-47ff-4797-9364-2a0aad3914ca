# Fortalecimiento de habilidades de desarrollo backend con asistentes AI

En un entorno de desarrollo backend para un sistema de gestión de préstamos en una fintech, el desarrollador debe integrar y utilizar eficientemente asistentes de inteligencia artificial (como Amazon Q Dev, Kiro, Github Copilot) para mejorar la productividad y calidad del código. El sistema gestiona solicitudes de préstamos, valida información de los solicitantes y coordina con múltiples servicios internos y externos (buró de crédito, motor de riesgo, sistema de pagos). El desarrollador enfrentará escenarios realistas donde deberá tomar decisiones sobre cuándo y cómo utilizar estos asistentes para resolver problemas complejos y optimizar el flujo de trabajo.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Desarrollo |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 10 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Integración inicial de asistentes AI

**Objetivo:** Configurar y utilizar asistentes AI para tareas básicas de desarrollo.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Identificar tareas repetitivas o complejas en el flujo de desarrollo backend.
- Seleccionar un asistente AI adecuado para automatizar o asistir en estas tareas.
- Documentar el proceso de integración y los beneficios observados.

**Entregable:** Documentación de la integración de asistentes AI en el flujo de desarrollo, incluyendo ejemplos de tareas automatizadas.

<details>
<summary>Pistas de conocimiento</summary>

- Beneficios de utilizar asistentes AI en desarrollo.
- Escenarios comunes donde los asistentes AI pueden mejorar la eficiencia.

</details>

### Fase 2: Optimización del flujo de trabajo con asistentes AI

**Objetivo:** Utilizar asistentes AI para optimizar el flujo de trabajo y mejorar la calidad del código.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Identificar áreas de mejora en el flujo de trabajo actual.
- Utilizar asistentes AI para automatizar pruebas, refactorizar código y detectar errores.
- Documentar los cambios implementados y los resultados obtenidos.

**Entregable:** Documentación de los cambios implementados en el flujo de trabajo, incluyendo ejemplos de tareas automatizadas y mejoras en la calidad del código.

<details>
<summary>Pistas de conocimiento</summary>

- Estrategias para optimizar el flujo de trabajo con asistentes AI.
- Métodos para mejorar la calidad del código utilizando asistentes AI.

</details>

### Fase 3: Evaluación y toma de decisiones con asistentes AI

**Objetivo:** Evaluar el impacto de los asistentes AI en el desarrollo y tomar decisiones informadas sobre su uso continuo.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Evaluar los resultados obtenidos en las fases anteriores.
- Tomar decisiones informadas sobre el uso continuo de asistentes AI en el desarrollo backend.
- Documentar las conclusiones y recomendaciones para el equipo de desarrollo.

**Entregable:** Documentación de la evaluación de los resultados, conclusiones y recomendaciones para el uso continuo de asistentes AI en el desarrollo backend.

<details>
<summary>Pistas de conocimiento</summary>

- Métodos para evaluar el impacto de los asistentes AI en el desarrollo.
- Estrategias para tomar decisiones informadas sobre el uso de asistentes AI.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son los asistentes de inteligencia artificial y cómo pueden beneficiar el desarrollo backend?
- **paraQueSirve**: ¿Para qué sirven los asistentes AI en el contexto de desarrollo backend?
- **comoSeUsa**: ¿Cómo se utilizan los asistentes AI para mejorar la eficiencia y calidad del código en desarrollo backend?
- **erroresComunes**: ¿Cuáles son los errores comunes al integrar y utilizar asistentes AI en el desarrollo backend?
- **queDecisionesImplica**: ¿Qué decisiones implica la evaluación y uso continuo de asistentes AI en el desarrollo backend?

## Criterios de Evaluacion

- Integración efectiva de asistentes AI en el flujo de desarrollo.
- Mejora demostrable en la eficiencia y calidad del código.
- Evaluación y toma de decisiones informadas sobre el uso continuo de asistentes AI.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*

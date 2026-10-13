# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/fintech/loans/application/usecase/SubmitLoanApplication.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/loans/application/usecase/SubmitLoanApplication.java` — `Applicant.id`: Se invoca `id` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/application/usecase/SubmitLoanApplication.java` — `Applicant.employmentStatus`: Se invoca `employmentStatus` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/application/usecase/SubmitLoanApplication.java` — `Applicant.monthlyIncome`: Se invoca `monthlyIncome` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java` — `Applicant.id`: Se invoca `id` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java` — `CreditBureauConfig.getResponseDelay`: Se invoca `getResponseDelay` sobre `CreditBureauConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java` — `CreditBureauConfig.isSimulateFailure`: Se invoca `isSimulateFailure` sobre `CreditBureauConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java` — `Applicant.identificationNumber`: Se invoca `identificationNumber` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java` — `Applicant.employmentStatus`: Se invoca `employmentStatus` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java` — `Applicant.age`: Se invoca `age` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java` — `Applicant.monthlyIncome`: Se invoca `monthlyIncome` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `Applicant.email`: Se invoca `email` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `Applicant.identificationNumber`: Se invoca `identificationNumber` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `Applicant.employmentStatus`: Se invoca `employmentStatus` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskRequest.identification`: Se invoca `identification` sobre `RiskRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskRequest.email`: Se invoca `email` sobre `RiskRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskRequest.amount`: Se invoca `amount` sobre `RiskRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskRequest.employmentStatus`: Se invoca `employmentStatus` sobre `RiskRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskResponse.getOrDefault`: Se invoca `getOrDefault` sobre `RiskResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskResponse.getLevel`: Se invoca `getLevel` sobre `RiskResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskResponse.isApproved`: Se invoca `isApproved` sobre `RiskResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskResponse.getScore`: Se invoca `getScore` sobre `RiskResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskResponse.getMessage`: Se invoca `getMessage` sobre `RiskResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `Applicant.email`: Se invoca `email` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `Applicant.identificationNumber`: Se invoca `identificationNumber` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `Applicant.phoneNumber`: Se invoca `phoneNumber` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentResponse.get`: Se invoca `get` sobre `PaymentResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentRequest.loanId`: Se invoca `loanId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentRequest.identification`: Se invoca `identification` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentRequest.email`: Se invoca `email` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentRequest.phone`: Se invoca `phone` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentRequest.amount`: Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentRequest.scheduledDate`: Se invoca `scheduledDate` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentResponse.getOrDefault`: Se invoca `getOrDefault` sobre `PaymentResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentRequest.borrowerId`: Se invoca `borrowerId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentRequest.paymentDate`: Se invoca `paymentDate` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentResponse.isSuccess`: Se invoca `isSuccess` sobre `PaymentResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentResponse.transactionId`: Se invoca `transactionId` sobre `PaymentResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentResponse.message`: Se invoca `message` sobre `PaymentResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java` — `ErrorResponse.setTimestamp`: Se invoca `setTimestamp` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java` — `ErrorResponse.setStatus`: Se invoca `setStatus` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java` — `ErrorResponse.setError`: Se invoca `setError` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java` — `ErrorResponse.setMessage`: Se invoca `setMessage` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java` — `ErrorResponse.setFieldErrors`: Se invoca `setFieldErrors` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java` — `ErrorResponse.setPath`: Se invoca `setPath` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplicationRequest.applicantId`: Se invoca `applicantId` sobre `LoanApplicationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplicationRequest.fullName`: Se invoca `fullName` sobre `LoanApplicationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplicationRequest.email`: Se invoca `email` sobre `LoanApplicationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplicationRequest.annualIncome`: Se invoca `annualIncome` sobre `LoanApplicationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplicationRequest.employmentStatus`: Se invoca `employmentStatus` sobre `LoanApplicationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplicationRequest.requestedAmount`: Se invoca `requestedAmount` sobre `LoanApplicationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplicationRequest.termMonths`: Se invoca `termMonths` sobre `LoanApplicationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplicationRequest.purpose`: Se invoca `purpose` sobre `LoanApplicationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanRepository.findAll`: Se invoca `findAll` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplication.getApplicantFullName`: Se invoca `getApplicantFullName` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplication.getApplicantEmail`: Se invoca `getApplicantEmail` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplication.getAnnualIncome`: Se invoca `getAnnualIncome` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplication.getEmploymentStatus`: Se invoca `getEmploymentStatus` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplication.getRequestedAmount`: Se invoca `getRequestedAmount` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplication.getRiskLevel`: Se invoca `getRiskLevel` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `RiskEngineService.evaluateRisk`: Se invoca `evaluateRisk` sobre `RiskEngineService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `LoanApplication.getRiskLevel`: Se invoca `getRiskLevel` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `PaymentService.processPayment`: Se invoca `processPayment` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `Applicant.id`: Se invoca `id` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `Applicant.fullName`: Se invoca `fullName` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `LoanApplication.getApplicantFullName`: Se invoca `getApplicantFullName` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `Applicant.email`: Se invoca `email` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `LoanApplication.getApplicantEmail`: Se invoca `getApplicantEmail` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `LoanApplication.getRequestedAmount`: Se invoca `getRequestedAmount` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `docs/evaluacion-resultados.md` — `Applicant.id`: Se invoca `id` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `docs/evaluacion-resultados.md` — `Applicant.ssn`: Se invoca `ssn` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced

### Brecha de conocimiento
Ha trabajado con algún asistente AI en desarrollo (Amazon Q Dev, Kiro, Github Copilot) y lo utiliza en su día a día como desarrollador. Necesita fortalecer su práctica y dominio de estas herramientas en contextos reales de desarrollo backend.

### Misión / candidato
Candidato con experiencia como developer backend en Java, en equipo distribuido.

### Reto
- Tema: Desarrollo
- Seniority: advanced-l2
- Tipo: practical
- Título: Fortalecimiento de habilidades de desarrollo backend con asistentes AI
- Tiempo estimado: 10 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Integración inicial de asistentes AI — objetivo: Configurar y utilizar asistentes AI para tareas básicas de desarrollo. — entregable (NO resolver): Documentación de la integración de asistentes AI en el flujo de desarrollo, incluyendo ejemplos de tareas automatizadas.
- Fase 2: Optimización del flujo de trabajo con asistentes AI — objetivo: Utilizar asistentes AI para optimizar el flujo de trabajo y mejorar la calidad del código. — entregable (NO resolver): Documentación de los cambios implementados en el flujo de trabajo, incluyendo ejemplos de tareas automatizadas y mejoras en la calidad del código.
- Fase 3: Evaluación y toma de decisiones con asistentes AI — objetivo: Evaluar el impacto de los asistentes AI en el desarrollo y tomar decisiones informadas sobre su uso continuo. — entregable (NO resolver): Documentación de la evaluación de los resultados, conclusiones y recomendaciones para el uso continuo de asistentes AI en el desarrollo backend.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.fintech</groupId>
    <artifactId>loans</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>loans</name>
    <description>Sistema de gestión de préstamos para fintech</description>

    <properties>
        <java.version>21</java.version>
        <springdoc-openapi.version>2.5.0</springdoc-openapi.version>
        <lombok.version>1.18.30</lombok.version>
        <postgresql.version>42.7.3</postgresql.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>

        <!-- PostgreSQL -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>${postgresql.version}</version>
            <scope>runtime</scope>
        </dependency>

        <!-- OpenAPI/Swagger -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc-openapi.version}</version>
        </dependency>
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>2.2.22</version>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.12.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>5.12.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/fintech/loans/LoansApplication.java ===
package com.fintech.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class LoansApplication {
    private final Environment environment;

    public LoansApplication(Environment environment) {
        this.environment = environment;
        validateActiveProfiles();
    }

    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        System.out.println("Application started with profiles: " + Arrays.toString(environment.getActiveProfiles()));
    }

    private void validateActiveProfiles() {
        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length == 0) {
            throw new IllegalStateException("No active Spring profiles set. Please configure at least one profile (dev, prod, test)");
        }

        boolean hasDevOrProd = Arrays.stream(activeProfiles)
                .anyMatch(profile -> profile.equals("dev") || profile.equals("prod"));

        if (!hasDevOrProd) {
            throw new IllegalStateException("At least one of 'dev' or 'prod' profile must be active");
        }
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: loans-service
  profiles:
    active: dev
  datasource:
    url: jdbc:postgresql://localhost:5432/loans_db
    username: loans_user
    password: loans_pass
    driver-class-name: org.postgresql.Driver
    hikari:
      connection-timeout: 20000
      maximum-pool-size: 10
      pool-name: loans-pool
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        jdbc:
          batch_size: 20
        order_inserts: true
        order_updates: true
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher

server:
  port: 8080
  servlet:
    context-path: /api/loans
  error:
    include-message: always
    include-binding-errors: always
    include-stacktrace: on_param

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  info:
    env:
      enabled: true

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    disable-swagger-default-url: true
    operations-sorter: alpha
    tags-sorter: alpha
    doc-expansion: none

logging:
  level:
    root: INFO
    com.fintech.loans: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
    org.springframework.web: INFO
    org.springframework.security: INFO
    org.springframework.boot: INFO

// === ARCHIVO: src/main/java/com/fintech/loans/domain/model/Applicant.java ===
package com.fintech.loans.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Representa a un solicitante de préstamo en el sistema.
 * Contiene la información personal y financiera necesaria para evaluar su elegibilidad.
 */
public record Applicant(
        @NotNull
        UUID id,

        @NotBlank(message = "El nombre completo es obligatorio")
        @Size(max = 100, message = "El nombre completo no puede exceder los 100 caracteres")
        String fullName,

        @NotBlank(message = "El número de identificación es obligatorio")
        @Size(max = 20, message = "El número de identificación no puede exceder los 20 caracteres")
        String identificationNumber,

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        LocalDate birthDate,

        @NotBlank(message = "La dirección es obligatoria")
        @Size(max = 200, message = "La dirección no puede exceder los 200 caracteres")
        String address,

        @NotBlank(message = "El correo electrónico es obligatorio")
        @Size(max = 100, message = "El correo electrónico no puede exceder los 100 caracteres")
        String email,

        @NotBlank(message = "El número de teléfono es obligatorio")
        @Size(max = 20, message = "El número de teléfono no puede exceder los 20 caracteres")
        String phoneNumber,

        @NotNull(message = "El ingreso mensual es obligatorio")
        @Positive(message = "El ingreso mensual debe ser un valor positivo")
        BigDecimal monthlyIncome,

        @NotNull(message = "El puntaje de crédito es obligatorio")
        @Positive(message = "El puntaje de crédito debe ser un valor positivo")
        Integer creditScore,

        @NotBlank(message = "La ocupación es obligatoria")
        @Size(max = 50, message = "La ocupación no puede exceder los 50 caracteres")
        String occupation,

        @NotNull(message = "El estado de empleo es obligatorio")
        EmploymentStatus employmentStatus) {

    /**
     * Enumeración que representa el estado de empleo del solicitante.
     */
    public enum EmploymentStatus {
        EMPLOYED,
        SELF_EMPLOYED,
        UNEMPLOYED,
        RETIRED
    }

    /**
     * Valida que la información del solicitante sea consistente.
     * @throws IllegalArgumentException si alguna validación falla.
     */
    public void validate() {
        if (birthDate != null && birthDate.isAfter(LocalDate.now().minusYears(18))) {
            throw new IllegalArgumentException("El solicitante debe ser mayor de 18 años");
        }
        if (creditScore != null && (creditScore < 300 || creditScore > 850)) {
            throw new IllegalArgumentException("El puntaje de crédito debe estar entre 300 y 850");
        }
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/domain/port/LoanRepository.java ===
package com.fintech.loans.domain.port;

import com.fintech.loans.domain.model.LoanApplication;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto que define las operaciones de persistencia para solicitudes de préstamo.
 * Las implementaciones concretas se proporcionan en la capa de infraestructura.
 */
public interface LoanRepository {
    /**
     * Guarda una solicitud de préstamo en el repositorio.
     * @param loanApplication la solicitud de préstamo a guardar.
     * @return la solicitud de préstamo guardada.
     */
    LoanApplication save(LoanApplication loanApplication);

    /**
     * Busca una solicitud de préstamo por su identificador.
     * @param id el identificador de la solicitud de préstamo.
     * @return un Optional que contiene la solicitud de préstamo si existe, o vacío si no.
     */
    Optional<LoanApplication> findById(UUID id);

    /**
     * Busca todas las solicitudes de préstamo de un solicitante específico.
     * @param applicantId el identificador del solicitante.
     * @return una lista de solicitudes de préstamo asociadas al solicitante.
     */
    List<LoanApplication> findByApplicantId(UUID applicantId);

    /**
     * Busca todas las solicitudes de préstamo con un estado específico.
     * @param status el estado de las solicitudes de préstamo.
     * @return una lista de solicitudes de préstamo con el estado especificado.
     */
    List<LoanApplication> findByStatus(String status);

    /**
     * Elimina una solicitud de préstamo del repositorio.
     * @param id el identificador de la solicitud de préstamo a eliminar.
     */
    void deleteById(UUID id);
}

// === ARCHIVO: src/main/java/com/fintech/loans/domain/port/CreditBureauService.java ===
package com.fintech.loans.domain.port;

import com.fintech.loans.domain.model.Applicant;
import java.util.concurrent.CompletableFuture;

/**
 * Puerto que define la integración con un servicio externo de buró de crédito.
 * Las implementaciones concretas se proporcionan en la capa de infraestructura.
 */
public interface CreditBureauService {
    /**
     * Obtiene el puntaje de crédito de un solicitante desde el buró de crédito.
     * @param applicant la información del solicitante.
     * @return un CompletableFuture que resuelve con el puntaje de crédito del solicitante.
     * @throws CreditBureauException si ocurre un error al consultar el buró de crédito.
     */
    CompletableFuture<Integer> getCreditScore(Applicant applicant) throws CreditBureauException;

    /**
     * Verifica si el solicitante tiene historial crediticio en el buró.
     * @param applicant la información del solicitante.
     * @return un CompletableFuture que resuelve con true si el solicitante tiene historial, false si no.
     * @throws CreditBureauException si ocurre un error al consultar el buró de crédito.
     */
    CompletableFuture<Boolean> hasCreditHistory(Applicant applicant) throws CreditBureauException;

    /**
     * Excepción personalizada para errores en la integración con el buró de crédito.
     */
    class CreditBureauException extends Exception {
        public CreditBureauException(String message) {
            super(message);
        }

        public CreditBureauException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/domain/model/LoanApplication.java ===
package com.fintech.loans.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "loan_applications", indexes = {
    @Index(name = "idx_applicant_id", columnList = "applicant_id"),
    @Index(name = "idx_status", columnList = "status"),
    @Index(name = "idx_created_at", columnList = "created_at")
})
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "applicant_id", nullable = false)
    private UUID applicantId;

    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    @NotNull(message = "El monto del préstamo es obligatorio")
    @DecimalMin(value = "1000.00", message = "El monto mínimo es 1000")
    @DecimalMax(value = "500000.00", message = "El monto máximo es 500000")
    private BigDecimal amount;

    @Column(name = "term_months", nullable = false)
    @NotNull(message = "El plazo en meses es obligatorio")
    @Min(value = 6, message = "El plazo mínimo es 6 meses")
    @Max(value = 120, message = "El plazo máximo es 120 meses")
    private Integer termMonths;

    @Column(name = "interest_rate", nullable = false, precision = 5, scale = 4)
    @NotNull(message = "La tasa de interés es obligatoria")
    private BigDecimal interestRate;

    @Column(name = "monthly_payment", precision = 19, scale = 4)
    private BigDecimal monthlyPayment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private LoanStatus status;

    @Column(name = "purpose", length = 500)
    private String purpose;

    @Column(name = "credit_score")
    private Integer creditScore;

    @Column(name = "risk_score")
    private Integer riskScore;

    @Column(name = "rejection_reason", length = 1000)
    private String rejectionReason;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Version
    @Column(name = "version")
    private Long version;

    public enum LoanStatus {
        SUBMITTED,
        CREDIT_CHECK,
        RISK_ASSESSMENT,
        APPROVED,
        REJECTED,
        DISBURSED,
        CANCELLED
    }

    public LoanApplication() {
    }

    public LoanApplication(UUID applicantId, BigDecimal amount, Integer termMonths, 
                           BigDecimal interestRate, String purpose) {
        this.applicantId = applicantId;
        this.amount = amount;
        this.termMonths = termMonths;
        this.interestRate = interestRate;
        this.purpose = purpose;
        this.status = LoanStatus.SUBMITTED;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (updatedAt == null) updatedAt = LocalDateTime.now();
        if (status == null) status = LoanStatus.SUBMITTED;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void calculateMonthlyPayment() {
        if (amount != null && termMonths != null && interestRate != null) {
            BigDecimal monthlyRate = interestRate.divide(BigDecimal.valueOf(12), 8, java.math.RoundingMode.HALF_UP);
            BigDecimal factor = monthlyRate.add(BigDecimal.ONE).pow(termMonths);
            BigDecimal monthly = amount.multiply(monthlyRate).multiply(factor)
                    .divide(factor.subtract(BigDecimal.ONE), 4, java.math.RoundingMode.HALF_UP);
            this.monthlyPayment = monthly;
        }
    }

    public void approve() {
        this.status = LoanStatus.APPROVED;
        this.updatedAt = LocalDateTime.now();
    }

    public void reject(String reason) {
        this.status = LoanStatus.REJECTED;
        this.rejectionReason = reason;
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsCreditCheck() {
        this.status = LoanStatus.CREDIT_CHECK;
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsRiskAssessment() {
        this.status = LoanStatus.RISK_ASSESSMENT;
        this.updatedAt = LocalDateTime.now();
    }

    public void disburse() {
        this.status = LoanStatus.DISBURSED;
        this.updatedAt = LocalDateTime.now();
    }

    public void cancel() {
        this.status = LoanStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isApproved() {
        return status == LoanStatus.APPROVED;
    }

    public boolean isRejected() {
        return status == LoanStatus.REJECTED;
    }

    public boolean canTransitionTo(LoanStatus newStatus) {
        return switch (this.status) {
            case SUBMITTED -> newStatus == LoanStatus.CREDIT_CHECK;
            case CREDIT_CHECK -> newStatus == LoanStatus.RISK_ASSESSMENT || newStatus == LoanStatus.REJECTED;
            case RISK_ASSESSMENT -> newStatus == LoanStatus.APPROVED || newStatus == LoanStatus.REJECTED;
            case APPROVED -> newStatus == LoanStatus.DISBURSED || newStatus == LoanStatus.CANCELLED;
            default -> false;
        };
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(UUID applicantId) {
        this.applicantId = applicantId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public Integer getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/application/usecase/SubmitLoanApplication.java ===
package com.fintech.loans.application.usecase;

import com.fintech.loans.domain.model.Applicant;
import com.fintech.loans.domain.model.LoanApplication;
import com.fintech.loans.domain.port.CreditBureauService;
import com.fintech.loans.domain.port.LoanRepository;
import com.fintech.loans.domain.port.RiskEngineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class SubmitLoanApplication {

    private static final Logger logger = LoggerFactory.getLogger(SubmitLoanApplication.class);
    private static final int MIN_CREDIT_SCORE = 600;
    private static final int MAX_RISK_SCORE = 70;
    private static final BigDecimal MIN_INCOME_RATIO = BigDecimal.valueOf(0.4);

    private final LoanRepository loanRepository;
    private final CreditBureauService creditBureauService;
    private final RiskEngineService riskEngineService;

    @Autowired
    public SubmitLoanApplication(LoanRepository loanRepository,
                                  CreditBureauService creditBureauService,
                                  RiskEngineService riskEngineService) {
        this.loanRepository = loanRepository;
        this.creditBureauService = creditBureauService;
        this.riskEngineService = riskEngineService;
    }

    @Transactional
    public LoanApplication execute(Applicant applicant, BigDecimal amount, 
                                   Integer termMonths, String purpose) {
        logger.info("Iniciando procesamiento de solicitud de préstamo para applicant: {}", applicant);

        validarSolicitud(applicant, amount, termMonths);

        LoanApplication loanApplication = new LoanApplication(
            applicant.id(),
            amount,
            termMonths,
            calcularTasaInteres(applicant),
            purpose
        );

        loanApplication = loanRepository.save(loanApplication);
        logger.info("Solicitud {} guardada con estado: {}", loanApplication.getId(), loanApplication.getStatus());

        loanApplication.markAsCreditCheck();
        loanApplication = loanRepository.save(loanApplication);

        Integer creditScore = obtenerCreditScore(applicant).join();
        loanApplication.setCreditScore(creditScore);
        logger.info("Credit score obtenido: {}", creditScore);

        if (!evaluarCreditScore(creditScore)) {
            String reason = "Credit score insuficiente: " + creditScore;
            logger.warn("Solicitud {} rechazada por credit score bajo: {}", loanApplication.getId(), creditScore);
            loanApplication.reject(reason);
            return loanRepository.save(loanApplication);
        }

        loanApplication.markAsRiskAssessment();
        loanApplication = loanRepository.save(loanApplication);

        Integer riskScore = evaluarRiesgo(loanApplication, applicant).join();
        loanApplication.setRiskScore(riskScore);
        logger.info("Risk score obtenido: {}", riskScore);

        if (!evaluarRiskScore(riskScore)) {
            String reason = "Risk score demasiado alto: " + riskScore;
            logger.warn("Solicitud {} rechazada por risk score alto: {}", loanApplication.getId(), riskScore);
            loanApplication.reject(reason);
            return loanRepository.save(loanApplication);
        }

        loanApplication.calculateMonthlyPayment();
        loanApplication.approve();
        logger.info("Solicitud {} aprobada con mensualidad: {}", 
                   loanApplication.getId(), loanApplication.getMonthlyPayment());

        return loanRepository.save(loanApplication);
    }

    private void validarSolicitud(Applicant applicant, BigDecimal amount, Integer termMonths) {
        if (applicant == null) {
            throw new IllegalArgumentException("El solicitante no puede ser nulo");
        }
        applicant.validate();

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del préstamo debe ser mayor a cero");
        }
        if (amount.compareTo(BigDecimal.valueOf(1000)) < 0) {
            throw new IllegalArgumentException("El monto mínimo del préstamo es 1000");
        }
        if (amount.compareTo(BigDecimal.valueOf(500000)) > 0) {
            throw new IllegalArgumentException("El monto máximo del préstamo es 500000");
        }

        if (termMonths == null || termMonths < 6 || termMonths > 120) {
            throw new IllegalArgumentException("El plazo debe estar entre 6 y 120 meses");
        }
    }

    private BigDecimal calcularTasaInteres(Applicant applicant) {
        BigDecimal baseRate = BigDecimal.valueOf(0.15);
        BigDecimal employmentAdjustment = switch (applicant.employmentStatus()) {
            case EMPLOYED -> BigDecimal.valueOf(-0.02);
            case SELF_EMPLOYED -> BigDecimal.valueOf(0.01);
            case UNEMPLOYED -> BigDecimal.valueOf(0.05);
            case RETIRED -> BigDecimal.valueOf(0.00);
        };

        BigDecimal incomeAdjustment = BigDecimal.ZERO;
        if (applicant.monthlyIncome() != null) {
            if (applicant.monthlyIncome().compareTo(BigDecimal.valueOf(10000)) > 0) {
                incomeAdjustment = BigDecimal.valueOf(-0.01);
            } else if (applicant.monthlyIncome().compareTo(BigDecimal.valueOf(3000)) < 0) {
                incomeAdjustment = BigDecimal.valueOf(0.02);
            }
        }

        return baseRate.add(employmentAdjustment).add(incomeAdjustment)
                .setScale(4, RoundingMode.HALF_UP);
    }

    private CompletableFuture<Integer> obtenerCreditScore(Applicant applicant) {
        return creditBureauService.getCreditScore(applicant)
            .exceptionally(ex -> {
                logger.error("Error al obtener credit score, usando default: {}", ex.getMessage());
                return 650;
            });
    }

    private boolean evaluarCreditScore(Integer creditScore) {
        return creditScore != null && creditScore >= MIN_CREDIT_SCORE;
    }

    private CompletableFuture<Integer> evaluarRiesgo(LoanApplication loanApplication, Applicant applicant) {
        return riskEngineService.assessRisk(loanApplication, applicant)
            .exceptionally(ex -> {
                logger.error("Error al evaluar riesgo, usando default: {}", ex.getMessage());
                return 50;
            });
    }

    private boolean evaluarRiskScore(Integer riskScore) {
        return riskScore != null && riskScore <= MAX_RISK_SCORE;
    }

    public LoanApplication findById(UUID id) {
        return loanRepository.findById(id).orElse(null);
    }

    public LoanApplication findByApplicantId(UUID applicantId) {
        return loanRepository.findByApplicantId(applicantId).stream().findFirst().orElse(null);
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/domain/port/RiskEngineService.java ===
package com.fintech.loans.domain.port;

import com.fintech.loans.domain.model.Applicant;
import com.fintech.loans.domain.model.LoanApplication;

import java.util.concurrent.CompletableFuture;

public interface RiskEngineService {

    CompletableFuture<Integer> assessRisk(LoanApplication loanApplication, Applicant applicant) 
            throws RiskEngineException;

    CompletableFuture<RiskCategory> categorizeRisk(LoanApplication loanApplication, Applicant applicant)
            throws RiskEngineException;

    class RiskEngineException extends Exception {
        private final String errorCode;
        private final String details;

        public RiskEngineException(String message) {
            super(message);
            this.errorCode = "RISK_ENGINE_ERROR";
            this.details = null;
        }

        public RiskEngineException(String message, String errorCode) {
            super(message);
            this.errorCode = errorCode;
            this.details = null;
        }

        public RiskEngineException(String message, String errorCode, String details) {
            super(message);
            this.errorCode = errorCode;
            this.details = details;
        }

        public RiskEngineException(String message, Throwable cause) {
            super(message, cause);
            this.errorCode = "RISK_ENGINE_ERROR";
            this.details = null;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getDetails() {
            return details;
        }
    }

    enum RiskCategory {
        LOW(0, 30, "Riesgo bajo - Aprobación automática"),
        MEDIUM(31, 60, "Riesgo medio - Requiere revisión manual"),
        HIGH(61, 80, "Riesgo alto - Requiere aprobación especial"),
        VERY_HIGH(81, 100, "Riesgo muy alto - Rechazo automático");

        private final int minScore;
        private final int maxScore;
        private final String description;

        RiskCategory(int minScore, int maxScore, String description) {
            this.minScore = minScore;
            this.maxScore = maxScore;
            this.description = description;
        }

        public int getMinScore() {
            return minScore;
        }

        public int getMaxScore() {
            return maxScore;
        }

        public String getDescription() {
            return description;
        }

        public static RiskCategory fromScore(Integer score) {
            if (score == null) {
                return MEDIUM;
            }
            for (RiskCategory category : values()) {
                if (score >= category.minScore && score <= category.maxScore) {
                    return category;
                }
            }
            return VERY_HIGH;
        }
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/domain/port/PaymentService.java ===
package com.fintech.loans.domain.port;

import com.fintech.loans.domain.model.LoanApplication;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

/**
 * Puerto para la integración con el sistema de pagos.
 * Define el contrato que debe implementar cualquier adaptador de pagos.
 */
public interface PaymentService {

    /**
     * Procesa el desembolso de un préstamo al solicitante.
     *
     * @param loanApplication la solicitud de préstamo aprobada
     * @param disbursementDate fecha programada para el desembolso
     * @return un CompletableFuture con el identificador de transacción del pago
     * @throws PaymentException si ocurre un error durante el procesamiento
     */
    CompletableFuture<String> disburseLoan(LoanApplication loanApplication, LocalDate disbursementDate)
            throws PaymentException;

    /**
     * Procesa el cobro de una cuota del préstamo.
     *
     * @param loanApplication la solicitud de préstamo asociada
     * @param amount cantidad a cobrar
     * @param dueDate fecha de vencimiento de la cuota
     * @return un CompletableFuture con el identificador de transacción del cobro
     * @throws PaymentException si ocurre un error durante el procesamiento
     */
    CompletableFuture<String> collectPayment(LoanApplication loanApplication, BigDecimal amount, LocalDate dueDate)
            throws PaymentException;

    /**
     * Verifica el estado de una transacción de pago existente.
     *
     * @param transactionId identificador de la transacción
     * @return un CompletableFuture con el estado de la transacción
     * @throws PaymentException si ocurre un error durante la verificación
     */
    CompletableFuture<PaymentStatus> getPaymentStatus(String transactionId) throws PaymentException;

    /**
     * Reembolsa un pago previously realizado.
     *
     * @param transactionId identificador de la transacción a reembolsar
     * @param reason motivo del reembolso
     * @return un CompletableFuture con el identificador de la transacción de reembolso
     * @throws PaymentException si ocurre un error durante el procesamiento
     */
    CompletableFuture<String> refundPayment(String transactionId, String reason) throws PaymentException;

    /**
     * Excepción personalizada para errores del servicio de pagos.
     */
    class PaymentException extends Exception {
        private final String transactionId;
        private final PaymentErrorCode errorCode;

        public PaymentException(String message, String transactionId, PaymentErrorCode errorCode) {
            super(message);
            this.transactionId = transactionId;
            this.errorCode = errorCode;
        }

        public PaymentException(String message, Throwable cause, String transactionId, PaymentErrorCode errorCode) {
            super(message, cause);
            this.transactionId = transactionId;
            this.errorCode = errorCode;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public PaymentErrorCode getErrorCode() {
            return errorCode;
        }
    }

    /**
     * Estados posibles de una transacción de pago.
     */
    enum PaymentStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED,
        CANCELLED,
        REFUNDED
    }

    /**
     * Códigos de error para el servicio de pagos.
     */
    enum PaymentErrorCode {
        INSUFFICIENT_FUNDS,
        ACCOUNT_NOT_FOUND,
        INVALID_ACCOUNT,
        TRANSACTION_TIMEOUT,
        DUPLICATE_TRANSACTION,
        SYSTEM_ERROR,
        VALIDATION_ERROR
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/infrastructure/adapter/LoanJpaRepository.java ===
package com.fintech.loans.infrastructure.adapter;

import com.fintech.loans.domain.model.LoanApplication;
import com.fintech.loans.domain.port.LoanRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Adaptador de persistencia que implementa el puerto LoanRepository
 * utilizando Spring Data JPA para interagir con la base de datos PostgreSQL.
 */
@Repository
public interface LoanJpaRepository extends JpaRepository<LoanApplication, UUID>, LoanRepository {

    @Override
    default LoanApplication save(LoanApplication loanApplication) {
        if (loanApplication.getId() == null) {
            return this.save(loanApplication);
        }
        return this.save(loanApplication);
    }

    @Override
    Optional<LoanApplication> findById(UUID id);

    @Override
    List<LoanApplication> findByApplicantId(UUID applicantId);

    @Override
    List<LoanApplication> findByStatus(String status);

    @Override
    void deleteById(UUID id);

    /**
     * Busca solicitudes de préstamo por rango de fecha de creación.
     */
    @Query("SELECT l FROM LoanApplication l WHERE l.createdAt BETWEEN :startDate AND :endDate")
    List<LoanApplication> findByCreatedAtBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    /**
     * Busca solicitudes de préstamo por identificador de solicitante y estado.
     */
    @Query("SELECT l FROM LoanApplication l WHERE l.applicant.id = :applicantId AND l.status = :status")
    List<LoanApplication> findByApplicantIdAndStatus(
            @Param("applicantId") UUID applicantId,
            @Param("status") String status);

    /**
     * Cuenta el número de solicitudes de préstamo por estado.
     */
    @Query("SELECT COUNT(l) FROM LoanApplication l WHERE l.status = :status")
    long countByStatus(@Param("status") String status);

    /**
     * Busca solicitudes de préstamo con monto mayor al especificado.
     */
    @Query("SELECT l FROM LoanApplication l WHERE l.requestedAmount > :amount")
    List<LoanApplication> findByRequestedAmountGreaterThan(@Param("amount") java.math.BigDecimal amount);

    /**
     * Busca solicitudes de préstamo que están pendientes de revisión por más de
     * un número de días especificado.
     */
    @Query("SELECT l FROM LoanApplication l WHERE l.status = 'PENDING' AND l.createdAt < :thresholdDate")
    List<LoanApplication> findPendingApplicationsOlderThan(@Param("thresholdDate") LocalDateTime thresholdDate);
}

// === ARCHIVO: src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java ===
package com.fintech.loans.infrastructure.adapter;



import com.fintech.loans.domain.model.EmploymentStatus;
import com.fintech.loans.domain.port.CreditBureauException;
import com.fintech.loans.domain.model.Applicant;
import com.fintech.loans.domain.port.CreditBureauService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Adaptador que implementa la integración con el servicio externo de buró de crédito.
 * Provee métodos para obtener el puntaje crediticio y verificar el historial crediticio
 * de los solicitantes mediante llamadas al sistema externo del buró de crédito.
 */
@Service
public class CreditBureauAdapter implements CreditBureauService {

    private static final Logger logger = LoggerFactory.getLogger(CreditBureauAdapter.class);
    private final ExecutorService executorService;
    private final CreditBureauConfig config;

    public CreditBureauAdapter(CreditBureauConfig config) {
        this.executorService = Executors.newCachedThreadPool();
        this.config = config;
    }

    @Override
    public CompletableFuture<Integer> getCreditScore(Applicant applicant) throws CreditBureauException {
        logger.info("Consultando puntaje crediticio para solicitante: {}", applicant.id());

        return CompletableFuture.supplyAsync(() -> {
            try {
                validateApplicant(applicant);
                Thread.sleep(config.getResponseDelay());

                if (config.isSimulateFailure() && Math.random() < 0.1) {
                    throw new CreditBureauException("Error de conexión con el buró de crédito",
                            new RuntimeException("Connection timeout"));
                }

                int score = calculateCreditScore(applicant);
                logger.info("Puntaje crediticio obtenido para {}: {}", applicant.id(), score);
                return score;

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new CreditBureauException("La consulta fue interrumpida", e);
            } catch (CreditBureauException e) {
                throw e;
            } catch (Exception e) {
                throw new CreditBureauException("Error al obtener puntaje crediticio", e);
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<Boolean> hasCreditHistory(Applicant applicant) throws CreditBureauException {
        logger.info("Verificando historial crediticio para solicitante: {}", applicant.id());

        return CompletableFuture.supplyAsync(() -> {
            try {
                validateApplicant(applicant);
                Thread.sleep(config.getResponseDelay() / 2);

                boolean hasHistory = calculateCreditHistory(applicant);
                logger.info("Historial crediticio para {}: {}", applicant.id(), hasHistory ? "existe" : "no existe");
                return hasHistory;

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new CreditBureauException("La consulta fue interrumpida", e);
            } catch (CreditBureauException e) {
                throw e;
            } catch (Exception e) {
                throw new CreditBureauException("Error al verificar historial crediticio", e);
            }
        }, executorService);
    }

    private void validateApplicant(Applicant applicant) throws CreditBureauException {
        if (applicant == null) {
            throw new CreditBureauException("El solicitante no puede ser null");
        }
        if (applicant.id() == null) {
            throw new CreditBureauException("El identificador del solicitante no puede ser null");
        }
        if (applicant.identificationNumber() == null || applicant.identificationNumber().isBlank()) {
            throw new CreditBureauException("El número de identificación no puede estar vacío");
        }
    }

    private int calculateCreditScore(Applicant applicant) {
        int baseScore = 650;

        if (applicant.employmentStatus() == Applicant.EmploymentStatus.EMPLOYED) {
            baseScore += 50;
        } else if (applicant.employmentStatus() == Applicant.EmploymentStatus.SELF_EMPLOYED) {
            baseScore += 30;
        }

        int age = applicant.age();
        if (age >= 25 && age <= 55) {
            baseScore += 20;
        } else if (age < 25) {
            baseScore -= 30;
        }

        if (applicant.monthlyIncome() != null) {
            if (applicant.monthlyIncome().doubleValue() > 5000) {
                baseScore += 50;
            } else if (applicant.monthlyIncome().doubleValue() > 3000) {
                baseScore += 25;
            }
        }

        return Math.max(300, Math.min(850, baseScore));
    }

    private boolean calculateCreditHistory(Applicant applicant) {
        return applicant.identificationNumber() != null
                && !applicant.identificationNumber().isEmpty()
                && applicant.identificationNumber().length() >= 8;
    }

    /**
     * Configuración del adaptador de buró de crédito.
     */
    public static class CreditBureauConfig {
        private final String endpoint;
        private final String apiKey;
        private final int responseDelay;
        private final boolean simulateFailure;

        public CreditBureauConfig(String endpoint, String apiKey, int responseDelay, boolean simulateFailure) {
            this.endpoint = endpoint;
            this.apiKey = apiKey;
            this.responseDelay = responseDelay;
            this.simulateFailure = simulateFailure;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public String getApiKey() {
            return apiKey;
        }

        public int getResponseDelay() {
            return responseDelay;
        }

        public boolean isSimulateFailure() {
            return simulateFailure;
        }
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java ===
package com.fintech.loans.infrastructure.adapter;

import com.fintech.loans.domain.model.Applicant;
import com.fintech.loans.domain.port.RiskEngineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class RiskEngineAdapter implements RiskEngineService {

    private static final Logger log = LoggerFactory.getLogger(RiskEngineAdapter.class);

    private final RestTemplate restTemplate;
    private final String riskEngineUrl;
    private final String riskEngineApiKey;

    public RiskEngineAdapter(
            @Value("${external-services.risk-engine.url:http://localhost:8081/api/risk}") String riskEngineUrl,
            @Value("${external-services.risk-engine.api-key:demo-key}") String riskEngineApiKey) {
        this.restTemplate = new RestTemplate();
        this.riskEngineUrl = riskEngineUrl;
        this.riskEngineApiKey = riskEngineApiKey;
    }

    @Override
    public CompletableFuture<RiskEvaluation> evaluateRisk(Applicant applicant, BigDecimal requestedAmount) {
        log.info("Iniciando evaluación de riesgo para solicitante: {} con monto: {}", 
                applicant.email(), requestedAmount);

        return CompletableFuture.supplyAsync(() -> {
            try {
                RiskRequest request = new RiskRequest(
                        applicant.identificationNumber(),
                        applicant.email(),
                        requestedAmount,
                        applicant.employmentStatus().name()
                );

                RiskResponse response = callRiskEngine(request);

                return mapToRiskEvaluation(response);
            } catch (Exception e) {
                log.error("Error al evaluar riesgo para solicitante: {}", applicant.email(), e);
                return RiskEvaluation.builder()
                        .approved(false)
                        .riskScore(0)
                        .riskLevel(RiskLevel.HIGH)
                        .reason("Error en la evaluación de riesgo: " + e.getMessage())
                        .build();
            }
        });
    }

    private RiskResponse callRiskEngine(RiskRequest request) {
        try {
            Map<String, Object> body = Map.of(
                    "identification", request.identification(),
                    "email", request.email(),
                    "amount", request.amount(),
                    "employmentStatus", request.employmentStatus()
            );

            Map<String, Object> response = restTemplate.postForObject(
                    riskEngineUrl + "/evaluate",
                    body,
                    Map.class
            );

            if (response == null) {
                throw new RuntimeException("Respuesta nula del motor de riesgo");
            }

            return RiskResponse.builder()
                    .approved((Boolean) response.getOrDefault("approved", false))
                    .score(((Number) response.getOrDefault("score", 0)).intValue())
                    .level((String) response.getOrDefault("level", "HIGH"))
                    .message((String) response.getOrDefault("message", ""))
                    .build();
        } catch (Exception e) {
            log.warn("Error en llamada al motor de riesgo, retornando evaluación por defecto: {}", e.getMessage());
            return RiskResponse.builder()
                    .approved(false)
                    .score(0)
                    .level("HIGH")
                    .message("Servicio de riesgo no disponible")
                    .build();
        }
    }

    private RiskEvaluation mapToRiskEvaluation(RiskResponse response) {
        RiskLevel level = switch (response.getLevel().toUpperCase()) {
            case "LOW" -> RiskLevel.LOW;
            case "MEDIUM" -> RiskLevel.MEDIUM;
            default -> RiskLevel.HIGH;
        };

        return RiskEvaluation.builder()
                .approved(response.isApproved())
                .riskScore(response.getScore())
                .riskLevel(level)
                .reason(response.getMessage())
                .build();
    }

    public record RiskRequest(
            String identification,
            String email,
            BigDecimal amount,
            String employmentStatus
    ) {}

    public record RiskResponse(
            boolean approved,
            int score,
            String level,
            String message
    ) {
        public static RiskResponseBuilder builder() {
            return new RiskResponseBuilder();
        }
    }

    public static class RiskResponseBuilder {
        private boolean approved;
        private int score;
        private String level;
        private String message;

        public RiskResponseBuilder approved(boolean approved) {
            this.approved = approved;
            return this;
        }

        public RiskResponseBuilder score(int score) {
            this.score = score;
            return this;
        }

        public RiskResponseBuilder level(String level) {
            this.level = level;
            return this;
        }

        public RiskResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public RiskResponse build() {
            return new RiskResponse(approved, score, level, message);
        }
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java ===
package com.fintech.loans.infrastructure.adapter;

import com.fintech.loans.domain.model.Applicant;
import com.fintech.loans.domain.port.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentAdapter implements PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentAdapter.class);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    private final RestTemplate restTemplate;
    private final String paymentServiceUrl;
    private final String paymentServiceApiKey;
    private final boolean sandboxMode;

    public PaymentAdapter(
            @Value("${external-services.payment.url:http://localhost:8082/api/payments}") String paymentServiceUrl,
            @Value("${external-services.payment.api-key:demo-key}") String paymentServiceApiKey,
            @Value("${external-services.payment.sandbox-mode:true}") boolean sandboxMode) {
        this.restTemplate = new RestTemplate();
        this.paymentServiceUrl = paymentServiceUrl;
        this.paymentServiceApiKey = paymentServiceApiKey;
        this.sandboxMode = sandboxMode;
    }

    @Override
    public CompletableFuture<PaymentResult> processDisbursement(
            UUID loanId,
            Applicant recipient,
            BigDecimal amount) {

        log.info("Iniciando desembolso para préstamo: {} monto: {} a: {}", 
                loanId, amount, recipient.email());

        return CompletableFuture.supplyAsync(() -> {
            try {
                DisbursementRequest request = new DisbursementRequest(
                        loanId.toString(),
                        recipient.identificationNumber(),
                        recipient.email(),
                        recipient.phoneNumber(),
                        amount,
                        LocalDate.now().plusDays(1).format(DATE_FORMATTER)
                );

                DisbursementResponse response = callPaymentService(request);

                return mapToPaymentResult(response, loanId);
            } catch (Exception e) {
                log.error("Error al procesar desembolso para préstamo: {}", loanId, e);
                return PaymentResult.builder()
                        .success(false)
                        .transactionId(null)
                        .errorMessage("Error al procesar desembolso: " + e.getMessage())
                        .build();
            }
        });
    }

    @Override
    public CompletableFuture<PaymentResult> processPayment(
            UUID loanId,
            UUID borrowerId,
            BigDecimal amount) {

        log.info("Iniciando proceso de pago para préstamo: {} monto: {}", loanId, amount);

        return CompletableFuture.supplyAsync(() -> {
            try {
                PaymentRequest request = new PaymentRequest(
                        loanId.toString(),
                        borrowerId.toString(),
                        amount,
                        LocalDate.now().format(DATE_FORMATTER)
                );

                PaymentResponse response = callCollectPayment(request);

                return mapToCollectPaymentResult(response, loanId);
            } catch (Exception e) {
                log.error("Error al procesar pago para préstamo: {}", loanId, e);
                return PaymentResult.builder()
                        .success(false)
                        .transactionId(null)
                        .errorMessage("Error al procesar pago: " + e.getMessage())
                        .build();
            }
        });
    }

    @Override
    public CompletableFuture<Boolean> validatePaymentMethod(String paymentMethodId) {
        log.info("Validando método de pago: {}", paymentMethodId);

        return CompletableFuture.supplyAsync(() -> {
            try {
                Map<String, Object> response = restTemplate.getForObject(
                        paymentServiceUrl + "/methods/{id}/validate",
                        Map.class,
                        paymentMethodId
                );

                return response != null && Boolean.TRUE.equals(response.get("valid"));
            } catch (Exception e) {
                log.warn("Error al validar método de pago, retornando false: {}", e.getMessage());
                return false;
            }
        });
    }

    private DisbursementResponse callPaymentService(DisbursementRequest request) {
        try {
            Map<String, Object> body = Map.of(
                    "loanId", request.loanId(),
                    "identification", request.identification(),
                    "email", request.email(),
                    "phone", request.phone(),
                    "amount", request.amount(),
                    "scheduledDate", request.scheduledDate()
            );

            @SuppressWarnings("unchecked")
            Map<String, Object> response = restTemplate.postForObject(
                    paymentServiceUrl + "/disbursement",
                    body,
                    Map.class
            );

            if (response == null) {
                throw new RuntimeException("Respuesta nula del servicio de pagos");
            }

            return DisbursementResponse.builder()
                    .success(Boolean.TRUE.equals(response.get("success")))
                    .transactionId((String) response.get("transactionId"))
                    .message((String) response.getOrDefault("message", ""))
                    .build();
        } catch (Exception e) {
            log.warn("Error en llamada al servicio de pagos, retornando respuesta por defecto");
            return DisbursementResponse.builder()
                    .success(false)
                    .transactionId(null)
                    .message("Servicio de pagos no disponible")
                    .build();
        }
    }

    private PaymentResponse callCollectPayment(PaymentRequest request) {
        try {
            Map<String, Object> body = Map.of(
                    "loanId", request.loanId(),
                    "borrowerId", request.borrowerId(),
                    "amount", request.amount(),
                    "paymentDate", request.paymentDate()
            );

            @SuppressWarnings("unchecked")
            Map<String, Object> response = restTemplate.postForObject(
                    paymentServiceUrl + "/collect",
                    body,
                    Map.class
            );

            if (response == null) {
                throw new RuntimeException("Respuesta nula del servicio de cobros");
            }

            return PaymentResponse.builder()
                    .success(Boolean.TRUE.equals(response.get("success")))
                    .transactionId((String) response.get("transactionId"))
                    .message((String) response.getOrDefault("message", ""))
                    .build();
        } catch (Exception e) {
            log.warn("Error en llamada al servicio de cobros, retornando respuesta por defecto");
            return PaymentResponse.builder()
                    .success(false)
                    .transactionId(null)
                    .message("Servicio de cobros no disponible")
                    .build();
        }
    }

    private PaymentResult mapToPaymentResult(DisbursementResponse response, UUID loanId) {
        return PaymentResult.builder()
                .success(response.isSuccess())
                .transactionId(response.transactionId())
                .errorMessage(response.isSuccess() ? null : response.message())
                .build();
    }

    private PaymentResult mapToCollectPaymentResult(PaymentResponse response, UUID loanId) {
        return PaymentResult.builder()
                .success(response.isSuccess())
                .transactionId(response.transactionId())
                .errorMessage(response.isSuccess() ? null : response.message())
                .build();
    }

    public record DisbursementRequest(
            String loanId,
            String identification,
            String email,
            String phone,
            BigDecimal amount,
            String scheduledDate
    ) {}

    public record DisbursementResponse(
            boolean success,
            String transactionId,
            String message
    ) {
        public static DisbursementResponseBuilder builder() {
            return new DisbursementResponseBuilder();
        }
    }

    public static class DisbursementResponseBuilder {
        private boolean success;
        private String transactionId;
        private String message;

        public DisbursementResponseBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public DisbursementResponseBuilder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public DisbursementResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public DisbursementResponse build() {
            return new DisbursementResponse(success, transactionId, message);
        }
    }

    public record PaymentRequest(
            String loanId,
            String borrowerId,
            BigDecimal amount,
            String paymentDate
    ) {}

    public record PaymentResponse(
            boolean success,
            String transactionId,
            String message
    ) {
        public static PaymentResponseBuilder builder() {
            return new PaymentResponseBuilder();
        }
    }

    public static class PaymentResponseBuilder {
        private boolean success;
        private String transactionId;
        private String message;

        public PaymentResponseBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public PaymentResponseBuilder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public PaymentResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public PaymentResponse build() {
            return new PaymentResponse(success, transactionId, message);
        }
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/infrastructure/config/OpenApiConfig.java ===
package com.fintech.loans.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${application.name:Loans Management System}")
    private String applicationName;

    @Value("${application.version:1.0.0}")
    private String applicationVersion;

    @Value("${application.description:Sistema de gestión de préstamos para fintech}")
    private String applicationDescription;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(applicationName)
                        .version(applicationVersion)
                        .description(applicationDescription)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo Fintech")
                                .email("dev@fintech.com")
                                .url("https://www.fintech.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Token JWT de autenticación")));
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java ===
package com.fintech.loans.infrastructure.config;

import com.fintech.loans.domain.port.CreditBureauService.CreditBureauException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null 
                                ? error.getDefaultMessage() 
                                : "Valor no válido",
                        (existing, replacement) -> existing
                ));

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validación de datos fallida")
                .message("Los datos proporcionados no cumplen con las validaciones requeridas")
                .fieldErrors(fieldErrors)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(CreditBureauException.class)
    public ResponseEntity<ErrorResponse> handleCreditBureauException(
            CreditBureauException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .error("Servicio de crédito no disponible")
                .message("No se pudo completar la verificación con el buró de crédito: " + ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Parámetros inválidos")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(
            IllegalStateException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Estado inconsistente")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Error interno del servidor")
                .message("Ocurrió un error inesperado. Por favor, contacte al administrador.")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    public static class ErrorResponse {
        private Instant timestamp;
        private int status;
        private String error;
        private String message;
        private Map<String, String> fieldErrors;
        private String path;

        private ErrorResponse() {}

        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }

        public Instant getTimestamp() { return timestamp; }
        public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public Map<String, String> getFieldErrors() { return fieldErrors; }
        public void setFieldErrors(Map<String, String> fieldErrors) { this.fieldErrors = fieldErrors; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
    }

    public static class ErrorResponseBuilder {
        private Instant timestamp;
        private int status;
        private String error;
        private String message;
        private Map<String, String> fieldErrors = new HashMap<>();
        private String path;

        public ErrorResponseBuilder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorResponseBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ErrorResponseBuilder error(String error) {
            this.error = error;
            return this;
        }

        public ErrorResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseBuilder fieldErrors(Map<String, String> fieldErrors) {
            this.fieldErrors = fieldErrors;
            return this;
        }

        public ErrorResponseBuilder path(String path) {
            this.path = path;
            return this;
        }

        public ErrorResponse build() {
            ErrorResponse response = new ErrorResponse();
            response.setTimestamp(timestamp);
            response.setStatus(status);
            response.setError(error);
            response.setMessage(message);
            response.setFieldErrors(fieldErrors);
            response.setPath(path);
            return response;
        }
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java ===
package com.fintech.loans.infrastructure.controller;


import com.fintech.loans.domain.model.EmploymentStatus;
import com.fintech.loans.application.usecase.SubmitLoanApplication;
import com.fintech.loans.domain.model.Applicant;
import com.fintech.loans.domain.model.LoanApplication;
import com.fintech.loans.domain.port.LoanRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/loans")
@Tag(name = "Gestión de Préstamos", description = "Endpoints para gestionar solicitudes de préstamos")
public class LoanController {

    private final SubmitLoanApplication submitLoanApplication;
    private final LoanRepository loanRepository;

    public LoanController(SubmitLoanApplication submitLoanApplication, LoanRepository loanRepository) {
        this.submitLoanApplication = submitLoanApplication;
        this.loanRepository = loanRepository;
    }

    @PostMapping
    @Operation(summary = "Crear solicitud de préstamo", description = "Envía una nueva solicitud de préstamo para procesamiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Solicitud creada exitosamente",
                    content = @Content(schema = @Schema(implementation = LoanApplicationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "503", description = "Servicio externo no disponible")
    })
    public ResponseEntity<LoanApplicationResponse> createLoanApplication(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la solicitud de préstamo",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LoanApplicationRequest.class))
            )
            @RequestBody LoanApplicationRequest request) {

        Applicant applicant = new Applicant(
                request.applicantId(),
                request.fullName(),
                request.email(),
                request.annualIncome(),
                Applicant.EmploymentStatus.valueOf(request.employmentStatus())
        );

        LoanApplication loanApplication = submitLoanApplication.execute(
                applicant,
                request.requestedAmount(),
                request.termMonths(),
                request.purpose()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(LoanApplicationResponse.fromDomain(loanApplication));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener solicitud por ID", description = "Recupera los detalles de una solicitud de préstamo específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    public ResponseEntity<LoanApplicationResponse> getLoanApplicationById(
            @Parameter(description = "ID de la solicitud de préstamo", required = true)
            @PathVariable UUID id) {

        return loanRepository.findById(id)
                .map(loan -> ResponseEntity.ok(LoanApplicationResponse.fromDomain(loan)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar solicitudes", description = "Lista todas las solicitudes de préstamo con filtros opcionales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de solicitudes")
    })
    public ResponseEntity<List<LoanApplicationResponse>> getAllLoanApplications(
            @Parameter(description = "Filtrar por ID del solicitante")
            @RequestParam(required = false) UUID applicantId,
            @Parameter(description = "Filtrar por estado de la solicitud")
            @RequestParam(required = false) String status) {

        List<LoanApplication> applications;

        if (applicantId != null) {
            applications = loanRepository.findByApplicantId(applicantId);
        } else if (status != null) {
            applications = loanRepository.findByStatus(status);
        } else {
            applications = loanRepository.findAll();
        }

        List<LoanApplicationResponse> responses = applications.stream()
                .map(LoanApplicationResponse::fromDomain)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar solicitud", description = "Elimina una solicitud de préstamo por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Solicitud eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    public ResponseEntity<Void> deleteLoanApplication(
            @Parameter(description = "ID de la solicitud de préstamo", required = true)
            @PathVariable UUID id) {

        if (loanRepository.findById(id).isPresent()) {
            loanRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public record LoanApplicationRequest(
            UUID applicantId,
            String fullName,
            String email,
            BigDecimal annualIncome,
            String employmentStatus,
            BigDecimal requestedAmount,
            Integer termMonths,
            String purpose
    ) {}

    public record LoanApplicationResponse(
            UUID id,
            UUID applicantId,
            String fullName,
            String email,
            BigDecimal annualIncome,
            String employmentStatus,
            BigDecimal requestedAmount,
            Integer termMonths,
            String purpose,
            String status,
            Integer creditScore,
            String riskLevel
    ) {
        public static LoanApplicationResponse fromDomain(LoanApplication loan) {
            return new LoanApplicationResponse(
                    loan.getId(),
                    loan.getApplicantId(),
                    loan.getApplicantFullName(),
                    loan.getApplicantEmail(),
                    loan.getAnnualIncome(),
                    loan.getEmploymentStatus().name(),
                    loan.getRequestedAmount(),
                    loan.getTermMonths(),
                    loan.getPurpose(),
                    loan.getStatus(),
                    loan.getCreditScore(),
                    loan.getRiskLevel()
            );
        }
    }

    public record ErrorResponse(
            int status,
            String error,
            String message
    ) {}
}

// === ARCHIVO: src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java ===
package com.fintech.loans.application.usecase;



import com.fintech.loans.domain.port.CreditBureauException;
import com.fintech.loans.domain.model.EmploymentStatus;
import com.fintech.loans.domain.model.Applicant;
import com.fintech.loans.domain.model.LoanApplication;
import com.fintech.loans.domain.port.CreditBureauService;
import com.fintech.loans.domain.port.LoanRepository;
import com.fintech.loans.domain.port.PaymentService;
import com.fintech.loans.domain.port.RiskEngineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para SubmitLoanApplication")
class SubmitLoanApplicationTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private CreditBureauService creditBureauService;

    @Mock
    private RiskEngineService riskEngineService;

    @Mock
    private PaymentService paymentService;

    private SubmitLoanApplication submitLoanApplication;

    private Applicant validApplicant;

    @BeforeEach
    void setUp() {
        submitLoanApplication = new SubmitLoanApplication(
                loanRepository,
                creditBureauService,
                riskEngineService,
                paymentService
        );

        validApplicant = new Applicant(
                UUID.randomUUID(),
                "Juan Pérez García",
                "juan.perez@email.com",
                new BigDecimal("60000"),
                Applicant.EmploymentStatus.EMPLOYED
        );
    }

    @Test
    @DisplayName("Debería crear solicitud exitosamente cuando el solicitante tiene buen historial crediticio")
    void shouldCreateLoanApplicationSuccessfully() throws Exception {
        when(creditBureauService.getCreditScore(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(750));
        when(creditBureauService.hasCreditHistory(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(true));
        when(riskEngineService.evaluateRisk(any(Applicant.class), any(BigDecimal.class)))
                .thenReturn(CompletableFuture.completedFuture("LOW"));
        when(loanRepository.save(any(LoanApplication.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        LoanApplication result = submitLoanApplication.execute(
                validApplicant,
                new BigDecimal("10000"),
                24,
                "CONSUMO"
        );

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("PENDING", result.getStatus());
        assertEquals(750, result.getCreditScore());
        assertEquals("LOW", result.getRiskLevel());

        verify(loanRepository).save(any(LoanApplication.class));
    }

    @Test
    @DisplayName("Debería rechazar solicitud cuando el puntaje crediticio es muy bajo")
    void shouldRejectApplicationWhenCreditScoreIsLow() throws Exception {
        when(creditBureauService.getCreditScore(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(450));

        LoanApplication result = submitLoanApplication.execute(
                validApplicant,
                new BigDecimal("10000"),
                24,
                "CONSUMO"
        );

        assertEquals("REJECTED", result.getStatus());
        assertEquals("CREDIT_SCORE_TOO_LOW", result.getRejectionReason());

        verify(loanRepository).save(any(LoanApplication.class));
        verify(paymentService, never()).processPayment(any(), any());
    }

    @Test
    @DisplayName("Debería rechazar solicitud cuando el nivel de riesgo es alto")
    void shouldRejectApplicationWhenRiskLevelIsHigh() throws Exception {
        when(creditBureauService.getCreditScore(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(700));
        when(creditBureauService.hasCreditHistory(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(true));
        when(riskEngineService.evaluateRisk(any(Applicant.class), any(BigDecimal.class)))
                .thenReturn(CompletableFuture.completedFuture("HIGH"));

        LoanApplication result = submitLoanApplication.execute(
                validApplicant,
                new BigDecimal("50000"),
                12,
                "CONSUMO"
        );

        assertEquals("REJECTED", result.getStatus());
        assertEquals("HIGH_RISK", result.getRejectionReason());

        verify(loanRepository).save(any(LoanApplication.class));
    }

    @Test
    @DisplayName("Debería aprobar solicitud cuando cumple todos los criterios")
    void shouldApproveApplicationWhenAllCriteriaMet() throws Exception {
        when(creditBureauService.getCreditScore(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(720));
        when(creditBureauService.hasCreditHistory(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(true));
        when(riskEngineService.evaluateRisk(any(Applicant.class), any(BigDecimal.class)))
                .thenReturn(CompletableFuture.completedFuture("LOW"));
        when(paymentService.processPayment(any(), any()))
                .thenReturn(CompletableFuture.completedFuture(true));
        when(loanRepository.save(any(LoanApplication.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        LoanApplication result = submitLoanApplication.execute(
                validApplicant,
                new BigDecimal("15000"),
                36,
                "CONSUMO"
        );

        assertEquals("APPROVED", result.getStatus());
        verify(paymentService).processPayment(any(), any());
    }

    @Test
    @DisplayName("Debería guardar la solicitud en repositorio con datos correctos")
    void shouldSaveApplicationWithCorrectData() throws Exception {
        BigDecimal requestedAmount = new BigDecimal("20000");
        Integer termMonths = 48;
        String purpose = "CONSUMO";

        when(creditBureauService.getCreditScore(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(680));
        when(creditBureauService.hasCreditHistory(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(true));
        when(riskEngineService.evaluateRisk(any(Applicant.class), any(BigDecimal.class)))
                .thenReturn(CompletableFuture.completedFuture("MEDIUM"));
        when(loanRepository.save(any(LoanApplication.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        LoanApplication result = submitLoanApplication.execute(
                validApplicant,
                requestedAmount,
                termMonths,
                purpose
        );

        ArgumentCaptor<LoanApplication> captor = ArgumentCaptor.forClass(LoanApplication.class);
        verify(loanRepository).save(captor.capture());

        LoanApplication savedApplication = captor.getValue();
        assertEquals(validApplicant.id(), savedApplication.getApplicantId());
        assertEquals(validApplicant.fullName(), savedApplication.getApplicantFullName());
        assertEquals(validApplicant.email(), savedApplication.getApplicantEmail());
        assertEquals(requestedAmount, savedApplication.getRequestedAmount());
        assertEquals(termMonths, savedApplication.getTermMonths());
        assertEquals(purpose, savedApplication.getPurpose());
    }

    @Test
    @DisplayName("Debería manejar excepción del servicio de buró de crédito")
    void shouldHandleCreditBureauException() throws Exception {
        when(creditBureauService.getCreditScore(any(Applicant.class)))
                .thenReturn(CompletableFuture.failedFuture(
                        new CreditBureauService.CreditBureauException("Servicio no disponible")));

        assertThrows(Exception.class, () -> submitLoanApplication.execute(
                validApplicant,
                new BigDecimal("10000"),
                24,
                "CONSUMO"
        ));

        verify(loanRepository, never()).save(any(LoanApplication.class));
    }

    @Test
    @DisplayName("Debería rechazar solicitante sin historial crediticio")
    void shouldRejectApplicantWithoutCreditHistory() throws Exception {
        when(creditBureauService.getCreditScore(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(0));
        when(creditBureauService.hasCreditHistory(any(Applicant.class)))
                .thenReturn(CompletableFuture.completedFuture(false));

        LoanApplication result = submitLoanApplication.execute(
                validApplicant,
                new BigDecimal("5000"),
                12,
                "CONSUMO"
        );

        assertEquals("REJECTED", result.getStatus());
        assertEquals("NO_CREDIT_HISTORY", result.getRejectionReason());
    }
}

// === ARCHIVO: docs/integracion-asistentes-ai.md ===
# Integración de Asistentes AI en el Flujo de Desarrollo

## 1. Contexto del Proyecto

El sistema de gestión de préstamos de fintech requiere una integración eficiente de asistentes de inteligencia artificial para optimizar el flujo de desarrollo. Este documento describe la configuración inicial y los casos de uso implementados durante la primera fase del proyecto.

El ecosistema tecnológico del proyecto incluye:
- **Framework**: Spring Boot 3.5 con Java 21
- **Patrón arquitectónico**: Hexagonal/Clean Architecture
- **Base de datos**: PostgreSQL con JPA
- **Documentación API**: OpenAPI/Swagger
- **Gestión de dependencias**: Maven

## 2. Configuración del Entorno de Desarrollo

### 2.1. Instalación y Configuración de Amazon Q Dev

Amazon Q Developer se configuró en el entorno de desarrollo siguiendo estos pasos:

1. **Instalación de la extensión**: En VS Code, buscar "Amazon Q" en el marketplace e instalar la extensión oficial.
2. **Autenticación**: Conectar con las credenciales corporativas de AWS mediante el comando `aws sso login`.
3. **Configuración de contexto**: Especificar el directorio raíz del proyecto para que el asistente indexe el código existente.

La configuración inicial toma aproximadamente 15-20 minutos, incluyendo el tiempo de indexación del código base existente.

### 2.2. Configuración de GitHub Copilot

Para equipos que utilizan GitHub como proveedor de control de versiones:

1. Instalar la extensión "GitHub Copilot" en el IDE.
2. Autenticar mediante GitHub CLI (`gh auth login`).
3. Habilitar en la configuración del proyecto los archivos Java a serem indexados.

## 3. Casos de Uso Implementados

### 3.1. Generación Automática de Código Repetitivo

**Problema original**: La creación de registros DTO para las respuestas de API requería escribir manualmente campos, constructores, getters y métodos equals/hashCode.

**Solución con asistente AI**: Utilizando Amazon Q, se generó automáticamente el código para el DTO de respuesta de solicitud de préstamo:

```java
public record LoanApplicationResponse(
    UUID id,
    UUID applicantId,
    BigDecimal amount,
    Integer termMonths,
    String status,
    LocalDateTime submittedAt,
    LocalDateTime approvedAt
) {}
```

El asistente generó este código en segundos, respetando las convenciones del proyecto (records para DTOs inmutables).

### 3.2. Completado Inteligente de Código

**Contexto**: El patrón hexagonal requiere implementar adaptadores que implementen puertos del dominio. El asistente completó automáticamente la firma del método `save` en el repositorio JPA:

```java
@Override
public LoanApplication save(LoanApplication loanApplication) {
    return entityManager.merge(loanApplication);
}
```

El completado incluyó la inyección correcta del `EntityManager` mediante el constructor, siguiendo las convenciones de inyección de dependencias del proyecto.

### 3.3. Generación de Documentación de API

**Problema**: Documentar manualmente cada endpoint con anotaciones OpenAPI era una tarea repetitiva.

**Solución**: GitHub Copilot generó las anotaciones de documentación:

```java
@Operation(
    summary = "Obtener solicitud de préstamo por ID",
    description = "Retrieves a loan application by its unique identifier",
    responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Solicitud encontrada",
            content = @Content(schema = @Schema(implementation = LoanApplicationResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    }
)
@GetMapping("/{id}")
public ResponseEntity<LoanApplicationResponse> getById(@PathVariable UUID id) {
    // implementación
}
```

## 4. Tareas Automatizadas en el Dominio Financiero

### 4.1. Validaciones de Dominio

El sistema de préstamos requiere validaciones específicas del dominio financiero. Los asistentes AI ayudan a generar validaciones complejas:

- **Validación de rango de monto**: El monto solicitado debe estar entre $1,000 y $500,000.
- **Validación de plazo**: El plazo en meses debe ser múltiplo de 6, entre 6 y 60 meses.
- **Validación de solicitante**: Verificar que el applicant tenga employmentStatus válido.

```java
public void validate() {
    if (amount.compareTo(new BigDecimal("1000")) < 0) {
        throw new IllegalArgumentException("El monto mínimo es $1,000");
    }
    if (amount.compareTo(new BigDecimal("500000")) > 0) {
        throw new IllegalArgumentException("El monto máximo es $500,000");
    }
}
```

### 4.2. Integración con Servicios Externos

Los asistentes AI ayudan a generar el código necesario para integrar con servicios externos como el buró de crédito:

```java
public interface CreditBureauService {
    CompletableFuture<Integer> getCreditScore(Applicant applicant) 
        throws CreditBureauException;
    
    CompletableFuture<Boolean> hasCreditHistory(Applicant applicant) 
        throws CreditBureauException;
}
```

## 5. Métricas de la Fase 1

| Métrica | Valor |
|---------|-------|
| Tiempo de configuración inicial | 20 minutos |
| Archivos generados automáticamente | 12 |
| Líneas de código generadas | ~450 |
| Reducción de tiempo en tareas repetitivas | 35% |
| Errors de sintaxis en código generado | 0 |

## 6. Limitaciones Identificadas

Durante la primera fase se identificaron las siguientes limitaciones:

1. **Contexto limitado**: El asistente no siempre comprende el contexto completo del patrón hexagonal, requiriendo verificación manual de que el código generado respeta las capas del dominio.

2. **Dependencia de convenciones**: El código generado depende de que las convenciones del proyecto estén bien documentadas y sean consistentes.

3. **Casos de dominio complejo**: Para lógica de negocio específica del dominio financiero, el asistente requiere instrucciones más detalladas.

## 7. Recomendaciones para la Fase 2

Para mejorar la integración en las siguientes fases:

- **Documentar el contexto**: Crear archivos README en cada capa que expliquen las convenciones y patrones utilizados.

- **Usar prompts específicos**: En lugar de pedidos genéricos, proporcionar contexto del patrón arquitectónico y las convenciones del equipo.

- **Revisar siempre el código generado**: Los asistentes son herramientas de apoyo, no reemplazos del juicio del desarrollador.

---

*Documento generado como parte del proceso de evaluación del uso de asistentes AI en el desarrollo backend.*

// === ARCHIVO: docs/optimizacion-flujo-trabajo.md ===
# Optimización del Flujo de Trabajo con Asistentes AI

## 1. Resumen Ejecutivo

Esta documentación describe los cambios implementados en el flujo de trabajo durante la segunda fase del proyecto, enfocados en utilizar asistentes de inteligencia artificial para optimizar la productividad y mejorar la calidad del código en el sistema de gestión de préstamos.

## 2. Evolución del Flujo de Trabajo

### 2.1. Flujo Original (Fase 1)

El flujo de trabajo inicial seguía un patrón tradicional:

1. Análisis de requisitos → 2. Diseño → 3. Implementación → 4. Testing → 5. Code Review → 6. Despliegue

Cada etapa requería intervención manual completa, con tiempos significativos en tareas repetitivas como generación de boilerplate, documentación de APIs y escritura de pruebas unitarias.

### 2.2. Flujo Optimizado (Fase 2)

Con la integración de asistentes AI, el flujo evolucionó a:

1. Análisis de requisitos → 2. Diseño (asistido por AI) → 3. Implementación (AI como copiloto) → 4. Testing (AI genera estructura) → 5. Code Review (AI ayuda en análisis) → 6. Despliegue

La diferencia fundamental es que el desarrollador mantiene el control creativo y de decisión, mientras que el asistente maneja tareas repetitivas y proporciona sugerencias contextuales.

## 3. Mejoras Implementadas

### 3.1. Generación Automatizada de Tests Unitarios

**Problema**: Escribir pruebas unitarias para cada caso de uso consumía aproximadamente el 40% del tiempo de desarrollo.

**Solución implementada**: Utilizando el asistente AI para generar la estructura de pruebas, el desarrollador proporciona el caso de uso y el asistente genera el esqueleto de prueba:

```java
@ExtendWith(MockitoExtension.class)
class SubmitLoanApplicationTest {
    
    @Mock
    private LoanRepository loanRepository;
    
    @Mock
    private CreditBureauService creditBureauService;
    
    @Mock
    private RiskEngineService riskEngineService;
    
    @InjectMocks
    private SubmitLoanApplication useCase;
    
    @Test
    void shouldApproveLoanWhenCreditScoreIsHigh() {
        // Given: applicant with good credit history
        Applicant applicant = new Applicant(
            UUID.randomUUID(),
            "John Doe",
            "john.doe@email.com",
            new BigDecimal("50000"),
            Applicant.EmploymentStatus.EMPLOYED
        );
        
        // When: credit score is above threshold
        when(creditBureauService.getCreditScore(applicant))
            .thenReturn(CompletableFuture.completedFuture(750));
        when(creditBureauService.hasCreditHistory(applicant))
            .thenReturn(CompletableFuture.completedFuture(true));
        
        // Then: loan should be approved
        // Implementation continues...
    }
}
```

**Resultado**: Reducción del 60% en el tiempo de escritura de pruebas, manteniendo la cobertura de código.

### 3.2. Refactorización Asistida

El patrón hexagonal requiere mantener separadas las capas de dominio, aplicación e infraestructura. El asistente ayudó a identificar y corregir violaciones del patrón:

**Antes (código con acoplamiento incorrecto)**:
```java
public class LoanService {
    @Autowired
    private EntityManager entityManager;  // Violación: infraestructura en dominio
    
    public void processLoan(LoanApplication loan) {
        entityManager.persist(loan);  // Violación: acceso directo a JPA
    }
}
```

**Después (código refactorizado)**:
```java
public class LoanService {
    private final LoanRepository loanRepository;  // Puerto del dominio
    
    public void processLoan(LoanApplication loan) {
        loanRepository.save(loan);  // Implementación en infraestructura
    }
}
```

El asistente sugirió la refactorización al detectar el uso directo de EntityManager en la capa de aplicación.

### 3.3. Generación de Documentación técnica

La documentación técnica se generó automáticamente para componentes complejos:

- **README.md por módulo**: Descripción del propósito y responsabilidades.
- **Documentación de puertos**: Descripción de cada interfaz del dominio y su propósito.
- **Documentación de adaptadores**: Explicación de la implementación de cada puerto.

```markdown
# Módulo de Dominio - Préstamos

## Responsabilidades
- Definir las entidades del dominio (Applicant, LoanApplication)
- Definir los puertos de comunicación con servicios externos
- Implementar la lógica de validación de negocio

## Entidades
- `Applicant`: Representa un solicitante de préstamo
- `LoanApplication`: Representa una solicitud de préstamo

## Puertos
- `LoanRepository`: Persistencia de solicitudes de préstamo
- `CreditBureauService`: Integración con buró de crédito
- `RiskEngineService`: Evaluación de riesgo del préstamo
```

## 4. Mejoras en la Calidad del Código

### 4.1. Consistencia en Convenciones

El asistente AI helped mantener consistencia en las convenciones del proyecto:

| Aspecto | Conveniencia | Cumplimiento |
|---------|--------------|---------------|
| Nombres de clases | PascalCase | 100% |
| Nombres de métodos | camelCase | 100% |
| Records para DTOs | Inmutables | 100% |
| Inyección por constructor | Recomendado | 95% |
| Anotaciones de validación | Jakarta | 100% |

### 4.2. Detección de Code Smells

El asistente identificó potenciales problemas durante la escritura:

- **Métodos demasiado largos**: Sugirió拆分 métodos grandes en funciones más pequeñas.
- **Duplicación de código**: Identificó patrones repetidos y sugirió extracción a métodos utilitarios.
- **Excepciones no controladas**: Advirtió sobre excepciones que deberían manejarse explícitamente.

### 4.3. Mejoras en Seguridad

Se implementaron recomendaciones de seguridad generadas por el asistente:

- Validación de entradas con Jakarta Bean Validation.
- Sanitización de datos antes de persistirlos.
- Manejo seguro de información sensible en logs.

```java
@PostMapping("/apply")
public ResponseEntity<LoanApplicationResponse> submitApplication(
        @Valid @RequestBody LoanApplicationRequest request,
        BindingResult result) {
    
    if (result.hasErrors()) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponse("Validation failed", result.getFieldErrors()));
    }
    
    // Process application
}
```

## 5. Casos de Uso Avanzados

### 5.1. Generación de Consultas JPA

El asistente generó consultas complejas para reportes financieros:

```java
@Query("SELECT la FROM LoanApplication la " +
       "WHERE la.status = :status " +
       "AND la.submittedAt BETWEEN :startDate AND :endDate " +
       "ORDER BY la.amount DESC")
List<LoanApplication> findByStatusAndDateRange(
    @Param("status") String status,
    @Param("startDate") LocalDateTime startDate,
    @Param("endDate") LocalDateTime endDate);
```

### 5.2. Manejo de Excepciones Centralizado

El asistente generó el manejador global de excepciones siguiendo las mejores prácticas de Spring:

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> new FieldError(error.getField(), error.getDefaultMessage()))
            .collect(Collectors.toList());
        
        return ResponseEntity.badRequest()
            .body(new ErrorResponse("Validation failed", errors));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("Unexpected error occurred", ex);
        return ResponseEntity.internalServerError()
            .body(new ErrorResponse("Internal server error", null));
    }
}
```

## 6. Métricas de la Fase 2

| Métrica | Fase 1 | Fase 2 | Mejora |
|---------|--------|--------|--------|
| Tiempo promedio por historia de usuario | 4.5 horas | 3.2 horas | 29% |
| Cobertura de pruebas | 65% | 82% | 17% |
| Defectos en producción | 3 | 1 | 67% |
| Tiempo en code review | 2 horas | 1.5 horas | 25% |
| Documentación actualizada | Parcial | Completa | 100% |

## 7. Lecciones Aprendidas

### 7.1. Éxitos

- La generación de código repetitivo reduction significantly el tiempo de desarrollo.
- La consistencia del código mejoró notablemente con la asistencia continua.
- El onboarding de nuevos desarrolladores se aceleró al tener código bien documentado.

### 7.2. Áreas de Mejora

- El asistente requiere contexto claro del patrón arquitectónico para generar código correcto.
- La revisión humana sigue siendo esencial para lógica de negocio compleja.
- Es necesario establecer convenciones claras antes de depender del asistente.

---

*Documento generado como parte del proceso de evaluación del uso de asistentes AI en el desarrollo backend.*

// === ARCHIVO: docs/evaluacion-resultados.md ===
# Evaluación de Resultados y Recomendaciones

## 1. Resumen de la Evaluación

Este documento presenta la evaluación completa del impacto de los asistentes de inteligencia artificial en el desarrollo backend del sistema de gestión de préstamos. La evaluación cubre las tres fases del proyecto y proporciona recomendaciones para el uso continuo de estas herramientas.

## 2. Métricas Cuantitativas

### 2.1. Productividad del Desarrollo

Se midieron las siguientes métricas de productividad a lo largo del proyecto:

| Métrica | Sin AI | Con AI | Variación |
|---------|--------|--------|----------|
| Líneas de código por día | 85 | 142 | +67% |
| Tiempo promedio por tarea | 4.5 h | 2.8 h | -38% |
| Tareas completadas por sprint | 12 | 18 | +50% |
| Bugs introducidos por semana | 4.2 | 1.8 | -57% |
| Tiempo en refactorización | 8 h/sprint | 3 h/sprint | -62% |

### 2.2. Calidad del Código

La calidad del código se evaluó utilizando múltiples indicadores:

- **Cobertura de pruebas**: Incremento del 65% al 87%.
- **Complejidad ciclomática**: Reducción promedio de 2.3 puntos por método.
- **Duplicación de código**: Reducción del 15% al 4%.
- **Violaciones de estilo**: Reducción del 25% al 2%.

### 2.3. Documentación

La documentación del proyecto mejoró significativamente:

- **Documentación de APIs**: 100% de endpoints documentados (antes: 60%).
- **Documentación de módulos**: Todos los módulos tienen README (antes: ninguno).
- **Javadoc en clases públicas**: 85% de cobertura (antes: 30%).

## 3. Análisis por Fase

### 3.1. Fase 1: Integración Inicial

**Hallazgos principales**:

- La configuración inicial de los asistentes took 20-30 minutos por desarrollador.
- La curva de aprendizaje inicial fue de aproximadamente 2 semanas para alcanzar productividad básica.
- Los casos de uso más efectivos fueron la generación de código repetitivo y el completado de código.

**Métricas de la fase**:

- Tiempo de configuración: 25 minutos promedio
- Productividad inicial: +15% vs baseline
- Satisfaction del equipo: 3.8/5.0

### 3.2. Fase 2: Optimización del Flujo

**Hallazgos principales**:

- La refactorización asistida redujo significativamente las violaciones del patrón arquitectónico.
- La generación automática de pruebas unitarias permitió aumentar la cobertura sin incrementar el tiempo de desarrollo.
- El flujo de trabajo se volvió más predecible y repetible.

**Métricas de la fase**:

- Reducción de tiempo en tareas repetitivas: 45%
- Aumento en cobertura de pruebas: +22%
- Satisfacción del equipo: 4.3/5.0

### 3.3. Fase 3: Evaluación y Decisiones

**Hallazgos principales**:

- El equipo desarrolló un marco mental para determinar cuándo usar el asistente y cuándo no.
- Se identificaron patrones claros de éxito y limitaciones.
- La documentación generada automáticamente fue tan buena o mejor que la escrita manualmente.

**Métricas de la fase**:

- Decisiones de diseño tomadas con asistencia: 78%
- Decisiones de diseño tomadas sin asistencia: 22%
- Satisfacción del equipo: 4.5/5.0

## 4. Análisis Cualitativo

### 4.1. Percepción del Equipo

Se realizó una encuesta anónima al equipo de desarrollo con los siguientes resultados:

| Pregunta | Promedio (1-5) |
|----------|----------------|
| ¿Los asistentes AI mejoraron tu productividad? | 4.4 |
| ¿Sientes que la calidad de tu código mejoró? | 4.2 |
| ¿Recomendaría el uso de asistentes AI? | 4.6 |
| ¿El código generado requiere más revisión? | 3.1 |
| ¿Sientes que tu aprendizaje se ha visto afectado? | 2.8 |

**Interpretación**: El equipo valora positivamente el impacto de los asistentes, aunque reconoce que el código generado requiere revisión y existe preocupación por el impacto en el aprendizaje.

### 4.2. Casos de Éxito

**Caso 1: Generación de adaptadores de integración**

El asistente generó el adaptador completo para el servicio de buró de crédito en 15 minutos, incluyendo manejo de excepciones y logging apropiado:

```java
@Component
public class CreditBureauAdapter implements CreditBureauService {
    
    private final CreditBureauClient client;
    private final Logger logger = LoggerFactory.getLogger(CreditBureauAdapter.class);
    
    @Override
    public CompletableFuture<Integer> getCreditScore(Applicant applicant) 
            throws CreditBureauException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                logger.info("Consulting credit score for applicant: {}", applicant.id());
                CreditScoreResponse response = client.getCreditScore(
                    new CreditScoreRequest(applicant.id(), applicant.ssn())
                );
                return response.score();
            } catch (CreditBureauClientException e) {
                logger.error("Error consulting credit bureau", e);
                throw new CreditBureauException("Failed to get credit score", e);
            }
        });
    }
}
```

**Caso 2: Refactorización del manejador de excepciones**

El asistente sugirió y ayudó a implementar un manejador de excepciones centralizado que redujo el código repetitivo en controladores en un 70%.

### 4.3. Limitaciones y Desafíos

**Desafío 1: Contexto arquitectónico**

El asistente ocasionalmente generó código que violaba el patrón hexagonal al mezclar capas. Esto requirió revisión cuidadosa y, en algunos casos, reescritura del código sugerido.

**Desafío 2: Lógica de dominio compleja**

Para la lógica de negocio específica del dominio financiero (cálculo de riesgo, evaluación de capacidad de pago), el asistente necesitó instrucciones detalladas y ejemplos de código existente.

**Desafío 3: Actualización de contexto**

El asistente no siempre capturaba los cambios recientes en el código base, lo que llevaba a sugerir código obsoleto o incompatible.

## 5. Recomendaciones

### 5.1. Recomendaciones para el Equipo de Desarrollo

1. **Establecer convenciones claras**: Antes de adoptar asistentes AI, el equipo debe documentar y acordar las convenciones de código, patrones arquitectónicos y estándares de calidad.

2. **Usar el asistente como herramienta de apoyo, no de reemplazo**: El desarrollador debe mantener el control sobre las decisiones de diseño y la lógica de negocio crítica.

3. **Revisar siempre el código generado**: Aunque el código generado es generalmente de alta calidad, la revisión humana es esencial para garantizar que cumple con los requisitos específicos del proyecto.

4. **Proporcionar contexto**: Al interactuar con el asistente, proporcionar siempre contexto del patrón arquitectónico, convenciones del equipo y requisitos específicos del dominio.

5. **Mantener el aprendizaje**: Los desarrolladores deben continuar aprendiendo los fundamentos y las mejores prácticas, no depender exclusivamente del asistente para resolver problemas.

### 5.2. Recomendaciones para la Organización

1. **Capacitación estructurada**: Implementar programas de capacitación que ensenen a los desarrolladores a utilizar efectivamente los asistentes AI.

2. **Herramientas de calidad**: Integrar herramientas de análisis estático de código que complementen el trabajo de los asistentes AI.

3. **Métricas de seguimiento**: Establecer métricas para monitorear el impacto de los asistentes AI en la productividad y calidad del código.

4. **Cultura de mejora continua**: Fomentar la retroalimentación continua sobre el uso de asistentes AI y la evolución de las prácticas del equipo.

### 5.3. Recomendaciones Técnicas

1. **Configuración de IDE**: Personalizar la configuración del asistente para que respete las convenciones del proyecto y el lenguaje de programación utilizado.

2. **Integración con CI/CD**: Incluir verificaciones automatizadas que detecten código generado que no cumpla con los estándares del proyecto.

3. **Documentación de patrones**: Mantener documentación actualizada de los patrones arquitectónicos y las convenciones del proyecto para que el asistente pueda acceder a ella.

## 6. Conclusiones

### 6.1. Impacto General

La integración de asistentes AI en el flujo de desarrollo del sistema de gestión de préstamos tuvo un impacto positivo significativo:

- **Productividad**: Incremento del 67% en líneas de código por día.
- **Calidad**: Reducción del 57% en defectos introducidos.
- **Documentación**: Cobertura del 100% en documentación de APIs.
- **Satisfacción**: Calificación de 4.5/5.0 del equipo de desarrollo.

### 6.2. Viabilidad del Uso Continuo

Basado en los resultados de las tres fases, se concluye que el uso de asistentes AI es **altamente recomendable** para el desarrollo backend por las siguientes razones:

1. **Retorno de inversión positivo**: La inversión en configuración y capacitación se recupera rápidamente en productividad.

2. **Mejora sostenida**: Los beneficios se mantienen y amplían a medida que el equipo ganha familiaridade con las herramientas.

3. **Calidad mejorada**: El código generado es consistente con las convenciones del proyecto y cumple con los estándares de calidad.

4. **Satisfacción del equipo**: Los desarrolladores valoran positivamente la asistencia y la consideran una herramienta útil.

### 6.3. Próximos Pasos

Para continuar mejorando el uso de asistentes AI en el proyecto:

1. **Expanding el uso**: Extender el uso de asistentes a otras áreas del proyecto, incluyendo DevOps y documentación técnica.

2. **Personalización**: Desarrollar prompts y templates específicos para el dominio financiero que automatizen tareas comunes.

3. **Integración**: Integrar los asistentes con las herramientas de CI/CD para verificar automáticamente la calidad del código generado.

4. **Evaluación continua**: Establecer un proceso de evaluación continua para monitorear el impacto y ajustar las prácticas según sea necesario.

---

*Este documento forma parte del proyecto de evaluación del uso de asistentes AI en el desarrollo backend del sistema de gestión de préstamos.*
```

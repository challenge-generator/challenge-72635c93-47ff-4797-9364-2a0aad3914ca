# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Fortalecimiento de habilidades de desarrollo backend con asistentes AI**.

| | |
|---|---|
| Tema | Desarrollo |
| Nivel | advanced-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 10 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web n/a
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.springframework.boot:spring-boot-maven-plugin n/a
- org.projectlombok:lombok 1.18.30
- org.postgresql:postgresql 42.7.3
- io.swagger.core.v3:swagger-annotations 2.2.22
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.5.0
- org.mockito:mockito-core 5.12.0
- org.mockito:mockito-junit-jupiter 5.12.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Integración inicial de asistentes AI**: Documentación de la integración de asistentes AI en el flujo de desarrollo, incluyendo ejemplos de tareas automatizadas.
- **Fase 2 — Optimización del flujo de trabajo con asistentes AI**: Documentación de los cambios implementados en el flujo de trabajo, incluyendo ejemplos de tareas automatizadas y mejoras en la calidad del código.
- **Fase 3 — Evaluación y toma de decisiones con asistentes AI**: Documentación de la evaluación de los resultados, conclusiones y recomendaciones para el uso continuo de asistentes AI en el desarrollo backend.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Referencias colgando (74)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/fintech/loans/application/usecase/SubmitLoanApplication.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/loans/application/usecase/SubmitLoanApplication.java` — `Applicant.id`
      Se invoca `id` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/application/usecase/SubmitLoanApplication.java` — `Applicant.employmentStatus`
      Se invoca `employmentStatus` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/application/usecase/SubmitLoanApplication.java` — `Applicant.monthlyIncome`
      Se invoca `monthlyIncome` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java` — `Applicant.id`
      Se invoca `id` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java` — `CreditBureauConfig.getResponseDelay`
      Se invoca `getResponseDelay` sobre `CreditBureauConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java` — `CreditBureauConfig.isSimulateFailure`
      Se invoca `isSimulateFailure` sobre `CreditBureauConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java` — `Applicant.identificationNumber`
      Se invoca `identificationNumber` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java` — `Applicant.employmentStatus`
      Se invoca `employmentStatus` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java` — `Applicant.age`
      Se invoca `age` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java` — `Applicant.monthlyIncome`
      Se invoca `monthlyIncome` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `Applicant.email`
      Se invoca `email` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `Applicant.identificationNumber`
      Se invoca `identificationNumber` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `Applicant.employmentStatus`
      Se invoca `employmentStatus` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskRequest.identification`
      Se invoca `identification` sobre `RiskRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskRequest.email`
      Se invoca `email` sobre `RiskRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskRequest.amount`
      Se invoca `amount` sobre `RiskRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskRequest.employmentStatus`
      Se invoca `employmentStatus` sobre `RiskRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskResponse.getOrDefault`
      Se invoca `getOrDefault` sobre `RiskResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskResponse.getLevel`
      Se invoca `getLevel` sobre `RiskResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskResponse.isApproved`
      Se invoca `isApproved` sobre `RiskResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskResponse.getScore`
      Se invoca `getScore` sobre `RiskResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java` — `RiskResponse.getMessage`
      Se invoca `getMessage` sobre `RiskResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `Applicant.email`
      Se invoca `email` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `Applicant.identificationNumber`
      Se invoca `identificationNumber` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `Applicant.phoneNumber`
      Se invoca `phoneNumber` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentResponse.get`
      Se invoca `get` sobre `PaymentResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentRequest.loanId`
      Se invoca `loanId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentRequest.identification`
      Se invoca `identification` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentRequest.email`
      Se invoca `email` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentRequest.phone`
      Se invoca `phone` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentRequest.amount`
      Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentRequest.scheduledDate`
      Se invoca `scheduledDate` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentResponse.getOrDefault`
      Se invoca `getOrDefault` sobre `PaymentResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentRequest.borrowerId`
      Se invoca `borrowerId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentRequest.paymentDate`
      Se invoca `paymentDate` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentResponse.isSuccess`
      Se invoca `isSuccess` sobre `PaymentResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentResponse.transactionId`
      Se invoca `transactionId` sobre `PaymentResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java` — `PaymentResponse.message`
      Se invoca `message` sobre `PaymentResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java` — `ErrorResponse.setTimestamp`
      Se invoca `setTimestamp` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java` — `ErrorResponse.setStatus`
      Se invoca `setStatus` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java` — `ErrorResponse.setError`
      Se invoca `setError` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java` — `ErrorResponse.setMessage`
      Se invoca `setMessage` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java` — `ErrorResponse.setFieldErrors`
      Se invoca `setFieldErrors` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java` — `ErrorResponse.setPath`
      Se invoca `setPath` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplicationRequest.applicantId`
      Se invoca `applicantId` sobre `LoanApplicationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplicationRequest.fullName`
      Se invoca `fullName` sobre `LoanApplicationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplicationRequest.email`
      Se invoca `email` sobre `LoanApplicationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplicationRequest.annualIncome`
      Se invoca `annualIncome` sobre `LoanApplicationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplicationRequest.employmentStatus`
      Se invoca `employmentStatus` sobre `LoanApplicationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplicationRequest.requestedAmount`
      Se invoca `requestedAmount` sobre `LoanApplicationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplicationRequest.termMonths`
      Se invoca `termMonths` sobre `LoanApplicationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplicationRequest.purpose`
      Se invoca `purpose` sobre `LoanApplicationRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanRepository.findAll`
      Se invoca `findAll` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplication.getApplicantFullName`
      Se invoca `getApplicantFullName` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplication.getApplicantEmail`
      Se invoca `getApplicantEmail` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplication.getAnnualIncome`
      Se invoca `getAnnualIncome` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplication.getEmploymentStatus`
      Se invoca `getEmploymentStatus` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplication.getRequestedAmount`
      Se invoca `getRequestedAmount` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java` — `LoanApplication.getRiskLevel`
      Se invoca `getRiskLevel` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `RiskEngineService.evaluateRisk`
      Se invoca `evaluateRisk` sobre `RiskEngineService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `LoanApplication.getRiskLevel`
      Se invoca `getRiskLevel` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `PaymentService.processPayment`
      Se invoca `processPayment` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `Applicant.id`
      Se invoca `id` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `Applicant.fullName`
      Se invoca `fullName` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `LoanApplication.getApplicantFullName`
      Se invoca `getApplicantFullName` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `Applicant.email`
      Se invoca `email` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `LoanApplication.getApplicantEmail`
      Se invoca `getApplicantEmail` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java` — `LoanApplication.getRequestedAmount`
      Se invoca `getRequestedAmount` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `docs/evaluacion-resultados.md` — `Applicant.id`
      Se invoca `id` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `docs/evaluacion-resultados.md` — `Applicant.ssn`
      Se invoca `ssn` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (21)

- `pom.xml`
- `src/main/java/com/fintech/loans/LoansApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/fintech/loans/domain/model/Applicant.java`
- `src/main/java/com/fintech/loans/domain/port/LoanRepository.java`
- `src/main/java/com/fintech/loans/domain/port/CreditBureauService.java`
- `src/main/java/com/fintech/loans/domain/model/LoanApplication.java`
- `src/main/java/com/fintech/loans/application/usecase/SubmitLoanApplication.java`
- `src/main/java/com/fintech/loans/domain/port/RiskEngineService.java`
- `src/main/java/com/fintech/loans/domain/port/PaymentService.java`
- `src/main/java/com/fintech/loans/infrastructure/adapter/LoanJpaRepository.java`
- `src/main/java/com/fintech/loans/infrastructure/adapter/CreditBureauAdapter.java`
- `src/main/java/com/fintech/loans/infrastructure/adapter/RiskEngineAdapter.java`
- `src/main/java/com/fintech/loans/infrastructure/adapter/PaymentAdapter.java`
- `src/main/java/com/fintech/loans/infrastructure/config/OpenApiConfig.java`
- `src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java`
- `src/main/java/com/fintech/loans/infrastructure/controller/LoanController.java`
- `src/test/java/com/fintech/loans/application/usecase/SubmitLoanApplicationTest.java`
- `docs/integracion-asistentes-ai.md`
- `docs/optimizacion-flujo-trabajo.md`
- `docs/evaluacion-resultados.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/fintech/loans`
- `src/main/java/com/fintech/loans/domain`
- `src/main/java/com/fintech/loans/domain/model`
- `src/main/java/com/fintech/loans/domain/port`
- `src/main/java/com/fintech/loans/application`
- `src/main/java/com/fintech/loans/application/usecase`
- `src/main/java/com/fintech/loans/infrastructure`
- `src/main/java/com/fintech/loans/infrastructure/adapter`
- `src/main/java/com/fintech/loans/infrastructure/config`
- `src/main/java/com/fintech/loans/infrastructure/controller`
- `src/main/resources`
- `src/test/java/com/fintech/loans`

## Verificacion

```bash
mvn clean compile
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced
- Brecha que el reto ataca: Ha trabajado con algún asistente AI en desarrollo (Amazon Q Dev, Kiro, Github Copilot) y lo utiliza en su día a día como desarrollador. Necesita fortalecer su práctica y dominio de estas herramientas en contextos reales de desarrollo backend.
- Mision: Candidato con experiencia como developer backend en Java, en equipo distribuido.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*

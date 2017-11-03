/*-------------------------------------------*/
/*      INSERCION DE TABLA ESTUDIANTE        */
/*-------------------------------------------*/

INSERT INTO PROYECTOTALLER.TABLA_ESTUDIANTE
VALUES (1152456846, 'Juan Esteban', 'Garcia', 'Humanidades', TO_DATE('09/04/1996', 'MM/DD/YYYY'), 5840395, 'juanegt098@gmail.com');
INSERT INTO PROYECTOTALLER.TABLA_ESTUDIANTE
VALUES (2152456847, 'Monica', 'Gallego', 'Humanidades', TO_DATE('04/101994', 'MM/DD/YYYY'), 2340393, 'Monicagal@homail');
INSERT INTO PROYECTOTALLER.TABLA_ESTUDIANTE
VALUES (2152456848, 'Andres', 'Delgado', 'Humanidades', TO_DATE('08/12/1998', 'MM/DD/YYYY'), 3410598, 'delgadoandres82@gmail.com');
INSERT INTO PROYECTOTALLER.TABLA_ESTUDIANTE
VALUES (2152456849, 'Wilson', 'Ramirez', 'Ciencias naturales', TO_DATE('11/02/1990', 'MM/DD/YYYY'), 3421278, 'wramirez@hotmail.com');
INSERT INTO PROYECTOTALLER.TABLA_ESTUDIANTE
VALUES (2152456841, 'Andres', 'Gomez', 'Ciencias naturales', TO_DATE('12/201991', 'MM/DD/YYYY'), 3435578, 'gomezandres@gmail.com');
INSERT INTO PROYECTOTALLER.TABLA_ESTUDIANTE
VALUES (2152456842, 'Manuela', 'Parra', 'Matematicas', TO_DATE('08/19/1993', 'MM/DD/YYYY'), 3415371, 'manup2010@gmail.com');
INSERT INTO PROYECTOTALLER.TABLA_ESTUDIANTE
VALUES (2152456843, 'Luis', 'Bedoya', 'Matematicas', TO_DATE('09/10/1993', 'MM/DD/YYYY'), 3435578, 'lbedoya@hotmail.com');
INSERT INTO PROYECTOTALLER.TABLA_ESTUDIANTE
VALUES (2152456844, 'Wilberto Andres', 'Davila', 'Etica', TO_DATE('01/01/1993', 'MM/DD/YYYY'), 3502209, 'wadav12@gmail.com');


/*-------------------------------------------*/
/*      INSERCION DE TABLA PROFESOR          */
/*-------------------------------------------*/

INSERT INTO PROYECTOTALLER.TABLA_PROFESOR
VALUES (196090415, 'Amparo', 'Gonzales', 5430908, 'ampa19@gmail.com', 'Licenciada en matematicas');
INSERT INTO PROYECTOTALLER.TABLA_PROFESOR
VALUES (296090416, 'Carlos', 'Garcia', 5829895, 'cgarcia29@hotmail.com', 'Master en ciencias naturales');
INSERT INTO PROYECTOTALLER.TABLA_PROFESOR
VALUES (396090417, 'Ruby', 'Galeano', 4843495, 'rubyagale39@gmail.com', 'Certificado en ciencias humanas');
INSERT INTO PROYECTOTALLER.TABLA_PROFESOR
VALUES (496090418, 'Andres', 'Rendon', 3289656, 'andresr49@gmail.com', 'Docente certificado en etica y valores');

/*-------------------------------------------*/
/*      INSERCION DE TABLA ASIGNATURA        */
/*-------------------------------------------*/

INSERT INTO PROYECTOTALLER.TABLA_ASIGNATURA
VALUES (001, 'Matematicas', 196090415 );
INSERT INTO PROYECTOTALLER.TABLA_ASIGNATURA
VALUES (002, 'Ciencias naturales', 296090416 );
INSERT INTO PROYECTOTALLER.TABLA_ASIGNATURA
VALUES (003, 'Humanidades', 396090417 );
INSERT INTO PROYECTOTALLER.TABLA_ASIGNATURA
VALUES (004, 'Etica', 496090418 );


/*-------------------------------------------*/
/*      INSERCION DE TABLA SEGUIMIENTO       */
/*-------------------------------------------*/

INSERT INTO PROYECTOTALLER.TABLA_SEGUIMIENTO
VALUES (123, 5, 3, 001 ,1152456846);
INSERT INTO PROYECTOTALLER.TABLA_SEGUIMIENTO
VALUES (124, 2, 3, 002 ,2152456847);
INSERT INTO PROYECTOTALLER.TABLA_SEGUIMIENTO
VALUES (125, 4, 2, 003 ,2152456849);
INSERT INTO PROYECTOTALLER.TABLA_SEGUIMIENTO
VALUES (126, 4, 5, 004 ,2152456841);


/*-------------------------------------------*/
/*      INSERCION DE TABLA GRUPO             */
/*-------------------------------------------*/

INSERT INTO PROYECTOTALLER.TABLA_GRUPO
VALUES (10, 'Lunes y Miercoles', 4, 001);
INSERT INTO PROYECTOTALLER.TABLA_GRUPO
VALUES (20, 'Lunes', 2, 002);
INSERT INTO PROYECTOTALLER.TABLA_GRUPO
VALUES (30, 'Martes y Jueves', 4, 003);
INSERT INTO PROYECTOTALLER.TABLA_GRUPO
VALUES (40, 'Sabado', 1, 004);


/*-------------------------------------------*/
/*      INSERCION DE TABLA NOTA              */
/*-------------------------------------------*/

INSERT INTO PROYECTOTALLER.TABLA_NOTA
VALUES (101,'exposicion', 5, 123);
INSERT INTO PROYECTOTALLER.TABLA_NOTA
VALUES (102,'trabajo escrito', 2, 123);
INSERT INTO PROYECTOTALLER.TABLA_NOTA
VALUES (103,'examen virtual', 2, 125);
INSERT INTO PROYECTOTALLER.TABLA_NOTA
VALUES (104,'dibujo', 4, 126);


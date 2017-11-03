/*-------------------------------------------*/
/*  MODELO ENTIDAD RELACION PROYECTO TALLER  */
/*-------------------------------------------*/
/*
 * Created by:  - Veronica Gomez
 *              - Daniel londo�o
 *              - Juan Jose Rendon
 */
 
/*Create user PROYECTOTALLER identified by 123456
default tablespace "USERS"
temporary tablespace "TEMP";

Grant resource to PROYECTOTALLER;
Grant connect to PROYECTOTALLER;*/
/*-------------------------------------------*/
/*             BORRADO DE TABLAS             */
/*-------------------------------------------*/

DROP TABLE PROYECTOTALLER.TABLA_NOTA;
DROP TABLE PROYECTOTALLER.TABLA_GRUPO;
DROP TABLE PROYECTOTALLER.TABLA_SEGUIMIENTO;
DROP TABLE PROYECTOTALLER.TABLA_ASIGNATURA;
DROP TABLE PROYECTOTALLER.TABLA_PROFESOR;
DROP TABLE PROYECTOTALLER.TABLA_ESTUDIANTE;

/*-------------------------------------------*/
/*  SCRIPTS DE CREACION DE LA BASE DE DATOS  */
/*-------------------------------------------*/


CREATE TABLE PROYECTOTALLER.TABLA_ESTUDIANTE (
    ID_ESTUDIANTE           NUMBER(10),
    NOMBRE_ESTUDIANTE       VARCHAR2(50),
    APELLIDO_ESTUDIANTE     VARCHAR2(50),
    PROGRAMA                VARCHAR2(100),
    EDAD                    DATE,
    TELEFONO                NUMBER(10),
    CORREO                  VARCHAR2(50)
);

CREATE TABLE PROYECTOTALLER.TABLA_PROFESOR (
    ID_PROFESOR             NUMBER(10),
    NOMBRE_PROFESOR         VARCHAR2(50),
    APELLIDO_PROFESOR       VARCHAR2(50),
    TELEFONO                NUMBER(10),
    CORREO                  VARCHAR2(50),
    PROFESION               VARCHAR2(100)
);

CREATE TABLE PROYECTOTALLER.TABLA_ASIGNATURA (
    CODIGO_ASIGNATURA       NUMBER(6),
    NOMBRE                  VARCHAR2(50),
    ID_PROFESOR             NUMBER(10)
);

CREATE TABLE PROYECTOTALLER.TABLA_GRUPO (
    CODIGO_GRUPO            NUMBER(6),
    HORARIO                 VARCHAR2(50),
    INTENSIDAD              NUMBER(1),
    CODIGO_ASIGNATURA       NUMBER(6)
);

CREATE TABLE PROYECTOTALLER.TABLA_NOTA (
    CODIGO_NOTA             NUMBER(6),
    NOMBRE                  VARCHAR2(50),
    NOTA                    NUMBER(1),
    CODIGO_SEGUIMIENTO      NUMBER(6)
);

CREATE TABLE PROYECTOTALLER.TABLA_SEGUIMIENTO (
    CODIGO_SEGUIMIENTO      NUMBER(6),
    PARCIAL_1               NUMBER(1),
    PARCIAL_2               NUMBER(1),
    CODIGO_ASIGNATURA       NUMBER(6),
    ID_ESTUDIANTE           NUMBER(10)
);


/*-------------------------------------------*/
/*         CREACION CLAVES PRIMARIAS         */
/*-------------------------------------------*/

ALTER TABLE PROYECTOTALLER.TABLA_ESTUDIANTE ADD CONSTRAINT PK_ID_ESTUDIANTE PRIMARY KEY ( ID_ESTUDIANTE );
ALTER TABLE PROYECTOTALLER.TABLA_PROFESOR ADD CONSTRAINT PK_ID_PROFESOR PRIMARY KEY ( ID_PROFESOR );
ALTER TABLE PROYECTOTALLER.TABLA_ASIGNATURA ADD CONSTRAINT PK_CODIGO_ASIGNATURA PRIMARY KEY ( CODIGO_ASIGNATURA );
ALTER TABLE PROYECTOTALLER.TABLA_GRUPO ADD CONSTRAINT PK_CODIGO_GRUPO PRIMARY KEY ( CODIGO_GRUPO );
ALTER TABLE PROYECTOTALLER.TABLA_NOTA ADD CONSTRAINT PK_CODIGO_NOTA PRIMARY KEY ( CODIGO_NOTA );
ALTER TABLE PROYECTOTALLER.TABLA_SEGUIMIENTO ADD CONSTRAINT PK_CODIGO_SEGUIMIENTO PRIMARY KEY ( CODIGO_SEGUIMIENTO );

/*-------------------------------------------*/
/*         CREACION CLAVES FORANEAS          */
/*-------------------------------------------*/

ALTER TABLE PROYECTOTALLER.TABLA_SEGUIMIENTO ADD CONSTRAINT FK_ASIGNATURA_SEGUIMIENTO 
FOREIGN KEY( CODIGO_ASIGNATURA ) REFERENCES PROYECTOTALLER.TABLA_ASIGNATURA( CODIGO_ASIGNATURA );

ALTER TABLE PROYECTOTALLER.TABLA_SEGUIMIENTO ADD CONSTRAINT FK_ESTUDIANTE_SEGUIMIENTO
FOREIGN KEY( ID_ESTUDIANTE ) REFERENCES PROYECTOTALLER.TABLA_ESTUDIANTE( ID_ESTUDIANTE );

ALTER TABLE PROYECTOTALLER.TABLA_ASIGNATURA ADD CONSTRAINT FK_PROFESOR_ASIGNATURA 
FOREIGN KEY( ID_PROFESOR ) REFERENCES PROYECTOTALLER.TABLA_PROFESOR( ID_PROFESOR );

ALTER TABLE PROYECTOTALLER.TABLA_GRUPO ADD CONSTRAINT FK_ASIGNATURA_GRUPO 
FOREIGN KEY( CODIGO_ASIGNATURA ) REFERENCES PROYECTOTALLER.TABLA_ASIGNATURA( CODIGO_ASIGNATURA );

ALTER TABLE PROYECTOTALLER.TABLA_NOTA ADD CONSTRAINT FK_SEGUIMIENTO_NOTA 
FOREIGN KEY( CODIGO_SEGUIMIENTO ) REFERENCES PROYECTOTALLER.TABLA_SEGUIMIENTO( CODIGO_SEGUIMIENTO );

/*-------------------------------------------*/
/*                 INSERCIONES               */
/*-------------------------------------------*/

INSERT INTO PROYECTOTALLER.TABLA_ESTUDIANTE VALUES (12345, 'JUAN', 'RENDON', 'INGENIERIA INFORMATICA', TO_DATE('08/19/1993', 'MM/DD/YYYY'), 301654, 'correo@yopmail.com' );
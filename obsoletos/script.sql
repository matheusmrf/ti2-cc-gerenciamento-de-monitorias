
CREATE SCHEMA IF NOT EXISTS 'mentorship_db' DEFAULT CHARACTER SET utf8 ;

USE 'mentorship_db' ;

CREATE TABLE IF NOT EXISTS 'mentorship_db'.'CURSO' (
  'codigo' INT NOT NULL UNIQUE,
  'nome' VARCHAR(100) NOT NULL,
  PRIMARY KEY ('codigo'),
)


CREATE TABLE IF NOT EXISTS 'mentorship_db'.'ALUNO' (
  'codigo' INT NOT NULL UNIQUE,
  'nome' VARCHAR(100) NOT NULL,
  'usuario' VARCHAR(45) NOT NULL UNIQUE,
  'senha' VARCHAR(45) NOT NULL,
  'curso' INT,
  PRIMARY KEY ('codigo'),
  CONSTRAINT 'fk_curso'
    FOREIGN KEY ('curso')
    REFERENCES 'mentorship_db'.'CURSO' ('codigo')
)


CREATE TABLE IF NOT EXISTS 'mentorship_db'.'MATERIA' (
  'codigo' INT NOT NULL,
  'nome' VARCHAR(45),
  'curso' VARCHAR(45),
  PRIMARY KEY ('codigo'),
  CONSTRAINT 'fk_curso'
    FOREIGN KEY ('curso')
    REFERENCES 'mentorship_db'.'CURSO' ('codigo')
)


CREATE TABLE IF NOT EXISTS 'mentorship_db'.'MONITORIA' (
  'id' INT NOT NULL AUTO_INCREMENT,
  'data_hora' DATETIME,
  'local' VARCHAR(45),
  'presencial' TINYINT,
  'codigo_monitor' INT,
  'codigo_materia' INT,
  PRIMARY KEY ('id'),
  CONSTRAINT 'fk_monitor'
    FOREIGN KEY ('codigo_monitor')
    REFERENCES 'mentorship_db'.'ALUNO' ('codigo'),
  CONSTRAINT 'fk_materia'
    FOREIGN KEY ('codigo_materia')
    REFERENCES 'mentorship_db'.'MATERIA' ('codigo')
)


CREATE TABLE IF NOT EXISTS 'mentorship_db'.'PARTICIPA' (
  'id' INT NOT NULL AUTO_INCREMENT,
  'aluno' INT,
  'monitoria' INT,
  PRIMARY KEY ('id'),
  CONSTRAINT 'fk_aluno'
    FOREIGN KEY ('aluno')
    REFERENCES 'mentorship_db'.'ALUNO' ('codigo'),
  CONSTRAINT 'fk_monitoria'
    FOREIGN KEY ('monitoria')
    REFERENCES 'mentorship_db'.'MONITORIA' ('id')
)


CREATE TABLE IF NOT EXISTS 'mentorship_db'.'POSSUI' (
  'id' INT NOT NULL,
  'curso' INT,
  'materia' INT,
  PRIMARY KEY ('id'),
  CONSTRAINT 'fk_curso'
    FOREIGN KEY ('curso')
    REFERENCES 'mentorship_db'.'CURSO' ('codigo'),
  CONSTRAINT 'fk_materia'
    FOREIGN KEY ('materia')
    REFERENCES 'mentorship_db'.'MATERIA' ('codigo')
)


CREATE DATABASE 'mentorship';
USE 'mentorship';

CREATE TABLE 'aluno' (
  'id' INT NOT NULL,
  'nome' VARCHAR(100) NOT NULL,
  'cpf' CHAR(11) NOT NULL UNIQUE,
  'senha' VARCHAR(50) NOT NULL,
  'is_monitor' INT NOT NULL,
  'curso' VARCHAR(50) NOT NULL,
  PRIMARY KEY ('id')
);

CREATE TABLE 'monitoria' (
  'id' INT NOT NULL,
  'dia' VARCHAR(20) NOT NULL,
  'hora' VARCHAR(50) NOT NULL,
  'presencial' BOOL NOT NULL,
  'monitor_id' INT NOT NULL,
  'local_monitoria' VARCHAR(50),
  'materia' VARCHAR(50) NOT NULL,
  PRIMARY KEY ('id'),
  CONSTRAINT 'fk_aluno'
    FOREIGN KEY ('monitor_id')
    REFERENCES 'aluno' ('id')
);

CREATE TABLE 'favoritos' (
  'id' INT NOT NULL,
  'aluno_id' INT NOT NULL,
  'monitoria_id' INT NOT NULL,
  PRIMARY KEY ('id'),
  CONSTRAINT 'fk_aluno'
    FOREIGN KEY ('aluno_id')
    REFERENCES 'aluno' ('id'),
  CONSTRAINT 'fk_monitoria'
    FOREIGN KEY ('monitoria_id')
    REFERENCES 'monitoria' ('id')
    ON DELETE CASCADE
);
 
CREATE TABLE 'feedback' (
  'id' INT NOT NULL,
  'mensagem' VARCHAR(2000) NOT NULL,
  'aluno_id' INT NOT NULL,
  PRIMARY KEY ('id'),
  CONSTRAINT 'fk_aluno'
    FOREIGN KEY ('aluno_id')
    REFERENCES 'aluno' ('id')
);
 

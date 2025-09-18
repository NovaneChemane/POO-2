create database HouseHolder2_bd;
CREATE TABLE activos (
  IdActivos int unsigned NOT NULL AUTO_INCREMENT,
  TipoActivos varchar(45) DEFAULT NULL,
  ValorActivos double DEFAULT NULL,
  DataActivos date DEFAULT NULL,
  PRIMARY KEY(IdActivos));
  
  CREATE TABLE dependentes (
  IdDependente int unsigned NOT NULL AUTO_INCREMENT,
  Nome varchar(45) DEFAULT NULL,
  Tipo varchar(45) DEFAULT NULL,
  PRIMARY KEY (IdDependente),
  UNIQUE KEY unique_nome (Nome));
  
  CREATE TABLE despesas (
  IdDespesas int unsigned NOT NULL AUTO_INCREMENT,
  Designação varchar(45) DEFAULT NULL,
  QuantiaTotal double DEFAULT NULL,
  PrazoDePagamento date DEFAULT NULL,
  PRIMARY KEY(IdDespesas));
  
  CREATE TABLE dívidas (
  IdDívidas int unsigned NOT NULL AUTO_INCREMENT,
  Estado varchar(45) DEFAULT NULL,
  Designação varchar(45) DEFAULT NULL,
  valor double DEFAULT NULL,
  dataDívidas date DEFAULT NULL,
  PRIMARY KEY(IdDívidas));
  
  CREATE TABLE especificação (
  IdEspecificação int unsigned NOT NULL AUTO_INCREMENT,
  Quantia double DEFAULT NULL,
  dataEspecificação date DEFAULT NULL,
  nome varchar(45) DEFAULT NULL,
  PRIMARY KEY (IdEspecificação),
  KEY fk_nome_dependentes (nome),
  CONSTRAINT fk_nome_dependentes FOREIGN KEY (nome) REFERENCES dependentes (Nome) ON DELETE CASCADE ON UPDATE CASCADE);
  
  CREATE TABLE usuário (
  numerodependentes int DEFAULT NULL,
  Nome varchar(45) NOT NULL,
  Idade int DEFAULT NULL,
  Email varchar(45) NOT NULL,
  pass varchar(45) DEFAULT NULL,
  apelido varchar(45) DEFAULT NULL,
  confirmarpass varchar(45) DEFAULT NULL,
  UNIQUE KEY unique_email (Email));
  
  CREATE TABLE user (
  pass varchar(45) DEFAULT NULL,
  Email varchar(45) DEFAULT NULL,
  KEY fk_email_usuário (Email),
  KEY fk_pass_usuário (pass),
  CONSTRAINT fk_email_usuário FOREIGN KEY (Email) REFERENCES usuário (Email) ON DELETE CASCADE ON UPDATE CASCADE);
 
    alter table dependentes
  add column emailUsuario varchar(45) not null,
  Add constraint fk_email_dependentes
  foreign key (emailUsuario) references usuário(email);
  
  alter table activos
  add column emailUsuario varchar(45) not null,
  Add constraint fk_email_activos
  foreign key (emailUsuario) references usuário(email);
  
  alter table despesas
  add column emailUsuario varchar(45) not null,
  Add constraint fk_email_despesas
  foreign key (emailUsuario) references usuário(email);
  
  alter table dívidas
  add column emailUsuario varchar(45) not null,
  Add constraint fk_email_dividas
  foreign key (emailUsuario) references usuário(email);
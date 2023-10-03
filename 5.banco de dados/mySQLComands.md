# SLQ


# Data types
- Numbers: int, big int, double, decimal(6,2) # numeric precision
- Text: varchar(50) # text and length
- Binary: bit(1)
- Time: time

___

# Database selections
```console
use [database-name];
```

# Creation

### create, alter, delete
```console
create database [database-name];
```

### create table
```console
create table tarefa(
	id bigint not null primary key auto_increment,
	titulo varchar(50) not null,
	descricao varchar(150),
	completa bit(1) not null default(b'0'),
	id_lista bigint not null
);

create table lista(
	id bigint not null primary key auto_increment,
	nome varchar(50) not null,
	cap_max int
);

create table checklist_item(
	id bigint not null primary key auto_increment,
	titulo varchar(50) not null,
	descricao varchar(150)
);

create table checklist(
	id_tarefa bigint not null,
	id_checklist_item bigint not null,
	completa bit(1) not null default(b'0'),
	foreign key (id_tarefa) references tarefa(id),
	foreign key (id_checklist_item) references checklist_item(id),
	primary key (id_tarefa, id_checklist_item)
);
```

### alter table
```console
#adicionando chave estrangeira de lista na tabela tarefa
alter table tarefa add constraint FK_LISTA_TAREFA foreign key (id_lista) references lista(id);

#add column to table
alter table tarefa add column id_lista bigint not null;

#change column properties
alter table tarefa modify column id_lista bigint not null;

#add foreign key to an column
alter table item add foreign key (id_franquia) references franquia(id);
```

# Manipulation

### insert, update, search/select, inner join

### insert
```console
insert into lista(nome, cap_max) values ('jogatina', 10);
insert into tarefa(titulo, descricao, id_lista) values ('cyberpunk 2077', 'all afternoon', 1);
```

### update
```console
update tarefa set completa = 1 where id = 2;
update tarefa set titulo = 'ytube The Quarry',descricao = 'MaxMRM' where id = 3;
#where titulo has Shreck someplace
update item set id_franquia = 1 where titulo like '%Skreck%';
```

### selection
```console
select * from tarefa t;
select * from tarefa where id_lista = 1 and completa = 0;
select titulo,descricao from tarefa where id_lista = 2;
select * from tarefa where id_lista in (1,2);
select i.titulo from item i where id_franquia is null;

#order by decrescent order limited by one ocurrance:	desc/asc
select l.autor, i.titulo from livro l
inner join item i on i.id = l.id_item
order by l.ano_publicacao desc limit 1;

#seleciona itens que tem id junto a tabela dvd
select i.* from item i
inner join dvd d on d.id_item = i.id;

#all itens in left table
select i.* from item i
left join dvd d on d.id_item = i.id;

#all itens that arent in right table
select i.* from item i
right outer dvd d on d.id_item = i.id;

#all itens that aren't dvds
select i.* from item i
left join dvd d on d.id_item = i.id
where d.id_item  is null;

#sql functions - max(values), count(values), sum(values)
#select max value and group by item.titulo
select i.titulo, max(i.valor_venda) from item i
join dvd d on d.id_item  = i.id 
group by i.titulo;

#find common line betwen genero & item / select movies where genero = 'Aventura'
select i.* from item i
inner join genero_item gi on gi.id_item = i.id
inner join genero g on g.id = gi.id_genero 
where g.nome = 'Aventura';
```

# Deletation

### delete database, table, column...

### delete database
```console
drop database [database-name];
```

### delete table
```console
drop table [table-name];
```
### delete line
```console
delete from tarefa where id_lista = 2;

delete from lista where id = 2;
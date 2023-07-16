# SQLite


# Creation

### create database
```console
CREATE DATABASE base_name;
```

### create table
```console
CREATE TABLE table_name(
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    default_value INTEGER NOT NULL DEFAULT(0),
    id_otherINTEGER NOT NULL,
    PRIMARY KEY (idtableone, idtabletwo),
    FOREIGN KEY (id_other) REFERENCES other_table(id_from_other_table)
);
```

# Alter/Update

```console
# ADD FIELD IN CREATED TABLE
ALTER TABLE checklist ADD COLUMN completo INTEGER NOT NULL DEFAULT 0;

# ALTER FIELD
ALTER TABLE checklist RENAME COLUMN completo TO completo;

# MODIFY CREATED FIELD
ALTER TABLE checklist MODIFY COLUMN completo BOOLEAN NOT NULL DEFAULT 0;

# EXCLUDE FIELD
ALTER TABLE checklist DROP completo;

# ADD FOREIGN KEY TO TABLE
ALTER TABLE table_name ADD CONSTRAINT FK_NAME_LIST 
FOREIGN KEY (id_other) REFERENCES other_table(id_from_other_table);
```

# Deletation

```console
# EXCLUDE DATABASE
DROP DATABASE base_name;

# EXCLUDE TABLE
DROP TABLE table_name;
```

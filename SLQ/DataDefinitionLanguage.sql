CREATE TABLE lines (
    id_line integer PRIMARY KEY,
    line_name varchar(15) NOT NULL,
);

CREATE TABLE categories (
    id_category integer PRIMARY KEY,
    category_name varchar(20) NOT NULL,
    id_line integer,
    FOREIGN KEY (id_line) REFERENCES lines(id_line)
);

CREATE TABLE models (
    id_model integer PRIMARY KEY,
    model_name varchar(25) NOT NULL,
    id_category integer,
    FOREIGN KEY (id_category) REFERENCES categories(id_category)
);
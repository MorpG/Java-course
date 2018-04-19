CREATE.SQL

CREATE TABLE roles (
  id   INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE rules (
  id   INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
  id       INTEGER PRIMARY KEY AUTOINCREMENT,
  login    VARCHAR(50)                   NOT NULL UNIQUE,
  password VARCHAR(50),
  role_id  INTEGER REFERENCES roles (id) NOT NULL
);

CREATE TABLE state (
  id   INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE categories (
  id   INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE item (
  id          INTEGER PRIMARY KEY AUTOINCREMENT,
  name        VARCHAR(50)                        NOT NULL,
  description TEXT                               NOT NULL,
  user_id     INTEGER REFERENCES users (id)      NOT NULL,
  category_id INTEGER REFERENCES categories (id) NOT NULL,
  state_id    INTEGER REFERENCES state (id)      NOT NULL
);


CREATE TABLE attaches (
  id          INTEGER PRIMARY KEY AUTOINCREMENT,
  description TEXT                         NOT NULL,
  item_id     INTEGER REFERENCES item (id) NOT NULL
);

CREATE TABLE comments (
  id          INTEGER PRIMARY KEY AUTOINCREMENT,
  description TEXT                         NOT NULL,
  item_id     INTEGER REFERENCES item (id) NOT NULL
);

INSERT INTO roles (name) VALUES ('root'), ('user'), ('guest');

INSERT INTO rules (name) VALUES ('create'), ('read'), ('update'), ('delete');

INSERT INTO users (login, password, role_id) VALUES
  ('root', 'root', (SELECT id
                    FROM roles
                    WHERE name = 'root')),
  ('user', 'user', (SELECT id
                    FROM roles
                    WHERE name = 'user')),
  ('guest', 'guest', (SELECT id
                      FROM roles
                      WHERE name = 'guest'));

INSERT INTO state (name) VALUES ('new'), ('in work'), ('close');

INSERT INTO categories (name) VALUES ('S1'), ('S2'), ('S3');

INSERT INTO item (name, description, user_id, category_id, state_id) VALUES
  (
    'item_1',
    'desc One',
    (SELECT id
     FROM users
     WHERE login = 'user'),
    (SELECT id
     FROM categories
     WHERE name = 'S1'),
    (SELECT id
     FROM state
     WHERE name = 'new')
  ),
  (
    'item_2',
    'desc Two',
    (SELECT id
     FROM users
     WHERE login = 'user'),
    (SELECT id
     FROM categories
     WHERE name = 'S2'),
    (SELECT id
     FROM state
     WHERE name = 'close')
  );

INSERT INTO comments (description, item_id) VALUES
  (
    'Comment short',
    (SELECT id
     FROM item
     WHERE name = 'item_1')
  ),
  (
    'Comment',
    (SELECT id
     FROM item
     WHERE name = 'item_2')
  );

INSERT INTO attaches (description, item_id) VALUES (
  'attach', (SELECT id
             FROM item
             WHERE name = 'item_2')
)
CREATE TABLE students (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(100),
                          email VARCHAR(100),
                          age INT
);

INSERT INTO students (name, email, age) VALUES
                                            ('Ali', 'ali@gmail.com', 22),
                                            ('Sara', 'sara@gmail.com', 21),
                                            ('Omar', 'omar@gmail.com', 23);
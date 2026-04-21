CREATE TABLE users (
id BIGSERIAL PRIMARY KEY,
user_id UUID UNIQUE,
user_name VARCHAR(50),
first_name VARCHAR(50),
telegram_user_id BIGINT UNIQUE
);

CREATE TABLE messages (
id BIGSERIAL PRIMARY KEY,
message_id UUID UNIQUE,
message_text text,
user_id BIGINT,
FOREIGN KEY (user_id) REFERENCES users(id)
);
CREATE TABLE if NOT EXISTS messages (
    id               BIGSERIAL PRIMARY KEY,
    message_text     VARCHAR (255) NOT NULL,
    create_timestamp TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_time
)
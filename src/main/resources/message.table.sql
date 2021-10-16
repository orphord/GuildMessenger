CREATE TABLE if NOT EXISTS messages (
    id               BIGSERIAL PRIMARY KEY,
    sender_id        VARCHAR (255) NOT NULL,
    receiver_id      VARCHAR (255) NOT NULL,
    message_text     VARCHAR (255) NOT NULL,
    create_timestamp TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_time
)
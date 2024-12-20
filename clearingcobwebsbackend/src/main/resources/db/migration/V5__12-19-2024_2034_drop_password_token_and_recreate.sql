DROP TABLE "password_reset_tokens";

CREATE TABLE "password_reset_tokens"(
  "id" int GENERATED ALWAYS AS IDENTITY,
  "token" text,
  "token_expiration_date" time,
  "user_id" int,
  FOREIGN KEY ("user_id") REFERENCES users("id"),
  PRIMARY KEY ("id")
)
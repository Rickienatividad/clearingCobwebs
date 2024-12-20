CREATE TABLE "password_reset_tokens" (
  "id" int NOT NULL,
  "token" text,
  "token_expiration_date" time,
  "user_id" int REFERENCES users("id"),
  PRIMARY KEY ("id")
)
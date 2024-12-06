DROP TABLE "users";

CREATE TABLE "users" (
  "id" int GENERATED ALWAYS AS IDENTITY,
  "first_name" text,
  "last_name" text,
  "email" text,
  "password" text,
  PRIMARY KEY ("id")
)
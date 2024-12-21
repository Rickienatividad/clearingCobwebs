ALTER TABLE "password_reset_tokens"
  DROP COLUMN "token_expiration_date";

ALTER TABLE "password_reset_tokens"
  ADD COLUMN "token_expiration" TIMESTAMP;
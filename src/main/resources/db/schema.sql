CREATE TABLE accounts (
    id BIGINT PRIMARY KEY,
    customer_id VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
);

CREATE TABLE balances (
    id BIGINT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    currency VARCHAR(3) NOT NULL,
    available_amount NUMERIC(19,4) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE transactions (
  id SERIAL PRIMARY KEY,
  account_id BIGINT NOT NULL,
  amount DECIMAL(19, 4) NOT NULL,
  currency VARCHAR(3) NOT NULL,
  direction VARCHAR(4) NOT NULL,
  description VARCHAR(255) NOT NULL,
  balance_after DECIMAL(19, 4) NOT NULL,
  FOREIGN KEY (account_id) REFERENCES accounts(id)
);
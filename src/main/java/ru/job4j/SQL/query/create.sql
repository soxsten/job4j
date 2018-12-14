CREATE TABLE "type" (
  "id" SERIAL CONSTRAINT "pk_type" PRIMARY KEY,
  "name" TEXT NOT NULL
);

CREATE TABLE "product" (
  "id" SERIAL CONSTRAINT "pk_product" PRIMARY KEY,
  "name" TEXT NOT NULL,
  "type_id" INTEGER NOT NULL,
  "expired_date" DATE,
  "price" DOUBLE PRECISION
);

ALTER TABLE "product" ADD CONSTRAINT "fk_product__type_id" FOREIGN KEY ("type_id") REFERENCES "type" ("id")
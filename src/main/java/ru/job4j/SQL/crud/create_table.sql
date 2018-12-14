CREATE TABLE "category_" (
  "id" SERIAL CONSTRAINT "pk_category_" PRIMARY KEY,
  "name" VARCHAR(1000) NOT NULL
);

CREATE TABLE "role_" (
  "id" SERIAL CONSTRAINT "pk_role_" PRIMARY KEY,
  "name" TEXT NOT NULL
);

CREATE TABLE "rule_" (
  "id" SERIAL CONSTRAINT "pk_rule_" PRIMARY KEY,
  "name" VARCHAR(2000) NOT NULL
);

CREATE TABLE "role__rules" (
  "role_" INTEGER NOT NULL,
  "rule_" INTEGER NOT NULL,
  CONSTRAINT "pk_role__rules" PRIMARY KEY ("role_", "rule_")
);

ALTER TABLE "role__rules" ADD CONSTRAINT "fk_role__rules__role_" FOREIGN KEY ("role_") REFERENCES "role_" ("id");

ALTER TABLE "role__rules" ADD CONSTRAINT "fk_role__rules__rule_" FOREIGN KEY ("rule_") REFERENCES "rule_" ("id");

CREATE TABLE "state_" (
  "id" SERIAL CONSTRAINT "pk_state_" PRIMARY KEY,
  "name" VARCHAR(1000) NOT NULL
);

CREATE TABLE "user_" (
  "id" SERIAL CONSTRAINT "pk_user_" PRIMARY KEY,
  "name" VARCHAR(10000) NOT NULL,
  "role_" INTEGER NOT NULL
);

ALTER TABLE "user_" ADD CONSTRAINT "fk_user___role_" FOREIGN KEY ("role_") REFERENCES "role_" ("id");

CREATE TABLE "item_" (
  "id" SERIAL CONSTRAINT "pk_item_" PRIMARY KEY,
  "name" VARCHAR(1000) NOT NULL,
  "category_" INTEGER NOT NULL,
  "state_" INTEGER NOT NULL,
  "user_" INTEGER NOT NULL
);

ALTER TABLE "item_" ADD CONSTRAINT "fk_item___category_" FOREIGN KEY ("category_") REFERENCES "category_" ("id");

ALTER TABLE "item_" ADD CONSTRAINT "fk_item___state_" FOREIGN KEY ("state_") REFERENCES "state_" ("id");

ALTER TABLE "item_" ADD CONSTRAINT "fk_item___user_" FOREIGN KEY ("user_") REFERENCES "user_" ("id");

CREATE TABLE "attach_" (
  "id" SERIAL CONSTRAINT "pk_attach_" PRIMARY KEY,
  "path" VARCHAR(1000) NOT NULL,
  "item_" INTEGER NOT NULL
);

ALTER TABLE "attach_" ADD CONSTRAINT "fk_attach___item_" FOREIGN KEY ("item_") REFERENCES "item_" ("id");

CREATE TABLE "comment_" (
  "id" SERIAL CONSTRAINT "pk_comment_" PRIMARY KEY,
  "message" VARCHAR(4000) NOT NULL,
  "item_" INTEGER NOT NULL
);

ALTER TABLE "comment_" ADD CONSTRAINT "fk_comment___item_" FOREIGN KEY ("item_") REFERENCES "item_" ("id")
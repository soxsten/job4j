CREATE TABLE engine (
	"id" SERIAL PRIMARY KEY,
	"name" VARCHAR(200) NOT NULL
);

CREATE TABLE transmission (
	"id" SERIAL PRIMARY KEY,
	"name" VARCHAR(200) NOT NULL
);

CREATE TABLE carbody (
	"id" SERIAL PRIMARY KEY,
	"name" VARCHAR(200) NOT NULL
);

CREATE TABLE car (
	"id" SERIAL PRIMARY KEY,
	"name" VARCHAR(200),
	engine_id INT REFERENCES engine(id) NOT NULL,
	transmission_id INT REFERENCES transmission(id) NOT NULL,
	carbody_id INT REFERENCES carbody(id) NOT NULL
);

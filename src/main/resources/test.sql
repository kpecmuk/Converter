DROP TABLE time, routes, stops, days;

CREATE TABLE days
(
  id    VARCHAR(7)   NOT NULL PRIMARY KEY,
  title VARCHAR(100) NOT NULL
);

CREATE TABLE stops
(
  id    VARCHAR(10) NOT NULL PRIMARY KEY,
  title VARCHAR(50) NOT NULL
);

CREATE TABLE routes
(
  id    VARCHAR(10) NOT NULL PRIMARY KEY,
  title VARCHAR(50) NOT NULL
);

CREATE TABLE time
(
  hour   SMALLINT,
  minute SMALLINT,
  route  VARCHAR(10) REFERENCES routes (id),
  stop   VARCHAR(10) REFERENCES stops (id),
  days   VARCHAR(7) REFERENCES days (id)
);

SELECT
  time.hour,
  time.minute,
  stops.title
FROM time
  RIGHT OUTER JOIN stops ON time.stop = stops.id
WHERE stop = '1a' AND hour = 6

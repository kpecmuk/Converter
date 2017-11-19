DROP TABLE schedule, routes, stops, days;

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

CREATE TABLE schedule
(
  time  TIME,
  route VARCHAR(10) REFERENCES routes (id),
  stop  VARCHAR(10) REFERENCES stops (id),
  days  VARCHAR(7) REFERENCES days (id)
);

SELECT
  schedule.time,
  stops.title,
  routes.title,
  days.title
FROM schedule
  JOIN stops ON schedule.stop = stops.id
  JOIN routes ON schedule.route = routes.id
  JOIN days ON schedule.days = days.id
WHERE stop = '10a' AND time >= '7:00' AND time <= '7:40' AND days = '12345'
ORDER BY time
FETCH FIRST 20 ROW ONLY;


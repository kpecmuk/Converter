CREATE TABLE IF NOT EXISTS schedule
(
  time  TIME,
  route VARCHAR(10) REFERENCES routes (id),
  stop  VARCHAR(10) REFERENCES stops (id),
  days  VARCHAR(7) REFERENCES days (id)
);
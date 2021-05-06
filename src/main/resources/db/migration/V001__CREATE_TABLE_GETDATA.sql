CREATE TABLE get_data (
    id BIGINT AUTO_INCREMENT,
    time_added TIMESTAMP NOT NULL,
    amount FLOAT(53),
    base_currency varchar(10),
    target_currency varchar(10),
    result FLOAT(53),
    PRIMARY KEY(id)
);
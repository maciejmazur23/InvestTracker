CREATE TABLE ACTIVE_TRANSACTIONS
(
    ACTIVE_TRANSACTION_ID SERIAL         NOT NULL,
    TICKER                VARCHAR(10)    NOT NULL,
    QUANTITY              NUMERIC(19, 6) NOT NULL,
    COURSE                NUMERIC(19, 6) NOT NULL,
    TOTAL_VALUE           NUMERIC(19, 6) NOT NULL,
    DATE_OF_TRANSACTION   DATE           NOT NULL,
    PRIMARY KEY (ACTIVE_TRANSACTION_ID)
);
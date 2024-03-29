CREATE TABLE ASSET_DETAILS
(
    ASSET_DETAILS_ID SERIAL      NOT NULL,
    ASSET_TYPE       VARCHAR(20) NOT NULL,
    TICKER           VARCHAR(10) NOT NULL,
    FULL_NAME        VARCHAR(20) NOT NULL,
    API_KEY          VARCHAR(20) NOT NULL,
    PRIMARY KEY (ASSET_DETAILS_ID),
    UNIQUE (TICKER, FULL_NAME, API_KEY)
);
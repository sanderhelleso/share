CREATE TABLE share_info
(
    id SERIAL,
    tot_downloads INTEGER NOT NULL DEFAULT 0,
    last_downloaded_at Date NULL DEFAULT NULL,
    expires_at Date NOT NULL DEFAULT NULL,
    del_on_enter BOOLEAN NOT NULL DEFAULT false,
    shared_from_country VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE share_info_shared_from_country
(
    id SERIAL,
    share_info_id INTEGER NOT NULl,
    country VARCHAR(255) NOT NULL DEFAULT NULL,
    downloads INTEGER NOT NULL DEFAULT NULL,
    FOREIGN KEY (share_info_id) REFERENCES share_info(id) ON DELETE CASCADE,
    PRIMARY KEY (id, share_info_id)
)
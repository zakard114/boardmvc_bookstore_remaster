create table persistent_logings (
                                    username varchar(64) not null,
                                    series varchar(64) primary key,
                                    token varchar(64) not null,
                                    last_used timestamp not null
);

create table diet
(
    id              bigint auto_increment primary key,
    day_of_week     tinyint                             not null,
    day             date                                not null,
    main_menu       varchar(255)                        null,
    common_menu     varchar(255)                        null,
    company_name    varchar(255)                        null,
    meal_type       enum ('DINNER', 'LUNCH', 'MORNING') null,
    restaurant_type enum ('STAFF', 'STUDENT')           null,
    is_deleted      bit                                 not null,
    created_at      datetime(6)                         not null,
    updated_at      datetime(6)                         not null
);


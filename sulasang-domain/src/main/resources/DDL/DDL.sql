create table diet
(
    id              bigint auto_increment primary key,
    day             date not null,
    day_of_week     enum ('FRIDAY', 'MONDAY', 'THURSDAY', 'TUESDAY', 'WEDNESDAY') null,
    common_menu     varchar(255) null,
    main_menu       varchar(255) null,
    company_name    varchar(255) null,
    meal_type       enum ('DINNER', 'LUNCH', 'MORNING') null,
    restaurant_type enum ('ACE_EDUCATION_CENTER_STAFF', 'ACE_EDUCATION_CENTER_STUDENT', 'AMARAENSE_STAFF', 'AMARAENSE_STUDENT') null,
    created_at      datetime(6) not null,
    updated_at      datetime(6) not null
);

create table api_statistics
(
    id                                         bigint auto_increment primary key,
    target_date                                date        not null,
    day_and_type_retrieve_api_call_count       int         not null default 0,
    day_and_type_retrieve_api_process_time_avg bigint      not null default 1,
    is_deleted                                 bit         not null,
    created_at                                 datetime(6) not null,
    updated_at                                 datetime(6) not null
);
